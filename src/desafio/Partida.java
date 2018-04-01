/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package desafio;

import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author javier
 */
public class Partida implements Serializable{
    private int puntuacion, intentos, aciertos;
    private ArrayList<String> letras;

    public Partida() {
        this.puntuacion = 0;
        this.intentos = 0;
        this.aciertos = 0;
        this.letras = new ArrayList();
    }

    public Partida(int puntuacion, int intentos, int aciertos, ArrayList<String> letras) {
        this.puntuacion = puntuacion;
        this.intentos = intentos;
        this.aciertos = aciertos;
        this.letras = letras;
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

    public ArrayList<String> getLetras() {
        return letras;
    }
    
}
