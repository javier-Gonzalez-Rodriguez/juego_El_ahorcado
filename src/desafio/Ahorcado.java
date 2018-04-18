/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafio;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 * Ciclo formativo: Desarrollo de Aplicaciones Multiplataforma Módulo
 * profesional: Programación Reto número 2 Alumno: Javier Gonzalez Rodriguez
 * Curso académico: 2017-2018 Instituto Tecnológico Poniente
 *
 * @author javier
 */
public class Ahorcado extends javax.swing.JFrame {
    
    private ArrayList<String> lista_letras, letras_acertadas;
    private int intentos, puntos, aciertos, h , m, s;
    private Timer tiempo;

    /**
     * Creates new form Ahorcado
     */
    public Ahorcado() {
        initComponents();
        lista_letras = new ArrayList();
        
        letras_acertadas = new ArrayList();
        intentos = 1;
        
        puntos = 0;
        
        ImageIcon imagen = new ImageIcon(getClass().getResource("imagenes//im1.jpg"));
        Limagen.setIcon(imagen);
        ImageIcon icon = new ImageIcon(getClass().getResource("imagenes//interrogacion_azul.png"));
        String nombre;
        
        nombre = (String) JOptionPane.showInputDialog(this, null, "Introduce tu nombre", JOptionPane.PLAIN_MESSAGE, icon, null, null);
        if (nombre == null || nombre.equals("")) {
            nombre = "Sin nombre";
        }
        
        Lnombre.setText(nombre);
        
        buscarPalabra();
        
        h = 0;
        m = 0;
        s = 0;
        System.out.println(lista_letras);
        
        tiempo = new Timer(1000, accion);
        tiempo.start();
    }

