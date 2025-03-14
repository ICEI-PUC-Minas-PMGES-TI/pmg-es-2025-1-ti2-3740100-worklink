Processo Seletivo – Aplicativo de Vagas de Emprego
Este documento apresenta o modelo BPMN completo para o Processo Seletivo do Aplicativo de Vagas de Emprego. São destacados os fluxos principais, as oportunidades de melhoria, o detalhamento das atividades com os campos e comandos, além dos tipos de dados a serem utilizados.

![Modelo BPMN do Processo 1](https://github.com/ICEI-PUC-Minas-PMGES-TI/pmg-es-2025-1-ti2-3740100-worklink/blob/main/docs/images/process.png)

Descrição do Processo
Fluxo do Processo
O fluxo do processo seletivo é dividido em duas atividades principais:

Inscrição do Candidato
Nesta etapa, o candidato se cadastra no aplicativo fornecendo informações essenciais, como e-mail, senha, nome completo e telefone. São oferecidos comandos para submeter a candidatura ou cancelar a inscrição.

Triagem e Avaliação
Após a inscrição, o candidato deve enviar informações complementares, incluindo currículo, detalhes sobre a experiência profissional, pretensão salarial e disponibilidade para entrevistas. Nesta etapa, o fluxo permite avaliar a candidatura ou solicitar mais informações, conforme necessário.

Oportunidades de Melhoria
As melhorias identificadas para otimização do processo seletivo incluem:

Triagem de Currículos: Aperfeiçoar a análise dos currículos enviados para reduzir o tempo de avaliação.
Notificações Automatizadas: Implementar alertas automáticos para manter os candidatos informados sobre o andamento da seleção.
Agendamento de Entrevistas: Agilizar o processo de marcação de entrevistas para aumentar a eficiência do processo.
Detalhamento das Atividades
Abaixo, apresentamos cada atividade em tabelas com seus campos, restrições, valor default e comandos.

Atividade 1: Inscrição do Candidato
Campos

Campo	Tipo	Restrições	Valor default
email	Caixa de texto	Formato de e-mail	-
senha	Caixa de texto	Mínimo de 8 caracteres	-
nome completo	Caixa de texto	-	-
telefone	Caixa de texto	Formato de telefone	-
Comandos

Comando	Destino	Tipo
submeter candidatura	Triagem e Avaliação	Default
cancelar inscrição	Fim do Processo Seletivo	Cancel
Atividade 2: Triagem e Avaliação
Campos

Campo	Tipo	Restrições	Valor default
currículo	Arquivo	PDF ou DOC	-
experiência profissional	Área de texto	Mínimo de 50 caracteres	-
pretensão salarial	Número	-	-
disponibilidade para entrevista	Data e Hora	-	-
Comandos

Comando	Destino	Tipo
avaliar candidatura	Decisão Final	Default
solicitar mais informações	Solicitação Adicional	Cancel
(Obs.: As atividades “Decisão Final” e “Solicitação Adicional” podem ser etapas posteriores, caso sejam detalhadas no modelo BPMN ou no fluxo do sistema.)

Tipos de Dados Utilizados
Área de texto: campo para múltiplas linhas.
Caixa de texto: campo para uma linha.
Número: campo para valores numéricos.
Data: campo para datas no formato dd-mm-aaaa.
Hora: campo para horários no formato hh:mm:ss.
Data e Hora: campo para data e hora no formato dd-mm-aaaa, hh:mm:ss.
Imagem: campo para upload de imagens.
Seleção única: opções mutuamente exclusivas (radio button ou combobox).
Seleção múltipla: múltiplas opções (checkbox ou listbox).
Arquivo: campo para upload de documentos.
Link: campo para armazenar URLs.
Tabela: matriz de valores.
