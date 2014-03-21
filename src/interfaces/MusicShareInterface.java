package interfaces;

import java.io.File;
import java.io.ObjectOutputStream;
import java.net.Socket;


public interface  MusicShareInterface {

    public boolean Registrarse (String nombre, String user, String pass1, String pass2);
    public boolean IniciarSesion (String user, String pass);
    public boolean CerrarSesion (String user);
    public boolean SubirCancion (String user, String ruta, String nombreCancion, Socket socket, String tamaño);
    public boolean DescargarCancion(String user, String nombreCancion, Socket socket);
    public boolean yoEscucho(String cancion, String usuario);
    public String ConsultarTamano(String cancion);
    public String verBibliotecaPublica ();
    public String VerConectados ();
 
}

