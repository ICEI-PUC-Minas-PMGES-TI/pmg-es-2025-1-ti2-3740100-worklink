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

// Função para retornar o ícone Font Awesome conforme o título da vaga
function getIconeVaga(titulo) {
    if (!titulo) return '<i class="fa-solid fa-briefcase"></i>';
    const t = titulo.toLowerCase();
    if (t.includes("dev") || t.includes("programador") || t.includes("software")) return '<i class="fa-solid fa-laptop-code"></i>';
    if (t.includes("design")) return '<i class="fa-solid fa-palette"></i>';
    if (t.includes("analista") || t.includes("dados") || t.includes("bi")) return '<i class="fa-solid fa-chart-line"></i>';
    if (t.includes("vendas") || t.includes("comercial")) return '<i class="fa-solid fa-cart-shopping"></i>';
    if (t.includes("rh") || t.includes("recursos humanos")) return '<i class="fa-solid fa-user-tie"></i>';
    if (t.includes("engenheiro") || t.includes("engenharia")) return '<i class="fa-solid fa-gears"></i>';
    if (t.includes("administrativo") || t.includes("administração")) return '<i class="fa-solid fa-building"></i>';
    return '<i class="fa-solid fa-briefcase"></i>';
}

//exibirVagas -> Lista
function exibirVagas(vagas) {
    const lista = document.getElementById("lista-vagas");
    lista.innerHTML = ""; // Limpa a lista antes de adicionar novos itens

    if (!vagas || vagas.length === 0) {
        lista.innerHTML = `<p class="text-center">Nenhuma vaga cadastrada.</p>`;
        return;
    }

    // Ordena as vagas pelo maior ID (mais recente primeiro)
    vagas.sort((a, b) => b.id - a.id);

    vagas.forEach(vaga => {
        const card = document.createElement("div");
        card.classList.add("vaga-card");

        // Trata datas
        let dataFinal = "Não especificada";
        if (vaga.dataFinal) {
            let data = vaga.dataFinal;
            if (data.length > 10) data = data.substring(0, 10);
            const [ano, mes, dia] = data.split("-");
            dataFinal = `${dia}/${mes}/${ano}`;
        }
        let dataCriacao = "Data não informada";
        if (vaga.dataCriacao) {
            dataCriacao = formatarDataBR(vaga.dataCriacao);
        }

        const modalidade = vaga.modalidade || 'Não especificada';
        const tipoContrato = vaga.tipoContrato || 'Não especificado';
        const beneficios = vaga.beneficios && vaga.beneficios.trim() !== "" ? vaga.beneficios : 'Não informado';

        // Select de status
        const statusAtual = vaga.status ? vaga.status.toLowerCase() : "aberta";
        const selectStatus = `
            <select class="vaga-status-select${statusAtual === "fechada" ? " fechada" : ""}" style="float:right; min-width:110px; border-radius:0.7rem; margin-left:1rem;"
                onchange="alterarStatusVaga(${vaga.id}, this.value)">
                <option value="aberta" ${statusAtual === "aberta" ? "selected" : ""}>Aberta</option>
                <option value="fechada" ${statusAtual === "fechada" ? "selected" : ""}>Fechada</option>
            </select>
        `;

        card.innerHTML = `
            <div class="vaga-info">
                <div style="display:flex; justify-content:space-between; align-items:flex-start;">
                    <h2>${getIconeVaga(vaga.titulo)} ${vaga.titulo}</h2>
                    ${selectStatus}
                </div>
                <p><i class="fas fa-align-left"></i> <strong>Descrição:</strong> ${vaga.descricao}</p>
                <p><i class="fas fa-gift"></i> <strong>Benefícios:</strong> ${beneficios}</p>
                <p><i class="fas fa-file-contract"></i> <strong>Tipo de Contrato:</strong> ${tipoContrato}</p>
                <p><i class="fas fa-map-marker-alt"></i> <strong>Modalidade:</strong> ${modalidade}</p>
                <p><i class="fas fa-calendar"></i> <strong>Data de publicação:</strong> ${dataCriacao}</p>
                <p><i class="fas fa-calendar-check"></i> <strong>Fim das inscrições:</strong> ${dataFinal}</p>
                <p><i class="fas fa-money-bill-wave"></i> <strong>Salário:</strong> R$ ${vaga.salario ? vaga.salario.toFixed(2) : "A combinar"}</p>
            </div>
            <div class="vaga-actions">
                <button class="btn-editar" onclick="editarVaga(${vaga.id})"><i class="fas fa-edit"></i> Editar</button>
                <button class="btn-candidatos" onclick="verCandidaturas(${vaga.id})"><i class="fas fa-users"></i> Ver candidaturas</button>
                <button class="btn-excluir" onclick="deletarVaga(${vaga.id})"><i class="fas fa-trash"></i> Excluir</button>
            </div>
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

// Função para carregar e exibir vagas no perfil da empresa



function verCandidaturas(vagaId) {
    // Redireciona para a página de candidaturas da vaga com o ID na URL
    window.location.href = `candidaturasVaga.html?vagaId=${vagaId}`;
}

// Função para alterar status da vaga (implemente a chamada à API conforme seu backend)
function alterarStatusVaga(id, novoStatus) {
    // Primeiro, busca a vaga atual
    fetch(`http://localhost:8080/vagas/${id}`)
        .then(resp => resp.json())
        .then(vaga => {
            // Atualiza só o status
            vaga.status = novoStatus;

            // Remove campos que não devem ser enviados (caso existam)
            delete vaga.empresa; // Se o backend não espera o objeto empresa, remova esta linha

            // Envia todos os dados da vaga no PUT
            return fetch(`http://localhost:8080/vagas/${id}`, {
                method: 'PUT',
                headers: { 'Content-Type': 'application/json' },
                body: JSON.stringify(vaga)
            });
        })
        .then(resp => {
            if (!resp.ok) throw new Error('Erro ao atualizar status');
            getVagas();
        })
        .catch(() => alert('Erro ao atualizar status da vaga!'));
}