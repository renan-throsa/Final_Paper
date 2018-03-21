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

/********************************************************************
 * Este arquivo é parte integrante do livro identificado abaixo e é *
 * protegido pela legislação que trata dos direitos autorais.       *
 *                                                                  *
 * Título:  Programação de Computadores em Java                     *
 * Autor:   Rui Rossi dos Santos                                    *
 * Editora: NovaTerra Editora e Distribuidora Ltda.                 *
 * Ano:     2014                                                    *
 ********************************************************************/