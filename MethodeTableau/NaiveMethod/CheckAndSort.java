package NaiveMethod;

import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.LinkedBlockingQueue;


public class CheckAndSort extends Thread{
	//Remove elements form pileToProcess
	//Check for each if it is a character
	//If yes, put it in the HashMap of resutls
	
	LinkedBlockingQueue<String> pileToProcess;
	HashMap<String,Integer> resultatsfinaux;
	HashSet<String> visitedPages;
	
	int parallelRead;
	int parallelCheck;
	
	int generation;
	int profondeur;
	

	
	
	public CheckAndSort(HashSet<String> visitedPages,LinkedBlockingQueue<String> pileToProcess,HashMap<String,Integer> resultatsfinaux,
			int parallelRead,int parallelCheck,
			int generation,int profondeur){
		this.visitedPages = visitedPages;
		this.parallelCheck = parallelCheck;
		this.parallelRead = parallelRead;
		this.pileToProcess=pileToProcess;
		this.resultatsfinaux=resultatsfinaux;
		this.generation=generation;
		this.profondeur = profondeur;
	}
	
	public void run(){
		try {
			sleep(10);
		} catch (InterruptedException e2) {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
		while(!pileToProcess.isEmpty()){
			String linkToCheck;
			try {
				linkToCheck = this.pileToProcess.take();
				String page =  LirePage.getTextFile("http://starwars.wikia.com"+linkToCheck);
				
				if (page.indexOf("Biographical information", 0)>=0 && page.indexOf("Species", 0)>=0) {
		             this.resultatsfinaux.put(linkToCheck, generation);
		             System.out.println(linkToCheck);
		             GatherPageLinks g= new GatherPageLinks(parallelRead,parallelCheck,linkToCheck,
		            		 visitedPages,pileToProcess,this.resultatsfinaux,generation+1,profondeur);
		             
		             g.start();
		             g.join();

				}
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		
		}
		return;
	}
		
			
		
	}
	

