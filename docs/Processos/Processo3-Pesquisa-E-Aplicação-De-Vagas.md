### 3.3.3 Processo 3 – PESQUISA E APLICAÇÃO DE VAGAS

_O processo inicia quando o candidato se inscreve em uma vaga. A empresa avalia sua candidatura e decide se ele está apto. Se não estiver, envia um feedback. Se estiver, pode agendar uma entrevista ou aprová-lo diretamente. O candidato recebe notificações conforme sua situação.Todas as decisões são registradas no sistema, encerrando o processo._

![PESQUISA-E-APLICACAO-DE-VAGAS-Diagrama](https://github.com/user-attachments/assets/3b017e59-44ec-493e-8a0a-31cb4beba2f1)

#### Detalhamento das atividades

_Descreva aqui cada uma das propriedades das atividades do processo 3. 
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

### 1️ Acesso ao Sistema
O candidato acessa o sistema para visualizar e se candidatar a vagas de emprego.

| **Campo**        | **Tipo**       | **Restrições**             | **Valor default** |
|-----------------|---------------|----------------------------|-------------------|
| Email          | Caixa de texto | Formato de e-mail válido  | -                 |
| Senha          | Caixa de texto | Mínimo de 8 caracteres    | -                 |

| **Comandos**   | **Destino**         | **Tipo**    |
|--------------|-------------------|------------|
| Entrar       | Verificação de Cadastro | Default    |
| Esqueci a senha | Recuperação de Senha | Default    |

---

### 2️ Usuário Cadastrado?
Verifica se o usuário já tem uma conta.

| **Decisão**       | **Destino**          |
|------------------|--------------------|
| Sim             | Área de Vagas       |
| Não             | Fim do Processo     |

---

### 3️ Área de Vagas
O candidato acessa a área onde pode pesquisar oportunidades.

| **Campo**      | **Tipo**       | **Restrições** | **Valor default** |
|--------------|---------------|--------------|----------------|
| Barra de Pesquisa | Caixa de texto | Palavra-chave | - |
| Filtros        | Seleção múltipla | Localização, Salário, Tipo de Contrato | - |

| **Comandos**   | **Destino**      | **Tipo**   |
|--------------|-----------------|---------|
| Pesquisar    | Lista de Vagas  | Default |

---

### 4️ Pesquisar Vagas
O candidato realiza uma busca e visualiza os resultados.

| **Campo**      | **Tipo**       | **Restrições** | **Valor default** |
|--------------|---------------|--------------|----------------|
| Lista de Vagas | Tabela        | Dados de vagas disponíveis | - |

| **Comandos**       | **Destino**         | **Tipo**    |
|------------------|-------------------|------------|
| Selecionar Vaga | Visualizar Detalhes da Vaga | Default |

---

### 5️ Visualizar Detalhes da Vaga
O candidato acessa informações detalhadas sobre uma vaga.

| **Campo**          | **Tipo**  | **Restrições** | **Valor default** |
|------------------|---------|--------------|----------------|
| Nome da Empresa | Caixa de texto | - | - |
| Cargo          | Caixa de texto | - | - |
| Salário       | Número | Apenas números positivos | - |
| Descrição da Vaga | Área de texto | - | - |

| **Comandos**   | **Destino**          | **Tipo**    |
|--------------|-------------------|------------|
| Candidatar-se | Decisão: Candidatar a Vaga? | Default |

---

### 6️ Candidatar a Vaga?
O candidato decide se quer se candidatar à vaga.

| **Decisão**       | **Destino**          |
|------------------|--------------------|
| Sim             | Aplicar Vaga       |
| Não             | Fim do Processo    |

---

### 7️ Aplicar Vaga
O candidato finaliza sua candidatura na vaga desejada.

| **Campo**      | **Tipo**       | **Restrições** | **Valor default** |
|--------------|---------------|--------------|----------------|
| Currículo    | Arquivo       | PDF, máximo 5MB | - |
| Carta de Apresentação | Área de texto | - | - |

| **Comandos**   | **Destino**   | **Tipo**  |
|--------------|------------|---------|
| Enviar      | Fim do Processo | Default |
