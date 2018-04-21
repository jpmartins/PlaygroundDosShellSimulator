import java.util.*;

public class ExitCmd implements Command{
     public ExitCmd(){ super();}
     
     public synchronized void exec(StringTokenizer b,Executor ex) throws FSException {
          ex.getCurrentDir().removeExecutorInAll();
          ex.setEnd(); 
     }
}
