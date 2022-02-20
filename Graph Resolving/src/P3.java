import java.util.LinkedList;

public class P3 {
    public static int dir(Ponto pk, Ponto pj, Ponto pi){
        return (((pk.x-pi.x)*(pj.y-pi.y))-((pj.x-pi.x)*(pk.y-pi.y)));
    }

    public static boolean on_seg(Ponto pi, Ponto pj, Ponto pk){
       if(((Math.min(pi.x, pj.x) <= pk.x) && (Math.max(pi.x, pj.x) >= pk.x)) && ((Math.min(pi.y, pj.y)) <= pk.y) && (Math.max(pi.y, pj.y) >= pk.y)){
          return true;
       }
        return false;
    }

    public static boolean segment_intersect(Ponto p1, Ponto p2, Ponto p3, Ponto p4){
        if ((p2.x==p3.x) && (p2.y==p3.y)) return false;
        int d1, d2, d3, d4;
        d1 = dir(p3, p4, p1);
        d2 = dir(p3, p4, p2);
        d3 = dir(p1, p2, p3);
        d4 = dir(p1, p2, p4);
        //System.out.println("d1 = " + d1);
        //System.out.println("d2 = " + d2);
        //System.out.println("d3 = " + d3);
        //System.out.println("d4 = " + d4);

        if (((d1>0 && d2<0) || (d1<0 && d2>0)) && ((d3>0 && d4<0) || (d3<0 && d4>0)))
            return true;
        else if ((d1 == 0) && on_seg(p3,p4,p1)) return true;
        else if ((d2 == 0) && on_seg(p3,p4,p2)) return true;
        else if ((d3 == 0) && on_seg(p1,p2,p3)) return true;
        else if ((d4 == 0) && on_seg(p1,p2,p4)) return true;

    return false;
    }


}
