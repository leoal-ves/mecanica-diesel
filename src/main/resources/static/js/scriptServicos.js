document.addEventListener("DOMContentLoaded", () => {
    fetch('/api/servicos')
        .then(response => response.json())
        .then(data => {
            const tbody = document.getElementById('tabelaServicosBody');
            tbody.innerHTML = '';
            
            data.forEach(s => {
                const [ano, mes, dia] = s.dataServico.split('-');
                const dataFormatada = `${dia}/${mes}/${ano}`;

                tbody.innerHTML += `
                    <tr>
                        <td>${s.id}</td>
                        <td>${s.nomeCliente}</td>
                        <td>${s.placaVeiculo}</td>
                        <td>${s.descricao}</td>
                        <td>${dataFormatada}</td>
                        <td>${s.avisoEnviado ? 'Sim' : 'Não'}</td>
                        <td>
                            <button class="btn btn-sm btn-warning" onclick="editarServico(${s.id})">Editar</button>
                            <button class="btn btn-sm btn-danger" onclick="excluirServico(${s.id})">Excluir</button>
                            </td>
                    </tr>
                `;
            });
        });
});

function excluirServico(id) {
    if (confirm('Tem certeza que deseja excluir este serviço?')) {
        fetch(`/api/servicos/${id}`, { method: 'DELETE' })
            .then(() => location.reload());
    }
}

function editarServico(id) {
    window.location.href = `/servicos/novo?id=${id}`;
}