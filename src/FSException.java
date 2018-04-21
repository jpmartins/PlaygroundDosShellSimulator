
public abstract class FSException extends Exception{
     /**
	 * 
	 */
	private static final long serialVersionUID = -5255046480972196151L;
	public String text;
     public FSException(String t) { text = t; }
     public String getMsg() { return text; }
}
