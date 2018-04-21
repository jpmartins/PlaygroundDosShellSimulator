import java.util.*;


public class ChangeDir implements Command{
     public ChangeDir(){ super(); }
     
     public synchronized void exec(StringTokenizer st, Executor ex)throws FSException{
          try{
               String name = st.nextToken();
               if( name.equals("..") )
               {
                    if(ex.getCurrentDir()!=ex.getCurrentDrive().getroot())
                    {
                        ex.getCurrentDir().decExecutores();
                        ex.setParentAsCurr();
                    }
               }
               else
               {
                    ex.setCurrentDir( name );
                    ex.getCurrentDir().incExecutores();
               }
          }catch(NoSuchElementException e){
          // excepço no nextToken() => numero de parametros invalido
               throw new MissingParam("<dir_name>");
          }
     }

}