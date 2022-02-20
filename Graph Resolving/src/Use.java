import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;
@SuppressWarnings("unchecked")
public class Use {
    public static void main(String[] args) {
        LinkedList<Ponto> p = new LinkedList<>();
        LinkedList<Ponto> ligados = new LinkedList<>();
        Scanner in = new Scanner(System.in);
        //Ponto teste = new Ponto(0,0);

        int flag1;
        System.out.println("Insira a forma de geracao de listas");
        System.out.println("1 - Introducao manual dos pontos | 2 - Geracao aleatorio de pontos");
        System.out.println();
        flag1 = in.nextInt();
        if (flag1 == 1) {
            System.out.println("Insira o numero de pontos");
            int n = in.nextInt();
            for (int i = 0; i < n; i++) {
                int x, y;
                x = in.nextInt();
                y = in.nextInt();
                Ponto temp = new Ponto(x, y);
                p.add(temp);
            }
        } else {
            int n, m;

            System.out.println("Insira o numero de pontos(n): ");
            n = in.nextInt();
            System.out.println("Insira o valor maximo(m): ");
            m = in.nextInt();

            P1.gerarlista(p, n, m);
            System.out.println("Inicial: " + p);

            P2_1.gerarpermutacao(p);
            System.out.println("Shuffle: " + p);
        }

        int n = p.size();

        System.out.println();
        System.out.println("Insira a forma de geracao de ordenacao pelo nearest neighbour");
        System.out.println("0 - Não utilizar a ordenação por nearest neighbour | 1 - A partir do 1 ponto inserido/criado | 2 - A partir de um ponto aleatorio");
        flag1 = in.nextInt();
        System.out.println();

        if (flag1==1) {
            ligados = P2_2.nearest_neighbour(p, p.get(0));
            System.out.println("Retas nearest_neighbour: " + ligados.toString());
        } else if (flag1 == 2){
            Random random = new Random();
            int aleatorio = random.nextInt(n); // 0 ate size-1
            //System.out.println("aleatorio = " + aleatorio);
            ligados = P2_2.nearest_neighbour(p, p.get(aleatorio));
            System.out.println("Retas nearest_neighbour: " + ligados.toString());
        } else {
            //System.out.println("Nao utilizou nearest neighbour");
            ligados = (LinkedList<Ponto>) p.clone();
            System.out.println("Retas nearest_neighbour: " + ligados);
        }

        if (flag1 == 1 || flag1==2) {
            // APAGAR PONTOS IGUAIS QUE ESTEJAM SEGUIDOS
            //System.out.println("lista antes de nearest neigbour: " + ligados);
            for (int i = 0; i < ligados.size() - 1; i++) {
                //System.out.println("No: " + ligados.get(i));
                if (P2_2.pontos_iguais(ligados.get(i), ligados.get(i + 1))) {
                    ligados.remove(i + 1);
                    i--;
                }
            }
        } else {
            for (int i = 0; i<ligados.size(); i++){
                for (int j = i+1; j<ligados.size(); j++) {
                    if (P2_2.pontos_iguais(ligados.get(i), ligados.get(j))){
                        ligados.remove(j);
                        j--;
                    }
                }
            }
        }
        ligados.add(ligados.get(0));
        System.out.println("Lista depois de nearest neigbour: " + ligados);

        // P3 verificar se tem intersecoes (FEITO)
        for (int i = 0; i < ligados.size(); i++) {
            for (int j = i + 2; j < ligados.size() - 1; j++) {
                if (i == 0 && j == ligados.size()-2) break;
                boolean intersecao = P3.segment_intersect(ligados.get(i), ligados.get(i + 1), ligados.get(j), ligados.get(j + 1));
                if (intersecao)
                    // PRINTA OS PONTOS QUE INTERSETAM
                    System.out.println("Os Pontos: " + ligados.get(i) + ligados.get(i + 1) + ligados.get(j) + ligados.get(j + 1) + " intersetam-se");
            }
        }

        /*for (int i=0; i<ligados.size()-1; i++){
                System.out.println("Distancia entre os 2 nos " + ligados.get(i) + "," + ligados.get(i+1) + " = " + P2_2.calcular_distancia(ligados.get(i), ligados.get(i+1)));
        }
        System.out.println("Distancia entre os 2 nos " + ligados.get(ligados.size()-1) + "," + ligados.get(0) + " = " + P2_2.calcular_distancia(ligados.get(ligados.size()-1), ligados.get(0)));*/

        LinkedList<Ponto> ligados_backup = (LinkedList<Ponto>) ligados.clone();
        LinkedList<Ponto> ant_col = (LinkedList<Ponto>) ligados.clone();

        for (int i = 0; i<ligados_backup.size(); i++){
            ligados_backup.get(i).usado = false;
            ant_col.get(i).usado = false;
        }

        System.out.println();
        System.out.println("Insira a flag");
        System.out.println("1- Candidato que reduz mais o perimetro | 2- Primeiro candidato que encontrar nessa vizinhanca | 3- Candidato que tem menos conflitos de arestas | 4- Qualquer candidato nessa vizinhanca");
        int flag = in.nextInt();
        System.out.println();

        if (flag == 1) {
            // Eliminar a intersecao que vai melhorar mais o perimetro 4a
            System.out.println("Reta Inicial(P4a): " + ligados);
            System.out.println("Perimetro inicial(P4a) = " + P4_a.perimetro(ligados));
            for (int k = 0; k < ligados.size(); k++) {
                double perimetro = P4_a.perimetro(ligados); //atencao a esta lista
                double diferenca = Double.MAX_VALUE;
                double temp_perimetro = perimetro;
                for (int i = 0; i < ligados.size() - 1; i++) {
                    int p1 = 0;
                    int p2 = 0;
                    for (int j = i + 2; j < ligados.size() - 1; j++) {
                        if (i == 0 && j == n - 1) break;
                        LinkedList<Ponto> temp = (LinkedList<Ponto>) ligados.clone();
                        if (P3.segment_intersect(ligados.get(i), ligados.get(i + 1), ligados.get(j), ligados.get(j + 1))) {
                            //System.out.println("Os Pontos: " + ligados.get(i) + ligados.get(i + 1) + ligados.get(j) + ligados.get(j + 1) + " intersetam-se");
                            temp = P4_a.espelhar(temp, i + 1, j);
                            //System.out.println("Temp espelhada " + temp);
                            //System.out.println(temp);
                            double novo_perimetro = P4_a.perimetro(temp);
                            //if (Math.abs(novo_perimetro-perimetro) < diferenca) {
                            if (novo_perimetro - perimetro < diferenca) {
                                temp_perimetro = novo_perimetro;
                                //diferenca = Math.abs(novo_perimetro-perimetro);
                                diferenca = novo_perimetro - perimetro;
                                p1 = i + 1;
                                p2 = j;
                            }
                        }
                    }
                    if (p2 != 0) {
                        ligados = P4_a.espelhar(ligados, p1, p2);
                        perimetro = temp_perimetro;
                        //System.out.println("Nova lista ligados " + ligados.toString() + "e Novo perimetro = " + perimetro);
                    }
                }
            }
            System.out.println("Reta final(P4a): " + ligados);
            System.out.println("Perimetro final(P4a) = " + P4_a.perimetro(ligados));

        } else if (flag == 2) {
            // Eliminar as intersecoes P4_b
            LinkedList<Ponto> sem_inters = (LinkedList<Ponto>) ligados.clone();
            System.out.println("Reta Inicial(P4b) " + sem_inters.toString());
            //System.out.println("Sem_inters.size = " + sem_inters.size());
            int existe_intersecao = 2;

            while (existe_intersecao != 0) {
                existe_intersecao = 0;
                for (int i = 0; i < sem_inters.size(); i++) {
                    for (int j = i + 2; j < sem_inters.size()-1; j++) {
                        if (i == 0 && j == sem_inters.size()-2) break; // verificar se é n-1 ou n-2
                        boolean intersecao = P3.segment_intersect(sem_inters.get(i), sem_inters.get(i + 1), sem_inters.get(j), sem_inters.get(j + 1));
                        if (intersecao) {
                            existe_intersecao = 1;
                            //System.out.println("Intersecao dos Pontos: " + sem_inters.get(i) + sem_inters.get(i + 1) + sem_inters.get(j) + sem_inters.get(j + 1) + " = " + intersecao);
                            sem_inters = P4_a.espelhar(sem_inters, i + 1, j);
                            //System.out.println("Retas Novas: " + sem_inters.toString());
                            //intersecao = P3.segment_intersect(sem_inters.get(i), sem_inters.get(i + 1), sem_inters.get(j), sem_inters.get(j + 1));
                            //System.out.println("Intersecao dos Pontos: " + sem_inters.get(i) + sem_inters.get(i + 1) + sem_inters.get(j) + sem_inters.get(j + 1) + " = " + intersecao);
                        }
                    }
                }
            }
            System.out.println("Reta Final(p4b): " + sem_inters.toString());
        } else if (flag == 3) {
            System.out.println("Reta Inicial(P4c): " + ligados);
            int existe_intersecao = 2;

            while (existe_intersecao != 0) {
                existe_intersecao = 0;

                for (int i = 0; i < ligados.size(); i++) {
                    ligados.get(i).conflitos = 0;
                }

                P4_c.novo_contar_conflitos(ligados);
                /*for (int i = 0; i < p.size() - 1; i++) {
                    System.out.println("Reta" + ligados.get(i).toString() + "," + ligados.get(i + 1) + " tem " + ligados.get(i).conflitos + " conflitos");

                }*/

                int min = Integer.MAX_VALUE;
                int min_conflitos = Integer.MAX_VALUE;
                //System.out.println("Antes de entrar no for");
                for (int i = 0; i < ligados.size(); i++) {
                    if ((ligados.get(i).conflitos > 0) && (ligados.get(i).conflitos < min_conflitos)) {
                        existe_intersecao = 1;
                        min = i;
                        min_conflitos = ligados.get(i).conflitos;
                    }
                }

                if (existe_intersecao != 0) {
                    ligados = P4_c.resolve_ponto(ligados, min);
                    //System.out.println("Nova lista ligada: " + ligados);
                    //System.out.println("Retas que tem apenas 0 conflitos" + conflitos);
                }
            }

            System.out.println("Reta Final(P4c): " + ligados);
        } else if (flag == 4) { // CERTO E VERIFICADO

            System.out.println("Reta Inicial(P4d): " + ligados);

            int existe_intersecao = 2;

            while (existe_intersecao != 0) {
                existe_intersecao = 0;
                int aleatorio;

                for (int i = 0; i < ligados.size(); i++) {
                    ligados.get(i).conflitos = 0;
                }

                P4_d.novo_conflitos_random(ligados);

                /*for (int i = 0; i < p.size() - 1; i++) {
                    System.out.println("Reta" + ligados.get(i).toString() + "," + ligados.get(i + 1) + " tem " + ligados.get(i).conflitos + " conflitos");
                }*/

                for(int i = 0; i<ligados.size(); i++){
                    if (ligados.get(i).conflitos > 0){
                        Random random = new Random();
                        aleatorio = random.nextInt(ligados.get(i).conflitos);
                        //System.out.println("Numero aleatorio = " + aleatorio);
                        //System.out.println("O ponto " + ligados.get(i) + " interseta com as retas " + ligados.get(i).retas.toString());
                        for (int j = i+2; j<ligados.size()-1; j++){
                            //talvez falte a condicao
                            if(P3.segment_intersect(ligados.get(i), ligados.get(i + 1), ligados.get(j), ligados.get(j + 1))){
                                existe_intersecao = 1;
                                //System.out.println("Os nos " + ligados.get(i) + ligados.get(i+1) + ligados.get(j) + ligados.get(j+1) + "intersetam-se");
                                if (aleatorio != 0)
                                    aleatorio --;
                                else {
                                    ligados = P4_a.espelhar(ligados, i+1, j);
                                    break;
                                }
                            }
                        }
                        break;
                    }
                }
                //System.out.println("Nova Lista sem intersecoes" + ligados.toString());
            }
            System.out.println("Reta Final(P4d): " + ligados.toString());

        }
        /* testar funcao espelhar
        else if (flag == 5) {
            System.out.println("Original " + ligados.toString());
            ligados = P4_a.espelhar(ligados, 2, 6);
            System.out.println("Espelhado " + ligados.toString());
        }
        */

        //P5
        //System.out.println("Ligados_Backup(5): " + ligados_backup);
        System.out.println("Reta Inicial(5): " + ligados_backup);
        double t = P5.criar_t(ligados_backup.size());
        int custo;
        double fator_arrefecimento = 0.95;
        // ver o numero de interacoes
        //while (t>0){ // condicao de diminuicao de temperatura algo do genero 0,95
        for (; t>0; t*=fator_arrefecimento){
             //System.out.println("t = " + t);
             custo = P5.conflitos_totais(ligados_backup);
            for(int i = 0; i<ligados_backup.size(); i++){
                for (int j=i+2; j< ligados_backup.size()-1; j++){
                    if (i == 0 && j == n - 1) break;
                    if(P3.segment_intersect(ligados_backup.get(i), ligados_backup.get(i+1), ligados_backup.get(j), ligados_backup.get(j+1))){
                        LinkedList<Ponto> temp = P4_a.espelhar(ligados_backup, i+1, j);
                        //System.out.println("Temp = " + temp);
                        //System.out.println("Custo = " + custo);
                        int novo_custo = P5.conflitos_totais(temp);
                        //System.out.println("Novo custo = " + novo_custo);
                        //int variacao = novo_custo - custo;
                        int variacao = custo - novo_custo;
                        //System.out.println("Variacao = " + variacao);
                        if (variacao > 0){
                            custo = novo_custo;
                            ligados_backup = temp;
                            break; // ver
                        } else {
                            //System.out.println("Variacao = " + variacao);
                            //System.out.println("T = " + t);
                            //double expoente = (double) 5/2;
                            //System.out.println("Expoente = " + variacao/t);
                            double probabilidade = Math.exp((double) variacao/t);
                            //probabilidade = probabilidade * t;
                            //System.out.println("Probabiliade = " + variacao/t);
                            double aleatorio = Math.random(); // intervalo de 0 a 1
                            //System.out.println("Probabilidade = " + probabilidade + " Aleatório = " + aleatorio);
                            if (aleatorio <= probabilidade){
                                //System.out.println("Entrou na troca devido à probabilidade");
                                custo = novo_custo;
                                ligados_backup = temp;
                                //break;
                            }
                        }
                    }
                }
            }
        t -=1;
        }
        System.out.println("Nova lista(5) = " + ligados_backup);
        //System.out.println("Total de interseções(5) = " + custo);

        //P6

        //System.out.println("Lista Ant(6): " + ant_col);

        double c = 1.0;
        double alpha = 1;
        double beta = 5;
        double evaporation = 0.5;
        double Q = 500;
        double antFactor = 0.8;
        double randomFactor = 0.01;

        for (int i=0; i<ant_col.size(); i++)
            ant_col.get(i).usado = false;
            //System.out.println("No" + ant_col.get(i) + " usado = " + ant_col.get(i).usado);

        LinkedList<Ant> formigas = new LinkedList<>();

        int tentativas = 20;

        for (int i=0;i<tentativas;i++){ // criar todas as formigas e respetivos caminhos
            LinkedList temp = (LinkedList) ant_col.clone();
            Ant formiga_temp = new Ant();
            formiga_temp.caminho = P6.gera_caminho_aleatorio(temp);
            formigas.add(formiga_temp);
        }
        //System.out.println("Lista formigas size = " + formigas.size());

        for(int i=0; i<formigas.size();i++){
            formigas.get(i).comprimento = P4_a.perimetro(formigas.get(i).caminho);
        }


    }
}
