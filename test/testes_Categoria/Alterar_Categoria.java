package testes_Categoria;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;

import org.junit.Test;

import aspects.Exceptions.DAOException;
import persistence.CategoriaDAO;
import transference.Categoria;

public class Alterar_Categoria {

	@Test
	public void deveAlterarCategoria()
			throws SQLFeatureNotSupportedException, ClassNotFoundException, SQLException, DAOException {
		
		CategoriaDAO dao = new CategoriaDAO();
		Categoria c = dao.pesquisar(1);
		c.setDescricao("CategoriaAlterada");
		dao.alterar(c);
		Categoria alterada = dao.pesquisar(1);
		assertEquals("CategoriaAlterada",alterada.getDescricao());
	}

}
