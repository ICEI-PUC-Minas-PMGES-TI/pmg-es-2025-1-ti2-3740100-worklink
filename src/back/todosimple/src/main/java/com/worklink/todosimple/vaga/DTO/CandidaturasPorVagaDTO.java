package com.worklink.todosimple.vaga.DTO;

public class CandidaturasPorVagaDTO {
    private long totalVagas;
    private long totalCandidaturas;
    private double mediaCandidaturasPorVaga;

    public CandidaturasPorVagaDTO(long totalVagas, long totalCandidaturas) {
        this.totalVagas = totalVagas;
        this.totalCandidaturas = totalCandidaturas;
        this.mediaCandidaturasPorVaga = totalVagas > 0 ? (double) totalCandidaturas / totalVagas : 0.0;
    }

    public long getTotalVagas() {
        return totalVagas;
    }

    public long getTotalCandidaturas() {
        return totalCandidaturas;
    }

    public double getMediaCandidaturasPorVaga() {
        return mediaCandidaturasPorVaga;
    }

    // setters opcionais (se precisar)
    public void setTotalVagas(long totalVagas) {
        this.totalVagas = totalVagas;
    }

    public void setTotalCandidaturas(long totalCandidaturas) {
        this.totalCandidaturas = totalCandidaturas;
    }

    public void setMediaCandidaturasPorVaga(double mediaCandidaturasPorVaga) {
        this.mediaCandidaturasPorVaga = mediaCandidaturasPorVaga;
    }
}
