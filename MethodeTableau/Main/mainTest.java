package Main;

import java.io.IOException;
import java.util.concurrent.ConcurrentHashMap;

import ExctractingCharacters.CharactersDownload;
import ExctractingCharacters.IntermediaryDownload;
import Resolution.Research;
import Resolution.ResearchHomeMade;

public class mainTest {
	
	
	public static void  main(String[]args) throws InterruptedException, IOException, ClassNotFoundException{
		/*
		
		// First, we need to upload the characters
		
		IntermediaryDownload.IntermediaryDownload();
		
		CharactersDownload.CharactersDownload();
		
		// Once the downloading is finished, you'll be able to comment these two previous lanes
		
		
		
		
		*/
		
		// Now, the resolution
		
		ConcurrentHashMap<String,Integer> finalresults = new ConcurrentHashMap<String,Integer>();
		
		// choose your parameters: 
		String father = "/wiki/Yoda"; // choose the initial character
		int numberOfGenerationRequired = 5; // choose the depth of the research you want
		int numberOfParallelThread = 30; // you can amend the number of threads which will be launched
		
		//1°) Resolution with Java Structures (ConcurrentHashMap only):
		
		finalresults = Research.research(father, numberOfGenerationRequired, numberOfParallelThread);
		
		//2°) Resolution ith home-made structures (we used an OptimisticQueue, written in the package Resolution as class : DataStructure
		
		//finalresults = ResearchHomeMade.homemaderesearch(father, numberOfGenerationRequired, numberOfParallelThread);
	
		// The results of the research is available in the hashMap finalresults 
		 

	}
}
