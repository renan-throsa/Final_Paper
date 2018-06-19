package testes_Produto;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;

import org.junit.Test;

import aspects.Exceptions.DAOException;
import persistence.ProdutoDAO;
import transference.Produto;

public class Busca_Produto {

	@Test
	public void deveRetornarNaoNulo()
			throws SQLFeatureNotSupportedException, ClassNotFoundException, SQLException, DAOException {
		ProdutoDAO dao = new ProdutoDAO();
		Produto p = dao.pesquisar(1);
		assertNotNull(p);
	}

	@Test
	public void deveRetornarNulo()
			throws SQLFeatureNotSupportedException, ClassNotFoundException, SQLException, DAOException {
		ProdutoDAO dao = new ProdutoDAO();
		Produto p = dao.pesquisar(1);
		assertNull(p);

	}
}
