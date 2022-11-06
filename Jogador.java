
import java.net.InetAddress;
import java.util.ArrayList;
import java.util.List;

public class Jogador {
    private String nickname;
    private int idSala;
    List<Item> inventario = new ArrayList<>();
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
    public List<Item> getInventario() {
        return inventario;
    }

    public void setInventario(final List<Item> inventario) {
        this.inventario = inventario;
    }
    public String listaInvetario(){
        String resposta = "";
        if(getInventario()==null ) return "\nInventario vazio";
        if(getInventario().size()==0 ) return "\nInventario vazio";

        for(int i = 0; i <getInventario().size();i++ ){
            resposta += "\nItem -> " + getInventario().get(i).getNome();
        }
        return "\n" + resposta;
    }
    public boolean addItem(Item item){
        if(getInventario()==null){
            return inventario.add(item);
        }
        for(int i =0 ; i < getInventario().size(); i++){
            if(getInventario().get(i).getNome() == item.getNome())return false;
        }
        return inventario.add(item);
    }
    public boolean removeItem(Item item){
        if(item.getNome().trim() == "Mapa") return false;
        return getInventario().remove(item);
    }

    
}
