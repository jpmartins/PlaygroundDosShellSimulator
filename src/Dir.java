import java.io.*;
import java.util.*;

@SuppressWarnings({ "rawtypes", "unchecked" })
public class Dir extends Entry{
     /**
	 * 
	 */
	private static final long serialVersionUID = 7323374964523190623L;

	
	private Map  entrys = java.util.Collections.synchronizedMap( new HashMap());
     private Dir  parent;

     public Dir(String name, Dir par){
          super(name);
          parent=par;
     }
     
     public void addEntry(Entry entry) throws FSException{
          if(entrys.containsKey(entry.getName()))
               throw new DuplicateEntry( entry.getName() );
          entrys.put(entry.getName(),entry);
     }
     
     public void createDir(String name) throws FSException{
          this.addEntry( new Dir(name,this) );
     }
     
     public void createFile(String name,int size) throws FSException {
          this.addEntry(new File(name,size));
     }

     public void removeFile(String name) throws FSException{
          File aux = (File)entrys.get(name);
          if(aux == null)
                throw new FileNotFound(name);
          removeEntry(aux);
     }

     public void removeDir(String name) throws FSException{
          Dir aux = (Dir)entrys.get(name);
          if(aux == null)
                throw new DirNotFound(name);
          removeEntry(aux);
     }

     private void removeEntry(Entry e) throws FSException{
            if(!e.isfree())
              throw new OcupiedEntry(e.getName());
            entrys.remove(e.getName());
     }    

     public Entry getEntry(String name) throws FSException{
          Entry aux = (Entry)entrys.get(name);
          if( aux==null )
               throw new EntryNotFound(name);
          return aux;
     }

     public Dir getParent(){
          return parent;
     }

     public void setParent(Dir nextp){
          parent = nextp;
       }

     public int getSize(){
          Iterator i = entrys.values().iterator();
          int size=0;
          while(i.hasNext())
               size += ((Entry)i.next()).getSize() ;
          return size;
     }

     public void list(PrintStream out, int depth, int currlevel) 
     {
          out.print("<dir> ");
          super.list(out, 0, currlevel);
          Iterator i = entrys.values().iterator();
          if(depth!=0){
               while(i.hasNext()){
                    for(int j = 0; j <= currlevel ; ++j)
                         out.print("  ");
                    ((Entry)(i.next())).list(out, depth-1, currlevel+1);
               }
          }
     }

     public void removeExecutorInAll(){
        this.decExecutores();
        if(this == parent)
          return;
        parent.removeExecutorInAll();
    }
}

class OcupiedEntry extends FSException{
     /**
	 * 
	 */
	private static final long serialVersionUID = 8902148812006794117L;

	public OcupiedEntry(String name){ super(name); }
  
     public String getMsg(){ 
          return "Ocupied Entry : "+text+" not available operation"; 
     }
}

class DuplicateEntry extends FSException{
     /**
	 * 
	 */
	private static final long serialVersionUID = 729082295610372632L;

	public DuplicateEntry(String name){ super(name); }

     public String getMsg(){
          return "Entry "+text+" duplicated, can't create";
     }
}

class EntryNotFound extends FSException{
     /**
	 * 
	 */
	private static final long serialVersionUID = 7309432246795095610L;

	public EntryNotFound(String name) { super(name); }
     
     public String getMsg(){
          return  "Entry "+text+" not found ";
     }
}