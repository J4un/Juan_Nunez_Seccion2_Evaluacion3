(

function(){

  const muebleId = window.MUEBLE_ID || (document.getElementById('muebleIdHidden') && document.getElementById('muebleIdHidden').value) || null;
  const tableBody = document.querySelector('#variantesTable tbody');
  const crearBtn = document.getElementById('crearVarBtn');

  if (!muebleId) {

    if (crearBtn) crearBtn.disabled = true;
    return;
  }

  function load(){

    tableBody.innerHTML = '';
    fetch('/api/variantes/mueble/' + muebleId)
      .then(r => {
        if (!r.ok) {
          throw new Error('error de vateiantes: (HTTP ' + r.status + ')');
        }
        return r.json();
      })
      .then(data=>{
        if(!Array.isArray(data)) data = [];
        data.forEach(v=>{
          const tr = document.createElement('tr');
          tr.innerHTML = `<td>${v.id}</td><td>${v.nombre}</td><td>${v.precioExtra}</td>`;
          tableBody.appendChild(tr);
        });
      })




      .catch(err=>{
      });
  }

  crearBtn.addEventListener('click', function(){
    const payload = {
      nombre: document.getElementById('varNombre').value,
      precioExtra: parseFloat(document.getElementById('varExtra').value) || 0
    };
    fetch('/api/variantes/mueble/' + muebleId, {
      method: 'POST',
      headers: {'Content-Type':'application/json'},
      body: JSON.stringify(payload)
    }).then(r => {
      if (!r.ok) return r.text().then(t => { throw new Error(t || ('HTTP ' + r.status)); });
      return r.json();
    }).then(resp=>{
      // refrescar
      document.getElementById('varNombre').value = '';
      document.getElementById('varExtra').value = '';
      load();
    }).catch(err=>{
    });
  });

  load();
})();
