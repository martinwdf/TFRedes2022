
// Le uma linha do teclado
// Envia o pacote (linha digitada) ao servidor

import java.io.*; // classes para input e output streams e
import java.net.*;// DatagramaSocket,InetAddress,DatagramaPacket
import java.util.Arrays;
import java.util.Scanner;

class UDPClient {
   public static void main(String args[]) throws Exception {
      if (args.length < 2) {
         System.out.println("Usage: java UDPClient <server_ip> <port>");
         return;
      }

      String serverAddr = args[0];
      int port = Integer.parseInt(args[1]);
      EnviarMensagem enviarMensagem  = new EnviarMensagem();
      ReceberMensagem receberMensagem  = new ReceberMensagem();
      // declara socket cliente
      DatagramSocket clientSocket = new DatagramSocket();
      enviarMensagem.setVariaveis(port, serverAddr, clientSocket);
      receberMensagem.setVariaveis(port, serverAddr, clientSocket);
      System.out.println("Por favor faça o login com o comando: Login <nickname>:");
      byte[] sendData = new byte[1024];

      final String sentence =" in.nextLine();";
      InetAddress IPAddress = InetAddress.getByName(serverAddr);
      Arrays.fill(sendData, (byte) 0);
      // le uma linha do teclado
      sendData = sentence.getBytes();
      
      // cria pacote com o dado, o endereco do server e porta do servidor
      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
      clientSocket.send(sendPacket);
      // uma thread para receber mensagems outra para enviar
      new Thread(receberMensagem).start();
      new Thread(enviarMensagem).start();

   }
}

class EnviarMensagem implements Runnable {
   int port;
   InetAddress IPAddress;
   DatagramSocket senderSocket;
   public void setVariaveis(int port, String serverAddr,  DatagramSocket senderSocket) throws UnknownHostException {
      this.senderSocket = senderSocket;
      this.port = port;
      this.IPAddress = InetAddress.getByName(serverAddr);
   }
   @Override
   public void run() {
      final Scanner in = new Scanner(System.in);
      byte[] sendData = new byte[1024];
      try {
         while (in.hasNext()) {
            // System.out.println("Esperando comando do Jogador: ");
            final String sentence = in.nextLine();
            Arrays.fill(sendData, (byte) 0);
            // le uma linha do teclado
            sendData = sentence.getBytes();
            
            // cria pacote com o dado, o endereco do server e porta do servidor
            DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
            // envia o pacote
            senderSocket.send(sendPacket);
            try {
               Thread.sleep(2000);
            } catch (InterruptedException ex) {
               throw new IllegalStateException(ex);
            }
         }
      } catch (SocketException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      }

}
}

class ReceberMensagem implements Runnable {
   int port;
   String serverAddr;
   DatagramSocket receiverSocket;

   public void setVariaveis(int port, String serverAddr, DatagramSocket receiverSocket) throws UnknownHostException {
      this.port = port;
      this.serverAddr = serverAddr;
      this.receiverSocket = receiverSocket;
   }
   @Override
   public void run() {
      try {
         byte[] resposta = new byte[1024];
         try {
            Thread.sleep(2000);
         } catch (InterruptedException ex) {
            throw new IllegalStateException(ex);
         }
         while(true) {
            Arrays.fill(resposta, (byte) 0);
            // declara o pacote a ser recebido
            DatagramPacket receivePacket = new DatagramPacket(resposta, resposta.length);
            // recebe o pacote do Servidor
            receiverSocket.receive(receivePacket);
            String sentenceReceived = new String(receivePacket.getData());
            System.out.println("Mensagem recebida do servidor: " + sentenceReceived);
            if (sentenceReceived.trim().equals("FIM")) {
               System.out.println("Jogador x ganhou o jogo");
               receiverSocket.close();
               System.exit(0);
            }
         }
      } catch (SocketException e) {
         e.printStackTrace();
      } 
      catch (IOException e) {
         e.printStackTrace();
      }
   }
}
