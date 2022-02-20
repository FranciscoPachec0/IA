import java.util.LinkedList;

public class P4_c {
    public static int dir(Ponto pk, Ponto pj, Ponto pi) {
        return (((pk.x - pi.x) * (pj.y - pi.y)) - ((pj.x - pi.x) * (pk.y - pi.y)));
    }

    public static boolean on_seg(Ponto pi, Ponto pj, Ponto pk) {
        if (((Math.min(pi.x, pj.x) <= pk.x) && (Math.max(pi.x, pj.x) >= pk.x)) && ((Math.min(pi.y, pj.y)) <= pk.y) && (Math.max(pi.y, pj.y) >= pk.y)) {
            return true;
        }
        return false;
    }

    public static int conflitos(Ponto p1, Ponto p2, Ponto p3, Ponto p4) {
        int d1, d2, d3, d4;
        d1 = dir(p3, p4, p1);
        d2 = dir(p3, p4, p2);
        d3 = dir(p1, p2, p3);
        d4 = dir(p1, p2, p4);
        //System.out.println("d1 = " + d1);
        //System.out.println("d2 = " + d2);
        //System.out.println("d3 = " + d3);
        //System.out.println("d4 = " + d4);

        if (((d1 > 0 && d2 < 0) || (d1 < 0 && d2 > 0)) && ((d3 > 0 && d4 < 0) || (d3 < 0 && d4 > 0))) p1.conflitos++;
        else if ((d1 == 0) && on_seg(p3, p4, p1)) p1.conflitos++;
        else if ((d2 == 0) && on_seg(p3, p4, p2)) p1.conflitos++;
        else if ((d3 == 0) && on_seg(p1, p2, p3)) p1.conflitos++;
        else if ((d4 == 0) && on_seg(p1, p2, p4)) p1.conflitos++;

        return p1.conflitos;
    }

    public static void novo_contar_conflitos(LinkedList<Ponto> lista) {
        for (int i = 0; i < lista.size(); i++)   // resetar o valor de usado
            lista.get(i).usado = false;

        for (int i = 0; i < lista.size(); i++) {
            for (int j = i + 2; j < lista.size() - 1; j++) {
                if (i == 0 && j == lista.size() - 2){ // neste caso é n-2 pk estamos a utilizar o lista.size ao contrario de nos outros exemplos que usamos o n que nos é passado, o list.size tem +1 de comprimento pk adicionamos o ultimo no de novo ao inicio
                    //System.out.println("Entrou no break com os nos: " + lista.get(i) + lista.get(i+1) + lista.get(j) + lista.get(j+1));
                    break;
                }
                P4_c.conflitos(lista.get(i), lista.get(i + 1), lista.get(j), lista.get(j + 1));
            }
        }
    }

    public static boolean retas_iguais(Ponto p1, Ponto p2, Ponto p3, Ponto p4) {
        if ((p1.x == p3.x) && (p1.y == p3.y)) return true;
        else if ((p1.x == p4.x) && (p1.y == p4.y)) return true;
        else if ((p2.x == p3.x) && (p2.y == p3.y)) return true;
        else if ((p2.x == p4.x) && (p2.y == p4.y)) return true;

        return false;
    }

    public static LinkedList<Ponto> resolve_ponto(LinkedList<Ponto> lista, int pos) { // pos corresponde a posicao do 1 no da minha reta com menos intersecoes != 0
        Ponto temp = lista.get(pos);
        Ponto temp_seguinte = lista.get(pos + 1);
        //int count=0;
        //for (int i = 0; i<lista.size(); i++){
        for (int j = 0; j < lista.size() - 1; j++) {
            if (!retas_iguais(temp, temp_seguinte, lista.get(j), lista.get(j + 1))) {
                    if (P3.segment_intersect(temp, temp_seguinte, lista.get(j), lista.get(j + 1))) {
                        //System.out.println("Intersecao das retas (dentro da funcao) " + temp + temp_seguinte + lista.get(j) + lista.get(j + 1));
                        lista = P4_a.espelhar(lista, pos + 1, j);
                        //count++;
                        //}
                }

            }
        }
        //System.out.println("Para a Reta" + temp + temp_seguinte + "count = " + count);
        return lista;
    }
}
