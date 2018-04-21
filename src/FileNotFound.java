
public class FileNotFound extends FSException{
     /**
	 * 
	 */
	private static final long serialVersionUID = 8739830955558087901L;

	public FileNotFound(String name) { super(name); }
     
     public String getMsg(){
          return "File "+text+" not found ";
     }
}