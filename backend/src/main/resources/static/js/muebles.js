
(
async function(){
  const tbody = document.querySelector('#mueblesTable tbody');
  const refreshBtn = document.getElementById('refreshBtn');

  async function load(){
    tbody.innerHTML = '';
    const resp = await fetch('/api/muebles');
    const muebles = await resp.json();
    muebles.forEach(m => {
      const tr = document.createElement('tr');
      tr.innerHTML = `
        <td>${m.id}</td>
        <td>${m.nombre}</td>
        <td>${m.tipo}</td>
        <td>${m.precioBase}</td>
        <td>${m.stock}</td>
        <td>${m.estado}</td>
        <td>
          <a class="btn" href="/muebles/${m.id}/editar">Editar</a>
          <a class="btn" href="/muebles/${m.id}/variantes">Variantes</a>
          <button class="btn" data-id="${m.id}" onclick="desactivar(${m.id})">Desactivar</button>
          <button class="btn" onclick="eliminar(${m.id})">Eliminar</button>
        </td>`;
      tbody.appendChild(tr);
    });
  }

  window.desactivar = async function(id){
    if(!confirm('Desactivar mueble '+id+'?')) return;
    await fetch('/api/muebles/' + id + '/desactivar', {
    method: 'PUT'
    });
    load();
  };

  window.eliminar = async function(id){
    if(!confirm('Eliminar mueble '+id+'?')) return;
    await fetch('/api/muebles/' + id, {method: 'DELETE'});
    load();
  };

  refreshBtn.addEventListener('click', load);
  load();





})();
