import java.util.*;


public class RemoveDir implements Command{
     public RemoveDir(){ super(); }
     
     public synchronized void exec(StringTokenizer st, Executor ex) 
                                                       throws FSException{
          String name="";
          try{
               name = st.nextToken();
               ex.getCurrentDir().removeDir(name);
          }catch(NoSuchElementException e){
               throw new MissingParam("<remove_entry_name>");    
          }
     }
}
