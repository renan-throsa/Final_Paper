package presentation;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;

import aspects.Exceptions.DAOException;
import persistence.CategoriaDAO;
import transference.Categoria;

public class IFCategoria extends IFCadastro {
	
	private static final long serialVersionUID = 1L;

	public IFCategoria() throws ClassNotFoundException, SQLException, DAOException {
		super("Cadastro de categorias", 300, 2);
	}

	protected void atualizarGrade() throws ClassNotFoundException, SQLException, DAOException  {

		ResultSet rs = new CategoriaDAO().carregarGrade();
		tbDados.setModel(new ModeloGrade(rs, new String[] { "Código", "Descricao" }));
		tbDados.getColumnModel().getColumn(0).setMaxWidth(50);

	}

	protected void incluir() throws SQLFeatureNotSupportedException, SQLException, ClassNotFoundException, DAOException {
		Categoria c = new Categoria();
		c.setDescricao(tfDesc.getText());
		new CategoriaDAO().incluir(c);
		atualizarGrade();
		tpAbas.setSelectedIndex(0);

	}

	protected void alterar() throws SQLFeatureNotSupportedException, SQLException, ClassNotFoundException, DAOException {
		Categoria c = new Categoria();
		c.setCodigo(tfCodigo.getText());
		c.setDescricao(tfDesc.getText());
		new CategoriaDAO().alterar(c);
		atualizarGrade();
		tpAbas.setSelectedIndex(0);

	}

	protected void excluir() throws SQLException, ClassNotFoundException, DAOException {
		
			int codigo = Integer.parseInt(tfCodigo.getText());
			new CategoriaDAO().excluir(codigo);
			ModeloGrade dtm = (ModeloGrade) tbDados.getModel();
			dtm.removeRow(tbDados.getSelectedRow());
			tpAbas.setSelectedIndex(0);
	
	}

	protected void carregarRegistro(String codigo) throws ClassNotFoundException, SQLException, DAOException  {
		Categoria c = new CategoriaDAO().pesquisar(codigo);
		tfCodigo.setText(String.valueOf(c.getCodigo()));
		tfDesc.setText(c.getDescricao());
	}
}
