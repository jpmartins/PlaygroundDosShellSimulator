import java.util.*;


public  class ChangeDrive implements Command{
     public ChangeDrive(){ super(); }
     
     public synchronized void exec(StringTokenizer st,Executor ex)
                                                       throws FSException{
          try{
             String letter = st.nextToken();
             if(letter.length()>1)
               throw new InvalidParam("<drive_name>");
             Character let = Character.valueOf(letter.charAt(0));
             ex.getCurrentDir().removeExecutorInAll();
             ex.setCurrentDrive(let);
          }catch(NoSuchElementException e){
             // excepço no nextToken() => numero de parametros invalido
             throw new MissingParam("<drive_name>");   
          }
     }
}
