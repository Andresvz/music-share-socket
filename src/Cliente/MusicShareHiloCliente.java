
package Cliente;

import interfaces.MusicShareInterface;
import servidor.Usuario;


/**
 *
 * @author Genesis Guerrero
 */
public class MusicShareHiloCliente extends Thread implements Runnable {
    RockolaForm form = null;
    MusicShareInterface rockola = null;
    String nombre = null;
    String contactos = null;
    String biblioteca = null;
    
    public MusicShareHiloCliente(RockolaForm chatForm, MusicShareInterface rockolaCliente, String nombreUsuario) {
            form = chatForm;
            rockola=rockolaCliente;
            nombre = nombreUsuario;
    }

    @Override
    public void run(){
        while(true){
            try {
                contactos = rockola.VerConectados();
                //biblioteca = rockola.verBibliotecaPublica();
                if (form.getEstadoReproduccion()){
                    rockola.yoEscucho(form.getNombreCancion(), form.getUsername());
                }
            } catch (Exception ex) {
                System.out.println("Error al sincronizar los datos");
            }
            form.actualizarContactos(contactos);
            //form.actualizarBiblioteca(biblioteca);
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
                System.out.println("InterruptedException");
            }
        }
    }

}
