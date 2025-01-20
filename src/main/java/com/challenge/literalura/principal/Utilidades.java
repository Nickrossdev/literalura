package com.challenge.literalura.principal;

import java.util.List;
import java.util.Scanner;

public class Utilidades {
    public static String leerEntrada(){
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public static Integer leerEntradaNumerica(){
        try{
            return Integer.parseInt(leerEntrada());
        } catch (NumberFormatException e){
            imprimirMensaje("INGRESE UN VALOR NUMERICO");
            return null;
        }
    }

    public static Boolean validarEntradaNumerica(Integer numero){
        if (numero != null) {
            return true;
        } else {
            return false;
        }
    }

    public static void imprimirMensaje(String texto) {
        System.out.printf("""
                ------------------------------------------------------------------------------------------------
                %s
                ------------------------------------------------------------------------------------------------%n""", texto);
    }

    public static <T> void imprimirLista(List<T> lista){
        imprimirMensaje("LISTA");
        lista.forEach(System.out::println);
    }
}
