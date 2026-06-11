const token = localStorage.getItem('token');
if (!token) window.location.href = '/';

async function fetchClientes() {
    const response = await fetch('/api/clientes', {
        headers: { 'Authorization': `Bearer ${token}` }
    });
    
    if (response.ok) {
        const clientes = await response.json();
        const tabelaBody = document.getElementById('tabelaClientesBody');
        tabelaBody.innerHTML = '';
        clientes.forEach(cliente => {
            const linha = `
                <tr>
                    <td>${cliente.id}</td>
                    <td>${cliente.nome}</td>
                    <td>${cliente.email}</td>
                    <td>
                    <button type="button" class="btn btn-warning btn-sm btn-editar" data-id="${cliente.id}">Editar</button>
                    <button type="button" class="btn btn-danger btn-sm btn-excluir" data-id="${cliente.id}">Excluir</button> 
                    </td>
                </tr>
            `;
            tabelaBody.innerHTML += linha;
        });
    } else {
        alert('Falha ao buscar clientes. Sua sessão pode ter expirado.');
    }
}

document.addEventListener('DOMContentLoaded', () => { fetchClientes(); });

document.getElementById('tabelaClientesBody').addEventListener('click', async (event) => {
    if (event.target.classList.contains('btn-editar')) {
        const id = event.target.dataset.id;
        window.location.href = `/clientes/novo?id=${id}`;
    }
    if (event.target.classList.contains('btn-excluir')) {
        if (!confirm('Tem certeza que deseja excluir este cliente?')) {
            return;
        }

        const clienteId = event.target.dataset.id;

        try {
            const response = await fetch(`/api/clientes/${clienteId}`, {
                method: 'DELETE',
                headers: { 'Authorization': `Bearer ${token}` }
            });

            if (response.ok) {
                alert('Cliente excluído com sucesso!');
                fetchClientes();
            } else {
                alert('Erro ao excluir o cliente do servidor.');
            }
        } catch (error) {
            console.error('Erro na requisição de exclusão:', error);
        }
    }
});