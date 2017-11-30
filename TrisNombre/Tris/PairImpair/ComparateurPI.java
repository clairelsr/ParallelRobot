package Tris.PairImpair;

public class ComparateurPI extends Thread{
	  int proc;  // numero de processeur
	    int val;   // valeur courante
	    int nMax;  // nb de valeurs a trier
	    int[] tab; // pour stocker les resultats finaux
	    Buffer lg,ld,eg,ed; // lecture-ecriture, droite-gauche

	    public ComparateurPI(int p,int v,int n,int[] tt,Buffer llg,Buffer eeg,Buffer lld,Buffer eed) {
	        proc = p; val = v; nMax = n; tab = tt; 
	        lg = llg; ld = lld; eg = eeg; ed = eed;
	    }

	    public void run() {
	        int dv,gv; // valeurs lues a gauche et a droite
	        for (int etape=0;etape<nMax;etape++) { 
	            if ((etape%2)==0) {
	                if ((proc%2)==0) {
	                    if (ed!=null) ed.write(val);
	                    if (ld!=null) dv = ld.read(); else dv = val;
	                    if (dv<val) { val = dv; }
	                } else {
	                    if (eg!=null) eg.write(val);
	                    if (lg!=null) gv = lg.read(); else gv = val;
	                    if (gv>val) { val = gv; }
	                }
	            } else {
	                if ((proc%2)==0) {
	                    if (eg!=null) eg.write(val);
	                    if (lg!=null) gv = lg.read(); else gv = val;
	                    if (gv>val) { val = gv; }
	                } else {
	                    if (ed!=null) ed.write(val);
	                    if (ld!=null) dv = ld.read(); else dv = val;
	                    if (dv<val) { val = dv; }
	                }
	            } 
	        }            
	        tab[proc] = val;
	    }
	}

