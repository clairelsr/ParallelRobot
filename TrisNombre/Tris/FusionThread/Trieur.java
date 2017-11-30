package Tris.FusionThread;



import java.util.LinkedList;
import java.util.concurrent.RecursiveAction;



	public class Trieur extends RecursiveAction {
	  private int[] t;
	  private int debut, fin;

	
	  private Trieur(int[] t, int debut, int fin) {
	    this.t = t;
	    this.debut = debut;
	    this.fin = fin;
	  }
	  
	  public Trieur(int[] t) {
	    this.t = t;
	    this.debut = 0;
	    this.fin = t.length - 1;
	  }

	  @Override
	  protected void compute() {
	    if (fin - debut <2) {
	      trierDirectement();
	    }
	    else {
	      int milieu = debut + (fin - debut) / 2;
	      LinkedList<Trieur> trieurs = new LinkedList<Trieur>();
	      Trieur TrieurDebut = new Trieur (t, debut, milieu);
	      Trieur TrieurFin = new Trieur(t, milieu+1, fin);
	      trieurs.add(TrieurDebut);
	      trieurs.add(TrieurFin);
	      invokeAll(trieurs);
	      triFusion(debut, fin);
	    }
	  }

	  private void trierDirectement() {
	    if (t[debut] > t[fin]) {
	    	int valeur = t[debut];
		    t[debut]= t[fin];
		    t[fin]=valeur;
	    }
	  }


	  private void triFusion(int debut, int fin) { // va fusionner les deux moitiés de t (champ du trieur)
	    // tableau oÃ¹ va aller la fusion
	    int[] tFusion = new int[fin - debut + 1];
	    int milieu = (debut + fin) / 2;
	    // Indices des Ã©lÃ©ments Ã  comparer
	    int i1 = debut, 
	        i2 = milieu + 1;
	    // indice de la prochaine case du tableau tFusion Ã  remplir
	    int iFusion = 0;
	    while (i1 <= milieu && i2 <= fin) {
	      if (t[i1] < t[i2]) {
	        tFusion[iFusion++] = t[i1++];
	      }
	      else {
	        tFusion[iFusion++] = t[i2++]; 
	      }
	    }
	    if (i1 > milieu) {
	      // la 1Ã¨re tranche est Ã©puisÃ©e
	      for (int i = i2; i <= fin; ) {
	        tFusion[iFusion++] = t[i++];
	      }
	    }
	    else {
	      // la 2Ã¨me tranche est Ã©puisÃ©e
	      for (int i = i1; i <= milieu; ) {
	        tFusion[iFusion++] = t[i++];
	      }
	    }
	    // Copie tFusion dans t
	    for (int i = 0, j = debut; i <= fin - debut; ) {
	      t[j++] = tFusion[i++];
	    }
	  }
	}

