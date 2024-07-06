package com.pooespol.Proceso;

import com.pooespol.Principales.Editor;
import com.pooespol.Principales.Revisor;
import com.pooespol.Publicacion.Articulo;
import com.pooespol.Tipos.EstadoArticulo;

public class Revision {
    private Articulo articulo;
    private Revisor revisor1;
    private Revisor revisor2;
    private Editor editor;

    public Revision(Articulo articulo, Revisor revisor1, Revisor revisor2, Editor editor) {
        this.articulo = articulo;
        this.revisor1 = revisor1;
        this.revisor2 = revisor2;
        this.editor = editor;
    }

    public Revisor getRevisor1() {
        return revisor1;
    }

    public void setRevisor1(Revisor revisor1) {
        this.revisor1 = revisor1;
    }

    public Revisor getRevisor2() {
        return revisor2;
    }

    public void setRevisor2(Revisor revisor2) {
        this.revisor2 = revisor2;
    }

    public Articulo getArticulo() {
        return articulo;
    }

    public void setArticulo(Articulo articulo) {
        this.articulo = articulo;
    }



    public void resultadoRevision() {
        if (editor.getDecision()) {
            articulo.setEstado(EstadoArticulo.PUBLICADO);
            System.out.println("El articulo: " + articulo.getTitulo() + " fue publicado, ¡felicidades!");
        } else {
            articulo.setEstado(EstadoArticulo.NOPUBLICADO);
            System.out.println("El articulo: " + articulo.getTitulo() + " no fue publicado o el editor todavia no toma la decision final, intenta mas tarde :)");
        }
    }
    

    public void imprimirRevision() {
        if(revisor1.getComentarios()!= null && revisor2.getComentarios()!=null){
            System.out.println("=== Información de la Revisión ===");
            System.out.println("Articulo: " + articulo.getTitulo() + " (ID: " + articulo.getCodigoArticulo() + ")");
            System.out.println("------------------------------------------------------------------");
            System.out.println("Revisor 1: " + revisor1.getNombre() + " " + revisor1.getApellido());
            System.out.println("Comentarios Revisor 1: " + revisor1.getComentarios());
            System.out.println("Decisión Revisor 1: " + revisor1.getDecision());
            System.out.println("------------------------------------------------------------------");
            System.out.println("Revisor 2: " + revisor2.getNombre() + " " + revisor2.getApellido());
            System.out.println("Comentarios Revisor 2: " + revisor2.getComentarios());
            System.out.println("Decisión Revisor 2: " + revisor2.getDecision());
            System.out.println("------------------------------------------------------------------");
            System.out.println("Decisión Editor: " + editor.getDecision());
            System.out.println("------------------------------------------------------------------");
            System.out.println("Estado del Artículo: " + articulo.getEstado());
            resultadoRevision();
        }
        else{
            System.out.println(" El articulo: "+ articulo.getTitulo()+ " sigue en proceso de revision");
        }
    }
}
