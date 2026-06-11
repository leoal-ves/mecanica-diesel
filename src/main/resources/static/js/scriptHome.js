document.addEventListener("DOMContentLoaded", () => {
    const tbody = document.getElementById('tabelaHomeBody');
    
    function formatarData(dataISO) {
        if (!dataISO) return "";
        const [ano, mes, dia] = dataISO.split('-');
        return `${dia}/${mes}/${ano}`;
    }

    const dataAlvo = new Date();
    dataAlvo.setFullYear(dataAlvo.getFullYear() - 1);
    const dataFormatada = dataAlvo.toISOString().split('T')[0];

    fetch('/api/servicos')
        .then(response => response.json())
        .then(data => {
            tbody.innerHTML = '';
            
            const filtrados = data.filter(s => {
                const descricaoNormalizada = s.descricao.normalize("NFD").replace(/[\u0300-\u036f]/g, "").toLowerCase();
                return descricaoNormalizada.includes('oleo') && s.dataServico === dataFormatada;
            });

            if (filtrados.length === 0) {
                tbody.innerHTML = '<tr><td colspan="4" class="text-center">Nenhuma troca de óleo encontrada para esta data.</td></tr>';
                return;
            }

            filtrados.forEach(s => {
                tbody.innerHTML += `
                    <tr>
                        <td>${s.nomeCliente}</td>
                        <td>${s.placaVeiculo}</td>
                        <td>${formatarData(s.dataServico)}</td>
                        <td>${s.descricao}</td>
                    </tr>
                `;
            });
        })
        .catch(err => console.error("Erro ao carregar dados:", err));
});