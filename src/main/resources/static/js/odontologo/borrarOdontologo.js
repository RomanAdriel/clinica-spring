window.addEventListener('load', () => {

  let crearToast = (alertType, msg) => `<div class="alert ${alertType}" role="alert">
  ${msg}
 </div>`

  let successClass = 'alert-success';
  let failureClass = 'alert-danger';

  let requestToast = document.querySelector('#requestToast');

  let borrarOdontologoForm = document.querySelector('#borrarOdontologo');

  borrarOdontologoForm.addEventListener('submit', (event) => {

    event.preventDefault();

    let idOdontologo = document.querySelector("#idOdontologo").value;

    let reqUrl = '/odontologos/' + idOdontologo;
    let reqSettings = {
      method: 'DELETE',
    }

    fetch(reqUrl, reqSettings).then((response) => {
      requestToast.innerHTML = crearToast(successClass, 'El odontologo se borro exitosamente!');

    }).catch(() => {
      requestToast.innerHTML = crearToast(failureClass, 'Hubo un problema al actualizar el odontologo');
    })
  })
})