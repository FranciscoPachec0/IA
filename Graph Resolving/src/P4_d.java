import java.util.LinkedList;

public class P4_d {
    /*
    public static int contar_conflitos_reta(LinkedList<Ponto> lista, int pos) {
        Ponto temp = lista.get(pos);
        Ponto temp_seguinte = lista.get(pos+1);
        //int count = 0;

        for (int i=0; i<lista.size()-1; i++){
            if (i!=pos)
                P4_c.conflitos_retas(temp, temp_seguinte, lista.get(i), lista.get(i+1)); // adicionar diretamente no no
        }
        return 0;
    }*/

    public static void conflitos_random(Ponto p1, Ponto p2, Ponto p3, Ponto p4) {
        int d1, d2, d3, d4;
        d1 = P4_c.dir(p3, p4, p1);
        d2 = P4_c.dir(p3, p4, p2);
        d3 = P4_c.dir(p1, p2, p3);
        d4 = P4_c.dir(p1, p2, p4);
        //System.out.println("d1 = " + d1);
        //System.out.println("d2 = " + d2);
        //System.out.println("d3 = " + d3);
        //System.out.println("d4 = " + d4);

        if (((d1>0 && d2<0) || (d1<0 && d2>0)) && ((d3>0 && d4<0) || (d3<0 && d4>0))){
            p1.conflitos++;
            p1.retas.add(p3);
            //p1.retas.add(p4);
        }
        else if ((d1 == 0) && P4_c.on_seg(p3,p4,p1)){
            p1.conflitos++;
            p1.retas.add(p3);
            //p1.retas.add(p4);
        }
        else if ((d2 == 0) && P4_c.on_seg(p3,p4,p2)){
            p1.conflitos++;
            p1.retas.add(p3);
            //p1.retas.add(p4);
        }
        else if ((d3 == 0) && P4_c.on_seg(p1,p2,p3)){
            p1.conflitos++;
            p1.retas.add(p3);
            //p1.retas.add(p4);
        }
        else if ((d4 == 0) && P4_c.on_seg(p1,p2,p4)){
            p1.conflitos++;
            p1.retas.add(p3);
            //p1.retas.add(p4);
        }

        //return p1.conflitos;
    }

    public static void novo_conflitos_random (LinkedList<Ponto> lista){
        int n = lista.size();

        for (int i = 0; i < lista.size(); i++)   // resetar o valor de usado
            lista.get(i).usado = false;

        for (int i = 0; i < lista.size(); i++) {
            for (int j = i + 2; j < lista.size() - 1; j++) {
                if (i==0 && j==n-2) break;
                P4_d.conflitos_random(lista.get(i), lista.get(i + 1), lista.get(j), lista.get(j + 1));
            }
        }
    }
}
