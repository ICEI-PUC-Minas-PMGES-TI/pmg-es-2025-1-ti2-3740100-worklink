// URL do endpoint para criar uma aplicação
const aplicacaoApiUrl = `http://localhost:8080/aplicacoes`;

// URL do endpoint para verificar inscrição
const verificarInscricaoApiUrl = `http://localhost:8080/aplicacoes/verificar-inscricao`;

// Função para verificar se o usuário é um candidato
function isCandidato() {
    const userType = localStorage.getItem('userType');
    return userType === "candidato";
}

// Função para verificar se o candidato já está inscrito na vaga
function verificarInscricao(vagaId, callback) {
    const cpfCandidato = localStorage.getItem('cpfUsuario');
    fetch(`${verificarInscricaoApiUrl}?vagaId=${vagaId}&cpf=${cpfCandidato}`)
        .then(response => {
            if (!response.ok) {
                throw new Error("Erro ao verificar inscrição");
            }
            return response.json();
        })
        .then(inscrito => {
            callback(inscrito); // Executa o callback com o resultado
        })
        .catch(error => {
            console.error("Erro ao verificar inscrição:", error);
        });
}

// Função para inscrever-se na vaga
function inscreverVaga(vagaId, dataFinal, buttonId) {
    // Verifica se o usuário é um candidato
    if (!isCandidato()) {
        alert("Apenas candidatos podem se inscrever em vagas.");
        return;
    }

    // Verifica se a data atual está dentro do prazo
    const dataAtual = new Date();
    const dataPrazo = new Date(dataFinal);

    if (dataAtual > dataPrazo) {
        alert("O prazo para inscrição nesta vaga já se encerrou.");
        return;
    }

    // Dados da aplicação
    const candidatoId = localStorage.getItem('candidatoId');
    const cpfCandidato = localStorage.getItem('cpfUsuario');
    const aplicacao = {
        status: "Em Análise",
        vaga: { id: vagaId },
        candidato: { id: candidatoId, cpf: cpfCandidato }
    };

    // Envia a aplicação para o backend
    fetch(aplicacaoApiUrl, {
        method: "POST",
        headers: {
            "Content-Type": "application/json"
        },
        body: JSON.stringify(aplicacao)
    })
        .then(response => {
            if (!response.ok) {
                throw new Error("Erro ao se inscrever na vaga");
            }
            return response.json();
        })
        .then(data => {
            alert("Inscrição realizada com sucesso!");
            // Atualiza o botão para indicar que o candidato já está inscrito
            const inscreverBtn = document.getElementById(buttonId);
            inscreverBtn.disabled = true;
            inscreverBtn.textContent = "Já inscrito";
        })
        .catch(error => {
            console.error("Erro ao se inscrever na vaga:", error);
            alert("Erro ao se inscrever na vaga. Tente novamente mais tarde.");
        });
}