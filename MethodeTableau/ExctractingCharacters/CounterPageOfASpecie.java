package ExctractingCharacters;

public class CounterPageOfASpecie extends Thread{
	String _url;
	int count;
	
	public CounterPageOfASpecie(String _url){
		this._url = _url;
	}
	
	public void run() {
		String page = LirePage.getTextFile(_url);
		String find ="data-page=\"";
		int debut = page.indexOf(find,70000);
		
		if(debut>0) {
			int avantdernier =debut;
			int avantavantdernier=debut;
			while (debut>=0) {
				avantavantdernier = avantdernier;
				avantdernier = debut;
				debut = page.indexOf(find,debut+1);
			}
			int fin =page.indexOf("\"" ,avantavantdernier+find.length());
			String number = page.substring(avantavantdernier+find.length(), fin);
			this.count =  Integer.parseInt(number);
			System.out.println(count);

		}
	}
	
	
}
