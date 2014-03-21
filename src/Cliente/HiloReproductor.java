
package Cliente;

import interfaces.MusicShareInterface;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.sound.sampled.AudioFormat;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.DataLine;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.SourceDataLine;
import javax.sound.sampled.UnsupportedAudioFileException;
import servidor.Usuario;



/**
 *
 * @author Genesis Guerrero
 */
public class HiloReproductor extends Thread implements Runnable {
    RockolaForm form = null;
    File cancion=null;    

  
    String nombreCancion = null;
    boolean reproduciendo;
    
    public HiloReproductor(RockolaForm chatForm) {
            form = chatForm;
            this.reproduciendo = reproduciendo;
    }
    
    private void rawplay(AudioFormat targetFormat, AudioInputStream din) throws IOException,                                                                                                LineUnavailableException
    {
        //cantidad de buffer que vamos a usar    
        byte[] data = new byte[4096];
        //Java sound obtiene recursos de los devices (tarjeta sonido) del sistema
        //a partir de la info que le pidamos, el busca entre los devices disponibles
        SourceDataLine line = getLine(targetFormat);
        if (line != null)
        {
          // Comienza la reproduccion
          line.start();
          int nBytesRead = 0, nBytesWritten = 0;
          while (nBytesRead != -1)
          {
              nBytesRead = din.read(data, 0, data.length);
              if (nBytesRead != -1) nBytesWritten = line.write(data, 0, nBytesRead);
          }
          // Cerrando conexiones y liberando recursos
          line.drain();
          line.stop();
          line.close();
          din.close();
        }
      }

      private SourceDataLine getLine(AudioFormat audioFormat) throws LineUnavailableException
      { //como pone arriba, Java mira entre los devices dispobiles y 
        // obtiene un canal para reproducir (en este caso)
        SourceDataLine res = null;
        DataLine.Info info = new DataLine.Info(SourceDataLine.class, audioFormat);
        res = (SourceDataLine) AudioSystem.getLine(info);
        res.open(audioFormat);
        return res;
    }
    
    @Override
    public void run(){
        while(true){
            try {
                if(isReproduciendo()){
                    cancion = new File("src/Cliente/cancionesDescargadas/"+nombreCancion+".mp3");
                    AudioInputStream in = null;
                    try {
                        in = AudioSystem.getAudioInputStream(cancion);
                    } catch (UnsupportedAudioFileException ex) {
                        Logger.getLogger(RockolaForm.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (IOException ex) {
                        Logger.getLogger(RockolaForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    AudioInputStream din = null;
                    AudioFormat baseFormat = in.getFormat();;           
                    AudioFormat decodedFormat = new AudioFormat(AudioFormat.Encoding.PCM_SIGNED,
                                                                                                  baseFormat.getSampleRate(),
                                                                                                  16,
                                                                                                  baseFormat.getChannels(),
                                                                                                  baseFormat.getChannels() * 2,
                                                                                                  baseFormat.getSampleRate(),
                                                                                                  false);
                   //Usamos el decoder para establecer el inputstream
                    din = AudioSystem.getAudioInputStream(decodedFormat, in);
                    try {
                        // vamos a reproducirlo
                        reproduciendo = true;
                        form.setEstadoReproduccion(reproduciendo);
                        rawplay(decodedFormat, din);  
                        //termina
                        reproduciendo = false;
                        setReproduciendo(reproduciendo);
                        form.setEstadoReproduccion(reproduciendo);
                    } catch (IOException ex) {
                        Logger.getLogger(RockolaForm.class.getName()).log(Level.SEVERE, null, ex);
                    } catch (LineUnavailableException ex) {
                        Logger.getLogger(RockolaForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    try {
                        in.close();
                    } catch (IOException ex) {
                        Logger.getLogger(RockolaForm.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            } catch (Exception ex) {
                System.out.println("Error al sincronizar los datos");
            }
            
        }
    }
    public String getNombreCancion() {
        return nombreCancion;
    }

    public void setNombreCancion(String nombreCancion) {
        this.nombreCancion = nombreCancion;
    }

    public boolean isReproduciendo() {
        return reproduciendo;
    }

    public void setReproduciendo(boolean reproduciendo) {
        this.reproduciendo = reproduciendo;
    }

}
