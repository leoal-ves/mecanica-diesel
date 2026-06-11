const token = localStorage.getItem('token');
if (!token) window.location.href = '/';

document.addEventListener("DOMContentLoaded", () => {
    const tbody = document.getElementById('tabelaServicosBody');
    const filtroData = document.getElementById('filtroData');
    const btnLimpar = document.getElementById('btnLimpar');
    
    let listaServicos = []; 

    async function carregarServicos() {
        const res = await fetch('/api/servicos', { headers: { 'Authorization': `Bearer ${token}` } });
        listaServicos = await res.json();
        renderizarTabela(listaServicos);
    }

    function renderizarTabela(dados) {
        tbody.innerHTML = '';
        dados.forEach(s => {
            const [ano, mes, dia] = s.dataServico.split('-');
            const dataFormatada = `${dia}/${mes}/${ano}`;

            tbody.innerHTML += `
                <tr>
                    <td>${s.id}</td>
                    <td>${s.nomeCliente || ''}</td>
                    <td>${s.placaVeiculo || ''}</td>
                    <td>${s.descricao || ''}</td>
                    <td>${dataFormatada}</td>
                    <td>${s.avisoEnviado ? 'Sim' : 'Não'}</td>
                    <td>
                        <button class="btn btn-sm btn-warning" onclick="editarServico(${s.id})">Editar</button>
                        <button class="btn btn-sm btn-danger" onclick="excluirServico(${s.id})">Excluir</button>
                    </td>
                </tr>
            `;
        });
    }

    if (filtroData) {
        filtroData.addEventListener('change', (e) => {
            const dataSelecionada = e.target.value; 
            if (!dataSelecionada) {
                renderizarTabela(listaServicos);
                return;
            }
            const filtrados = listaServicos.filter(s => s.dataServico === dataSelecionada);
            renderizarTabela(filtrados);
        });
    }

    if (btnLimpar) {
        btnLimpar.addEventListener('click', () => {
            filtroData.value = '';
            renderizarTabela(listaServicos);
        });
    }

    carregarServicos();
});

function excluirServico(id) {
    if (confirm('Tem certeza que deseja excluir este serviço?')) {
        fetch(`/api/servicos/${id}`, { 
            method: 'DELETE',
            headers: { 'Authorization': `Bearer ${token}` }
        }).then(() => location.reload());
    }
}

function editarServico(id) {
    window.location.href = `/servicos/novo?id=${id}`;
}