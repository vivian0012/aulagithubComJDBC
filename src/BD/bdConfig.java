package BD;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public class bdConfig {

	private static Connection connection = null;

	// CONECTAR-SE AO BANCO DE DADOS
	public static Connection getConnection() {

		// Verificação para saber se temos o JDBC intalado ou na bíblioteca da pasta
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			throw new BDIntegridadeExeption("Driver JDBC não encontrado: " + e.getMessage());
		}

		// CONECXÃO COM O BANCO

		if (connection == null) {
			try {
				Properties properties = loadProperties(); // Carregando o SQL
				String url = properties.getProperty("dburl");
				connection = DriverManager.getConnection(url, properties.getProperty("user"),
						properties.getProperty("password"));
			} catch (SQLException e) {
				throw new BDIntegridadeExeption("Erro ao tentar acessar: " + e.getMessage());
			}
		}
		return connection;
	}

	// DESCONECTAR DO BANCO
	public static void closeConnection() {
		if (connection != null) {
			try {
				connection.close();
			} catch (SQLException e) {
				throw new BDIntegridadeExeption("Desconexão falhou: " + e.getMessage());
			}
		}
	}

	// CARREGAMENTO DE ARQUIVO
	private static Properties loadProperties() {

		try (FileInputStream file = new FileInputStream(
				"C:\\Users\\Vivian\\eclipse-workspace" + "\\ControleDeEstoque\\BD.properties")) {

			Properties properties = new Properties();
			properties.load(file);
			return properties;
		}

		catch (IOException e) { // exceção do java que lida com tipos de arquivos.
			throw new BDIntegridadeExeption(e.getMessage());
		}

	}

	// MÉTODOS PARA FECHAR O STATEMENT E RESULTSET
	public static void closeStatement(Statement st) {
		if (st != null) {
			try {
				st.close();
			} catch (SQLException e) {
				throw new BDIntegridadeExeption(e.getMessage());
			}
		}

	}

	public static void closeResultSet(ResultSet rs) {
		if (rs != null) {
			try {
				rs.close();
			} catch (SQLException e) {
				throw new BDIntegridadeExeption(e.getMessage());
			}
		}
	}

}
