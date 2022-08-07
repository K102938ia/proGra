package sample.progect3temp;

import java.util.Comparator;

public class PostComparator implements Comparator < Post > {
    @Override
    public int compare ( Post o1, Post o2 ) {
        boolean b;
        b = o1.dateMade.isBefore (o2.dateMade);
        if(b){
            return 1;
        }
        else{
            return -1;
        }
    }
}
