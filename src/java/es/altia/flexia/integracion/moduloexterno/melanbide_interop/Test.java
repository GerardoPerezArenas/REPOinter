package es.altia.flexia.integracion.moduloexterno.melanbide_interop;

public class Test {

    /**
     * @param args argumentos de entrada
     */
    public static void main(String[] args) {
        String from = "/r02g/v77b//09f424018077a1c51690370825152_121_73-1690370831350-8940313.pdf";
        System.out.println("from => " + from);
        String nombreFichero = from.substring(from.lastIndexOf("/"), from.length());
        System.out.println("nombreFichero => " + nombreFichero);
        String nombreFichero2 = from.substring(from.lastIndexOf("/") + 1, from.length());
        System.out.println("nombreFichero2 => " + nombreFichero2);
    }

}
