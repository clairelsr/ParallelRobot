package ExctractingCharacters;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ConcurrentHashMap;

import javax.swing.text.BadLocationException;

public class IntermediaryDownload {
	public static void IntermediaryDownload() throws InterruptedException, IOException{
		
		//On enregistre toutes les espèces qui existent
		
		long startTime = System.currentTimeMillis();
		final ExctractingAllSpecies classSpecies = new ExctractingAllSpecies();
		Thread[] t  = new Thread[4];
		for (int i = 0 ; i < 4; i++){
			final int id = i + 1;
			t[i] = new Thread(new Runnable(){
				public void run(){
					try {
						classSpecies.ExctractingSpeciesIndividual(id);
					} catch (IOException | BadLocationException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
			t[i].start();
		}
		for (int i = 0 ; i < 4; i++){t[i].join();}
		
		long endTime = System.currentTimeMillis();
		
		final long endTime1 = endTime - startTime;
		
		System.out.println(endTime1);
		int counter = 0;
		for(String s :classSpecies.species){
			System.out.println(s);
			counter++;
		}
		System.out.println(counter);
		
		// Résultat de la première phase : 
		// En moins de 4 secondes, classSpecies contient dans sa LinkedList toutes les 624 espèces du monde
		
		
		
		
		
		
		// Pour chaque espèce, on lit le nombre de pages
		
		startTime = System.currentTimeMillis();
		
		
		CounterPageOfASpecie[] unthreadparespece = new CounterPageOfASpecie[counter];
		int y = 0;
		for(String s :classSpecies.species){
			System.out.println(y);
			unthreadparespece[y] = new CounterPageOfASpecie(s);
			unthreadparespece[y].start();
			y++;
		}
		
		for(int i = 0 ; i < counter ; i ++){
			unthreadparespece[i].join();
		}
		
		for(int i = 0 ; i < counter ; i ++){
			System.out.print(unthreadparespece[i]._url);
			System.out.println(unthreadparespece[i].count);
		}
		
		
		
		endTime = System.currentTimeMillis();
		
		long endTime2 = endTime - startTime;
		System.out.println(endTime2);
		

		
		// Résultat : en un peu plus d'une minute, pour chaque espèce, on a identifié le nombre de pages 
		
		
		
		
		
		// Intermediary : we download unthreadparespece into a linkedlist
		
		startTime = System.currentTimeMillis();
		
		LinkedList<String> pagesToExplore = new LinkedList<String>();
		
		for(int i = 0 ; i < counter ; i ++){
			if(unthreadparespece[i].count == 0 || unthreadparespece[i].count == 1){
				System.out.println(unthreadparespece[i]._url);
				pagesToExplore.add(unthreadparespece[i]._url);
			}
			else{
				for(int k = 0 ; k < unthreadparespece[i].count ; k++){
					String plus = Integer.toString(k+1);
					String page = unthreadparespece[i]._url+"?page="+plus;
					pagesToExplore.add(page);
					System.out.println(page);
				}
			}
		}
		
		SaveAndLoad.save("IntermediaryCharacters.txt",pagesToExplore);
		
		endTime = System.currentTimeMillis();
		
		long endTime3 = endTime - startTime;
		
		System.out.println("Intermediary Downloading successful on "+ endTime3 + " milliseconds");
	}
	
	
	

}
