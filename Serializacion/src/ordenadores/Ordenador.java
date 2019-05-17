package ordenadores;
import java.io.Serializable;

/**
 * @author jaime
 *
 */
public class Ordenador implements Serializable, Comparable<Ordenador>
{
    
    private static final long serialVersionUID = 1234567890L;

    // instance variables 
    String marca;  
    int año; 
    int memoriaRam;
    int disco;
    int numserie;
    
    public Ordenador(int numserie, String marca,int año, int memoriaRam, int disco)
    {
      this.numserie = numserie;
      this.marca = marca;
      this.año   = año;
      this.memoriaRam = memoriaRam;
      this.disco = disco;
    }
    
    public String toString()
    {
        return numserie+":"+ marca +":"+año+":"+memoriaRam+" GB:"+disco+ " GB";
    }
    
    public int getaño() {
		return año;
    	
    }
    
    public int getMemoriaRam() {
		return memoriaRam;
	}

	public void setMemoriaRam(int memoriaRam) {
		this.memoriaRam = memoriaRam;
	}

	public int getDisco() {
		return disco;
	}

	public void setDisco(int disco) {
		this.disco = disco;
	}

	public int getNumserie() {
		return numserie;
	}

	public void setNumserie(int numserie) {
		this.numserie = numserie;
	}

	public String getmarca() {
		return marca;
    	
    }
    
    @Override
    public int compareTo(Ordenador o) {
        if (año < o.año) {
            return -1;
        }
        if (año > o.año) {
            return 1;
        }
        return 0;
    }
}
