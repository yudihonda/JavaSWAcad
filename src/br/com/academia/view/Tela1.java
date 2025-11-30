package br.com.academia.view;

import br.com.academia.db.TreinoDB;
import br.com.academia.model.Treino;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;
import java.time.LocalDate;
import java.util.List;

public class Tela1 extends JFrame {
    private Integer alunoId;
    private JTextField txtTipoTreino, txtDescricao, txtDuracao, txtDataInicio;
    private JTable tableTreinos;
    private TreinoDB treinoDB = new TreinoDB();

    public Tela1(Integer alunoId, String nomeAluno) {
        this.alunoId = alunoId;

        setTitle("Treinos de " + nomeAluno);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setSize(650, 500);
        setLayout(null);

        JPanel panel = new JPanel();
        panel.setBorder(new TitledBorder("Dados do Treino"));
        panel.setBounds(10, 10, 610, 200);
        panel.setLayout(null);
        add(panel);

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setBounds(10, 30, 50, 20);
        panel.add(lblTipo);

        txtTipoTreino = new JTextField();
        txtTipoTreino.setBounds(70, 30, 200, 20);
        panel.add(txtTipoTreino);

        JLabel lblDesc = new JLabel("Descrição:");
        lblDesc.setBounds(10, 70, 80, 20);
        panel.add(lblDesc);

        txtDescricao = new JTextField();
        txtDescricao.setBounds(90, 70, 500, 20);
        panel.add(txtDescricao);

        JLabel lblDur = new JLabel("Duração (min):");
        lblDur.setBounds(10, 110, 100, 20);
        panel.add(lblDur);

        txtDuracao = new JTextField();
        txtDuracao.setBounds(120, 110, 80, 20);
        panel.add(txtDuracao);

        JLabel lblData = new JLabel("Data Início (AAAA-MM-DD):");
        lblData.setBounds(210, 110, 200, 20);
        panel.add(lblData);

        txtDataInicio = new JTextField();
        txtDataInicio.setBounds(400, 110, 100, 20);
        panel.add(txtDataInicio);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(10, 150, 100, 30);
        panel.add(btnSalvar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(120, 150, 100, 30);
        panel.add(btnExcluir);

        JScrollPane scroll = new JScrollPane();
        scroll.setBounds(10, 220, 610, 220);
        add(scroll);

        tableTreinos = new JTable();
        tableTreinos.setModel(new DefaultTableModel(
            new Object[][]{},
            new String[]{"ID", "Tipo", "Descrição", "Duração", "Data Início"}
        ));
        scroll.setViewportView(tableTreinos);

        carregarTreinos();

        btnSalvar.addActionListener(e -> salvarTreino());
        btnExcluir.addActionListener(e -> excluirTreino());
    }

    private void carregarTreinos() {
        try {
            List<Treino> treinos = treinoDB.listarPorAluno(alunoId);
            DefaultTableModel model = (DefaultTableModel) tableTreinos.getModel();
            model.setRowCount(0);
            for (Treino t : treinos) {
                model.addRow(new Object[]{
                    t.getId(), t.getTipoTreino(), t.getDescricao(),
                    t.getDuracaoMinutos(), t.getDataInicio()
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void salvarTreino() {
        try {
            Treino t = new Treino();
            t.setAlunoId(alunoId);
            t.setTipoTreino(txtTipoTreino.getText());
            t.setDescricao(txtDescricao.getText());
            t.setDuracaoMinutos(Integer.parseInt(txtDuracao.getText()));
            t.setDataInicio(LocalDate.parse(txtDataInicio.getText()));
            treinoDB.salvar(t);
            JOptionPane.showMessageDialog(this, "Treino salvo com sucesso!");
            carregarTreinos();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this, "Erro ao salvar treino: " + e.getMessage());
        }
    }

    private void excluirTreino() {
        int row = tableTreinos.getSelectedRow();
        if (row != -1) {
            Integer id = (Integer) tableTreinos.getValueAt(row, 0);
            try {
                treinoDB.excluir(id);
                JOptionPane.showMessageDialog(this, "Treino excluído com sucesso!");
                carregarTreinos();
            } catch (Exception e) {
                JOptionPane.showMessageDialog(this, "Erro ao excluir treino: " + e.getMessage());
            }
        } else {
            JOptionPane.showMessageDialog(this, "Selecione um treino para excluir.");
        }
    }
}
