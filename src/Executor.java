import java.util.*;
import java.io.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class Executor implements Runnable{
     private FileSystem fs;
	private static HashMap commands;

     static
     {
          commands = new HashMap();
          commands.put("createDrive", new CreateDrive());
          commands.put("createDir"  , new CreateDir()  );
          commands.put("createFile" , new CreateFile() );
          commands.put("removeFile" , new RemoveFile() );
          commands.put("removeDir"  , new RemoveDir()  );
          commands.put("cd"         , new ChangeDir()  );
          commands.put("drive"      , new ChangeDrive());
          commands.put("dir"        , new ListDir()    );
          commands.put("exit"       , new ExitCmd()    );
          commands.put("shell"      , new Shell()      );
     }

     private Drive currentDrive;
     private Dir currentDir;
     protected PrintStream out;
     protected BufferedReader in;
     protected boolean exit = false;


 public Executor(PrintStream w,BufferedReader r,FileSystem f) 
                                                       throws FSException {
          out = w;
          in  = r;
          //inciaço por defeito do Executor
          fs = f;
          currentDrive = fs.getDrive(new Character('c'));
          currentDir   = currentDrive.getroot();
          currentDir.decExecutores();
          Program.incExecs();
 }

     public void run(){
       try{

          while( !exit )
          {
            try{
             out.print("prompt>");
             execmd( in );
            }catch(FSException e){
              out.println(e.getMsg());
            }
          }
       }catch(Exception e){
    	  e.printStackTrace();
          out.println( "Erro Inesperado1: "+e.getClass()+" "+e.getMessage());
       }finally{
          try{
            Program.decExecs();
            out.close();
            in.close();
          }catch(Exception f){f.printStackTrace();}
       }
     }

     @SuppressWarnings("serial")
	protected void execmd(BufferedReader inp)throws FSException{
          try{
            StringTokenizer commandline=new StringTokenizer(inp.readLine());
            if(commandline.hasMoreTokens()){
                 String word = commandline.nextToken();
                 if( commands.containsKey( word ) ){
                      Command aux = (Command)(commands.get(word));
                      aux.exec( commandline, this );
                 }else
                      throw new FSException("Comand Unknow"){ };
            }
          }catch(IOException e){
              exit = true;
          }
      }

     public Drive getCurrentDrive(){ return currentDrive; }

     public void setCurrentDrive(Character l) throws FSException{
          currentDrive = fs.getDrive(l);
          currentDir = currentDrive.getroot();
     }
     
     public Dir getCurrentDir(){ return currentDir; }

     public void setCurrentDir(String name) throws FSException{
       try{
          Entry aux  = currentDir.getEntry(name);
          currentDir = (Dir)aux;
       }catch(FSException e){
          throw new DirNotFound(name);
       }
     }

     public void setParentAsCurr(){ currentDir = currentDir.getParent(); }

     public void setEnd() { exit = true; }

     public PrintStream getOut() { return out; }

     public FileSystem getFSystem(){ return fs; }
}