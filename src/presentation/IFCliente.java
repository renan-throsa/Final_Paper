
package presentation;

import static javax.swing.JOptionPane.ERROR_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;

import javax.swing.JFormattedTextField;
import javax.swing.JLabel;
import javax.swing.text.MaskFormatter;

import persistence.ClienteDAO;
import transference.Cliente;

public class IFCliente extends IFCadastro {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JFormattedTextField tfCpf;
	private JFormattedTextField tfNascimento;

	public IFCliente() throws ClassNotFoundException, SQLException, ParseException, NullPointerException {
		super("Cadastro de clientes", 300, 4);

		MaskFormatter fmtCpf = new MaskFormatter("###.###.###-##");
		fmtCpf.setPlaceholderCharacter('_');
		MaskFormatter fmtData = new MaskFormatter("##/##/####");
		fmtData.setPlaceholderCharacter('_');

		tfCpf = new JFormattedTextField(fmtCpf);
		tfNascimento = new JFormattedTextField(fmtData);

		((JLabel) pnRotulos.getComponent(1)).setText("Nome");
		pnRotulos.add(new JLabel(" CPF: "));
		pnRotulos.add(new JLabel(" Nascimento: "));
		pnCampos.add(tfCpf);
		pnCampos.add(tfNascimento);
	}

	public void atualizarGrade() throws ClassNotFoundException, SQLException {

		ResultSet rs = new ClienteDAO().carregarGrade();
		tbDados.setModel(new ModeloGrade(rs, new String[] { "Código", "Nome" }));
		tbDados.getColumnModel().getColumn(0).setMaxWidth(50);

	}

	public void incluir() throws ParseException, ClassNotFoundException, SQLException {
		Cliente c = new Cliente();
		c.setNome(tfDesc.getText());
		c.setCpf(tfCpf.getText());
		c.setNascimento(tfNascimento.getText());
		new ClienteDAO().incluir(c);
		atualizarGrade();
		tpAbas.setSelectedIndex(0);

	}

	public void alterar() throws SQLException, ParseException, ClassNotFoundException {
		Cliente c = new Cliente();
		c.setCodigo(tfCodigo.getText());
		c.setNome(tfDesc.getText());
		c.setCpf(tfCpf.getText());
		c.setNascimento(tfNascimento.getText());
		new ClienteDAO().alterar(c);
		atualizarGrade();
		tpAbas.setSelectedIndex(0);

	}

	public void excluir() throws NumberFormatException, SQLException, ClassNotFoundException {

		new ClienteDAO().excluir(Integer.parseInt(tfCodigo.getText()));
		ModeloGrade dtm = (ModeloGrade) tbDados.getModel();
		dtm.removeRow(tbDados.getSelectedRow());
		tpAbas.setSelectedIndex(0);

	}

	protected void carregarRegistro(String codigo) throws SQLException, NumberFormatException, ClassNotFoundException {
		Cliente c = new ClienteDAO().pesquisar(codigo);
		tfCodigo.setText(String.valueOf(c.getCodigo()));
		tfDesc.setText(c.getNome());
		tfCpf.setText(c.getCpf());
		tfNascimento.setText(c.getNascimentoFmt());
	}
}
