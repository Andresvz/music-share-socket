package servidor;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Genesis Guerrero
 */
public class MusicShareServidorTCP {
        private ServerSocket socketServidor = null;
        private Socket socketCliente = null;
	private MusicShareHiloServidor factory = null;
        MusicShareImplementacion rockola = null;

        public MusicShareServidorTCP(int port)
   	{
   		try {
                    rockola = new MusicShareImplementacion();
                    socketServidor = new ServerSocket(port);
   		} catch (IOException e) {}
         }
   	
        
        public void daemon(){
        while (true) {
   			try {
   				System.out.println("Estoy esperando...");
   				socketCliente = socketServidor.accept();                                
   				System.out.println("encontre un cliente");
                                System.out.println(socketCliente.getInetAddress().getHostName());
   				factory = new MusicShareHiloServidor(socketCliente, rockola);
                                factory.start();
   			} catch (IOException e) {
                            e.printStackTrace();
                        }
   		}
        }

public static void main(String arg[]){
    MusicShareServidorTCP server = new MusicShareServidorTCP(7777);
		server.daemon();
}

}
