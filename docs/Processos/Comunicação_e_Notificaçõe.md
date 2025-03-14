### 3.3.4 Processo 4 – Comunicação e Notificações

_Este processo trata do envio e recebimento de mensagens entre usuários e a empresa, permitindo tanto o envio de currículos quanto a solicitação de suporte ou qualquer outra comunicação necessária._

#### Oportunidades de melhoria:
- Melhor organização das mensagens enviadas e recebidas.
- Padronização do fluxo de comunicação.
- Automatização de respostas para aumentar a eficiência do atendimento.

### Modelo BPMN

_(Aqui deve ser inserido o diagrama BPMN correspondente ao fluxo de comunicação e notificações)_

#### Detalhamento das atividades

_Este processo permite o envio de mensagens de diferentes tipos para a empresa e o recebimento de respostas. As atividades descritas abaixo abrangem o fluxo geral de comunicação._

_Os tipos de dados utilizados são:_

_* **Área de texto** - campo para mensagens longas._
_* **Caixa de texto** - campo para assuntos curtos._
_* **Número** - campo para identificação de protocolos._
_* **Data e Hora** - registro de quando a mensagem foi enviada/recebida._
_* **Arquivo** - upload de documentos, como currículos ou capturas de tela para suporte._
_* **Seleção única** - escolha do tipo de mensagem (currículo, suporte, outro)._
_* **Link** - armazenamento de URLs relevantes._

---

**Atividade 1: Envio de Mensagem**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| Nome do remetente | Caixa de Texto | Obrigatório | - |
| E-mail | Caixa de Texto | Formato de e-mail | - |
| Tipo de mensagem | Seleção única | (Currículo, Suporte, Outro) | Outro |
| Mensagem | Área de texto | Mínimo 10 caracteres | - |
| Arquivo anexo | Arquivo | Opcional | - |

| **Comandos**   | **Destino**             | **Tipo**  |
| ---            | ---                      | ---       |
| Enviar        | Processamento da mensagem | Default   |
| Cancelar      | Cancelamento da ação     | Cancel    |

---

**Atividade 2: Processamento da Mensagem**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| ID da mensagem | Número | Gerado automaticamente | - |
| Data e hora do recebimento | Data e Hora | Automático | - |
| Status | Seleção única | (Recebido, Em Análise, Respondido) | Recebido |

| **Comandos**   | **Destino**                     | **Tipo**  |
| ---            | ---                              | ---       |
| Responder     | Geração de resposta              | Default   |
| Encaminhar    | Encaminhamento para setor responsável | Default   |
| Fechar        | Finalização do atendimento       | Cancel    |

---

**Atividade 3: Resposta ao Usuário**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| ID da mensagem | Número | Obrigatório | - |
| Resposta | Área de texto | Obrigatório | - |
| Anexo (se necessário) | Arquivo | Opcional | - |
| Data e hora da resposta | Data e Hora | Automático | - |

| **Comandos**   | **Destino**             | **Tipo**  |
| ---            | ---                      | ---       |
| Enviar resposta | Fim do Processo | Default   |
| Reabrir solicitação | Retorno ao Processamento da Mensagem | Default   |

---
