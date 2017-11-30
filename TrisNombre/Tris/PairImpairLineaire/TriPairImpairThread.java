package Tris.PairImpairLineaire;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class TriPairImpairThread {
		int[] t;
		
		
		public TriPairImpairThread (int[] t){		
			this.t=t;
			
		}	
		
	     public synchronized int[] Trier(){    	
	    	 int n=t.length;
	    	 Thread[] threads= new Thread[n];
	    	 int NombrePair= n/2;
	    	 int NombreImpair= (n-1)/2;
	    	 double NombreEtape= Math.log(n)/Math.log(2);
	    	 NombreEtape= (int)NombreEtape +1;
	    	 for (int EtapeCourante=0;EtapeCourante<NombreEtape+1;EtapeCourante++){
	    		 if (EtapeCourante%2==0){
	    			
	    			 for (int i=0; i< NombrePair; i++){
	    				Thread s= new Thread(new CompareThread(2*i,this.t));
	    				threads[2*i]=s;
	    				s.start();
	    			 }
	    			 
	    			 for (int i=0; i< NombrePair; i++){
		    				try {
								threads[2*i].join();
		
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
		    			 }
	    		
	    	
	    		 }
	    		 else{
	    	
	    				 
	    			 for (int i=0; i< NombreImpair; i++){
	    				 Thread s= new Thread(new CompareThread(2*i+1,this.t));	
	    				 threads[2*i+1]=s;
		    				s.start();
	    			 	}
	    			 for (int i=0; i< NombreImpair; i++){
		    				try {
								threads[2*i+1].join();
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
		    			 }
	    	
	    	 }
	    		 
	     }
	    
	    	 return t;
	    	

	}

	}

