### 3.3.3 Processo 3 – PROCESSO SELETIVO

_Apresente aqui o nome e as oportunidades de melhoria para o processo 3. 
Em seguida, apresente o modelo do processo 3, descrito no padrão BPMN._

![Modelo BPMN do Processo 1](https://github.com/ICEI-PUC-Minas-PMGES-TI/pmg-es-2025-1-ti2-3740100-worklink/blob/main/docs/images/process.png)


%% Processo Seletivo – Aplicativo de Vagas de Emprego - Modelo BPMN Completo

flowchart TD
    %% Eventos Iniciais e Finais
    start([Início do Processo Seletivo: Aplicativo de Vagas de Emprego])
    finish([Fim do Processo Seletivo])
    
    %% Atividades do Processo
    act1[Atividade 1: Inscrição do Candidato]
    act2[Atividade 2: Triagem e Avaliação]
    
    %% Fluxo do Processo
    start --> act1
    act1 --> act2
    act2 --> finish

    %% Oportunidades de Melhoria (anexadas ao início)
    start --- improve[Oportunidades de melhoria:
    - Otimizar a triagem de currículos
    - Automatizar notificações aos candidatos
    - Agilizar o agendamento de entrevistas]

    %% Tipos de Dados Utilizados
    start --- types[Tipos de Dados:
    • Área de texto: múltiplas linhas
    • Caixa de texto: uma linha
    • Número: numérico
    • Data: (dd-mm-aaaa)
    • Hora: (hh:mm:ss)
    • Data e Hora: (dd-mm-aaaa, hh:mm:ss)
    • Imagem: arquivo de imagem
    • Seleção única: radio button/combobox
    • Seleção múltipla: checkbox/listbox
    • Arquivo: upload de documento
    • Link: URL
    • Tabela: matriz de valores]

    %% Detalhamento da Atividade 1 - Inscrição do Candidato
    act1 --- note1[Detalhamento da Atividade 1:
Campos:
  - email: Caixa de texto (restrição: formato de e-mail)
  - senha: Caixa de texto (restrição: mínimo de 8 caracteres)
  - nome completo: Caixa de texto
  - telefone: Caixa de texto (restrição: formato de telefone)
Comandos:
  - submeter candidatura: Destino → Triagem e Avaliação (default)
  - cancelar inscrição: Destino → Fim do Processo Seletivo (cancel)]

    %% Detalhamento da Atividade 2 - Triagem e Avaliação
    act2 --- note2[Detalhamento da Atividade 2:
Campos:
  - currículo: Arquivo (restrição: PDF, DOC)
  - experiência profissional: Área de texto (restrição: mínimo de 50 caracteres)
  - pretensão salarial: Número
  - disponibilidade para entrevista: Data e Hora
Comandos:
  - avaliar candidatura: Destino → Decisão Final (default)
  - solicitar mais informações: Destino → Solicitação Adicional (cancel)]


Descrição do Processo
Fluxo do Processo
O fluxo do processo seletivo é dividido em duas atividades principais:

Inscrição do Candidato
Nesta etapa, o candidato se cadastra no aplicativo fornecendo informações essenciais, como e-mail, senha, nome completo e telefone. São oferecidos comandos para submeter a candidatura ou cancelar a inscrição.

Triagem e Avaliação
Após a inscrição, o candidato deve enviar informações complementares, incluindo currículo, detalhes sobre a experiência profissional, pretensão salarial e disponibilidade para entrevistas. Nesta etapa, o fluxo permite a avaliação da candidatura ou a solicitação de informações adicionais, conforme necessário.

Oportunidades de Melhoria
As melhorias identificadas para otimização do processo seletivo incluem:

Triagem de Currículos: Aperfeiçoar a análise dos currículos enviados para reduzir o tempo de avaliação.
Notificações Automatizadas: Implementar alertas automáticos para manter os candidatos informados sobre o andamento da seleção.
Agendamento de Entrevistas: Agilizar o processo de marcação de entrevistas para aumentar a eficiência do processo.
Tipos de Dados Utilizados
Para a implementação do sistema, foram definidos os seguintes tipos de dados:

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
