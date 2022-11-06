import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Menu {
    private String respostaCliente;
    private Boolean gameOver;
    private byte[] receiveData;
    private byte[] resposta;
    DatagramSocket serverSocket;
    HashMap<String, Jogador> jogadores;

    public Menu(HashMap<String, Jogador> jogadores, DatagramSocket serverSocket) {
        this.setGameOver(false);
        this.jogadores = jogadores;
        this.receiveData = new byte[1024];
        this.resposta = new byte[1024];
        this.serverSocket = serverSocket;
        this.respostaCliente = "";
    }

    public Boolean getGameOver() {
        return gameOver;
    }

    public void setGameOver(Boolean gameOver) {
        this.gameOver = gameOver;
    }

    public void commands() throws IOException {
        Jogador jogadorAtual;
        while(!this.gameOver){
            Arrays.fill(receiveData, (byte)0);
            
            DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
            serverSocket.receive(receivePacket);
            String sentence = new String(receivePacket.getData());
            InetAddress IPAddress = receivePacket.getAddress();
            int receivePort = receivePacket.getPort();
            
            String[] command = sentence.split(" ");
            String key =IPAddress + ":" + receivePort;
            // se o command for irregular ou o Jogador realizar o comando ajuda
            jogadorAtual = jogadores.get(key);
            System.out.println("Jogador: " + jogadorAtual.getNickname() + " tentando o comando" + Arrays.toString(command));

            if(command.length == 1 || command.length > 3) Arrays.fill(command, "Ajuda");
            switch (command[0]) {
                case "Examinar":
                    enviaMensagemRespostaTodosJogadores("Mensagem para todos");
                    break;
                case "Mover":
                    break;
                default:
                    enviaMensagemRespostaJogadorEspecico(ajuda(), jogadorAtual);
                    break;
            }

        }
    }
    public void isGameOver(){
        setGameOver(true);
    }
    public void enviaMensagemRespostaTodosJogadores(String respostaCliente) throws IOException {
        Arrays.fill(resposta, (byte)0);
        resposta = respostaCliente.getBytes();
        //127.0.0.1 8586
        // String key = IPAddress.getHostAddress() + String.valueOf(receivePort);
        System.out.println("Tentando eviar mensagem para os jogadores");
        for (Map.Entry<String, Jogador> set : jogadores.entrySet()) {
            System.out.println(set.getKey()+ " -> "+ set.getValue().getNickname());
            DatagramPacket sendPacket = new DatagramPacket(resposta, resposta.length, set.getValue().getIPAddress(), set.getValue().getReceivePort());
            serverSocket.send(sendPacket);
         }
    }
    public void enviaMensagemRespostaJogadorEspecico(String respostaCliente, Jogador jogador) throws IOException {
        Arrays.fill(resposta, (byte)0);
        resposta = respostaCliente.getBytes();
        //127.0.0.1 8586
        // String key = IPAddress.getHostAddress() + String.valueOf(receivePort);
        DatagramPacket sendPacket = new DatagramPacket(resposta, resposta.length, jogador.getIPAddress(), jogador.getReceivePort());
        serverSocket.send(sendPacket);
    }


    public String Examinar(){
        respostaCliente = "Realizando o comando Examinar";
        return respostaCliente;
    }
    public String ajuda(){
        respostaCliente = "\nExaminar [sala/objeto]\n\to Retorna a descrição da sala atual (sala) ou objeto mencionado.\n\to A descrição da sala também deve listar as salas adjacentes e suas\n\trespectivas direções, objetos e demais jogadores presentes no local.\n Mover [N/S/L/O]\n\to O jogador deve mover-se para a direção indicada (norte, sul, leste ou oeste).\n\to Ao entrar numa nova sala, o jogo deve executar automaticamente\n\to comando “examinar sala”, que descreve o novo ambiente ao jogador.\n Pegar [objeto]\n\to O jogador coleta um objeto que está na sala atual.\n\to Alguns objetos não podem ser coletados, como no caso de “porta”.\n Largar [objeto]\n\to O jogador larga um objeto que está no seu inventório, na sala atual.\n Inventário\n\to O jogo lista todos os objetos carregados atualmente pelo jogador. \n Usar [objeto] {alvo}\n\to O jogador usa o objeto mencionado;\n\to Em alguns casos específicos, o objeto indicado necessitará de outro\n\t(alvo) para ser ativado (ex: usar chave porta).\n Falar [texto]\n\to O jogador envia um texto que será retransmitido para todos os jogadores presentes na sala atual.\n Cochichar [texto] [jogador]\n\to O jogador envia uma mensagem de texto apenas para o jogador especificado, se ambos estiverem na mesma sala.\n Ajuda\n\to Lista todos os comandos possíveis do jogo.";
        return respostaCliente;
    }
}
