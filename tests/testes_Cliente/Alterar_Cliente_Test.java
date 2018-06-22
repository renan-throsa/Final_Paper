package testes_Cliente;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;

import org.junit.Test;

import aspects.Exceptions.DAOException;
import persistence.ClienteDAO;
import transference.Cliente;

public class Alterar_Cliente_Test {

	@Test
	public void deveAlterarCliente()
			throws SQLFeatureNotSupportedException, ClassNotFoundException, SQLException, DAOException {
		
		Cliente c = new ClienteDAO().pesquisar(1);
		c.setNome("Philip K. Dick");
		new ClienteDAO().alterar(c);
		Cliente alterada = new ClienteDAO().pesquisar(1);
		assertEquals("Philip K. Dick",alterada.getNome());
	}

}
