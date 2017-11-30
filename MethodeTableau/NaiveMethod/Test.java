package NaiveMethod;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.concurrent.LinkedBlockingQueue;

import NaiveMethod.GatherPageLinks;




// On a mis un join pour les reader pour être sûr que le programme soit correct


public class Test {
	public static void main(String[]args) throws InterruptedException{
		
final int profondeur = 2; 
final String character = "/wiki/Yoda";
final int parallelCheck = 5;

		
		long startTime = System.currentTimeMillis();
		
		HashMap<String,Integer> resultatsfinaux = new HashMap<String,Integer>();
		HashSet<String> visitedPages = new HashSet<String>();
		LinkedBlockingQueue<String> pileToProcess = new LinkedBlockingQueue<String>();
		resultatsfinaux.put(character, 0);
		
		GatherPageLinks rAL = new GatherPageLinks(1,parallelCheck,character,visitedPages,
				pileToProcess,resultatsfinaux,1,profondeur);
		
		rAL.start();
		rAL.join();
		
		resultatsfinaux = rAL.getResults();
		
		System.out.println("La première génération d'amis:");
		for(Entry<String, Integer> entry : resultatsfinaux.entrySet()) {
		    String cle = entry.getKey();
		    Integer value = entry.getValue();
		    System.out.println(cle+ " rencontré à la génération "+ value);
		}
		
		long TimeGene1 = System.currentTimeMillis();
		
		long endTime1GENE = TimeGene1-startTime;
		
		System.out.println(endTime1GENE);
		
		
		
		
		
		
		
		
	}
}

