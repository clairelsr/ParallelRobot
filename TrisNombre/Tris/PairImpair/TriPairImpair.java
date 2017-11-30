package Tris.PairImpair;



public class TriPairImpair {
	
	   

	    public static int[] tri (int [] tab) {
	    	int nMax=tab.length;
	    	int[] tabTrie = new int[nMax];
	        ComparateurPI[] cmp = new ComparateurPI[nMax];
	        Buffer lg,eg,ld,ed;
	        lg = null; eg = null ;
	        for (int i=0;i<nMax-1;i++) { // creation des threads
	            ld = new Buffer();
	            ed = new Buffer();
	            cmp[i]= new ComparateurPI(i,tab[i],nMax,tabTrie,lg,eg,ld,ed);
	            lg = ed; eg = ld;
	        };
	        ld = null; ed = null; // creation du dernier thread
	        cmp[nMax-1] = new ComparateurPI(nMax-1,tab[nMax-1],nMax,tabTrie,lg,eg,ld,ed);

	        for (int i=0;i<nMax;i++) { // execution des threads
	            cmp[i].start();
	        };
	        for (int i=0;i<nMax;i++) { // attente des resultats
	            try { cmp[i].join(); } catch (InterruptedException e) {} ; 
	        };
	        for (int i=0;i<nMax;i++) { // affichage resultats
	            System.out.println(tabTrie[i]);
	        };
	        return tabTrie;
	    }
	
	
	
	
}




