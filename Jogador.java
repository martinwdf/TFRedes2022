
import java.net.InetAddress;

public class Jogador {
    String nickname;
    Sala sala;
    int idSala;
    Item inventario[];
    InetAddress IPAddress;
    int receivePort;

    public Jogador(final String nickname, final InetAddress IPAddress, final int receivePort) {
        this.nickname = nickname;
        this.IPAddress = IPAddress;
        this.receivePort = receivePort;
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

    public Sala getSala() {
        return sala;
    }

    public void setSala(final Sala sala) {
        this.sala = sala;
    }

    public Item[] getInventario() {
        return inventario;
    }

    public void setInventario(final Item[] inventario) {
        this.inventario = inventario;
    }

    
}
