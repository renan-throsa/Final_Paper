package interfaces;

import java.sql.ResultSet;
import java.sql.SQLException;

public interface Downloadable {
	
	public ResultSet loadGrid(int codigo)throws ClassNotFoundException, SQLException;
}
