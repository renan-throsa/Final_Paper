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

public class Alterar_Produto {

	@Test
	public void deveAlterarCategoria()
			throws SQLFeatureNotSupportedException, ClassNotFoundException, SQLException, DAOException {

		ProdutoDAO dao = new ProdutoDAO();
		Produto c = dao.pesquisar(1);
		c.setDescricao("ProdutoAlterado");
		dao.alterar(c);
		Produto alterada = dao.pesquisar(1);
		assertEquals("ProdutoAlterado", alterada.getDescricao());
	}

	@Test
	public void deveReotrnarErroPorCausaDaCategoria()
			throws SQLFeatureNotSupportedException, ClassNotFoundException, SQLException, DAOException {
		ProdutoDAO dao = new ProdutoDAO();
		Produto p = dao.pesquisar(1);
		p.setDescricao("ProdutoAlterado");
		p.setIdCategoria(-1);

		try {
			dao.alterar(p);
			fail();
		} catch (MySQLIntegrityConstraintViolationException expected) {
			assertEquals(MySQLIntegrityConstraintViolationException.class, expected.getClass());
		}
	}

}
