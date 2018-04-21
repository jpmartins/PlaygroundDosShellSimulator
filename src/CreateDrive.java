import java.util.*;


public class CreateDrive implements Command{
     public CreateDrive(){ super();}
          
     public synchronized void exec(StringTokenizer st,Executor ex)throws FSException{
        String cap="";
        try{
          String letter = st.nextToken();
          if(letter.length()>1)
               throw new InvalidParam("<drive_name>");
          cap = st.nextToken();
          int capac = Integer.parseInt(cap);
          ex.getFSystem().addDrive(new Character(letter.charAt(0)),capac);
        }catch(NoSuchElementException e){
          throw new MissingParam("<new_drive_letter> <new_drive_size>");
        }catch(NumberFormatException e){
          throw new InvalidParam(cap);
        }
     }
}