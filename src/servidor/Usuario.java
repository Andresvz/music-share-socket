package servidor;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Genesis Guerrero
 */
public class Usuario{

    private String nombre;
    private String userName;
    private String password;

   
    private String cancionActual;
    private boolean enlinea; //estado del usuario conectado/desconectado
    private boolean escuchando; //este atributo indica el estado del usuario
                                //verdadero si esta escuchando una cancion
                                // y falso en caso contrario

    public Usuario(String nombre, String user, String pass) {
        this.nombre = nombre;
        this.userName = user;
        this.password = pass;
        this.escuchando = false;
        this.cancionActual = "";
    }
    
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
     public void setCancionActual(String cancionActual) {
        this.cancionActual = cancionActual;
    }
    public String getCancionActual() {
        return this.cancionActual;
    }
    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getPassword() {
        return password;
    }
    public boolean estaEscuchando() {
        return escuchando;
    }

    public void setEscuchando(boolean escuchando) {
        this.escuchando = escuchando;
    }

    public boolean estaEnlinea() {
        return enlinea;
    }

    public void setEnlinea(boolean enlinea) {
        this.enlinea = enlinea;
    }


}
