window.addEventListener('load', () => {

  let crearToast = (alertType, msg) => `<div class="alert ${alertType}" role="alert">
  ${msg}
 </div>`

  let successClass = 'alert-success';
  let failureClass = 'alert-danger';

  let requestToast = document.querySelector('#requestToast');
  let actualizarOdontologoForm = document.querySelector('#actualizarOdontologo');

  actualizarOdontologoForm.addEventListener('submit', (event) => {

    event.preventDefault();

    let idOdontologo = document.querySelector("#idOdontologo").value;

    let reqBody = {
      nombre: document.querySelector("#nombre").value,
      apellido: document.querySelector("#apellido").value,
      matricula: document.querySelector("#matricula").value,
    }

    let reqUrl = '/odontologos/' + idOdontologo;
    let reqSettings = {
      method: 'PUT',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(reqBody)
    }

    fetch(reqUrl, reqSettings).then((response) => {
      requestToast.innerHTML = crearToast(successClass, 'El odontologo se actualizo exitosamente!');

    }).catch(() => {
      requestToast.innerHTML = crearToast(failureClass, 'Hubo un problema al actualizar el odontologo');
    })
  })
})