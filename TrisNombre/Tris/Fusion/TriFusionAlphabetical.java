package Tris.Fusion;


public class TriFusionAlphabetical {
	
	
	public static void triFusion (String [] tab){
	triFusionRec (tab, 0, tab.length-1);
	}
	public static void triFusionRec (String [] tab, int début, int fin)
	{
		int milieu;
		if(début < fin)
		{
			milieu = (début+fin) / 2;
			triFusionRec(tab, début, milieu);
			triFusionRec(tab, milieu + 1, fin);
			fusionner (tab, début, milieu, fin);
		}
	}

	public static void fusionner (String tab[], int début, int milieu, int fin)
	{
		String [] old_tab = (String[]) tab.clone(); 
	        // tab.clone est tres gourmand en temps d'execution surtout dans un algo recursif
	        // il faudrait passer par un tableau temporaire pour stocker les données triées.
	        // puis recopier la partie triée a la fin de la méthode.

		int i1 = début; //indice dans la première moitié de old_tab
		int i2 = milieu + 1; // indice dans la deuxième moitié de old_tab
		int i = début; //indice dans le tableau tab

		while (i1 <= milieu && i2 <= fin)
		{
			//quelle est la plus petite tête de liste?
			if(tab[i1].compareTo(tab[i2])<0)
			{
				tab[i] = old_tab[i1];
				i1++;
			}
			else
			{
				tab[i] = old_tab[i2];
				i2++;
			}
			i++;
		}
		if (i <= fin)
		{
			while(i1 <= milieu)  // le reste de la première moitié
			{
				tab[i] = old_tab[i1];
				i1++;
				i++;
			}
			while(i2 <= fin) // le reste de la deuxième moitié
			{
				tab[i] = old_tab[i2];
				i2++;
				i++;
			}
		}
	}
}
