package Tris.PairImpairLineaire;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class Compare implements Runnable {
	int i;
	int[]t;
	int seuil;
	AtomicInteger N;

	
	Compare(int i, int[]t, int seuil,AtomicInteger N){
		this.i=i;
		this.t=t;
		this.seuil=seuil;
		this.N=N;
	}
	
	@SuppressWarnings("finally")
	public synchronized void run(){
	
			
			
			if (t[i]>t[i+1]){
				int val=t[i];
				t[i]=t[i+1];
				t[i+1]=val;}
			 try {
	                synchronized (this.N) {
	                   
	                   this.N.getAndIncrement();
	                    if (this.N.compareAndSet(seuil, 0)){N.notifyAll();}
	                }
			 }finally {return;}
	                
		

	}

}
