import java.util.*;

public class EncryptionProgram {
	
	// Declaração de variáveis
	private Scanner scanner;// Para ler a entrada do usuário
	private Random random;// Para gerar aleatoriedade
	private ArrayList <Character> list;// Lista de caracteres originais (chave)
	private ArrayList <Character> shuffledList;// Lista embaralhada de caracteres (chave embaralhada)
	private char character;// Variável para iterar sobre caracteres
	private String line;// Variável para armazenar uma linha de texto
	private char[] letters;// Array para armazenar caracteres de uma mensagem
	private char[] secretLetters;// Array para armazenar caracteres criptografados

	// Construtor do programa
	EncryptionProgram() {
		
		// Inicializa variáveis
		scanner = new Scanner(System.in);
		random = new Random();
		list = new ArrayList();
		shuffledList = new ArrayList();
		character = ' ';
		
		newKey();// Gera uma nova chave de criptografia
		askQuestion();// Pergunta ao usuário qual ação realizar
		
	}
	
	// Função para perguntar ao usuário qual ação ele quer realizar
	private void askQuestion() {
		while(true) {
			System.out.println("****************************************************************************");
			System.out.println("O que você quer fazer?");
			System.out.println("(N)ovaChave, (P)egarChave, (C)riptografar, (D)escriptografar, (S)air");
			char response = Character.toUpperCase(scanner.nextLine().charAt(0));// Obtém a resposta do usuário e a converte para maiúscula
			
			switch(response) {// Escolhe a ação com base na resposta do usuário
			case 'N':// Gera uma nova chave de criptografia
				newKey();
				break;
			case 'P':// Exibe a chave atual
				getKey();
				break;
			case 'C':// Criptografa uma mensagem
				encrypt();
				break;
			case 'D':// Descriptografa uma mensagem
				decrypt();
				break;
			case 'S':// Sai do programa
				quit();
				break;
			default:// Resposta inválida
				System.out.println("Resposta inválida.");
			}
		}
	}
	
	// Função para gerar uma nova chave de criptografia
	private void newKey() {
		character = ' ';// Inicia com o caractere de espaço
		list.clear();// Limpa a lista de caracteres originais
		shuffledList.clear();// Limpa a lista de caracteres embaralhados
		
		// Preenche a lista com caracteres ASCII de 32 (espaço) a 126 (~)
		for(int i=32;i<127;i++) {
			list.add(Character.valueOf(character));// Adiciona caracteres à lista
			character++;// Próximo caractere ASCII
		}
		
		// Embaralha a lista para criar a chave embaralhada
		shuffledList = new ArrayList(list);// Cria uma cópia da lista original
		Collections.shuffle(shuffledList); // Embaralha a cópia
		System.out.println("*Uma nova chave foi gerada*");// Mensagem para indicar que a chave foi gerada
	}
	
	// Função para exibir a chave atual
	private void getKey() {
		System.out.println("Chave: ");// Exibe a chave
		for(Character x : list) {// Imprime a lista de caracteres originais
			System.out.print(x);// Exibe cada caractere original
		}
		System.out.println();// Nova linha
		for(Character x : shuffledList) {// Imprime a lista de caracteres embaralhados
			System.out.print(x);// Exibe cada caractere embaralhado
		}
		System.out.println();// Nova linha
	}
	
	private void encrypt() {// Função para criptografar uma mensagem
		System.out.println("Escreva a mensagem que será criptografada: ");// Solicita a mensagem do usuário
		String message = scanner.nextLine();// Lê a mensagem do usuário
		
		letters = message.toCharArray();// Converte a mensagem para um array de caracteres
		
		// Substitui cada caractere pelo correspondente na lista embaralhada
		for(int i =0;i<letters.length;i++) {
			for(int j =0;j<list.size();j++) {
				if(letters[i]==list.get(j)) {// Encontra o caractere correspondente na lista original
					letters[i]=shuffledList.get(j);// Substitui pelo correspondente na lista embaralhada
					break;
				}
			}
		}
		System.out.println("Criptografado: ");// Exibe a mensagem criptografada
		for(char x : letters) {// Exibe cada caractere criptografado
			System.out.print(x);// Exibe o caractere
		}
		System.out.println();// Nova linha para separar a saída
	}
	
	private void decrypt() {// Função para descriptografar uma mensagem
		System.out.println("Escreva a mensagem a ser descriptografada: ");// Solicita a mensagem criptografada
		String message = scanner.nextLine();// Lê a mensagem criptografada do usuário
		
		letters = message.toCharArray();// Converte a mensagem para um array de caracteres
		
		for(int i =0;i<letters.length;i++) {
			
			// Substitui cada caractere pelo correspondente na lista original
			for(int j =0;j<shuffledList.size();j++) {
				if(letters[i]==shuffledList.get(j)) {// Encontra o caractere correspondente na lista embaralhada
					letters[i]=list.get(j);// Substitui pelo correspondente na lista original
					break;
				}
			}
		}
		System.out.println("Descriptografado: ");// Exibe a mensagem descriptografada
		for(char x : letters) {// Exibe cada caractere descriptografado
			System.out.print(x); // Exibe o caractere
		}
		System.out.println();// Nova linha para separar a saída
	}
	
	// Função para sair do programa
	private void quit() {
		System.out.println("Obrigado tenha um bom dia!");// Mensagem de despedida
		System.exit(0);// Sair do programa
	}
}
