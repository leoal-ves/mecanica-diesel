const token = localStorage.getItem('token');
if (!token) window.location.href = '/';

document.addEventListener("DOMContentLoaded", () => {
    const urlParams = new URLSearchParams(window.location.search);
    const idCliente = urlParams.get('id');
    const form = document.getElementById("clienteForm");
    const titulo = document.querySelector("main h1");

    if (idCliente) {
        if (titulo) titulo.innerText = "Editar Cliente";
        
        fetch(`/api/clientes/${idCliente}`, {
            headers: { 'Authorization': `Bearer ${token}` }
        })
            .then(res => {
                if (!res.ok) throw new Error("Erro ao buscar dados do cliente");
                return res.json();
            })
            .then(data => {
                document.getElementsByName("nome")[0].value = data.nome;
                document.getElementsByName("email")[0].value = data.email;
            })
            .catch(err => console.error(err));
    }

    form.addEventListener('submit', async (event) => {
        event.preventDefault();

        const dados = {
            nome: document.getElementsByName("nome")[0].value,
            email: document.getElementsByName("email")[0].value
        };

        const method = idCliente ? 'PATCH' : 'POST';
        const url = idCliente ? `/api/clientes/${idCliente}` : '/api/clientes';

        try {
            const response = await fetch(url, { 
                method: method, 
                headers: { 
                    'Content-Type': 'application/json',
                    'Authorization': `Bearer ${token}`
                }, 
                body: JSON.stringify(dados) 
            });

            if (response.ok) {
                alert(idCliente ? 'Cliente atualizado com sucesso!' : 'Cliente criado com sucesso!');
                window.location.href = '/clientes';
            } else {
                const errorData = await response.json();
                alert('Falha ao salvar: ' + (errorData.message || 'Erro desconhecido'));
            }
        } catch (error) {
            console.error("Erro na requisição:", error);
            alert('Erro de conexão com o servidor.');
        }
    });
});