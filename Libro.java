/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Negocio;

/**
 *
 * @author Acer
 */
public class Libro extends Documento{
    private boolean prestado;

    public Libro(int codigo, String titulo, String autor, String editorial, int anoPub,boolean prestado) {
        super(codigo, titulo, autor, editorial, anoPub);
        this.prestado=prestado;
    }

    public boolean isPrestado() {
        return prestado;
    }

    public void setPrestado(boolean prestado) {
        this.prestado = prestado;
    }

    @Override
    public String toString() {
        return super.toString()+"prestado=" + prestado +"\n"+"-------------------------"+"\n";
    }
    
    
    
    
}
