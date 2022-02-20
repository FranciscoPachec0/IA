import java.util.*;

@SuppressWarnings("unchecked")
public class P2_1 {
    public static void gerarpermutacao(LinkedList<Ponto> pontos){
        LinkedList<Ponto> temp = (LinkedList<Ponto>) pontos.clone();
        java.util.Collections.shuffle(pontos);
        if(listas_iguais(pontos, temp)){
            //System.out.println("Entrou nas listas iguais");
            gerarpermutacao(temp);
            pontos = (LinkedList<Ponto>) temp.clone();
        }
    }

    public static boolean listas_iguais(LinkedList<Ponto> pontos, LinkedList<Ponto> temp){
        if(pontos.size() != temp.size()) return false;
        else{
            for (int i=0; i<pontos.size(); i++){
                if (pontos.get(i) != temp.get(i)){
                    //System.out.println("Entrou nas listas diferentes");
                    return false;
                }

            }
        }
        return true;
    }
}
