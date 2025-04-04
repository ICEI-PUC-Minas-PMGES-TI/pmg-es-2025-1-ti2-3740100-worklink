### 3.3.3 Processo 3 – PESQUISA E APLICAÇÃO DE VAGAS

_O processo inicia quando o candidato se inscreve em uma vaga. A empresa avalia sua candidatura e decide se ele está apto. Se não estiver, envia um feedback. Se estiver, pode agendar uma entrevista ou aprová-lo diretamente. O candidato recebe notificações conforme sua situação.Todas as decisões são registradas no sistema, encerrando o processo._

![Modelo BPMN do Processo 1](https://github.com/ICEI-PUC-Minas-PMGES-TI/pmg-es-2025-1-ti2-3740100-worklink/blob/main/docs/images/CRUD%20de%20vagas%20(OFICIAL)%20Diagrama%20(1).png?raw=true)


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

### **Atividade 1: Login no Sistema**

| **Campo**      | **Tipo**         | **Restrições**             | **Valor default** |
|---------------|----------------|---------------------------|-------------------|
| E-mail        | Caixa de Texto  | Formato de e-mail válido  |                   |
| Senha         | Caixa de Texto  | Mínimo de 8 caracteres    |                   |

#### **Comandos**

| **Nome do botão/link** | **Destino**        | **Tipo**   |
|------------------------|--------------------|------------|
| Entrar                | Área de Vagas       | default    |
| Esqueci a senha       | Recuperação de Senha |            |

---

### **Atividade 2: Pesquisa de Vagas**

| **Campo**            | **Tipo**         | **Restrições**                       | **Valor default** |
|----------------------|------------------|--------------------------------------|-------------------|
| Palavra-chave       | Caixa de Texto   | Mínimo de 3 caracteres               |                   |
| Localização         | Caixa de Texto   |                                      |                   |
| Modalidade          | Seleção única    | Remoto, Presencial, Híbrido          |                   |
| Área de atuação     | Seleção única    |                                      |                   |
| Faixa salarial      | Número           | Apenas valores positivos             |                   |
| Nível da vaga       | Seleção única    | Júnior, Pleno, Sênior                |                   |
| Data da publicação  | Data             |                                      |                   |

#### **Comandos**

| **Nome do botão/link** | **Destino**               | **Tipo**   |
|------------------------|---------------------------|------------|
| Buscar                | Listagem de Vagas          | default    |
| Limpar filtros        | Pesquisa de Vagas          | cancel     |
| Visualizar vaga       | Detalhes da Vaga           | default    |

---

### **Atividade 3: Aplicação na Vaga**

| **Campo**                | **Tipo**         | **Restrições**                       | **Valor default** |
|--------------------------|------------------|--------------------------------------|-------------------|
| Nome completo           | Caixa de Texto   | Obrigatório                          |                   |
| E-mail                  | Caixa de Texto   | Formato de e-mail                    |                   |
| Currículo               | Arquivo          | Formato PDF, máx 5MB                 |                   |
| Mensagem de apresentação | Área de Texto    | Até 1000 caracteres                  |                   |
| Data da aplicação       | Data e Hora      | Preenchido automaticamente           | Data atual        |
| Perfil do GitHub        | Link             | Deve começar com `https://`          |                   |
| Tecnologias dominadas   | Seleção múltipla |                                      |                   |

#### **Comandos**

| **Nome do botão/link** | **Destino**                | **Tipo**   |
|------------------------|----------------------------|------------|
| Aplicar                | Confirmação de Aplicação    | default    |
| Cancelar               | Fim do processo de Aplicação| cancel     |

---

### **Atividade 4: Edição ou Exclusão da Vaga (Empresa)**

| **Campo**            | **Tipo**         | **Restrições**                            | **Valor default** |
|----------------------|------------------|-------------------------------------------|-------------------|
| Vaga Selecionada    | Seleção única    | Apenas vagas cadastradas pela empresa    |                   |
| Novo Título         | Caixa de Texto   | Opcional                                  |                   |
| Nova Descrição      | Área de Texto    | Opcional                                  |                   |
| Nova Faixa Salarial | Número           | Apenas valores positivos                  |                   |

#### **Comandos**

| **Nome do botão/link** | **Destino**               | **Tipo**   |
|------------------------|---------------------------|------------|
| Alterar vaga          | Validação de Alterações    | default    |
| Excluir vaga          | Confirmação de Exclusão    | default    |
| Cancelar              | Área de Vagas              | cancel     |

---

### **Atividade 5: Confirmação de Exclusão de Vaga**

| **Campo**    | **Tipo**         | **Restrições** | **Valor default** |
|-------------|----------------|---------------|-------------------|
| Vaga        | Seleção única   | Apenas vagas cadastradas |   |

#### **Comandos**

| **Nome do botão/link** | **Destino**                     | **Tipo**   |
|------------------------|---------------------------------|------------|
| Confirmar exclusão    | Remover Vaga do Banco de Dados | default    |
| Cancelar              | Área de Vagas                  | cancel     |
