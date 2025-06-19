document.addEventListener("DOMContentLoaded", () => {
    const notificacaoBtn = document.getElementById("notificacaoBtn");
    const notificacaoDropdown = document.getElementById("notificacaoDropdown");
    const notificacaoBadge = document.getElementById("notificacaoBadge");
    const semNotificacoes = document.getElementById("semNotificacoes");

    let notificacoes = [];

    // Função para adicionar uma notificação
    function adicionarNotificacao(mensagem, tipo = "info") {
        notificacoes.push({ mensagem, tipo });

        // Atualiza o contador de notificações
        notificacaoBadge.style.display = "inline-block";
        notificacaoBadge.textContent = notificacoes.length;

        // Adiciona a notificação ao dropdown
        const notificacaoItem = document.createElement("li");
        notificacaoItem.className = `dropdown-item text-${tipo}`;
        notificacaoItem.textContent = mensagem;

        notificacaoDropdown.insertBefore(notificacaoItem, semNotificacoes);
        semNotificacoes.style.display = "none";
    }

    // Função para exibir feedback da empresa
    function exibirFeedbackEmpresa(feedback, empresa, vaga) {
        const mensagem = `Feedback da empresa ${empresa} para a vaga ${vaga}: "${feedback}"`;
        adicionarNotificacao(mensagem, "info");
    }

    // Função para carregar candidaturas e exibir notificações
    function carregarCandidaturas() {
        const apiUrl = "http://localhost:8080/candidaturas"; // Endpoint para buscar candidaturas

        fetch(apiUrl)
            .then(response => {
                if (!response.ok) {
                    throw new Error("Erro ao buscar candidaturas");
                }
                return response.json();
            })
            .then(candidaturas => {
                candidaturas.forEach(candidatura => {
                    if (candidatura.status === "Reprovado" && candidatura.feedback) {
                        exibirFeedbackEmpresa(candidatura.feedback, candidatura.empresa.nome, candidatura.vaga.titulo);
                    }
                });
            })
            .catch(error => {
                console.error("Erro ao carregar candidaturas:", error);
            });
    }

    // Limpar notificações ao clicar no sininho
    notificacaoBtn.addEventListener("click", () => {
        notificacaoBadge.style.display = "none";
    });

    // Chama a função ao carregar a página
    carregarCandidaturas();
});