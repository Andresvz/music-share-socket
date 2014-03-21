
package Cliente;

import interfaces.MusicShareInterface;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Genesis Guerrero
 */
public class MusicShareClienteTCP implements MusicShareInterface{

    private Socket socket;
    private DataOutputStream out;
    private DataInputStream in;
    
    public MusicShareClienteTCP(String host, int port) {
        try{
            socket=new Socket(InetAddress.getByName(host), port);
            out=new DataOutputStream(socket.getOutputStream());
            in=new DataInputStream(socket.getInputStream());    
        }catch(Exception e){
            e.printStackTrace();
        }
    }
   
    @Override
    public boolean Registrarse(String nombre, String user, String pass1, String pass2) {                
        return Boolean.parseBoolean(enviarMensaje("Registrarse%.%"+nombre+"%.%"+user+"%.%"+pass1+"%.%"+pass2));
    }

    @Override
    public boolean IniciarSesion(String user, String pass) {
        return Boolean.parseBoolean(enviarMensaje("IniciarSesion%.%"+user+"%.%"+pass));
    }
    @Override
    public boolean CerrarSesion(String user) {
        return Boolean.parseBoolean(enviarMensaje("CerrarSesion%.%"+user));
    }
    @Override
    public boolean SubirCancion(String user, String rutaCancion, String nombreCancion, Socket cliente, String tamaño) {
        boolean resp= false;
        int size = 0;
        try { 
            
            PrintStream envio=new PrintStream(socket.getOutputStream());
            FileInputStream origen=new FileInputStream(rutaCancion); 
            size = origen.available();
            //se envia el mensaje al servidor para que reciba los bytes
            out.writeUTF("SubirCancion%.%"+user+"%.%"+rutaCancion+"%.%"+nombreCancion+"%.%"+size);   
            out.flush();
            
            byte[] buffer = new byte[8000]; 
            int len; 
            
            while( size > 0 ){ 
                len=origen.read(buffer);
                envio.write(buffer,0,len); 
                System.out.println("Enviado "+len);
                size -= len;
            }
            origen.close();
            resp = Boolean.parseBoolean(in.readUTF());
        } catch (IOException ex) {
            Logger.getLogger(MusicShareClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
            resp  = false;
        }    
        return resp;
    }
    @Override
    public String ConsultarTamano(String cancion) {
        return enviarMensaje("ConsultarTamano%.%"+cancion);
    }
    @Override
    public boolean DescargarCancion(String user, String nombreCancion, Socket cliente) {
        boolean resp = false;
        String path = "src/Cliente/cancionesDescargadas/"+nombreCancion+".mp3";
        int size = 0;
        try {
                       
            size = Integer.parseInt(ConsultarTamano(nombreCancion));
            InputStream llegada = socket.getInputStream();            
            FileOutputStream destino = new FileOutputStream(path);
            out.writeUTF("DescargarCancion%.%"+user+"%.%"+nombreCancion);   
            out.flush();
            
            byte[] buffer = new byte[8000]; 
            int len = 0; 
            int aux = 0;
            
            while( size > 0 ){                  
                len=llegada.read(buffer);
                aux += len;
                destino.write(buffer,0,len);                
                System.out.println("Recibido "+len+ "bytes");                
                size -= len;
                if(aux >= size){  break; }
            }
            destino.close();            
            resp = Boolean.parseBoolean(in.readUTF());
        } catch (IOException ex) {
            Logger.getLogger(MusicShareClienteTCP.class.getName()).log(Level.SEVERE, null, ex);
            resp = false;
        }
        return resp;
    }
    @Override
    public String verBibliotecaPublica() {
        return enviarMensaje("VerBiblioteca");
    }
    @Override
    public String VerConectados() {
        return enviarMensaje("VerConectados");
    }
    
    
     public String enviarMensaje(String msg){  
          try {
                    System.out.println("sending "+msg);
                    out.writeUTF(msg);
                    out.flush();
                    msg = in.readUTF();
                    System.out.println("receiving "+msg);  
           } catch (IOException e) {}
          
             return msg;
    }
   
    public boolean enviaFichero(String fichero, ObjectOutputStream oos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean yoEscucho(String cancion, String usuario) {
        return  Boolean.parseBoolean(enviarMensaje("yoEscucho"+"%.%"+cancion+"%.%"+usuario));
    }

   

}
