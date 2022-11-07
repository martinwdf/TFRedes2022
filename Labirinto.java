import java.util.ArrayList;
import java.util.List;


public class Labirinto {
    private Sala salas[];

    public Labirinto() {
        // Cria sala id = 0
            // 2 portas abertas, sentidos leste e sul
        Porta portaSala0DirecaoLeste = new Porta("portaSala0DirecaoLeste", 'L', false, 1);
        Porta portaSala0DirecaoSul = new Porta("portaSala0DirecaoSul", 'S', false, 2);
        Porta portasSala0[] = {portaSala0DirecaoLeste, portaSala0DirecaoSul};
        List<Item> itemsSala0 = new ArrayList<Item>();
        Sala sala0 = new Sala(0, portasSala0, itemsSala0);
        
        // Criar sala id = 1
            // 1 porta sul fechada, 1 porta oeste aberta
            // 2 chaves na sala
        Porta portaSala1DirecaoSul = new Porta("portaSala1DirecaoSul", 'S', true, 3);
        Porta portaSala1DirecaoOeste = new Porta("portaSala1DirecaoOeste", 'O', false, 0);
        List<Item> itemsSala1 = new ArrayList<Item>();
        Chave chave0Sala1 = new Chave("Chave");
        Chave chave1Sala1 = new Chave("Chave");
        itemsSala1.add(chave0Sala1);
        itemsSala1.add(chave1Sala1);
        Porta portasSala1[] = {portaSala1DirecaoSul, portaSala1DirecaoOeste};
        Sala sala1 = new Sala(1, portasSala1, itemsSala1);
        
        //Criar Sala id =2
        // 1 porta Leste aberta, 1 porta sul fechada, 1 porta norte aberta
        Porta portaSala2DirecaoSul =  new Porta("portaSala2DirecaoSul", 'S', true, 4);
        Porta portaSala2DirecaoLeste =  new Porta("portaSala2DirecaoLeste", 'L', false, 3);
        Porta portaSala2DirecaoNorte =  new Porta("portaSala2DirecaoNorte", 'N', false, 0);
        Porta portasSala2[] = {portaSala2DirecaoSul, portaSala2DirecaoLeste, portaSala2DirecaoNorte};
        List<Item> itemsSala2 = new ArrayList<Item>();
        Sala sala2 = new Sala(2, portasSala2, itemsSala2);
        
        // Criar sala id =3
        // 1 porta direcao Oeste fechada, 1 porta direcao norte aberta
        // 2 chaves do tesouro na sala
        Porta portaSala3DirecaoOeste = new Porta("portaSala3DirecaoOeste", 'O', false, 2);
        Porta portaSala3DirecaoNorte = new Porta("portaSala3DirecaoNorte", 'N', false, 1);
        Porta portasSala3[] = {portaSala3DirecaoOeste, portaSala3DirecaoNorte};
        List<Item> itemsSala3 = new ArrayList<Item>();
        ChaveTesouro chaveTesouro0 = new ChaveTesouro("Chave_Tesouro");        
        ChaveTesouro chaveTesouro1 = new ChaveTesouro("Chave_Tesouro");     
        itemsSala3.add(chaveTesouro0);   
        itemsSala3.add(chaveTesouro1);   
        Sala sala3 = new Sala(3, portasSala3, itemsSala3);
        
        // Criar sala id = 4
        //1 porta direcao norte aberta
        // 1 Tesouro
        Porta portaSala4DirecaoNorte = new Porta("portaSala4DirecaoNorte", 'N', true, 2);
        Porta portasSala4[] = {portaSala4DirecaoNorte};
        List<Item> itemsSala4 = new ArrayList<Item>();
        Tesouro tesouro = new Tesouro("Tesouro");
        itemsSala4.add(tesouro);
        Sala sala4 = new Sala(4, portasSala4, itemsSala4);

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