package Tris.Coll;

import java.io.IOException;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class TriColAvecExecuteurs {
	int[] t ;
	
	public TriColAvecExecuteurs(int[]t ){
		this.t=t;
	}
	
	public int[] Tri1 () {
		int n = this.t.length;
		int[][] m = new int[n][n];
		   
		Thread[][] comparators = new Thread[n][n]; 
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
	    
	    int[] trie = new int [n];
	    ultime ultimeaction = new ultime(r,t,trie);
	    ultimeaction.rearranger();
	    
	    return trie;
}
	
	public int[] Tri2 (int Nthreads1, int NAutorises, int Nthreads3) {
		int n =t.length;
		int[][] m = new int[n][n];
		   
		   // avec ProcessorCol (Exec à l'intérieur)
		    ProcessorCol comparators;
			try {
				comparators = new ProcessorCol (t,m,Nthreads1);
				comparators.run();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
			 //Contrôle des threads
			 
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
				   int[] trie = new int [n];
				    ProcessorUltime remplisseurs;
				  		try {
				  			remplisseurs = new ProcessorUltime (t,r,trie,Nthreads3);
				  			remplisseurs.run();
				  		} catch (IOException e1) {
				  			// TODO Auto-generated catch block
				  			e1.printStackTrace();
				  		}
				  return trie;
	}
	 
	public int[] Tri3 (int Nthreads, int Nautorises){
		return Tri2 (Nthreads, Nautorises, Nthreads);
	}
	public int[] Tri4 (){
		int Nthreads = Runtime.getRuntime().availableProcessors();
		return Tri2 (Nthreads, 2, Nthreads);
	}
	
	
	 
}
