package Tris.Coll;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class utilmeThread extends Thread {
	 int i;
	 int[]l; 
	AtomicIntegerArray r ; 
	int[]trie;
		
public utilmeThread (int i ,int[]l , AtomicIntegerArray r,int[]trie)		 {
	
	this.i = i;
	this.l = l;
	this.r = r;
	this.trie = trie;
}
	 public void run(){
		 int j = r.get(i);
		 trie[j-r.length()]=l[i];
	 }

}
