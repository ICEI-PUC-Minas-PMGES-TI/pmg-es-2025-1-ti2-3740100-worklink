window.URL = "http://localhost:8080";

document.addEventListener("DOMContentLoaded", function() {
    const botaoLogin = document.getElementById("loginUsuario");
    if (!botaoLogin) {
        // Se o botão não existe, não faz nada
        return;
    }

    botaoLogin.addEventListener("click", function(event) {
        event.preventDefault();

        const email = document.getElementById("caduser_email").value;
        const senha = document.getElementById("caduser_senha").value;

        if (!email || !senha) {
            alert("Preencha e-mail e senha.");
            return;
        }

        fetch(`${URL}/empresas/email/${encodeURIComponent(email)}`)
            .then(res => {
                if (!res.ok) throw new Error("Empresa não encontrada");
                return res.json();
            })
            .then(empresa => {
                if (empresa.senha === senha) {
                    alert("Login realizado com sucesso!");
                    localStorage.setItem("empresaCnpj", empresa.cnpj);
                    localStorage.setItem("empresaNome", empresa.nome);
                    window.location.href = "../Vaga/homeEmpresa.html";
                } else {
                    alert("Senha incorreta!");
                }
            })
            .catch(() => {
                alert("Empresa não encontrada!");
            });
    });
});