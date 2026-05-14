const URLFIJA = "http://localhost:8080/api/reservas/";

async function listarCLiente() {
  try {
    const response = await fetch(URLFIJA + "listar/clientes");
    if (!response.ok) throw new Error("Error al conectar con la API");

    const data = await response.json();
    let body =
      '<option value="" selected disabled>Seleccione un cliente...</option>';
    data.forEach((task) => {
      body += `<option value="${task.id}">${task.nombre}</option>`;
    });
    document.getElementById("selectCliente").innerHTML = body;
  } catch (error) {
    console.log("Error al acceder a la api clientes");
  }
}

async function listarTrabajadores() {
  try {
    const response = await fetch(URLFIJA + "listar/trabajadores");
    if (!response.ok) throw new Error("Error al conectar con la API");
    const data = await response.json();
    let body =
      '<option value="" selected disabled>Seleccione un trabajador...</option>';
    data.forEach((task) => {
      body += `
        <option value="${task.id}">${task.nombre}</option>
        `;
    });
    document.getElementById("selectTrabajador").innerHTML = body;
  } catch (error) {
    console.log("Error al accerder a la api");
  }
}

async function sincronizarEstado() {
  try {
    const fecha = document.getElementById("fechaAgenda").value;
    if (!fecha) return;

    const response = await fetch(URLFIJA + "listar/reservas");
    const reservas = await response.json();

    const filas = document.querySelectorAll("#tablaCitas tr");

    filas.forEach((fila) => {
      const btn = fila.querySelector(".btn-agendar");
      if (!btn) return;

      const horaFila = btn.dataset.horaInicio;
      const badge = fila.querySelector(".badge");

      const reservaOcupada = reservas.find(
        (r) => r.fecha === fecha && r.horaInicio === horaFila,
      );

      if (reservaOcupada) {
        const nombre = reservaOcupada.cliente
          ? reservaOcupada.cliente.nombre
          : "Ocupado";
        if (badge) {
          badge.textContent = nombre;
          badge.classList.remove("bg-success");
          badge.classList.add("bg-info");
        }
        btn.disabled = true;
        btn.textContent = "Reservado";
        btn.classList.remove("btn-primary");
        btn.classList.add("btn-secondary");
      } else {
        if (badge) {
          badge.textContent = "Libre";
          badge.classList.remove("bg-info");
          badge.classList.add("bg-success");
        }
      }
    });
  } catch (error) {
    console.error("Error al sincronizar:", error);
  }
}

listarCLiente();
listarTrabajadores();
document.addEventListener("DOMContentLoaded", sincronizarEstado);
document
  .getElementById("fechaAgenda")
  .addEventListener("change", sincronizarEstado);

document.getElementById("tablaCitas").addEventListener("click", async (e) => {
  if (e.target.classList.contains("btn-agendar")) {
    const select = document.getElementById("selectCliente");
    const selectWorker = document.getElementById('selectTrabajador');
    const cliente = select.value;
    const trabajador = selectWorker.value;
    const fecha = document.getElementById("fechaAgenda").value;
    const nombreCliente = select.options[select.selectedIndex].text;
    const nombreTrabajador = selectWorker.options[selectWorker.selectedIndex].text;

    if (!cliente || !fecha) {
      Swal.fire({
        icon: "error",
        title: "Atención",
        text: "Debes seleccionar un cliente y una fecha",
      });
      return;
    }

    const reserva = {
      fecha: fecha,
      horaInicio: e.target.dataset.horaInicio,
      horaFin: e.target.dataset.horaFin,
      clienteId: cliente,
      trabajadorId: trabajador
    };

    await crearReserva(reserva, nombreCliente, e.target, nombreTrabajador);
  }
});

async function crearReserva(reserva, nombreCliente, btnPulsado, nombreTrabajador) {
  try {
    const urlCrearCita = URLFIJA + "crear/reserva";
    const response = await fetch(urlCrearCita, {
      method: "POST",
      headers: { "Content-Type": "application/json" },
      body: JSON.stringify(reserva),
    });

    if (response.ok) {
      const fila = btnPulsado.closest("tr");
      const badge = fila.querySelector(".badge");

      if (badge) {
        badge.textContent = nombreCliente;
        badge.classList.replace("bg-success", "bg-info");
      }

      btnPulsado.disabled = true;
      btnPulsado.textContent = "Reservado";
      btnPulsado.classList.replace("btn-primary", "btn-secondary");

      Swal.fire("¡Éxito!", "Reserva creada correctamente", "success");
    } else {
      throw new Error("Error en la respuesta del servidor");
    }
  } catch (error) {
    console.log("Error al acceder a la API");
  }
}
