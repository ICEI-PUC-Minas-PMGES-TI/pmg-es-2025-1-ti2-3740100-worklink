### 3.3.6 Processo 6 – AVALIAÇÃO DA EMPRESA

_O processo inicia quando o candidato se inscreve em uma vaga. A empresa avalia sua candidatura e decide se ele está apto. Se não estiver, envia um feedback. Se estiver, pode agendar uma entrevista ou aprová-lo diretamente. O candidato recebe notificações conforme sua situação.Todas as decisões são registradas no sistema, encerrando o processo._

![Modelo BPMN do Processo 6](https://github.com/ICEI-PUC-Minas-PMGES-TI/pmg-es-2025-1-ti2-3740100-worklink/blob/main/docs/images/Status%20da%20candidatura%20Diagrama.png?raw=true)


#### Detalhamento das atividades

Avaliação do Processo Seletivo (do candidato)

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| clareza_informacoes           | Caixa de Texto   | mínimo 10 palavras |                |
| facilidade_uso           | Caixa de Texto   | mínimo 10 palavras |           |

| **Comandos**         |  **Destino**                   | **Tipo** |
| ---                  | ---                            | ---               |
| enviar_feedback | Fim do processo de avaliação | default |
| cancelar | Fim do processo de avaliação  | cancel |


Avaliação da Entrevista (do candidato)

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| clareza_perguntas | Caixa de Texto  | mínimo 10 palavras |                   |
| qualidade_entrevistador | Caixa de Texto | mínimo 10 palavras |                   |

| **Comandos**         |  **Destino**                   | **Tipo**          |
| ---                  | ---                            | ---               |
| enviar_feedback               | Fim do processo de avaliação              | default           |
| cancelar            | Fim do processo de avaliação  |        cancel           |

Avaliação do Processo de Seleção (da empresa)

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| satisfação_geral | Caixa de Texto   | mínimo 10 palavras |                |
| pontos_fortes | Caixa de Texto   | mínimo 10 palavras |           |

| **Comandos**         |  **Destino**                   | **Tipo** |
| ---                  | ---                            | ---               |
| enviar_feedback               | Fim do processo de avaliação              | default           |
| cancelar            | Fim do processo de avaliação  |        cancel           |

Avaliação do Tempo do Processo (da empresa)

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| tempo_total | Caixa de Texto   | texto livre |                |

| **Comandos**         |  **Destino**                   | **Tipo** |
| ---                  | ---                            | ---               |
| enviar_feedback               | Fim do processo de avaliação              | default           |
| cancelar            | Fim do processo de avaliação  |        cancel           |

Comentários Finais (Candidato e Empresa)

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| feedback_geral	 | Caixa de Texto   | mínimo 10 palavras |                |

| **Comandos**         |  **Destino**                   | **Tipo** |
| ---                  | ---                            | ---               |
| enviar_feedback               | Fim do processo de avaliação              | default           |
| cancelar            | Fim do processo de avaliação  |        cancel           |
