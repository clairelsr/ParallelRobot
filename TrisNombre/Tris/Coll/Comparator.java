package Tris.Coll;

public class Comparator extends Thread{
	 int[] l ;
	 int i;
	 int j;
	 int [][] m;
	 
	 public Comparator (int[] l, int i, int j,int [][] m) {
		 this.l = l;
		 this.i = i;
		 this.j = j;
		 this.m=m;
	 }
	 
	 public void run(){
		 if (this.l[i]>this.l[j]) { 
			 this.m[i][j] = 2;
		}
		 else {
			 if(this.l[i]==this.l[j]&&i<j) { this.m[i][j] = 2;}
			 else { this.m[i][j] = 1;}
		 }
	 }
		 
	 
}
