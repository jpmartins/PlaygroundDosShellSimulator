

public class File extends Entry{
     /**
	 * 
	 */
	private static final long serialVersionUID = -8285059810073578670L;
	private int size;
     
     public File(String name, int size){
          super(name);
          this.size=size;
     }
     
     public int getSize(){ return size; }
}
