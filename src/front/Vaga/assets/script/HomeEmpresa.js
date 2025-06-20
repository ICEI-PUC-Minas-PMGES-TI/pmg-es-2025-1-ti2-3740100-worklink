// Verificação de login
const cnpj = localStorage.getItem("empresaCnpj");
if (!cnpj) {
    alert("Você precisa estar logado para acessar esta página.");
    window.location.href = "../Cadastro_Login/login.html";
}

// Elementos do DOM
const vagasContainer = document.getElementById('vagas-container');
const modal = document.getElementById('modal');
const modalForm = document.getElementById('form-vaga');
const closeBtn = document.querySelector('.close');
const cancelBtn = document.getElementById('btn-cancelar');

// Elementos do perfil da empresa
const empresaPerfilLogo = document.getElementById('empresa-perfil-logo');
const empresaPerfilNome = document.getElementById('empresa-perfil-nome');

// Novos elementos dinâmicos
const empresaResponsavel = document.getElementById('empresa-responsavel');
const empresaCargo = document.getElementById('empresa-cargo');
const empresaTelefone = document.getElementById('empresa-telefone');
const empresaAvaliacao = document.getElementById('avaliacao');
const empresaDescricao = document.getElementById('empresa-historia-descricao');
const empresaEndereco = document.getElementById('empresa-historia-endereco');

// Estado da aplicação
let empresaInfo = null;

// Carregar dados ao iniciar a página
document.addEventListener('DOMContentLoaded', () => {
    carregarEmpresaInfo();
    carregarVagasDaEmpresa();
    const nomeEmpresa = document.getElementById('empresa-perfil-nome');
    const btnLogout = document.getElementById('btn-logout');
    const perfilInfo = document.querySelector('.empresa-perfil-info');

    if (nomeEmpresa && btnLogout && perfilInfo) {
        perfilInfo.addEventListener('mouseenter', () => {
            btnLogout.style.display = 'inline-block';
        });
        perfilInfo.addEventListener('mouseleave', () => {
            btnLogout.style.display = 'none';
        });

        btnLogout.addEventListener('click', () => {
            localStorage.clear();
            window.location.href = "../Cadastro_Login/login.html";
        });
    }
});

// Função para carregar informações da empresa pelo CNPJ via API
async function carregarEmpresaInfo() {
    const cnpj = localStorage.getItem("empresaCnpj");
    if (!cnpj) {
        alert("CNPJ da empresa não encontrado. Faça login novamente.");
        return;
    }
    try {
        const resp = await fetch(`http://localhost:8080/empresas/cnpj/${encodeURIComponent(cnpj)}`);
        if (!resp.ok) throw new Error("Empresa não encontrada");
        const empresa = await resp.json();

        // Atualiza os elementos do perfil
        if (empresaPerfilNome) empresaPerfilNome.textContent = empresa.nome || "Nome não informado";
        if (empresaPerfilLogo) empresaPerfilLogo.src = empresa.fotoPerfil ? `http://localhost:8080${empresa.fotoPerfil}` : "assets/img/empresa-exemplo.png";
        if (empresaResponsavel) empresaResponsavel.textContent = empresa.nomeResponsavel || "";
        if (empresaCargo) empresaCargo.textContent = empresa.cargo || "";
        if (empresaTelefone) empresaTelefone.textContent = empresa.telefone || "";
        if (empresaAvaliacao) empresaAvaliacao.textContent = empresa.avaliacao !== null && empresa.avaliacao !== undefined ? empresa.avaliacao : "N/A";
        if (empresaDescricao) empresaDescricao.textContent = empresa.descricao || "Sem descrição.";
        if (empresaEndereco) {
            const rua = empresa.endereco || "";
            const cidade = empresa.cidade || "";
            const enderecoCompleto = rua && cidade ? `${rua}, ${cidade}` : rua || cidade || "Não informado";
            empresaEndereco.textContent = enderecoCompleto;
        }
    } catch (error) {
        console.error('Erro ao carregar informações da empresa:', error);
        alert("Erro ao carregar informações da empresa.");
    }
}

// Função para buscar vagas da empresa logada
async function carregarVagasDaEmpresa() {
    const cnpj = localStorage.getItem("empresaCnpj");
    if (!cnpj) {
        alert("CNPJ da empresa não encontrado. Faça login novamente.");
        return;
    }
    try {
        const resp = await fetch(`http://localhost:8080/vagas/empresa/cnpj/${encodeURIComponent(cnpj)}`);
        const vagas = await resp.json();
        exibirVagas(vagas); // Aqui está correto!
        atualizarContadorVagas(vagas.length);
    } catch (err) {
        alert("Erro ao buscar vagas da empresa!");
        console.error(err);
    }
}

// Atualiza o contador de vagas
function atualizarContadorVagas(qtd) {
    const contador = document.getElementById("empresa-perfil-vagas-count");
    if (contador) contador.textContent = qtd;
}

//APRESENTAÇÃO DE VAGAS

// exibirVagas(vagas);
// contarVagasNoDOM();
