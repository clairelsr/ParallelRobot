package Resolution;

public class DataStructure {
	final private Node head;
	
	
	// The constructor
	public DataStructure(){
		Node min = new Node(Integer.MIN_VALUE);
		Node max = new Node(Integer.MAX_VALUE);
		min.setNext(max);
		this.head = min;
	}

	
	
	// Auxiliary function letting know whether nodes are really linked
	
	public boolean validate(Node pred, Node curr){
		Node node = this.head;
		while(node.getKey() <= pred.getKey()){
			if(node == pred){
				return pred.getNext() == curr;
			}
			else{
				node = node.getNext();
			}
		}
		return false;
	}
	
	
	
	// Add an element into the DataStructure 
	public boolean add (String element){
		int key = element.hashCode();
		while(true){
			Node pred = head;
			Node curr = head.getNext();
			while(curr.getKey() < key){
				pred = curr;
				curr = curr.getNext();
			}
			pred.lock();
			try{
				curr.lock();
				try{
					if(validate(pred,curr)){
						if(curr.getValue().equals(element)){return false;}
						else{
							// Si les éléments curr et element sont distincts mais ont la même clé, on recommence 
							//tout en modifiant la valeur de la clé
							if(curr.getKey() == key){key++;} 
							else{
								Node node = new Node(element, key,curr);
								pred.setNext(node);
								return true;
							}
						}
					}
				}finally{
					curr.unlock();
				}
			}finally{
				pred.unlock();
			}	
		}
	}
	
	
	
	// Letting know whether the DataStructure contains a specified element
	
	public boolean contains(String element){
		int key = element.hashCode();
		while(true){
			Node pred = head;
			Node curr = head.getNext();
			while(curr.getKey() < key){
				pred = curr;
				curr = curr.getNext();
			}
			pred.lock();
			try{
				curr.lock();
				try{
					if(validate(pred,curr)){
						// We build the structure such as item and key are univocal 
						if(curr.getValue().equals(element)){return true;}
						else{return false;}
					}
				}finally{
					curr.unlock();
				}
			}finally{
				pred.unlock();
			}	
		}
	}
}
