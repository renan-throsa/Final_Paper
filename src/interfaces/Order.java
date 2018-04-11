package interfaces;

import java.sql.SQLException;

import aspects.DAOException;

public interface Order {
	public void verPedido()throws ClassNotFoundException, SQLException,DAOException; 
}
