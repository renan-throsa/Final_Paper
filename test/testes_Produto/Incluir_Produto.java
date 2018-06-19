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

public class Incluir_Produto {

	@Test
	public void deveIncluirProdutoComSucesso()
			throws SQLFeatureNotSupportedException, ClassNotFoundException, SQLException, DAOException {
		Produto p = new Produto(1, "Produto um", 1.99, 1);

		ProdutoDAO dao = new ProdutoDAO();
		dao.incluir(p);
		Produto inserida = dao.pesquisar(1);
		assertEquals("Produto um", inserida.getDescricao());
	}

	@Test
	public void deveReotrnarErroPorCausaDaCategoria()
			throws SQLFeatureNotSupportedException, ClassNotFoundException, SQLException, DAOException {
		Produto p = new Produto(2, "Produto dois", 1.99, -1);

		ProdutoDAO dao = new ProdutoDAO();
		try {
			dao.incluir(p);
			fail();
		} catch (MySQLIntegrityConstraintViolationException expected) {
			assertEquals(MySQLIntegrityConstraintViolationException.class, expected.getClass());
		}
	}

}
