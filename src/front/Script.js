// NAVBAR PADRÃO WORKLINK - comportamento do botão de perfil e do link Worklink
document.addEventListener('DOMContentLoaded', function() {
    const perfilBtn = document.querySelector('.btn.btn-primary-custom, #meuPerfilBtn');
    const navbarBrand = document.querySelector('.navbar-brand');
    const userType = localStorage.getItem('userType');

    // Esconde o botão "Meu Perfil" se não estiver logado
    if (!userType && perfilBtn) {
        perfilBtn.style.display = 'none';
    }

    // Ajusta o link do Worklink conforme login
    if (navbarBrand) {
        navbarBrand.addEventListener('click', function(e) {
            if (!userType) {
                // Não logado: vai para index.html
                navbarBrand.setAttribute('href', '../index.html');
            } else if (userType === 'empresa') {
                // Empresa logada: vai para painel da empresa
                e.preventDefault();
                window.location.href = "../Vaga/HomeEmpresa.html";
            } else if (userType === 'candidato') {
                // Candidato logado: vai para home do candidato
                e.preventDefault();
                window.location.href = "../Candidato/homeCandidato.html";
            }
        });
    }

    // Ajusta o botão de perfil conforme login
    if (perfilBtn) {
        if (userType === 'empresa') {
            perfilBtn.textContent = 'Painel Empresa';
            perfilBtn.href = "../Vaga/HomeEmpresa.html";
        } else if (userType === 'candidato') {
            perfilBtn.textContent = 'Meu Perfil';
            perfilBtn.href = "../Candidato/homeCandidato.html";
        } else {
            perfilBtn.textContent = 'Entrar';
            perfilBtn.href = "../Cadastro_Login/login.html";
        }
    }
});