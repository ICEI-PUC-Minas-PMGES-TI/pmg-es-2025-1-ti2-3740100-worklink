### 3.3.4 Processo 4 – Comunicação e Notificações

_Este processo trata do envio e recebimento de mensagens entre usuários e a empresa, permitindo tanto o envio de currículos quanto a solicitação de suporte ou qualquer outra comunicação necessária._

#### Oportunidades de melhoria:
- Melhor organização das mensagens enviadas e recebidas.
- Padronização do fluxo de comunicação.
- Automatização de respostas para aumentar a eficiência do atendimento.

### Modelo BPMN

![image](https://github.com/ICEI-PUC-Minas-PMGES-TI/pmg-es-2025-1-ti2-3740100-worklink/blob/main/docs/images/Notifica%C3%A7%C3%A3o%20e%20comunica%C3%A7%C3%A3o%20Diagrama.png?raw=true)

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
| Mensagem | Área de texto | Mínimo 2 caracteres | - |
| Arquivo anexo | Arquivo | Opcional | - |

| **Comandos**   | **Destino**             | **Tipo**  |
| ---            | ---                      | ---       |
| Enviar        | Processamento da mensagem | Default   |
| Cancelar      | Cancelamento da ação     | Cancel    |

---

**Atividade 2: Roteamento da Mensagem**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| Destinatário | Seleção única | (RH, Candidato) | - |
| Status | Seleção única | (Recebido, Em Análise, Respondido) | Recebido |

| **Comandos**   | **Destino**                     | **Tipo**  |
| ---            | ---                              | ---       |
| Encaminhar    | Setor responsável | Default   |
| Aguardar Resposta | Verificação de inatividade | Default   |

---

**Atividade 3: Verificação de Inatividade**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| Tempo de Inatividade | Número | Medido em horas | 48h |

| **Comandos**   | **Destino**             | **Tipo**  |
| ---            | ---                      | ---       |
| Continuar Aguardando | Retorno ao fluxo principal | Default   |
| Encerrar Conversa | Finalização da comunicação | Default   |

---

**Atividade 4: Resposta ao Usuário**

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

**Atividade 5: Encerramento da Conversa**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| Mensagem Final | Área de texto | Obrigatório | "Sua conversa foi encerrada. Caso precise de mais informações, inicie um novo contato." |

| **Comandos**   | **Destino**             | **Tipo**  |
| ---            | ---                      | ---       |
| Finalizar | Arquivamento da comunicação | Default   |

---

_Esse fluxo garante uma comunicação eficiente e estruturada entre usuários e empresa, melhorando a gestão de mensagens e respostas._

