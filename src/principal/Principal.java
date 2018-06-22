package principal;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import presentation.JFPrincipal;

public class Principal {
  
	public static void main(String[] args) throws UnsupportedLookAndFeelException {
   
      UIManager.setLookAndFeel(
          new javax.swing.plaf.nimbus.NimbusLookAndFeel());
   
    new JFPrincipal().setVisible(true);
  }
}
