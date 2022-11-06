
public class Porta extends Item {
    private Boolean fechada;
    private char direcao;
    private int salaDestino;

    public Porta(String nome, char direcao, boolean fechada, int salaDestino) {
        super(nome);
        this.setFechada(fechada);
        this.setDirecao(direcao);
        this.setSalaDestino(salaDestino);
    }

    public int getSalaDestino() {
        return salaDestino;
    }

    public void setSalaDestino(int salaDestino) {
        this.salaDestino = salaDestino;
    }

    public char getDirecao() {
        return direcao;
    }

    public void setDirecao(char direcao) {
        this.direcao = direcao;
    }

    public Boolean getFechada() {
        return fechada;
    }

    public void setFechada(Boolean fechada) {
        this.fechada = fechada;
    }

}