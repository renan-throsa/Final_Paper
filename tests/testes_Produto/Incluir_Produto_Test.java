package testes_Produto;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;

import org.junit.Test;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import aspects.Exceptions.DAOException;
import persistence.ProdutoDAO;
import transference.Produto;

public class Incluir_Produto_Test {

	@Test
	public void deveIncluirProdutoComSucesso()
			throws SQLFeatureNotSupportedException, ClassNotFoundException, SQLException, DAOException {
		Produto p = new Produto(1, "ProdutoUm", 1.99, 2);

		new ProdutoDAO().incluir(p);
		Produto inserida = new ProdutoDAO().pesquisar(1);
		assertEquals("ProdutoUm", inserida.getDescricao());
	}

	@Test
	public void deveIncluirProdutoComPersistente()
			throws SQLFeatureNotSupportedException, ClassNotFoundException, SQLException, DAOException {
		Produto p = new Produto(2, "ProdutoDois", 2.99, 2);

		new ProdutoDAO().incluir(p);
		Produto inserida = new ProdutoDAO().pesquisar(2);
		assertEquals("ProdutoDois", inserida.getDescricao());
	}

	@Test
	public void deveReotrnarErroPorCausaDaCategoria()
			throws SQLFeatureNotSupportedException, ClassNotFoundException, SQLException, DAOException {
		Produto p = new Produto(2, "ProdutoTres", 1.99, -1);
		ProdutoDAO dao = new ProdutoDAO();

		try {
			dao.incluir(p);
			fail();
		} catch (DAOException expected) {
			assertEquals(DAOException.class, expected.getClass());
		}
	}

}
