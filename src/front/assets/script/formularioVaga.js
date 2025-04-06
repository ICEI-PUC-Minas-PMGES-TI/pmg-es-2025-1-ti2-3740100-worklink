// Abre menu
function ativaMenu() {
    const menu = document.querySelector('.links-menu');
    const icon = document.querySelector('.icone-mobile');
    menu.classList.toggle('ativo');
    icon.classList.toggle('ativo');
}