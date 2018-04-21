package presentation;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JDesktopPane;
import javax.swing.JTable;

public aspect Auxiliary {

	/**
	 * I do not know why,but i get an error when this aspect is in another package.
	 * 
	 * @author renan
	 *
	 */
	public static class MouseHandler extends MouseAdapter {

		private IFCadastro _enclosing;

		public MouseHandler(IFCadastro cadastro) {
			this._enclosing = cadastro;
		}

		public void mouseReleased(MouseEvent e) {

			if (e.getButton() != MouseEvent.BUTTON1)
				return;
			JTable tb = (JTable) e.getSource();
			if (tb.getSelectionModel().isSelectionEmpty())
				return;
			int lin = tb.getSelectionModel().getMinSelectionIndex();
			String codigo = tb.getModel().getValueAt(lin, 0).toString();

			try {

				this._enclosing.carregarRegistro(codigo);
				this._enclosing.tpAbas.setSelectedComponent(this._enclosing.pnManutencao);
				this._enclosing.tfDesc.requestFocus();
			} catch (Exception ex) {
				showMessageDialog(this._enclosing, ex.getMessage(), "Erro", ERROR_MESSAGE);
			}
		}

	}

	public static class DesktopImagem extends JDesktopPane {
		private static final long serialVersionUID = -5591845940213709096L;
		private ImageIcon iiImagem;

		public DesktopImagem(String imagem) {
			iiImagem = new ImageIcon(imagem);
		}

		public void paintComponent(Graphics g) {
			super.paintComponent(g);
			iiImagem.paintIcon(this, g, 0, 0);
		}

		public Dimension getPreferredSize() {
			return new Dimension(700, 500);
		}
	}

}