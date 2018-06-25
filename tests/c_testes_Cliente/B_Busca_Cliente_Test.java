package c_testes_Cliente;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;

import org.junit.Test;

import aspects.Exceptions.DAOException;
import persistence.ClienteDAO;
import transference.Cliente;

public class B_Busca_Cliente_Test {

	@Test
	public void deveRetornarNaoNulo()
			throws SQLFeatureNotSupportedException, ClassNotFoundException, SQLException, DAOException {
		ClienteDAO dao = new ClienteDAO();
		Cliente c = dao.pesquisar(1);
		assertNotNull(c);
	}


}
