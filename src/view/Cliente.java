package view;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.Socket;

public class Cliente {

	public static void main(String[] args) throws Exception {
		String textoEnviado;
		String textoServidor;
		int status = 0;
		BancoDados bd = new BancoDados();  // realiza conexao
		
		// cria o strem do teclado
		BufferedReader cadeiaUsuario = new BufferedReader(new InputStreamReader(System.in)); 
		
		//cria o socket de acesso ao server hostname na porta 6789
		Socket clienteSocket = new Socket("127.0.0.1", 6789);
		
		// cria os stream (encadeamentos) de entrada e saida com o servidor
		DataOutputStream clienteParaServidor = new DataOutputStream(clienteSocket.getOutputStream());
		BufferedReader cadeiaServidor = new BufferedReader(new InputStreamReader(clienteSocket.getInputStream()));
		
		while(true) {	
			// lê uma linha do teclado e coloca em sentença
			System.out.print("Cliente : ");
			textoEnviado = cadeiaUsuario.readLine();
			bd.guardaMsg("INSERT INTO SOCKET (SK_MENSAGEM)" + "VALUES ('Cliente: "+textoEnviado +"')");

			// envia a linha para o server
			clienteParaServidor.writeBytes(textoEnviado + "\n");
			
			// lê uma linha do server
			textoServidor = cadeiaServidor.readLine();
			
			// apresenta a linha do server no console
			System.out.println("Do servidor: " + textoServidor);
			
			//envia de retorno do server pra banco de dados
			bd.guardaMsg("INSERT INTO SOCKET (SK_MENSAGEM)" + "VALUES ('Servidor: "+textoServidor +"')");
	

		}
	}

}
