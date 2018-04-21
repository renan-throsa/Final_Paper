package aspects;

import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.text.ParseException;
import java.util.regex.PatternSyntaxException;

import javax.swing.JOptionPane;

import aspects.Implementations.Exhibitable;
import connection.ConexaoComercio;
import presentation.JFPrincipal;

public aspect Exceptions {

	// ----------------------------------------------------------------------------------------------------
	// Class DAOException

	public static final class DAOException extends Exception {

		private static final long serialVersionUID = 1L;

		public DAOException(String message) {
			super(message);
		}

		public DAOException(String message, Throwable throwable) {
			super(message, throwable);
		}
	}

	// ----------------------------------------------------------------------------------------------------
	// Methods exceptions

	public pointcut DAOExceptions(): execution(private * JFPrincipal.*(..)throws DAOException);

	public pointcut SQLFNSExceptions(): execution(private * JFPrincipal.*(..)throws SQLFeatureNotSupportedException);

	public pointcut CCEExceptions(): execution(private * JFPrincipal.*(..)throws ClassCastException );

	public pointcut CNFExceptions(): execution(private * JFPrincipal.*(..)throws ClassNotFoundException );

	public pointcut NPExceptions(): execution(private * JFPrincipal.*(..)throws NullPointerException);

	public pointcut PEExceptions(): execution(private * JFPrincipal.*(..)throws ParseException);
	
	public pointcut NFEExceptions(): execution(* JFPrincipal.*(..)throws NumberFormatException);

	public pointcut PSEExceptions(): execution(* JFPrincipal.*(..)throws PatternSyntaxException);

	public pointcut UOExceptions(): execution(* JFPrincipal.*(..)throws UnsupportedOperationException );

	public pointcut IAExceptions(): execution(* JFPrincipal.*(..)throws IllegalArgumentException );

	// ----------------------------------------------------------------------------------------------------

	after() throwing (DAOException dex): DAOExceptions(){
		JOptionPane.showMessageDialog(null, dex.getMessage(), "Erro", JOptionPane.ERROR_MESSAGE);

	}

	after() throwing (ClassCastException ex): CNFExceptions(){
		JOptionPane.showMessageDialog(null, ex.getMessage(), "Tipo do elemento especificado incompatível",
				JOptionPane.ERROR_MESSAGE);

	}

	after() throwing (ClassNotFoundException ex): CNFExceptions(){
		JOptionPane.showMessageDialog(null, ex.getMessage(), "Driver não encontrado!", JOptionPane.ERROR_MESSAGE);

	}

	after() throwing (NullPointerException ex): NPExceptions(){
		JOptionPane.showMessageDialog(null, thisJoinPoint.getSignature(), "Elemento especificado nulo",
				JOptionPane.ERROR_MESSAGE);

	}

	after() throwing (ParseException ex): PEExceptions(){
		JOptionPane.showMessageDialog(null, ex.getMessage(), "Início da string não pode ser analisado.",
				JOptionPane.ERROR_MESSAGE);

	}

	after() throwing (SQLFeatureNotSupportedException ex): SQLFNSExceptions(){
		JOptionPane.showMessageDialog(null, ex.getMessage(), "Caracteristica MySQL não suportada",
				JOptionPane.ERROR_MESSAGE);

	}

	
	after() throwing (NumberFormatException ex): NFEExceptions(){
		JOptionPane.showMessageDialog(null, ex.getMessage(), "String não contém um número inteiro permissível.",
				JOptionPane.ERROR_MESSAGE);

	}

	after() throwing (PatternSyntaxException ex): PSEExceptions(){
		JOptionPane.showMessageDialog(null, ex.getMessage(), "Sintaxe da expressão regular é inválida",
				JOptionPane.ERROR_MESSAGE);

	}


	after() throwing (UnsupportedOperationException ex): UOExceptions(){
		JOptionPane.showMessageDialog(null, ex.getMessage(), "Operação de remoção não é suportada",
				JOptionPane.ERROR_MESSAGE);

	}

	after() throwing (IllegalArgumentException ex): IAExceptions(){
		JOptionPane.showMessageDialog(null, ex.getMessage(), "Elemento especificado não suportado",
				JOptionPane.ERROR_MESSAGE);

	}

	/*
	 * Object around(ConexaoComercio connection) : call (public void
	 * ConexaoComercio.*(..) ) && target(connection)
	 * 
	 * { long startTime = System.nanoTime(); Object ret = proceed(connection);
	 * System.out.println( "Operation " + thisJoinPoint + " on " + connection +
	 * " took " + (System.nanoTime() - startTime)); return ret;
	 * 
	 * }
	 */

	// ----------------------------------------------------------------------------------------------------
	public pointcut CNFException(): initialization( public ConexaoComercio.new(..) throws ClassNotFoundException);

	public pointcut SQLExceptionExeption(): initialization( public ConexaoComercio.new(..) throws SQLException);

	// Constructors advises
	after() throwing (SQLException sql): SQLExceptionExeption(){
		JOptionPane.showMessageDialog(null, sql.getMessage(), "Falha MySQL ocorrida", JOptionPane.ERROR_MESSAGE);
	}

	after() throwing (ClassNotFoundException cnfe): CNFException(){
		JOptionPane.showMessageDialog(null, cnfe.getMessage(), "Driver não encontrado!", JOptionPane.ERROR_MESSAGE);

	}

}
