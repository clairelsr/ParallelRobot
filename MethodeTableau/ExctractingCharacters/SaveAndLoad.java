package ExctractingCharacters;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

public class SaveAndLoad {
	public static <E extends Collection<? extends Serializable>> void save(String fileName, E...toSave) throws IOException
	{
		ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(new File(fileName)));
		oos.writeInt(toSave.length); // on indique le nombre d'objets
		for (E e : toSave)
			oos.writeObject(e); // on écrit l'objet
		oos.close();
	}
	
	public static <E extends Collection<? extends Serializable>> List<E> load(String fileName) throws IOException, ClassNotFoundException
	{
		LinkedList<E> list = new LinkedList<E>();
		long startTime = System.currentTimeMillis();
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File(fileName)));
		int n=ois.readInt(); // on lit le nombre d'objets
		long endTime = System.currentTimeMillis();
		System.out.println(endTime-startTime);
		for (int i=0; i<n; i++)
			list.add((E) ois.readObject()); // on lit l'objet
		ois.close();
		
		return list;
	}
	
	public static <E> void display(Collection<? extends Collection<E>> coll)
	{
		for (Collection<E> c : coll)
		{
			System.out.print(c.getClass().getSimpleName()+" \t-->\t");
			for (E e : c)
				System.out.print(e.getClass().getSimpleName()+"\t"+e+"\t");
			System.out.println();
		}
	}
	
	
	/*
	public static void main(String...args) throws IOException, ClassNotFoundException
	{	
		// Programme 1
		
		/*ArrayList<Integer> liste1 = new ArrayList<Integer>();
			liste1.add(1);
			liste1.add(2);
			liste1.add(18);
			
		LinkedList<Double> liste2 = new LinkedList<Double>();
			liste2.add(4.0);
			liste2.add(5.0);
			liste2.add(6.0);
		
		HashSet<String> liste3 = new HashSet<String>();
			liste3.add("7");
			liste3.add("8");
			liste3.add("9");
		
		// Fin du programme 1
		
		save("Test2.txt",liste1,liste2,liste3);

		// Début du programme 2
		
		List<Collection<Serializable>> liste = load("Test2.txt");
		
		// Programme 2
		
		System.out.println("Données chargées : ");
		display(liste);
		
		//Nouvel enregistrement
		
		LinkedList<String> alphabet = new LinkedList<String>();
		for(int i = 0 ; i< 30_000 ; i++){
			alphabet.add("/wiki/Anakin_Skywalker");
		}
		long startTime = System.currentTimeMillis();

		save("Test2.txt",alphabet); // 35 secondes de chargement pour 10 millions de Anakin_Skywalker; 0.1 pour 30k Anakin_Skywalker.
		long endTime = System.currentTimeMillis();
		System.out.println(endTime - startTime);


	} */
}


