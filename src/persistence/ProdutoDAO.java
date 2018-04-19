package persistence;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import aspects.Exceptions.DAOException;
import connection.ConexaoComercio;
import transference.Produto;

public class ProdutoDAO {
	private ConexaoComercio cc;

	public ProdutoDAO() throws ClassNotFoundException, SQLException {
		cc = new ConexaoComercio();
	}

	public ConexaoComercio getConnection() {
		return cc;
	}

	public void incluir(Produto c) throws SQLException, DAOException {

		PreparedStatement pst = cc.getConexao().prepareStatement(
				"INSERT INTO PRODUTO (DESCRICAO,PRECO,ID_CATEGORIA) " + "VALUES(?,?,?)",
				Statement.RETURN_GENERATED_KEYS);
		pst.setString(1, c.getDescricao());
		pst.setDouble(2, c.getPreco());
		pst.setInt(3, c.getIdCategoria());
		pst.executeUpdate();
		cc.confirmarTransacao();

		ResultSet rs = pst.getGeneratedKeys();
		if (rs.next())
			c.setCodigo(rs.getInt(1));
		rs.close();
		pst.close();
	}

	public void alterar(Produto c) throws SQLException, DAOException {
		PreparedStatement pst = cc.getConexao().prepareStatement(
				"UPDATE PRODUTO SET DESCRICAO = ?, PRECO = ?, " + "ID_CATEGORIA = ? WHERE CODIGO = ?");
		pst.setString(1, c.getDescricao());
		pst.setDouble(2, c.getPreco());
		pst.setInt(3, c.getIdCategoria());
		pst.setInt(4, c.getCodigo());
		pst.executeUpdate();
		pst.close();
		cc.confirmarTransacao();
	}

	public void excluir(int codigo) throws SQLException, DAOException {
		PreparedStatement pst = cc.getConexao().prepareStatement("DELETE FROM PRODUTO WHERE CODIGO = ?");
		pst.setInt(1, codigo);
		pst.executeUpdate();
		pst.close();
		cc.confirmarTransacao();
	}

	public ResultSet carregarGrade() throws SQLException, DAOException {
		Statement stm = cc.getConexao().createStatement();
		return stm.executeQuery("SELECT CODIGO,DESCRICAO FROM PRODUTO ORDER BY DESCRICAO");
	}

	public Vector<Produto> carregarCombo() throws SQLException, DAOException {
		Statement stm = cc.getConexao().createStatement();
		ResultSet rs = stm.executeQuery("SELECT CODIGO,DESCRICAO FROM PRODUTO ORDER BY DESCRICAO");
		Vector<Produto> v = new Vector<Produto>();
		while (rs.next())
			v.add(new Produto(rs.getInt("CODIGO"), rs.getString("DESCRICAO")));
		return v;
	}

	public Produto pesquisar(int codigo) throws SQLException, DAOException {
		PreparedStatement pst = cc.getConexao().prepareStatement("SELECT * FROM PRODUTO WHERE CODIGO = ?");
		pst.setInt(1, codigo);
		ResultSet rs = pst.executeQuery();
		if (!rs.next())
			return null;
		return new Produto(rs.getInt("CODIGO"), rs.getString("DESCRICAO"), rs.getDouble("PRECO"),
				rs.getInt("ID_CATEGORIA"));
	}

	public Produto pesquisar(String codigo) throws NumberFormatException, SQLException, DAOException {
		return pesquisar(Integer.parseInt(codigo));
	}
}
