package connection;
import static javax.swing.JOptionPane.*;

public class TesteConexao {
  public static void main(String[] args) {
    try{
      ConexaoComercio c = new ConexaoComercio();
      showMessageDialog(null,"Conexão aberta!");
      c.fechar();
      showMessageDialog(null,"Conexão fechada!");
    }
    catch(Exception ex) {
      showMessageDialog(null,ex.getMessage(),"Erro",ERROR_MESSAGE);
    }
    System.exit(0);
  }
}
