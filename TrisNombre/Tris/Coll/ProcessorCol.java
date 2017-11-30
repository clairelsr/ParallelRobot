package Tris.Coll;



import java.io.IOException;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProcessorCol implements Runnable {
	int[] l ;
	int [][] m;
	private final ExecutorService pool;
	int poolSize;

	 public ProcessorCol(int []l, int[][] m, int poolSize)  throws IOException{
	    	this.l=l;
	    	this.m=m;
	    	this.poolSize =poolSize;
	    	 pool = Executors.newFixedThreadPool(poolSize);
	    }

		  
		   public void run() { // run the service
		     for (int i=0;i<l.length;i++){
				 for (int j=0;j<l.length;j++){	
					 this.pool.execute(new  Comparator (this.l, i, j,this.m));
				 }
			 }
		     pool.shutdown();
		   }
		 
}

