import java.util.Random;
import java.util.Scanner;
import java.util.LinkedList;

public class P1 {

    public static void gerarlista(LinkedList<Ponto> pontos, int n, int m){
        boolean usar = true;
        Scanner in = new Scanner(System.in);
        //LinkedList<Ponto> pontos = new LinkedList<>();
        Random aleatorio = new Random();
        for (int i=0; i<n;i++){
            //System.out.println("m:" + m + " -m:" + -m);
            int x = aleatorio.nextInt((m - (-m)) + 1) + (-m);
            int y = aleatorio.nextInt((m - (-m)) + 1) + (-m);
            //System.out.println("x gerado: " + x);
            //System.out.println("y gerado: " + y);
            Ponto p = new Ponto(x,y);
            for (int k=0; k<pontos.size();k++){
                if (P2_2.pontos_iguais(pontos.get(k), p)) {
                    usar = false;
                    i--;
                }
            }
            if (usar) {
                pontos.add(p);
            }
            usar = true;
        }
    }
}
