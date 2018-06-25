package a_testes_Categoria;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;

import org.junit.Test;

import aspects.Exceptions.DAOException;
import persistence.CategoriaDAO;
import transference.Categoria;

public class C_Alterar_Categoria_Test {

	@Test
	public void deveAlterarCategoria()
			throws SQLFeatureNotSupportedException, ClassNotFoundException, SQLException, DAOException {
		
		Categoria c = new CategoriaDAO().pesquisar(1);
		c.setDescricao("CategoriaAlterada");
		new CategoriaDAO().alterar(c);
		Categoria alterada = new CategoriaDAO().pesquisar(1);
		assertEquals("CategoriaAlterada",alterada.getDescricao());
	}

}
