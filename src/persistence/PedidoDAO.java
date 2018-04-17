package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.sql.Statement;

import aspects.Exceptions.DAOException;
import connection.ConexaoComercio;
import transference.Item;
import transference.Pedido;

public class PedidoDAO {
	private ConexaoComercio cc;

	public PedidoDAO() throws Exception {
		cc = new ConexaoComercio();
	}

	public ConexaoComercio getConnection() {
		return cc;
	}
	
	public void incluir(Pedido p) throws SQLException,SQLFeatureNotSupportedException, ClassNotFoundException, DAOException {
		PreparedStatement pst = cc.getConexao().prepareStatement(
				"INSERT INTO PEDIDO(DATA,HORARIO,ID_CLIENTE,STATUS) " + "VALUES(?,?,?,?)",
				Statement.RETURN_GENERATED_KEYS);
		pst.setDate(1, new java.sql.Date(new java.util.Date().getTime()));
		pst.setTime(2, new java.sql.Time(new java.util.Date().getTime()));
		pst.setInt(3, p.getIdCliente());
		pst.setString(4, String.valueOf(p.getStatus()));
		pst.executeUpdate();

		ResultSet rs = pst.getGeneratedKeys();
		if (rs.next())
			p.setNumero(rs.getInt(1));
		rs.close();
		pst.close();

		for (Item i : p.getItens()) {
			i.setIdPedido(p.getNumero());
			new ItemDAO().incluir(i);
		}
		
		
		cc.confirmarTransacao();
	}
	
	
	
	
}
