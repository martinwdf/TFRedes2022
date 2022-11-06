import java.util.ArrayList;
import java.util.List;

public class Sala {
    private int idSala;
    List<Item> items = new ArrayList<>();
    private Porta portas[];

    public Sala(int id, Porta portas[], List<Item> items) {
        this.setIdSala(id);
        this.setPortas(portas);
        this.setItems(items);
    }


    public int getIdSala() {
        return idSala;
    }

    public void setIdSala(int idSala) {
        this.idSala = idSala;
    }

    public Porta[] getPortas() {
        return portas;
    }

    public void setPortas(Porta portas[]) {
        this.portas = portas;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
    public boolean adicionaItemSala(Item item){
        return true;
    }
    public Item removeItemSala(Item item){
        return item;
    }
    public String listaPortasAdjacentes(){
        String salasAdjacentes = "\nSalas adjacentes:\n";
        //adiciona na string as salas adjacentes
        for(int i=0; i< portas.length; i++){
            salasAdjacentes += "\tSala " + getPortas()[i].getSalaDestino() + " na direcao " + getPortas()[i].getDirecao() +"\n";
        }
        System.out.println("Cheguei aqui");
        System.out.println(salasAdjacentes);
        return salasAdjacentes;
    }
    public String listaObjetos(){
        String objetos ="";
        if(getItems() == null)return "Nenhum item disponivel na sala";

        if (getItems().size() > 0) {
           objetos = "Objetos Disponiveis:\n";
           for(int i=0; i< getItems().size(); i++){
            objetos += "\tObjeto " + items.get(i).getNome() + "\n";
        }
        } else {
            objetos = "Nenhum objeto disponivel na sala";
        }
        return objetos;
    }
    public int caminhoPossivel(String direcao){
        System.out.println("Cheguei aqui");
        for(int i=0; i< portas.length; i++){
            System.out.println("direcao " + Character.toString(getPortas()[i].getDirecao()));
            System.out.println("fechada " +getPortas()[i].getFechada());
            if(Character.toString(getPortas()[i].getDirecao()).equals(direcao) && getPortas()[i].getFechada() == false)return getPortas()[i].getSalaDestino();
        }
        return -1;
    }
    public boolean removeItem(Item item){
        return getItems().remove(item);
    }
    public boolean addItem(Item item){
        return getItems().add(item);
    }

}