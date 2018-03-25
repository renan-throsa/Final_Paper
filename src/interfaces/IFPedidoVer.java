package interfaces;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import persistence.ClienteDAO;
import presentation.ModeloGrade;
import transference.Cliente;

public class IFPedidoVer extends JInternalFrame implements ItemListener, DocumentListener, ActionListener {

	private static final long serialVersionUID = 1L;
	protected JComboBox<Cliente> coCliente;
	protected JTable tbDados;
	protected JPanel pnEntrada;
	protected JPanel pnCampos;

	public IFPedidoVer() {
		setTitle("Ver pedido");
		setSize(375,400);
		setClosable(true);
		setIconifiable(true);
		setResizable(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

		tbDados = new JTable(new ModeloGrade(new String[] { "Número", "Data", "Horário", "Id", "Status" }));
		tbDados.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		tbDados.setCursor(new Cursor(Cursor.HAND_CURSOR));
		tbDados.setFocusable(false);
		tbDados.getColumnModel().getColumn(0).setMaxWidth(75);
		tbDados.getColumnModel().getColumn(1).setMaxWidth(75);
		tbDados.getColumnModel().getColumn(2).setMaxWidth(75);
		tbDados.getColumnModel().getColumn(3).setMaxWidth(75);
		tbDados.getColumnModel().getColumn(4).setMaxWidth(75);

		coCliente = new JComboBox<Cliente>();

		try {
			coCliente.setModel(new DefaultComboBoxModel<Cliente>(new ClienteDAO().carregarCombo()));
		} catch (ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		pnEntrada = new JPanel();
		pnCampos = new JPanel();

		pnEntrada.setLayout(new BorderLayout());
		pnCampos.setLayout(new GridLayout(1, 1));

		pnCampos.add(coCliente);

		pnEntrada.add(pnCampos, BorderLayout.CENTER);

		JScrollPane spDados = new JScrollPane(tbDados, JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,
				JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		spDados.setFocusable(false);

		getContentPane().setLayout(new BorderLayout());
		getContentPane().add(pnEntrada, BorderLayout.NORTH);
		getContentPane().add(spDados, BorderLayout.CENTER);

	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void changedUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void insertUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeUpdate(DocumentEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void itemStateChanged(ItemEvent arg0) {
		// TODO Auto-generated method stub

	}

}
