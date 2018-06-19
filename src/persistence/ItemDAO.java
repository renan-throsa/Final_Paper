package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import aspects.Exceptions.DAOException;
import connection.ConexaoComercio;
import transference.Item;

public class ItemDAO {
	private ConexaoComercio cc;

	public ItemDAO() throws ClassNotFoundException, SQLException {
		cc = new ConexaoComercio();
	}

	public void incluir(Item item) throws SQLException, DAOException {
		PreparedStatement pst = cc.getConexao().prepareStatement("INSERT INTO ITEM VALUES(?,?,?,?)");
		pst.setInt(1, item.getIdPedido());
		pst.setInt(2, item.getIdProduto());
		pst.setInt(3, item.getQuantidade());
		pst.setDouble(4, item.getUnitario());
		pst.executeUpdate();
		pst.close();
	}

	public Item pesquisar(int codigo) throws SQLException, DAOException {
		PreparedStatement pst = cc.getConexao().prepareStatement("SELECT * FROM ITEM WHERE CODIGO = ?");
		pst.setInt(1, codigo);
		ResultSet rs = pst.executeQuery();
		if (!rs.next())
			return null;

		return new Item(rs.getInt("ID_PEDIDO"), rs.getInt("ID_PRODUTO"), rs.getInt("QUANTIDADE"),
				rs.getDouble("UNITARIO"));
	}

}
