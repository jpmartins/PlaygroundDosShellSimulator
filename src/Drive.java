import java.io.Serializable;

public class Drive implements Serializable{
     /**
	 * 
	 */
	private static final long serialVersionUID = 1184792296082960976L;
     private Dir root;
     private int capacity;

     public Drive(Character letter,int capacity){
          this.capacity=capacity;
          root = new Dir(letter.toString(),null);
          root.setParent(root);
     }
     
     public int getFreeSize(){
          return capacity-root.getSize();
     }

     public Dir getroot(){
          return root;
     }
}
