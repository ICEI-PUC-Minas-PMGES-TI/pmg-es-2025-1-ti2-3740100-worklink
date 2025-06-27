// NAVBAR PADRÃO WORKLINK - comportamento do botão de perfil e do link Worklink
document.addEventListener('DOMContentLoaded', function() {
    // --- NOVA LÓGICA PARA OS BOTÕES DA NAVBAR ---
    const perfilActions = document.getElementById('perfil-actions');
    if (perfilActions) {
        perfilActions.innerHTML = ''; // Limpa qualquer conteúdo

        // Considere logado apenas se userType E algum identificador de usuário existirem
        const userType = localStorage.getItem('userType');
        const empresaCnpj = localStorage.getItem('empresaCnpj');
        const candidatoCpf = localStorage.getItem('cpfUsuario');

        if (userType === 'empresa' && empresaCnpj) {
            perfilActions.innerHTML = `<a href="../Vaga/HomeEmpresa.html" class="btn-primary-custom">Painel Empresa</a>`;
        } else if (userType === 'candidato' && candidatoCpf) {
            perfilActions.innerHTML = `<a href="../Candidato/homeCandidato.html" class="btn-primary-custom">Meu Perfil</a>`;
        } else {
            // Não logado: mostra os dois botões
            perfilActions.innerHTML = `
                <a href="../Cadastro_Login/Login.html" class="btn-primary-custom">Entrar</a>
                <a href="../Cadastro_Login/escolhaCadastro.html" class="btn-primary-custom">Cadastrar</a>
            `;
        }
    }

    // --- AJUSTE DO LINK DA MARCA WORKLINK ---
    const navbarBrand = document.querySelector('.navbar-brand');
    const userType = localStorage.getItem('userType');
    if (navbarBrand) {
        navbarBrand.addEventListener('click', function(e) {
            if (!userType) {
                navbarBrand.setAttribute('href', '../index.html');
            } else if (userType === 'empresa') {
                e.preventDefault();
                window.location.href = "../Vaga/HomeEmpresa.html";
            } else if (userType === 'candidato') {
                e.preventDefault();
                window.location.href = "../Candidato/homeCandidato.html";
            }
        });
    }
});