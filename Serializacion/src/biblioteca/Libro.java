package biblioteca;
/**
 * Write a description of class Libro here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
import java.io.Serializable;

public class Libro implements Comparable <Libro>, Serializable
{
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	// instance variables - replace the example below with your own
    int id; // Identificador o clave del libro
    String titulo;
    String autor;
    Genero genero;
    int    edicion;
    
    /**
     * Constructor for objects of class Libro
     */
    public Libro( int id, String titulo, String autor, Genero genero, int edicion)
    {
        this.id = id;
        this.titulo = titulo;
        this.autor = autor;
        this.genero = genero;
        this.edicion = edicion;
    }
    
    public String toString(){
        return titulo+", "+autor+", "+ genero +", "+ edicion + ".";
    }

    // Para poder ordenar Libros por Id
    public int compareTo ( Libro otro){
        return this.id - otro.id;
    }
}
