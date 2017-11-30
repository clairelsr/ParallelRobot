package Tris;



import java.io.IOException;
import java.util.Arrays;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicIntegerArray;

import Tris.Coll.ProcessorCol;
import Tris.Coll.ProcessorUltime;
import Tris.Coll.SumLine;


public class TestColl {
	public static void main(String[] args) {
		Random random = new Random();
	    int n = 10;
	    // Tableau Ã  trier
	    int[] t = new int[n];
	    // Remplir le tableau
	    for (int i = 0; i < n; i++) {
	    	t[i] = n-i;
	    }
	    System.out.println("Tableau à  trier :");
	    System.out.println(Arrays.toString(t));
	    long debut = System.currentTimeMillis();
	    System.out.println(n);
	    
	    
	    
	    
	   int[][] m = new int[n][n];
	   
	   // avec ProcessorCol (Exec à l'intérieur)
	    ProcessorCol comparators;
		try {
			comparators = new ProcessorCol (t,m,10);
			comparators.run();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	   
		
		//sans executeur 
	  /*Thread[][] comparators = new Thread[n][n]; 
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
	    }*/
	    
	    //afficher la matrice m 
	    /*for (int i=0;i<n;i++){
	    	System.out.println("ligne"+i);
	    	 for (int j=0;j<n;j++){
	    		 System.out.print(m[i][j]);
	    	 }
	    }
	    */
	   //Contrôle des threads
		int NAutorises=2;
		 
			  int indice=0;
			  AtomicIntegerArray r = new AtomicIntegerArray(n);
			  
			  SumLine[] sums = new SumLine[NAutorises];
			
			   for (int i=0;i<n;i++){
				 if (sums[indice]==null) {
					 SumLine s= new SumLine(i,m,r);
					 sums[indice]=s;
					 s.start();
					 indice=(indice+1)%NAutorises;
				 }
				 else{
					 try {
						sums[indice].join();
					} catch (InterruptedException e) {
			
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					 SumLine s= new SumLine(i,m,r);
					 sums[indice]=s;
					 s.start();
					 indice=(indice+1)%NAutorises;
				 }
			   
		    	
			   	}
			   for (int i=0;i<NAutorises;i++){
				   try {
						sums[i].join();
					} catch (InterruptedException e) {
			
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			   }
			   
		    
	   // sans controle du nombre de sumline invoqués en meme temps
	   /*Thread[] sums = new Thread[n];
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
	    }*/
	    System.out.println(r);
	  
	   // la dernière étape qui permet de passer de r au tableau trié sans executeur
	   /* int[] trie = new int [n];
	    ultime ultimeaction = new ultime(r,t,trie);
	    ultimeaction.rearranger();*/
	    
	    //la dernière etape avec executor
	   int[] trie = new int [n];
	    ProcessorUltime remplisseurs;
	  		try {
	  			remplisseurs = new ProcessorUltime (t,r,trie,10);
	  			remplisseurs.run();
	  		} catch (IOException e1) {
	  			// TODO Auto-generated catch block
	  			e1.printStackTrace();
	  		}
	 

	    long duree = System.currentTimeMillis() - debut;
	    System.out.println("Durée = " + duree + " millisecondes.");
	    System.out.println("Tableau trié :");
	     System.out.println(Arrays.toString(trie));
	}

	
}
