package Tris;



import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.atomic.AtomicIntegerArray;

import Tris.Coll.Comparator;
import Tris.Coll.SumLine;
import Tris.Coll.ultime;


public class TestColl2 {
	public static void main(String[] args) {
		Random random = new Random();
	    int n = 6;
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
	    
	    
	    
	    Thread[][] comparators = new Thread[n][n]; 
	    int[][] m = new int[n][n];
	    for (int i=0;i<n;i++){
	    	 for (int j=0;j<n;j++){	
	    		 	comparators[i][j] = new Comparator (t,i,j,m);
	    		 	comparators[i][j].start();
	    	 }
	    }
	    
	    
	    for (int i=0;i<n;i++){
	    	 for (int j=0;j<n;j++){
	    		 	try {
						comparators[i][j].join();
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
	    	 }
	    }
	    
	    //afficher la matrice m 
	    /*for (int i=0;i<n;i++){
	    	System.out.println("ligne"+i);
	    	 for (int j=0;j<n;j++){
	    		 System.out.print(m[i][j]);
	    	 }
	    }*/
	    
	    
	   
	    Thread[] sums = new Thread[n];
	    AtomicIntegerArray r = new AtomicIntegerArray(n);
	    for (int i=0;i<n;i++){
	    	SumLine s=new SumLine(i,m,r);
	    	sums[i]  = s;
	    	sums[i].start();
	    }
	    for (int i=0;i<n;i++){
	    	try {
				sums[i].join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    }
	    System.out.println(r);
	    
	  int[] trie = new int [n];
	    ultime ultimeaction = new ultime(r,t,trie);
	    ultimeaction.rearranger();
	    
	 

	    long duree = System.currentTimeMillis() - debut;
	    System.out.println("Durée = " + duree + " millisecondes.");
	    System.out.println("Tableau trié :");
	     System.out.println(Arrays.toString(trie));
	}

	
}
