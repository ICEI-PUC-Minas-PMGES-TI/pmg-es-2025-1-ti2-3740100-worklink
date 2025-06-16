## 5. Indicadores de desempenho

| **Indicador** | **Objetivos** | **Descrição** | **Fonte de dados** | **Fórmula de cálculo** |
| ---           | ---           | ---           | ---             | ---             |
| Criação de Vagas por Empresas | Avaliar a participação das empresas na plataforma      | Mede quantas vagas cada empresa está criando no sistema. Este indicador pode ser visualizado pelas próprias empresas e pela equipe WorkLink. | Tabelas: `empresa`, `vaga`   | número de vagas cadastradas / número de empresas                              |
| Avaliação por Empresa         | Medir o engajamento das empresas com as candidaturas   | Mede o percentual de candidaturas que tiveram o status atualizado pelas empresas. Este indicador é de uso interno da equipe WorkLink.        | Tabela: `aplicacao`          | (número de aplicações com status alterado / número total de aplicações) × 100 |
| Candidaturas por Vaga         | Medir o interesse dos candidatos nas vagas disponíveis | Indica o número total de candidaturas feitas por vaga publicada. Esse indicador é acessível pela empresa e também pela equipe WorkLink.      | Tabelas: `vaga`, `aplicacao` | número total de candidaturas / número total de vagas                          |
| Média de Vagas por Empresa    | Avaliar a carga de criação de vagas por empresa        | Mede quantas vagas, em média, cada empresa está criando. Esse indicador é de uso interno da equipe WorkLink.                                 | Tabelas: `empresa`, `vaga`   | número total de vagas / número total de empresas                              |


