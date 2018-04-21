import java.io.*;


public class ShellExecutor extends Executor{

    public ShellExecutor(PrintStream w,BufferedReader r,FileSystem f) 
                                                       throws FSException {
      super(w,r,f);
    }
    public void run(){
       try{
          while( !exit )
          {
              execmd( in );
          }
      }catch(FSException e){
          out.println(e.getMsg());
      }catch(Exception e){
    	  e.printStackTrace();
          out.println( "Erro Inesperado2: "+e.getClass()+" "+e.getMessage() );
       }finally{
          try{
            Program.decExecs();
            out.close();
            in.close();
          }catch(Exception f){f.printStackTrace();}
       }
     }
     
}