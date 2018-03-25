package aspects;

import javax.swing.JMenuItem;

import interfaces.IFPedidoVer;
import interfaces.Order;
import presentation.JFPrincipal;

public aspect Introductions {
	
	public JMenuItem JFPrincipal.miVer = new JMenuItem("Ver");
	
	
	declare parents: JFPrincipal implements Order;
	
	public void JFPrincipal.verPedido(){
		IFPedidoVer janela = new IFPedidoVer();
	    janela.setLocation(10, 10);
	    desktop.add(janela);
	    janela.setVisible(true);
	}
	
}
