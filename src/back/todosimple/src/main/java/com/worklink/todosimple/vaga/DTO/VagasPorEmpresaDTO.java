package com.worklink.todosimple.vaga.DTO;

public class VagasPorEmpresaDTO {
    private String nomeEmpresa;
    private Long quantidadeVagas;

    public VagasPorEmpresaDTO(String nomeEmpresa, Long quantidadeVagas) {
        this.nomeEmpresa = nomeEmpresa;
        this.quantidadeVagas = quantidadeVagas;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public Long getQuantidadeVagas() {
        return quantidadeVagas;
    }

    public void setQuantidadeVagas(Long quantidadeVagas) {
        this.quantidadeVagas = quantidadeVagas;
    }
}
