package br.com.academia.db;

import br.com.academia.model.Aluno;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AlunoDB {

    public void salvar(Aluno a) throws SQLException {
        String sql = "INSERT INTO alunos(nome, cpf, data_nascimento, telefone, email) VALUES(?,?,?,?,?)";
        try (Connection conn = Cone.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, a.getNome());
            ps.setString(2, a.getCpf());
            ps.setString(3, a.getDataNascimento() != null ? a.getDataNascimento().toString() : null);
            ps.setString(4, a.getTelefone());
            ps.setString(5, a.getEmail());
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) a.setId(rs.getInt(1));
            }
        }
    }

    public void atualizar(Aluno a) throws SQLException {
        String sql = "UPDATE alunos SET nome=?, cpf=?, data_nascimento=?, telefone=?, email=? WHERE id=?";
        try (Connection conn = Cone.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, a.getNome());
            ps.setString(2, a.getCpf());
            ps.setString(3, a.getDataNascimento() != null ? a.getDataNascimento().toString() : null);
            ps.setString(4, a.getTelefone());
            ps.setString(5, a.getEmail());
            ps.setInt(6, a.getId());
            ps.executeUpdate();
        }
    }

    public void excluir(int id) throws SQLException {
        String sql = "DELETE FROM alunos WHERE id = ?";
        try (Connection conn = Cone.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            ps.executeUpdate();
        }
    }

    public List<Aluno> listarTodos() throws SQLException {
        List<Aluno> lista = new ArrayList<>();
        String sql = "SELECT * FROM alunos";
        try (Connection conn = Cone.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {
                Aluno a = new Aluno();
                a.setId(rs.getInt("id"));
                a.setNome(rs.getString("nome"));
                a.setCpf(rs.getString("cpf"));
                String d = rs.getString("data_nascimento");
                if (d != null) a.setDataNascimento(LocalDate.parse(d));
                a.setTelefone(rs.getString("telefone"));
                a.setEmail(rs.getString("email"));
                lista.add(a);
            }
        }
        return lista;
    }

    public void salvarOuAtualizar(Aluno a) throws SQLException {
        if (a.getId() == null) {
            salvar(a);
        } else {
            atualizar(a);
        }
    }
}
