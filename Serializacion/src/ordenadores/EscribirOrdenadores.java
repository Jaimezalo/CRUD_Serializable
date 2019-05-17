package ordenadores;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

import personas.Persona;

import java.io.IOException;
import java.io.ObjectInputStream;

public class EscribirOrdenadores
{
    
    private static String fichero = "ordenadores.objetos";
    
    public static void main(String[] args) throws ClassNotFoundException {
      
    
    
    try{
         
         FileOutputStream fos= new FileOutputStream(fichero);
         ObjectOutputStream oos= new ObjectOutputStream(fos);
         
         
          oos.writeObject(new Ordenador (202323,"HP",2012,8,1000));
          oos.writeObject(new Ordenador (202323,"HP",2014,8,2000));
          oos.writeObject(new Ordenador (102039,"LENOVO",2010,4,500));
          oos.writeObject(new Ordenador (293000,"APPLE",2012,8,1500));
          
          
         oos.close(); 
         fos.close();  
         
       }catch(IOException |ClassCastException ioe){
            ioe.printStackTrace();
            ioe.getMessage();
        }    
    
    try{
        
        FileInputStream fin= new FileInputStream(fichero);
        ObjectInputStream oin= new ObjectInputStream(fin);
        ArrayList<Ordenador> pcs = new ArrayList<Ordenador>();
        
        try {
            Ordenador aux;
          
            while ( true ){  
                aux = (Ordenador) oin.readObject();
                pcs.add(aux);
            }   
            

        }catch  (EOFException ex){
        	
        }finally {
            fin.close(); 
            oin.close();
        }
        Collections.sort(pcs);
        
        for(Ordenador p: pcs) {
        	System.out.println(p);
        }
        
    }catch(IOException ioe){
        System.err.println(" Error en E/S sobre fichero "+fichero+ " "+ioe);
    }
    catch(ClassNotFoundException cex){
        System.err.println(" El fichero no tiene objetos ");
    }
    

        
    }    
}


