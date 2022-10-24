// Recebe um pacote de algum cliente
// Separa o dado, o endereco IP e a porta deste cliente
// Imprime o dado na tela

import java.io.*;
import java.net.*;

class UDPServer {

   public static void main(String args[])  throws Exception
      {
         if (args.length < 1) {
            System.out.println("Usage: java UDPServer <port>");
            return;
         }

         int port = Integer.parseInt(args[0]);

         // cria socket do servidor com a porta 9876
         DatagramSocket serverSocket = new DatagramSocket(port);

            byte[] receiveData = new byte[1024];
            while(true)
            {
                  byte[] response = new byte[1024];
                  // declara o pacote a ser recebido
                  DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

                  // recebe o pacote do cliente
                  serverSocket.receive(receivePacket);

                  // pega os dados, o endereco IP e a porta do cliente
                  // para poder mandar a msg de volta
                  String sentence = new String(receivePacket.getData());
                  InetAddress IPAddress = receivePacket.getAddress();

                  int receivePort = receivePacket.getPort();
                  // Lógica para criação das salas (Sala sendo uma classe?)
                     //Salas são estáticas, ou seja, o estado inicial sempre será o mesmo
                  // Lógica para receber o ID  do jogador, jogador que vai passar o ID único deve retornar se o ID está disponível ou não
                     // Criar lista para armazenar os ID dos jogadores
                  // Lógica para identificar em qual sala o jogador se encontra no momento atual

                  
                  String message = "";
                  
                  // Lógica 
                  switch (sentence.trim()) {
                     case "Ajuda":
                        DatagramPacket sendPacket = new DatagramPacket(response, response.length, IPAddress, receivePort);
                        serverSocket.send(sendPacket);
                        break;
                     case "N":
                        DatagramPacket sendMessageNorth = new DatagramPacket(response, response.length, IPAddress, receivePort);
                        serverSocket.send(sendMessageNorth);
                        break;
                     default:
                        break;
                  }
                  
                  
                  
                  
                  
                  
                  System.out.println("Mensagem recebida: " + sentence);
                  if(!sentence.trim().equals("FIM")){
                  // cria pacote com o dado, o endereco do server e porta do servidor
                  DatagramPacket sendPacket = new DatagramPacket(receiveData, receiveData.length, IPAddress, receivePort);
                  serverSocket.send(sendPacket);
                  }
                  else {
                     System.out.println("Encerrando o servidor...");
                     System.exit(0);
                     serverSocket.close();
                     break;
                  }
               }
      }
}
