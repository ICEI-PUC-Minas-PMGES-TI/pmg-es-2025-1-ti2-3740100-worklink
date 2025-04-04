### 3.3.2 Processo 2 – GESTÃO DE VAGAS

_O processo Gestão de Vagas permite que uma empresa crie, edite ou exclua vagas em um sistema. Inicialmente, um gateway direciona a ação escolhida. Se a empresa optar por criar uma vaga, ela preenche as informações necessárias e, caso a vaga exija um teste, deve enviar um arquivo em PDF antes da revisão e publicação. Se a opção for editar uma vaga, a empresa acessa a página de edição, escolhe os campos a modificar e visualiza as alterações antes de confirmar. Para excluir/finalizar uma vaga, basta acessar a página de edição, selecionar a opção de deletar e confirmar a remoção. O processo garante que todas as alterações passem por uma etapa de revisão antes da finalização._

![Modelo BPMN do Processo 2](https://github.com/ICEI-PUC-Minas-PMGES-TI/pmg-es-2025-1-ti2-3740100-worklink/blob/main/docs/images/processo2-GestãoDeVagas.png)


#### Detalhamento das atividades

**Preencher informações da vaga**

| **Campo**                     | **Tipo**         | **Restrições**                                 | **Valor default** |
| ---                           | ---              | ---                                            | ---               |
| Título da vaga                | Caixa de texto   |                                                |                   |
| Descrição da vaga             | Área de texto    |                                                |                   |
| Faixa salarial                | Número           | Campo opcional                                 | R$0,00            |
| Data final para inscrições    | Data             |                                                |                   |
| Tipo de contrato              | Seleção única    | Escolher entre: CLT, Estágio, PJ, etc          |                   |
| Modalidade                    | Seleção única    | Escolher entre: Presencial, Remoto ou Híbrido  |                   |
| Benefícios                    | Caixa de texto   |                                                |                   |

| **Comandos**   |  **Destino**                   | **Tipo**            |
| ---            | ---                            | ---                 |
| Seguir         | Próxima atividade              | default             |
| Cancelar       | Volta para página inicial      | cancel              |


**Enviar arquivo .pdf do teste**

| **Campo**                     | **Tipo**          | **Restrições**                  | **Valor default** |
| ---                           | ---               | ---                             | ---               |
| Upload de arquivo .pdf        | Arquivo           | Arquivo enviado deve ser .pdf   |                   |
| Vaga não tem teste            | Seleção múltipla  |                                 |                   |

| **Comandos**    |  **Destino**                   | **Tipo**            |
| ---             | ---                            | ---                 |
| Seguir          | Próxima atividade              | default             |


**Revisar informações dadas e confirmar publicação da vaga**

| **Campo**                     | **Tipo**         | **Restrições**       | **Valor default** |
| ---                           | ---              | ---                  | ---               |
| Título da vaga                | Caixa de texto   | Campo Não Editável   |                   |
| Descrição da vaga             | Área de texto    | Campo Não Editável   |                   |
| Faixa salarial                | Número           | Campo Não Editável   |                   |
| Data final para inscrições    | Data             | Campo Não Editável   |                   |
| Tipo de contrato              | Seleção única    | Campo Não Editável   |                   |
| Modalidade                    | Seleção única    | Campo Não Editável   |                   |
| Benefícios                    | Caixa de texto   | Campo Não Editável   |                   |
| Arquivo.pdf                   | Arquivo          | Campo Não Editável   |                   |

| **Comandos**   |  **Destino**                   | **Tipo**            |
| ---            | ---                            | ---                 |
| Seguir         | Fim do processo 2              | default             |
| Cancelar       | Volta para página inicial      | cancel              |


**Pesquisar Vaga**

| **Campo**        | **Tipo**         | **Restrições**                           | **Valor default** |
| ---              | ---              | ---                                      | ---               |
| Card da vaga     | Caixa de texto   | Campo Não Editável                       |                   |
| Btn Reticências  | Seleção única    | Escolher entre: Editar ou Excluir vaga   |                   |

| **Comandos**      |  **Destino**                   | **Tipo**            |
| ---               | ---                            | ---                 |
| Btn editar vaga   | Atividade editar vaga          | default             |
| Btn excluir vaga  | Atividade excluir vaga         | default             |


**Excluir Vaga**

| **Campo**        | **Tipo**         | **Restrições**                           | **Valor default** |
| ---              | ---              | ---                                      | ---               |
|                  |                  |                                          |                   |

| **Comandos**   |  **Destino**        | **Tipo**            |
| ---            | ---                 | ---                 |
| Btn cancelar   | Página inicial      | cancel              |
| Btn deletar    | Fim do processo 2   | default             |


**Editar campos**

| **Campo**                     | **Tipo**         | **Restrições**                                 | **Valor default** |
| ---                           | ---              | ---                                            | ---               |
| Título da vaga                | Caixa de texto   |                                                |                   |
| Descrição da vaga             | Área de texto    |                                                |                   |
| Faixa salarial                | Número           | Campo opcional                                 | R$0,00            |
| Data final para inscrições    | Data             |                                                |                   |
| Tipo de contrato              | Seleção única    | Escolher entre: CLT, Estágio, PJ, etc          |                   |
| Modalidade                    | Seleção única    | Escolher entre: Presencial, Remoto ou Híbrido  |                   |
| Benefícios                    | Caixa de texto   |                                                |                   |
| Arquivo.pdf                   | Arquivo          |                                                |                   |


| **Comandos**   |  **Destino**                   | **Tipo**            |
| ---            | ---                            | ---                 |
| Seguir         | Próxima atividade              | default             |
| Cancelar       | Volta para página inicial      | cancel              |


**Revisar informações dadas e confirmar alterações**

| **Campo**                     | **Tipo**         | **Restrições**       | **Valor default** |
| ---                           | ---              | ---                  | ---               |
| Título da vaga                | Caixa de texto   | Campo Não Editável   |                   |
| Descrição da vaga             | Área de texto    | Campo Não Editável   |                   |
| Faixa salarial                | Número           | Campo Não Editável   |                   |
| Data final para inscrições    | Data             | Campo Não Editável   |                   |
| Tipo de contrato              | Seleção única    | Campo Não Editável   |                   |
| Modalidade                    | Seleção única    | Campo Não Editável   |                   |
| Benefícios                    | Caixa de texto   | Campo Não Editável   |                   |
| Arquivo.pdf                   | Arquivo          | Campo Não Editável   |                   |

| **Comandos**   |  **Destino**                   | **Tipo**            |
| ---            | ---                            | ---                 |
| Seguir         | Fim do processo 2              | default             |
| Cancelar       | Volta para página inicial      | cancel              |