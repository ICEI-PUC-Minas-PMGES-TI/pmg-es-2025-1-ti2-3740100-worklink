### 3.3.2 Processo 2 – Cadastro de Candidato e Empresa

Processo de Cadastro de Candidato e Empresa – Aplicativo de Vagas de Emprego
Este documento apresenta o modelo BPMN completo para o Processo de Cadastro de Candidato e Empresa do Aplicativo de Vagas de Emprego. São destacados os fluxos principais, as oportunidades de melhoria, o detalhamento das atividades com os campos e comandos, além dos tipos de dados a serem utilizados.


![Modelo BPMN do Processo 1](https://github.com/ICEI-PUC-Minas-PMGES-TI/pmg-es-2025-1-ti2-3740100-worklink/blob/main/docs/images/ProcessoDeCadastroDeCandidatoOuEmpresa.png)


Fluxo do Processo
O fluxo do processo de cadastro é dividido em três atividades principais:

Cadastro do Candidato
Cadastro da Empresa
Processamento das Inscrições
Atividade 1: Cadastro do Candidato
Nesta etapa, o candidato se cadastra no aplicativo fornecendo informações essenciais, como e-mail, senha, nome completo e telefone. São oferecidos comandos para submeter a inscrição ou cancelar o cadastro.



#### Detalhamento das atividades

_Descreva aqui cada uma das propriedades das atividades do processo de *Cadastro de Candidato e Empresa*.  
Devem estar relacionadas com o modelo de processo apresentado anteriormente._

Os tipos de dados a serem utilizados são:

* *Área de texto* - campo texto de múltiplas linhas
    * Usado para campos como *Mensagem de apresentação* do candidato ou *Descrição da vaga* da empresa, onde é necessário incluir informações mais detalhadas.

* *Caixa de texto* - campo texto de uma linha
    * Usado para campos como *Nome do candidato, **E-mail, **Telefone* e *Nome da empresa*, que exigem dados em formato simples e curto.

* *Número* - campo numérico
    * Usado para campos como *ID da candidatura* e *ID da empresa*, que são gerados automaticamente como números únicos.

* *Data* - campo do tipo data (dd-mm-aaaa)
    * Usado para registrar a *data de inscrição* e a *data de validade da vaga*, caso seja necessário definir um prazo para a candidatura.

* *Hora* - campo do tipo hora (hh:mm:ss)
    * Usado para registrar a *hora de inscrição* do candidato, quando necessário, para fins de registro preciso no processo.

* *Data e Hora* - campo do tipo data e hora (dd-mm-aaaa, hh:mm:ss)
    * Usado para campos como *Data e hora da inscrição* e *Data e hora de resposta ao candidato*, registrando tanto a data quanto a hora exata do evento.

* *Imagem* - campo contendo uma imagem
    * Usado para a *foto do candidato* ou a *logo da empresa*, caso as informações visuais sejam necessárias.

* *Seleção única* - campo com várias opções de valores que são mutuamente exclusivas (tradicional radio button ou combobox)
    * Usado para campos como *Tipo de vaga* (Desenvolvimento, Suporte, Outro), onde o candidato ou a empresa escolhe uma única opção.

* *Seleção múltipla* - campo com várias opções que podem ser selecionadas mutuamente (tradicional checkbox ou listbox)
    * Usado para opções como *Habilidades do candidato* ou *Áreas de atuação da vaga*, onde o candidato pode selecionar várias opções.

* *Arquivo* - campo de upload de documento
    * Usado para o *Currículo do candidato* ou *Anexos* relacionados à vaga, onde documentos (PDF, DOC, etc.) precisam ser enviados.

* *Link* - campo que armazena uma URL
    * Usado para armazenar *URLs de portfólios* ou *Links de redes sociais* do candidato ou da empresa.

* *Tabela* - campo formado por uma matriz de valores
    * Usado para *histórico de candidaturas* ou *detalhes da vaga*, onde uma lista de informações organizadas em linhas e colunas é necessária (como histórico de entrevistas ou requisitos da vaga).



*Cadastro do Candidato*

| *Campo*       | *Tipo*         | *Restrições* | *Valor default* |
| ---             | ---              | ---            | ---               |
| Nome do candidato | Caixa de Texto   | Obrigatório    | -                 |
| E-mail            | Caixa de Texto   | Formato de e-mail | -             |
| Senha             | Caixa de Texto   | Mínimo 6 caracteres | -             |
| Telefone          | Caixa de Texto   | Formato de telefone | -            |
| Tipo de vaga      | Seleção única    | (Desenvolvimento, Suporte, Outro) | Outro |
| Currículo (anexo) | Arquivo          | Opcional (PDF, DOC) | -              |

| *Comandos*   | *Destino*               | *Tipo*  |
| -------------- | ------------------------- | --------- |
| Enviar         | Processamento da Inscrição | Default   |
| Cancelar       | Cancelamento da ação      | Cancel    |

---

*Cadastro da Empresa*

| *Campo*       | *Tipo*         | *Restrições* | *Valor default* |
| ---             | ---              | ---            | ---               |
| Nome da empresa | Caixa de Texto   | Obrigatório    | -                 |
| E-mail da empresa | Caixa de Texto   | Formato de e-mail | -              |
| Tipo de vaga    | Seleção única    | (Desenvolvimento, Suporte, Outro) | Outro |
| Descrição da vaga | Área de Texto    | Mínimo 50 caracteres | -            |
| Localização     | Caixa de Texto   | Opcional       | -                 |

| *Comandos*   | *Destino*               | *Tipo*  |
| -------------- | ------------------------- | --------- |
| Enviar         | Processamento do Cadastro  | Default   |
| Cancelar       | Cancelamento da ação      | Cancel    |

---

*Processamento das Inscrições*

| *Campo*       | *Tipo*         | *Restrições* | *Valor default* |
| ---             | ---              | ---            | ---               |
| ID da candidatura | Número          | Gerado automaticamente | -            |
| ID da empresa    | Número          | Gerado automaticamente | -            |
| Data e hora da inscrição | Data e Hora | Automático | -              |
| Status da candidatura | Seleção única | (Recebido, Em Análise, Entrevista Agendada) | Recebido |

| *Comandos*   | *Destino*               | *Tipo*  |
| -------------- | ------------------------- | --------- |
| Avaliar        | Análise da candidatura    | Default   |
| Agendar entrevista | Agendamento de entrevista | Default |
| Fechar         | Finalização do cadastro   | Cancel    |
