package personas;
/**
 * Write a description of class FicheroPersonas here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */

import java.io.*;

public class FicheroPersonas
{

    // Fichero donde se guardan los objetos
    static final String nombreFichero = "personas.ser";

    public static void main (String argv[]){

        // Creo dos objetos
        Persona p1 =  new Persona ("Pepe Luis", new Fecha(10,5,2001),3040999);
        Persona p2 =  new Persona ("Maria Luisa", new Fecha(12,4,1999),5043003);

        // ESCRIBIR OBJETOS

        try{
            // Creo Flujo de tipo fichero de byte 
            FileOutputStream fos= new FileOutputStream(nombreFichero);
            // Creo un Flujo de objetos sobre el fichero
            ObjectOutputStream oos= new ObjectOutputStream(fos);
            // Escribo los objetos al fichero
            oos.writeObject(p1);
            oos.writeObject(p2);
            oos.close(); // Cierro el fujo de objectos
            fos.close(); // Cierro el flujo de 
        }catch(IOException ioe){
            System.err.println(" Error en E/S sobre fichero "+nombreFichero+ " "+ioe);
        }    

        // LEER OBJETOS

        try{
            // Creo Flujo de tipo fichero de byte 
            FileInputStream fin= new FileInputStream(nombreFichero);
            // Creo un Flujo de objetos sobre el fichero
            ObjectInputStream oin= new ObjectInputStream(fin);
            
            // Leo objetos hasta llegar a final de fichero
            try {
                Persona aux = (Persona) oin.readObject();
                // Sale cuando se llege al final de fichero End of File EOF
                while ( true ){
                    aux.imprimirDatos();
                    aux = (Persona) oin.readObject();

                }
            }catch  (EOFException ex){} // No hago nada

            fin.close(); // Cierro el fujo de objectos
            oin.close(); // Cierro el flujo de bytes
        }catch(IOException ioe){
            System.err.println(" Error en E/S sobre fichero "+nombreFichero+ " "+ioe);
        }

        catch(ClassNotFoundException cex){
            System.err.println(" El fichero no tiene objetos ");
        }

    }

}
