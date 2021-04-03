package view;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class Servidor {

	public static void main(String[] args) throws Exception {
		String clienteSentenca;
		String sentencaCaptura;
		
		// cria socket de comunica��o com os clientes na porta 6789
		ServerSocket bemVindoSocket = new ServerSocket (6789);
		
		// espera mensagem de algum cliente e trata
		while(true) {
			//espera a conex�o de algum cliente
			Socket conexaoSocket = bemVindoSocket.accept();
			
			//cria strem de entra e saida com cliente que chegou
			BufferedReader cadeiaCliente = new BufferedReader(new InputStreamReader(conexaoSocket.getInputStream()));
			DataOutputStream servidorParaCliente = new DataOutputStream(conexaoSocket.getOutputStream());
			
			// l� uma linha do cliente
			clienteSentenca = cadeiaCliente.readLine();
			
			// transoforma a linha em maiusculas
			sentencaCaptura = clienteSentenca.toUpperCase() + "\n";
			servidorParaCliente.writeBytes(sentencaCaptura);
		}
		
	}
	
}
