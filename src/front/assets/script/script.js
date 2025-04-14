const API_URL = "http://localhost:8080/api/vagas";

//GET
function getVagas(){
    fetch(API_URL)
        .then(resp => resp.json())
        .then(vagas => {
            console.log("Vagas: ", vagas);
            exibirVagas(vagas);
        })
} 

//POST
function postVagas(){
    const urlParams = new URLSearchParams(window.location.search);

    const novaVagas = {
        titulo: document.querySelector("#titulo").value,
        descricao: document.querySelector("#descricao").value,
        requisitos: document.querySelector("#requisitos").value,
        salario: parseFloat(document.querySelector("#salario").value)
    };

    fetch(API_URL, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify(novaVagas)
    })

    .then(res => res.json())
    .then(data => {
        alert("Vaga criada com sucesso");
        console.log(data);
        window.location.href = "formularioVaga.html";
    })

    .catch(err => console.error("Erro ao criar vaga: ", err));
}

//PUT
function putVagas(id){
    const vagaAtualizada = {
        titulo: document.querySelector("#titulo").value,
        descricao: document.querySelector("#descricao").value,
        requisitos: document.querySelector("#requisitos").value,
        salario: parseFloat(document.querySelector("#salario").value)
    };

    fetch(`${API_URL}/${id}`, {
        method: "PUT",
        headers: {
            "Content-Type": "application/json"
        },

        body: JSON.stringify(vagaAtualizada)
    })

    .then(res => res.json())
    .then(data => {
        alert("Vaga Atualizada com sucesso");
        console.log(data);
        window.location.href = "formularioVaga.html";
    })

    .catch(err => console.log("Erro ao atualizar vaga: ", err));
}

//DELETE
function deleteVagas(id){
    if(confirm("Tem certeza que deseja deletar esta vaga")){
        fetch(`${API_URL}/${id}`, {
            method: "DELETE"
        })

        .then(() => {
            alert("Vaga deletada com sucesso");
            getVagas()//atualiza a lista de vagas apos deletar
        })

        .catch(err => console.error("Erro ao deletar a vaga: ", err));
    }
}

//exibirVagas -> Lista
function exibirVagas(vagas){
    const lista = document.getElementById("lista-vagas");
    lista.innerHTML = ""; //limpa antes de carregar de novo

    vagas.forEach(vaga => {
        const item = document.createElement("div");
        item.classList.add("vaga-item");
        item.innerHTML = `
        <h4>${vaga.titulo}</h4>
        <p>${vaga.descricao}</p>
        <p><strong>Salário:</strong> R$ ${vaga.salario.toFixed(2)}</p>
        <button onclick="putVagas(${vagas.id})">Editar</button>
        <button onvclick="deleteVagas(${vaga.id})">Deletar</button>`;
        
        lista.appendChild(item);
    });
}