package testes_Cliente;

import static org.junit.Assert.assertEquals;

import java.sql.SQLException;
import java.sql.SQLFeatureNotSupportedException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;

import aspects.Exceptions.DAOException;
import persistence.ClienteDAO;
import transference.Cliente;

public class Incluir_Cliente {

	@Test
	public void deveIncluirClienteComSucesso()
			throws SQLFeatureNotSupportedException, ClassNotFoundException, SQLException, DAOException, ParseException {

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date dataFormatada = formato.parse("16/10/1922");

		Cliente c = new Cliente(1, "Saramago", "887.452.090-57", dataFormatada);
		new ClienteDAO().incluir(c);
		Cliente inserido = new ClienteDAO().pesquisar(1);
		assertEquals("Saramago", inserido.getNome());

	}

	@Test
	public void deveIncluirClientePesistente()
			throws SQLFeatureNotSupportedException, ClassNotFoundException, SQLException, DAOException, ParseException {

		SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
		Date dataFormatada = formato.parse("09/10/1934");

		Cliente c = new Cliente(2, "Sagan", "887.452.090-57", dataFormatada);
		new ClienteDAO().incluir(c);
		Cliente inserido = new ClienteDAO().pesquisar(2);
		assertEquals("Sagan", inserido.getNome());

	}

}
