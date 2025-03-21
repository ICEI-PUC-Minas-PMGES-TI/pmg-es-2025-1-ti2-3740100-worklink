Processo Seletivo – Aplicativo de Vagas de Emprego

Este documento apresenta o modelo BPMN completo para o Processo Seletivo do Aplicativo de Vagas de Emprego. São destacados os fluxos principais, as oportunidades de melhoria, o detalhamento das atividades com os campos e comandos, além dos tipos de dados a serem utilizados.

![Usuário Se candidata a vaga](https://github.com/user-attachments/assets/a027d559-6b2e-441f-8fa9-2ca274422fc8)

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


**Atividade 1: Inscrição do Candidato**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| Nome do candidato | Caixa de Texto | Obrigatório | - |
| E-mail | Caixa de Texto | Formato de e-mail | - |
| Tipo de vaga | Seleção única | (Desenvolvimento, Suporte, Outro) | Outro |
| Currículo (anexo)	 | Arquivo | Opcional (PDF, DOC) | - |
| Mensagem de apresentação | Área de texto | Mínimo 10 caracteres	 | - |

| **Comandos**   | **Destino**             | **Tipo**  |
| ---            | ---                      | ---       |
| Enviar        | Processamento da Candidatura	 | Default   |
| Cancelar      | Cancelamento da ação     | Cancel    |

---

**Atividade 2: Processamento da Candidatura**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| ID da candidatura | Número | Gerado automaticamente | - |
| Data e hora do recebimento | Data e Hora | Automático | - |
| Status | Seleção única | (Recebido, Em Análise, Respondido) | Recebido |

| **Comandos**   | **Destino**                     | **Tipo**  |
| ---            | ---                              | ---       |
| Avaliar candidatura | Resposta ao Candidato | Default   |
| Solicitar mais informações  | Retorno ao candidato (para envio de dados) | Default   |
| Fechar  | Finalização do atendimento | Cancel  |

---

**Atividade 3: Resposta ao Candidato**

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
| ---             | ---              | ---            | ---               |
| ID da candidatura | Número | Obrigatório | - |
| Resposta | Área de texto | Obrigatório | - |
| Anexo (se necessário) | Arquivo | Opcional | - |
| Data e hora da resposta | Data e Hora | Automático | - |

| **Comandos**   | **Destino**             | **Tipo**  |
| ---            | ---                      | ---       |
| Enviar resposta | Fim do Processo | Default   |
| Reabrir solicitação | Retorno ao Processamento da Mensagem | Default   |

---
