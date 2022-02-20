import java.util.LinkedList;

public class P6 {
    public static boolean visitado(int i){

        return true;
    }

    public static LinkedList<Ponto> gera_caminho_aleatorio(LinkedList<Ponto> lista){
        //System.out.println("Lista: " + lista);
        //P2_1.gerarpermutacao(lista);
        java.util.Collections.shuffle(lista);
        //System.out.println("Caminho Formiga: " + lista);

        return lista;
    }
}
