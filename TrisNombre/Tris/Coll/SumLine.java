package Tris.Coll;

import java.util.concurrent.atomic.AtomicIntegerArray;
import java.util.concurrent.locks.ReentrantLock;

public class SumLine extends Thread{
	int i; // la ligne dont le thread doit se charger 
	int [][] m;
	AtomicIntegerArray r ;
	
	public SumLine(int i, int [][] m, AtomicIntegerArray r){
		this.i= i;
		this.m=m;
		this.r = r;
	}
	
	public void run(){
			Somme s = new Somme(m[i]) ;
			r.set(i, s.retourneTrie());
			
			return;
			
	}
	
	
	
	
}
