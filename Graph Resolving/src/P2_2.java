import java.util.LinkedList;

public class P2_2 {
    public static double calcular_distancia(Ponto p1, Ponto p2){
        double x = Math.pow((p2.x-p1.x), 2);
        double y = Math.pow((p2.y-p1.y),2);
        double distancia = Math.sqrt(x+y);

        return distancia;
    }

    public static LinkedList<Ponto> nearest_neighbour(LinkedList<Ponto> p,Ponto inicial){
        LinkedList<Ponto> ligados = new LinkedList<>();
        //int j=0;
        double distcalculada;
        Ponto temp = new Ponto(0,0);

        //System.out.println("No [" + j++ + "] = " + inicial);
        //inicial.usado = true;

        for (int k = 0; k<p.size(); k++) {
            ligados.add(inicial);
            inicial.usado = true;
            double distancia = Double.MAX_VALUE;
            for (int i = 0; i < p.size(); i++) {
                Ponto seguinte = p.get(i);
                //System.out.println("No [seguinte] = " + seguinte);
                if (!seguinte.usado) {
                    distcalculada = calcular_distancia(inicial, seguinte);
                    //System.out.println("Distancia = " + distancia);
                    //System.out.println("Distancia entre no inicial e seguinte = " + distcalculada);
                    if (distcalculada < distancia) {
                        //System.out.println("Entrou no if pois distancia = " + distancia + " > " + distcalculada + " = distcalculada");
                        distancia = distcalculada;
                        temp = seguinte;
                    }
                }
            }
            inicial = temp;
            //temp.usado = true;
            //System.out.println("No [" + j++ + "] = " + inicial);
        }
    return ligados;
    }

    public static boolean pontos_iguais(Ponto p1, Ponto p2){
        if ((p1.x == p2.x) && (p1.y == p2.y)) return true;

        return false;
    }
}
