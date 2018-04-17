package interfaces;

import java.sql.SQLException;

import aspects.Exceptions.DAOException;

public interface Order {
	public void verPedido()throws ClassNotFoundException, SQLException,DAOException; 
}
