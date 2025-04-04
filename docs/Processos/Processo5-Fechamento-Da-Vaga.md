### 3.3.5 Processo 5 – FECHAMENTO DA VAGA

_O diagrama BPMN representa o processo de fechamento de uma vaga por uma empresa recrutadora. Após a análise do desempenho do candidato, uma decisão é tomada para determinar se ele foi aprovado. Caso seja aprovado, a empresa envia uma mensagem informando a aprovação ao candidato. O processo então se encerra._

![Modelo BPMN do Processo 1](https://github.com/ICEI-PUC-Minas-PMGES-TI/pmg-es-2025-1-ti2-3740100-worklink/blob/main/docs/images/tis%20Diagrama%20fechamento%20de%20vaga.png)


#### Detalhamento das atividades

_Descreva aqui cada uma das propriedades das atividades do processo 5. 
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

**Nome da atividade 1**

| **Campo**         | **Tipo**       | **Restrições**          | **Valor default** |
| Nome do candidato | Caixa de Texto | Somente caracteres      | -                 |
| Nota da Avaliação | Número         | Somente números         |                   |
| Comentários       | Área de Texto  | Máximo de 500 caracteres|                   |
| Data da avaliação | Data           | Formato dd-mm-aaaa      |                   |

| **Comandos**         |  **Destino**                   | **Tipo** |
| Continuar            | Aprovado                       | Default  |


**Nome da atividade 2**

| **Campo**           | **Tipo**        | **Restrições**               | **Valor default**              |
| Nome do candidato   | Caixa de Texto  | Somente caracteres           | -                              |
| E-mail do candidato | Caixa de Texto  | Formato de e-mail válido     | -                              |
| Data de envio       | Data e Hora     | Formato dd-mm-aaaa, hh:mm:ss | -                              |
| Mensagem            | Área de Texto   | Máximo de 1000 caracteres    | "Parabéns! Você foi aprovado." |
                  |

| **Comandos**     |  **Destino**                        | **Tipo** |
| Enviar           | Fim                                 | Default  |
| Cancelar         | Analisar o desempenho do candidato  | Cancel   |

