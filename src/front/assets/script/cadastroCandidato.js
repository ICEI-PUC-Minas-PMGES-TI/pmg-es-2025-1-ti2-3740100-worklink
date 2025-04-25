const inome = document.getElementById("caduser_nome");
const iemail = document.getElementById("caduser_email");
const isenha = document.getElementById("caduser_conSenha");
const itelefone = document.getElementById("caduser_tel");
const iendereco = document.getElementById("caduser_endereco");
const icpf = document.getElementById("caduser_cpf");
const idataNasc = document.getElementById("caduser_nasc");
const iareaAtuacao = document.getElementById("caduser_area");
const isexo = document.getElementById("caduser_sexo");
const icep = document.getElementById("caduser_cep");


function cadastrar(){
  fetch("http://localhost:8080/candidatos",
      {
          headers: {
              "Accept": "application/json",
              "Content-Type": "application/json"
          },
          method: "POST",
          body: JSON.stringify({
            nome: inome.value,
            email: iemail.value,
            senha: isenha.value,
            telefone: itelefone.value,
            endereco: iendereco.value,
            cpf: icpf.value,
            dataNasc: idataNasc.value,
            areaAtuacao: iareaAtuacao.value,
            sexo: isexo.value,
            cep: icep.value,
          })

      })
      .then(function (res){ console.log(res) })
      .catch(function (res){ console.log(res) })
}

const cadUserButton = document.getElementById("cadUser");

cadUserButton.addEventListener("click", function(event){
    event.preventDefault();  // Previne o comportamento padrão (não vai recarregar a página)
    cadastrar();  // Chama a função de cadastro
});