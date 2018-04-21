import java.util.*;
import java.io.*;

@SuppressWarnings("unchecked")
public class FileSystem implements Serializable{
     /**
	 * 
	 */
	private static final long serialVersionUID = -9215635452942335864L;

	@SuppressWarnings("rawtypes")
	private Map drives = java.util.Collections.synchronizedMap(new HashMap());

     public FileSystem(){}
     public FileSystem(int size){
      drives.put(new Character('c'), new Drive(new Character('c'),size));
     }

     public void addDrive(Character l,int size) throws FSException{
          if( drives.containsKey(l))
               throw new DuplicateDrive(l.toString());
          drives.put( l,new Drive(l,size) );
     }

     public Drive getDrive(Character l) throws FSException{
          Drive tmp = (Drive)drives.get(l);
          if(tmp==null) throw new DriveNotFound( l.toString() );
          return tmp;
     }

  public void load(InputStream file) 
          throws IOException, ClassCastException, ClassNotFoundException
  {
      //carrega a estruturas serializadas noutras alturas caso existam
      ObjectInputStream ois = new ObjectInputStream( file );
      this.drives = ((FileSystem) ois.readObject()).drives;
      ois.close();
  }

  public void save(OutputStream os) 
          throws ObjectStreamException, IOException
  {
     ObjectOutputStream oos = new ObjectOutputStream(os);
     oos.writeObject(this);
     oos.close();
/* + possiveis excepções InvalidClassException, NotSerializableException */
  }
}

class DriveNotFound extends FSException{
     /**
	 * 
	 */
	private static final long serialVersionUID = -3025433728474293241L;

	public DriveNotFound(String name){ super(name); }
     
     public String getMsg(){
          return "Drive "+text+" not found";
     }
}

class DuplicateDrive extends FSException{
     /**
	 * 
	 */
	private static final long serialVersionUID = 2413166414008524786L;

	public DuplicateDrive(String name) { super(name); }

     public String getMsg(){
          return  "duplicated Drive, can't create drive";
     }
}