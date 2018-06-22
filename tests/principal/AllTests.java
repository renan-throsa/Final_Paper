package principal;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import testes_Categoria.Alterar_Categoria_Test;
import testes_Categoria.Busca_Categoria_Test;
import testes_Categoria.Excluir_Categoria_Test;
import testes_Categoria.Incluir_Categoria_Test;
import testes_Cliente.Alterar_Cliente_Test;
import testes_Cliente.Busca_Cliente_Test;
import testes_Cliente.Excluir_Cliente_Test;
import testes_Cliente.Incluir_Cliente_Test;
import testes_Item.Buscar_Item_Test;
import testes_Pedido.Incluir_Pedido_Test;
import testes_Pedido.Pesquisar_Pedido_Test;
import testes_Produto.Alterar_Produto_Test;
import testes_Produto.Busca_Produto_Test;
import testes_Produto.Excluir_Produto_Test;
import testes_Produto.Incluir_Produto_Test;

@RunWith(Suite.class)
@SuiteClasses({ Incluir_Categoria_Test.class, Busca_Categoria_Test.class, Alterar_Categoria_Test.class, Excluir_Categoria_Test.class,
		Incluir_Produto_Test.class, Busca_Produto_Test.class, Alterar_Produto_Test.class, Excluir_Produto_Test.class, Incluir_Cliente_Test.class,
		Busca_Cliente_Test.class, Alterar_Cliente_Test.class, Excluir_Cliente_Test.class, Incluir_Pedido_Test.class, Pesquisar_Pedido_Test.class,
		Buscar_Item_Test.class })
public class AllTests {

}
