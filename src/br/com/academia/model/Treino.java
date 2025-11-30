package br.com.academia.model;
import java.time.LocalDate;
public class Treino {
    private Integer id;
    private Integer alunoId;
    private String tipoTreino;
    private String descricao;
    private Integer duracaoMinutos;
    private LocalDate dataInicio;
    public Treino() {}
    public Integer getId() { return id; }
    public void setId(Integer id) { this.id = id; }
    public Integer getAlunoId() { return alunoId; }
    public void setAlunoId(Integer alunoId) { this.alunoId = alunoId; }
    public String getTipoTreino() { return tipoTreino; }
    public void setTipoTreino(String tipoTreino) { this.tipoTreino = tipoTreino; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public Integer getDuracaoMinutos() { return duracaoMinutos; }
    public void setDuracaoMinutos(Integer duracaoMinutos) { this.duracaoMinutos = duracaoMinutos; }
    public LocalDate getDataInicio() { return dataInicio; }
    public void setDataInicio(LocalDate dataInicio) { this.dataInicio = dataInicio; }
}
