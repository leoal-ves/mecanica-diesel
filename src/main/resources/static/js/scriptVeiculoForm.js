const token = localStorage.getItem('token');
if (!token) window.location.href = '/';

document.addEventListener("DOMContentLoaded", async () => {
    const urlParams = new URLSearchParams(window.location.search);
    const idVeiculo = urlParams.get('id');
    const form = document.getElementById("veiculoForm");

    const resClientes = await fetch('/api/clientes', { headers: { 'Authorization': `Bearer ${token}` } });
    const clientes = await resClientes.json();
    const select = document.getElementById('clienteSelect');
    clientes.forEach(c => select.innerHTML += `<option value="${c.id}">${c.nome}</option>`);

    if (idVeiculo) {
        document.querySelector('h1').innerText = "Editar Veículo";
        const resVeiculo = await fetch(`/api/veiculos/${idVeiculo}`, { headers: { 'Authorization': `Bearer ${token}` } });
        const v = await resVeiculo.json();
        
        document.getElementById('clienteSelect').value = v.id_cliente;
        document.getElementById('modelo').value = v.modelo;
        document.getElementById('placa').value = v.placa;
    }

    form.addEventListener('submit', async (e) => {
        e.preventDefault();
        const dados = {
            id_cliente: document.getElementById("clienteSelect").value,
            modelo: document.getElementById("modelo").value,
            placa: document.getElementById("placa").value
        };

        const method = idVeiculo ? 'PATCH' : 'POST';
        const url = idVeiculo ? `/api/veiculos/${idVeiculo}` : '/api/veiculos';

        const res = await fetch(url, {
            method: method,
            headers: { 
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify(dados)
        });

        if (res.ok) {
            alert('Salvo com sucesso!');
            window.location.href = '/veiculos';
        }
    });
});