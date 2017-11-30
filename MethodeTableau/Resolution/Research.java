package Resolution;
import java.io.IOException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.Map.Entry;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import ExctractingCharacters.SaveAndLoad;


public class Research extends Thread{
	
	public static ConcurrentHashMap<String,Integer> research(String father,int numberOfGenerationRequired,int numberOfParallelThread) throws ClassNotFoundException, IOException, InterruptedException{
		// First, we upload the list containing all the characters, that we will require later
		
		long BigstartTime = System.currentTimeMillis();
		long startTime = System.currentTimeMillis();
		Collection<LinkedList<String>> Characters = SaveAndLoad.load("Characters.txt");
		SaveAndLoad.display(Characters);
		
		
		// Second, we put all the characters into a thread-safe structure
		
		ConcurrentHashMap<String,Integer> characters = new ConcurrentHashMap<String,Integer>(); // THREAD-SAFE STRUCUTURE
		
		
	
		
		for (LinkedList<String> c : Characters)
		{
			for (String e : c)
				characters.put(e, (int) (Math.random() * 10));

		}
		
		
		int c = 0; 
		for(Entry<String, Integer> entry : characters.entrySet()) {
			System.out.println(entry.getKey());
			c++;
		}
		System.out.println(c);
		
		
		
		
		long endTime = System.currentTimeMillis(); 
		System.out.println("All characters has been downloaded in " + (endTime - startTime) + " milliseconds");
		
		
		
		
		
		
		
		
		
		
		
		// Once the characters have been downloaded, we launch the final research
		
		
		
		
		
		ConcurrentHashMap<String,Integer> finalresults = new ConcurrentHashMap<String,Integer>(); // Thread-Safe structure where all the friends will be store 
		
		
		
		// The first generation
		
		startTime = System.currentTimeMillis();
		
		LinkedBlockingQueue<String> pageToExplore = new LinkedBlockingQueue<String>();
		
		pageToExplore.add(father);
		
		ReadAllLinksFromAPage firstGeneration = new ReadAllLinksFromAPage(characters, finalresults,father, 1,pageToExplore);

		firstGeneration.start();
		firstGeneration.join();
		endTime = System.currentTimeMillis();
		
		// Watch all the links that have been retrieved from yoda's page
		int d = 0;
		for(Entry<String, Integer> entry : finalresults.entrySet()) {
			System.out.print(entry.getKey());System.out.println(entry.getValue());
			d++;
		}
		System.out.println("Number of friends" + d);
		
		System.out.println("All direct friends retrieved in "+ (endTime -startTime)+ " milliseconds");
	
		
		
		
		// further generation
		
		
		ReadAllLinksFromAPage[] pageFromThePreviousGenerationToExplore = new ReadAllLinksFromAPage[numberOfParallelThread];
		
		int compteur = 0;
		
		for (int z = 2 ; z <=numberOfGenerationRequired ; z++){
			
			//sleep(10000);
			
			
			startTime = System.currentTimeMillis();
			
			
			for(Entry<String, Integer> entry : finalresults.entrySet()) {
				if(entry.getValue() == (z-1)){
					pageToExplore.add(entry.getKey());
				}
			}
			
			for(int i = 0; i < numberOfParallelThread ; i++){
				pageFromThePreviousGenerationToExplore[i] = new ReadAllLinksFromAPage(characters,finalresults,father,z,pageToExplore);
			}
			for(int i = 0; i < numberOfParallelThread ; i++){
				pageFromThePreviousGenerationToExplore[i].start();
			}
			for(int i = 0; i < numberOfParallelThread ; i++){
				pageFromThePreviousGenerationToExplore[i].join();
			}
			
			
			endTime = System.currentTimeMillis();

			for(Entry<String, Integer> entry : finalresults.entrySet()) {
				System.out.print(entry.getKey());System.out.println(entry.getValue());
				compteur++;
			}
			System.out.println("Number of friends" + compteur);
			
			System.out.println("All up to the degree "+z+" friends retrieved in "+ (endTime -startTime)+ " milliseconds");
			
			
			
			endTime = System.currentTimeMillis();
			compteur = 0;
		}
		
		
		
		
		long BigendTime = System.currentTimeMillis();
		
		
		for(Entry<String, Integer> entry : finalresults.entrySet()) {
			System.out.print(entry.getKey());System.out.println(entry.getValue());
			compteur++;
		}
		System.out.println("Number of friends" + compteur);
		
		System.out.println("All up to the degree "+numberOfGenerationRequired+" friends retrieved in "+ (BigendTime -BigstartTime)+ " milliseconds");
		
		
		
		return finalresults;
	
	}
	
	public void main(String []args) throws ClassNotFoundException, IOException, InterruptedException{
		ConcurrentHashMap<String,Integer> finalresults = new ConcurrentHashMap<String,Integer>();
		
		// choose your parameters: 
		String father = "/wiki/Yoda"; // choose the initial character
		int numberOfGenerationRequired = 5; // choose the depth of the research you want
		int numberOfParallelThread = 30; // you can amend the number of threads which will be launched
		
		//1°) Resolution with Java Structures (ConcurrentHashMap only):
		
		finalresults = research(father, numberOfGenerationRequired, numberOfParallelThread);
	}
}
