package biblioteca;
/**
 * Write a description of class Biblioteca_HashMap here.
 * UTILIZA LA SERIALIZACIÓN GRABANDO EL HASHMAP COMPLETO
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.io.*;

public class Biblioteca_HashMap
{
    
    static final String LIBROS_DISPONIBLES = "disponibles.objetos";
    static final String LIBROS_PRESTADOS   = "prestados.objetos";
    
    
   public static void main ( String argv[]){
       
       HashMap <String,Libro> disponibles = new HashMap <String,Libro> ();
       HashMap <String,Libro> prestados   = new HashMap <String,Libro> ();
       
       Scanner sc = new Scanner(System.in);
       
       
       // Rellenar las listas con valores de prueba
       Libro nuevo = new Libro(19,"El Quijote"," Cervantes", Genero.NOVELA, 2012);
       
       disponibles.put(nuevo.titulo, nuevo);
       disponibles.put("El Buscón", new Libro(20,"El Buscón","Quevedo",Genero.NOVELA,1997));
       disponibles.put("Romeo y Julieta",new Libro(21,"Romeo y Julieta","Shakespeare ",Genero.TEATRO,1997));
       disponibles.put("Mio Cid",new Libro(22,"Mio Cid","Anónimo",Genero.POESIA,1989));
   
       
       prestados.put("Harry Potter I", new Libro(30,"Harry Potter I", "J. K. Rowling", Genero.NOVELA,1998));
       prestados.put("Guerra y Paz", new Libro(34,"Guerra y Paz","León Tolstói",Genero.NOVELA,1975));
       prestados.put("El Decamerón" ,new Libro(19,"El Decamerón","Giovanni Boccaccio",Genero.CUENTOS,1992));
      
       /*
       // leo del fichero
       disponibles = cargarCatalogo(LIBROS_DISPONIBLES);
       prestados   = cargarCatalogo(LIBROS_PRESTADOS);
       */
       
       System.out.println("Libros prestados:");
       verCatalogo(prestados);
      
       System.out.println("Libros disponibles:");
       verCatalogo(disponibles);
       
       
       // Pedir datos al usuarios y probar el método prestarLibro
       
       System.out.println(" ¿Que libro desea?:");
       String titulo = sc.nextLine();
       int resu = prestarLibro(titulo,prestados,disponibles);
       if ( resu == 0) System.out.println("El libro no está en el catalogo");
          else if ( resu == -1) System.out.println("El libro está prestado");
            else System.out.println("Su pestamo ha sido anotado");
       
       System.out.println("Libros disponibles:");
       verCatalogo(disponibles);
       
       System.out.println("Libros prestados:");
       verCatalogo(prestados);

       
       // Salvo al fichero
       salvarCatalogo(disponibles, LIBROS_DISPONIBLES);
       salvarCatalogo(prestados, LIBROS_PRESTADOS);
    }
   
    
    private static void salvarCatalogo ( HashMap <String,Libro> hmap , String fichero)
    {
    try{
         // Creo Flujo de tipo fichero de byte 
         FileOutputStream fos= new FileOutputStream(fichero);
         // Creo un Flujo de objetos sobre el fichero
         ObjectOutputStream oos= new ObjectOutputStream(fos);
         oos.writeObject(hmap);
         oos.close(); // Cierro el fujo de objectos
         fos.close(); // Cierro el flujo de 
       }catch(IOException ioe){
            ioe.printStackTrace();
        }    
        
    }
        
    private static  HashMap <String,Libro> cargarCatalogo (String fichero){
        // Si falla genera un HashMap vacio
        HashMap <String,Libro> resu = new HashMap <String,Libro> (); 
        
        try{
         // Creo Flujo de tipo fichero de byte 
         FileInputStream finput= new FileInputStream(fichero);
         // Creo un Flujo de objetos sobre el fichero
         ObjectInputStream oinput= new ObjectInputStream(finput);
         // Casting de conversion de Object --> HashMap
         resu = (HashMap <String,Libro> ) oinput.readObject(); 
         oinput.close(); // Cierro el fujo de objectos
         finput.close(); // Cierro el flujo de 
       }catch(IOException ioe){
            ioe.printStackTrace();
        }
        // Por si falla el casting 
       catch ( ClassNotFoundException ex ){
           ex.printStackTrace();
        }
        return resu;
        
    }
    
    
    
    private static void verCatalogo ( HashMap <String,Libro> mlibros){
        int n = 1;
        for (Map.Entry claveLibro: mlibros.entrySet()){
            System.out.println(n+"º :"+ claveLibro.getValue());
            n++;
        }
        
    }
    
  
   
   /*
    * Devuelve:
    * 0 si no existe el libro en niguna de la listas
    * 1 Si puede prestarlo, el libro pasa de ldisponibles a lprestados
    * -1 Si ya esta prestado.
    */
   
   
   public static int prestarLibro ( String titulo, HashMap<String,Libro> mprestados, HashMap <String,Libro> mdisponibles){
   
      // Busco en ambas listas 
      Libro libroprestado     = mprestados.get(titulo);
      Libro librodisponible   = mdisponibles.get(titulo);
      
      if ( libroprestado == null && librodisponible == null) return 0; // No esta en catalogo
      if ( libroprestado !=  null)  return -1; // Esta prestado
      
      mdisponibles.remove(titulo);
      mprestados.put(titulo,librodisponible);
      return 1;
    }
      
 } 
    
