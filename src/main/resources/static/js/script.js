document.getElementById("loginForm").addEventListener('submit', async (event) => {
    event.preventDefault();
    const dados = {
        email: document.getElementsByName("email")[0].value,
        senha: document.getElementsByName("senha")[0].value
    };
    const response = await fetch('/auth/login', { method: 'POST', headers: { 'Content-Type': 'application/json' }, body: JSON.stringify(dados) });
    if (response.ok) {
        // const data = await response.json();
        // localStorage.setItem('token', data.token);
        window.location.href = '/home';
    } else {
        alert('Login falhou. Verifique suas credenciais.');
    }
});
