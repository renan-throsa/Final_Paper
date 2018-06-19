package aspects;

import java.sql.SQLFeatureNotSupportedException;
import java.text.ParseException;
import java.util.regex.PatternSyntaxException;

import javax.swing.JOptionPane;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;

import aspects.Exceptions.DAOException;

@Aspect
public class AJExceptions {
/*	
	// ----------------------------------------------------------------------------------------------------
		// Class DAOException

		public static final class DAOException extends Exception {

			private static final long serialVersionUID = 1395458548657103955L;

			public DAOException(String message) {
				super(message);
			}

			public DAOException(String message, Throwable throwable) {
				super(message, throwable);
			}
		}


	
	@AfterThrowing(pointcut = "execution(* presentation.JFPrincipal.*(..))", throwing = "error")
	public void afterThrowing_DAOExceptions(DAOException error) {
		JOptionPane.showMessageDialog(null, error.getMessage(), "DAOException", JOptionPane.ERROR_MESSAGE);
	}

	@AfterThrowing(pointcut = "execution(* presentation.JFPrincipal.*(..))", throwing = "error")
	public void afterThrowing_CCEExceptions(ClassCastException error) {
		JOptionPane.showMessageDialog(null, error.getMessage(), "Tipo do elemento especificado incompatível",
				JOptionPane.ERROR_MESSAGE);
	}

	@AfterThrowing(pointcut = "execution(* presentation.JFPrincipal.*(..))", throwing = "error")
	public void afterThrowing_CNFExceptions(ClassNotFoundException error) {
		JOptionPane.showMessageDialog(null, error.getMessage(), "Driver não encontrado!", JOptionPane.ERROR_MESSAGE);
	}

	@AfterThrowing(pointcut = "execution(* presentation.JFPrincipal.*(..))", throwing = "error")
	public void afterThrowing_CNFExceptions(JoinPoint joinPoint, NullPointerException error) {
		JOptionPane.showMessageDialog(null, joinPoint.getSignature(), "Elemento especificado nulo",
				JOptionPane.ERROR_MESSAGE);
	}

	@AfterThrowing(pointcut = "execution(* presentation.JFPrincipal.*(..))", throwing = "error")
	public void afterThrowing_PEExceptions(ParseException error) {
		JOptionPane.showMessageDialog(null, error.getMessage(), "Início da string não pode ser analisado.",
				JOptionPane.ERROR_MESSAGE);
	}

	@AfterThrowing(pointcut = "execution(* presentation.*.*(..))", throwing = "error")
	public void afterThrowing_SQLFNSExceptions(SQLFeatureNotSupportedException error) {
		JOptionPane.showMessageDialog(null, error.getMessage(), "Caracteristica MySQL não suportada",
				JOptionPane.ERROR_MESSAGE);
	}

	@AfterThrowing(pointcut = "execution(* presentation.*.*(..))", throwing = "error")
	public void afterThrowing_NFEExceptions(NumberFormatException error) {
		JOptionPane.showMessageDialog(null, error.getMessage(), "String não contém um número inteiro permissível.",
				JOptionPane.ERROR_MESSAGE);
	}

	// TODO There is a difference between @Aspect ant the point cut
	// afterThrowing_PSEExceptions
	@AfterThrowing(pointcut = "execution(* presentation.IFCliente.*(..))", throwing = "error")
	public void afterThrowing_PSEExceptions(PatternSyntaxException error) {
		JOptionPane.showMessageDialog(null, error.getMessage(), "Sintaxe da expressão regular é inválida",
				JOptionPane.ERROR_MESSAGE);
	}

	@AfterThrowing(pointcut = "execution(* presentation.IFCadastro.*(..))", throwing = "error")
	public void afterThrowing_UOExceptions(UnsupportedOperationException error) {
		JOptionPane.showMessageDialog(null, error.getMessage(), "Operação de remoção não é suportada",
				JOptionPane.ERROR_MESSAGE);
	}
	
	@AfterThrowing(pointcut = "execution(* presentation.IFCadastro.*(..))", throwing = "error")
	public void afterThrowing_IAExceptions(IllegalArgumentException error) {
		JOptionPane.showMessageDialog(null, error.getMessage(), "Operação de remoção não é suportada",
				JOptionPane.ERROR_MESSAGE);
	}

	// ----------------------------------------------------------------------------------------------------
		// Checked Exceptions.

	
*/	
	
}
