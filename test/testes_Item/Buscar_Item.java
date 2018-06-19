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
	public void deveEncontrarPedidoComSucesso()
			throws SQLFeatureNotSupportedException, ClassNotFoundException, SQLException, DAOException, Exception {
		ItemDAO dao = new ItemDAO();
		Item incluido = dao.pesquisar(1);
		assertNotNull(incluido);
		assertEquals(1, incluido.getIdPedido());
	}

	@Test
	public void naoDeveEncontrarPedido()
			throws SQLFeatureNotSupportedException, ClassNotFoundException, SQLException, DAOException, Exception {
		ItemDAO dao = new ItemDAO();
		Item incluido = dao.pesquisar(1);
		assertNull(incluido);

	}
}
