package Resolution;
import java.io.IOException;

	import java.util.Collection;
	import java.util.LinkedList;
	import java.util.Map.Entry;
	import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;

import ExctractingCharacters.SaveAndLoad;

public class ResearchHomeMade {
	

		
		public static ConcurrentHashMap<String,Integer> homemaderesearch(String father,int numberOfGenerationRequired,int numberOfParallelThread) throws ClassNotFoundException, IOException, InterruptedException{
			// First, we upload the list containing all the characters, that we will require later
			
			long BigstartTime = System.currentTimeMillis();
			long startTime = System.currentTimeMillis();
			Collection<LinkedList<String>> Characters = SaveAndLoad.load("Characters.txt");
			SaveAndLoad.display(Characters);
			
			
			// Second, we put all the characters into a thread-safe structure
			
			DataStructure characters = new DataStructure(); // Home-made THREAD-SAFE STRUCUTURE
			
			for (LinkedList<String> c : Characters)
			{
				for (String e : c)
					characters.add(e);

			}
			
			
			
			

			
			
			long endTime = System.currentTimeMillis(); 
			System.out.println("All characters has been downloaded in " + (endTime - startTime) + " milliseconds");
			
			
			
			
			
			
			
			
			
			
			
			// Once the characters have been downloaded, we launch the final research
			
			
			
			
			ConcurrentHashMap<String,Integer> finalresults = new ConcurrentHashMap<String,Integer>(); // Thread-Safe structure where all the friends will be store 

			// The first generation
			
			startTime = System.currentTimeMillis();
			
			LinkedBlockingQueue<String> pageToExplore = new LinkedBlockingQueue<String>();
			
			pageToExplore.add(father);

			ReadAllLinksFromAPageWithHomeMadeDataStructure firstGeneration = new ReadAllLinksFromAPageWithHomeMadeDataStructure(characters, finalresults,father, 1,pageToExplore);

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
			
			
			ReadAllLinksFromAPageWithHomeMadeDataStructure[] pageFromThePreviousGenerationToExplore = new ReadAllLinksFromAPageWithHomeMadeDataStructure[numberOfParallelThread];
			
			
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
					pageFromThePreviousGenerationToExplore[i] = new ReadAllLinksFromAPageWithHomeMadeDataStructure(characters,finalresults,father,z,pageToExplore);
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
	}


