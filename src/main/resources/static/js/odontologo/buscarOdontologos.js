window.addEventListener('load', () => {

    let buscarOdontologoIdForm = document.querySelector('#buscarOdontologoPorId');
    let buscarOdontologoTodos = document.querySelector('#buscarTodosOdontologos');

    buscarOdontologoIdForm.addEventListener('submit', (event) => {

        event.preventDefault();

        let idOdontologo = document.querySelector("#odontologoId").value;

        let reqUrl = '/odontologos/' + idOdontologo;
        let reqSettings = {
            method: 'GET',
        }

        fetch(reqUrl, reqSettings).then(response => response.json()).then((odontologo => {
            let tabla = document.querySelector('#listadoOdontologos');
            tabla.innerHTML = "";
            tabla.innerHTML += `<tr>
            <td>${odontologo.nombre}</td>
            <td>${odontologo.apellido}</td>
            <td>${odontologo.matricula}</td>
        </tr>`
        })).catch(error => {
            let tabla = document.querySelector('#listadoOdontologos');
            tabla.innerHTML = "";
            tabla.innerHTML += `<tr>
            <td>${error}</td>
            <td>${error}</td>
            <td>${error}</td>
        </tr>`

        })
    })

    buscarOdontologoTodos.addEventListener('submit', (event) => {

        event.preventDefault();

        let reqUrl = '/odontologos';
        let reqSettings = {
            method: 'GET',
        }

        fetch(reqUrl, reqSettings).then(response => response.json()).then((listado => {
            listarOdontologos(listado);
        }))
    })
})

function listarOdontologos(listaOdontologos) {

    let tabla = document.querySelector('#listadoOdontologos');
    tabla.innerHTML = "";

    listaOdontologos.forEach((odontologo) => {

        tabla.innerHTML += `<tr>
        <td>${odontologo.nombre}</td>
        <td>${odontologo.apellido}</td>
        <td>${odontologo.matricula}</td>
    </tr>`

    })




}