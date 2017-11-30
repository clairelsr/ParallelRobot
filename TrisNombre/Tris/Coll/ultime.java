package Tris.Coll;

import java.util.concurrent.atomic.AtomicIntegerArray;

public class ultime {
	int[]l; 
	AtomicIntegerArray r ; 
	int[]trie;
	public ultime(AtomicIntegerArray r,int[]l,int[]trie) {
		this.r=r;
		this.l=l;
		this.trie=trie;
	}
	
	public void rearranger() {
		int n = r.length();
		for(int i =0;i<n;i++){
			int j =r.get(i);
			trie[j-n]=l[i];
		}
		
	}
	
}
