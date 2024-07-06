package com.pooespol.Publicacion;

import java.util.ArrayList;

import com.pooespol.Principales.Editor;
import com.pooespol.Principales.Revisor;
import com.pooespol.Tipos.EstadoArticulo;

public class Articulo {
    private Autor autor;
    private int codigoArticulo;
    private String titulo;
    private String resumen;
    private String contenido;
    private String palabrasClave;
    private EstadoArticulo estado;
    private ArrayList<Revisor> revisores;
    private Editor editor;

    public Articulo(Autor autor ,int codigoArticulo, String titulo, String resumen, String contenido, String palabrasClave) {
        this.codigoArticulo = codigoArticulo;
        this.titulo = titulo;
        this.resumen = resumen;
        this.contenido = contenido;
        this.palabrasClave = palabrasClave;
        this.revisores = new ArrayList<>();
        this.editor = null; // Inicialmente no tiene editor asignado
        this.estado=EstadoArticulo.SINREVISION;
    }
    public Autor getAutor() {
        return autor;
    }

    public int getCodigoArticulo() {
        return codigoArticulo;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getResumen() {
        return resumen;
    }

    public String getContenido() {
        return contenido;
    }

    public String getPalabrasClave() {
        return palabrasClave;
    }

    public EstadoArticulo getEstado() {
        return estado;
    }

    public void setEstado(EstadoArticulo estado) {
        this.estado = estado;
    }
     public void agregarRevisor(Revisor revisor) {
        revisores.add(revisor);
    }

    public ArrayList<Revisor> getRevisores() {
        return revisores;
    }

    // Métodos para manejar editor
    public void setEditor(Editor editor) {
        this.editor = editor;
    }

    public Editor getEditor() {
        return editor;
    }

    
    @Override
    public String toString() {
        return "El Articulo con " +
                "Titulo='" + titulo + '\'' +
                ",codigo del Articulo=" + codigoArticulo +
                ", resumen='" + resumen + '\'' +
                ", contenido='" + contenido + '\'' +
                ", palabrasClave='" + palabrasClave + '\'' +
                ", estado=" + estado +
                " fue sometido existosamente";
    }
}

