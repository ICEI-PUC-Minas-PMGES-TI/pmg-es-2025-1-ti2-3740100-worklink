### 3.3.6 Processo 6 – AVALIAÇÃO DA EMPRESA

_O processo de avaliação do candidato inicia quando ele completa a avaliação do processo seletivo da empresa. A empresa avalia suas respostas e decide se o processo segue para a próxima etapa ou se um feedback é enviado ao candidato. Caso o candidato avance, ele pode ser avaliado em uma entrevista técnica ou comportamental. Durante todas as etapas, o candidato é notificado sobre sua situação no processo. As decisões tomadas pela empresa e o feedback fornecido ao candidato são registrados no sistema, encerrando o processo de avaliação._

![Modelo BPMN do Processo 6](https://github.com/user-attachments/assets/72768c6b-dd6c-40e0-a6e3-22e6685bf060)


#### Detalhamento das atividades

**Avaliação do Processo Seletivo (do candidato)**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| clareza_informacoes           | Caixa de Texto   | mínimo 10 palavras |                |
| facilidade_uso           | Caixa de Texto   | mínimo 10 palavras |           |

| **Comandos**         |  **Destino**                   | **Tipo** |
| ---                  | ---                            | ---               |
| enviar_feedback | Fim do processo de avaliação | default |
| cancelar | Fim do processo de avaliação  | cancel |


**Avaliação da Entrevista (do candidato)**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| clareza_perguntas | Caixa de Texto  | mínimo 10 palavras |                   |
| qualidade_entrevistador | Caixa de Texto | mínimo 10 palavras |                   |

| **Comandos**         |  **Destino**                   | **Tipo**          |
| ---                  | ---                            | ---               |
| enviar_feedback               | Fim do processo de avaliação              | default           |
| cancelar            | Fim do processo de avaliação  |        cancel           |

**Avaliação do Processo de Seleção (da empresa)**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| satisfação_geral | Caixa de Texto   | mínimo 10 palavras |                |
| pontos_fortes | Caixa de Texto   | mínimo 10 palavras |           |

| **Comandos**         |  **Destino**                   | **Tipo** |
| ---                  | ---                            | ---               |
| enviar_feedback               | Fim do processo de avaliação              | default           |
| cancelar            | Fim do processo de avaliação  |        cancel           |

**Avaliação do Tempo do Processo (da empresa)**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| tempo_total | Caixa de Texto   | texto livre |                |

| **Comandos**         |  **Destino**                   | **Tipo** |
| ---                  | ---                            | ---               |
| enviar_feedback               | Fim do processo de avaliação              | default           |
| cancelar            | Fim do processo de avaliação  |        cancel           |

**Comentários Finais (Candidato e Empresa)**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| feedback_geral	 | Caixa de Texto   | mínimo 10 palavras |                |

| **Comandos**         |  **Destino**                   | **Tipo** |
| ---                  | ---                            | ---               |
| enviar_feedback               | Fim do processo de avaliação              | default           |
| cancelar            | Fim do processo de avaliação  |        cancel           |
