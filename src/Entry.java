import java.io.*;

public abstract class Entry implements Serializable{
     /**
	 * 
	 */
	private static final long serialVersionUID = -1261801706039455400L;
	private String name;
     transient private int nexecutores = 0;

     public Entry(String name) { this.name=name; }

     public final String getName() { return name; }

     public void list(PrintStream out ,int depth, int currlevel)
     {
          out.print(name+" ");
          out.println( Integer.valueOf( getSize() ) ); //valueOf uses cache, more efficient the using constructor
     }

     public abstract int getSize();

     public boolean isfree(){ return nexecutores==0;}

     public void incExecutores(){ nexecutores++; }

     public void decExecutores(){ nexecutores--; }

}
