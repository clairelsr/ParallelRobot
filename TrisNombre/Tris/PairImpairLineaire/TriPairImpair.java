package Tris.PairImpairLineaire;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class TriPairImpair {
	int[] t;
	
	
	TriPairImpair (int[] t){		
		this.t=t;
		
	}	
	
     public synchronized int[] Trier(){    	
    	 ExecutorService exec= Executors.newFixedThreadPool(20);
    	 int n=t.length;
    	 int NombrePair= n/2;
    	 int NombreImpair= (n-1)/2;
    	 AtomicInteger NPair= new AtomicInteger(0);
    	 AtomicInteger NImPair= new AtomicInteger(0);
    	 double NombreEtape= Math.log(n)/Math.log(2);
    	 NombreEtape= (int)NombreEtape +1;
    	 for (int EtapeCourante=0;EtapeCourante<NombreEtape+10;EtapeCourante++){
    		 if (EtapeCourante%2==0){
    			if (EtapeCourante>0){ 
    				try { 
    					synchronized(NImPair){
    					if(NImPair.get()>0){NImPair.wait();}}
    				} catch (InterruptedException e) {
    					// TODO Auto-generated catch block
    					e.printStackTrace();
    				}}
    			else {}
    			 for (int i=0; i< NombrePair; i++){
    				exec.execute(new Compare(2*i,this.t,NombreImpair,NPair));    				
    			 }
    		
    	
    		 }
    		 else{
    			  try {
    				  synchronized(NPair){
    					  if(NPair.get()>0){NPair.wait();}}
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
    			 for (int i=0; i< NombreImpair; i++){
    				 exec.execute(new Compare(2*i+1,this.t,NombrePair, NImPair));	
    		 } 
    	
    	 }
    		 
     }
    	 exec.shutdown();
    	 return t;
    	

}

}