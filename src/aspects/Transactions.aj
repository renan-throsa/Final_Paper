package aspects;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import com.mysql.jdbc.exceptions.jdbc4.MySQLIntegrityConstraintViolationException;

import aspects.Exceptions.DAOException;
import aspects.Implementations.Connectable;
import transference.Categoria;
import transference.Produto;

public aspect Transactions {

	pointcut pointcutA():execution(public void Connectable+.*(..) throws SQLException);

	pointcut pointcutB():execution(public Vector Connectable+.*(..) throws SQLException);

	pointcut pointcutC():execution(public Categoria Connectable+.*(..) throws SQLException);

	pointcut pointcutD():execution(public Produto Connectable+.*(..) throws SQLException);

	pointcut pointcutE():execution( public ResultSet Connectable+.*(..) throws SQLException);

	public pointcut inclusionOperation(Connectable dao)
	: pointcutA() && target(dao);

	void around(Connectable dao) throws DAOException, MySQLIntegrityConstraintViolationException: inclusionOperation(dao) {
		String method = thisJoinPoint.getSignature().getName();
		try

		{
			proceed(dao);

			if (shouldShow(method)) {
				showMessage(method);
			}

		} catch (SQLException e) {
			try {
				if (dao.getConnection() != null)
					dao.getConnection().cancelarTransacao();
				throw new DAOException("Erro, operação " + method, e);
			} catch (SQLException e1) {
				throw new DAOException("Erro, operação " + method + " e rollback.", e1);
			}

		} /*finally {
			try {
				if (dao.getConnection() != null)
					dao.getConnection().fechar();
				dao.getConnection().fechar();
				if (shouldShow(method)) {
					// JOptionPane.showMessageDialog(null, "Conexão fechada com sucesso!",
					// "Mensagem",JOptionPane.INFORMATION_MESSAGE);
					System.out.println("Mensagem - Conexão fechada com sucesso!");
				}
			} catch (SQLException e2) {
				throw new DAOException("Erro ao fechar a conexão.", e2);
			}
		}
*/
	}

	public pointcut transactionOperation(Connectable dao)
	: (pointcutB()
			||pointcutC()
				||pointcutD())
				&& target(dao);

	Object around(Connectable dao) throws DAOException, MySQLIntegrityConstraintViolationException: transactionOperation(dao) {
		String method = thisJoinPoint.getSignature().getName();
		try

		{
			Object ret = proceed(dao);

			if (shouldShow(method)) {
				showMessage(method);
			}
			return ret;
		} catch (SQLException e) {
			try {
				if (dao.getConnection() != null)
					dao.getConnection().cancelarTransacao();
				throw new DAOException("Erro, operação " + method, e);
			} catch (SQLException e1) {
				throw new DAOException("Erro, operação " + method + " e rollback.", e1);
			}

		} finally {
			try {
				if (dao.getConnection() != null)
					dao.getConnection().fechar();
				if (shouldShow(method)) {
					// JOptionPane.showMessageDialog(null, "Conexão fechada com sucesso!",
					// "Mensagem",JOptionPane.INFORMATION_MESSAGE);
					System.out.println("Mensagem - Conexão fechada com sucesso!");
				}
			} catch (SQLException e2) {
				return new DAOException("Erro ao fechar a conexão.", e2);
			}
		}

	}

	public pointcut queryOperation(Connectable dao)
	:  pointcutE() && target(dao);

	Object around(Connectable dao) throws DAOException: queryOperation(dao) {
		try {
			Object ret = proceed(dao);
			return ret;
		} catch (

		SQLException e) {
			try {
				if (dao.getConnection() != null)
					dao.getConnection().fechar();
				throw new DAOException("Erro na Querry.", e);
			} catch (SQLException e1) {
				throw new DAOException("Erro na Querry e ao fechar a conexão.", e1);
			}

		}

	}

	private boolean shouldShow(String method) {
		return method.equals("incluir") || method.equals("alterar") || method.equals("excluir");
	}

	private void showMessage(String method) {
		// JOptionPane.showMessageDialog(null, "Operação " + method + " realizada com
		// sucesso!", "Mensagem",JOptionPane.INFORMATION_MESSAGE);

		System.out.println("Operação " + method + " realizada com sucesso!");

	}

}
