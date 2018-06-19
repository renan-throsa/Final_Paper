package testes_Pedido;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.fail;

import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;

import org.junit.Test;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import aspects.Exceptions.DAOException;
import persistence.PedidoDAO;
import transference.Item;
import transference.Pedido;

public class Incluir_Pedido {

	@Test
	public void deveIncluirPedidoComSucesso()
			throws SQLFeatureNotSupportedException, ClassNotFoundException, SQLException, DAOException, Exception {
		Pedido p = new Pedido();
		p.setIdCliente(1);
		p.setStatus('A');
		p.incluirItem(new Item(1, 1, 1, 1.99));
		PedidoDAO dao = new PedidoDAO();
		dao.incluir(p);

		Pedido incluido = dao.pesquisar(1);
		assertNotNull(incluido);
		assertEquals(1, incluido.getIdCliente());
	}

	@Test
	public void naoDeveIncluirPedidoComSucessoPorCausaDoCliente()
			throws SQLFeatureNotSupportedException, ClassNotFoundException, SQLException, DAOException, Exception {
		Pedido p = new Pedido();
		p.setIdCliente(-1);
		p.setStatus('A');
		p.incluirItem(new Item(1, 1, 1, 1.99));
		PedidoDAO dao = new PedidoDAO();

		try {
			dao.incluir(p);
			fail();
		} catch (MySQLIntegrityConstraintViolationException expected) {
			assertEquals(MySQLIntegrityConstraintViolationException.class, expected.getClass());
		}

	}

	@Test
	public void naoDeveIncluirPedidoComSucessoPorCausaDoItem()
			throws SQLFeatureNotSupportedException, ClassNotFoundException, SQLException, DAOException, Exception {
		Pedido p = new Pedido();
		p.setIdCliente(1);
		p.setStatus('A');
		p.incluirItem(new Item(-1, -1, -1, 1.99));
		PedidoDAO dao = new PedidoDAO();

		try {
			dao.incluir(p);
			fail();
		} catch (MySQLIntegrityConstraintViolationException expected) {
			assertEquals(MySQLIntegrityConstraintViolationException.class, expected.getClass());
		}

	}

}
//