package testes_Cliente;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;

import org.junit.Test;

import aspects.Exceptions.DAOException;
import persistence.ClienteDAO;
import transference.Cliente;

public class Alterar_Cliente {

	@Test
	public void deveAlterarCategoria()
			throws SQLFeatureNotSupportedException, ClassNotFoundException, SQLException, DAOException {
		
		ClienteDAO dao = new ClienteDAO();
		Cliente c = dao.pesquisar(1);
		c.setNome("Nome alterado");
		dao.alterar(c);
		Cliente alterada = dao.pesquisar(1);
		assertEquals("Nome alterado",alterada.getNome());
	}

}
