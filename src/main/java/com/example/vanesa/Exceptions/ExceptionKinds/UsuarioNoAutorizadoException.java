package com.example.vanesa.Exceptions.ExceptionKinds;

public class UsuarioNoAutorizadoException extends RuntimeException  {
    public UsuarioNoAutorizadoException (String message, int i){super(message);}
}
