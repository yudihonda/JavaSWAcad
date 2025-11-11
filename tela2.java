package br.com.academia.view;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.border.TitledBorder;
import java.awt.Font;
import javax.swing.border.EtchedBorder;
import java.awt.Color;

public class tela2 extends JFrame {
	private JPanel contentPane;
	private JTextField txtNome;
	private JTextField txtCpf;
	private JTextField txtDataNascimento;
	private JTextField txtTelefone;
	private JTextField txtEmail;
	private JTable tableAlunos;
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					tela2 frame = new tela2();
					frame.setVisible(true);
					frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	public tela2() {
		setTitle("Gerenciamento de Alunos");
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Fecha só esta janela
		setBounds(100, 100, 700, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panelCadastro = new JPanel();
		panelCadastro.setBorder(new TitledBorder(new EtchedBorder(EtchedBorder.LOWERED, new Color(255, 255, 255), new Color(160, 160, 160)), "Dados do Aluno", TitledBorder.LEADING, TitledBorder.TOP, null, new Color(0, 0, 0)));
		panelCadastro.setBounds(10, 11, 664, 200);
		contentPane.add(panelCadastro);
		panelCadastro.setLayout(null);

		JLabel lblNome = new JLabel("Nome:");
		lblNome.setBounds(10, 30, 46, 14);
		panelCadastro.add(lblNome);

		txtNome = new JTextField();
		txtNome.setBounds(66, 27, 250, 20);
		panelCadastro.add(txtNome);
		txtNome.setColumns(10);

		JLabel lblCpf = new JLabel("CPF:");
		lblCpf.setBounds(330, 30, 46, 14);
		panelCadastro.add(lblCpf);

		txtCpf = new JTextField();
		txtCpf.setBounds(386, 27, 150, 20);
		panelCadastro.add(txtCpf);
		txtCpf.setColumns(10);

		JLabel lblDataNascimento = new JLabel("Data Nasc.:");
		lblDataNascimento.setBounds(10, 70, 70, 14);
		panelCadastro.add(lblDataNascimento);

		txtDataNascimento = new JTextField();
		txtDataNascimento.setBounds(86, 67, 100, 20);
		panelCadastro.add(txtDataNascimento);
		txtDataNascimento.setColumns(10);

		JLabel lblTelefone = new JLabel("Telefone:");
		lblTelefone.setBounds(200, 70, 60, 14);
		panelCadastro.add(lblTelefone);

		txtTelefone = new JTextField();
		txtTelefone.setBounds(270, 67, 120, 20);
		panelCadastro.add(txtTelefone);
		txtTelefone.setColumns(10);

		JLabel lblEmail = new JLabel("Email:");
		lblEmail.setBounds(10, 110, 46, 14);
		panelCadastro.add(lblEmail);

		txtEmail = new JTextField();
		txtEmail.setBounds(66, 107, 250, 20);
		panelCadastro.add(txtEmail);
		txtEmail.setColumns(10);

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
				tableAlunos.setModel(new DefaultTableModel(
					new Object[][] {},
					new String[] {
						"ID", "Nome", "CPF", "Data Nasc.", "Telefone", "Email"
					}
				) {
					@Override
					public boolean isCellEditable(int row, int column) {
						return false;
					}
				});
				scrollPane.setViewportView(tableAlunos);
			}
		}