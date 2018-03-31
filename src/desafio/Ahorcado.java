/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

/**
 * Ciclo formativo: Desarrollo de Aplicaciones Multiplataforma 
 * Módulo profesional: Programación Reto número 2 
 * Alumno: Javier Gonzalez Rodriguez
 * Curso académico: 2017-2018 
 * Instituto Tecnológico Poniente
 * @author javier
 */
public class Ahorcado extends javax.swing.JFrame {

    private ArrayList<String> lista_letras;
    /**
     * Creates new form Ahorcado
     */
    public Ahorcado() {
        initComponents();
        lista_letras = new ArrayList();
        
        ImageIcon imagen = new ImageIcon(getClass().getResource("imagenes//im1.jpg"));
        Limagen.setIcon(imagen);
        ImageIcon icon = new ImageIcon(getClass().getResource("imagenes//interrogacion_azul.png"));
        String nombre;
        
        nombre = (String) JOptionPane.showInputDialog(this, null, "Introduce tu nombre", JOptionPane.PLAIN_MESSAGE, icon, null, null);
        if (nombre == null ||nombre.equals("") ) {
            nombre = "Sin nombre";
        }
        
        Lnombre.setText(nombre);
        
        buscarPalabra();
    }

    /**
     * 
     * busca una palabra 
     * 
     */
    private void buscarPalabra(){
        Random aleatorio = new Random();
        File archivo = null;
        FileReader flujo = null;
        BufferedReader leer = null;
        
        String palabra = "";
        try{
            archivo = new File("palabras.txt");
            flujo = new FileReader(archivo);
            leer = new BufferedReader(flujo);
            int contador = 1;
            
            while((palabra = leer.readLine()) != null){
                contador++;
            }
            int num = aleatorio.nextInt(contador);
            
            palabraSeleccionada(num);
            
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "error al leer palabra");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "error");
        }
        finally{
            try {
                leer.close();
                flujo.close();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "error");
            }
        }
    }
    
    /**
     * seleccionamos una palabra
     * @param num posicion de la palabra en el documento
     * 
     */
    private void palabraSeleccionada(int num){
        String p = "";
        File archivo = null;
        FileReader flujo = null;
        BufferedReader leer = null;
        try{
            archivo = new File("palabras.txt");
            flujo = new FileReader(archivo);
            leer = new BufferedReader(flujo);
            for (int i = 0; i < num; i++) {
                p = leer.readLine();
            }
            String letras[] = p.split("");
            for (int i = 0; i < letras.length; i++) {
                lista_letras.add(letras[i]);
                PpalabraSecreta.add(new JLabel("_"));
            }
            
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "error al seleccionar palabra");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "error al seleccionar palabra");
        }
        
    }
    
    /**
     * comprobamos que la letra seleccionada esta en el array
     * @param letra letra a comprobar
     * @return devuelve si esta o no la letra
     */
    private boolean letraEsta(String letra){
        boolean esta = false;
        for (int i = 0; i < lista_letras.size(); i++) {
            if (lista_letras.get(i).toLowerCase().equals(letra)) {
                esta = true;
            }
        }
        return esta;
    }
    
    /**
     * modificamos el JLabel para poner su letra
     * @param letra letra a poner
     */
    private void insertarLetra(String letra){
        for (int i = 0; i < lista_letras.size(); i++) {
            if (lista_letras.get(i).toUpperCase().equals(letra)) {
                JLabel L = (JLabel) PpalabraSecreta.getComponent(i);
                L.setText(letra);
            }
        }
    }
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        Limagen = new javax.swing.JLabel();
        Ltexto = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        LnombreJuego = new javax.swing.JLabel();
        jPanel5 = new javax.swing.JPanel();
        jPanel6 = new javax.swing.JPanel();
        PpalabraSecreta = new javax.swing.JPanel();
        jPanel8 = new javax.swing.JPanel();
        Lresolver = new javax.swing.JLabel();
        TFresolver = new javax.swing.JTextField();
        jPanel7 = new javax.swing.JPanel();
        jPanel9 = new javax.swing.JPanel();
        Bresolver = new javax.swing.JButton();
        jPanel10 = new javax.swing.JPanel();
        BA = new javax.swing.JButton();
        BB = new javax.swing.JButton();
        BC = new javax.swing.JButton();
        BD = new javax.swing.JButton();
        BE = new javax.swing.JButton();
        BF = new javax.swing.JButton();
        BG = new javax.swing.JButton();
        BH = new javax.swing.JButton();
        BI = new javax.swing.JButton();
        BJ = new javax.swing.JButton();
        BK = new javax.swing.JButton();
        BL = new javax.swing.JButton();
        BM = new javax.swing.JButton();
        BN = new javax.swing.JButton();
        Bñ = new javax.swing.JButton();
        BO = new javax.swing.JButton();
        BP = new javax.swing.JButton();
        BQ = new javax.swing.JButton();
        BR = new javax.swing.JButton();
        BS = new javax.swing.JButton();
        BT = new javax.swing.JButton();
        BU = new javax.swing.JButton();
        BV = new javax.swing.JButton();
        BW = new javax.swing.JButton();
        BX = new javax.swing.JButton();
        BY = new javax.swing.JButton();
        BZ = new javax.swing.JButton();
        jPanel11 = new javax.swing.JPanel();
        Ltiempo = new javax.swing.JLabel();
        Lnombre = new javax.swing.JLabel();
        Lpuntuacion = new javax.swing.JLabel();
        jMenuBar1 = new javax.swing.JMenuBar();
        jMenu1 = new javax.swing.JMenu();
        MenuNuevaPartida = new javax.swing.JMenuItem();
        MenuGuardar = new javax.swing.JMenuItem();
        MenuCargar = new javax.swing.JMenuItem();
        jMenu2 = new javax.swing.JMenu();
        MenuInformacion = new javax.swing.JMenuItem();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("JUEGO DEL AHORCADO");
        setPreferredSize(new java.awt.Dimension(615, 330));
        setResizable(false);

        jPanel1.setBackground(new java.awt.Color(254, 254, 254));
        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel1.setLayout(new java.awt.BorderLayout());

        Limagen.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel1.add(Limagen, java.awt.BorderLayout.CENTER);

        Ltexto.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Ltexto.setText("           ");
        jPanel1.add(Ltexto, java.awt.BorderLayout.SOUTH);

        getContentPane().add(jPanel1, java.awt.BorderLayout.WEST);

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jPanel2.setLayout(new java.awt.BorderLayout());

        jPanel3.setLayout(new java.awt.BorderLayout());

        LnombreJuego.setText("JUEGO DEL AHORCADO");
        LnombreJuego.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel4.add(LnombreJuego);

        jPanel3.add(jPanel4, java.awt.BorderLayout.NORTH);

        jPanel5.setLayout(new java.awt.BorderLayout());

        jPanel6.setBorder(null);
        jPanel6.setLayout(new java.awt.BorderLayout());

        PpalabraSecreta.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Palabra secreta", javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, javax.swing.border.TitledBorder.ABOVE_TOP, new java.awt.Font("Dialog", 1, 12))); // NOI18N
        jPanel6.add(PpalabraSecreta, java.awt.BorderLayout.NORTH);

        jPanel8.setLayout(new java.awt.BorderLayout());

        Lresolver.setText("Resolver: ");
        jPanel8.add(Lresolver, java.awt.BorderLayout.WEST);
        jPanel8.add(TFresolver, java.awt.BorderLayout.CENTER);

        jPanel6.add(jPanel8, java.awt.BorderLayout.SOUTH);

        jPanel5.add(jPanel6, java.awt.BorderLayout.NORTH);

        jPanel7.setLayout(new java.awt.BorderLayout());

        jPanel9.setLayout(new java.awt.GridLayout(1, 0));

        Bresolver.setText("Resolver");
        jPanel9.add(Bresolver);

        jPanel7.add(jPanel9, java.awt.BorderLayout.NORTH);

        jPanel10.setLayout(new java.awt.GridLayout(6, 6));

        BA.setText("A");
        BA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAActionPerformed(evt);
            }
        });
        jPanel10.add(BA);

        BB.setText("B");
        BB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BBActionPerformed(evt);
            }
        });
        jPanel10.add(BB);

        BC.setText("C");
        BC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCActionPerformed(evt);
            }
        });
        jPanel10.add(BC);

        BD.setText("D");
        BD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDActionPerformed(evt);
            }
        });
        jPanel10.add(BD);

        BE.setText("E");
        BE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEActionPerformed(evt);
            }
        });
        jPanel10.add(BE);

        BF.setText("F");
        BF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BFActionPerformed(evt);
            }
        });
        jPanel10.add(BF);

        BG.setText("G");
        BG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BGActionPerformed(evt);
            }
        });
        jPanel10.add(BG);

        BH.setText("H");
        BH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BHActionPerformed(evt);
            }
        });
        jPanel10.add(BH);

        BI.setText("I");
        BI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BIActionPerformed(evt);
            }
        });
        jPanel10.add(BI);

        BJ.setText("J");
        BJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BJActionPerformed(evt);
            }
        });
        jPanel10.add(BJ);

        BK.setText("K");
        BK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BKActionPerformed(evt);
            }
        });
        jPanel10.add(BK);

        BL.setText("L");
        BL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BLActionPerformed(evt);
            }
        });
        jPanel10.add(BL);

        BM.setText("M");
        BM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BMActionPerformed(evt);
            }
        });
        jPanel10.add(BM);

        BN.setText("N");
        BN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BNActionPerformed(evt);
            }
        });
        jPanel10.add(BN);

        Bñ.setText("ñ");
        Bñ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BñActionPerformed(evt);
            }
        });
        jPanel10.add(Bñ);

        BO.setText("O");
        BO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BOActionPerformed(evt);
            }
        });
        jPanel10.add(BO);

        BP.setText("P");
        BP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BPActionPerformed(evt);
            }
        });
        jPanel10.add(BP);

        BQ.setText("Q");
        BQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BQActionPerformed(evt);
            }
        });
        jPanel10.add(BQ);

        BR.setText("R");
        BR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BRActionPerformed(evt);
            }
        });
        jPanel10.add(BR);

        BS.setText("S");
        BS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSActionPerformed(evt);
            }
        });
        jPanel10.add(BS);

        BT.setText("T");
        BT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTActionPerformed(evt);
            }
        });
        jPanel10.add(BT);

        BU.setText("U");
        BU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BUActionPerformed(evt);
            }
        });
        jPanel10.add(BU);

        BV.setText("V");
        BV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BVActionPerformed(evt);
            }
        });
        jPanel10.add(BV);

        BW.setText("W");
        BW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BWActionPerformed(evt);
            }
        });
        jPanel10.add(BW);

        BX.setText("X");
        BX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BXActionPerformed(evt);
            }
        });
        jPanel10.add(BX);

        BY.setText("Y");
        BY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BYActionPerformed(evt);
            }
        });
        jPanel10.add(BY);

        BZ.setText("Z");
        BZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BZActionPerformed(evt);
            }
        });
        jPanel10.add(BZ);

        jPanel7.add(jPanel10, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel7, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel5, java.awt.BorderLayout.CENTER);

        jPanel2.add(jPanel3, java.awt.BorderLayout.CENTER);

        getContentPane().add(jPanel2, java.awt.BorderLayout.CENTER);

        jPanel11.setLayout(new java.awt.GridLayout(1, 0));

        Ltiempo.setText("Tiempo jugado: 00:00:00");
        jPanel11.add(Ltiempo);

        Lnombre.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jPanel11.add(Lnombre);

        Lpuntuacion.setText("Puntuacion: 0");
        jPanel11.add(Lpuntuacion);

        getContentPane().add(jPanel11, java.awt.BorderLayout.SOUTH);

        jMenu1.setText("Juego");

        MenuNuevaPartida.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_N, java.awt.event.InputEvent.CTRL_MASK));
        MenuNuevaPartida.setText("Nueva partida");
        jMenu1.add(MenuNuevaPartida);

        MenuGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        MenuGuardar.setText("Guardar partida");
        jMenu1.add(MenuGuardar);

        MenuCargar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        MenuCargar.setText("Cargar partida");
        jMenu1.add(MenuCargar);

        jMenuBar1.add(jMenu1);

        jMenu2.setText("Sobre el juego");

        MenuInformacion.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_I, java.awt.event.InputEvent.CTRL_MASK));
        MenuInformacion.setText("Información");
        MenuInformacion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuInformacionActionPerformed(evt);
            }
        });
        jMenu2.add(MenuInformacion);

        jMenuBar1.add(jMenu2);

        setJMenuBar(jMenuBar1);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void MenuInformacionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuInformacionActionPerformed
        ImageIcon imagen = new ImageIcon(getClass().getResource("imagenes//logo.png"));
        JOptionPane.showMessageDialog(this, "<html> <b>Alumno: </b> Javier Gonzalez Rodriguez <br>"
                + "<b>Curso: </b> 2017-2018 <br>"
                + "<b>Reto: </b> reto numero 2 <br>"
                + "<b>INTITUTO TECNOLOGICO PONIENTE</b> <br>"
                + "<img src="+imagen+"></html>");
    }//GEN-LAST:event_MenuInformacionActionPerformed

    private void BBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BBActionPerformed
        if (letraEsta("b")) {
            insertarLetra("B");
        }
    }//GEN-LAST:event_BBActionPerformed

    private void BAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAActionPerformed
        if (letraEsta("a")) {
            insertarLetra("A");
        }
    }//GEN-LAST:event_BAActionPerformed

    private void BCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCActionPerformed
        if (letraEsta("c")) {
            insertarLetra("C");
        }
    }//GEN-LAST:event_BCActionPerformed

    private void BDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDActionPerformed
        if (letraEsta("d")) {
            insertarLetra("D");
        }
    }//GEN-LAST:event_BDActionPerformed

    private void BEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEActionPerformed
        if (letraEsta("e")) {
            insertarLetra("E");
        }
    }//GEN-LAST:event_BEActionPerformed

    private void BFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BFActionPerformed
        if (letraEsta("f")) {
            insertarLetra("F");
        }
    }//GEN-LAST:event_BFActionPerformed

    private void BGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BGActionPerformed
        if (letraEsta("g")) {
            insertarLetra("G");
        }
    }//GEN-LAST:event_BGActionPerformed

    private void BHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BHActionPerformed
        if (letraEsta("h")) {
            insertarLetra("H");
        }
    }//GEN-LAST:event_BHActionPerformed

    private void BIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BIActionPerformed
        if (letraEsta("i")) {
            insertarLetra("I");
        }
    }//GEN-LAST:event_BIActionPerformed

    private void BJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BJActionPerformed
        if (letraEsta("j")) {
            insertarLetra("J");
        }
    }//GEN-LAST:event_BJActionPerformed

    private void BKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BKActionPerformed
        if (letraEsta("k")) {
            insertarLetra("K");
        }
    }//GEN-LAST:event_BKActionPerformed

    private void BLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BLActionPerformed
       if (letraEsta("l")) {
            insertarLetra("L");
        }
    }//GEN-LAST:event_BLActionPerformed

    private void BMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BMActionPerformed
        if (letraEsta("m")) {
            insertarLetra("M");
        }
    }//GEN-LAST:event_BMActionPerformed

    private void BNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BNActionPerformed
       if (letraEsta("n")) {
            insertarLetra("N");
        }
    }//GEN-LAST:event_BNActionPerformed

    private void BñActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BñActionPerformed
        if (letraEsta("ñ")) {
            insertarLetra("Ñ");
        }
    }//GEN-LAST:event_BñActionPerformed

    private void BOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BOActionPerformed
       if (letraEsta("o")) {
            insertarLetra("O");
        }
    }//GEN-LAST:event_BOActionPerformed

    private void BPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BPActionPerformed
        if (letraEsta("p")) {
            insertarLetra("P");
        }
    }//GEN-LAST:event_BPActionPerformed

    private void BQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BQActionPerformed
        if (letraEsta("q")) {
            insertarLetra("Q");
        }
    }//GEN-LAST:event_BQActionPerformed

    private void BRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BRActionPerformed
        if (letraEsta("r")) {
            insertarLetra("R");
        }
    }//GEN-LAST:event_BRActionPerformed

    private void BSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSActionPerformed
        if (letraEsta("s")) {
            insertarLetra("S");
        }
    }//GEN-LAST:event_BSActionPerformed

    private void BTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTActionPerformed
        if (letraEsta("t")) {
            insertarLetra("T");
        }
    }//GEN-LAST:event_BTActionPerformed

    private void BUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BUActionPerformed
        if (letraEsta("u")) {
            insertarLetra("U");
        }
    }//GEN-LAST:event_BUActionPerformed

    private void BVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BVActionPerformed
        if (letraEsta("v")) {
            insertarLetra("V");
        }
    }//GEN-LAST:event_BVActionPerformed

    private void BWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BWActionPerformed
        if (letraEsta("w")) {
            insertarLetra("W");
        }
    }//GEN-LAST:event_BWActionPerformed

    private void BXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BXActionPerformed
        if (letraEsta("x")) {
            insertarLetra("X");
        }
    }//GEN-LAST:event_BXActionPerformed

    private void BYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BYActionPerformed
        if (letraEsta("y")) {
            insertarLetra("Y");
        }
    }//GEN-LAST:event_BYActionPerformed

    private void BZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BZActionPerformed
        if (letraEsta("z")) {
            insertarLetra("Z");
        }
    }//GEN-LAST:event_BZActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Ahorcado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Ahorcado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Ahorcado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Ahorcado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Ahorcado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BA;
    private javax.swing.JButton BB;
    private javax.swing.JButton BC;
    private javax.swing.JButton BD;
    private javax.swing.JButton BE;
    private javax.swing.JButton BF;
    private javax.swing.JButton BG;
    private javax.swing.JButton BH;
    private javax.swing.JButton BI;
    private javax.swing.JButton BJ;
    private javax.swing.JButton BK;
    private javax.swing.JButton BL;
    private javax.swing.JButton BM;
    private javax.swing.JButton BN;
    private javax.swing.JButton BO;
    private javax.swing.JButton BP;
    private javax.swing.JButton BQ;
    private javax.swing.JButton BR;
    private javax.swing.JButton BS;
    private javax.swing.JButton BT;
    private javax.swing.JButton BU;
    private javax.swing.JButton BV;
    private javax.swing.JButton BW;
    private javax.swing.JButton BX;
    private javax.swing.JButton BY;
    private javax.swing.JButton BZ;
    private javax.swing.JButton Bresolver;
    private javax.swing.JButton Bñ;
    private javax.swing.JLabel Limagen;
    private javax.swing.JLabel Lnombre;
    private javax.swing.JLabel LnombreJuego;
    private javax.swing.JLabel Lpuntuacion;
    private javax.swing.JLabel Lresolver;
    private javax.swing.JLabel Ltexto;
    private javax.swing.JLabel Ltiempo;
    private javax.swing.JMenuItem MenuCargar;
    private javax.swing.JMenuItem MenuGuardar;
    private javax.swing.JMenuItem MenuInformacion;
    private javax.swing.JMenuItem MenuNuevaPartida;
    private javax.swing.JPanel PpalabraSecreta;
    private javax.swing.JTextField TFresolver;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    // End of variables declaration//GEN-END:variables
}