package exceptions;

import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.text.ParseException;
import java.util.regex.PatternSyntaxException;

import javax.swing.JOptionPane;

import connection.ConexaoComercio;

public aspect Exceptions {

	/*
	 * The return object is collected in returning() by specifying the type and
	 * object identifier.
	 */
	// Methods exceptions
	public pointcut SQLExptions(Object object): execution( * *.*(..) throws SQLException ) && target(object);

	public pointcut SQLFNSExceptions(): execution(* *.*(..)throws SQLFeatureNotSupportedException);

	public pointcut NFEExceptions(): execution(* *.*(..)throws NumberFormatException);

	public pointcut PEExceptions(): execution(* *.*(..)throws ParseException);

	public pointcut PSEExceptions(): execution(* *.*(..)throws PatternSyntaxException);

	public pointcut CCEExceptions(): execution(* *.*(..)throws ClassCastException );

	public pointcut CNFExceptions(): execution(* *.*(..)throws ClassNotFoundException );

	public pointcut NPExceptions(): execution(* *.*(..)throws NullPointerException);

	public pointcut UOExceptions(): execution(* *.*(..)throws UnsupportedOperationException );

	public pointcut IAExceptions(): execution(* *.*(..)throws IllegalArgumentException );

	after(Object object) throwing (SQLException ex): SQLExptions(object){
		JOptionPane.showMessageDialog(null, ex.getMessage(), "Falha MySQL ocorrida", 0);
		if(object != null) {
			try {
				((ConexaoComercio)object).cancelarTransacao();
			} catch (SQLException e) {
				
			}
		}
	}

	after() throwing (SQLFeatureNotSupportedException ex): SQLFNSExceptions(){
		JOptionPane.showMessageDialog(null, ex.getMessage(), "Caracteristica MySQL não suportada", 0);

	}

	after() throwing (ParseException ex): PEExceptions(){
		JOptionPane.showMessageDialog(null, ex.getMessage(), "Início da string especificada não pode ser analisado.",
				0);

	}

	after() throwing (NumberFormatException ex): NFEExceptions(){
		JOptionPane.showMessageDialog(null, ex.getMessage(), "String não contém um número inteiro permissível.", 0);

	}

	after() throwing (PatternSyntaxException ex): PSEExceptions(){
		JOptionPane.showMessageDialog(null, ex.getMessage(), "Sintaxe da expressão regular é inválida", 0);

	}

	after() throwing (ClassCastException ex): CNFExceptions(){
		JOptionPane.showMessageDialog(null, ex.getMessage(), "Tipo do elemento especificado incompatível", 0);

	}

	after() throwing (ClassNotFoundException ex): CNFExceptions(){
		JOptionPane.showMessageDialog(null, ex.getMessage(), "Driver não encontrado!", 0);

	}

	after() throwing (NullPointerException ex): NPExceptions(){
		JOptionPane.showMessageDialog(null, ex.getMessage(), "Elemento especificado nulo", 0);

	}

	after() throwing (UnsupportedOperationException ex): UOExceptions(){
		JOptionPane.showMessageDialog(null, ex.getMessage(), "Operação de remoção não é suportada", 0);

	}

	after() throwing (IllegalArgumentException ex): IAExceptions(){
		JOptionPane.showMessageDialog(null, ex.getMessage(), "Elemento especificado não suportado", 0);

	}

	// around advises
	Object around(ConexaoComercio connection) 
	: call (public void ConexaoComercio.*(..) )
	&& target(connection)

	{
		long startTime = System.nanoTime();
		Object ret = proceed(connection);
		System.out.println(
				"Operation " + thisJoinPoint + " on " + connection + " took " + (System.nanoTime() - startTime));
		return ret;
	}
	
	

	// Constructors exceptions

	public pointcut CNFException(): execution( public ConexaoComercio.new(..) throws ClassNotFoundException);

	public pointcut SQLExceptionExeption(): execution( public ConexaoComercio.new(..) throws SQLException);

	// Constructors advises
	after() throwing (SQLException sql): SQLExceptionExeption(){
		JOptionPane.showMessageDialog(null, sql.getMessage(), "Falha MySQL ocorrida", 0);
	}

	after() throwing (ClassNotFoundException cnfe): CNFException(){
		JOptionPane.showMessageDialog(null, cnfe.getMessage(), "Driver não encontrado!", 0);

	}

}
