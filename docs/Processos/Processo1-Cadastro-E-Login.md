### 3.3.1 Processo 1 – FLUXO DE CADASTRO E AUTENTICAÇÃO DE USUÁRIOS

_O processo se inicia quando o usuário acessa a tela inicial e escolhe entre realizar login ou efetuar um novo cadastro. Ao optar pelo login, o usuário insere suas credenciais, que são verificadas pelo sistema. Em caso de erro, o sistema exibe uma mensagem de falha e retorna à tela de login. Se as credenciais estiverem corretas, o usuário é redirecionado conforme seu perfil (candidato ou empresa). Caso o usuário escolha se cadastrar, ele preenche um formulário com seus dados. O sistema valida essas informações e, se houver erro, solicita correções. Se estiverem corretas, a conta é criada e confirmada, encerrando o processo._

![Modelo BPMN do Processo 1](../images/Cadastro_Login_BPMN.pnga)

#### Detalhamento das atividades

##### Tela Inicial: Escolha entre Login ou Cadastro

| **Campo**       | **Tipo**         | **Restrições** | **Valor default** |
|-----------------|------------------|----------------|-------------------|
| Ação desejada   | Seleção única    | Login/Cadastro | -                 |

| **Comandos**   | **Destino**        | **Tipo**  |
|----------------|--------------------|-----------|
| Avançar        | Tela de Login ou Tela de Cadastro | Default |

##### Tela de Login

| **Campo** | **Tipo**       | **Restrições**         | **Valor default** |
|----------|----------------|------------------------|-------------------|
| E-mail   | Caixa de texto | Formato de e-mail      | -                 |
| Senha    | Caixa de texto | Mínimo de 8 caracteres | -                 |

| **Comandos**   | **Destino**               | **Tipo**  |
|---------------|---------------------------|-----------|
| Entrar        | Verificação de Credenciais | Default   |
| Voltar        | Tela Inicial               | Cancel    |

##### Verificação de Credenciais

| **Campo**        | **Tipo**       | **Restrições**   | **Valor default** |
|------------------|----------------|------------------|-------------------|
| Resultado        | Seleção única  | Sucesso/Erro     | -                 |

| **Comandos**       | **Destino**                         | **Tipo**  |
|--------------------|-------------------------------------|-----------|
| Acessar Sistema    | Página de Vagas ou Gerenciamento    | Default   |
| Corrigir dados     | Tela de Login                       | Cancel    |

##### Tela de Cadastro

| **Campo**      | **Tipo**       | **Restrições**           | **Valor default** |
|----------------|----------------|--------------------------|-------------------|
| Nome           | Caixa de texto | Obrigatório              | -                 |
| E-mail         | Caixa de texto | Formato de e-mail        | -                 |
| Senha          | Caixa de texto | Mínimo de 8 caracteres   | -                 |

| **Comandos**   | **Destino**           | **Tipo**  |
|----------------|-----------------------|-----------|
| Cadastrar      | Verificação de Dados  | Default   |
| Voltar         | Tela Inicial           | Cancel    |

##### Verificação de Dados do Cadastro

| **Campo**        | **Tipo**       | **Restrições**   | **Valor default** |
|------------------|----------------|------------------|-------------------|
| Resultado        | Seleção única  | Sucesso/Erro     | -                 |

| **Comandos**       | **Destino**            | **Tipo**  |
|--------------------|------------------------|-----------|
| Criar Conta        | Confirmação de Cadastro | Default  |
| Corrigir Formulário| Tela de Cadastro        | Cancel   |

##### Confirmação de Cadastro

| **Campo**       | **Tipo** | **Restrições** | **Valor default**                    |
|----------------|----------|----------------|--------------------------------------|
| Mensagem       | Texto    | -              | "Cadastro realizado com sucesso!"   |

| **Comandos**   | **Destino**       | **Tipo**  |
|----------------|-------------------|-----------|
| Finalizar      | Fim do processo   | Default   |
