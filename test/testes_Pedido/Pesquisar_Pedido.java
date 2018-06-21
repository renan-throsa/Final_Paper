package testes_Pedido;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;

import org.junit.Test;

import aspects.Exceptions.DAOException;
import persistence.PedidoDAO;
import transference.Pedido;

public class Pesquisar_Pedido {

	@Test
	public void deveEncontrarPedidoComSucesso()
			throws SQLFeatureNotSupportedException, ClassNotFoundException, SQLException, DAOException, Exception {
		Pedido incluido = new PedidoDAO().pesquisar(1);
		assertNotNull(incluido);
		assertEquals(2, incluido.getIdCliente());
	}

	@Test
	public void naoDeveEncontrarPedido()
			throws SQLFeatureNotSupportedException, ClassNotFoundException, SQLException, DAOException, Exception {

		Pedido incluido = new PedidoDAO().pesquisar(2);
		assertNull(incluido);

	}
}