    private ActionListener accion = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            s++;
            if (s == 60) {
                s = 0;
                m++;
            }
            if (m == 60) {
                m = 0;
                h++;
            }
            cronometro();
        }
    };
    /**
     *
     * busca una palabra
     *
     */
    private void buscarPalabra() {
        Random aleatorio = new Random();
        File archivo = null;
        FileReader flujo = null;
        BufferedReader leer = null;
        
        String palabra = "";
        try {
            archivo = new File("palabras.txt");
            flujo = new FileReader(archivo);
            leer = new BufferedReader(flujo);
            int contador = 1;
            
            while ((palabra = leer.readLine()) != null) {
                contador++;
            }
            int num = aleatorio.nextInt(contador);
            
            palabraSeleccionada(num);
            
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "error al leer palabra");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "error");
        } finally {
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
     *
     * @param num posicion de la palabra en el documento
     *
     */
    private void palabraSeleccionada(int num) {
        String p = "";
        File archivo = null;
        FileReader flujo = null;
        BufferedReader leer = null;
        try {
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
                letras_acertadas.add("_");
            }
            
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "error al seleccionar palabra");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "error al seleccionar palabra");
        }
        
    }

    /**
     * modificamos el JLabel para poner su letra
     *
     * @param letra letra a poner
     */
    private void insertarLetra(String letra) {
        int n_letras = 0;
        for (int i = 0; i < lista_letras.size(); i++) {
            if (lista_letras.get(i).toUpperCase().equals(letra)) {
                n_letras++;
                JLabel L = (JLabel) PpalabraSecreta.getComponent(i);
                L.setText(letra);
                letras_acertadas.set(i, letra);
            }
        }
        
        if (n_letras == 0) {
            Ltexto.setText("La letra " + letra + " no esta en la palabra");
            modificarImagen();
        } else {
            Ltexto.setText("La palabra contiene " + n_letras + " " + letra);
            aciertos += n_letras;
            puntosAciertos(false);
        }
    }

    /**
     * modifica la imagen del ahorcado al fallar
     */
    private void modificarImagen() {
        intentos++;
        ImageIcon i = new ImageIcon(getClass().getResource("imagenes//im" + intentos + ".jpg"));
        Limagen.setIcon(i);
        if (intentos == 6) {
            String palabra = "";
            for (int j = 0; j < lista_letras.size(); j++) {
                palabra += lista_letras.get(j);
            }
            JOptionPane.showMessageDialog(this, "Has perdido la palabra era " + palabra);
            Component b[] = Pletras.getComponents();
            
            for (int j = 0; j < b.length; j++) {
                b[j].setEnabled(false);
            }
        }
    }

    /**
     * nos da puntos por resolver la palabra o letras de esta
     *
     * @param resolucion nos dice si la palabra fue resuelta de golpe
     */
    private void puntosAciertos(boolean resolucion) {
        if (!resolucion) {
            puntos += lista_letras.size();
        } else {
            puntos += (lista_letras.size() * (lista_letras.size() - aciertos));
        }
        Lpuntuacion.setText("Puntuacion: " + puntos);
        System.out.println(aciertos);
        if (aciertos == lista_letras.size() || resolucion) {
            Component b[] = Pletras.getComponents();
            
            for (int j = 0; j < b.length; j++) {
                b[j].setEnabled(false);
            }
            String opciones[] = {"si", "no"};
            int seguir = JOptionPane.showOptionDialog(this, "acertaste la palabra ¿otra partida?"
                    + "", "Fin de ", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, null, opciones, opciones[1]);
            if (seguir == 0) {
                nuevaPartida(false);
            }
            
        }
        //lista_letras, letras_acertadas
    }

    /**
     * creamos una nueva partida
     *
     * @param eliminar especificamos si queremos comenzar de nuevo o mantener
     * puntuacion
     *
     */
    private void nuevaPartida(boolean eliminar) {
        lista_letras.removeAll(lista_letras);
        letras_acertadas.removeAll(letras_acertadas);
        
        if (eliminar) {
            puntos = 0;
        }
        
        aciertos = 0;
        
        intentos = 0;
        
        ImageIcon imagen = new ImageIcon(getClass().getResource("imagenes//im1.jpg"));
        Limagen.setIcon(imagen);
        
        Ltexto.setText("");
        
        PpalabraSecreta.removeAll();
        
        Component b[] = Pletras.getComponents();
        
        for (int j = 0; j < b.length; j++) {
            b[j].setEnabled(true);
        }
        buscarPalabra();
        h = 0;
        s = 0;
        m = 0;
        System.out.println(lista_letras);
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
        Pdatos = new javax.swing.JPanel();
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
        Pletras = new javax.swing.JPanel();
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
        BÑ = new javax.swing.JButton();
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

        Pdatos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Pdatos.setLayout(new java.awt.BorderLayout());

        jPanel3.setLayout(new java.awt.BorderLayout());

        LnombreJuego.setText("JUEGO DEL AHORCADO");
        LnombreJuego.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel4.add(LnombreJuego);

        jPanel3.add(jPanel4, java.awt.BorderLayout.NORTH);

        jPanel5.setLayout(new java.awt.BorderLayout());

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
        Bresolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BresolverActionPerformed(evt);
            }
        });
        jPanel9.add(Bresolver);

        jPanel7.add(jPanel9, java.awt.BorderLayout.NORTH);

        Pletras.setLayout(new java.awt.GridLayout(6, 6));

        BA.setText("A");
        BA.setFocusable(false);
        BA.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BAActionPerformed(evt);
            }
        });
        Pletras.add(BA);

        BB.setText("B");
        BB.setFocusable(false);
        BB.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BBActionPerformed(evt);
            }
        });
        Pletras.add(BB);

        BC.setText("C");
        BC.setFocusable(false);
        BC.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BCActionPerformed(evt);
            }
        });
        Pletras.add(BC);

        BD.setText("D");
        BD.setFocusable(false);
        BD.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDActionPerformed(evt);
            }
        });
        Pletras.add(BD);

        BE.setText("E");
        BE.setFocusable(false);
        BE.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BEActionPerformed(evt);
            }
        });
        Pletras.add(BE);

        BF.setText("F");
        BF.setFocusable(false);
        BF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BFActionPerformed(evt);
            }
        });
        Pletras.add(BF);

        BG.setText("G");
        BG.setFocusable(false);
        BG.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BGActionPerformed(evt);
            }
        });
        Pletras.add(BG);

        BH.setText("H");
        BH.setFocusable(false);
        BH.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BHActionPerformed(evt);
            }
        });
        Pletras.add(BH);

        BI.setText("I");
        BI.setFocusable(false);
        BI.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BIActionPerformed(evt);
            }
        });
        Pletras.add(BI);

        BJ.setText("J");
        BJ.setFocusable(false);
        BJ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BJActionPerformed(evt);
            }
        });
        Pletras.add(BJ);

        BK.setText("K");
        BK.setFocusable(false);
        BK.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BKActionPerformed(evt);
            }
        });
        Pletras.add(BK);

        BL.setText("L");
        BL.setFocusable(false);
        BL.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BLActionPerformed(evt);
            }
        });
        Pletras.add(BL);

        BM.setText("M");
        BM.setFocusable(false);
        BM.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BMActionPerformed(evt);
            }
        });
        Pletras.add(BM);

        BN.setText("N");
        BN.setFocusable(false);
        BN.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BNActionPerformed(evt);
            }
        });
        Pletras.add(BN);

        BÑ.setText("Ñ");
        BÑ.setFocusable(false);
        BÑ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BÑActionPerformed(evt);
            }
        });
        Pletras.add(BÑ);

        BO.setText("O");
        BO.setFocusable(false);
        BO.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BOActionPerformed(evt);
            }
        });
        Pletras.add(BO);

        BP.setText("P");
        BP.setFocusable(false);
        BP.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BPActionPerformed(evt);
            }
        });
        Pletras.add(BP);

        BQ.setText("Q");
        BQ.setFocusable(false);
        BQ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BQActionPerformed(evt);
            }
        });
        Pletras.add(BQ);

        BR.setText("R");
        BR.setFocusable(false);
        BR.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BRActionPerformed(evt);
            }
        });
        Pletras.add(BR);

        BS.setText("S");
        BS.setFocusable(false);
        BS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BSActionPerformed(evt);
            }
        });
        Pletras.add(BS);

        BT.setText("T");
        BT.setFocusable(false);
        BT.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BTActionPerformed(evt);
            }
        });
        Pletras.add(BT);

        BU.setText("U");
        BU.setFocusable(false);
        BU.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BUActionPerformed(evt);
            }
        });
        Pletras.add(BU);

        BV.setText("V");
        BV.setFocusable(false);
        BV.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BVActionPerformed(evt);
            }
        });
        Pletras.add(BV);

        BW.setText("W");
        BW.setFocusable(false);
        BW.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BWActionPerformed(evt);
            }
        });
        Pletras.add(BW);

        BX.setText("X");
        BX.setFocusable(false);
        BX.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BXActionPerformed(evt);
            }
        });
        Pletras.add(BX);

        BY.setText("Y");
        BY.setFocusable(false);
        BY.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BYActionPerformed(evt);
            }
        });
        Pletras.add(BY);

        BZ.setText("Z");
        BZ.setFocusable(false);
        BZ.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BZActionPerformed(evt);
            }
        });
        Pletras.add(BZ);

        jPanel7.add(Pletras, java.awt.BorderLayout.CENTER);

        jPanel5.add(jPanel7, java.awt.BorderLayout.CENTER);

        jPanel3.add(jPanel5, java.awt.BorderLayout.CENTER);

        Pdatos.add(jPanel3, java.awt.BorderLayout.CENTER);

        getContentPane().add(Pdatos, java.awt.BorderLayout.CENTER);

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
        MenuNuevaPartida.setToolTipText("reinicia el juego");
        MenuNuevaPartida.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuNuevaPartidaActionPerformed(evt);
            }
        });
        jMenu1.add(MenuNuevaPartida);

        MenuGuardar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_S, java.awt.event.InputEvent.CTRL_MASK));
        MenuGuardar.setText("Guardar partida");
        MenuGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuGuardarActionPerformed(evt);
            }
        });
        jMenu1.add(MenuGuardar);

        MenuCargar.setAccelerator(javax.swing.KeyStroke.getKeyStroke(java.awt.event.KeyEvent.VK_C, java.awt.event.InputEvent.CTRL_MASK));
        MenuCargar.setText("Cargar partida");
        MenuCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuCargarActionPerformed(evt);
            }
        });
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
                + "<img src=" + imagen + "></html>");
    }//GEN-LAST:event_MenuInformacionActionPerformed

    private void BBActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BBActionPerformed
        insertarLetra("B");
        BB.setEnabled(false);
    }//GEN-LAST:event_BBActionPerformed

    private void BAActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BAActionPerformed
        BA.setEnabled(false);
        insertarLetra("A");
    }//GEN-LAST:event_BAActionPerformed

    private void BCActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BCActionPerformed
        BC.setEnabled(false);
        insertarLetra("C");
    }//GEN-LAST:event_BCActionPerformed

    private void BDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDActionPerformed
        BD.setEnabled(false);
        insertarLetra("D");
    }//GEN-LAST:event_BDActionPerformed

    private void BEActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BEActionPerformed
        BE.setEnabled(false);
        insertarLetra("E");
    }//GEN-LAST:event_BEActionPerformed

    private void BFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BFActionPerformed
        BF.setEnabled(false);
        insertarLetra("F");
    }//GEN-LAST:event_BFActionPerformed

    private void BGActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BGActionPerformed
        BG.setEnabled(false);
        insertarLetra("G");
        
    }//GEN-LAST:event_BGActionPerformed

    private void BHActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BHActionPerformed
        BH.setEnabled(false);
        insertarLetra("H");
    }//GEN-LAST:event_BHActionPerformed

    private void BIActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BIActionPerformed
        BI.setEnabled(false);
        insertarLetra("I");
    }//GEN-LAST:event_BIActionPerformed

    private void BJActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BJActionPerformed
        BJ.setEnabled(false);
        insertarLetra("J");
    }//GEN-LAST:event_BJActionPerformed

    private void BKActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BKActionPerformed
        BK.setEnabled(false);
        insertarLetra("K");
    }//GEN-LAST:event_BKActionPerformed

    private void BLActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BLActionPerformed
        BL.setEnabled(false);
        insertarLetra("L");
    }//GEN-LAST:event_BLActionPerformed

    private void BMActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BMActionPerformed
        BM.setEnabled(false);
        insertarLetra("M");
    }//GEN-LAST:event_BMActionPerformed

    private void BNActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BNActionPerformed
        BN.setEnabled(false);
        insertarLetra("N");
    }//GEN-LAST:event_BNActionPerformed

    private void BÑActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BÑActionPerformed
        BÑ.setEnabled(false);
        insertarLetra("Ñ");
    }//GEN-LAST:event_BÑActionPerformed

    private void BOActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BOActionPerformed
        BO.setEnabled(false);
        insertarLetra("O");
    }//GEN-LAST:event_BOActionPerformed

    private void BPActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BPActionPerformed
        BP.setEnabled(false);
        insertarLetra("P");
    }//GEN-LAST:event_BPActionPerformed

    private void BQActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BQActionPerformed
        BQ.setEnabled(false);
        insertarLetra("Q");
    }//GEN-LAST:event_BQActionPerformed

    private void BRActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BRActionPerformed
        BR.setEnabled(false);
        insertarLetra("R");
    }//GEN-LAST:event_BRActionPerformed

    private void BSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BSActionPerformed
        BS.setEnabled(false);
        insertarLetra("S");
    }//GEN-LAST:event_BSActionPerformed

    private void BTActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BTActionPerformed
        BT.setEnabled(false);
        insertarLetra("T");
    }//GEN-LAST:event_BTActionPerformed

    private void BUActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BUActionPerformed
        BU.setEnabled(false);
        insertarLetra("U");
    }//GEN-LAST:event_BUActionPerformed

    private void BVActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BVActionPerformed
        BV.setEnabled(false);
        insertarLetra("V");
    }//GEN-LAST:event_BVActionPerformed

    private void BWActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BWActionPerformed
        BW.setEnabled(false);
        insertarLetra("W");
    }//GEN-LAST:event_BWActionPerformed

    private void BXActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BXActionPerformed
        BX.setEnabled(false);
        insertarLetra("X");
    }//GEN-LAST:event_BXActionPerformed

    private void BYActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BYActionPerformed
        BY.setEnabled(false);
        insertarLetra("Y");
    }//GEN-LAST:event_BYActionPerformed

    private void BZActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BZActionPerformed
        BZ.setEnabled(false);
        insertarLetra("Z");
    }//GEN-LAST:event_BZActionPerformed

    private void BresolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BresolverActionPerformed
        String palabra[] = TFresolver.getText().split("");
        boolean solucion = true;
        
        for (int i = 0; i < lista_letras.size(); i++) {
            if (!lista_letras.get(i).toLowerCase().equals(palabra[i].toLowerCase())) {
                solucion = false;
            }
        }
        
        if (solucion) {
            puntosAciertos(true);
            for (int i = 0; i < PpalabraSecreta.getComponentCount(); i++) {
                JLabel j = (JLabel) PpalabraSecreta.getComponent(i);
                j.setText(lista_letras.get(i).toUpperCase());
            }
        } else {
            modificarImagen();
            JOptionPane.showMessageDialog(this, "palabra incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
        }
        TFresolver.setText("");
    }//GEN-LAST:event_BresolverActionPerformed

    private void MenuNuevaPartidaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuNuevaPartidaActionPerformed
        nuevaPartida(true);
    }//GEN-LAST:event_MenuNuevaPartidaActionPerformed

    private void MenuGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuGuardarActionPerformed
        tiempo.stop();
        Partida p = new Partida(puntos, intentos, aciertos, lista_letras, letras_acertadas, Lnombre.getText(), Limagen, h, m, s);
        Calendar c = new GregorianCalendar();
        
        String hora = c.get(Calendar.HOUR_OF_DAY) + "-";
        String minuto = c.get(Calendar.MINUTE) + "-";
        String segundo = "" + c.get(Calendar.SECOND);
        
        String nombre_fichero = "Partida_" + Lnombre.getText() + "_" + c.get(Calendar.YEAR) + "_" + (c.get(Calendar.MONTH) + 1) + "_hora_" + hora + minuto + segundo + ".sav";
        
        File archivo = null;
        FileOutputStream flujo = null;
        ObjectOutputStream guardar = null;
        
        try {
            archivo = new File("Partidas//" + nombre_fichero);
            flujo = new FileOutputStream(archivo);
            guardar = new ObjectOutputStream(flujo);
            
            guardar.writeObject(p);
            
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(this, "fallo en flujo de datos", "Error", JOptionPane.ERROR_MESSAGE);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this, "fallo al guardar", "Error", JOptionPane.ERROR_MESSAGE);
        } finally {
            try {
                guardar.close();
                flujo.close();
                JOptionPane.showMessageDialog(this, "partida guardada con exito");
                tiempo.start();
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "fallo al cerrar flujo o guardar", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_MenuGuardarActionPerformed

    private void MenuCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuCargarActionPerformed
        
        FileInputStream flujo = null;
        ObjectInputStream cargar = null;
        
        Partida p = null;
        
        JFileChooser archivo = new JFileChooser("Partidas");
        
        archivo.setFileFilter(new FileNameExtensionFilter("archivo de partida", "sav"));
        
        int i = archivo.showOpenDialog(null);
        if (i == JFileChooser.APPROVE_OPTION) {
            try {
                flujo = new FileInputStream(archivo.getSelectedFile());
                cargar = new ObjectInputStream(flujo);
                p = (Partida) cargar.readObject();
                
            } catch (FileNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "fallo al cargar datos", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(this, "archivo corrupto", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (ClassNotFoundException ex) {
                JOptionPane.showMessageDialog(this, "fallo al cargar", "Error", JOptionPane.ERROR_MESSAGE);
            }
            finally{
                try {
                    cargar.close();
                    flujo.close();
                    aciertos = p.getAciertos();
                    puntos = p.getPuntuacion();
                    Lpuntuacion.setText("Puntuacion: " + puntos);
                    intentos = p.getIntentos();
                    lista_letras = p.getLetras();
                    letras_acertadas = p.getLertas_acertadas();
                    Lnombre.setText("");
                    h = p.getH();
                    m = p.getM();
                    s = p.getS();
                    PpalabraSecreta.removeAll();
                    
                    Limagen.setIcon(p.getImagen().getIcon());
                    
                    
                    for (int j = 0; j < letras_acertadas.size(); j++) {
                        PpalabraSecreta.add(new JLabel(letras_acertadas.get(j)));
                    }
                    Lnombre.setText(p.getNombre());
                    this.invalidate();
                    this.validate();
                    this.repaint();
                    JOptionPane.showMessageDialog(this, "partida cargada con exito");
                } catch (IOException ex) {
                    Logger.getLogger(Ahorcado.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        
    }//GEN-LAST:event_MenuCargarActionPerformed

    /**
     * actualiza el tiempo del cronometro
     */
    private void cronometro(){
        String horas = h+"";
        String minutos = m+"";
        String segundos = s+"";
        if (h < 10) {
            horas = 0+horas;
        }
        if (s < 10) {
            segundos = "0"+segundos;
        }
        if (m < 10 ) {
            minutos = 0+minutos;
        }
        Ltiempo.setText("Tiempo jugado: "+horas+":"+minutos+":"+segundos);
    }
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
    private javax.swing.JButton BÑ;
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
    private javax.swing.JPanel Pdatos;
    private javax.swing.JPanel Pletras;
    private javax.swing.JPanel PpalabraSecreta;
    private javax.swing.JTextField TFresolver;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenuBar jMenuBar1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    // End of variables declaration//GEN-END:variables
}
