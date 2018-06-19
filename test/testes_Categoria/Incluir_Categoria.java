package testes_Categoria;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;

import org.junit.Test;

import aspects.Exceptions.DAOException;
import persistence.CategoriaDAO;
import transference.Categoria;

public class Incluir_Categoria {

	@Test
	public void deveIncluirCategoriaComSucesso()
			throws SQLFeatureNotSupportedException, ClassNotFoundException, SQLException, DAOException {
		Categoria c = new Categoria();
		c.setDescricao("CategoriaUm");
		CategoriaDAO dao = new CategoriaDAO();
		dao.incluir(c);
		Categoria inserida = dao.pesquisar(1);
		assertEquals("CategoriaUm", inserida.getDescricao());
	}

	
}
