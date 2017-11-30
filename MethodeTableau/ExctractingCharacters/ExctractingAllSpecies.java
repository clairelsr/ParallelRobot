package ExctractingCharacters;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.net.URLConnection;
import java.util.concurrent.LinkedBlockingQueue;

import javax.swing.text.BadLocationException;
import javax.swing.text.EditorKit;
import javax.swing.text.SimpleAttributeSet;
import javax.swing.text.html.HTML;
import javax.swing.text.html.HTMLDocument;
import javax.swing.text.html.HTMLEditorKit;

public class ExctractingAllSpecies{
	LinkedBlockingQueue<String> species;
	
	public ExctractingAllSpecies (){
		this.species = new LinkedBlockingQueue<String>();
	}
		
	
	
	public void ExctractingSpeciesIndividual(int page) throws IOException, BadLocationException{
	
		String _url;
		if(page == 1){_url = "http://starwars.wikia.com/wiki/Category:Individuals_by_species";}
		else{_url = "http://starwars.wikia.com/wiki/Category:Individuals_by_species?page="+page;}
		
		URL url = new URL(_url);
        URLConnection uconnection = url.openConnection();
        Reader rd = new InputStreamReader(uconnection.getInputStream());
        //lire le document HTML
        EditorKit kit = new HTMLEditorKit();
        HTMLDocument doc = (HTMLDocument) kit.createDefaultDocument();
        doc.putProperty("IgnoreCharsetDirective", new Boolean(true));
        kit.read(rd, doc, 0);
        //Parcourir la balise lien
        HTMLDocument.Iterator it = doc.getIterator(HTML.Tag.A);
        
        while(it.isValid()){
            SimpleAttributeSet s = (SimpleAttributeSet) it.getAttributes();
            String link = (String) s.getAttribute(HTML.Attribute.HREF);
            
            if(link!=null){    			
    			if ((link.startsWith("/wiki/Category:")) && 
    						!(link.startsWith("/wiki/Category:Individuals")) &&
    							!(link.startsWith("/wiki/Category:Wookieepedia")) && 
    								!(link.startsWith("/wiki/Category:Star_Wars")) && 
    									!(link.startsWith("/wiki/Category:Browse")) && 
    										!(link.startsWith("/wiki/Category:Forum")) && 
    											!(link.startsWith("/wiki/Category:Sentient"))){
    				this.species.add(link);
    			}
            }
            it.next();
        }
        return;
	}
	
	
	
}
