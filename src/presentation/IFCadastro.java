package presentation;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.text.ParseException;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFormattedTextField;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.ListSelectionModel;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import javax.swing.text.JTextComponent;

import aspects.Exceptions.DAOException;
import presentation.Handler.MouseHandler;


@SuppressWarnings("serial")
public abstract class IFCadastro extends JInternalFrame implements ChangeListener, ActionListener {
	protected JTabbedPane tpAbas;
	protected JTable tbDados;
	protected JPanel pnManutencao;
	protected JPanel pnRotulos;
	protected JPanel pnCampos;
	protected JPanel pnBotoes;
	protected JFormattedTextField tfCodigo;
	protected JTextField tfDesc;
	protected JButton btIncluir;
	protected JButton btAlterar;
	protected JButton btExcluir;
	
	
	@SuppressWarnings("deprecation")
	public IFCadastro(String titulo, int largura, int campos)
			throws ClassNotFoundException, SQLException, DAOException {
		setTitle(titulo);
		setSize(largura, 100 + 30 * campos);
		setClosable(true);
		setIconifiable(true);
		setResizable(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		tbDados = new JTable(new ModeloGrade());
		tbDados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbDados.setCursor(new Cursor(Cursor.HAND_CURSOR));
		tbDados.setFocusable(false);
		tbDados.setToolTipText("Clique para selecionar");

		tfCodigo = new JFormattedTextField(new Integer(0));
		tfCodigo.setEnabled(false);
		tfDesc = new JTextField();
		
		btIncluir = new JButton("Incluir");
		btAlterar = new JButton("Alterar");
		btExcluir = new JButton("Excluir");

		btIncluir.setMnemonic('I');
		btAlterar.setMnemonic('A');
		btExcluir.setMnemonic('E');

		pnManutencao = new JPanel();
		pnRotulos = new JPanel();
		pnCampos = new JPanel();
		pnBotoes = new JPanel();

		pnManutencao.setLayout(new BorderLayout());
		pnRotulos.setLayout(new GridLayout(campos, 1));
		pnCampos.setLayout(new GridLayout(campos, 1));

		pnRotulos.add(new JLabel(" Codigo: "));
		pnRotulos.add(new JLabel(" Descrição: "));
		pnCampos.add(tfCodigo);
		pnCampos.add(tfDesc);
		pnBotoes.add(btIncluir);
		pnBotoes.add(btAlterar);
		pnBotoes.add(btExcluir);
		pnManutencao.add(pnRotulos, BorderLayout.WEST);
		pnManutencao.add(pnCampos, BorderLayout.CENTER);
		pnManutencao.add(pnBotoes, BorderLayout.SOUTH);

		JScrollPane spDados = new JScrollPane(tbDados);
		spDados.setFocusable(false);

		tpAbas = new JTabbedPane();
		tpAbas.add("Seleção", spDados);
		tpAbas.add("Manutenção", pnManutencao);
		tpAbas.setMnemonicAt(0, 'S');
		tpAbas.setMnemonicAt(1, 'M');

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(tpAbas, BorderLayout.CENTER);

		tpAbas.addChangeListener(this);
		tbDados.addMouseListener( new MouseHandler(this));
		btIncluir.addActionListener(this);
		btAlterar.addActionListener(this);
		btExcluir.addActionListener(this);

		atualizarGrade();
	}

	@SuppressWarnings("rawtypes")
	public void stateChanged(ChangeEvent e) {
		if (tpAbas.getSelectedIndex() != 0)
			return;
		for (int i = 0; i < pnCampos.getComponentCount(); i++) {
			if (pnCampos.getComponent(i) instanceof JTextComponent)
				((JTextComponent) pnCampos.getComponent(i)).setText("");
			if (pnCampos.getComponent(i) instanceof JComboBox)
				((JComboBox) pnCampos.getComponent(i)).setSelectedIndex(0);
		}
	}

	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btIncluir)
			try {
				incluir();
			} catch (Exception ex) {
				// TODO Auto-generated catch block
				ex.printStackTrace();
			}
		else if (e.getSource() == btAlterar)
			try {
				alterar();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		else if (e.getSource() == btExcluir)
			try {
				excluir();
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	}

	protected abstract void atualizarGrade() throws ClassNotFoundException, SQLException, DAOException;

	protected abstract void incluir()
			throws ParseException, SQLFeatureNotSupportedException, SQLException, ClassNotFoundException, DAOException;

	protected abstract void alterar()
			throws ParseException, SQLFeatureNotSupportedException, SQLException, ClassNotFoundException, DAOException;

	protected abstract void excluir() throws SQLException, ClassNotFoundException, DAOException;

	protected abstract void carregarRegistro(String codigo) throws ClassNotFoundException, SQLException, DAOException;

}
