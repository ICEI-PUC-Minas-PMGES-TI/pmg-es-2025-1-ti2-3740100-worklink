console.log('Arquivo formularioVaga.js carregado');

// Abre menu
function ativaMenu() {
    const menu = document.querySelector('.links-menu');
    const icon = document.querySelector('.icone-mobile');
    if (menu && icon) {
        menu.classList.toggle('ativo');
        icon.classList.toggle('ativo');
    }
}

// Formata campo data para dd/mm/aaaa
const dataInput = document.getElementById('data');
if (dataInput) {
    dataInput.addEventListener('input', (e) => {
        let value = e.target.value.replace(/\D/g, ''); // remove tudo que não for número

        if (value.length >= 3 && value.length <= 4)
            value = value.replace(/(\d{2})(\d{1,2})/, '$1/$2');
        else if (value.length > 4)
            value = value.replace(/(\d{2})(\d{2})(\d{1,4})/, '$1/$2/$3');

        e.target.value = value;
    });
}

// Formata campo faixa salarial para R$ 0,00
const valorInput = document.getElementById('salario');
if (valorInput) {
    valorInput.addEventListener('input', (e) => {
        let value = e.target.value.replace(/\D/g, ''); // Remove tudo que não for número

        if (value.length === 0) {
            e.target.value = '';
            e.target.setAttribute('data-valor-real', ''); // Limpa o valor real
            return;
        }

        // Formata o valor para exibição
        let formatted = (parseInt(value, 10) / 100).toFixed(2) + '';
        formatted = formatted.replace('.', ',');
        formatted = formatted.replace(/\B(?=(\d{3})+(?!\d))/g, '.');

        e.target.value = "R$ " + formatted;

        // Armazena o valor real (sem formatação) em um atributo
        e.target.setAttribute('data-valor-real', (parseInt(value, 10) / 100).toFixed(2)); // Corrige o valor real
    });
}

// Salva dados da página formularioVaga.html no local sessionStorage
function salvarDadosLocalmente() {
    const faixaSalarialInput = document.querySelector('.dado7');
    let faixaSalarialValor = faixaSalarialInput ? faixaSalarialInput.getAttribute('data-valor-real') : '';

    if (!faixaSalarialValor && faixaSalarialInput && faixaSalarialInput.value) {
        // Tenta extrair o valor manualmente do texto, se o atributo não estiver definido
        const valorSemMascara = faixaSalarialInput.value.replace(/\D/g, '');
        if (valorSemMascara) {
            faixaSalarialValor = (parseInt(valorSemMascara, 10) / 100).toFixed(2);
        }
    }

    const dadosVaga = {
        titulo: document.querySelector('.dado1') ? document.querySelector('.dado1').value : '',
        descricao: document.querySelector('.dado2') ? document.querySelector('.dado2').value : '',
        beneficios: document.querySelector('.dado3') ? document.querySelector('.dado3').value : '',
        dataFinal: document.querySelector('.dado4') ? document.querySelector('.dado4').value : '',
        tipoContrato: document.querySelector('.dado5') ? document.querySelector('.dado5').value : '',
        modalidade: document.querySelector('.dado6') ? document.querySelector('.dado6').value : '',
        salario: faixaSalarialValor
    };

    sessionStorage.setItem("dadosVaga", JSON.stringify(dadosVaga));
    console.log('Dados salvos no sessionStorage:', dadosVaga); // Log para depuração
}

// Salva dados da página envioTesteVaga.html no local sessionStorage
const arquivoInput = document.querySelector('.dado8');
if (arquivoInput) {
    arquivoInput.addEventListener('change', function () {
        const file = arquivoInput.files[0];
        const reader = new FileReader();

        reader.onload = function () {
            sessionStorage.setItem('arquivoPDF', reader.result); // base64
        };

        reader.readAsDataURL(file);
    });
}

