package aspects;

import connection.ConexaoComercio;
import persistence.CategoriaDAO;
import persistence.ClienteDAO;
import persistence.ItemDAO;
import persistence.ProdutoDAO;

public aspect Implementations {
	// ----------------------------------------------------------------------------------------------------
	// Implementations related with persistence layer.
	public static interface Connectable {
		public ConexaoComercio getConnection();
	}

	private ConexaoComercio Connectable.cc;

	public ConexaoComercio Connectable.getConnection() {
		return this.cc;
	}

	declare parents: CategoriaDAO implements Connectable;
	declare parents: ClienteDAO implements Connectable;
	declare parents: ItemDAO implements Connectable;
	declare parents: ProdutoDAO implements Connectable;

	// ----------------------------------------------------------------------------------------------------
	// Implementations related with presentation layer.

}
