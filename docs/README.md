# WorkLink

**Arthur Fonseca Delmiro de Jesus**

**Gabriel Máximo de Castro, email do aluno 1**

**Gustavo Lima Dias, gudias0503@gmail.com**

**Ítalo Vinhas Antunes Silva, italovinhas@icloud.com**

**Luís Henrique Fantini Almeida, luishfantini@gmail.com**

**Pedro Henrique Santos Vieira, pedrohsantos355@gmail.com**

**Rafael de Oliveira Caldeira Lopes, rafaeldeoliveiracl@gmail.com**


---

Professores:

**Michelle Hanne Soares de Andrade**

**Joana Gabriela Ribeiro de Souza**

**Danilo de Quadros Maia Filho**

---

_Curso de Engenharia de Software_

_Instituto de Informática e Ciências Exatas – Pontifícia Universidade Católica de Minas Gerais (PUC MINAS), Belo Horizonte – MG – Brasil_

---

_**Resumo**. O WorkLink é uma plataforma web de recrutamento que conecta empresas e candidatos de forma eficiente. Com o avanço da digitalização no mercado de trabalho, torna-se essencial um sistema que agilize o processo seletivo e facilite o acesso às oportunidades. O objetivo do projeto é permitir que empresas cadastrem vagas e que profissionais encontrem e se candidatem a essas oportunidades de maneira simplificada. A plataforma busca otimizar o recrutamento, reduzindo burocracias e tornando o processo mais acessível. Como resultado, espera-se aumentar a eficiência na seleção de talentos e melhorar a experiência tanto para empregadores quanto para candidatos._

---


## 1. Introdução

_O projeto consiste em uma plataforma web voltada para a conexão entre empresas e candidatos que buscam oportunidades de emprego. A aplicação permitirá que empresas se cadastrem, publiquem vagas e gerenciem candidaturas de forma simples e organizada. Por outro lado, candidatos poderão se registrar, visualizar vagas compatíveis com seus perfis e se candidatar enviando seus currículos diretamente pelo site._

### 1.1 Contextualização

_Nos últimos anos, a digitalização dos processos seletivos tem transformado a forma como as empresas recrutam talentos. Uma parcela significativa das contratações já ocorre por meio de plataformas online, refletindo a necessidade de soluções mais ágeis e acessíveis no mercado de trabalho [1.1]._

_O WorkLink surge nesse contexto como uma plataforma web voltada à conexão entre empresas e candidatos. O sistema permite que empresas cadastrem vagas, enquanto profissionais podem buscar oportunidades e se candidatar de maneira simplificada. Além disso, a plataforma exige que todas as empresas forneçam feedback aos candidatos não selecionados, proporcionando orientações valiosas para o aprimoramento de currículos e habilidades. Dessa forma, o sistema contribui para a otimização do recrutamento, tornando-o mais rápido, transparente e acessível para todos os envolvidos._

### 1.2 Problema

_A busca por vagas de emprego alinhadas ao perfil e às habilidades dos candidatos pode ser desafiadora. Os métodos tradicionais exigem a navegação por múltiplos sites, resultando em dificuldades como a ausência de filtros eficientes, a presença de vagas desatualizadas e a falta de retorno sobre as candidaturas. Além disso, a comunicação entre empresas e candidatos nem sempre ocorre de maneira organizada, o que pode prolongar o processo seletivo e dificultar a contratação._

### 1.3 Objetivo geral

_Desenvolvimento de um software para otimizar o processo de recrutamento e candidatura a vagas de emprego. A plataforma permitirá o cadastro de vagas por empresas, enquanto candidatos poderão visualizar as oportunidades disponíveis, enviar currículos e participar do processo seletivo de maneira organizada e digital._

#### 1.3.1 Objetivos específicos

- Possibilitar a publicação vagas de emprego;
- Possibilitar o cadastro de empresas e candidatos;
- Possibilitar gerenciamento de candidaturas;

### 1.4 Justificativas

_A digitalização dos processos seletivos tem impactado significativamente o recrutamento, tornando-o mais ágil e acessível. De acordo com o IBGE (2022), uma parcela crescente das contratações já ocorre por meio de plataformas online, evidenciando a necessidade de ferramentas que otimizem a conexão entre empresas e candidatos. No entanto, muitas plataformas existentes ainda apresentam desafios, como a falta de filtros eficientes, vagas desatualizadas e ausência de retorno sobre candidaturas, o que dificulta a experiência dos candidatos e prolonga o tempo de contratação [1.1]._

_Além disso, a falta de transparência no processo seletivo e a dificuldade na comunicação entre empresas e profissionais contribuem para um mercado de trabalho menos eficiente. A ausência de feedback para candidatos não selecionados também representa um obstáculo para o aprimoramento profissional, limitando as chances de recolocação no mercado. O projeto tem como objetivo otimizar esse processo, facilitando a conexão entre empresas e profissionais, reduzindo o tempo de contratação e aumentando a precisão na seleção de talentos. Dessa forma, contribui para a ampliação do acesso às oportunidades e para a eficiência do recrutamento. Diante desse cenário, torna-se essencial a adoção de soluções que tornem o recrutamento mais dinâmico, acessível e estruturado._

## 2. Participantes do processo

