

public class MissingParam extends FSException{
     /**
	 * 
	 */
	private static final long serialVersionUID = -5439756067863504291L;

	public MissingParam(String name){ super(name); }

     public String getMsg(){
          return "Missing Parameters "+text;
     }
}