package NaiveMethod;

import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;


public class ReadAndClass extends Thread{
	//Browse all links of "it" (all links in the url
	//Put them in the global HashSet visitedPages if possible characters
	//Put them also in the pileToProcess
	
	String _url;
	
	HashSet<String> visitedPages;
	LinkedBlockingQueue<String> pileToProcess;
	HTMLDocument.Iterator it;
	
	HashMap<String,Integer> resultatsfinaux;
	int generation;

	
	public ReadAndClass(String _url,HashSet<String> visitedPages,LinkedBlockingQueue<String> pileToProcess,HTMLDocument.Iterator it,
			HashMap<String,Integer> resultatsfinaux,int generation){
		this._url=_url;
		this.visitedPages=visitedPages;
		this.pileToProcess=pileToProcess;
		this.it=it;
		this.resultatsfinaux=resultatsfinaux;
		this.generation=generation;
		
	}
	
	public void run(){
		while(it.isValid()){
            SimpleAttributeSet s = (SimpleAttributeSet) it.getAttributes();
            String link = (String) s.getAttribute(HTML.Attribute.HREF);
            
            /*if(link!=null && !visitedPages.contains(link)){    			
    			if ((link.startsWith("/wiki/")) && 
    					!(link.endsWith("/Legends")) && 
    						!(link.startsWith("/wiki/Wookipedia")) &&
    							!(link.startsWith("/wiki/Category")) &&
    								!(link.startsWith("/wiki/Forum")) &&
    									!(link.startsWith("/wiki/Special:")) &&
    									!(link.equals("/wiki/New_Jedi_Order_era")) && !(link.equals("/wiki/Star_Wars_Legends")) && !(link.equals("/wiki/Main_Page")) && !(link.equals("/wiki/Local_Sitemap")) && !(link.equals("/wiki/Article_nominations")) && !(link.equals("/wiki/Help:Contents")) &&     
    											!(link.startsWith("/wiki/Talk:")) &&
    												!(link.startsWith("/wiki/List_of_Star")) && 
    													!(link.startsWith("/wiki/Template:"))){
    				pileToProcess.add(link);
    				visitedPages.add(link);
    			}
            }  */
            if(link!=null && resultatsfinaux.containsKey(link)){
            	if(resultatsfinaux.get(link) >generation){resultatsfinaux.put(link, generation);}
            }
            else{
            	if(link!=null && !visitedPages.contains(link)){ 
            	visitedPages.add(link);
            	if ((link.startsWith("/wiki/")) && 
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
                	if(pasunedatedate){pileToProcess.add(link);}
            	}
            																				
                	
                }
            }
            it.next();
		}
		return;
	}
	
}
