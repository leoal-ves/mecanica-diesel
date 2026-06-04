document.getElementById("clienteForm").addEventListener('submit', async (event) => {
    event.preventDefault();
    const dados = {
        nome: document.getElementsByName("nome")[0].value,
        email: document.getElementsByName("email")[0].value
    };

    const response = await fetch('/api/clientes', { method: 'POST', headers: { 'Content-Type': 'application/json' }, body: JSON.stringify(dados) });
    if (response.ok) {
        alert('Cliente criado com sucesso!');
        window.location.href = '/clientes';
    } else {
        alert('Falha ao criar cliente. Verifique os dados e tente novamente.');
    }
});

