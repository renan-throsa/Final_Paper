package aspects;

import java.sql.SQLException;

import javax.swing.JOptionPane;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

import aspects.Exceptions.DAOException;
import aspects.Implementations.Connectable;

@Aspect
public class AJTransactions {

	/*
	@Pointcut("execution(public void Connectable+.*(..))")
	public void pointcutA() {
	}

	
	@Pointcut("pointcutA()" + " && target(dao)")
	public void transactionOperation(Connectable dao) {
	}

	@Around("transactionOperation(dao)")
	public Object mangeTransaction(ProceedingJoinPoint pjp, Connectable dao) throws Throwable {
		String method = pjp.getSignature().getName();

		try

		{
			Object ret = pjp.proceed();

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
					JOptionPane.showMessageDialog(null, "Conexão fechada com sucesso!", "Mensagem",
							JOptionPane.INFORMATION_MESSAGE);
				}
			} catch (SQLException e2) {
				return new DAOException("Erro ao fechar a conexão.", e2);
			}
		}

	}

	@Pointcut("execution(public * aspects.Implementations.Connectable+.*(..))" + " && target(dao)")
	public void queryOperation(Connectable dao) {
	}

	@Around("queryOperation(dao)")
	public Object queryTransaction(ProceedingJoinPoint pjp, Connectable dao) throws Throwable {
		String method = pjp.getSignature().getName();
		try {
			Object ret = pjp.proceed();
			return ret;
		} catch (SQLException e) {
			try {
				if (dao.getConnection() != null)
					dao.getConnection().cancelarTransacao();
				throw new DAOException("Erro, operação " + method, e);
			} catch (SQLException e1) {
				throw new DAOException("Erro, operação " + method + " e rollback.", e1);
			}
		}

	}

	private void showMessage(String method) {
		JOptionPane.showMessageDialog(null, "Operação " + method + " realizada com sucesso!", "Mensagem",
				JOptionPane.INFORMATION_MESSAGE);

	}

	private boolean shouldShow(String method) {
		return method.equals("incluir") || method.equals("alterar") || method.equals("excluir");
	}
*/
}
