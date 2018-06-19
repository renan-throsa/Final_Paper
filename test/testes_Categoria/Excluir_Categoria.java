package testes_Categoria;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;

import org.junit.Test;

import aspects.Exceptions.DAOException;
import persistence.CategoriaDAO;
import transference.Categoria;

public class Excluir_Categoria {
	
	@Test
	public void deveExcluirCategoria() throws ClassNotFoundException, SQLException, DAOException {
		CategoriaDAO dao = new CategoriaDAO();
		Categoria c = dao.pesquisar(1);
		assertNotNull(c);
		dao.excluir(c.getCodigo());
		Categoria excluida = dao.pesquisar(1);
		assertNull(excluida);

	}
}
