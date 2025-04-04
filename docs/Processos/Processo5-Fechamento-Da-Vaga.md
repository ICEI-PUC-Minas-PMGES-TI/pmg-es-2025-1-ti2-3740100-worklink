### 3.3.5 Processo 5 – FECHAMENTO DA VAGA

_O diagrama BPMN representa o processo de fechamento de uma vaga por uma empresa recrutadora. Após a análise do desempenho do candidato, uma decisão é tomada para determinar se ele foi aprovado. Caso seja aprovado, a empresa envia uma mensagem informando a aprovação ao candidato. O processo então se encerra._

![Modelo BPMN do Processo 1](https://github.com/ICEI-PUC-Minas-PMGES-TI/pmg-es-2025-1-ti2-3740100-worklink/blob/main/docs/images/tis%20Diagrama%20fechamento%20de%20vaga.png)


#### Detalhamento das atividades

**Analisar o desempenho do candidato**

| **Campo**         | **Tipo**       | **Restrições**          | **Valor default** |
| Nome do candidato | Caixa de Texto | Somente caracteres      |                   |
| Nota da Avaliação | Número         | Somente números         |                   |
| Comentários       | Área de Texto  | Máximo de 500 caracteres|                   |
| Data da avaliação | Data           | Formato dd-mm-aaaa      |                   |

| **Comandos**         |  **Destino**                   | **Tipo** |
| Continuar            | Aprovado                       | Default  |


**Enviar mensagem para o candidato informando aprovação**

| **Campo**           | **Tipo**        | **Restrições**               | **Valor default**              |
| Nome do candidato   | Caixa de Texto  | Somente caracteres           | -                              |
| E-mail do candidato | Caixa de Texto  | Formato de e-mail válido     | -                              |
| Data de envio       | Data e Hora     | Formato dd-mm-aaaa, hh:mm:ss | -                              |
| Mensagem            | Área de Texto   | Máximo de 1000 caracteres    | "Parabéns! Você foi aprovado." |
                  |

| **Comandos**     |  **Destino**                        | **Tipo** |
| Enviar           | Fim                                 | Default  |
| Cancelar         | Analisar o desempenho do candidato  | Cancel   |

##### Analisar o desempenho do candidato

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
|-----------------|------------------|----------------|-------------------|
| Nome do candidato   | Caixa de Texto    | Somente caracteres | -        |
| Nota da Avaliação   |  Número   | Somente números  | -        | -
| Comentários       | Área de Texto  | Máximo de 500 caracteres| -                   |
| Data da avaliação | Data           | Formato dd-mm-aaaa      |  -                 |


| **Comandos**   | **Destino**        | **Tipo**  |
|----------------|--------------------|-----------|
| Continuar        | Aprovado | Default |

##### Enviar mensagem para o candidato informando aprovação

| **Campo** | **Tipo**       | **Restrições**         | **Valor default** |
|----------|----------------|------------------------|-------------------|
| Nome do candidato   | Caixa de Texto  | Somente caracteres           | -                              |
| E-mail do candidato | Caixa de Texto  | Formato de e-mail válido     | -                              |
| Data de envio       | Data e Hora     | Formato dd-mm-aaaa, hh:mm:ss | -                              |
| Mensagem            | Área de Texto   | Máximo de 1000 caracteres    | "Parabéns! Você foi aprovado." |

| **Comandos**   | **Destino**               | **Tipo**  |
|---------------|---------------------------|-----------|
| Enviar        | Fim | Default   |
| Cancelar        | Analisar o desempenho do candidato                | Cancel    |

