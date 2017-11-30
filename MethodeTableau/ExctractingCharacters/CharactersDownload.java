package ExctractingCharacters;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class CharactersDownload {

	public static void CharactersDownload() throws ClassNotFoundException, IOException, InterruptedException{
		
		// We upload the previous LinkedList. It does take few milliseconds 
		Collection<LinkedList<String>> liste = SaveAndLoad.load("Intermediary1.txt");
		SaveAndLoad.display(liste);
		LinkedList<String> pageToExplore = new LinkedList<String>();
		for (LinkedList<String> c : liste)
		{
			for (String e : c)
				pageToExplore.add(e);
		}
		
		
		
		// Pour chaque page de chaque espèce, on enregistre le personnage dans la liste finale 
		
				long startTime = System.currentTimeMillis();
				
				// the global set of characters
				
				//DataStructure characters = new DataStructure(); 
				LinkedList<String> characters = new LinkedList<String>();
				
				for(String s : pageToExplore){
					ExctractingCharactersFromAPageOfASpecie.Exctracting(s, characters);
				}
				
				
				long endTime = System.currentTimeMillis();
				
				long endTime4 = endTime - startTime;
				System.out.println(endTime4);
				
				int c = 0;
				for(String s : characters){
					c++;
				}
				System.out.println("Numbers of characters added:  " +c);
				
				
				
				// Finally : we download all the characters in a document
				
				startTime = System.currentTimeMillis();
				
				
				SaveAndLoad.save("Characters.txt",characters);
				
				endTime = System.currentTimeMillis();
				
				long endTime5 = endTime - startTime;
				
				System.out.println("Downloading successful on "+ endTime5 + " milliseconds");
				

				
				
				
	}
}
