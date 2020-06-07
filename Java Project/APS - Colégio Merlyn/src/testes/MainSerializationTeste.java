package testes;

import java.util.*;

public class MainSerializationTeste {

	public static void main(String[] args) {
		/*ClasseTeste1 e SubClasseTeste2*/
		/* Debugando serialization */
		
		/* Leitura de Arquivos */
		ClasseTeste1.desserialization();
		SubClasseTeste2.desserialization();
		/* Leitura de Arquivos */
		
		System.out.println("Desserialização Completa!");
				
		int rep=0;
		
		Scanner leitorInt = new Scanner(System.in);
		Scanner leitorStr = new Scanner(System.in);
		
		int select;
		
		String nome, dataNasc;
		
		while(rep == 0) {
			
			
			System.out.println("1 - Cadastrar Classe");
			System.out.println("2 - Cadastrar SubClasse");
			System.out.println("5 - Sair");
			
			select = leitorInt.nextInt();
			
			if(select == 1) {
				System.out.println("Digite nome da Classe:");
				nome = leitorStr.nextLine();
				
				System.out.println("Digite a data de nascimento da Classe:");
				dataNasc = leitorStr.nextLine();
				
				ClasseTeste1.cadastrarClasse();
				
			}else if(select == 2) {
				System.out.println("Digite nome da SubClasse:");
				nome = leitorStr.nextLine();
				
				System.out.println("Digite a data de nascimento da Classe:");
				dataNasc = leitorStr.nextLine();
				
				SubClasseTeste2.cadastrarSubClasse();
			}else if(select == 5){
				rep++;
			}else {
				System.out.println("OP não exite");
				rep++;
				break;
			}
			
		}
		
		leitorInt.close();
		leitorStr.close();
		
		/* Salvamento de Arquivos */
		ClasseTeste1.serialization();
		SubClasseTeste2.serialization();
		/* Salvamento de Arquivos */
		
		System.out.println("Serialização Completa!");
	}
	
}
