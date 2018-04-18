/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafio;

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 *
 * @author javier
 */
public class Partida implements Serializable{
    private int puntuacion, intentos, aciertos, h, m, s;
    private ArrayList<String> letras, lertas_acertadas;
    private String nombre;
    private JLabel imagen;

    public Partida() {
        this.puntuacion = 0;
        this.intentos = 0;
        this.aciertos = 0;
        this.letras = new ArrayList();
        this.lertas_acertadas = new ArrayList();
        this.nombre = "";
        this.imagen = null;
        this.h = 0;
        this.s = 0;
        this.m = 0;
    }

    public Partida(int puntuacion, int intentos, int aciertos, ArrayList<String> letras, ArrayList<String> letras_acertadas, String nombre, JLabel label_img
    , int hor, int min, int seg) {
        this.puntuacion = puntuacion;
        this.intentos = intentos;
        this.aciertos = aciertos;
        this.letras = letras;
        this.lertas_acertadas = letras_acertadas;
        this.nombre = nombre;
        this.imagen = label_img;
        this.h = hor;
        this.s = seg;
        this.m = min;
    }

    public int getPuntuacion() {
        return puntuacion;
    }

    public int getIntentos() {
        return intentos;
    }

    public int getAciertos() {
        return aciertos;
    }

    public int getH() {
        return h;
    }

    public int getM() {
        return m;
    }

    public int getS() {
        return s;
    }

    public ArrayList<String> getLetras() {
        return letras;
    }

    public ArrayList<String> getLertas_acertadas() {
        return lertas_acertadas;
    }

    public String getNombre() {
        return nombre;
    }

    public JLabel getImagen() {
        return imagen;
    }
}
