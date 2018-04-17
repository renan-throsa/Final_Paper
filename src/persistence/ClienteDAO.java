package persistence;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;

import aspects.Exceptions.DAOException;
import connection.ConexaoComercio;
import transference.Cliente;

public class ClienteDAO {
  private ConexaoComercio cc;
  
  public ClienteDAO() throws ClassNotFoundException, SQLException  {
    cc = new ConexaoComercio();
  }
  
  public ConexaoComercio getConnection() {
		return cc;
	}
  
  public void incluir(Cliente c) throws SQLException,DAOException {
    PreparedStatement pst = cc.getConexao().prepareStatement(
      "INSERT INTO CLIENTE (NOME,CPF,NASCIMENTO) VALUES(?,?,?)",
      Statement.RETURN_GENERATED_KEYS);
    pst.setString(1, c.getNome());
    pst.setString(2, c.getCpf());
    pst.setDate(3, new Date(c.getNascimento().getTime()));
    pst.executeUpdate();
    cc.confirmarTransacao();
    
    ResultSet rs = pst.getGeneratedKeys();
    if (rs.next()) c.setCodigo(rs.getInt(1));
    rs.close();
    pst.close();
  }
  
  public void alterar(Cliente c) throws SQLException,DAOException {
    PreparedStatement pst = cc.getConexao().prepareStatement(
      "UPDATE CLIENTE SET NOME = ?, CPF = ?, NASCIMENTO = ? " +
      "WHERE CODIGO = ?");
    pst.setString(1, c.getNome());
    pst.setString(2, c.getCpf());
    pst.setDate(3, new Date(c.getNascimento().getTime()));
    pst.setInt(4, c.getCodigo());
    pst.executeUpdate();
    pst.close();
    cc.confirmarTransacao();
  }
  
  public void excluir(int codigo) throws SQLException,DAOException {
    PreparedStatement pst = cc.getConexao().prepareStatement(
      "DELETE FROM CLIENTE WHERE CODIGO = ?");
    pst.setInt(1, codigo);
    pst.executeUpdate();
    pst.close();
    cc.confirmarTransacao();
  }
  
  public ResultSet carregarGrade( ) throws SQLException,DAOException {
    Statement stm = cc.getConexao().createStatement();
    return stm.executeQuery(
      "SELECT CODIGO,NOME FROM CLIENTE ORDER BY NOME");
  }
  
  public Vector<Cliente> carregarCombo( ) throws SQLException,DAOException {
    Statement stm = cc.getConexao().createStatement();
    ResultSet rs = stm.executeQuery(
      "SELECT CODIGO,NOME FROM CLIENTE ORDER BY NOME");
    Vector<Cliente> v = new Vector<Cliente>();
    while(rs.next())
      v.add(new Cliente(rs.getInt("CODIGO"),rs.getString("NOME")));
    return v;
  }
  
  public Cliente pesquisar(int codigo) throws SQLException {
    PreparedStatement pst = cc.getConexao().prepareStatement(
        "SELECT * FROM CLIENTE WHERE CODIGO = ?");
    pst.setInt(1, codigo);
    ResultSet rs = pst.executeQuery();
    if (!rs.next()) return null;
    return new Cliente(rs.getInt("CODIGO"),rs.getString("NOME"),
        rs.getString("CPF"),rs.getDate("NASCIMENTO"));
  }
  
  public Cliente pesquisar(String codigo) throws SQLException,NumberFormatException {
    return pesquisar(Integer.parseInt(codigo));
  }
}
