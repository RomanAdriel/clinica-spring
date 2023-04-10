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
         requestToast.innerHTML = crearToast('El paciente se creo exitosamente!');
   
         }).catch(() => {
         requestToast.innerHTML = crearToast('Hubo un problema al crear el paciente');
       })
     })
   })