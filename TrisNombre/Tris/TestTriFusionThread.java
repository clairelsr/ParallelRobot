package Tris;

import java.util.Arrays;
import java.util.Random;

import Tris.Coll.TriColAvecExecuteurs;

public class TestTriFusionThread{
	public static void main(String[] args) {
	Random random = new Random();
    int n = 8;
    // Tableau Ã  trier
    int[] t = new int[n];
    // Remplir le tableau
    for (int i = 0; i < n; i++) {
    	t[i] = random.nextInt(n);
    }
    System.out.println("Tableau à  trier :");
    System.out.println(Arrays.toString(t));
    long debut = System.currentTimeMillis();
    System.out.println(n);
    TriColAvecExecuteurs Trieur = new TriColAvecExecuteurs(t);
    
  
    //int [] trie = Trieur.Tri1();
    //int [] trie = Trieur.Tri2(10,2,10);
    //int [] trie = Trieur.Tri3(10,2);
    int [] trie = Trieur.Tri4();
    
    long duree = System.currentTimeMillis() - debut;
    System.out.println("Durée = " + duree + " millisecondes.");
    System.out.println("Tableau trié :");
     System.out.println(Arrays.toString(trie));
    
	}
}
