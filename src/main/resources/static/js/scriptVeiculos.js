const token = localStorage.getItem('token');
if (!token) window.location.href = '/';

document.addEventListener("DOMContentLoaded", () => {
    const tbody = document.getElementById('tabelaVeiculosBody');
    const filtro = document.getElementById('filtroCliente');

    function renderTabela(data) {
        tbody.innerHTML = '';
        data.forEach(v => {
            tbody.innerHTML += `<tr>
                <td>${v.id}</td>
                <td>${v.nomeCliente}</td>
                <td>${v.modelo}</td>
                <td>${v.placa}</td>
                <td>
                    <button class="btn btn-warning btn-sm btn-editar" data-id="${v.id}">Editar</button>
                    <button class="btn btn-danger btn-sm btn-excluir" data-id="${v.id}">Excluir</button>
                </td>
            </tr>`;
        });
    }

    fetch('/api/clientes', { headers: { 'Authorization': `Bearer ${token}` } })
        .then(res => res.json())
        .then(data => {
            data.forEach(c => {
                filtro.innerHTML += `<option value="${c.id}">${c.nome}</option>`;
            });
        });

    async function carregarVeiculos(url) {
        const res = await fetch(url, { headers: { 'Authorization': `Bearer ${token}` } });
        const data = await res.json();
        renderTabela(data);
    }

    filtro.addEventListener('change', (e) => {
        const clienteId = e.target.value;
        const url = clienteId ? `/api/veiculos/cliente/${clienteId}` : '/api/veiculos';
        carregarVeiculos(url);
    });

    carregarVeiculos('/api/veiculos');

    document.getElementById('tabelaVeiculosBody').addEventListener('click', async (e) => {
        const id = e.target.dataset.id;
        
        if (e.target.classList.contains('btn-editar')) {
            window.location.href = `/veiculos/novo?id=${id}`;
        }
        
        if (e.target.classList.contains('btn-excluir')) {
            if (confirm('Tem certeza?')) {
                const res = await fetch(`/api/veiculos/${id}`, { 
                    method: 'DELETE',
                    headers: { 'Authorization': `Bearer ${token}` }
                });
                if (res.ok) {
                    alert('Veículo excluído!');
                    const url = filtro.value ? `/api/veiculos/cliente/${filtro.value}` : '/api/veiculos';
                    carregarVeiculos(url);
                }
            }
        }
    });
});