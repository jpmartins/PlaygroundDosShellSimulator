import java.io.*;

public class Program{
     private static int NumberOfExecutores;
     private static java.io.File f;
     private static FileSystem sys;
     
     public static void main(String[] args){
         try{
         
           // Iniciaço do ficheiro de Serializaço...
           if(args.length>=1)
             f = new java.io.File(args[0]);
           else
             f = new java.io.File("default.ser");
           
           // Iniciaço/carregamento do Systema de ficheiros
           iniciateFS();
           
           // iniciar as streams de escrita/leitura
           BufferedReader r =
                      new BufferedReader(new InputStreamReader(System.in));
           PrintStream w = System.out;

           // Instancia um o executor principal
           Executor Standard = new Executor(w,r,sys);

           Thread t = new Thread(Standard);
           t.start();
           while (NumberOfExecutores>0)   Thread.sleep(1000);
                          
           try(FileOutputStream fos = new FileOutputStream(f)){
	           // serializa os dados para ficheiro
	           if(f.canWrite())
	               sys.save(fos);
           }
             
          }catch(DriveNotFound e){
               System.err.println("Erro na iniciaço do Excutor");
          }catch(ObjectStreamException e){
               System.err.println("Erro na Serializaço dos Objectos");
         	  e.printStackTrace();
          }catch(Exception e){
               System.err.print("Erro Inesperado3: ");
               System.err.println(e.getClass()+" "+e.getMessage());
         	  e.printStackTrace();
          }
     }

     private static void iniciateFS() 
                              throws IOException, ClassNotFoundException{ 
    	 if(f.canRead()){
    		   try(InputStream inputstream=new FileInputStream(f)){
	               sys = new FileSystem();
	               sys.load(inputstream);
    		   }
           }else{
               sys = new FileSystem(1458128);
               if(f.createNewFile()==false)
            	   System.err.println("unable to read file, file recreated");
           }
     }
     
     public static void incExecs(){ ++NumberOfExecutores; }

     public static void decExecs(){ --NumberOfExecutores; }
}

