package testes_Produto;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ Incluir_Produto.class, Busca_Produto.class,Alterar_Produto.class, Excluir_Produto.class, })
public class AllTests {

}
