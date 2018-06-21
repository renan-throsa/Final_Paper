package principal;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import testes_Categoria.Alterar_Categoria;
import testes_Categoria.Busca_Categoria;
import testes_Categoria.Excluir_Categoria;
import testes_Categoria.Incluir_Categoria;
import testes_Cliente.Alterar_Cliente;
import testes_Cliente.Busca_Cliente;
import testes_Cliente.Excluir_Cliente;
import testes_Cliente.Incluir_Cliente;
import testes_Item.Buscar_Item;
import testes_Pedido.Incluir_Pedido;
import testes_Pedido.Pesquisar_Pedido;
import testes_Produto.Alterar_Produto;
import testes_Produto.Busca_Produto;
import testes_Produto.Excluir_Produto;
import testes_Produto.Incluir_Produto;

@RunWith(Suite.class)
@SuiteClasses({ Incluir_Categoria.class, Busca_Categoria.class, Alterar_Categoria.class, Excluir_Categoria.class,
		Incluir_Produto.class, Busca_Produto.class, Alterar_Produto.class, Excluir_Produto.class, Incluir_Cliente.class,
		Busca_Cliente.class, Alterar_Cliente.class, Excluir_Cliente.class, Incluir_Pedido.class, Pesquisar_Pedido.class,
		Buscar_Item.class })
public class AllTests {

}
