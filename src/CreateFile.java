import java.util.*;


public class CreateFile implements Command{
     public CreateFile(){ super();}
     
     public synchronized void exec(StringTokenizer st,Executor ex)
                                                       throws FSException{
          String cap="";
          try{
               String name = st.nextToken();
               cap = st.nextToken();
               int size = Integer.parseInt(cap);
               int freespace = ex.getCurrentDrive().getFreeSize();
               if(freespace<size)
                    throw new NotEnoughSpace(Integer.toString(freespace));
               ex.getCurrentDir().createFile( name, size );
          }catch(NoSuchElementException e){
               throw new MissingParam("<new_file_name> <new_file_size>");  
          }catch(NumberFormatException e){
               throw new InvalidParam(cap);
          }
     }
}

class NotEnoughSpace extends FSException{
     /**
	 * 
	 */
	private static final long serialVersionUID = 3732445892151407950L;

	public NotEnoughSpace(String name){ super(name); }

     public String getMsg(){
          return "Not Enough Space in Drive, free Space: "+text;
     }
}