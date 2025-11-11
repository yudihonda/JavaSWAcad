package br.com.academia.view;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import java.awt.Color;
import javax.swing.border.EtchedBorder;

public class Tela1 extends JFrame {

	private JPanel contentPane;
	private JTextField txtTipoTreino;
	private JTextField txtDuracao;
	private JTextField txtDataInicio;
	private JTable tableTreinos;
	private JTextArea txtDescricao;
	private JLabel lblAlunoNome; // Label para mostrar o nome do aluno


	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					// Exemplo de como iniciar (para teste)
					Tela1 frame = new Tela1(1, "Nome do Aluno Teste");
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public Tela1(int alunoId, String alunoNome) {


		setTitle("Gerenciamento de Treinos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 600, 550);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		lblAlunoNome = new JLabel("Treinos de: " + alunoNome);
		lblAlunoNome.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblAlunoNome.setBounds(10, 11, 564, 25);
		contentPane.add(lblAlunoNome);

		JPanel panelCadastro = new JPanel();
		panelCadastro.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Novo Treino", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelCadastro.setBounds(10, 47, 564, 200);
		contentPane.add(panelCadastro);
		panelCadastro.setLayout(null);

		JLabel lblTipoTreino = new JLabel("Tipo de Treino:");
		lblTipoTreino.setBounds(10, 30, 90, 14);
		panelCadastro.add(lblTipoTreino);

		txtTipoTreino = new JTextField();
		txtTipoTreino.setBounds(110, 27, 150, 20);
		panelCadastro.add(txtTipoTreino);
		txtTipoTreino.setColumns(10);

		JLabel lblDuracao = new JLabel("Duração (min):");
		lblDuracao.setBounds(280, 30, 90, 14);
		panelCadastro.add(lblDuracao);

		txtDuracao = new JTextField();
		txtDuracao.setBounds(380, 27, 80, 20);
		panelCadastro.add(txtDuracao);
		txtDuracao.setColumns(10);

		JLabel lblDataInicio = new JLabel("Data de Início:");
		lblDataInicio.setBounds(10, 70, 90, 14);
		panelCadastro.add(lblDataInicio);

		txtDataInicio = new JTextField();
		txtDataInicio.setBounds(110, 67, 100, 20);
		panelCadastro.add(txtDataInicio);
		txtDataInicio.setColumns(10);

		JLabel lblDescricao = new JLabel("Descrição:");
		lblDescricao.setBounds(10, 110, 70, 14);
		panelCadastro.add(lblDescricao);

		JScrollPane scrollDescricao = new JScrollPane();
		scrollDescricao.setBounds(110, 107, 350, 40);
		panelCadastro.add(scrollDescricao);
		
		txtDescricao = new JTextArea();
		scrollDescricao.setViewportView(txtDescricao);

		JButton btnSalvarTreino = new JButton("Salvar Treino");
		btnSalvarTreino.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnSalvarTreino.setBounds(10, 160, 130, 30);
		panelCadastro.add(btnSalvarTreino);

		JButton btnExcluirTreino = new JButton("Excluir Treino");
		btnExcluirTreino.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnExcluirTreino.setBounds(150, 160, 130, 30);
		panelCadastro.add(btnExcluirTreino);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 258, 564, 242);
		contentPane.add(scrollPane);

		tableTreinos = new JTable();
		tableTreinos.setModel(new DefaultTableModel(
			new Object[][] {},
			new String[] {
				"ID", "Tipo", "Duração (min)", "Data Início", "Descrição"
			}
		) {
			@Override
			public boolean isCellEditable(int row, int column) {
				return false;
			}
		});
		scrollPane.setViewportView(tableTreinos);
	}
}
