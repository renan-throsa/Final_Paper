package a_testes_Categoria;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;

import org.junit.Test;

import aspects.Exceptions.DAOException;
import persistence.CategoriaDAO;
import transference.Categoria;

public class B_Busca_Categoria_Test {

	@Test
	public void deveRetornarNaoNulo() throws SQLFeatureNotSupportedException, ClassNotFoundException, SQLException, DAOException {
		CategoriaDAO dao = new CategoriaDAO();
		Categoria c = dao.pesquisar(1);
		assertNotNull(c);
	}

	@Test
	public void deveRetornarNulo() throws SQLFeatureNotSupportedException, ClassNotFoundException, SQLException, DAOException {
		CategoriaDAO dao = new CategoriaDAO();
		Categoria c = dao.pesquisar(-1);
		assertNull(c);
	}
}
