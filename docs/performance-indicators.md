## 5. Indicadores de desempenho

| **Indicador** | **Objetivos** | **Descrição** | **Fonte de dados** | **Fórmula de cálculo** |
| ---           | ---           | ---           | ---             | ---             |
| Criação de Vagas por Empresas | Avaliar a participação das empresas na plataforma      | Mede quantas vagas cada empresa está criando no sistema                          | Tabelas: `empresa`, `vaga`   | número de vagas cadastradas / número de empresas                              |
| Avaliação por Empresa         | Medir o engajamento das empresas com as candidaturas   | Mede o percentual de candidaturas que tiveram o status atualizado pelas empresas | Tabela: `aplicacao`          | (número de aplicações com status alterado / número total de aplicações) × 100 |
| Candidatura por Vaga          | Medir o interesse dos candidatos nas vagas disponíveis | Indica a média de candidaturas feitas por vaga publicada                         | Tabelas: `vaga`, `aplicacao` | número total de candidaturas / número total de vagas                          |

