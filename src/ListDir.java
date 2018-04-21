import java.util.*;
import java.io.*;

public class ListDir implements Command{
     public ListDir(){ super(); }
     
     public synchronized void exec(StringTokenizer st, Executor ex) throws FSException{
        PrintStream out = ex.getOut();
        String depth="";
        try{
          if(st.hasMoreTokens()){
             depth = st.nextToken();

             Dir curr = ex.getCurrentDir();
             curr.list(out,Integer.parseInt(depth),0);
          }else
             ex.getCurrentDir().list(out, 1,0 );

          int size = ex.getCurrentDir().getSize();
          int freespace = ex.getCurrentDrive().getFreeSize();

          out.println("Space Ocupied by Dir: "+Integer.toString(size));
          out.println("Space free on drive: "+Integer.toString(freespace));

        }catch(NumberFormatException e){
          throw new InvalidParam(depth);
        }
     }
}
