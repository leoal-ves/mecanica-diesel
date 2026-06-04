
async function fetchClientes() {
    const response = await fetch('/api/clientes');
    if (response.ok) {
        const clientes = await response.json();
        // Process the fetched clientes data
        const tabelaBody = document.getElementById('tabelaClientesBody');
        tabelaBody.innerHTML = '';
        clientes.forEach(cliente => {
            const linha = `<tr>
                    <td>${cliente.id}</td>
                    <td>${cliente.nome}</td>
                    <td>${cliente.email}</td>
               <td> <button type="button" class="btn btn-danger btn-excluir" data-id="${cliente.id}">Excluir</button>                   </td>
                </tr>
            `;
            tabelaBody.innerHTML += linha;
        });
    } else {
        alert('Falha ao buscar clientes.');
    }
}
document.addEventListener('DOMContentLoaded', () => { fetchClientes(); });

document.getElementById('tabelaClientesBody').addEventListener('click', async (event) => {
    if (event.target.classList.contains('btn-excluir')) {

        // Confirmação para o usuário não deletar sem querer
        if (!confirm('Tem certeza que deseja excluir este cliente?')) {
            return;
        }

        // Pega o ID diretamente do atributo data-id do botão clicado
        const clienteId = event.target.dataset.id;

        try {
            const response = await fetch(`/api/clientes/${clienteId}`, {
                method: 'DELETE'
            });

            if (response.ok) {
                alert('Cliente excluído com sucesso!');

                // Em vez de recarregar a página inteira, chamamos a função 
                // que busca os clientes novamente para atualizar a tabela na tela!
                fetchClientes();
            } else {
                alert('Erro ao excluir o cliente do servidor.');
            }
        } catch (error) {
            console.error('Erro na requisição de exclusão:', error);
        }
    }
});