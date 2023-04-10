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
  
    let borrarPacienteForm = document.querySelector('#borrarPaciente');
  
    borrarPacienteForm.addEventListener('submit', (event) => {
  
      event.preventDefault();
  
      let idPaciente = document.querySelector("#idPaciente").value;
  
      let reqUrl = '/pacientes/' + idPaciente;
      let reqSettings = {
        method: 'DELETE',
      }
  
      fetch(reqUrl, reqSettings).then((response) => {
        requestToast.innerHTML = crearToast('El paciente se borro exitosamente!');
  
      }).catch(() => {
        requestToast.innerHTML = crearToast('Hubo un problema al actualizar el paciente');
      })
    })
  })