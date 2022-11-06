
import java.net.InetAddress;

public class Jogador {
    private String nickname;
    private int idSala;
    private Item inventario[];
    private InetAddress IPAddress;
    private int receivePort;

    public Jogador(final String nickname, final InetAddress IPAddress, final int receivePort) {
        this.nickname = nickname;
        this.IPAddress = IPAddress;
        this.receivePort = receivePort;
        setIdSala(0);
    }

    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public InetAddress getIPAddress() {
        return IPAddress;
    }

    public void setIPAddress(final InetAddress iPAddress) {
        IPAddress = iPAddress;
    }

    public int getReceivePort() {
        return receivePort;
    }

    public void setReceivePort(final int receivePort) {
        this.receivePort = receivePort;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(final String nickname) {
        this.nickname = nickname;
    }
    public Item[] getInventario() {
        return inventario;
    }

    public void setInventario(final Item[] inventario) {
        this.inventario = inventario;
    }
    public String listaInvetario(){
        String resposta = "";
        if(getInventario()==null ) return "\nInventario vazio";
        if(getInventario().length==0 ) return "\nInventario vazio";

        for(int i = 0; i <getInventario().length;i++ ){
            resposta += "\nItem -> " + getInventario()[i].getNome();
        }
        return "\n" + resposta;
    }

    
}
