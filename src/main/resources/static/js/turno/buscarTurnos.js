window.addEventListener('load', () => {

    let buscarTurnoIdForm = document.querySelector('#buscarTurnoPorId');
    let buscarTurnoTodos = document.querySelector('#buscarTodosTurnos');

    buscarTurnoIdForm.addEventListener('submit', (event) => {

        event.preventDefault();

        let idTurno = document.querySelector("#turnoId").value;

        let reqUrl = '/turnos/' + idTurno;
        let reqSettings = {
            method: 'GET',
        }

        fetch(reqUrl, reqSettings).then(response => response.json()).then((turno => {
            let tabla = document.querySelector('#listadoTurnos');
            tabla.innerHTML = "";
            tabla.innerHTML += `<tr>
            <td>${turno.odontologo}</td>
            <td>${turno.paciente}</td>
            <td>${turno.fecha}</td>
            <td>${turno.hora}</td>
        </tr>`
        })).catch(error => {
            let tabla = document.querySelector('#listadoTurnos');
            tabla.innerHTML = "";
            tabla.innerHTML += `<tr>
            <td>${error}</td>
            <td>${error}</td>
            <td>${error}</td>
        </tr>`

        })
    })

    buscarTurnoTodos.addEventListener('submit', (event) => {

        event.preventDefault();

        let reqUrl = '/turnos';
        let reqSettings = {
            method: 'GET',
        }

        fetch(reqUrl, reqSettings).then(response => response.json()).then((listado => {
            listarTurnos(listado);
        }))
    })
})

function listarTurnos(listaTurnos) {

    let tabla = document.querySelector('#listadoTurnos');
    tabla.innerHTML = "";

    listaTurnos.forEach((turno) => {

        tabla.innerHTML += `<tr>
        <td>${turno.odontologo}</td>
        <td>${turno.paciente}</td>
        <td>${turno.fecha}</td>
        <td>${turno.hora}</td>
    </tr>`

    })




}