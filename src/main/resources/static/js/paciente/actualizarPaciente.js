window.addEventListener('load', () => {

  let crearToast = (alertType, msg) => `<div class="alert ${alertType}" role="alert">
  ${msg}
 </div>`

  let successClass = 'alert-success';
  let failureClass = 'alert-danger';

  let requestToast = document.querySelector('#requestToast');
  let actualizarPacienteForm = document.querySelector('#actualizarPaciente');

  actualizarPacienteForm.addEventListener('submit', (event) => {

    event.preventDefault();

    let idPaciente = document.querySelector('#idPaciente').value;

    let reqBody = {
      nombre: document.querySelector("#nombre").value,
      apellido: document.querySelector("#apellido").value,
      dni: parseInt(document.querySelector("#dni").value),
      fechaAlta: null,
      domicilio: {
        calle: document.querySelector("#calle").value,
        numero: parseInt(document.querySelector("#numero").value),
        localidad: document.querySelector("#localidad").value,
        provincia: document.querySelector("#provincia").value,
        pais: 'Argentina'
      }
    }

    let reqUrl = '/pacientes/' + idPaciente;
    let reqSettings = {
      method: 'PUT',
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