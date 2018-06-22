package testes_Cliente;

import static org.junit.Assert.assertNull;

import java.sql.SQLException;

import org.junit.Test;

import aspects.Exceptions.DAOException;
import persistence.ClienteDAO;
import transference.Cliente;

public class Excluir_Cliente_Test {

	@Test
	public void deveExcluirClienteComSucesso() throws ClassNotFoundException, SQLException, DAOException {

		new ClienteDAO().excluir(1);
		Cliente buscado = new ClienteDAO().pesquisar(1);
		assertNull(buscado);

	}
	
	

}
