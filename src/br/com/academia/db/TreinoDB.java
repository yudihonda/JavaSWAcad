package br.com.academia.db;

import br.com.academia.model.Treino;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TreinoDB {

    public void salvar(Treino t) throws SQLException {
        String sql = "INSERT INTO treinos(aluno_id, tipo_treino, descricao, duracao_minutos, data_inicio) VALUES(?,?,?,?,?)";
        try (Connection conn = Cone.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setInt(1, t.getAlunoId());
            ps.setString(2, t.getTipoTreino());
            ps.setString(3, t.getDescricao());
            ps.setInt(4, t.getDuracaoMinutos());
            ps.setString(5, t.getDataInicio() != null ? t.getDataInicio().toString() : null);
            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM treinos WHERE id = ?";
        try (Connection conn = Cone.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<Treino> listarPorAluno(int alunoId) throws SQLException {
        List<Treino> lista = new ArrayList<>();
        String sql = "SELECT * FROM treinos WHERE aluno_id = ?";
        try (Connection conn = Cone.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, alunoId);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Treino t = new Treino();
                    t.setId(rs.getInt("id"));
                    t.setAlunoId(rs.getInt("aluno_id"));
                    t.setTipoTreino(rs.getString("tipo_treino"));
                    t.setDescricao(rs.getString("descricao"));
                    t.setDuracaoMinutos(rs.getInt("duracao_minutos"));
                    String d = rs.getString("data_inicio");
                    if (d != null) t.setDataInicio(LocalDate.parse(d));
                    lista.add(t);
                }
            }
        }
        return lista;
    }
}
