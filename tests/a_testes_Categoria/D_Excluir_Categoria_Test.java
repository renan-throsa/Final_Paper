package a_testes_Categoria;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;

import org.junit.Test;

import aspects.Exceptions.DAOException;
import persistence.CategoriaDAO;
import transference.Categoria;

public class D_Excluir_Categoria_Test {
	
	@Test
	public void deveExcluirCategoria() throws ClassNotFoundException, SQLException, DAOException {
		Categoria c = new CategoriaDAO().pesquisar(1);
		assertNotNull(c);
		new CategoriaDAO().excluir(c.getCodigo());
		Categoria excluida = new CategoriaDAO().pesquisar(1);
		assertNull(excluida);

	}
}
