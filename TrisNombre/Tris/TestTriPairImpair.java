package Tris;
import Tris.PairImpair.*;

public class TestTriPairImpair {
	
    

    public static void main(String[] args) {
       int nMax = 1000;
         int[] tab = { 62,49,22,67,11,6,51,17,29,37 };

        int[] t= new int[nMax];
    	for (int j=0;j<nMax;j++) {t[j]=(int) (((double)nMax)*Math.random())+1;}
    	tab=t;
    	
    	int[] tabTrie= TriPairImpair.tri(tab);
    	for (int i=0;i<nMax;i++) { // affichage resultats
            System.out.println(tabTrie[i]);
        };
    }
}
