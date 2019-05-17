package CRUD;
import java.io.File;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

public class ModeloArrayListFileObj extends ModeloArrayList
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	static final String nombrefichero = "productos.csv";
    
    public ModeloArrayListFileObj()
    {
       //super();
       cargarDesdeFichero();
       
    }
    
    private void salvarAfichero(){
        File fproductos =new File (nombrefichero);
        try{
         // Creo Flujo de tipo fichero de byte 
         FileOutputStream fos= new FileOutputStream(fproductos);
         // Creo un Flujo de objetos sobre el fichero
         ObjectOutputStream oos= new ObjectOutputStream(fos);
         // Escribo la lista de productos al fichero
         for (Producto pro: lista){
             oos.writeObject(pro);          
            }
         oos.writeObject(new Producto (1001,  "Morcillas ",       10,     5,     10.5f));
         oos.writeObject(new Producto (1002,  "Chorizo"   ,       30,     5,     12.5f));
         oos.writeObject(new Producto (1003,  "Chope"   ,         70,     15,    4.5f));
         oos.writeObject(new Producto (1004,  "Fuet"     ,         5,     25,    2.5f));
         oos.writeObject(new Producto (2001,  "Tocino"   ,        10,     5,     1.5f));
         oos.writeObject(new Producto (3021,  "Jamón York",       60,     50,    10.0f));
         oos.writeObject(new Producto (1001,  "Cecina",           10,     15,    15.0f));
         oos.writeObject(new Producto (6001,  "Mortadela",        20,     5,     20.5f));
         oos.writeObject(new Producto (4201,  "Torreznos",      2340,     5,     13.7f));
         oos.writeObject(new Producto (3011,  "Jamón Serrano",    10,     3,     12.5f));
         oos.writeObject(new Producto (1501,  "Lacón ",           10,     15,    20.5f));
         oos.close(); 
         fos.close();
       }catch(IOException ioe){
            ioe.printStackTrace();
        }  
        
    }
    
    private void cargarDesdeFichero() {
    	
        File fproductos =new File (nombrefichero);
        if ( !fproductos.exists() ){
           return; // No hay datos
        }
        try{
         // Creo Flujo de tipo fichero de byte 
         FileInputStream fin= new FileInputStream(fproductos);
         // Creo un Flujo de objetos sobre el fichero
         ObjectInputStream oin= new ObjectInputStream(fin);
         
         try {
              Producto pro = (Producto) oin.readObject();
              while ( true ){
                lista.add(pro); // Añado el producto 
                pro = (Producto) oin.readObject();  
                }
            }
          catch (IOException ex){} 
         
         oin.close(); // Cierro el fujo de objectos
         fin.close(); // Cierro el flujo de 
       }catch(IOException ioe){
            ioe.printStackTrace();
        }    
       catch (ClassNotFoundException ex){
            ex.printStackTrace();
        }    
    }

    public boolean insertarProducto ( Producto p){
      boolean resu = super.insertarProducto(p);
      if ( resu ){
          salvarAfichero();
      }
      return resu;
    }
 
    public boolean borrarProducto ( int codigo ){
      boolean resu = super.borrarProducto(codigo);
      if ( resu ){
          salvarAfichero();
        }
      return resu;
    }
    
    
    public boolean modificarProducto (Producto nuevo){
       boolean resu = super.modificarProducto(nuevo);
       if ( resu ){
           salvarAfichero();
        }
       return (resu);
    }
    
    public void listarProductos (){
        int i = 1;
        for ( Producto p: lista){
            System.out.println(" Nº "+i+" "+p);
            i++;
        }
    }
    
        
}    