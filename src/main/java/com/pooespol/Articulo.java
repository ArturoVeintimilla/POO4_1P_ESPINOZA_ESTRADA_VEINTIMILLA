package com.pooespol;

public class Articulo {
   private String titulo;
   private String resumen;
   private String contenido;
   private String palabrasClave; 
   private TipoEstado estado;
   private int articuloID;
   
   public Articulo(String titulo, String resumen, String contenido, String palabrasClave, TipoEstado estado, int articuloID){
        this.titulo=titulo;
        this.resumen=resumen;
        this.contenido=contenido;
        this.palabrasClave=palabrasClave;
        this.estado=estado;
        this.articuloID=articuloID;
   }

        public String getTitulo() {
           return titulo;       
        }

        public void setTitulo(String titulo) {
            this.titulo = titulo;
        }

        public String getResumen() {
            return resumen;
        }

        public void setResumen(String resumen) {
            this.resumen = resumen;
        }

        public String getContenido() {
            return contenido;
        }

        public void setContenido(String contenido) {
            this.contenido = contenido;
        }

        public String getPalabrasClave() {
            return palabrasClave;
        }

        public void setPalabrasClave(String palabrasClave) {
            this.palabrasClave = palabrasClave;
        }

        public TipoEstado getEstado() {
            return estado;
        }

        public void setEstado(TipoEstado estado) {
            this.estado = estado;
        }

        public int getArticuloID() {
            return articuloID;
        }

        public void setArticuloID(int articuloID) {
            this.articuloID = articuloID;
        }

        @Override
        public String toString() {
            return titulo + ", " + resumen + ", "+ contenido + ", " + palabrasClave + ", " + estado + ", " + articuloID ;
        }
   

}