- Empresas Recrutadoras: _Publicam vagas, avaliam candidaturas e fornecem feedbacks construtivos_;
- Candidatos: _Se cadastram, buscam e se candidatam a vagas, e recebem feedback sobre seu desempenho_;
- Administradores da Plataforma: _Gerenciam o sistema e garantem a experiência do usuário_;
- Sistema de Recomendação: _Optimiza a correspondência entre vagas e candidatos, tornando o processo mais eficiente_:
- Feedback Automatizado: _Auxilia na melhoria do perfil do candidato com sugestões construtivas_;


## 3. Modelagem do processo de negócio

### 3.1. Análise da situação atual

_Atualmente, as plataformas de recrutamento como LinkedIn, Indeed e Glassdoor oferecem soluções para conectar empresas a candidatos. Essas plataformas possibilitam a criação de perfis profissionais, publicação de vagas e envio de candidaturas. No entanto, os sistemas existentes enfrentam desafios como falta de personalização nos processos seletivos, baixa taxa de feedback para candidatos, e dificuldades para organizar as candidaturas de forma eficiente. Além disso, a comunicação entre empresas e candidatos nem sempre é clara e objetiva, resultando em um processo de recrutamento muitas vezes demorado e ineficaz._

_Apesar da ampla adoção dessas plataformas, a falta de feedback contínuo e a dificuldade em encontrar uma correspondência precisa entre vagas e candidatos tornam o processo de recrutamento ineficiente, o que pode prejudicar a experiência tanto de empresas quanto de candidatos._

### 3.2. Descrição geral da proposta de solução

_A proposta do WorkLink é criar uma plataforma digital que não só simplifique e otimize o processo de recrutamento, mas também se preocupe com o desenvolvimento e feedback constante dos candidatos. O sistema será focado na qualidade do processo seletivo, oferecendo feedbacks construtivos e organizados para os candidatos que não forem selecionados. Além disso, o WorkLink proporcionará uma experiência mais humanizada e acessível, tanto para empresas quanto para candidatos, tornando o recrutamento mais assertivo e alinhado ao perfil dos profissionais_.

A proposta é baseada nos seguintes princípios:

- Feedback contínuo e educativo: Garantir que os candidatos sempre recebam retorno sobre o seu desempenho nas candidaturas, contribuindo para o seu desenvolvimento.
- Foco na qualidade, não na quantidade: Empresas poderão gerenciar suas vagas de forma eficiente, priorizando candidatos alinhados ao perfil desejado, em vez de uma grande quantidade de candidaturas.
- Plataforma humanizada e acessível: A interface será simples, intuitiva e voltada para a inclusão, permitindo fácil acesso às oportunidades de emprego para todos os perfis de candidatos.
  
### 3.3. Modelagem dos processos

[PROCESSO 1 - Cadastro de Vagas](Processos/Cadastro_de_Vagas.md "Detalhamento do Processo 1.")

[PROCESSO 2 - Cadastro de Candidatos ou Empresas](Processos/Cadastro_de_Candidatos_ou_Empresas.md "Detalhamento do Processo 2.")

[PROCESSO 3 - Processo Seletivo](Processos/Processo_Seletivo.md "Detalhamento do Processo 3.")

[PROCESSO 4 - Gerenciamento das Aplicações](Processos/Gerenciamento_das_Aplicações.md "Detalhamento do Processo 4.")

[PROCESSO 5 - Comunicação e Notificações](Processos/Comunicação_e_Notificações.md "Detalhamento do Processo 4.")


## 4. Projeto da solução

_O documento a seguir apresenta o detalhamento do projeto da solução. São apresentadas duas seções que descrevem, respectivamente: modelo relacional e tecnologias._

[Projeto da solução](solution-design.md "Detalhamento do projeto da solução: modelo relacional e tecnologias.")


## 5. Indicadores de desempenho

_O documento a seguir apresenta os indicadores de desempenho dos processos._

[Indicadores de desempenho dos processos](performance-indicators.md)


## 6. Interface do sistema

_A sessão a seguir apresenta a descrição do produto de software desenvolvido._ 

[Documentação da interface do sistema](interface.md)

## 7. Conclusão

_Apresente aqui a conclusão do seu trabalho. Deve ser apresentada aqui uma discussão dos resultados obtidos no trabalho, local em que se verifica as observações pessoais de cada aluno. Essa seção poderá também apresentar sugestões de novas linhas de estudo._

# REFERÊNCIAS

_Como um projeto de software não requer revisão bibliográfica, a inclusão das referências não é obrigatória. No entanto, caso você deseje incluir referências relacionadas às tecnologias, padrões, ou metodologias que serão usadas no seu trabalho, relacione-as de acordo com a ABNT._

_Verifique no link abaixo como devem ser as referências no padrão ABNT:_

http://portal.pucminas.br/imagedb/documento/DOC_DSC_NOME_ARQUI20160217102425.pdf

**[1.1]** - _Instituto Brasileiro de Geografia e Estatística (IBGE). Pesquisa Nacional por Amostra de Domicílios Contínua - Trabalho e Rendimento, 2022._


# APÊNDICES


_Atualizar os links e adicionar novos links para que a estrutura do código esteja corretamente documentada._


## Apêndice A - Código fonte

[Código do front-end](../src/front) -- repositório do código do front-end

[Código do back-end](../src/back)  -- repositório do código do back-end


## Apêndice B - Apresentação final


[Slides da apresentação final](presentations/)


[Vídeo da apresentação final](video/)






