// Elementos do DOM
const vagasContainer = document.getElementById('vagas-container');
const modal = document.getElementById('modal');
const modalForm = document.getElementById('form-vaga');
const closeBtn = document.querySelector('.close');
const cancelBtn = document.getElementById('btn-cancelar');

// Elementos do perfil da empresa
const empresaPerfilLogo = document.getElementById('empresa-perfil-logo');
const empresaPerfilNome = document.getElementById('empresa-perfil-nome');
const empresaPerfilAnoCriacao = document.getElementById('empresa-perfil-ano-criacao');
const empresaPerfilDescricao = document.getElementById('empresa-perfil-descricao');
const empresaPerfilFuncionarios = document.getElementById('empresa-perfil-funcionarios');
const empresaPerfilLocais = document.getElementById('empresa-perfil-locais');

// Elementos do modal de história
const modalHistoria = document.getElementById('modal-historia');
const btnHistoriaEmpresa = document.getElementById('btn-historia-empresa');
const closeHistoriaBtn = document.getElementById('close-historia');
const empresaHistoriaNome = document.getElementById('empresa-historia-nome');
const empresaHistoriaAnoCriacao = document.getElementById('empresa-historia-ano-criacao');
const empresaHistoriaDescricao = document.getElementById('empresa-historia-descricao');
const empresaHistoriaTexto = document.getElementById('empresa-historia-texto');

// Estado da aplicação
let empresaInfo = null;

// Carregar dados ao iniciar a página
document.addEventListener('DOMContentLoaded', () => {
    carregarEmpresaInfo();
    carregarVagasDaEmpresa();
    configurarEventListenersHistoria();

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
            localStorage.removeItem('empresaCnpj');
            localStorage.removeItem('empresaNome');
            // Adicione outros itens se necessário
            window.location.href = "../Cadastro_Login/Login.html";
        });
    }
});

// Função para configurar os event listeners do modal de história
function configurarEventListenersHistoria() {
    // Abrir o modal de história
    btnHistoriaEmpresa.addEventListener('click', () => {
        modalHistoria.style.display = 'block';
    });
    
    // Fechar o modal de história
    closeHistoriaBtn.addEventListener('click', () => {
        modalHistoria.style.display = 'none';
    });
    
    // Fechar o modal de história ao clicar fora dele
    window.addEventListener('click', (event) => {
        if (event.target === modalHistoria) {
            modalHistoria.style.display = 'none';
        }
    });
}

// Função para carregar informações da empresa
async function carregarEmpresaInfo() {
    try {
        // Por enquanto, vamos usar dados de exemplo
        empresaInfo = {
            nome: "Empresa Exemplo Ltda.",
            logo: "img/empresa-exemplo.png",
            anoCriacao: 2010,
            descricao: "Empresa líder no setor de tecnologia, especializada em desenvolvimento de software e soluções digitais.",
            historia: "A Empresa Exemplo Ltda. foi fundada em 2010 por um grupo de empreendedores visionários com o objetivo de transformar o mercado de tecnologia. Inicialmente, começamos como uma pequena startup com apenas 5 funcionários, focando em desenvolvimento de aplicativos móveis.\n\nEm 2015, expandimos nossas operações para incluir soluções empresariais e abrimos nosso segundo escritório. Nos anos seguintes, crescemos rapidamente, atingindo a marca de 150 funcionários em 2020.\n\nHoje, somos uma das empresas mais respeitadas no setor, com três locais em diferentes cidades e uma carteira diversificada de clientes. Nossa missão continua a mesma: inovar e criar soluções tecnológicas que transformam a vida das pessoas.",
            funcionarios: "150+",
            locais: 3
        };
        
        // Atualizar a interface com as informações da empresa
        atualizarInterfaceEmpresa();
    } catch (error) {
        console.error('Erro ao carregar informações da empresa:', error);
    }
}

// Função para atualizar a interface com as informações da empresa
function atualizarInterfaceEmpresa() {
    const nomeEmpresa = localStorage.getItem("empresaNome");
    if (nomeEmpresa) {
        empresaPerfilNome.textContent = nomeEmpresa;
        empresaHistoriaNome.textContent = nomeEmpresa;
    }
    if (empresaInfo) {
        empresaPerfilLogo.src = empresaInfo.logo;
        empresaPerfilAnoCriacao.textContent = empresaInfo.anoCriacao;
        empresaPerfilDescricao.textContent = empresaInfo.descricao;
        empresaPerfilFuncionarios.textContent = empresaInfo.funcionarios;
        empresaPerfilLocais.textContent = empresaInfo.locais;
        
        // Atualizar o modal de história
        empresaHistoriaAnoCriacao.textContent = empresaInfo.anoCriacao;
        empresaHistoriaDescricao.textContent = empresaInfo.descricao;
        empresaHistoriaTexto.innerHTML = empresaInfo.historia.replace(/\n\n/g, '</p><p>');
    }
}

// Função para contar vagas no DOM
function contarVagasNoDOM() {
    const vagas = document.querySelectorAll(".vaga-card");
    const quantidade = vagas.length;
    console.log(`Quantidade de vagas no DOM: ${quantidade}`);
    const contador = document.getElementById("contador-vagas");
    if (contador) {
        contador.textContent = `Total de Vagas: ${quantidade}`;
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
        exibirVagas(vagas); // sua função para renderizar as vagas
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

// Chame essa função após renderizar as vagas
exibirVagas(vagas);
contarVagasNoDOM();


