window.addEventListener('load', () => {

    let crearToast = (alertType, msg) => `<div class="alert ${alertType}" role="alert">
    ${msg}
   </div>`

    let successClass = 'alert-success';
    let failureClass = 'alert-danger';

    let requestToast = document.querySelector('#requestToast');
    let agregarTurnoSinObj = document.querySelector('#agregarTurnoSinObj');
    let agregarTurnoConObj = document.querySelector('#agregarTurnoConObj');

    agregarTurnoSinObj.addEventListener('submit', (event) => {

        event.preventDefault();

        let reqBody = {
            odontologo: {
                nombre: null,
                apellido: null,
                matricula: document.querySelector("#matricula").value,

            },
            paciente: {
                nombre: null,
                apellido: null,
                dni: document.querySelector("#dni").value,
                fechaAlta: null,
                domicilio: {
                    calle: null,
                    numero: null,
                    localidad: null,
                    provincia: null,
                    pais: null
                }
            },
            fecha: document.querySelector("#fecha").value,
            hora: document.querySelector("#hora").value + ":00",
        }

        let reqUrl = '/turnos';
        let reqSettings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(reqBody)
        }

        fetch(reqUrl, reqSettings).then((response) => {
            requestToast.innerHTML = crearToast(successClass, 'El paciente se creo exitosamente!');

        }).catch(() => {
            requestToast.innerHTML = crearToast(failureClass, 'Hubo un problema al crear el paciente');
        })
    })

    agregarTurnoConObj.addEventListener('submit', (event) => {

        let fechaActual = obtenerFechaActual();

        event.preventDefault();

        let reqBody = {
            odontologo: {
                nombre: document.querySelector("#nombreOdontologo").value,
                apellido: document.querySelector("#apellidoOdontologo").value,
                matricula: parseInt(document.querySelector("#matriculaNuevo").value),

            },
            paciente: {
                nombre: document.querySelector("#nombrePaciente").value,
                apellido: document.querySelector("#apellidoPaciente").value,
                dni: parseInt(document.querySelector("#dniNuevo").value),
                fechaAlta: fechaActual,
                domicilio: {
                    calle: document.querySelector("#calle").value,
                    numero: parseInt(document.querySelector("#numero").value),
                    localidad: document.querySelector("#localidad").value,
                    provincia: document.querySelector("#provincia").value,
                    pais: "Argentina"
                }
            },
            fecha: document.querySelector("#fecha").value,
            hora: document.querySelector("#hora").value + ":00",
        }

        let reqUrl = '/turnos';
        let reqSettings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(reqBody)
        }

        fetch(reqUrl, reqSettings).then((response) => {
            requestToast.innerHTML = crearToast(successClass, 'El paciente se creo exitosamente!');

        }).catch(() => {
            requestToast.innerHTML = crearToast(failureClass, 'Hubo un problema al crear el paciente');
        })
    })
})

function obtenerFechaActual() {

    let fecha = new Date();
    let dia = fecha.getDate();
    let mes = fecha.getMonth() + 1;
    let anio = fecha.getFullYear();

    if (parseInt(mes) < 10) {
        mes = `0${mes}`;
    }
    let fechaFormateada = `${anio}-${mes}-${dia}`;
    return fechaFormateada;
}