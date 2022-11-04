
import java.net.InetAddress;

public class Jogador {
    String nickname;
    Sala sala;
    Item inventario[];
    InetAddress IPAddress;
    int receivePort;

    public Jogador(String nickname, InetAddress IPAddress, int receivePort){
        this.nickname = nickname;
        this.IPAddress = IPAddress;
        this.receivePort = receivePort;
    }

    public InetAddress getIPAddress() {
        return IPAddress;
    }

    public void setIPAddress(InetAddress iPAddress) {
        IPAddress = iPAddress;
    }

    public int getReceivePort() {
        return receivePort;
    }

    public void setReceivePort(int receivePort) {
        this.receivePort = receivePort;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Sala getSala() {
        return sala;
    }

    public void setSala(Sala sala) {
        this.sala = sala;
    }

    public Item[] getInventario() {
        return inventario;
    }

    public void setInventario(Item[] inventario) {
        this.inventario = inventario;
    }

    
}