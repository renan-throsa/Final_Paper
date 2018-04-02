package aspects;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import persistence.CategoriaDAO;

public aspect Transactions {

	public pointcut transactionOperation(CategoriaDAO dao)
		: execution( public void CategoriaDAO.*(..) throws SQLException ) && target(dao);

	void around(CategoriaDAO dao): transactionOperation(dao) {

		try {
			proceed(dao);
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

}
