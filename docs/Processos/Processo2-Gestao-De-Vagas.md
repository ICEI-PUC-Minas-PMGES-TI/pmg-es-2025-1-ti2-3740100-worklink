### 3.3.2 Processo 2 – GESTÃO DE VAGAS

_O processo Gestão de Vagas permite que uma empresa crie, edite ou exclua vagas em um sistema. Inicialmente, um gateway direciona a ação escolhida. Se a empresa optar por criar uma vaga, ela preenche as informações necessárias e, caso a vaga exija um teste, deve enviar um arquivo em PDF antes da revisão e publicação. Se a opção for editar uma vaga, a empresa acessa a página de edição, escolhe os campos a modificar e visualiza as alterações antes de confirmar. Para excluir/finalizar uma vaga, basta acessar a página de edição, selecionar a opção de deletar e confirmar a remoção. O processo garante que todas as alterações passem por uma etapa de revisão antes da finalização._

![Modelo BPMN do Processo 2](https://github.com/ICEI-PUC-Minas-PMGES-TI/pmg-es-2025-1-ti2-3740100-worklink/blob/main/docs/images/processo2-GestãoDeVagas.png)


#### Detalhamento das atividades

**Preencher informações da vaga**

| **Campo**             | **Tipo**         | **Restrições**                                 | **Valor default** |
| ---                   | ---              | ---                                            | ---               |
| titulo                | Caixa de texto   |                                                |                   |
| descricao             | Área de texto    |                                                |                   |
| pretencaoSalarial     | Número           | Campo opcional                                 | R$0,00            |
| dataFinal             | Data             |                                                |                   |
| tipoContrato          | Seleção única    | Escolher entre: CLT, Estágio, PJ, etc          |                   |
| modalidade            | Seleção única    | Escolher entre: Presencial, Remoto ou Híbrido  |                   |
| beneficios            | Caixa de texto   |                                                |                   |

| **Comandos**    |  **Destino**                   | **Tipo**            |
| ---             | ---                            | ---                 |
| btn_seguir      | Próxima atividade              | default             |
| btn_cancelar    | Volta para página inicial      | cancel              |


**Enviar arquivo .pdf do teste**

| **Campo**            | **Tipo**          | **Restrições**                  | **Valor default** |
| ---                  | ---               | ---                             | ---               |
| teste                | Arquivo           | Arquivo enviado deve ser .pdf   |                   |
| checkbox_semTeste    | Seleção múltipla  |                                 |                   |

| **Comandos**    |  **Destino**                   | **Tipo**            |
| ---             | ---                            | ---                 |
| btn_seguir      | Próxima atividade              | default             |


**Revisar informações dadas e confirmar publicação da vaga**

| **Campo**             | **Tipo**         | **Restrições**       | **Valor default** |
| ---                   | ---              | ---                  | ---               |
| titulo                | Caixa de texto   | Campo Não Editável   |                   |
| descricao             | Área de texto    | Campo Não Editável   |                   |
| pretencaoSalarial     | Número           | Campo Não Editável   |                   |
| dataFinal             | Data             | Campo Não Editável   |                   |
| tipoContrato          | Seleção única    | Campo Não Editável   |                   |
| modalidade            | Seleção única    | Campo Não Editável   |                   |
| beneficios            | Caixa de texto   | Campo Não Editável   |                   |
| teste                 | Arquivo          | Campo Não Editável   |                   |

| **Comandos**       |  **Destino**                   | **Tipo**            |
| ---                | ---                            | ---                 |
| btn_seguir         | Fim do processo 2              | default             |
| btn_cancelar       | Volta para página inicial      | cancel              |


**Pesquisar Vaga**

| **Campo**        | **Tipo**         | **Restrições**                           | **Valor default** |
| ---              | ---              | ---                                      | ---               |
| cardVaga         | Caixa de texto   | Campo Não Editável                       |                   |
| btn_opcoes       | Seleção única    | Escolher entre: Editar ou Excluir vaga   |                   |

| **Comandos**    |  **Destino**                   | **Tipo**            |
| ---             | ---                            | ---                 |
| btn_editar      | Atividade editar vaga          | default             |
| btn_excluir     | Atividade excluir vaga         | default             |


**Excluir Vaga**

| **Campo**        | **Tipo**         | **Restrições**    | **Valor default** |
| ---              | ---              | ---               | ---               |
|                  |                  |                   |                   |

| **Comandos**   |  **Destino**        | **Tipo**            |
| ---            | ---                 | ---                 |
| btn_cancelar   | Página inicial      | cancel              |
| btn_deletar    | Fim do processo 2   | default             |


**Editar campos**

| **Campo**             | **Tipo**         | **Restrições**                                 | **Valor default** |
| ---                   | ---              | ---                                            | ---               |
| titulo                | Caixa de texto   |                                                |                   |
| descricao             | Área de texto    |                                                |                   |
| pretencaoSalarial     | Número           | Campo opcional                                 | R$0,00            |
| dataFinal             | Data             |                                                |                   |
| tipoContrato          | Seleção única    | Escolher entre: CLT, Estágio, PJ, etc          |                   |
| modalidade            | Seleção única    | Escolher entre: Presencial, Remoto ou Híbrido  |                   |
| beneficios            | Caixa de texto   |                                                |                   |
| teste                 | Arquivo          | Arquivo enviado deve ser .pdf                  |                   |

| **Comandos**    |  **Destino**                   | **Tipo**            |
| ---             | ---                            | ---                 |
| btn_seguir      | Próxima atividade              | default             |
| btn_cancelar    | Volta para página inicial      | cancel              |


**Revisar informações dadas e confirmar alterações**

| **Campo**             | **Tipo**         | **Restrições**       | **Valor default** |
| ---                   | ---              | ---                  | ---               |
| titulo                | Caixa de texto   | Campo Não Editável   |                   |
| descricao             | Área de texto    | Campo Não Editável   |                   |
| pretencaoSalarial     | Número           | Campo Não Editável   |                   |
| dataFinal             | Data             | Campo Não Editável   |                   |
| tipoContrato          | Seleção única    | Campo Não Editável   |                   |
| modalidade            | Seleção única    | Campo Não Editável   |                   |
| beneficios            | Caixa de texto   | Campo Não Editável   |                   |
| teste                 | Arquivo          | Campo Não Editável   |                   |

| **Comandos**    |  **Destino**                   | **Tipo**    |
| ---             | ---                            | ---         |
| btn_seguir      | Fim do processo 2              | default     |
| btn_cancelar    | Volta para página inicial      | cancel      |