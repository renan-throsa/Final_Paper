package b_testes_Produto;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

import java.sql.SQLException;

import org.junit.Test;

import aspects.Exceptions.DAOException;
import persistence.ProdutoDAO;
import transference.Produto;

public class D_Excluir_Produto_Test {
	
	@Test
	public void deveExcluirCategoria() throws ClassNotFoundException, SQLException, DAOException {
		
		Produto p = new ProdutoDAO().pesquisar(1);
		assertNotNull(p);
		new ProdutoDAO().excluir(p.getCodigo());
		Produto excluida = new ProdutoDAO().pesquisar(1);
		assertNull(excluida);

	}
}
