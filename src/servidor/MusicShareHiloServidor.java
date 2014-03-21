
package servidor;

import interfaces.MusicShareInterface;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
/**
 *
 * @author Genesis Guerrero
 */
public class MusicShareHiloServidor extends Thread implements Runnable{
    private static MusicShareInterface rockola = null;
    private DataInputStream in = null;
    private DataOutputStream out = null;
    private Socket socketCliente = null;
    private String vec[];
    private String retval,cmd;
    
    public MusicShareHiloServidor(Socket socketCliente, MusicShareImplementacion rockolaServer) {
        
         this.socketCliente = socketCliente;
         this.rockola = rockolaServer;//new MusicShareImplementacion();
        try {
            in = new DataInputStream(socketCliente.getInputStream());
            out = new DataOutputStream(socketCliente.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


  public String demux(String msg) {
		vec = msg.split("%.%");
		retval="OK";
                try {
                    switch(vec[0]){
                        case "Registrarse":
                            retval=""+rockola.Registrarse(vec[1], vec[2], vec[3], vec[4]);
                            break;
                        case "IniciarSesion":
                            retval=""+rockola.IniciarSesion(vec[1], vec[2]);
                            System.out.println("respuesta "+retval);
                            break;
                        case "CerrarSesion":
                            rockola.CerrarSesion(vec[1]);
                            retval="CerrarSesion";
                            break;
                        case "VerBiblioteca":
                            retval = rockola.verBibliotecaPublica();
                            break;
                        case "DescargarCancion":
                            System.out.println("Comienza la descarga de una cancion al cliente..."+vec[2]);
                            retval=""+rockola.DescargarCancion(vec[1],vec[2],socketCliente);                            
                            break;
                        case "ConsultarTamano":
                            retval = rockola.ConsultarTamano(vec[1]);
                            break;
                        case "SubirCancion":
                            System.out.println("Comienza subida de una cancion al servidor..."+vec[2]);
                            retval=""+rockola.SubirCancion(vec[1],vec[2], vec[3], socketCliente, vec[4]);
                            break;
                        case "yoEscucho":                            
                            retval = ""+rockola.yoEscucho(vec[1], vec[2]);
                            break;
                        case "VerConectados":
                            retval=rockola.VerConectados();
                            break;
                    }              
                
            } catch (Exception e) {
                retval=e.getMessage();
            }
		
              // retval = eco.Mensaje(msg);*/
               return retval;
	}
    
    
    public void run() {
       
        cmd = "COMANDO";
        
        //System.out.println("run...");
        try {
        for(; !cmd.equals("CerrarSesion"); out.flush())
        {
                cmd = in.readUTF();
               //System.out.println("llego mensaje "+cmd);
               // retval = demux(cmd);
                out.writeUTF(demux(cmd));
        }
        socketCliente.close();
        } catch (IOException e) {
                e.printStackTrace();
        }

    }


}
