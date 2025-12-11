(


async function(){
  const tbody = document.querySelector('#cotTable tbody');
  const refresh = document.getElementById('refreshCotBtn');


  async function load(){
    tbody.innerHTML = '';
    const resp = await fetch('/api/cotizaciones');
    const items = await resp.json();
    items.forEach(c=>{
      const tr = document.createElement('tr');
      tr.innerHTML = `<td>${c.id}</td><td>${c.fecha}</td><td>${c.total}</td><td>${c.confirmada}</td>
        <td>
          ${c.confirmada ? '' : '<button class="btn" onclick="confirmar('+c.id+')">Confirmar venta</button>'}
          <button class="btn" onclick="verDetalles(${c.id})">Ver detalles</button>
        </td>`;
      tbody.appendChild(tr);
    });
  }

  window.confirmar = async function(id){
    if (!confirm('¿Confirmar cotizacion ' + id + ' ?')) return;
    const resp = await fetch('/api/cotizaciones/' + id + '/confirmar', { method: 'PUT' });
    if (!resp.ok) {
      const t = await resp.text();
      alert('waja error'+t);
    } else {
      alert('Venta confirmada');
    }
    load();
  };

/*
  window.verDetalles = function(id){
    // abrir con detalles
    fetch('/api/cotizaciones')
      .then(r=>r.json())
      .then(list=>{
        const c = list.find(x=>x.id==id);
        if (!c) return alert('No encontrado');
        let s = `Cotización ${c.id}\nFecha: ${c.fecha}\nTotal: ${c.total}\nConfirmada: ${c.confirmada}\nDetalles:\n`;
        (c.detalles || []).forEach(d=>{
          s += `- ${d.mueble?.nombre || 'mueble'} x${d.cantidad} => subtotal ${d.subtotal}\n`;
        });
        alert(s);
      });
  };
*/

  refresh && refresh.addEventListener('click', load);
  load();
})();
