public class Vaga {
    
    private String id;
    private String titulo;
    private String descricao;
    private String requisitos;
    private double salario;

    public Vaga(String id,String titulo, String descricrao, String requisitos, double salario){
            this.id = id;
            this.titulo = titulo;
            this.descricao = descricrao;
            this.requisitos = requisitos;
            this.salario = salario;
    }

    public String getId() {
        return id;
    }
    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }
    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescricao() {
        return descricao;
    }
    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getRequisitos() {
        return requisitos;
    }
    public void setRequisitos(String requisitos) {
        this.requisitos = requisitos;
    }
    
    public double getSalario() {
        return salario;
    }
    public void setSalario(double salario) {
        this.salario = salario;
    }

}
