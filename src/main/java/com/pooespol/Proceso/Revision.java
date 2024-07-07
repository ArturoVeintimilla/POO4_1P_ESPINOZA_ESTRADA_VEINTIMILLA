package com.pooespol.Proceso;

import com.pooespol.Main.Aplicacion;
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
    

    public String imprimirRevision() {
        String informe = "=== Información de la Revisión ===\n" +
            "Artículo: " + articulo.getTitulo() + " (ID: " + articulo.getCodigoArticulo() + ")\n" +
            "------------------------------------------------------------------\n" +
            "Revisor 1: " + revisor1.getNombre() + " " + revisor1.getApellido() + "\n" +
            "Comentarios Revisor 1: " + revisor1.getComentarios() + "\n" +
            "Decisión Revisor 1: " + revisor1.getDecision() + "\n" +
            "------------------------------------------------------------------\n" +
            "Revisor 2: " + revisor2.getNombre() + " " + revisor2.getApellido() + "\n" +
            "Comentarios Revisor 2: " + revisor2.getComentarios() + "\n" +
            "Decisión Revisor 2: " + revisor2.getDecision() + "\n" +
            "------------------------------------------------------------------\n" +
            "Decisión Editor: " + editor.getDecision() + "\n" +
            "------------------------------------------------------------------\n" +
            "Estado del Artículo: " + articulo.getEstado() + "\n";

         Aplicacion.escribirArchivo("C:\\VisualStudioCode\\proyecto\\src\\main\\java\\com\\pooespol\\Informacion.txt\\Revision.txt", "Articulo:"+articulo.getTitulo()+", Comentarios R1: "+revisor1.getComentarios()+", Comentarios R2:"+revisor2.getComentarios()+", Decision de Editor "+editor.getNombre()+": "+editor.getDecision());
        return informe;
    }
}
