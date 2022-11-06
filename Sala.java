
public class Sala {
    private int idSala;
    private Item items[];
    private Item portas[];

    public Sala(int id, Item portas[], int items[]) {

    }

    public Item[] getPortas() {
        return portas;
    }

    public void setPortas(Item portas[]) {
        this.portas = portas;
    }

    public Item[] getItems() {
        return items;
    }

    public void setItems(Item items[]) {
        this.items = items;
    }


}