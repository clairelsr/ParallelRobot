package Resolution;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Node {

        final private String item;
        final private int key;
        private volatile Node next;
        final private Lock lock;

        public Node(String value, int key, Node next) {
            this.item = value;
            this.key = key;
            this.next = next;
            this.lock = new ReentrantLock();
	}

        public Node(int key) {
        	this.item="";
            this.key = key;
            this.next=null;
            this.lock = new ReentrantLock();
        }

        public String getValue() {
            return item;
        }
        
        public int getKey() {
            return key;
        }

        public void setNext(Node next) {
            this.next = next;
        }

        public Node getNext() {
            return next;
        }

	public void lock() {
	    this.lock.lock();
	}
	
	public void unlock() {
	    this.lock.unlock();
	}
    }