window.addEventListener('load', () => {

  let crearToast = (alertType, msg) => `<div class="alert ${alertType}" role="alert">
  ${msg}
 </div>`

  let successClass = 'alert-success';
  let failureClass = 'alert-danger';
  
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
        requestToast.innerHTML = crearToast(successClass, 'El paciente se borro exitosamente!');
  
      }).catch(() => {
        requestToast.innerHTML = crearToast(failureClass, 'Hubo un problema al actualizar el paciente');
      })
    })
  })