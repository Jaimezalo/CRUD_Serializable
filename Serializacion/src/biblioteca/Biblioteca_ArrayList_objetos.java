package biblioteca;
/**
 * Write a description of class Biblioteca_ArrayList_objetos here.
 * 
 * UTILIZA LA SERIALIZACIÓN GRABANDO LIBRO A LIBRO
 * @author (your name) 
 * @version (a version number or a date)
*/

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Collections;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.IOException;
import java.io.EOFException;


public class Biblioteca_ArrayList_objetos
{
    
    
    static final String LIBROS_DISPONIBLES = "disponibles.Libros";
    static final String LIBROS_PRESTADOS   = "prestados.Libros";
    
   public static void main ( String argv[]){
       
       ArrayList <Libro> disponibles = new ArrayList <Libro> ();
       ArrayList <Libro> prestados   = new ArrayList <Libro> ();
       
       Scanner sc = new Scanner(System.in);
       
       // Rellenar las listas con valores de prueba
       /*
       Libro nuevo = new Libro(19,"El Quijote"," Cervantes", Genero.NOVELA, 2012);
       
       disponibles.add(nuevo);
       disponibles.add(new Libro(20,"El Buscón","Quevedo",Genero.NOVELA,1997));
       disponibles.add(new Libro(21,"Romeo y Julieta","Shakespeare ",Genero.TEATRO,1997));
       disponibles.add(new Libro(22,"Mio Cid","Anónimo",Genero.POESIA,1989));
   
       
       
       prestados.add( new Libro(30,"Harry Potter I", "J. K. Rowling", Genero.NOVELA,1998));
       prestados.add( new Libro(34,"Guerra y Paz","León Tolstói",Genero.NOVELA,1975));
       prestados.add( new Libro(19,"El Decamerón","Giovanni Boccaccio",Genero.CUENTOS,1992));
       
       */
       // leo del fichero
       disponibles = cargarCatalogo(LIBROS_DISPONIBLES);
       prestados   = cargarCatalogo(LIBROS_PRESTADOS);
                   
      
       System.out.println("Libros disponibles:");
       // Ordenacion de objetos --> Interfaz Comparable
       Collections.sort(disponibles);
       verCatalogo(disponibles);
       
       System.out.println("Libros prestados:");
       Collections.sort(prestados);
       verCatalogo(prestados);
      
       // Pedir datos al usuarios y probar el método prestarLibro
       
       System.out.println(" ¿Que libro desea?:");
       String titulo = sc.nextLine();
       int resu = prestarLibro(titulo,prestados,disponibles);
       if ( resu == 0) System.out.println("El libro no está en el catalogo");
          else if ( resu == -1) System.out.println("El libro está prestado");
            else System.out.println("Su pestamo ha sido anotado");
       
       System.out.println("Libros disponibles:");
       Collections.sort(disponibles);
       verCatalogo(disponibles);
       
       System.out.println("Libros prestados:");
       Collections.sort(prestados);
       verCatalogo(prestados);
       
       // Salvo a fichero
       salvarCatalogo(disponibles, LIBROS_DISPONIBLES);
       salvarCatalogo(prestados, LIBROS_PRESTADOS);
      
    }
   
    
     private static void salvarCatalogo (  ArrayList <Libro> lista , String fichero)
    {
    try{
         // Creo Flujo de tipo fichero de byte 
         FileOutputStream fos= new FileOutputStream(fichero);
         // Creo un Flujo de objetos sobre el fichero
         ObjectOutputStream oos= new ObjectOutputStream(fos);
         
         for ( Libro unlibro : lista){
             oos.writeObject(unlibro);
         }
         oos.close(); // Cierro el fujo de objectos
         fos.close(); // Cierro el flujo de 
       }catch(IOException ioe){
            ioe.printStackTrace();
        }    
        
    }
        
    private static   ArrayList <Libro> cargarCatalogo (String fichero){
        // Si falla genera un ArrayList vacio
         ArrayList <Libro> resu = new  ArrayList <Libro>();
        
        try{
         // Creo Flujo de tipo fichero de byte 
         FileInputStream finput= new FileInputStream(fichero);
         // Creo un Flujo de objetos sobre el fichero
         ObjectInputStream oinput= new ObjectInputStream(finput);
         // Casting de conversion de Object --> HashMap
         try {
                Libro unlibro = (Libro) oinput.readObject();
                // Sale cuando se llege al final de fichero End of File EOF
                while ( true ){
                    resu.add(unlibro);
                    unlibro = (Libro) oinput.readObject();

                }
            }catch  (EOFException ex){} // No hago nada
         
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
    
    
    
    
    
    private static void verCatalogo ( ArrayList <Libro> lista){
       
        for (int i=0; i < lista.size(); i++){
            System.out.println((i+1)+":"+ lista.get(i));
        }
        
    }
    
   // Primera version: realizo una busqueda secuencial 
   
   private static Libro buscarId ( String titulo, ArrayList <Libro> lista){
       
         Libro resu = null;     
         // For sobre colecciones o arrays 
         for ( Libro libroaux: lista) {
             if ( libroaux.titulo.equals(titulo) ){
                 resu = libroaux;
                 break;
                }
            }
         return resu; // Devuelo le libro o null si no esta
   }
   
   /*
    * Devuelve:
    * 0 si no existe el libro en niguna de la listas
    * 1 Si puede prestarlo, el libro pasa de ldisponibles a lprestados
    * -1 Si ya esta prestado.
    */
   
   
   public static int prestarLibro ( String titulo, ArrayList<Libro> lprestados, ArrayList <Libro> ldisponibles){
   
      // Busco en ambas listas 
      Libro libroprestado     = buscarId(titulo,lprestados);
      Libro librodisponible   = buscarId(titulo,ldisponibles);
      
      if ( libroprestado == null && librodisponible == null) return 0; // No esta en catalogo
      if ( libroprestado !=  null)  return -1; // Esta prestado
      
      ldisponibles.remove(librodisponible);
      lprestados.add(librodisponible);
      return 1;
    }
      
 } 
    


