
Sistema de Cadastro de Alunos e Treinos

Aplicação Java integrada ao MySQL para gerenciamento de alunos e treinos. O sistema permite cadastrar, consultar, editar e excluir alunos e treinos. Inclui telas gráficas para operação do sistema.

Funcionalidades:
Cadastro de alunos
Cadastro de treinos vinculados a alunos
Consulta, edição e exclusão de registros
Integração com MySQL usando JDBC

Tecnologias utilizadas:
Java (JDK 8 ou superior)
MySQL
JDBC (MySQL Connector/J)
IDE de preferência (IntelliJ, Eclipse, NetBeans, VS Code etc.)

Instalação e configuração:

Instalar Java (JDK)
Baixe e instale o JDK. Verifique a instalação executando no terminal:
java -version

Instalar MySQL e criar o banco de dados
Baixe e instale o MySQL Server e o MySQL Workbench.
Abra o software de SQL e execute o código tabelaSQL.txt

Baixar o driver JDBC
Baixe o MySQL Connector/J no site oficial da Oracle ou MySQL.
Adicione o arquivo .jar ao projeto na sua IDE, incluindo no classpath.

Abrir o projeto Java
Baixe ou clone o projeto e abra-o em sua IDE favorita.
Certifique-se de que o driver JDBC foi adicionado.
Abra os arquivos principais, incluindo Tela2.

Configurar a conexão com o banco de dados
No arquivo responsável pela conexão, ajuste as configurações conforme o seu ambiente:

String url = "jdbc:mysql://localhost:3306/SEU_BANCO";
String usuario = "root";
String senha = "SUA_SENHA";

Executar o programa através da Tela2
Localize o arquivo Tela2.java.
Clique em executar na IDE.
A interface gráfica do sistema será aberta.

Uso do sistema:
Na tela principal Tela2, escolha entre cadastrar aluno, consultar aluno, cadastrar treino ou consultar treino.
Preencha os dados solicitados.
Os dados serão gravados automaticamente no banco de dados MySQL.

Estrutura do projeto:
/src
/model
Aluno.java
Treino.java
Conexao.java
/db
AlunoDB.java
TreinoDB.java
/view
Tela2.java
tela1.java
README.md
tabelaSQL.sql

Suporte:
Posso gerar versões alternativas do README ou adaptá-lo conforme necessário. Basta pedir.
