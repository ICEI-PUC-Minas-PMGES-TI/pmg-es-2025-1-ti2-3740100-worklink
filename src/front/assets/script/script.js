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

// Função utilitária para formatar a data no padrão brasileiro
function formatarDataBR(dataISO) {
    if (!dataISO) return "Data não informada";
    const data = dataISO.length > 10 ? dataISO.substring(0, 10) : dataISO;
    const [ano, mes, dia] = data.split("-");
    return `${dia}/${mes}/${ano}`;
}

//exibirVagas -> Lista
function exibirVagas(vagas) {
    const lista = document.getElementById("lista-vagas");
    lista.innerHTML = ""; // Limpa a lista antes de adicionar novos itens

    if (!vagas || vagas.length === 0) {
        lista.innerHTML = `<p class="text-center">Nenhuma vaga cadastrada.</p>`;
        return;
    }

    // Cria o elemento de lista
    const ul = document.createElement("ul");
    ul.classList.add("vaga-lista");

    // Ordena as vagas pelo maior ID (mais recente primeiro)
    vagas.sort((a, b) => b.id - a.id);

    vagas.forEach(vaga => {
        const li = document.createElement("li");
        li.classList.add("vaga-item");

        // Trata o campo dataFinal
        let dataFinal = "Não especificada";
        if (vaga.dataFinal) {
            let data = vaga.dataFinal;
            if (data.length > 10) data = data.substring(0, 10);
            const [ano, mes, dia] = data.split("-");
            dataFinal = `${dia}/${mes}/${ano}`;
        }

        // Trata o campo dataCriacao (lançamento)
        let dataCriacao = "Data não informada";
        if (vaga.dataCriacao) {
            dataCriacao = formatarDataBR(vaga.dataCriacao);
        }

        const modalidade = vaga.modalidade || 'Não especificada';
        const tipoContrato = vaga.tipoContrato || 'Não especificado';
        const beneficios = vaga.beneficios && vaga.beneficios.trim() !== "" ? vaga.beneficios : 'Não informado';

        li.innerHTML = `
            <h3>${vaga.titulo}</h3>
            <p><strong>Sobre:</strong> ${vaga.descricao}</p>
            <p><strong>Benefícios:</strong> ${beneficios}</p>
            <p><strong>Data de Lançamento:</strong> ${dataCriacao}</p>
            <p><strong>Data Final:</strong> ${dataFinal}</p>
            <p><strong>Modalidade:</strong> ${modalidade}</p>
            <p><strong>Tipo de Contrato:</strong> ${tipoContrato}</p>
            <p><strong>Salário:</strong> R$ ${vaga.salario.toFixed(2)}</p>
            <button class="editar" onclick="editarVaga(${vaga.id})">Editar</button>
            <button class="ver-candidaturas" onclick="verCandidaturas(${vaga.id})">Ver candidaturas</button>
            <button class="excluir" onclick="deletarVaga(${vaga.id})">Excluir</button>
        `;

        ul.appendChild(li);
    });

    lista.appendChild(ul);
}

function editarVaga(id) {
    sessionStorage.setItem('idVagaEdicao', id); // Salva o ID da vaga no sessionStorage
    window.location.href = "editarVaga.html"; // Redireciona para a página de edição
}

function limparValor(valor) {
    if (!valor) return 0;
    return valor.replace(/[^\d,]/g, '').replace(',', '.');
}

// Função para contar vagas ativas da empresa logada
function contarVagasAtivas() {
    const cnpj = localStorage.getItem("empresaCnpj");
    if (!cnpj) {
        alert("CNPJ da empresa não encontrado. Faça login novamente.");
        return;
    }
    fetch(`http://localhost:8080/vagas/empresa/cnpj/${encodeURIComponent(cnpj)}`)
        .then(response => {
            if (!response.ok) throw new Error("Erro ao buscar vagas");
            return response.json();
        })
        .then(vagas => {
            const quantidade = vagas.length; // Conta o número de vagas da empresa logada
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

function verCandidaturas(vagaId) {
    // Redireciona para a página de candidaturas da vaga com o ID na URL
    window.location.href = `candidaturasVaga.html?vagaId=${vagaId}`;
}