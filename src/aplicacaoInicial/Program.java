package aplicacaoInicial;

import java.sql.Connection;
import java.util.Date;
import java.util.Locale;
import java.util.Scanner;

import BD.bdConfig;
import BancoDados.Departamento;
import BancoDados.Registro;
import metodosSQL.returnDAO;

public class Program {
	public static void main(String[] args) {

		RegistrosMain();

	}

	public static void RegistrosMain() {
		Locale.setDefault(Locale.US);

		Departamento dep = new Departamento();
		Connection connection = BD.bdConfig.getConnection();

		returnDAO retorno = new returnDAO(connection);

		Scanner sc = new Scanner(System.in);

		while (true) {
			System.out.println("BEM VINDO AO SISTEMA DE CADASTRAMENTO DO SUPER-MARKET");
			System.out.println("");
			System.out.println("ESCOLHA UMA OPÇÃO: ");
			System.out.println("1 - Consultar registros OU departamentos");
			System.out.println("2 - Adicionar registros");
			System.out.println("3 - Deletar registros");
			System.out.print("4 - SAIR");
			int choice = sc.nextInt();
			System.out.println(" ");

			// MOSTRAR TABELA
			if (choice == 1) {

				retorno.MostraTabela();
				System.out.println("");
				System.out.print("VOLTAR? \n1 - SIM / 2 - NÃO");
				System.out.print(" ");
				int yesrOrno = sc.nextInt();

				if (yesrOrno == 1) {
					continue;
				} else {
					System.out.println("PROGRAMA FECHADO");
					bdConfig.closeConnection();
					break;
				}

				// ADICIONAR REGISTROS
			} else if (choice == 2) {
				System.out.println("REGISTRO DE PRODUTOS");
				System.out.println(" ");
				System.out.print("Nome do produto: ");
				String scRegistro1 = sc.next();
				sc.nextLine();

				System.out.print("Nome de quem quer registrar: ");
				String scRegistro2 = sc.next();
				sc.nextLine();

				System.out.print("Quantidade do produto: ");
				Integer scRegistro3 = sc.nextInt();
				sc.nextLine();

				System.out.print("Valor do produto: R$");
				Double scRegistro4 = sc.nextDouble();
				sc.nextLine();

				retorno.DepartamentoShow();

				Integer scRegistro5 = sc.nextInt();
				sc.nextLine();

				dep.setId(scRegistro5);

				retorno.updateData(scRegistro1, scRegistro2, scRegistro3, scRegistro4, dep.getId());

				System.out.print("VOLTAR? \n1 - SIM / 2 - NÃO");
				System.out.print(" ");
				int yesrOrno = sc.nextInt();

				if (yesrOrno == 1) {
					continue;
				} else {
					System.out.println("PROGRAMA FECHADO");
					bdConfig.closeConnection();
					break;
				}
			}
			// EXCLUINDO TABELA
			else if (choice == 3) {
				retorno.RegistroShow();
				System.out.println("");
				System.out.print("DE ACORDO COM O ID, QUAL DESEJA EXCLUIR? (NÚMEROS)");
				int escolha = sc.nextInt();
				retorno.DelClient(escolha);
			} else {
				System.out.println("PROGRAMA FECHADO");
				bdConfig.closeConnection();
				break;
			}

		}
	}

}
