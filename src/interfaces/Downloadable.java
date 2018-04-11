package interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;

import aspects.DAOException;

public interface Downloadable {
	
	public ResultSet loadGrid(int codigo)throws ClassNotFoundException, SQLException,DAOException;
}
