package aspects;

import static javax.swing.JOptionPane.showMessageDialog;

import java.sql.SQLException;

import persistence.CategoriaDAO;

public aspect Transactions {

	public pointcut transactionOperation(CategoriaDAO dao)
	: execution( public * CategoriaDAO.*(..) throws SQLException ) && target(dao);

	Object around(CategoriaDAO dao): transactionOperation(dao) {
		try {
			Object ret = proceed(dao);
			showMessageDialog(null, "Operação realizada com sucesso!");
			return ret;
		} catch (SQLException e) {
			try {
				if (dao.getConnection() != null)
					dao.getConnection().cancelarTransacao();
				return new DAOException("Erro no insert.", e);
			} catch (SQLException e1) {
				return new DAOException("Erro no insert e rollback.", e1);
			}

		} finally {
			try {
				if (dao.getConnection() != null)
					dao.getConnection().fechar();
			} catch (SQLException e2) {
				return new DAOException("Erro ao fechar a conexão.", e2);
			}
		}
	}

}
