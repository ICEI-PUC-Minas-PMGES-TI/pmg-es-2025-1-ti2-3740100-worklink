// Abre menu
function ativaMenu() {
    const menu = document.querySelector('.links-menu');
    const icon = document.querySelector('.icone-mobile');
    menu.classList.toggle('ativo');
    icon.classList.toggle('ativo');
}

// Formata campo data para dd/mm/aaaa
const dataInput = document.getElementById('data');

dataInput.addEventListener('input', (e) => {
let value = e.target.value.replace(/\D/g, ''); // remove tudo que não for número

if (value.length >= 3 && value.length <= 4)
    value = value.replace(/(\d{2})(\d{1,2})/, '$1/$2');
else if (value.length > 4)
    value = value.replace(/(\d{2})(\d{2})(\d{1,4})/, '$1/$2/$3');

e.target.value = value;
});

// Formata campo faixa salarial para R$ 0,00
const valorInput = document.getElementById('valor');

valorInput.addEventListener('input', (e) => {
  let value = e.target.value.replace(/\D/g, ''); // remove tudo que não for número
  value = (parseInt(value, 10) / 100).toFixed(2) + ''; // transforma em número decimal

  value = 'R$ ' + value.replace('.', ',').replace(/\B(?=(\d{3})+(?!\d))/g, '.'); // adiciona os pontos de milhar e vírgula como separador decimal

  e.target.value = value;
});

// Salva dados da página formularioVaga.html no local sessionStorage
function salvarDadosLocalmente() {
    const vaga = {
        titulo: document.querySelector('.dado1').value,
        descricao: document.querySelector('.dado2').value,
        faixaSalarial: document.querySelector('.dado3').value,
        dataFinal: document.querySelector('.dado4').value,
        tipoContrato: document.querySelector('.dado5').value,
        modalidade: document.querySelector('.dado6').value,
        beneficios: document.querySelector('.dado7').value
    };

    sessionStorage.setItem('dadosVaga', JSON.stringify(vaga));
    console.log('Dados salvos no sessionStorage:', vaga); // Adicionado para depuração
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
    console.log('Função preencherCampos chamada'); // Adicionado para depuração

    const dadosVaga = JSON.parse(sessionStorage.getItem('dadosVaga'));

    if (dadosVaga) {
        document.querySelector('.resumoDado1').value = dadosVaga.titulo;
        document.querySelector('.resumoDado2').value = dadosVaga.descricao;
        document.querySelector('.resumoDado3').value = dadosVaga.faixaSalarial;
        document.querySelector('.resumoDado4').value = dadosVaga.dataFinal;
        document.querySelector('.resumoDado5').value = dadosVaga.tipoContrato;
        document.querySelector('.resumoDado6').value = dadosVaga.modalidade;
        document.querySelector('.resumoDado7').value = dadosVaga.beneficios;
    } else {
        console.log('Nenhum dado encontrado no sessionStorage'); // Adicionado para depuração
    }
}

//const arquivo = sessionStorage.getItem('arquivoPDF');
//document.getElementById('pdfPreview').src = arquivo; // se for um iframe/pdf-viewer

// Limpar dados salvos no sessionStorage se clicar no btn-cancelar
function limparDadosLocalmente() {
    sessionStorage.removeItem('dadosVaga'); // Remove os dados da vaga
    sessionStorage.removeItem('arquivoPDF'); // Remove o arquivo PDF, se necessário
    console.log('Dados removidos do sessionStorage'); // Adicionado para depuração
}