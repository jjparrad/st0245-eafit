import java.util.ArrayList;
public class Archivo implements Comparable<Archivo>
{
    String size;
    String nombre;
    Archivo jefe;
    boolean porNombre;
    boolean buscando;
    ArrayList<Archivo> iguales;
    public Archivo(String tam, String nom, Archivo boss){
        size = tam;
        nombre = nom;
        jefe = boss;
        porNombre = false;
        buscando = false;
        iguales = new ArrayList<Archivo>();
        iguales.add(this);
    }
    public Archivo(String tam, String nom){
        size = tam;
        nombre = nom;
        jefe = null;
        porNombre = false;
        buscando = false;
        iguales = new ArrayList<Archivo>();
        iguales.add(this);
    }
    public Archivo(String tam, boolean nom){
        porNombre = nom; 
        if (nom){
            size = "";
            nombre = tam;
        }else {
            size = tam;
            nombre = "";
        }
        jefe = null;
        buscando = true;
    }
    
    @Override
    public int compareTo(Archivo a){
        if(a.porNombre != porNombre){
            System.out.println("Hay un error");
            return 999;
        } else if (a.porNombre){
            return compararNombre(a);
        } else {
            return compararSize(a);
        }
    }
    
    public int compararSize(Archivo a){
        int n = size.compareTo(a.size);
        if(!(a.buscando) && !(buscando)){
            if(n == 0){
                a.iguales.add(this);
                buscando = true;
            }
        }
        return n;
    }
    public int compararNombre(Archivo a){
        int n = nombre.compareTo(a.nombre);
        if(!(a.buscando) && !(buscando)){
            if(n == 0){
                a.iguales.add(this);
                buscando = true;
            }
        }
        return n;
    }
    
    public String imprimirDireccion(){
        return imprimirDireccionAux(this) + "/" + nombre;
    }
    private String imprimirDireccionAux(Archivo a){
        if(a.jefe == null){
            return "";
        }
        String s = imprimirDireccionAux(a.jefe) + "/" + a.jefe.nombre;
        return s;
    }
    public String toString(){
        String s = nombre;
        return s + " " + size + " " + imprimirDireccion();
    }
}
