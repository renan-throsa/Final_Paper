package aspects;

import static javax.swing.JOptionPane.showMessageDialog;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import persistence.CategoriaDAO;
import transference.Categoria;

public aspect Transactions {

	public pointcut transactionOperation(CategoriaDAO dao, Categoria categoria)
		: execution( public void CategoriaDAO.*(Categoria) throws SQLException ) && target(dao)
		&& args(categoria);

	public pointcut transactionOperation2(CategoriaDAO dao, int code)
	: execution( public Categoria CategoriaDAO.*(..) throws SQLException ) && target(dao)&& args(code);

	void around(CategoriaDAO dao, Categoria categoria): transactionOperation(dao,categoria) {

		try {
			proceed(dao, categoria);
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro no insert.", 0);
			try {
				if (dao.getConnection() != null)
					dao.getConnection().cancelarTransacao();
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro no insert e rollback.", 0);
			}

		}

	}

	Object around(CategoriaDAO dao, int code): transactionOperation2(dao,code) {
		try {
			Object ret = proceed(dao, code);
			showMessageDialog(null,"Operação realizada com sucesso!");
			return ret;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage(), "Erro no insert.", 0);
			try {
				if (dao.getConnection() != null) {
					dao.getConnection().cancelarTransacao();
					
				}
			} catch (SQLException e1) {
				JOptionPane.showMessageDialog(null, e1.getMessage(), "Erro no insert e rollback.", 0);
				
			}
			return new DAOException("");
		}

	}

}
