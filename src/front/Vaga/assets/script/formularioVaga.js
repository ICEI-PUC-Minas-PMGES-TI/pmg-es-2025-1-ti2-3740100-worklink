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
    // Pega o valor real do salário
    const faixaSalarialInput = document.getElementById('salario');
    let faixaSalarialValor = faixaSalarialInput ? faixaSalarialInput.getAttribute('data-valor-real') : '';

    if (!faixaSalarialValor && faixaSalarialInput && faixaSalarialInput.value) {
        const valorSemMascara = faixaSalarialInput.value.replace(/\D/g, '');
        if (valorSemMascara) {
            faixaSalarialValor = (parseInt(valorSemMascara, 10) / 100).toFixed(2);
        }
    }

    const dadosVaga = {
        titulo: document.getElementById('titulo')?.value || '',
        tipoContrato: document.getElementById('tipo')?.value || '',
        modalidade: document.getElementById('modalidade')?.value || '', // <-- Adicione esta linha
        salario: faixaSalarialValor,
        descricao: document.getElementById('descricao')?.value || '',
        beneficios: document.getElementById('beneficios')?.value || '',
        dataFinal: document.getElementById('prazo')?.value || '',
        // Não envie local, requisitos, numeroVagas
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
    const campos = [
        { campo: document.getElementById('titulo'), nome: "Título da Vaga" },
        { campo: document.getElementById('tipo'), nome: "Tipo de Contrato" },
        { campo: document.getElementById('modalidade'), nome: "Modalidade" },
        { campo: document.getElementById('salario'), nome: "Faixa Salarial" },
        { campo: document.getElementById('descricao'), nome: "Descrição da Vaga" },
        { campo: document.getElementById('beneficios'), nome: "Benefícios" },
        { campo: document.getElementById('prazo'), nome: "Prazo de Inscrição" }
        // Removidos: Local de Trabalho, Requisitos, Número de Vagas
    ];

    let todosPreenchidos = true;
    let camposVazios = [];

    campos.forEach(({ campo, nome }) => {
        if (!campo || campo.value.trim() === "") {
            todosPreenchidos = false;
            camposVazios.push(nome);
            if (campo) campo.style.border = "2px solid red";
        } else {
            campo.style.border = "";
        }
    });

    if (!todosPreenchidos) {
        alert("Por favor, preencha todos os campos obrigatórios:\n" + camposVazios.join(", "));
    }

    return todosPreenchidos;
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

    console.log("Valor do campo prazo:", document.getElementById('prazo')?.value);
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
        alertarDadosVaga();      // Mostra o alert com os dados salvos
        console.log("Dados salvos no sessionStorage com sucesso.");
        window.location.href = "resumoVaga.html"; // Redireciona para a página de resumo
    } else {
        alert("Por favor, preencha todos os campos obrigatórios.");
    }
}

function alertarDadosVaga() {
    const dadosVaga = JSON.parse(sessionStorage.getItem('dadosVaga'));
    if (!dadosVaga) {
        alert("Nenhum dado de vaga encontrado no sessionStorage.");
        return;
    }
    let mensagem = "Dados da vaga salvos:\n";
    for (const chave in dadosVaga) {
        mensagem += `${chave}: ${dadosVaga[chave]}\n`;
    }
    alert(mensagem);
}

document.addEventListener('DOMContentLoaded', () => {
    // Se não veio do resumo (edição), limpa os dados antigos ao criar nova vaga
    const url = window.location.href;
    if (url.includes('formularioVaga.html') && !sessionStorage.getItem('edicaoVaga')) {
        sessionStorage.removeItem('dadosVaga');
    }

    // Carregar dados do sessionStorage se existirem (edição/voltar do resumo)
    const dadosVaga = JSON.parse(sessionStorage.getItem('dadosVaga'));
    if (dadosVaga) {
        if (document.getElementById('titulo'))        document.getElementById('titulo').value = dadosVaga.titulo || '';
        if (document.getElementById('tipo'))          document.getElementById('tipo').value = dadosVaga.tipoContrato || '';
        if (document.getElementById('modalidade'))    document.getElementById('modalidade').value = dadosVaga.modalidade || '';
        if (document.getElementById('salario'))       document.getElementById('salario').value = dadosVaga.salario || '';
        if (document.getElementById('descricao'))     document.getElementById('descricao').value = dadosVaga.descricao || '';
        if (document.getElementById('beneficios'))    document.getElementById('beneficios').value = dadosVaga.beneficios || '';
        if (document.getElementById('prazo'))         document.getElementById('prazo').value = dadosVaga.dataFinal ? dadosVaga.dataFinal.substring(0,10) : '';
        // Adicione outros campos se necessário
    }
});

