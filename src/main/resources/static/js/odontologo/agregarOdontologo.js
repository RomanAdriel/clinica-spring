window.addEventListener('load', () => {

  let crearToast = (alertType, msg) => `<div class="alert ${alertType}" role="alert">
 ${msg}
</div>`

  let successClass = 'alert-success';
  let failureClass = 'alert-danger';

  let requestToast = document.querySelector('#requestToast');
  let agregarOdontologoForm = document.querySelector('#agregarOdontologo');

  agregarOdontologoForm.addEventListener('submit', (event) => {

    event.preventDefault();

    let reqBody = {
      nombre: document.querySelector("#nombre").value,
      apellido: document.querySelector("#apellido").value,
      matricula: document.querySelector("#matricula").value,
    }

    let reqUrl = '/odontologos';
    let reqSettings = {
      method: 'POST',
      headers: {
        'Content-Type': 'application/json'
      },
      body: JSON.stringify(reqBody)
    }

    fetch(reqUrl, reqSettings).then((response) => {
      requestToast.innerHTML = crearToast(successClass, 'El odontologo se creo exitosamente!');

    }).catch(() => {
      requestToast.innerHTML = crearToast(failureClass, 'Hubo un problema al crear el odontologo');
    })
  })
})