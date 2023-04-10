window.addEventListener('load', () => {

 let crearToast = (msg) => `<div class="toast" role="alert" aria-live="assertive" aria-atomic="true">
    <div class="toast-header">
      <img src="..." class="rounded mr-2" alt="...">
      <strong class="mr-auto">Bootstrap</strong>
      <small class="text-muted">11 mins ago</small>
      <button type="button" class="ml-2 mb-1 close" data-dismiss="toast" aria-label="Close">
        <span aria-hidden="true">&times;</span>
      </button>
    </div>
    <div class="toast-body">
      ${msg}
    </div>
  </div>`

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
      requestToast.innerHTML = crearToast('El odontologo se creo exitosamente!');

      }).catch(() => {
      requestToast.innerHTML = crearToast('Hubo un problema al crear el odontologo');
    })
  })
})