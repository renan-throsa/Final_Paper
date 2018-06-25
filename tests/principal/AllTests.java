package principal;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import a_testes_Categoria.A_Incluir_Categoria_Test;
import a_testes_Categoria.B_Busca_Categoria_Test;
import a_testes_Categoria.C_Alterar_Categoria_Test;
import a_testes_Categoria.D_Excluir_Categoria_Test;
import b_testes_Produto.A_Incluir_Produto_Test;
import b_testes_Produto.B_Busca_Produto_Test;
import b_testes_Produto.C_Alterar_Produto_Test;
import b_testes_Produto.D_Excluir_Produto_Test;
import c_testes_Cliente.A_Incluir_Cliente_Test;
import c_testes_Cliente.B_Busca_Cliente_Test;
import c_testes_Cliente.C_Alterar_Cliente_Test;
import c_testes_Cliente.D_Excluir_Cliente_Test;
import d_testes_Pedido.Incluir_Pedido_Test;
import d_testes_Pedido.Pesquisar_Pedido_Test;
import e_testes_Item.Buscar_Item_Test;

@RunWith(Suite.class)
@SuiteClasses({ A_Incluir_Categoria_Test.class, B_Busca_Categoria_Test.class, C_Alterar_Categoria_Test.class, D_Excluir_Categoria_Test.class,
		A_Incluir_Produto_Test.class, B_Busca_Produto_Test.class, C_Alterar_Produto_Test.class, D_Excluir_Produto_Test.class, A_Incluir_Cliente_Test.class,
		B_Busca_Cliente_Test.class, C_Alterar_Cliente_Test.class, D_Excluir_Cliente_Test.class, Incluir_Pedido_Test.class, Pesquisar_Pedido_Test.class,
		Buscar_Item_Test.class })
public class AllTests {

}
