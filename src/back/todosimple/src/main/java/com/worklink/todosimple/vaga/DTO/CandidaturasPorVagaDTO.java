package com.worklink.todosimple.vaga.DTO;

public class CandidaturasPorVagaDTO {
    private long totalCandidaturas;
    private long totalVagas;
    private double mediaCandidaturasPorVaga;

    public CandidaturasPorVagaDTO(long totalCandidaturas, long totalVagas) {
        this.totalCandidaturas = totalCandidaturas;
        this.totalVagas = totalVagas;
        this.mediaCandidaturasPorVaga = totalVagas > 0 ? (double) totalCandidaturas / totalVagas : 0.0;
    }

    public long getTotalCandidaturas() {
        return totalCandidaturas;
    }

    public long getTotalVagas() {
        return totalVagas;
    }

    public double getMediaCandidaturasPorVaga() {
        return mediaCandidaturasPorVaga;
    }

    // Se quiser, pode adicionar setters também, mas não é obrigatório
}

