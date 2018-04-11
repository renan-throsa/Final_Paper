package aspects;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.JMenuItem;

import connection.ConexaoComercio;
import interfaces.Downloadable;
import interfaces.Order;
import persistence.PedidoDAO;
import presentation.IFPedidoVer;
import presentation.JFPrincipal;

public aspect Introductions {

public JMenuItem JFPrincipal.miVer = new JMenuItem("Ver");
	
	declare parents: JFPrincipal implements Order;
	
	public void JFPrincipal.verPedido() throws ClassNotFoundException, SQLException, DAOException {
		IFPedidoVer janela = new IFPedidoVer();
		janela.setLocation(10, 10);
		desktop.add(janela);
		janela.setVisible(true);
	}

	declare parents: PedidoDAO implements Downloadable;
	
	public ResultSet PedidoDAO.loadGrid(int codigo)throws ClassNotFoundException, SQLException,DAOException {
		ConexaoComercio cc = new ConexaoComercio();
		PreparedStatement pst = cc.getConexao().prepareStatement(
				"select NUMERO, DATA, HORARIO,ID_CLIENTE, STATUS from comercio.PEDIDO, "
						+ "comercio.CLIENTE where ID_CLIENTE = CODIGO and NUMERO = ?");
		pst.setInt(1,codigo);
		return pst.executeQuery();
	}
	
	
}
