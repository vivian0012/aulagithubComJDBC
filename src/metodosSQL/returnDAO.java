package metodosSQL;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.Scanner;

import BD.BDIntegridadeExeption;
import BancoDados.Registro;

public class returnDAO {

	// Criando uma dependencia de conexção na nossa classe
	private Connection connection = null;

	public returnDAO(Connection connection) {
		this.connection = connection;
	}

	// COLOCAR NOVOS REGISTROS DE DADOS
	public String updateData(String nomeProduto, String nomePessoa, Integer qtd, Double Valor, Integer integer) {

		PreparedStatement ps = null;

		try {
			LocalDateTime date = LocalDateTime.now();
			java.sql.Date sqlDate = java.sql.Date.valueOf(date.toLocalDate());

			ps = connection.prepareStatement("INSERT INTO registro"
					+ "(NomeProduto, NomePessoa, DataLocal, Quantidade, ValorBase, DepartamentoID)" + "VALUES"
					+ "(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS);

			// Inserindo o valor no ?
			ps.setString(1, nomeProduto);
			ps.setString(2, nomePessoa);
			ps.setDate(3, sqlDate);
			ps.setInt(4, qtd);
			ps.setDouble(5, Valor);
			ps.setInt(6, integer);

			// Mostrar q quantidade de linhas que foi alterado para o usuário.

			int linhasAfetadas = ps.executeUpdate();

			if (linhasAfetadas > 0) {
				System.out.println("Número de linhas: " + linhasAfetadas);

				// Mostrar o ID que foi alterado
				ResultSet rs = ps.getGeneratedKeys();
				while (rs.next()) {
					int id = rs.getInt(1);
					System.out.println("Id == " + id + " ALTERADO");
				}

			} else {
				System.out.println("Nenhuma linha alterada.");

			}

		} catch (SQLException e) {
			System.out.println("ERRO AO ATUALIZAR OS DADOS: " + e.getMessage());
		} finally {
			BD.bdConfig.closeStatement(ps);
		}

		return null;

	}

	// DELETANDO DADOS
	public Boolean DelClient(Integer id) {

		String sql = "DELETE FROM registro WHERE Id = ?";
		PreparedStatement pr = null;
		try {
			pr = connection.prepareStatement(sql);
			pr.setInt(1, id);

			int linhasAfetadas = pr.executeUpdate();
			System.out.println("Linhas afetadas: " + linhasAfetadas);
			return true;
		}

		catch (SQLException e) {
			throw new BDIntegridadeExeption("ERRO AO EXECUTAR O DELETE!" + "MENSAGEM DE ERRO: " + e.getMessage());
		} finally {
			BD.bdConfig.closeStatement(pr);

		}
	}

	// MOSTRANDO TABELA DO SQL
	public Boolean MostraTabela() {
		Scanner sc = new Scanner(System.in);
		System.out.println("TABELAS: ");
		System.out.println("1 - DEPARTAMENTOS\n2 - REGISTROS");
		System.out.print("Qual opção deseja (1/2): ");
		System.out.print(" ");
		int choice = sc.nextInt();

		Statement comando = null;
		ResultSet resultado = null;
		String departamento = "select * from departamento";
		String registro = "select * from registro";

		try {
			comando = connection.createStatement();

			if (choice == 1) {
				resultado = comando.executeQuery(departamento);
				System.out.println(" ");
				System.out.printf("%-3s | %-15s%n", "Id", "Departamentos");
				while (resultado.next()) {
					System.out.printf("%-3d | %-15s%n", resultado.getInt("Id"), resultado.getString("Nomes"));
				}

			} else {
				resultado = comando.executeQuery(registro);
				System.out.println(" ");
				System.out.printf("%-3s | %-20s | %-15s | %-12s | %-10s | %-10s | %-15s%n", "ID", "NomeProduto",
						"NomePessoa", "DataLocal", "Quantidade", "ValorBase", "DepartamentoID");

				while (resultado.next()) {
					System.out.printf("%-3d | %-20s | %-15s | %-12s | %-10d | %-10.2f | %-15d%n",
							resultado.getInt("Id"), resultado.getString("NomeProduto"),
							resultado.getString("NomePessoa"), resultado.getDate("DataLocal"),
							resultado.getInt("Quantidade"), resultado.getDouble("ValorBase"),
							resultado.getInt("DepartamentoID"));
				}
			}
			return true;

		} catch (SQLException e) {
			throw new BDIntegridadeExeption("ERRO AO EXIBIR A TABELA!" + "MENSAGEM DE ERRO: " + e.getMessage());
		} finally {
			BD.bdConfig.closeResultSet(resultado);
			BD.bdConfig.closeStatement(comando);
		}

	}

	// PEGANDO O DEPARTAMENTO
	public Boolean DepartamentoShow() {
		Statement comando = null;
		ResultSet resultado = null;
		String departamento = "select * from departamento";
		
		try {
			comando = connection.createStatement();
			
			resultado = comando.executeQuery(departamento);
			System.out.println("ESCOLHA O DEPARTAMENTO ABAIXO DE ACORDO COM NÚMERO DO ID (APENAS NÚMEROS)");
			System.out.println(" ");
			System.out.printf("%-3s | %-15s%n", "Id", "Departamentos");
			while (resultado.next()) {
				 System.out.printf("%-3d | %-15s%n",
			                resultado.getInt("Id"),
			                resultado.getString("Nomes"));
		}
			
		return true;
			
	} catch(SQLException e) {
		throw new BDIntegridadeExeption("ERRO AO EXIBIR A TABELA!" + "MENSAGEM DE ERRO: " + e.getMessage());
	} 
		finally {
		BD.bdConfig.closeResultSet(resultado);
		BD.bdConfig.closeStatement(comando);
	}
	}
	
	// PEGANDO O REGISTRO
	public Boolean RegistroShow() {
		Statement comando = null;
		ResultSet resultado = null;
		String registro = "select * from registro";
		try {
			comando = connection.createStatement();
			resultado = comando.executeQuery(registro);
			System.out.println(" ");
			System.out.printf("%-3s | %-20s | %-15s | %-12s | %-10s | %-10s | %-15s%n", "ID", "NomeProduto",
					"NomePessoa", "DataLocal", "Quantidade", "ValorBase", "DepartamentoID");

			while (resultado.next()) {
				System.out.printf("%-3d | %-20s | %-15s | %-12s | %-10d | %-10.2f | %-15d%n",
						resultado.getInt("Id"), resultado.getString("NomeProduto"),
						resultado.getString("NomePessoa"), resultado.getDate("DataLocal"),
						resultado.getInt("Quantidade"), resultado.getDouble("ValorBase"),
						resultado.getInt("DepartamentoID"));
			}
			return true;
		} 
		catch (SQLException e) {
			throw new BDIntegridadeExeption("ERRO AO EXIBIR A TABELA!" + "MENSAGEM DE ERRO: " + e.getMessage());
		} finally {
			BD.bdConfig.closeResultSet(resultado);
			BD.bdConfig.closeStatement(comando);
		}
	}
}
