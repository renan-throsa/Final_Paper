package a_testes_Categoria;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;

import org.junit.Test;

import aspects.Exceptions.DAOException;
import persistence.CategoriaDAO;
import transference.Categoria;

public class A_Incluir_Categoria_Test {

	@Test
	public void deveIncluirCategoriaComSucesso()
			throws SQLFeatureNotSupportedException, ClassNotFoundException, SQLException, DAOException {
		Categoria c = new Categoria();
		c.setDescricao("CategoriaUm");
		new CategoriaDAO().incluir(c);
		Categoria inserida = new CategoriaDAO().pesquisar(1);
		assertEquals("CategoriaUm", inserida.getDescricao());
	}

	@Test
	public void deveIncluirCategoriaPersistente()
			throws SQLFeatureNotSupportedException, ClassNotFoundException, SQLException, DAOException {
		Categoria c = new Categoria();
		c.setDescricao("CategoriaDois");
		new CategoriaDAO().incluir(c);
		Categoria inserida = new CategoriaDAO().pesquisar(2);
		assertEquals("CategoriaDois", inserida.getDescricao());
	}

}
