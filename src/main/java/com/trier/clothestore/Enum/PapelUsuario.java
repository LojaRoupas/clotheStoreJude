package com.trier.clothestore.Enum;

public enum PapelUsuario {
    ADMIN("admin"),
    USUARIO("usuario");

    private String papel;

    PapelUsuario (String papel){
        this.papel = papel;
    }

    public String getPapel(){
        return papel;
    }
}
