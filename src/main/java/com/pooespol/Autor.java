package com.pooespol;

public class Autor extends Usuario {
   private int codigoID;
   private String institucion;
   private String cInvest;

   public Autor(String nombre, String apellido, String correo, TipoRol rol, String usuario, String contrasenia, int codigoID, String institucion, String cInvest){
        super(nombre, apellido, correo, TipoRol.A,"noNecesita","noTiene");
        this.codigoID=codigoID;
        this.institucion=institucion;
        this.cInvest=cInvest;
   }

    public int getCodigoID() {
        return codigoID;
    }

    public void setCodigoID(int codigoID) {
        this.codigoID = codigoID;
    }

    public String getInstitucion() {
        return institucion;
    }

    public void setInstitucion(String institucion) {
        this.institucion = institucion;
    }

    public String getcInvest() {
        return cInvest;
    }

    public void setcInvest(String cInvest) {
        this.cInvest = cInvest;
    }
    
    @Override
    public String toString(){
        super.toString();
        return String.valueOf(codigoID)+", "+institucion+",  "+cInvest;
    }
   
}
