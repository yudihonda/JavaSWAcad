
Sistema de Cadastro de Alunos e Treinos

Aplicação Java integrada ao MySQL para gerenciamento de alunos e treinos. O sistema permite cadastrar, consultar, editar e excluir alunos e treinos. Inclui telas gráficas para operação do sistema.

Funcionalidades:
Cadastro de alunos <br>
Cadastro de treinos vinculados a alunos <br>
Consulta, edição e exclusão de registros <br>
Integração com MySQL usando JDBC <br>

Tecnologias utilizadas:
Java (JDK 8 ou superior) <br>
MySQL <br>
JDBC (MySQL Connector/J) <br>
IDE de preferência (IntelliJ, Eclipse, NetBeans, VS Code etc.) <br>

Instalação e configuração:

Instalar Java (JDK) <br>
Baixe e instale o JDK. Verifique a instalação executando no terminal: <br>
java -version <br>

Instalar MySQL e criar o banco de dados <br>
Baixe e instale o MySQL Server e o MySQL Workbench. <br>
Abra o software de SQL e execute o código tabelaSQL.txt <br>

Baixar o driver JDBC <br>
Baixe o MySQL Connector/J no site oficial da Oracle ou MySQL. <br>
Adicione o arquivo .jar ao projeto na sua IDE, incluindo no classpath. <br>

Abrir o projeto Java
Baixe ou clone o projeto e abra-o em sua IDE favorita. <br>
Certifique-se de que o driver JDBC foi adicionado. <br>
Abra os arquivos principais, incluindo Tela2. <br>

Configurar a conexão com o banco de dados
No arquivo responsável pela conexão, ajuste as configurações conforme o seu ambiente: <br>

String url = "jdbc:mysql://localhost:3306/SEU_BANCO"; <br>
String usuario = "root"; <br>
String senha = "SUA_SENHA"; <br>

Executar o programa através da Tela2 <br>
Localize o arquivo Tela2.java. <br>
Clique em executar na IDE. <br>
A interface gráfica do sistema será aberta. <br>

Uso do sistema: <br>
Na tela principal Tela2, escolha entre cadastrar aluno, consultar aluno, cadastrar treino ou consultar treino. <br>
Preencha os dados solicitados. <br>
Os dados serão gravados automaticamente no banco de dados MySQL. <br>

Estrutura do projeto: <br>
/src <br>
/model <br>
Aluno.java <br>
Treino.java <br>
Conexao.java <br>
/db <br>
AlunoDB.java <br>
TreinoDB.java <br>
/view <br>
Tela2.java <br>
tela1.java <br>
README.md <br>
tabelaSQL.sql <br>

Suporte:
Posso gerar versões alternativas do README ou adaptá-lo conforme necessário. Basta pedir.
