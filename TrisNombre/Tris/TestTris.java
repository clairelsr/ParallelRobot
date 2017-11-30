package Tris;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import Tris.PairImpairLineaire.TriPairImpairThread;

public class TestTris {
	public static void main(String[] args) {
		int N=100;
		int[] t = new int [N];
		for (int i=0;i<N;i++){t[i]=(int) (((double)N)*Math.random())+1;}
		int[]s= {9,8,4,3,5,1};
		
		TriPairImpairThread Trieur = new TriPairImpairThread(s);
		int [] trié = Trieur.Trier();
		for (int i =0; i<trié.length;i++){
			System.out.println(trié[i]);
		}
		
		
	}
	
}
