package aspects;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.sql.SQLFeatureNotSupportedException;
import java.text.ParseException;
import java.util.regex.PatternSyntaxException;

import javax.swing.JFrame;
import javax.swing.JInternalFrame;
import javax.swing.JOptionPane;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import presentation.IFCliente;

public aspect Exceptions {

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

	// ----------------------------------------------------------------------------------------------------
	// Class principal
	public pointcut DAOExceptions(): execution(* JInternalFrame+.*(..)throws DAOException);

	after() throwing (DAOException dex): DAOExceptions(){

		JOptionPane.showMessageDialog(null, dex.getMessage(), "DAOException", JOptionPane.ERROR_MESSAGE);
	}

	public pointcut CCEExceptions(): execution(private * JFrame+.*(..)throws ClassCastException );

	after() throwing (ClassCastException ex): CNFExceptions(){
		JOptionPane.showMessageDialog(null, ex.getMessage(), "Tipo do elemento especificado incompatível",
				JOptionPane.ERROR_MESSAGE);
	}

	public pointcut CNFExceptions(): execution(* JFrame+.*(..)throws ClassNotFoundException );

	after() throwing (ClassNotFoundException ex): CNFExceptions(){
		JOptionPane.showMessageDialog(null, ex.getMessage(), "Driver não encontrado!", JOptionPane.ERROR_MESSAGE);

	}

	public pointcut NPExceptions(): execution(* JFrame+.*(..)throws NullPointerException);

	after() throwing (NullPointerException ex): NPExceptions(){
		JOptionPane.showMessageDialog(null, thisJoinPoint.getSignature(), "Elemento especificado nulo",
				JOptionPane.ERROR_MESSAGE);

	}

	public pointcut PEExceptions(): execution(* IFCliente.*(..)throws ParseException);

	after() throwing (ParseException ex): PEExceptions(){
		JOptionPane.showMessageDialog(null, ex.getMessage(), "Início da string não pode ser analisado.",
				JOptionPane.ERROR_MESSAGE);

	}

	// ----------------------------------------------------------------------------------------------------
	// Other classes
	public pointcut SQLFNSExceptions(): execution(* JInternalFrame+.*(..)throws SQLFeatureNotSupportedException);

	after() throwing (SQLFeatureNotSupportedException ex): SQLFNSExceptions(){
		JOptionPane.showMessageDialog(null, ex.getMessage(), "Caracteristica MySQL não suportada",
				JOptionPane.ERROR_MESSAGE);

	}

	public pointcut NFEExceptions(): execution(* JInternalFrame+.*(..)throws NumberFormatException);

	after() throwing (NumberFormatException ex): NFEExceptions(){
		JOptionPane.showMessageDialog(null, ex.getMessage(), "String não contém um número inteiro permissível.",
				JOptionPane.ERROR_MESSAGE);

	}

	public pointcut PSEExceptions(): execution(* JInternalFrame+.*(..)throws PatternSyntaxException);

	after() throwing (PatternSyntaxException ex): PSEExceptions(){
		JOptionPane.showMessageDialog(null, ex.getMessage(), "Sintaxe da expressão regular é inválida",
				JOptionPane.ERROR_MESSAGE);

	}

	public pointcut UOExceptions(): execution(* JInternalFrame+.*(..)throws UnsupportedOperationException );

	after() throwing (UnsupportedOperationException ex): UOExceptions(){
		JOptionPane.showMessageDialog(null, ex.getMessage(), "Operação de remoção não é suportada",
				JOptionPane.ERROR_MESSAGE);

	}

	public pointcut IAExceptions(): execution(* JInternalFrame+.*(..)throws IllegalArgumentException );

	after() throwing (IllegalArgumentException ex): IAExceptions(){
		JOptionPane.showMessageDialog(null, ex.getMessage(), "Elemento especificado não suportado",
				JOptionPane.ERROR_MESSAGE);

	}

	// ----------------------------------------------------------------------------------------------------
	// Checked Exceptions.

	public pointcut CKDExceptions(): execution(public void ActionListener.actionPerformed(ActionEvent));

	declare soft : Exception : CKDExceptions();

	void around() : CKDExceptions(){
		try {
			proceed();
		} catch (Exception ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage(), "UNCKDExceptions at actionPerformed()",
					JOptionPane.ERROR_MESSAGE);

		}
	}

	public pointcut CKDExceptionsEvent(): execution(public void ItemListener.itemStateChanged(ItemEvent));

	declare soft : Exception : CKDExceptionsEvent();

	/*
	 * void around() : CKDExceptionsEvent(){ try { proceed(); } catch (Exception ex)
	 * { JOptionPane.showMessageDialog(null, ex.getMessage(),
	 * "UNCKDExceptions at itemStateChanged()", JOptionPane.ERROR_MESSAGE);
	 * 
	 * } }
	 */
	public pointcut CKDExceptionsDcument(): execution(public void DocumentListener.changedUpdate(DocumentEvent));

	declare soft : Exception : CKDExceptionsDcument();
	/*
	 * void around() : CKDExceptionsDcument(){ try { proceed(); } catch (Exception
	 * ex) { JOptionPane.showMessageDialog(null, ex.getMessage(),
	 * "UNCKDExceptions at changedUpdate()", JOptionPane.ERROR_MESSAGE);
	 * 
	 * } }
	 */

}
