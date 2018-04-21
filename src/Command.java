import java.util.*;

public interface Command
{
     public void exec(StringTokenizer t,Executor e) throws FSException;
}
