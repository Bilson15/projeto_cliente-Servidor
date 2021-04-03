package view;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Cliente {

	public static void main(String[] args) throws Exception {
		String sentenca;
		String sentencaModificada;
		BancoDados bd = new BancoDados();  // realiza conexao
		
		// cria o strem do teclado
		BufferedReader cadeiaUsuario = new BufferedReader(new InputStreamReader(System.in)); 
		
		//cria o socket de acesso ao server hostname na porta 6789
		Socket clienteSocket = new Socket("127.0.0.1", 6789);
		
		// cria os stream (encadeamentos) de entrada e saida com o servidor
		DataOutputStream clienteParaServidor = new DataOutputStream(clienteSocket.getOutputStream());
		BufferedReader cadeiaServidor = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
		
		// l� uma linha do teclado e coloca em senten�a
		sentenca = cadeiaUsuario.readLine();
		
		// envia a linha para o server
		clienteParaServidor.writeBytes(sentenca + "\n");
		
		// l� uma linha do server
		sentencaModificada = cadeiaServidor.readLine();
		
		// apresenta a linha do server no console
		System.out.println("Para o servidor: " + sentencaModificada);
		
		//envia de retorno do server pra banco de dados
		int status = bd.guardaMsg("INSERT INTO SOCKET (SK_NOME)" + "VALUES ('"+sentencaModificada +"')");
		if (status == 1) {
			System.out.println("Mensagem gravada no banco de dados");
		}else {
			System.out.println("Mensagem n�o foi gravada");
		}
		//fecha cliente
		clienteSocket.close();
	}

}
