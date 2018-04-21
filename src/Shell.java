import java.io.*;
import java.util.*;

public class Shell implements Command{
     public Shell(){ super(); }
     
     public synchronized void exec(StringTokenizer st, Executor e) 
                                                       throws FSException{
          String name="";
          try{
               name = st.nextToken();
               BufferedReader r = new BufferedReader( new InputStreamReader(
                                             new FileInputStream(name) 
                                                            ) );
               name = st.nextToken();
               PrintStream w;
               w = new PrintStream(new FileOutputStream(name));

              Thread t = new Thread(new ShellExecutor(w,r,e.getFSystem()));
              t.start();    // shellExecutor
          }catch(NoSuchElementException f){
               throw new MissingParam("<file_commands> <file_output>");
          }catch(FileNotFoundException f){
               throw new FileNotFound(name);
          }
     }
}
