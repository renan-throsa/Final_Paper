package b_testes_Produto;

import static org.junit.Assert.assertNotNull;

import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;

import org.junit.Test;

import aspects.Exceptions.DAOException;
import persistence.ProdutoDAO;
import transference.Produto;

public class B_Busca_Produto_Test {

	@Test
	public void deveRetornarNaoNulo()
			throws SQLFeatureNotSupportedException, ClassNotFoundException, SQLException, DAOException {
		ProdutoDAO dao = new ProdutoDAO();
		Produto p = dao.pesquisar(1);
		assertNotNull(p);
	}
}
