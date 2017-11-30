package Resolution;
import java.io.IOException;
	import java.io.InputStreamReader;
	import java.io.Reader;
	import java.net.URL;
	import java.net.URLConnection;
import java.util.LinkedList;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
	import java.util.logging.Logger;

	import javax.swing.text.BadLocationException;
	import javax.swing.text.EditorKit;
	import javax.swing.text.SimpleAttributeSet;
	import javax.swing.text.html.HTML;
	import javax.swing.text.html.HTMLDocument;
	import javax.swing.text.html.HTMLEditorKit;


	// For the given _url, will go through all the links of the page, check whether it is a character, 
	//and will store the results into the finalresults (if not already existing)
	// and add it to the list containing all the next links to explore

public class ReadAllLinksFromAPageWithHomeMadeDataStructure extends Thread{
	
		
		DataStructure characters; 
		ConcurrentHashMap<String,Integer> finalresults;
		String father; //the origin of the research
		int generation;
		LinkedBlockingQueue<String> pageToExplore;

		

		// The constructor
		
		public ReadAllLinksFromAPageWithHomeMadeDataStructure(DataStructure characters, // NEED TO FIND THE PROPER THREAD-SAFE STRUCTURE
		ConcurrentHashMap<String,Integer> finalresults,
		String father,
		int generation,	LinkedBlockingQueue<String> pageToExplore

		){
			this.characters = characters;
			this.finalresults = finalresults;
			this.father = father;
			this.generation = generation;
			this.pageToExplore = pageToExplore;
		}
		
		
		
		public void run(){
			while (!pageToExplore.isEmpty()){
				
				
				try {
			    	String _url = pageToExplore.take();
			        String page = ExctractingCharacters.LirePage.getTextFile(_url);
			        int debut = page.indexOf("/wiki/Template:Character", 0);
					int fin =  page.indexOf("div>In other languages", debut);
					if (debut==-1) {
						return;	
					}	
					String motini = "<a href=\"" ;//href="
					String motfin ="\" title";
					LinkedList<String> RepererMot  = RepererMot (debut, fin, motini, motfin,page);
			        for(String link : RepererMot){
			        	if(link!=null && finalresults.containsKey(link)){
			            	if(finalresults.get(link) > generation){
			            		finalresults.put(link, generation);
			            	}
			            }

			            else{ //first, we eliminate a lot of unreasonable links, which couldn't be a link for a charachter
			            	if ((link!=null) && (!(link.startsWith(_url))) && (!(link.startsWith(father))) &&
			            			(link.startsWith("/wiki/")) &&  
			    					!(link.endsWith("/Legends")) && !(link.endsWith("/Canon")) &&
			    						!(link.startsWith("/wiki/Wookieepedia")) &&
			    							!(link.startsWith("/wiki/Category")) &&
			    								!(link.startsWith("/wiki/Forum")) &&
			    									!(link.startsWith("/wiki/Special:")) &&
			    										!(link.equals("/wiki/Executor")) && !(link.equals("/wiki/Star_Wars_Legends")) && !(link.equals("/wiki/Main_Page")) && !(link.equals("/wiki/Local_Sitemap")) && !(link.equals("/wiki/Help:Contents")) &&     
			    											!(link.startsWith("/wiki/Talk:")) &&
			    												!(link.startsWith("/wiki/List_of_Star")) && 
			    													!(link.startsWith("/wiki/Template:")) &&
			    														!(link.startsWith("/wiki/The_Official_Star_Wars")) &&
			    															!(link.startsWith("/wiki/New_Jedi_Order")) && !(link.startsWith("/wiki/Jedi")) && !(link.startsWith("/wiki/Sith")) &&
			    																!(link.startsWith("/wiki/Fate_of_the_Jedi")) &&
			    																	!(link.startsWith("/wiki/File:")) &&
			    																		!(link.startsWith("/wiki/Article")) &&
			    																			!(link.equals("/wiki/Lightsaber_combat")) && !(link.equals("/wiki/Galactic_Republic")) && !(link.equals("/wiki/The_Force")) && !(link.equals("/wiki/The_galaxy")) && !(link.equals("/wiki/Timeline_of_galactic_history")) && !(link.equals("/wiki/Clone_Wars")) && !(link.equals("/wiki/Grand_Army_of_the_Republic")) && !(link.equals("/wiki/Confederacy_of_Independent_Systems")) &&
			    																				!(link.startsWith(_url)) && 
			    																					!(link.startsWith("/wiki/Star_Wars")) && !(link.startsWith("/wiki/StarWars")) &&
			    																						!(link.startsWith("/wiki/Encyclopedia_")) &&
			    																							!(link.startsWith("/wiki/Databank_")) &&
			    																								!(link.startsWith("/wiki/LEGO_Star_Wars")) &&
			    																									!(link.startsWith("/wiki/William_Shakespeare")) &&
			    																										!(link.startsWith("/wiki/Attack_of_the_Clones_"))){
			                	boolean pasunedatedate=true;
			                	for (int i = 1980; i<2018; i++){
			                		pasunedatedate = pasunedatedate && !link.equals("/wiki/"+i);
			                	}
			                	if(pasunedatedate){
			                		
			                		
			                		
			                		// and finally, we check whether the link is really a character
			                		
			                		if(characters.contains(link)){
			                			System.out.println(link);
			                			finalresults.put(link, generation);                			
			                		}
			                	}
			            	}
			            																				
			                	
			            }

			        }
			       
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
			        }	
	}
		
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
}


