package testes_Item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.SQLException;

import org.junit.Test;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import aspects.Exceptions.DAOException;
import persistence.ItemDAO;
import transference.Item;

public class Incluir_Item {

	@Test
	public void deveIncluirItemComSucesso() throws ClassNotFoundException, SQLException, DAOException {

		Item item = new Item(1, 1, 1, 1);
		ItemDAO dao = new ItemDAO();
		dao.incluir(item);

		Item incluido = dao.pesquisar(1);
		assertNotNull(incluido);
	}

	@Test
	public void naoDeveIncluirItemComSucesso() throws ClassNotFoundException, SQLException, DAOException {

		Item item = new Item(-1, 1, 1, 1);
		ItemDAO dao = new ItemDAO();

		try {
			dao.incluir(item);
			fail();
		} catch (MySQLIntegrityConstraintViolationException expected) {
			assertEquals(MySQLIntegrityConstraintViolationException.class, expected.getClass());
		}

	}
}
