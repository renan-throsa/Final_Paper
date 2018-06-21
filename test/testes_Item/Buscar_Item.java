package testes_Item;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;

import org.junit.Test;

import aspects.Exceptions.DAOException;
import persistence.ItemDAO;
import transference.Item;

public class Buscar_Item {

	@Test
	public void deveEncontrarItemComSucesso()
			throws SQLFeatureNotSupportedException, ClassNotFoundException, SQLException, DAOException, Exception {

		Item incluido = new ItemDAO().pesquisar(1);
		assertNotNull(incluido);
		assertEquals(1, incluido.getIdPedido());
	}

	@Test
	public void naoDeveEncontrarItem()
			throws SQLFeatureNotSupportedException, ClassNotFoundException, SQLException, DAOException, Exception {

		Item incluido = new ItemDAO().pesquisar(-11);
		assertNull(incluido);

	}
}
