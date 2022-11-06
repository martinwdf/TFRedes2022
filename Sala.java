
public class Sala {
    private int idSala;
    private Item items[];
    private Porta portas[];

    public Sala(int id, Porta portas[], Item items[]) {
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

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item items[]) {
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

        if (getItems().length > 0) {
           objetos = "Objetos Disponiveis:\n";
           for(int i=0; i< getItems().length; i++){
            objetos += "\tObjeto " + items[i].getNome() + "\n";
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
}