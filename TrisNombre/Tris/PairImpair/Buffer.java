package Tris.PairImpair;

public class Buffer {
    int val;
    public Buffer() { val = -1; } 

    synchronized void write(int v) {
        while (val!=-1) {
            try { wait(); } catch (InterruptedException e) {};
        }; 
        val = v;
        notifyAll();
    }

    synchronized int read() {
        while (val==-1) {
            try { wait(); } catch (InterruptedException e) {};
        }; 
        int n = val;
        val = -1;
        notifyAll();
        return n;
    }
}
