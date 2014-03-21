
package Cliente;

import interfaces.MusicShareInterface;
import java.io.File;
import static java.lang.System.exit;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author Genesis
 */
public class RockolaForm extends javax.swing.JDialog {

    MusicShareInterface music;
    MusicShareHiloCliente hilo ;
    HiloReproductor hiloReproductor;

    
    String username;    
    String nombreCancion;
    boolean reproduciendo = false;
    
    public RockolaForm(java.awt.Frame parent, boolean modal, MusicShareInterface clienteTCP, String user) {
        super(parent, modal);
        initComponents();
        this.setLocationRelativeTo(null); 
        
        try {
            music = clienteTCP;
        }catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error al conectar", "Chat", JOptionPane.ERROR_MESSAGE);
        }
        
        this.username = user;
        hilo = new MusicShareHiloCliente(this, music, username);
        hilo.start();
        hiloReproductor = new HiloReproductor(this); 
    }

     public void actualizarContactos(String contactos){
        txtUsuarios.setText(contactos);
     }
     public void actualizarBiblioteca(String biblioteca){
        String[] listaCanciones = biblioteca.split("%.%");      
        lstBibliotecaPublica.setListData(listaCanciones);
     }

     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        btnSubirCancion = new javax.swing.JButton();
        btnDescargar = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        jScrollPane5 = new javax.swing.JScrollPane();
        lstBibliotecaPublica = new javax.swing.JList();
        jScrollPane4 = new javax.swing.JScrollPane();
        lstListaDeReproduccion = new javax.swing.JList();
        jScrollPane3 = new javax.swing.JScrollPane();
        txtUsuarios = new javax.swing.JTextArea();
        jLabel5 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        btnSiguiente = new javax.swing.JButton();
        jSlider1 = new javax.swing.JSlider();
        btnplay = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel6 = new javax.swing.JLabel();
        btnAnterior = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        btnActualizarBiblioteca = new javax.swing.JButton();
        btnActualizarBiblioteca1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        btnSubirCancion.setText("Subir Cancion");
        btnSubirCancion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSubirCancionActionPerformed(evt);
            }
        });

        btnDescargar.setText(">");
        btnDescargar.setToolTipText("Descargar cancion");
        btnDescargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDescargarActionPerformed(evt);
            }
        });

        jSeparator2.setOrientation(javax.swing.SwingConstants.VERTICAL);

        lstBibliotecaPublica.setBackground(new java.awt.Color(255, 255, 195));
        lstBibliotecaPublica.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        jScrollPane5.setViewportView(lstBibliotecaPublica);

        lstListaDeReproduccion.setBackground(new java.awt.Color(255, 255, 196));
        jScrollPane4.setViewportView(lstListaDeReproduccion);

        txtUsuarios.setEditable(false);
        txtUsuarios.setBackground(new java.awt.Color(225, 255, 230));
        txtUsuarios.setColumns(20);
        txtUsuarios.setRows(5);
        jScrollPane3.setViewportView(txtUsuarios);

        jLabel5.setText("Usuarios Conectados");

        jLabel8.setText("00:00");

        jLabel4.setText("Rockcola");

        jLabel7.setText("Mi lista de reproduccion");

        btnSiguiente.setText("Siguiente");

        btnplay.setText("Play/pause");
        btnplay.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnplayActionPerformed(evt);
            }
        });

        jLabel6.setText("Biblioteca Pública");

        btnAnterior.setText("Anterior");

        jButton6.setText("Cerrar Sesion");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        btnActualizarBiblioteca.setText("Actualizar");
        btnActualizarBiblioteca.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarBibliotecaActionPerformed(evt);
            }
        });

        btnActualizarBiblioteca1.setText("Actualizar");
        btnActualizarBiblioteca1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActualizarBiblioteca1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(47, 47, 47)
                                .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(btnAnterior)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnplay)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(btnSiguiente)
                                        .addGap(146, 146, 146))
                                    .addComponent(jSlider1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 519, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(85, 85, 85))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel6))
                                    .addComponent(jLabel4))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jButton6)
                                        .addGap(49, 49, 49))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                        .addComponent(jLabel7)
                                        .addGap(68, 68, 68)))))
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnSubirCancion)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btnActualizarBiblioteca))
                            .addComponent(jScrollPane5, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(btnDescargar, javax.swing.GroupLayout.PREFERRED_SIZE, 53, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 180, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnActualizarBiblioteca1)
                                .addGap(69, 69, 69))))))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(jButton6))
                .addGap(28, 28, 28)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jLabel7)
                    .addComponent(jLabel6))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jSeparator2)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane3)
                                .addGap(18, 18, 18))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jScrollPane4)
                                    .addComponent(jScrollPane5, javax.swing.GroupLayout.DEFAULT_SIZE, 301, Short.MAX_VALUE)
                                    .addGroup(layout.createSequentialGroup()
                                        .addGap(102, 102, 102)
                                        .addComponent(btnDescargar)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnSubirCancion, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnActualizarBiblioteca, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btnActualizarBiblioteca1)))
                                .addGap(0, 14, Short.MAX_VALUE)))
                        .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel8, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jSlider1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnAnterior)
                    .addComponent(btnplay)
                    .addComponent(btnSiguiente))
                .addGap(20, 20, 20))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        if(music.CerrarSesion(username)){
            exit(0);
        }
        
    }//GEN-LAST:event_jButton6ActionPerformed

    private void btnActualizarBibliotecaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarBibliotecaActionPerformed
        String listaCanciones = music.verBibliotecaPublica();
        this.actualizarBiblioteca(listaCanciones);
    }//GEN-LAST:event_btnActualizarBibliotecaActionPerformed

    private void btnDescargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDescargarActionPerformed
        String cancion = lstBibliotecaPublica.getSelectedValue().toString();
        try{
            if(music.DescargarCancion(username, cancion, null)){
                JOptionPane.showMessageDialog(null, "Cancion descargada exitosamente");
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error al descargar el archivo");
        }
    }//GEN-LAST:event_btnDescargarActionPerformed

    private void btnSubirCancionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSubirCancionActionPerformed
        JFileChooser elegir = new JFileChooser();
        String nombre=null;
        String ruta=null;
        FileNameExtensionFilter filtroCanciones=new FileNameExtensionFilter("archivo de audio MP3", "mp3");
        elegir.setFileFilter(filtroCanciones);
        int opcion =  elegir.showOpenDialog(btnSubirCancion);
        
        if (opcion == JFileChooser.APPROVE_OPTION) {
            nombre = elegir.getSelectedFile().getName();
            ruta = elegir.getSelectedFile().getPath();
            System.out.println(""+ruta);
            System.out.println(""+nombre);
            if(music.SubirCancion(username,ruta, nombre, null, null)){
                JOptionPane.showMessageDialog(null, "Cancion subida exitosamente");
            }
        }
        
    }//GEN-LAST:event_btnSubirCancionActionPerformed

    private void btnplayActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnplayActionPerformed
        int index = lstListaDeReproduccion.getSelectedIndex();
        nombreCancion = lstListaDeReproduccion.getModel().getElementAt(index).toString();
        System.out.println(nombreCancion);
        hiloReproductor.setNombreCancion(nombreCancion);   
        hiloReproductor.setReproduciendo(true);
        hiloReproductor.start();
    }//GEN-LAST:event_btnplayActionPerformed

   
    private void btnActualizarBiblioteca1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActualizarBiblioteca1ActionPerformed
        String lista="";
        File directorio = new File("src/Cliente/cancionesDescargadas");
        String[] listaDirectorio = directorio.list();
        if (listaDirectorio == null)
             lista = "null";
        else {
            for (String listaDir : listaDirectorio) {
                lista += listaDir+"%.%";
            }
         }	
       
        lista = lista.replace(".mp3", "");
        String[] listaCanciones = lista.split("%.%");     
        lstListaDeReproduccion.setListData(listaCanciones);
    }//GEN-LAST:event_btnActualizarBiblioteca1ActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {                                  
        music.CerrarSesion(username);
    } 
    public boolean getEstadoReproduccion(){
        return reproduciendo;
    }
    public void setEstadoReproduccion(boolean val){
        this.reproduciendo = val;
    }
    public String getUsername() {
        return username;
    }
    public String getNombreCancion() {
        return nombreCancion;
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActualizarBiblioteca;
    private javax.swing.JButton btnActualizarBiblioteca1;
    private javax.swing.JButton btnAnterior;
    private javax.swing.JButton btnDescargar;
    private javax.swing.JButton btnSiguiente;
    private javax.swing.JButton btnSubirCancion;
    private javax.swing.JButton btnplay;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSlider jSlider1;
    private javax.swing.JList lstBibliotecaPublica;
    private javax.swing.JList lstListaDeReproduccion;
    private javax.swing.JTextArea txtUsuarios;
    // End of variables declaration//GEN-END:variables
}
