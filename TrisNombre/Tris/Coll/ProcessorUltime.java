package Tris.Coll;

import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicIntegerArray;

public class ProcessorUltime  implements Runnable {
	
	int[]l; 
	AtomicIntegerArray r ; 
	int[]trie;
	private final ExecutorService pool;
	int poolSize;
	
	public ProcessorUltime(int[]l, AtomicIntegerArray r, int[]trie, int poolSize) throws IOException{
		this.r=r;
		this.l=l;
		this.trie=trie;
		this.poolSize =poolSize;
		pool = Executors.newFixedThreadPool(poolSize);
		}

			  
	 public void run() { // run the service
		 for(int i =0;i<this.r.length();i++){
			 this.pool.execute(new utilmeThread(i,l,r,trie));	
		 }
		this.pool.shutdown();
	}
			 
}
