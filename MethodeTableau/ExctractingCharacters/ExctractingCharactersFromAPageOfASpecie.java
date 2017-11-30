package ExctractingCharacters;

import java.util.LinkedList;
import java.util.concurrent.BlockingQueue;


public class ExctractingCharactersFromAPageOfASpecie{
	
	public static LinkedList<String> RepererMot (int debut, int fin, String motini, String motfin, String page) {
		int k = page.indexOf(motini,debut);
		LinkedList<String> res = new LinkedList<String>();
		while(k<fin && k>=0) {
			int begin = k;
			int end  = page.indexOf(motfin,begin+1);
			String nouv = page.substring(begin + motini.length(), end);
			res.add(nouv);
			k = page.indexOf(motini,end+1);
		}
		return res;
	}
	
	public static void Exctracting(String _url, LinkedList<String> characters){
		
			String[] parts = _url.split(":");
			String specie = parts[1];
		
			String page = LirePage.getTextFile(_url);
			System.out.println(_url);
			int debut = page.indexOf("Perform a category intersection", 0);
			System.out.println(debut);
			int fin =  page.indexOf("</div>", debut);
			if (debut==-1) {
				return;	
			}	
			String motini = "href=\"" ;//href="
			String motfin ="\" title";
			LinkedList<String> RepererMot  = RepererMot (debut, fin, motini, motfin,page);
			
			for (String perso : RepererMot ) {
					/*if (!setOfCharacters.contains(perso)) {
						if(!perso.endsWith("/Legends")){
							setOfCharacters.add(perso);
							System.out.println(perso);
						}	
					}
					*/
					if(!characters.contains(perso)){
						if(!perso.endsWith("/Legends")){
							if(!perso.contains("people")){
								if(!perso.contains(specie)){
									characters.add(perso);
									System.out.println(perso);
								}
							}
							
						}
					}
			}
			
			
		
			
	}
}
