package principal;

import java.util.Scanner;

public class CallTest {

	public static void main(String[] args) {
		
		/*Principal*/
		
		/* Leitura dos arquivos */
		Professor.desserialization();
//		Diretor.desserialization();
		Aluno.desserialization();
//		Sala.desserialization();
//		Disciplina.desserialization();
		/* Leitura dos arquivos */
		
		System.out.println("Desserialização completa!");
		
		Scanner leitorSelection = new Scanner(System.in);

		/* Seleção */
		int userSelect;
		/* Condição de parada While */
		int rep=0;
		
		InterfaceGrafica.welcome();
		
		/* Operações */
		while(rep == 0) {
			
			InterfaceGrafica.operations();
			
			userSelect = leitorSelection.nextInt();
			
			OperationsTest operacao = new OperationsTest(userSelect);
			
			operacao.executar();
			
		}
		
		
			
		/* Salvamento dos arquivos */
		Professor.serialization();
//		Diretor.serialization();
		Aluno.serialization();
//		Sala.serialization();
//		Disciplina.serialization();
		/* Salvamento dos arquivos */
		
		System.out.println("Serialização completa!");
		
		InterfaceGrafica.end();
		
	}
	
}
