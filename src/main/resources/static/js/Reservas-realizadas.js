const URL_BASE = "http://localhost:8080/api/reservas/";

async function listarReservas() {
    const tabla = document.getElementById('listaReservas');

    if (!tabla) return;

    try {
        const response = await fetch(URL_BASE + 'listar/reservas');

        if (!response.ok) throw new Error("Error en el servidor");

        const data = await response.json();

        let html = '';

        if (data.length === 0) {
            html = `<tr><td colspan="5" class="text-center p-4 text-muted">No hay citas registradas</td></tr>`;
        } else {
            data.forEach(reserva => {
                html += `
                <tr>
                    <td><span class="fw-bold">${reserva.fecha}</span></td>
                    <td><span class="time-badge">${reserva.horaInicio} - ${reserva.horaFin}</span></td>
                    <td>
                        <div class="client-info">
                            <div class="client-avatar"><i class="bi bi-person"></i></div>
                            <div>
                                <div class="fw-bold">${reserva.cliente ? reserva.cliente.nombre : 'Cliente no encontrado'}</div>
                            </div>
                        </div>
                    </td>
                    <td class="text-end">
                        <button class="btn btn-outline-danger btn-sm rounded-pill" onclick="eliminar(${reserva.id})">
                            <i class="bi bi-trash"></i> Cancelar
                        </button>
                    </td>
                </tr>`;
            });
        }

        tabla.innerHTML = html;

    } catch (error) {
        console.error("Error al listar:", error);
        tabla.innerHTML = `<tr><td colspan="5" class="text-center text-danger p-4">No se pudo conectar con la API</td></tr>`;
    }
}

async function listarReservaPorTrabajador(idTrabajador) {
    try {
        const response = await fetch(URL_BASE + `listar/trabajador/${idTrabajador}`, {
            "method": "GET",
            "headers": {
                'Content-Type': 'application/json'
            },
        })

        if (!response.ok) throw new Error("No hubo respuesta de la API");
        const data = await response.json();
        let body = '';
        data.forEach(d => {
            body +=
                `
                <tr>
                    <td><span class="fw-bold">${d.fecha}</span></td>
                    <td><span class="time-badge">${d.horaInicio} - ${d.horaFin}</span></td>
                    <td>
                        <div class="client-info">
                            <div class="client-avatar"><i class="bi bi-person"></i></div>
                            <div>
                                <div class="fw-bold">${d.cliente ? d.cliente.nombre : 'Cliente no encontrado'}</div>
                            </div>
                        </div>
                    </td>
                    <td>
                        <div class="client-info">
                            <div class="client-avatar"><i class="bi bi-person"></i></div>
                            <div>
                                <div class="fw-bold">${d.trabajador ? d.trabajador.nombre : 'Trabajador no encontrado'}</div>
                            </div>
                        </div>
                    </td>
                    <td class="text-end">
                        <button class="btn btn-outline-danger btn-sm rounded-pill" onclick="eliminar(${d.id})">
                            <i class="bi bi-trash"></i> Cancelar
                        </button>
                    </td>
                </tr>
        `
        });
        document.getElementById('listaReservas').innerHTML = body;

    } catch (error) {
        console.log("Error al acceder a la API")
    }

}

document.addEventListener('DOMContentLoaded', listarReservas);

async function listarTrabajadores() {
    try {
        const response = await fetch(URL_BASE + "listar/trabajadores", {
            "method": "GET",
            "headers": {
                'Content-Type': 'application/json'
            }
        })
        if (!response.ok) throw new Error("Error al acceder a la API");
        const data = await response.json();
        let body = '<option value="" selected disabled>Seleccione un trabajador...</option>';
        data.forEach(d => {
            body +=
                `
            <option value = "${d.id}">${d.nombre}</option>
            `
        })
        document.getElementById('trabajadoresSelect').innerHTML = body;
    } catch (error) {
        console.log("Error al acceder a la API")
    }

}

listarTrabajadores();

async function eliminar(id) {
    try {
        const urlEliminar = URL_BASE + `eliminar/reserva/${id}`;
        const response = await fetch(urlEliminar, {
            "method": "DELETE",
            "headers": {
                'Content-Type': 'application/json'
            },
        })
        if (!response.ok) {
            throw new Error("Error al iniciar la API")
        }
        await listarReservas();
    } catch (error) {
        console.log("Error al iniciar la API")
    }
}
