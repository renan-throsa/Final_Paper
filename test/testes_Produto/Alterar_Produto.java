package testes_Produto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;

import org.junit.Test;

import aspects.Exceptions.DAOException;
import persistence.ProdutoDAO;
import transference.Produto;

public class Alterar_Produto {

	@Test
	public void deveAlterarProduto()
			throws SQLFeatureNotSupportedException, ClassNotFoundException, SQLException, DAOException {

		Produto p = new ProdutoDAO().pesquisar(1);
		p.setDescricao("ProdutoAlterado");
		new ProdutoDAO().alterar(p);
		Produto alterada = new ProdutoDAO().pesquisar(1);
		assertEquals("ProdutoAlterado", alterada.getDescricao());
	}

	@Test
	public void deveReotrnarErroPorCausaDaCategoria()
			throws SQLFeatureNotSupportedException, ClassNotFoundException, SQLException, DAOException {

		Produto p = new ProdutoDAO().pesquisar(2);
		assertNotNull(p);
		p.setDescricao("ProdutoAlterado");
		p.setIdCategoria(-1);

		try {
			new ProdutoDAO().alterar(p);
			fail();
		} catch (DAOException expected) {
			assertEquals(DAOException.class, expected.getClass());
		}
	}

}
