package Tris.PairImpairLineaire;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class CompareThread implements Runnable {

		int i;
		int[]t;

		CompareThread(int i, int[]t){
			this.i=i;
			this.t=t;
			
		}
		
		
		public void run(){
		
				
				
				if (t[i]>t[i+1]){
					int val=t[i];
					t[i]=t[i+1];
					t[i+1]=val;
		                
			

		}

	}

}
