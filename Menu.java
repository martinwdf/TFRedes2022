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
    private Labirinto labirinto;
    DatagramSocket serverSocket;
    HashMap<String, Jogador> jogadores;

    public Menu(HashMap<String, Jogador> jogadores, DatagramSocket serverSocket) {
        this.setGameOver(false);
        this.jogadores = jogadores;
        this.receiveData = new byte[1024];
        this.resposta = new byte[4096];
        this.serverSocket = serverSocket;
        this.respostaCliente = "";
        labirinto = new Labirinto();

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

            if(command.length > 3) Arrays.fill(command, "Ajuda");
            String comandoAtual = command[0].trim();
            switch (comandoAtual) {
                case "Examinar":
                    respostaCliente = Examinar(command, jogadorAtual);
                    enviaMensagemRespostaTodosJogadores(respostaCliente);
                    break;
                case "Mover":
                    mover(command, jogadorAtual);
                    break;
                case "Inventario":
                    inventario(jogadorAtual);
                    break;
                case "Ajuda":
                    ajuda(jogadorAtual);
                    break;
                default:
                    ajuda(jogadorAtual);
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


    public String Examinar(String[] command, Jogador jogadorAtual){
        respostaCliente ="";
        if(command.length != 2){
            respostaCliente = "Formato do comando errado, formato correto: Examinar [Sala/Objeto]";
        }
        else if(command[1].trim().equals("Sala")){
            int salaId = jogadorAtual.getIdSala();
            String jogadoresNaSala = "";
            int countJogadores = 0;
            for (Map.Entry<String, Jogador> set : jogadores.entrySet()) {
                System.out.println(set.getKey()+ " -> "+ set.getValue().getNickname());
                if(salaId == set.getValue().getIdSala() && set.getValue() != jogadorAtual){
                    jogadoresNaSala += "\nJogador: " + set.getValue().getNickname() + " esta na sala"; 
                    countJogadores++;
                }
            }
            if(countJogadores==0){
                jogadoresNaSala = "\nSo voce esta na sala";
            }
            else{
                jogadoresNaSala = "Jogadores na mesma sala: \n" + jogadoresNaSala;
            }
            Sala salaAtual = labirinto.getSalas()[salaId];
            respostaCliente = salaAtual.listaPortasAdjacentes() + "\n" +salaAtual.listaObjetos()+ "\n" + jogadoresNaSala;
        }
        
        return respostaCliente;
    }
    public void mover(String[] command, Jogador jogadorAtual) throws IOException {
        respostaCliente ="";
        int salaId = jogadorAtual.getIdSala();
        int salaDestino = -1;
        String direcao = command[1].trim();
        salaDestino = labirinto.getSalas()[salaId].caminhoPossivel(direcao);
        if(salaDestino==-1){
            respostaCliente = "Jogador " + jogadorAtual.getNickname() + " Tentou se mover da sala " + jogadorAtual.getIdSala() + " para a direcao " + direcao + ", caminho não existe ou porta esta trancada";
            enviaMensagemRespostaJogadorEspecico(respostaCliente, jogadorAtual);
        }else{
            respostaCliente = "Jogador " + jogadorAtual.getNickname() + " Se moveu da sala " + jogadorAtual.getIdSala() + " para a sala " + salaDestino;
            jogadorAtual.setIdSala(salaDestino);
            System.out.println("Nova sala " + jogadorAtual.getIdSala());
            enviaMensagemRespostaTodosJogadores(respostaCliente);
        }
    }
    public void inventario(Jogador jogadorAtual) throws IOException {
        respostaCliente = jogadorAtual.listaInvetario();
        enviaMensagemRespostaJogadorEspecico(respostaCliente, jogadorAtual);
    }

    public void ajuda(Jogador jogadorAtual) throws IOException {
        respostaCliente = "\nExaminar [sala/objeto]\n\to Retorna a descrição da sala atual (sala) ou objeto mencionado.\n\to A descrição da sala também deve listar as salas adjacentes e suas\n\trespectivas direções, objetos e demais jogadores presentes no local.\n Mover [N/S/L/O]\n\to O jogador deve mover-se para a direção indicada (norte, sul, leste ou oeste).\n\to Ao entrar numa nova sala, o jogo deve executar automaticamente\n\to comando “examinar sala”, que descreve o novo ambiente ao jogador.\n Pegar [objeto]\n\to O jogador coleta um objeto que está na sala atual.\n\to Alguns objetos não podem ser coletados, como no caso de “porta”.\n Largar [objeto]\n\to O jogador larga um objeto que está no seu inventório, na sala atual.\n Inventário\n\to O jogo lista todos os objetos carregados atualmente pelo jogador. \n Usar [objeto] {alvo}\n\to O jogador usa o objeto mencionado;\n\to Em alguns casos específicos, o objeto indicado necessitará de outro\n\t(alvo) para ser ativado (ex: usar chave porta).\n Falar [texto]\n\to O jogador envia um texto que será retransmitido para todos os jogadores presentes na sala atual.\n Cochichar [texto] [jogador]\n\to O jogador envia uma mensagem de texto apenas para o jogador especificado, se ambos estiverem na mesma sala.\n Ajuda\n\to Lista todos os comandos possíveis do jogo.";
        enviaMensagemRespostaJogadorEspecico(respostaCliente, jogadorAtual);
    }
}
