
public class Labirinto {
    private Sala salas[];

    public Labirinto() {
        // Cria sala id = 0
            // 2 portas abertas, sentidos leste e sul
        Porta portaSala0DirecaoLeste = new Porta("portaSala0DirecaoLeste", 'L', false, 1);
        Porta portaSala0DirecaoSul = new Porta("portaSala0DirecaoSul", 'S', false, 2);
        Porta portasSala0[] = {portaSala0DirecaoLeste, portaSala0DirecaoSul};
        Sala sala0 = new Sala(0, portasSala0, null);
        
        // Criar sala id = 1
            // 1 porta sul fechada, 1 porta oeste aberta
            // 2 chaves na sala
        Porta portaSala1DirecaoSul = new Porta("portaSala1DirecaoSul", 'S', true, 3);
        Porta portaSala1DirecaoOeste = new Porta("portaSala1DirecaoOeste", 'O', false, 0);
        Chave chave0Sala1 = new Chave("Chave");
        Chave chave1Sala1 = new Chave("Chave");
        Porta portasSala1[] = {portaSala1DirecaoSul, portaSala1DirecaoOeste};
        Item itemsSala1[] = {chave0Sala1, chave1Sala1};
        Sala sala1 = new Sala(1, portasSala1, itemsSala1);
        
        //Criar Sala id =2
        // 1 porta Leste aberta, 1 porta sul fechada, 1 porta norte aberta
        Porta portaSala2DirecaoSul =  new Porta("portaSala2DirecaoSul", 'S', true, 4);
        Porta portaSala2DirecaoLeste =  new Porta("portaSala2DirecaoLeste", 'L', true, 3);
        Porta portaSala2DirecaoNorte =  new Porta("portaSala2DirecaoNorte", 'N', true, 0);
        Porta portasSala2[] = {portaSala2DirecaoSul, portaSala2DirecaoLeste};
        Sala sala2 = new Sala(2, portasSala2, null);
        
        // Criar sala id =3
        // 1 porta direcao Oeste fechada, 1 porta direcao norte aberta
        // 2 chaves do tesouro na sala
        Porta portaSala3DirecaoOeste = new Porta("portaSala3DirecaoOeste", 'O', true, 2);
        Porta portaSala3DirecaoNorte = new Porta("portaSala3DirecaoNorte", 'N', true, 1);
        Porta portasSala3[] = {portaSala3DirecaoOeste, portaSala3DirecaoNorte};
        // TODO criar chaves do tesouro
        
        Sala sala3 = new Sala(3, portasSala3, null);
        
        // Criar sala id = 4
        //1 porta direcao norte aberta
        // 1 Tesouro
        Porta portaSala4DirecaoNorte = new Porta("portaSala4DirecaoNorte", 'N', false, 2);
        Porta portasSala4[] = {portaSala4DirecaoNorte};
        // TODO criar tesouro
        Sala sala4 = new Sala(4, portasSala4, null);

        Sala salasAux[] = {sala0, sala1, sala2, sala3, sala4};
        this.salas = salasAux;
    }

    public Sala[] getSalas() {
        return salas;
    }

    public void setSalas(Sala salas[]) {
        this.salas = salas;
    }
}