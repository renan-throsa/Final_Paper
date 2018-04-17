package presentation;
import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;

@SuppressWarnings("serial")
public class DesktopImagem extends JDesktopPane {
  private ImageIcon iiImagem;
  
  public DesktopImagem(String imagem) {
    iiImagem = new ImageIcon(imagem);
  }
  
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    iiImagem.paintIcon(this, g, 0, 0);
  }
  
  public Dimension getPreferredSize() {
    return new Dimension(700,
        500);
  }
}