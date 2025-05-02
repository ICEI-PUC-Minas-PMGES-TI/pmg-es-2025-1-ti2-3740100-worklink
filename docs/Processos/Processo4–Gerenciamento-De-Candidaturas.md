### 3.3.4 Processo 4 – GERENCIAMENTO DE CANDIDATURAS

_O processo inicia quando o candidato se inscreve em uma vaga. A empresa avalia sua candidatura e decide se ele está apto. Se não estiver, envia um feedback. Se estiver, pode agendar uma entrevista ou aprová-lo diretamente. O candidato recebe notificações conforme sua situação.Todas as decisões são registradas no sistema, encerrando o processo._

![Modelo BPMN do Processo 1](https://github.com/ICEI-PUC-Minas-PMGES-TI/pmg-es-2025-1-ti2-3740100-worklink/blob/main/docs/images/Processo4-Gerenciamento-De-Candidaturas.png)


#### Detalhamento das atividades

_Descreva aqui cada uma das propriedades das atividades do processo 4. 
Devem estar relacionadas com o modelo de processo apresentado anteriormente._

_Os tipos de dados a serem utilizados são:_

_* **Área de texto** - campo texto de múltiplas linhas_

_* **Caixa de texto** - campo texto de uma linha_

_* **Número** - campo numérico_

_* **Data** - campo do tipo data (dd-mm-aaaa)_

_* **Hora** - campo do tipo hora (hh:mm:ss)_

_* **Data e Hora** - campo do tipo data e hora (dd-mm-aaaa, hh:mm:ss)_

_* **Imagem** - campo contendo uma imagem_

_* **Seleção única** - campo com várias opções de valores que são mutuamente exclusivas (tradicional radio button ou combobox)_

_* **Seleção múltipla** - campo com várias opções que podem ser selecionadas mutuamente (tradicional checkbox ou listbox)_

_* **Arquivo** - campo de upload de documento_

_* **Link** - campo que armazena uma URL_

_* **Tabela** - campo formado por uma matriz de valores_

**Visualizar aplicações**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| [Nome do campo] | [tipo de dados]  |                |                   |
|                 |                  |                |                   |
|                 |                  |                |                   |

| **Comandos**         |  **Destino**                   | **Tipo** |
| ---                  | ---                            | ---               |
| [Nome do botão/link] | Atividade/processo de destino  | (default/cancel  ) |
| btn-agendarentrevista               | Início da atividade de agendar entrevista       | default           |
| btn-testetécnico           | Início da atividade de teste técnico            | default           |
| btn-desistir                 | Início da atividade desistir da vaga            | default           |

**Agendar entrevista**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| [Nome do campo] | [tipo de dados]  |                |                   |
| data                |   Data            |                |  dd-mm-aaaa               |
| horario             |   Hora            |                |  hh:mm:ss                 |

| **Comandos**         |  **Destino**                   | **Tipo**          |
| ---                  | ---                            | ---               |
| [Nome do botão/link] | Atividade/processo de destino  | (default/cancel  ) |
|  btn-enviarAgendamento                    |     Envia o agendamento para a empresa                  |     default              |

**Realizar teste técnico**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| [Nome do campo] | [tipo de dados]  |                |                   |
| upload-teste               |   Arquivo            |                |                 |

| **Comandos**         |  **Destino**                   | **Tipo**          |
| ---                  | ---                            | ---               |
| [Nome do botão/link] | Atividade/processo de destino  | (default/cancel  ) |
|  btn-enviarTeste        |     Envia o teste para a empresa avaliar                  |     default              |

**Desistir da vaga**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| [Nome do campo] | [tipo de dados]  |                |                   |
|                 |                  |                |                   |

| **Comandos**         |  **Destino**                   | **Tipo**          |
| ---                  | ---                            | ---               |
| [Nome do botão/link] | Atividade/processo de destino  | (default/cancel  )|
|  btn-cancelar            |  Cancela a desistencia         |     cancel        |
|  btn-desistir            |  Desiste da vaga               |     default       |

**Avaliar teste do candidato**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| [Nome do campo] | [tipo de dados]  |                |                   |
| feedback                |  Área de texto                |                |                   |

| **Comandos**         |  **Destino**                   | **Tipo**          |
| ---                  | ---                            | ---               |
| [Nome do botão/link] | Atividade/processo de destino  | (default/cancel  )|
|  btn-aprovar             |  Aprova o candidato para a proxima etapa      |     default       |
|  btn-reprovar            |  Reprova o candidato da seleção               |     default       |
