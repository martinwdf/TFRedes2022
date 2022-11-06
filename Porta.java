
public class Porta extends Item {
    private Boolean fechada;
    private int direcao;

    public Porta(String nome, int direcao) {
        super(nome);
        this.setFechada(true);
        this.setDirecao(direcao);
    }

    public int getDirecao() {
        return direcao;
    }

    public void setDirecao(int direcao) {
        this.direcao = direcao;
    }

    public Boolean getFechada() {
        return fechada;
    }

    public void setFechada(Boolean fechada) {
        this.fechada = fechada;
    }

}