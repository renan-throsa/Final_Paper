package testes_Cliente;

import static org.junit.Assert.assertNull;

import java.sql.SQLException;

import org.junit.Test;

import aspects.Exceptions.DAOException;
import persistence.ClienteDAO;
import transference.Cliente;

public class Excluir_Cliente {

	@Test
	public void deveExcluirCliente() throws ClassNotFoundException, SQLException, DAOException {

		ClienteDAO dao = new ClienteDAO();
		dao.excluir(1);
		Cliente buscado = dao.pesquisar(1);
		assertNull(buscado);

	}

}
