
// Le uma linha do teclado
// Envia o pacote (linha digitada) ao servidor

import java.io.*; // classes para input e output streams e
import java.net.*;// DatagramaSocket,InetAddress,DatagramaPacket

class UDPClient {
   public static void main(String args[]) throws Exception
   {
      if (args.length < 2) {
         System.out.println("Usage: java UDPClient <server_ip> <port>");
         return;
      }

      String serverAddr = args[0];
      int port = Integer.parseInt(args[1]);

      // cria o stream do teclado
      BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

      // declara socket cliente
      DatagramSocket clientSocket = new DatagramSocket();

      // obtem endereco IP do servidor com o DNS
      InetAddress IPAddress = InetAddress.getByName(serverAddr);

      byte[] sendData = new byte[1024];
      byte[] receiveData = new byte[1024];

      System.out.println("Por favor informe o ID do jogador em valor numérico:");
      // le uma linha do teclado
      String sentence = inFromUser.readLine();
      sendData = sentence.getBytes();

      // cria pacote com o dado, o endereco do server e porta do servidor
      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);

      //envia o pacote
      clientSocket.send(sendPacket);
      while(true){

         // declara o pacote a ser recebido
         DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
         
         // recebe o pacote do cliente
         clientSocket.receive(receivePacket);
         String sentenceReceived = new String(receivePacket.getData());
         System.out.println("Mensagem recebida do servidor: " + sentenceReceived);
         // fecha o cliente
         if(sentenceReceived == "FIM") break;
      }
         clientSocket.close();
   }
}
