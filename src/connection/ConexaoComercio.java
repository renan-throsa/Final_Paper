package connection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexaoComercio {
	private static Connection conexao;
	private String url = "jdbc:mysql://localhost/comercio"; 
	private String usuario = "root";
	private String senha = "lapeslab";

	public ConexaoComercio() throws ClassNotFoundException, SQLException {

		if (conexao != null && !conexao.isClosed())
			return;
		Class.forName("com.mysql.jdbc.Driver");
		conexao = DriverManager.getConnection(url, usuario, senha);
		conexao.setAutoCommit(false);
		conexao.setTransactionIsolation(Connection.TRANSACTION_READ_COMMITTED);

	}

	public Connection getConexao() {
		return conexao;

	}

	public void fechar() throws SQLException {

		if (conexao == null || conexao.isClosed())
			return;
		conexao.close();

	}

	public void confirmarTransacao() throws SQLException {

		if (conexao == null || conexao.isClosed())
			return;
		conexao.commit();

	}

	public void cancelarTransacao() throws SQLException {

		if (conexao == null || conexao.isClosed())
			return;
		conexao.rollback();

	}
}
