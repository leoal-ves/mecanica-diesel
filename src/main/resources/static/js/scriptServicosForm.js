const token = localStorage.getItem('token');
if (!token) window.location.href = '/';

document.addEventListener("DOMContentLoaded", () => {
    const urlParams = new URLSearchParams(window.location.search);
    const idServico = urlParams.get('id');

    const clienteSelect = document.getElementById('clienteSelect');
    const veiculoSelect = document.getElementById('veiculoSelect');
    const formServico = document.getElementById('formServico');

    fetch('/api/clientes', { headers: { 'Authorization': `Bearer ${token}` } })
        .then(res => res.json())
        .then(data => {
            data.forEach(c => {
                clienteSelect.innerHTML += `<option value="${c.id}">${c.nome}</option>`;
            });
            
            if (idServico) {
                loadServiceData(idServico);
            }
        });

    clienteSelect.addEventListener('change', function() {
        loadVehicles(this.value);
    });

    function loadVehicles(clienteId, selectedVeiculoId = null) {
        veiculoSelect.innerHTML = '<option value="">Selecione um veículo...</option>';
        if (clienteId) {
            fetch(`/api/veiculos/cliente/${clienteId}`, { headers: { 'Authorization': `Bearer ${token}` } })
                .then(res => res.json())
                .then(data => {
                    data.forEach(v => {
                        const isSelected = v.id == selectedVeiculoId ? 'selected' : '';
                        veiculoSelect.innerHTML += `<option value="${v.id}" ${isSelected}>${v.modelo} - ${v.placa}</option>`;
                    });
                });
        }
    }

    async function loadServiceData(id) {
        const res = await fetch(`/api/servicos/${id}`, { headers: { 'Authorization': `Bearer ${token}` } });
        const data = await res.json();
        
        clienteSelect.value = data.id_cliente;
        document.getElementById('descricao').value = data.descricao;
        document.getElementById('dataServico').value = data.dataServico;
        document.getElementById('quilometragem').value = data.quilometragem || '';
        
        loadVehicles(data.id_cliente, data.id_veiculo);
    }

    formServico.onsubmit = (e) => {
        e.preventDefault();
        
        const method = idServico ? 'PUT' : 'POST';
        const url = idServico ? `/api/servicos/${idServico}` : '/api/servicos';
        
        const kmInput = document.getElementById('quilometragem').value;
        const kmValor = kmInput ? parseInt(kmInput) : null;
        
        const servico = {
            id_cliente: clienteSelect.value,
            id_veiculo: veiculoSelect.value,
            descricao: document.getElementById('descricao').value,
            dataServico: document.getElementById('dataServico').value,
            quilometragem: kmValor
        };

        fetch(url, {
            method: method,
            headers: { 
                'Content-Type': 'application/json',
                'Authorization': `Bearer ${token}`
            },
            body: JSON.stringify(servico)
        }).then(() => {
            alert('Serviço salvo com sucesso!');
            window.location.href = '/servicos';
        });
    };
});