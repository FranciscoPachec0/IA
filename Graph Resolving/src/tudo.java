import java.util.LinkedList;

class Ponto {
    int x;
    int y;
    boolean usado = false;
    int conflitos=0;
    LinkedList<Ponto> retas = new LinkedList<>();

    public Ponto(int x, int y) {
        this.x = x;
        this.y = y;
    }


    @Override
    public String toString() {
        return "(" + x + "," + y + ')';
    }
}

class Ant{
    double comprimento;
    LinkedList<Ponto> caminho;
    //LinkedList<Ponto> pontos_visitados;
}
