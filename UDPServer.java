// Recebe um pacote de algum cliente
// Separa o dado, o endereco IP e a porta deste cliente
// Imprime o dado na tela

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;
import java.util.Arrays;

class UDPServer {
   public static void main(String args[])  throws Exception
      {
         if (args.length < 1) {
            System.out.println("Usage: java UDPServer <port>");
            return;
         }
         int port = Integer.parseInt(args[0]);
         // cria socket do servidor com a porta fornecida como parâmetro
         DatagramSocket serverSocket = new DatagramSocket(port);
         // declara o pacote a ser recebido
         // recebe o pacote do cliente
         HashMap<String, Jogador> jogadores = new HashMap<String, Jogador>();
         // logica para criar os dois jogadores
         byte[] receiveData = new byte[1024];
         byte[] resposta = new byte[1024];
         loginInicial(serverSocket, receiveData, resposta, jogadores);
         // pega os dados, o endereco IP e a porta do cliente
         // para poder mandar a msg de volta
         // criar chave para o jogador, sendo que key = Ipaddress:receiveport
         System.out.println("Dois Jogadores com login realizados, iniciando o jogo...");
         // cria pacote com o dado, o endereco do server e porta do servidor
         for (Map.Entry<String, Jogador> set : jogadores.entrySet()) {
            System.out.println("jogadores adicionados...");
            System.out.println(set.getKey()+ " -> "+ set.getValue().getNickname());
         }
         serverSocket.close();
                  
      }
      static void loginInicial(DatagramSocket serverSocket, byte[] receiveData, byte[] resposta,
      HashMap<String, Jogador> jogadores) throws IOException {
         while(jogadores.size() != 2){
            Arrays.fill(receiveData,(byte)0);
            Arrays.fill(resposta,(byte)0);
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);

            String sentence = new String(receivePacket.getData());
            InetAddress IPAddress = receivePacket.getAddress();
            int receivePort = receivePacket.getPort();

            String[] command = sentence.split(" ");
            String respostaCliente="";
            String key =IPAddress + ":" + receivePort;
            // Checa se a maquina já está em uso e se o jogador digitou o comando de login corretamente
            if(command[0].equals("Login") && jogadores.get(key) == null && command.length == 2){
                  System.out.println("Jogador tentando o login...");
                  Boolean nickNameExiste = false;
                  // Iterating HashMap through for loop, to check if nickname already exists
                  for (Map.Entry<String, Jogador> set : jogadores.entrySet()) {
                     if(set.getValue().getNickname().equals(command[1]))nickNameExiste = true;
                  }
                  //checa se o nome que o jogador escolheu já existe
                     // Se já existe jogador não é adicionado
                  if(!nickNameExiste){
                     Jogador j =  new Jogador(command[1], IPAddress, receivePort);
                     jogadores.put(key, j);
                     respostaCliente = "Login realizado com sucesso";   
                  }
                  else {
                     System.out.println("Nickname já em uso");
                     respostaCliente = "Nickname já em uso";
                  }
            }
            else{respostaCliente = "Comando de login invalido ou máquina já tem um login adicionado";}
            
            resposta = respostaCliente.getBytes();
            //127.0.0.1 8586
            // String key = IPAddress.getHostAddress() + String.valueOf(receivePort);
            DatagramPacket sendPacket = new DatagramPacket(resposta, resposta.length, IPAddress, receivePort);
            serverSocket.send(sendPacket);
      }
}
}

