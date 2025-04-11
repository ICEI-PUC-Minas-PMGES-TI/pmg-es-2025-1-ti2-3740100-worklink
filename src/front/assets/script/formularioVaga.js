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