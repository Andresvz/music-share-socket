package servidor;
import interfaces.MusicShareInterface;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MusicShareImplementacion  implements MusicShareInterface {
    ArrayList <Usuario> Usuarios;
    
    public MusicShareImplementacion () {
      
        Usuarios = new ArrayList();
    }

    @Override
    public boolean Registrarse(String nombre, String user, String pass1, String pass2){
        boolean resultado=false;
        boolean encontrado=false;
        
        /* se verifica si el nombre de usuario está disponible */
        for (Usuario Usuario : Usuarios) {
            if (Usuario.getUserName().equals(user)) {
                encontrado=true;
                break;
            } else {
            }
        }
        //validamos las contraseñas y agregamos el nuevo usuario
        if(!encontrado && (pass1 == null ? pass2 == null : pass1.equals(pass2))){           
            Usuarios.add(new Usuario(nombre, user,  pass1));
            resultado=true;   
        }
        return resultado;
    }
    
    @Override
    public boolean IniciarSesion (String user, String pass) {
        boolean resultado=false;
        
        for (Usuario Usuario : Usuarios) {
            if (Usuario.getUserName().equals(user)) { 
                /* despues de buscar el nombre de usuario
                se valida si la contraseña coincide */
                if(Usuario.getPassword().equals(pass)){
                    resultado = true;
                    Usuario.setEnlinea(true);
                    break;
                }                
                
            }
        }
        return resultado;
    }
    @Override
    public String ConsultarTamano(String nombreCancion){
        FileInputStream archivo = null;
        try {
          
            String path = "src/servidor/canciones/"+nombreCancion+".mp3";
            System.out.println("path: "+path);
            archivo = new FileInputStream(path);
            System.out.println("Este es el espacio de la cancion: "+archivo.available());
          
            return ""+archivo.available();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(MusicShareImplementacion.class.getName()).log(Level.SEVERE, null, ex);
            return "-1";
        } catch (IOException ex) {
            Logger.getLogger(MusicShareImplementacion.class.getName()).log(Level.SEVERE, null, ex);
            return "-1";
        } finally {
            try {
                archivo.close();
            } catch (IOException ex) {
                Logger.getLogger(MusicShareImplementacion.class.getName()).log(Level.SEVERE, null, ex);
                return "-1";
            }
        }
        
    }
    @Override
    public boolean DescargarCancion(String user, String nombreCancion, Socket socket) { 
           int size = 0;
           String path = "src/servidor/canciones/"+nombreCancion+".mp3";
                 
           try {
                PrintStream envio = new PrintStream(socket.getOutputStream());
                FileInputStream origen = new FileInputStream(path);                 
                size = origen.available();//tamaño del archivo
                
                byte[] buffer = new byte[8000]; 
                int len; 
               
                while( size > 0 ){ 
                    len=origen.read(buffer);
                    envio.write(buffer,0,len); 
                    System.out.println("Enviado "+len+" bytes");
                    size -= len;
                }
                origen.close();
                return true;
                
               }catch(IOException e){
                   System.out.println("Error!"+e.getMessage());
                   return false;
               }
    }
   
    @Override
     public boolean SubirCancion (String user,String path, String nombreCancion, Socket socket, String tamaño)  {
         path = "src/servidor/canciones/"+nombreCancion;
         int size = Integer.parseInt(tamaño); 
         try{
            InputStream llegada = socket.getInputStream(); 
            FileOutputStream destino=new FileOutputStream(path); 
            
            byte[] buffer = new byte[8000]; 
            int len; 
            while( size > 0){ 
                len=llegada.read(buffer);
                destino.write(buffer,0,len);
                System.out.println("Recibido "+len);                
                size -= len;
            }
            destino.close();
            return true;
         }catch(IOException e){
             System.out.println("Error!"+e.getMessage());
             return false;
         }        
    }


    @Override
    public String VerConectados () {
        String contactos="";
        String usuario="";
        String simbolo = "\u266C";
        for (Usuario Usuario : Usuarios) {
            if(Usuario.estaEnlinea()){
                usuario = Usuario.getUserName();
                if(Usuario.estaEscuchando()){
                    contactos = contactos + usuario +"-"+simbolo+" "+Usuario.getCancionActual()+"\n";
                }else{
                    contactos = contactos + usuario +"\n";
                }
            }
        }
        
        return contactos;
    }

    @Override
    public boolean CerrarSesion (String nombre) {
        boolean result=false;
        int index=-1;

        //Se busca el usuario por el nombre
        for(int i=0; i<Usuarios.size(); i++){
            if(Usuarios.get(i).getNombre().equals(nombre)){
                index=i;
            }
        }
        if (index > -1){//Si existe usuario remitente
            Usuarios.get(index).setEnlinea(false); //desconectar
            result=true;
        }
        return result;
    }
        
    @Override
    public String verBibliotecaPublica() {
        String lista="";
        File directorio = new File("src/servidor/canciones");
        String[] listaDirectorio = directorio.list();
        if (listaDirectorio == null)
             lista = "null";
        else {
            for (String listaDir : listaDirectorio) {
                lista += listaDir+"%.%";
            }
         }	
       
        lista = lista.replace(".mp3", "");
        System.out.println(lista);
        return lista;
    }
    

    //Prueba de los Metodos de esta Clase
    public static void main(String arg[]){

        try{
            MusicShareImplementacion chat=new MusicShareImplementacion();
            boolean x =chat.Registrarse("prueba", "prueba", "123", "123");
            System.out.println(""+ x);
            boolean y =chat.IniciarSesion("prueba", "123");
            System.out.println(""+y);
            boolean h = chat.yoEscucho("una cancion cualquiera", "prueba");
            System.out.println(""+h);
            boolean z = chat.CerrarSesion("prueba");
            System.out.println(""+z);
            chat.verBibliotecaPublica();
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public boolean yoEscucho(String cancion, String usuario) {
        boolean resp = false;

        for (Usuario Usuario : Usuarios) {
            if (Usuario.getUserName().equals(usuario)) {
                Usuario.setEscuchando(true);
                Usuario.setCancionActual(cancion);
                System.out.println("Server msg: El usuario " + Usuario.getUserName() + " esta escuchando " + Usuario.getCancionActual());                
                resp = true;
                break;
            }
        }
        return resp;        
    }

   
    
}

