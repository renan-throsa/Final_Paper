package aspects;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;

import interfaces.Connectable;
import transference.Categoria;
import transference.Produto;

public aspect Transactions {

	public pointcut transactionOperation(Connectable dao)
		: (call( public void Connectable+.*(..) throws SQLException ) 
			||execution( public Vector Connectable+.*(..) throws SQLException )
				||execution( public Categoria Connectable+.*(..) throws SQLException )
					||execution( public Produto Connectable+.*(..) throws SQLException ))
					&& target(dao);

	Object around(Connectable dao) throws DAOException: transactionOperation(dao) {
		String method = thisJoinPoint.getSignature().getName();
		try {
			Object ret = proceed(dao);

			if (method.equals("incluir") || method.equals("alterar") || method.equals("excluir")) {
				JOptionPane.showMessageDialog(null,
						"Operação " + thisJoinPoint.getSignature().getName() + " realizada com sucesso!", "Mensagem",
						JOptionPane.INFORMATION_MESSAGE);
			}
			return ret;
		} catch (SQLException e) {
			try {
				if (dao.getConnection() != null)
					dao.getConnection().cancelarTransacao();
				throw new DAOException("Erro na operação "+method, e);
			} catch (SQLException e1) {
				throw new DAOException("Erro no operação "+method +" e rollback.", e1);
			}

		}

		finally {
			try {
				if (dao.getConnection() != null)
					dao.getConnection().fechar();
				if (method.equals("incluir") || method.equals("alterar") || method.equals("excluir")) {

					JOptionPane.showMessageDialog(null, "Conexão fechada com sucesso!", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (SQLException e2) {
				return new DAOException("Erro ao fechar a conexão.", e2);
			}
		}

	}

	public pointcut queryOperation(Connectable dao)
	: execution( public ResultSet Connectable+.*(..) throws SQLException ) 
			&& target(dao);

	Object around(Connectable dao)throws DAOException: queryOperation(dao) {
		try {
			Object ret = proceed(dao);
			return ret;
		} catch (SQLException e) {
			try {
				if (dao.getConnection() != null)
					dao.getConnection().fechar();
				throw new DAOException("Erro na Querry.", e);
			} catch (SQLException e1) {
				throw new DAOException("Erro na Querry e ao fechar a conexão.", e1);
			}

		}

	}

}
