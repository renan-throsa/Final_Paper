package aspects;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import interfaces.Connectable;
import transference.Categoria;
import transference.Produto;

public aspect Transactions {

	public pointcut transactionOperation(Connectable dao)
		: (execution( public void Connectable+.*(..) throws SQLException ) 
			||execution( public Vector Connectable+.*(..) throws SQLException )
				||execution( public Categoria Connectable+.*(..) throws SQLException )
					||execution( public Produto Connectable+.*(..) throws SQLException ))
					&& target(dao);

	Object around(Connectable dao): transactionOperation(dao) {
		try {
			Object ret = proceed(dao);
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

	public pointcut queryOperation(Connectable dao)
	: execution( public ResultSet Connectable+.*(..) throws SQLException ) 
			&& target(dao);

	Object around(Connectable dao): queryOperation(dao) {
		try {
			Object ret = proceed(dao);
			return ret;
		} catch (SQLException e) {
			try {
				if (dao.getConnection() != null)
					dao.getConnection().fechar();
				return new DAOException("Erro na Querry.", e);
			} catch (SQLException e1) {
				return new DAOException("Erro na Querry e ao fechar a conexão.", e1);
			}

		}

	}

}
