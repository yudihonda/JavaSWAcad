package br.com.academia.view;

import br.com.academia.db.AlunoDB;
import br.com.academia.model.Aluno;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

public class tela2 extends JFrame {
    private JPanel contentPane;
    private JTextField txtNome;
    private JTextField txtCpf;
    private JTextField txtDataNascimento;
    private JTextField txtTelefone;
    private JTextField txtEmail;
    private JTable tableAlunos;

    private final AlunoDB alunoDB = new AlunoDB();
    private final DefaultTableModel model;

    private static final DateTimeFormatter fmtBR = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    private static final DateTimeFormatter fmtISO = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void main(String[] args) {
        EventQueue.invokeLater(() -> {
            try {
                tela2 frame = new tela2();
                frame.setVisible(true);
                frame.setLocationRelativeTo(null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    public tela2() {
        setTitle("Gerenciamento de Alunos");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 700, 600);
        contentPane = new JPanel();
        contentPane.setBorder(BorderFactory.createEmptyBorder(5,5,5,5));
        setContentPane(contentPane);
        contentPane.setLayout(null);

        JPanel panelCadastro = new JPanel();
        panelCadastro.setBorder(BorderFactory.createTitledBorder("Dados do Aluno"));
        panelCadastro.setBounds(10, 11, 664, 200);
        contentPane.add(panelCadastro);
        panelCadastro.setLayout(null);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(10, 30, 46, 14);
        panelCadastro.add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(66, 27, 250, 20);
        panelCadastro.add(txtNome);

        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBounds(330, 30, 46, 14);
        panelCadastro.add(lblCpf);

        txtCpf = new JTextField();
        txtCpf.setBounds(386, 27, 150, 20);
        panelCadastro.add(txtCpf);

        JLabel lblDataNascimento = new JLabel("Data Nasc.:");
        lblDataNascimento.setBounds(10, 70, 70, 14);
        panelCadastro.add(lblDataNascimento);

        txtDataNascimento = new JTextField();
        txtDataNascimento.setBounds(86, 67, 100, 20);
        panelCadastro.add(txtDataNascimento);

        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setBounds(200, 70, 60, 14);
        panelCadastro.add(lblTelefone);

        txtTelefone = new JTextField();
        txtTelefone.setBounds(270, 67, 120, 20);
        panelCadastro.add(txtTelefone);

        JLabel lblEmail = new JLabel("Email:");
        lblEmail.setBounds(10, 110, 46, 14);
        panelCadastro.add(lblEmail);

        txtEmail = new JTextField();
        txtEmail.setBounds(66, 107, 250, 20);
        panelCadastro.add(txtEmail);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnSalvar.setBounds(10, 150, 100, 30);
        panelCadastro.add(btnSalvar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnExcluir.setBounds(120, 150, 100, 30);
        panelCadastro.add(btnExcluir);

        JButton btnLimpar = new JButton("Limpar");
        btnLimpar.setFont(new Font("Tahoma", Font.PLAIN, 12));
        btnLimpar.setBounds(230, 150, 100, 30);
        panelCadastro.add(btnLimpar);

        JButton btnGerenciarTreinos = new JButton("Gerenciar Treinos");
        btnGerenciarTreinos.setFont(new Font("Tahoma", Font.BOLD, 12));
        btnGerenciarTreinos.setBounds(480, 150, 160, 30);
        panelCadastro.add(btnGerenciarTreinos);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setBounds(10, 222, 664, 328);
        contentPane.add(scrollPane);

        tableAlunos = new JTable();
        model = new DefaultTableModel(new Object[][] {}, new String[] {
                "ID", "Nome", "CPF", "Data Nasc.", "Telefone", "Email"
        }) {
            @Override
            public boolean isCellEditable(int row, int column) { return false; }
        };
        tableAlunos.setModel(model);
        scrollPane.setViewportView(tableAlunos);

        // Carregar dados iniciais
        carregarAlunos();

        // Quando selecionar linha -> preencher campos para editar
        tableAlunos.getSelectionModel().addListSelectionListener((ListSelectionEvent e) -> {
            if (!e.getValueIsAdjusting()) {
                int row = tableAlunos.getSelectedRow();
                if (row >= 0) {
                    txtNome.setText(valueAt(row,1));
                    txtCpf.setText(valueAt(row,2));
                    txtDataNascimento.setText(valueAt(row,3));
                    txtTelefone.setText(valueAt(row,4));
                    txtEmail.setText(valueAt(row,5));
                }
            }
        });

        btnSalvar.addActionListener(e -> {
            try {
                salvarAluno();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Erro ao salvar aluno: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                ex.printStackTrace();
            }
        });

        btnExcluir.addActionListener(e -> {
            int row = tableAlunos.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Selecione um aluno para excluir.", "Atenção", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int id = Integer.parseInt(valueAt(row,0));
            int op = JOptionPane.showConfirmDialog(this, "Confirma exclusão do aluno ID " + id + "?", "Confirma", JOptionPane.YES_NO_OPTION);
            if (op == JOptionPane.YES_OPTION) {
                try {
                    alunoDB.excluir(id);
                    carregarAlunos();
                    limparCampos();
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(this, "Erro ao excluir: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                }
            }
        });

        btnLimpar.addActionListener(e -> limparCampos());

         btnGerenciarTreinos.addActionListener(e -> {
            int row = tableAlunos.getSelectedRow();
            if (row < 0) {
                JOptionPane.showMessageDialog(this, "Selecione um aluno para gerenciar treinos.", "Atenção", JOptionPane.WARNING_MESSAGE);
                return;
            }
            int id = Integer.parseInt(valueAt(row,0));
            String nome = valueAt(row,1);
            SwingUtilities.invokeLater(() -> {
                Tela1 t1 = new Tela1(id, nome);
                t1.setLocationRelativeTo(this);
                t1.setVisible(true);
            });
        });
    }

    private String valueAt(int row, int col) {
        Object v = model.getValueAt(row, col);
        return v != null ? v.toString() : "";
    }

    private void salvarAluno() throws SQLException {
        String nome = txtNome.getText().trim();
        if (nome.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Nome é obrigatório.", "Atenção", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String cpf = txtCpf.getText().trim();
        String telefone = txtTelefone.getText().trim();
        String email = txtEmail.getText().trim();
        LocalDate dataNasc = parseDate(txtDataNascimento.getText().trim());

        Aluno a = new Aluno();
        a.setNome(nome);
        a.setCpf(cpf);
        a.setTelefone(telefone);
        a.setEmail(email);
        a.setDataNascimento(dataNasc);

        int row = tableAlunos.getSelectedRow();
        if (row >= 0) {
            String idStr = valueAt(row,0);
            if (!idStr.isEmpty()) a.setId(Integer.parseInt(idStr));
        }

        alunoDB.salvarOuAtualizar(a);
        carregarAlunos();
        limparCampos();
    }

    private void carregarAlunos() {
        try {
            List<Aluno> list = alunoDB.listarTodos();
            model.setRowCount(0);
            for (Aluno a : list) {
                String data = a.getDataNascimento() != null ? a.getDataNascimento().format(fmtBR) : "";
                model.addRow(new Object[] { a.getId(), a.getNome(), a.getCpf(), data, a.getTelefone(), a.getEmail() });
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Erro ao carregar alunos: " + ex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);
            ex.printStackTrace();
        }
    }

    private void limparCampos() {
        txtNome.setText("");
        txtCpf.setText("");
        txtDataNascimento.setText("");
        txtTelefone.setText("");
        txtEmail.setText("");
        tableAlunos.clearSelection();
    }

    private LocalDate parseDate(String s) {
        if (s == null || s.isBlank()) return null;
        try { return LocalDate.parse(s, fmtBR); } catch (DateTimeParseException e) {}
        try { return LocalDate.parse(s, fmtISO); } catch (DateTimeParseException e) {}
        try { return LocalDate.parse(s); } catch (DateTimeParseException e) {}
        JOptionPane.showMessageDialog(this, "Formato de data inválido. Use dd/MM/yyyy ou yyyy-MM-dd.", "Atenção", JOptionPane.WARNING_MESSAGE);
        return null;
    }
}
