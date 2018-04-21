
public class DirNotFound extends FSException{
     /**
	 * 
	 */
	private static final long serialVersionUID = -4852995075276486991L;

	public DirNotFound(String name) { super(name); }
     
     public String getMsg(){
          return "Dir "+text+" not found ";
     }
}