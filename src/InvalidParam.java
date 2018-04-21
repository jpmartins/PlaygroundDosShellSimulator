
public class InvalidParam extends FSException{
     /**
	 * 
	 */
	private static final long serialVersionUID = 515683592615301270L;

	public InvalidParam(String name){ super(name); }
     
     public String getMsg(){
          return "Parameter "+text+" is not valid";
     }
}