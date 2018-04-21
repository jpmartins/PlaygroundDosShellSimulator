import java.util.*;


public class RemoveFile implements Command{
     public RemoveFile(){ super(); }
     
     public synchronized void exec(StringTokenizer st, Executor ex)
                                                       throws FSException{
          String name="";
          try{
               name = st.nextToken();
               ex.getCurrentDir().removeFile(name);
          }catch(NoSuchElementException e){
               throw new MissingParam("<remove_entry_name>");
          }
     }
}
