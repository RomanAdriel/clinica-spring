window.addEventListener('load', () => {

  let crearToast = (alertType, msg) => `<div class="alert ${alertType}" role="alert">
  ${msg}
 </div>`

  let successClass = 'alert-success';
  let failureClass = 'alert-danger';
   
     let requestToast = document.querySelector('#requestToast');
     let agregarPacienteForm = document.querySelector('#agregarPaciente');
   
     agregarPacienteForm.addEventListener('submit', (event) => {
   
       event.preventDefault();

      let fechaActual = obtenerFechaActual();
   
       let reqBody = {
         nombre: document.querySelector("#nombre").value,
         apellido: document.querySelector("#apellido").value,
         dni: parseInt(document.querySelector("#dni").value),
         fechaAlta: fechaActual,
         domicilio: {
            calle: document.querySelector("#calle").value,
            numero: parseInt(document.querySelector("#numero").value),
            localidad: document.querySelector("#localidad").value,
            provincia: document.querySelector("#provincia").value,
            pais: 'Argentina'
         }
       }
   
       let reqUrl = '/pacientes';
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

    if(parseInt(mes) < 10) {
      mes = `0${mes}`;
    }
    let fechaFormateada = `${anio}-${mes}-${dia}`;
    return fechaFormateada;
   }