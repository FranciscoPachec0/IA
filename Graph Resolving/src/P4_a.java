import java.util.LinkedList;

public class P4_a {

    public static LinkedList<Ponto> espelhar(LinkedList<Ponto> lista, int i, int k){
        LinkedList<Ponto> temp = new LinkedList<>();
        int j = 0;
        int h = k;
        while (j < i){
            //ºSystem.out.println("Adicionamos o no " + lista.get(j));
            temp.add(lista.get(j));
            j++;
        }
        while (h>=i){
            //System.out.println("Adicionamos o no invertido" + lista.get(h));
            temp.add(lista.get(h));
            h--;
        }
        k++;
        while(k<lista.size()){
            //System.out.println("Resto dos nos" + lista.get(k));
            temp.add(lista.get(k));
            k++;
        }

        return temp;
    }

    public static double perimetro(LinkedList<Ponto> lista){
        double perimetro = 0;
        for (int i = 0; i<lista.size()-1; i++){
            perimetro += P2_2.calcular_distancia(lista.get(i), lista.get(i+1));
            //System.out.println("Distancia entre os pontos " + lista.get(i) + "," + lista.get(i+1) + " = " + distancia);
            //perimetro += distancia;
        }
        return perimetro;
    }
}
