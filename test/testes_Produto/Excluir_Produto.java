package testes_Produto;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;

import org.junit.Test;

import aspects.Exceptions.DAOException;
import persistence.ProdutoDAO;
import transference.Produto;

public class Excluir_Produto {
	
	@Test
	public void deveExcluirCategoria() throws ClassNotFoundException, SQLException, DAOException {
		ProdutoDAO dao = new ProdutoDAO();
		Produto p = dao.pesquisar(1);
		assertNotNull(p);
		dao.excluir(p.getCodigo());
		Produto excluida = dao.pesquisar(1);
		assertNull(excluida);

	}
}
