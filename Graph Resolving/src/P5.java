import java.util.LinkedList;

public class P5 {
    public static double criar_t(int n){
        return n*100000;
    }

    private static int contar_total(Ponto p1, Ponto p2, Ponto p3, Ponto p4){
        if ((p2.x==p3.x) && (p2.y==p3.y)) return 0;
        int d1, d2, d3, d4;
        d1 = P4_c.dir(p3, p4, p1);
        d2 = P4_c.dir(p3, p4, p2);
        d3 = P4_c.dir(p1, p2, p3);
        d4 = P4_c.dir(p1, p2, p4);
        //System.out.println("d1 = " + d1);
        //System.out.println("d2 = " + d2);
        //System.out.println("d3 = " + d3);
        //System.out.println("d4 = " + d4);

        if (((d1>0 && d2<0) || (d1<0 && d2>0)) && ((d3>0 && d4<0) || (d3<0 && d4>0)))
            return 1;
        else if ((d1 == 0) && P4_c.on_seg(p3,p4,p1)) return 1;
        else if ((d2 == 0) && P4_c.on_seg(p3,p4,p2)) return 1;
        else if ((d3 == 0) && P4_c.on_seg(p1,p2,p3)) return 1;
        else if ((d4 == 0) && P4_c.on_seg(p1,p2,p4)) return 1;

        return 0;
    }


    public static int conflitos_totais(LinkedList<Ponto> lista){
        int count = 0;
        for (int i = 0; i < lista.size(); i++) {
            for (int j = i + 2; j < lista.size() - 1; j++) {
                if (i==0 && j==lista.size()-2) break; //verificar aqui n-1/n-2 // AQUI É -2 POIS COMO ADICIONAMOS O VALOR INICIAL OUTRA VEZ NO FIM O LISTA.SIZE = N+1
                /*if (P3.segment_intersect(lista.get(i), lista.get(i + 1), lista.get(j), lista.get(j + 1))){
                    //System.out.println("Os Pontos: " + lista.get(i) + lista.get(i + 1) + lista.get(j) + lista.get(j + 1) + " intersetam-se");
                    count += contar_total(lista.get(i), lista.get(i + 1), lista.get(j), lista.get(j + 1));
                }*/
                if (P3.segment_intersect(lista.get(i), lista.get(i + 1), lista.get(j), lista.get(j + 1))){
                    count++;
                }
            }
        }

        return count;
    }
}
