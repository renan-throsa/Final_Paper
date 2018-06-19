package testes_Cliente;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({ Incluir_Cliente.class, Busca_Cliente.class,Alterar_Cliente.class, Excluir_Cliente.class, })
public class AllTests {

}
