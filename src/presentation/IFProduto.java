package presentation;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JLabel;

import aspects.Exceptions.DAOException;
import persistence.CategoriaDAO;
import persistence.ProdutoDAO;
import transference.Categoria;
import transference.Produto;

public class IFProduto extends IFCadastro {

	private static final long serialVersionUID = 1L;
	private JFormattedTextField tfPreco;
	private JComboBox<Categoria> coCategoria;

	public IFProduto() throws SQLException, ClassNotFoundException,DAOException {
		super("Cadastro de produtos", 300, 4);

		tfPreco = new JFormattedTextField(new Double(0));
		coCategoria = new JComboBox<Categoria>();

		pnRotulos.add(new JLabel(" Preço: "));
		pnRotulos.add(new JLabel(" Categoria: "));
		pnCampos.add(tfPreco);
		pnCampos.add(coCategoria);

		coCategoria.setModel(new DefaultComboBoxModel<Categoria>(new CategoriaDAO().carregarCombo()));

	}

	public void atualizarGrade() throws ClassNotFoundException, SQLException, DAOException {
		ResultSet rs = new ProdutoDAO().carregarGrade();
		tbDados.setModel(new ModeloGrade(rs, new String[] { "Código", "Descrição" }));
		tbDados.getColumnModel().getColumn(0).setMaxWidth(50);
	}

	public void incluir() throws SQLException, ClassNotFoundException, DAOException {
		Produto p = new Produto();
		p.setDescricao(tfDesc.getText());
		p.setPreco(tfPreco.getText());
		Categoria categoria = (Categoria) coCategoria.getSelectedItem();
		p.setIdCategoria(categoria.getCodigo());

		new ProdutoDAO().incluir(p);
		atualizarGrade();
		tpAbas.setSelectedIndex(0);

	}

	public void alterar() throws SQLException, ClassNotFoundException, DAOException {
		Produto p = new Produto();
		p.setCodigo(tfCodigo.getText());
		p.setDescricao(tfDesc.getText());
		p.setPreco(tfPreco.getText());
		Categoria categoria = (Categoria) coCategoria.getSelectedItem();
		p.setIdCategoria(categoria.getCodigo());

		new ProdutoDAO().alterar(p);
		atualizarGrade();
		tpAbas.setSelectedIndex(0);

	}

	public void excluir() throws NumberFormatException, SQLException, ClassNotFoundException, DAOException {

		new ProdutoDAO().excluir(Integer.parseInt(tfCodigo.getText()));
		ModeloGrade dtm = (ModeloGrade) tbDados.getModel();
		dtm.removeRow(tbDados.getSelectedRow());
		tpAbas.setSelectedIndex(0);

	}

	protected void carregarRegistro(String codigo) throws ClassNotFoundException, SQLException ,DAOException{
		Produto p = new ProdutoDAO().pesquisar(codigo);
		tfCodigo.setText(String.valueOf(p.getCodigo()));
		tfDesc.setText(p.getDescricao());
		tfPreco.setText(p.getPrecoFmt());
		coCategoria.setSelectedItem(new CategoriaDAO().pesquisar(p.getIdCategoria()));
	}
}