// Pega dados do local sessionStorage e preenche os campos do formulário disabilitados
function preencherCampos() {
    const dadosVaga = JSON.parse(sessionStorage.getItem('dadosVaga'));

    if (dadosVaga) {
        const resumoDado1 = document.querySelector('.resumoDado1');
        if (resumoDado1) resumoDado1.value = dadosVaga.titulo || 'Não informado';

        const resumoDado2 = document.querySelector('.resumoDado2');
        if (resumoDado2) resumoDado2.value = dadosVaga.descricao || 'Não informado';

        const resumoDado3 = document.querySelector('.resumoDado3');
        if (resumoDado3) resumoDado3.value = dadosVaga.beneficios || 'Não informado';

        const resumoDado4 = document.querySelector('.resumoDado4');
        if (resumoDado4) resumoDado4.value = dadosVaga.dataFinal || 'Não informado';

        const resumoDado5 = document.querySelector('.resumoDado5');
        if (resumoDado5) resumoDado5.value = dadosVaga.tipoContrato || '';

        const resumoDado6 = document.querySelector('.resumoDado6');
        if (resumoDado6) resumoDado6.value = dadosVaga.modalidade || '';

        // Preenche a faixa salarial com o valor formatado
        const faixaSalarialInput = document.querySelector('.resumoDado7');
        if (faixaSalarialInput) {
            if (dadosVaga.salario) {
                const valorFormatado = `R$ ${parseFloat(dadosVaga.salario).toFixed(2).replace('.', ',')}`;
                faixaSalarialInput.value = valorFormatado;
            } else {
                faixaSalarialInput.value = 'Não informado';
            }
        }
    } else {
        console.error("Nenhum dado encontrado no sessionStorage.");
        alert("Nenhum dado encontrado. Por favor, volte e preencha o formulário.");
        window.location.href = "formularioVaga.html"; // Redireciona para o formulário
    }
}

// Limpar dados salvos no sessionStorage se clicar no btn-cancelar
function limparDadosLocalmente() {
    sessionStorage.removeItem('dadosVaga'); // Remove os dados da vaga
    sessionStorage.removeItem('arquivoPDF'); // Remove o arquivo PDF, se necessário
    console.log('Dados removidos do sessionStorage'); // Adicionado para depuração
}

function validarFormulario() {
    let isValid = true;

    // Seleciona os campos obrigatórios
    const camposObrigatorios = [
        { campo: document.querySelector('.dado1'), mensagem: "Título é obrigatório" },
        { campo: document.querySelector('.dado2'), mensagem: "Descrição é obrigatória" },
        { campo: document.querySelector('.dado4'), mensagem: "Data Final é obrigatória" },
        { campo: document.querySelector('.dado5'), mensagem: "Tipo de Contrato é obrigatório" },
        { campo: document.querySelector('.dado6'), mensagem: "Modalidade é obrigatória" }
    ];

    // Remove classes de erro antes de validar
    camposObrigatorios.forEach(({ campo }) => {
        if (campo) campo.classList.remove('erro');
    });

    // Valida os campos
    camposObrigatorios.forEach(({ campo, mensagem }) => {
        if (!campo || !campo.value.trim()) {
            if (campo) campo.classList.add('erro'); // Adiciona a classe de erro
            isValid = false;
        }
    });

    return isValid; // Retorna true se todos os campos estiverem preenchidos
}

// Adiciona eventos para remover a borda vermelha ao preencher os campos
document.addEventListener('DOMContentLoaded', () => {
    const camposObrigatorios = [
        document.querySelector('.dado1'),
        document.querySelector('.dado2'),
        document.querySelector('.dado4'),
        document.querySelector('.dado5'),
        document.querySelector('.dado6')
    ];

    camposObrigatorios.forEach(campo => {
        if (!campo) return;
        campo.addEventListener('input', () => {
            if (campo.value.trim()) {
                campo.classList.remove('erro'); // Remove a classe de erro se o campo for preenchido
            }
        });

        // Para campos do tipo <select>, use o evento 'change'
        if (campo && campo.tagName === 'SELECT') {
            campo.addEventListener('change', () => {
                if (campo.value.trim()) {
                    campo.classList.remove('erro'); // Remove a classe de erro se o campo for preenchido
                }
            });
        }
    });
});

// Remova o bloco abaixo, pois não existe botão com id 'seguirButton' e isso gera erro
/*
document.addEventListener('DOMContentLoaded', function () {
    const seguirButton = document.getElementById('seguirButton');
    if (seguirButton) {
        seguirButton.addEventListener('click', function(event) {
            event.preventDefault();
            postVagas();
        });
    }
});
*/

// Função que busca as vagas e chama a exibição
function getVagas() {
    fetch(API_URL)
        .then(resp => resp.json())
        .then(vagas => {
            console.log("Vagas: ", vagas);
            exibirVagas(vagas); // Chama a função para exibir as vagas no formulário
        })
        .catch(err => console.error("Erro ao buscar vagas: ", err));
}

// Valida o formulário e salva os dados localmente
function validarESalvar() {
    if (validarFormulario()) {
        salvarDadosLocalmente(); // Salva os dados no sessionStorage
        console.log("Dados salvos no sessionStorage com sucesso.");
        window.location.href = "envioTesteVaga.html"; // Redireciona para a página de envio de PDF
    } else {
        alert("Por favor, preencha todos os campos obrigatórios.");
    }
}

function enviarPDF() {
    // Simula o envio do PDF
    alert("PDF enviado com sucesso!");
    // Redireciona para a página de resumo
    window.location.href = "resumoVaga.html";
}

