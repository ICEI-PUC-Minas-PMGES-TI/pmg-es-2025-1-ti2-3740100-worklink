const API_URL = "http://localhost:8080/vagas";

//GET
function getVagas() {
    const cnpj = localStorage.getItem("empresaCnpj");
    if (!cnpj) {
        alert("CNPJ da empresa não encontrado. Faça login novamente.");
        return;
    }
    fetch(`http://localhost:8080/vagas/empresa/cnpj/${encodeURIComponent(cnpj)}`)
        .then(resp => resp.json())
        .then(vagas => {
            console.log("Vagas recebidas:", vagas);
            exibirVagas(vagas);
        })
        .catch(err => console.error("Erro ao buscar vagas:", err));
}

//POST
window.postVagas = function postVagas() {
    console.log('Função postVagas chamada!');
    const dadosVaga = JSON.parse(sessionStorage.getItem('dadosVaga'));

    if (!dadosVaga) {
        alert("Nenhum dado encontrado para enviar. Por favor, volte e preencha o formulário.");
        window.location.href = "formularioVaga.html";
        return;
    }

    // Adicione o CNPJ da empresa logada ao objeto
    const cnpj = localStorage.getItem("empresaCnpj");
    if (!cnpj) {
        alert("CNPJ da empresa não encontrado. Faça login novamente.");
        window.location.href = "../Cadastro_Login/Login.html";
        return;
    }
    dadosVaga.cnpj = cnpj;

    // Adicione o nome do arquivo PDF do teste, se existir
    const teste = sessionStorage.getItem('teste');
    if (teste) {
        dadosVaga.teste = teste;
    }

    fetch("http://localhost:8080/vagas", {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(dadosVaga)
    })
    .then(res => {
        if (!res.ok) throw new Error("Erro ao criar a vaga.");
        return res.json();
    })
    .then(data => {
        alert("Vaga criada com sucesso!");
        console.log("Dados enviados ao backend:", data);
        sessionStorage.removeItem('dadosVaga');
        sessionStorage.removeItem('teste'); // Limpa o nome do arquivo após uso
        window.location.href = "HomeEmpresa.html";
    })
    .catch(err => {
        console.error("Erro ao criar a vaga:", err);
        alert("Ocorreu um erro ao criar a vaga. Tente novamente.");
    });
};

//PUT
function putVagas(id){
    const vagaAtualizada = {
        titulo: document.querySelector("#titulo").value,
        descricao: document.querySelector("#descricao").value,
        beneficios: document.querySelector("#Beneficios").value,
        dataFinal: document.querySelector("#DataFinal").value,
        tipoContrato: document.querySelector("#Tipo").value,
        modalidade: document.querySelector("#Modalidade").value,
        salario: limparValor(document.querySelector("#salario").value)
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
function deletarVaga(id) {
    if (confirm("Tem certeza que deseja deletar esta vaga?")) {
        fetch(`${API_URL}/${id}`, {
            method: "DELETE"
        })
        .then(() => {
            alert("Vaga deletada com sucesso!");
            getVagas();
        })
        .catch(err => console.error("Erro ao deletar vaga:", err));
    }
}

//exibirVagas -> Lista
function exibirVagas(vagas) {
    const lista = document.getElementById("lista-vagas");
    lista.innerHTML = ""; // Limpa a lista antes de adicionar novos cards

    const vagasContainer = document.getElementById('vagas-container');
    if (!vagasContainer) return; // Evita erro se o elemento não existir

    // Ordena as vagas pelo maior ID (mais recente primeiro)
    vagas.sort((a, b) => b.id - a.id);

    vagas.forEach(vaga => {
        const card = document.createElement("div");
        card.classList.add("vaga-card");

        // Trata o campo dataFinal
        let dataFinal = "Não especificada";
        if (vaga.dataFinal) {
            // Usa a data diretamente no formato dd/MM/yyyy
            dataFinal = vaga.dataFinal;
        }

        const modalidade = vaga.modalidade || 'Não especificada'; // Mapeia a modalidade
        const tipoContrato = vaga.tipoContrato || 'Não especificado'; // Mapeia o tipo de contrato
        const beneficios = vaga.beneficios && vaga.beneficios.trim() !== "" ? vaga.beneficios : 'Não informado';

        card.innerHTML = `
        <h2>${vaga.titulo}</h2>
            <p><strong>Sobre:</strong> ${vaga.descricao}</p>
            <p><strong>Benefícios:</strong> ${beneficios}</p>
            <p><strong>Data Final:</strong> ${dataFinal}</p>
            <p><strong>Modalidade:</strong> ${modalidade}</p>
            <p><strong>Tipo de Contrato:</strong> ${tipoContrato}</p>
            <p><strong>Salário:</strong> R$ ${vaga.salario.toFixed(2)}</p>
            <button class="editar" onclick="editarVaga(${vaga.id})">Editar</button>
            <button class="excluir" onclick="deletarVaga(${vaga.id})">Excluir</button>
        `;

        lista.appendChild(card);
    });
}

function editarVaga(id) {
    sessionStorage.setItem('idVagaEdicao', id); // Salva o ID da vaga no sessionStorage
    window.location.href = "editarVaga.html"; // Redireciona para a página de edição
}

function limparValor(valor) {
    if (!valor) return 0;
    return valor.replace(/[^\d,]/g, '').replace(',', '.');
}

// Função para contar vagas ativas
function contarVagasAtivas() {
    fetch("http://localhost:8080/vagas") // Substitua pela URL correta da sua API
        .then(response => {
            if (!response.ok) throw new Error("Erro ao buscar vagas");
            return response.json();
        })
        .then(vagas => {
            const quantidade = vagas.length; // Conta o número total de vagas
            console.log(`Quantidade de vagas ativas: ${quantidade}`);
            
            // Atualiza o contador no DOM
            const contador = document.getElementById("empresa-perfil-vagas-count");
            if (contador) {
                contador.textContent = quantidade; // Atualiza o número de vagas
            }
        })
        .catch(error => console.error("Erro ao contar vagas ativas:", error));
}

// Chamar a função ao carregar a página
document.addEventListener("DOMContentLoaded", () => {
    contarVagasAtivas();
});

getVagas();