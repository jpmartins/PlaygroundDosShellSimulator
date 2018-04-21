import java.util.*;


public class CreateDir implements Command{
     public CreateDir(){ super();}
     
     public synchronized void exec(StringTokenizer st,Executor ex) 
                                                       throws FSException{
          try{
               String name = st.nextToken();
               ex.getCurrentDir().createDir(name);
          }catch(NoSuchElementException e){
               throw new MissingParam("<new_dir_name>");
          }
     }
}
