window.addEventListener('load', () => {

    let buscarPacienteIdForm = document.querySelector('#buscarPacientePorId');
    let buscarPacienteTodos = document.querySelector('#buscarTodosPaciente');

    buscarPacienteIdForm.addEventListener('submit', (event) => {

        event.preventDefault();

        let idPaciente = document.querySelector("#pacienteId").value;

        let reqUrl = '/pacientes/' + idPaciente;
        let reqSettings = {
            method: 'GET',
        }

        fetch(reqUrl, reqSettings).then(response => response.json()).then((paciente => {
            let tabla = document.querySelector('#listadoPacientes');
            tabla.innerHTML = "";
            tabla.innerHTML += `<tr>
            <td>${paciente.nombre}</td>
            <td>${paciente.apellido}</td>
            <td>${paciente.domicilio.calle}</td>
            <td>${paciente.domicilio.numero}</td>
            <td>${paciente.domicilio.localidad}</td>
            <td>${paciente.domicilio.provincia}</td>
            <td>${paciente.domicilio.pais}</td>
        </tr>`
        })).catch(error => {
            let tabla = document.querySelector('#listadoPacientes');
            tabla.innerHTML = "";
            tabla.innerHTML += `<tr>
            <td>${error}</td>
            <td>${error}</td>
            <td>${error}</td>
            <td>${error}</td>
            <td>${error}</td>
            <td>${error}</td>
            <td>${error}</td>
        </tr>`

        })
    })

    buscarPacienteTodos.addEventListener('submit', (event) => {

        event.preventDefault();

        let reqUrl = '/pacientes';
        let reqSettings = {
            method: 'GET',
        }

        fetch(reqUrl, reqSettings).then(response => response.json()).then((listado => {
            listarPacientes(listado);
        }))
    })
})

function listarPacientes(listaPacientes) {

    let tabla = document.querySelector('#listadoPacientes');
    tabla.innerHTML = "";

    listaPacientes.forEach((paciente) => {

        tabla.innerHTML += `<tr>
        <td>${paciente.nombre}</td>
        <td>${paciente.apellido}</td>
        <td>${paciente.domicilio.calle}</td>
        <td>${paciente.domicilio.numero}</td>
        <td>${paciente.domicilio.localidad}</td>
        <td>${paciente.domicilio.provincia}</td>
        <td>${paciente.domicilio.pais}</td>
    </tr>`

    })




}