package NaiveMethod;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.text.BadLocationException;
import javax.swing.text.EditorKit;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;




public class GatherPageLinks extends Thread{
	String _url;
	HashSet<String> visitedPages;
	LinkedBlockingQueue<String> pileToProcess;
	HashMap<String,Integer> finalresults;
	
	int parallelRead;
	int parallelCheck;
	
	int generation;
	int profondeur;
	
	
	
	public GatherPageLinks(int parallelRead,int parallelCheck,String _url,HashSet<String> visitedPages,
			LinkedBlockingQueue<String> pileToProcess,HashMap<String,Integer> finalresults,int gene,int profondeur){
		this.parallelRead=parallelRead;
		this.parallelCheck=parallelCheck;
		this._url=_url;
		this.visitedPages=visitedPages;
		this.pileToProcess=pileToProcess;
		this.finalresults=finalresults;
		this.generation=gene;
		this.profondeur = profondeur;
	}
	
	public HashMap<String,Integer> getResults(){
		return this.finalresults;
	}
	
	
	public void ReadAllLinks() throws InterruptedException{
		try {
    	
        //Charger la page
        URL url = new URL("http://starwars.wikia.com"+this._url);
        URLConnection uconnection = url.openConnection();
        Reader rd = new InputStreamReader(uconnection.getInputStream());
        //lire le document HTML
        EditorKit kit = new HTMLEditorKit();
        HTMLDocument doc = (HTMLDocument) kit.createDefaultDocument();
        doc.putProperty("IgnoreCharsetDirective", new Boolean(true));
        kit.read(rd, doc, 0);
        //Parcourir la balise lien
        HTMLDocument.Iterator it = doc.getIterator(HTML.Tag.A);
        
        //Parallel Read:
        ReadAndClass[] r = new ReadAndClass[parallelRead];
        for (int i= 0 ; i<parallelRead; i++){
        	r[i] = new ReadAndClass(this._url,visitedPages,pileToProcess,it,finalresults,generation);
        }
        for (int i= 0 ; i<parallelRead; i++){
        	r[i].start();
        }
        
        
        //Parallel Check:
        
        CheckAndSort[] c = new CheckAndSort[parallelCheck];
        for (int i= 0 ; i<parallelCheck; i++){
        	c[i] = new CheckAndSort(visitedPages,pileToProcess,finalresults,parallelRead,parallelCheck,generation,profondeur);
        }
        
        for (int i= 0 ; i<parallelRead; i++){
        	r[i].join();
        }
        
        for (int i= 0 ; i<parallelCheck; i++){
        	c[i].start();
        }
        
        
        //Parallel join:
        for (int i= 0 ; i<parallelCheck; i++){
        	c[i].join();
        }
        
        
        
        
        return;
       
		} catch (BadLocationException ex) {
        Logger.getLogger(GatherPageLinks.class.getName()).log(Level.SEVERE, null, ex);
		} catch (IOException ex) {
        Logger.getLogger(GatherPageLinks.class.getName()).log(Level.SEVERE, null, ex);
		}		
	}
	
	
	public void run(){
		try {
			if(generation <= profondeur){this.ReadAllLinks();}
			else{return;}
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
