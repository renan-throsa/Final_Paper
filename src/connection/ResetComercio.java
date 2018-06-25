package connection;

import java.sql.SQLException;
import java.sql.Statement;

public class ResetComercio {

	private ConexaoComercio cc;

	public ResetComercio() throws ClassNotFoundException, SQLException {
		cc = new ConexaoComercio();
	}

	public void resetar() throws SQLException {
		Statement stm = cc.getConexao().createStatement();
		
		System.out.println("Resetando database comercio");
		stm.execute("DROP DATABASE IF EXISTS comercio;");
		
		System.out.println("Recriando database comercio");
				stm.execute("CREATE DATABASE comercio;");
				
				System.out.println("Criando tabela CATEGORIA");
				stm.execute("USE comercio;"); 
				System.out.println("Resetando Tabela comercio"); 
				stm.execute("CREATE TABLE CATEGORIA (" + 
				"  CODIGO INT NOT NULL AUTO_INCREMENT," + 
				"  DESCRICAO VARCHAR(50) NOT NULL," + 
				"  PRIMARY KEY (CODIGO)" + 
				") ENGINE = INNODB;"); 
				 
				System.out.println("Criando tabela PRODUTO");
				stm.execute("CREATE TABLE PRODUTO (" + 
				"  CODIGO INT NOT NULL AUTO_INCREMENT," + 
				"  DESCRICAO VARCHAR(50) NOT NULL," + 
				"  PRECO NUMERIC(15,2) NOT NULL," + 
				"  ID_CATEGORIA INT NOT NULL," + 
				"  PRIMARY KEY (CODIGO)" + 
				") ENGINE = INNODB;"); 
				 
				System.out.println("Criando tabela CLIENTE");
				stm.execute("CREATE TABLE CLIENTE (" + 
				"  CODIGO INT NOT NULL AUTO_INCREMENT," + 
				"  NOME VARCHAR(50) NOT NULL," + 
				"  CPF CHAR(11) NOT NULL," + 
				"  NASCIMENTO DATE NOT NULL," + 
				"  PRIMARY KEY (CODIGO)" + 
				") ENGINE = INNODB;"); 
				 
				System.out.println("Criando tabela PEDIDO");
				stm.execute("CREATE TABLE PEDIDO (" + 
				"  NUMERO INT NOT NULL AUTO_INCREMENT," + 
				"  DATA DATE NOT NULL," + 
				"  HORARIO TIME NOT NULL," + 
				"  ID_CLIENTE INT NOT NULL," + 
				"  STATUS CHAR(1) NOT NULL," + 
				"  PRIMARY KEY (NUMERO)" + 
				") ENGINE = INNODB;"); 
				
				System.out.println("Criando tabela ITEM");
				stm.execute("CREATE TABLE ITEM (" + 
				"  ID_PEDIDO INT NOT NULL," + 
				"  ID_PRODUTO INT NOT NULL," + 
				"  QUANTIDADE INT NOT NULL," + 
				"  UNITARIO NUMERIC(15,2) NOT NULL," + 
				"  PRIMARY KEY (ID_PEDIDO, ID_PRODUTO)" + 
				") ENGINE = INNODB;"); 
				 
				System.out.println("Criando chaves estrangeiras");
				stm.execute("ALTER TABLE PRODUTO ADD CONSTRAINT FK_PRODUTO_CATEGORIA" + 
				"  FOREIGN KEY(ID_CATEGORIA) REFERENCES CATEGORIA(CODIGO)" + 
				"  ON DELETE RESTRICT ON UPDATE RESTRICT;"); 
				 
				stm.execute("ALTER TABLE PEDIDO ADD CONSTRAINT FK_PEDIDO_CLIENTE" + 
				"  FOREIGN KEY(ID_CLIENTE) REFERENCES CLIENTE(CODIGO)" + 
				"  ON DELETE RESTRICT ON UPDATE RESTRICT;"); 
				 
				stm.execute("ALTER TABLE ITEM ADD CONSTRAINT FK_ITEM_PEDIDO" + 
				"  FOREIGN KEY(ID_PEDIDO) REFERENCES PEDIDO(NUMERO)" + 
				"  ON DELETE RESTRICT ON UPDATE RESTRICT;"); 
				 
				stm.execute("ALTER TABLE ITEM ADD CONSTRAINT FK_ITEM_PRODUTO" + 
				"  FOREIGN KEY(ID_PRODUTO) REFERENCES PRODUTO(CODIGO)" + 
				"  ON DELETE RESTRICT ON UPDATE RESTRICT;");
		 
			System.out.println("Tabela comercio resetado com sucesso.".toUpperCase());
		
		stm.close();
		cc.confirmarTransacao();
		cc.fechar();
		
	}

}
