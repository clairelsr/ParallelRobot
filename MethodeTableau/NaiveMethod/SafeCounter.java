package NaiveMethod;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

//We create our own class of shared pile

public class SafeCounter{
	ReentrantLock lock;
	Condition ThreadAvailable;
	int nbThread;
	int parallel;
	
	public SafeCounter(int nbThread,int parallel){
		this.lock = new ReentrantLock();
		this.ThreadAvailable = lock.newCondition();
		this.nbThread=0;
		this.parallel=parallel;
	}
	
	public void Increment(){
		lock.lock();
		try{
			while(nbThread>=1000){
				ThreadAvailable.awaitUninterruptibly();
			}
			nbThread = nbThread+parallel;
			return;
		}finally{
			lock.unlock();
		}
	}
	
	public void Decrement(){
		lock.lock();
		try{
			boolean wasFull = (nbThread>=1000);
			nbThread = nbThread-parallel;
			if(wasFull){
				ThreadAvailable.signalAll();
			}
			return;
		}finally{
			lock.unlock();
		}
	}
	
}