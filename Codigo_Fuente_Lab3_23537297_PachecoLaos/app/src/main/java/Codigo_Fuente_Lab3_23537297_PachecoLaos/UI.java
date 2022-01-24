package Codigo_Fuente_Lab3_23537297_PachecoLaos;

import java.util.ArrayList;
import java.util.Scanner;
import modelos.date;
import modelos.editor;

/**
 * Clase que representa toda la interfaz de usuario con el que el usuario interactuara
 * @author Alex Pacheco Laos
 */
public interface UI {
    
    static Scanner lectura = new Scanner(System.in).useDelimiter("\n");
    public static void print(String things){
        System.out.println(things);
    }
    /**
     * funcion que genera todos los testeos sobre las plataformas
     * @param plataformas array de plataformas sobre los cuales aplicar los testeos
     */
    public static void testGenerator(ArrayList<editor> plataformas){
        plataformas.add(new editor("ParadigmaDocs", new date(12,12,2021)));
        /*
        Generacion de testeos dentro de ParadigmaDocs
        */
        //creacion de 4 usuarios 
        plataformas.get(0).addUser("Sebastian","123456", new date(06,01,2022));
        plataformas.get(0).addUser("elcangri","asdfgh", new date(06,01,2022));
        plataformas.get(0).addUser("elsopaipilla","123456", new date(06,01,2022));
        plataformas.get(0).addUser("Pedro","abcdef", new date(06,01,2022));

        
        //creacion de documentos
        plataformas.get(0).authenticateUser("Sebastian","123456");
        /*id 0*/plataformas.get(0).addDoc("Lista de deseo", new date(12,12,2021), "Esta sera la lista de deseos para mi anio 2022");
        /*id 1*/plataformas.get(0).addDoc("Informe de IDI 4 (TDI)", new date(12,12,2021), "Integrantes: Sebastian, Pedro y el cangri (xd)");
        
        plataformas.get(0).authenticateUser("Pedro","asdfgh");
        /*id 2*/plataformas.get(0).addDoc("Poemas", new date(20,1,2022), "Rosas azuels...");
        /*id 3*/plataformas.get(0).addDoc("Lista de compra", new date(20,1,2022), "Cebollas 3, tallarines 4, ...");
        
        plataformas.get(0).authenticateUser("elcangri","asdfgh");
        /*id 4*/plataformas.get(0).addDoc("Cumpleanos de la deyanira", new date(25,12,2021), "Tengo q compraleh algo a deyianira, si o no");
        plataformas.get(0).shareDoc("Sebastian,elsopaipilla".split(","), "C", 3);
        
        //Compartir Docs
        plataformas.get(0).authenticateUser("Sebastian","123456");
        plataformas.get(0).shareDoc("elcangri,Pedro".split(","),"W",1);
        
        plataformas.get(0).authenticateUser("Pedro","asdfgh");
        plataformas.get(0).shareDoc("Sebastian,Pedro".split(","),"C",2);
        
        plataformas.get(0).authenticateUser("elcangri","asdfgh");
        plataformas.get(0).shareDoc("elsopaipilla,Pedro".split(","),"R",4);
        
            //Intenta compartir un documento y no es realizado porque no es propietario
        plataformas.get(0).authenticateUser("elsopaipilla","123456");
        plataformas.get(0).shareDoc("elcangri,Sebastian".split(","),"R",4);
        
        //Agregar contenido a los documentos
        plataformas.get(0).authenticateUser("Sebastian","123456");
        plataformas.get(0).addContentToDoc(0,"Espero lograr pasar todos mis ramos, sobre todo paradigmadocs de programacion");
        plataformas.get(0).addContentToDoc(1,"Se ha demostrado en varias ocasiones que el Sistema de Transporte P�blico Metropolitano (STPM) no es un medio seguro");
        
        plataformas.get(0).authenticateUser("Pedro","asdfgh");
        plataformas.get(0).addContentToDoc(1,", especialmente para las mujeres en relaci�n al tema del acoso.");
        
        plataformas.get(0).authenticateUser("elcangri","asdfgh");
        plataformas.get(0).addContentToDoc(1,"De acuerdo a las cifras difundidas por la agrupaci�n �Mujeres en Movimiento'' y el Banco");
        plataformas.get(0).addContentToDoc(1,"para el Desarrollo de Latinoam�rica (CAF), 9");
        //Restaurar version de un documento
        plataformas.get(0).authenticateUser("Sebastian","123456");
        plataformas.get(0).restoreVersion(0, 0);
        plataformas.get(0).authenticateUser("Pedro","asdfgh");
        plataformas.get(0).restoreVersion(0, 0);
        plataformas.get(0).authenticateUser("elcangri","asdfgh");
        plataformas.get(0).restoreVersion(1, 1);
        //Revocar acceso a un documento
        plataformas.get(0).authenticateUser("Sebastian","123456");
        plataformas.get(0).revokeAccess(0);
        plataformas.get(0).authenticateUser("Pedro","asdfgh");
        plataformas.get(0).revokeAccess(4);
        plataformas.get(0).authenticateUser("elcangri","asdfgh");
        plataformas.get(0).revokeAccess(1);
        // Volver a dar permisos
        plataformas.get(0).authenticateUser("Sebastian","123456");
        plataformas.get(0).shareDoc("elcangri,Pedro".split(","),"W",1);
        
        plataformas.get(0).authenticateUser("Pedro","asdfgh");
        plataformas.get(0).shareDoc("Sebastian,Pedro".split(","),"C",2);
        
        plataformas.get(0).authenticateUser("elcangri","asdfgh");
        plataformas.get(0).shareDoc("elsopaipilla,Pedro".split(","),"R",4);
        // Buscar en los documentos
        plataformas.get(0).authenticateUser("Sebastian","123456");
        plataformas.get(0).search("flores");
        //Borrar y reemplazar en un documento
        plataformas.get(0).authenticateUser("Sebastian","123456");
        plataformas.get(0).replace(0, "deseos", "dia del plaatanoo");
        // Aplicar estilos en un documento
        //Se define como una funcion que reutiliza otro metodo
        
        //Comentar en un documento
        plataformas.get(0).authenticateUser("Sebastian","123456");
        plataformas.get(0).addComment(0,"plaatanoo","Pienso que debe mejorar mi vocabulario");
        
        /*
        Generacion de testeos dentro de googleDocs, pero quien quiere google docs
        cuando tiene el poderisisimo ParadigmaDocs?
        */
        plataformas.add(new editor("GoogleDocs", new date(12,12,2021)));
    }
    /**
     * funcion que realiza la eleccion de las plataforma a afectar
     * @param plataformas arraylist de plataformas a afectar
     * @return numero que representa la posicion dentro del arraylist
     */
    public static int choosePlataform   (ArrayList<editor> plataformas){

        int i;
        print("Ingrese la plataforma que desea utilizar(ingrese el numero y luego presione enter): ");
        for(i = 0; i < plataformas.size();i++){
            print(String.format("%d. %s",i+1,plataformas.get(i).getName()));
        }
        print(String.format("%d. Crear plataforma",i+1));
        print("0. Salir del programa");
        String opcion = lectura.nextLine();
        if(opcion.equals(String.format("%d",i+1))){
            print("Ingrese el nombre de la nueva plataforma: ");
            String nombre = lectura.nextLine();
            print("Ingrese la fecha separado por guiones de la forma dia-mes-agnio: ");
            String[] fechacreacion = lectura.nextLine().split("-");
            int dia = Integer.parseInt(fechacreacion[0]);
            int mes = Integer.parseInt(fechacreacion[1]);
            int agnio = Integer.parseInt(fechacreacion[2]);
            date prdocmaked = new date(dia,mes,agnio);
            editor newprdoc = new editor(nombre,prdocmaked);
            plataformas.add(newprdoc);
            return plataformas.size()-1;
        }else{
            return Integer.parseInt(opcion)-1;
        }
    }
    /**
     * funcion que autentifica las credenciales dentro de la plataforma ingresada
     * @param plataforma plataforma a afectar
     * @return boolean que representa la autenticacion dentro de la plataforma
     */
    public static boolean authentification (editor plataforma){
        boolean logueado = false;
        String opcionlogueo = "0";
        print(String.format("=== %s ===",plataforma.getName()));
        while(!logueado){
            print("Escoja su opcion:\n1. Registrarse\n2. Loguearse\n0. Salir");
            opcionlogueo = lectura.nextLine();
            if(opcionlogueo.equals("1")){
                print("Ingrese su nombre de usuario:");
                String username = lectura.nextLine();
                while(plataforma.existUsername(username)){
                    print("Tal nombre de usuario ya existe\n Ingrese otro nombre de usuario:");
                    username = lectura.nextLine();
                }
                print("Ingrese su contrasenia:");
                String password = lectura.nextLine();
                print("Ingrese la fecha separado por guiones de la forma dia-mes-agnio: ");
                String[] fechacreacion = lectura.nextLine().split("-");
                int dia = Integer.parseInt(fechacreacion[0]);
                int mes = Integer.parseInt(fechacreacion[1]);
                int agnio = Integer.parseInt(fechacreacion[2]);
                date fechausuario = new date(dia,mes,agnio);
                plataforma.addUser(username, password, fechausuario);
                logueado = plataforma.authenticateUser(username,password);
            }else if(opcionlogueo.equals("2")){
                print("Ingrese su nombre de usuario:");
                String username = lectura.nextLine();
                print("Ingrese su contrasenia:");
                String password = lectura.nextLine();
                if(plataforma.authenticateUser(username,password)){
                    logueado = true;
                }else{
                    print("No ha logrado loguearse correctamente\n");
                }
            }else if(opcionlogueo.equals("0")){
                return false;
            }
        }
        return false;
    }
    
    /**
     * funcion que crea un documento dentro de la plataforma seleccionada
     * 
     * @param plataforma plataforma a afectar
     */
    public static void create           (editor plataforma){
        print("=== Crear nuevo documento ===");
        print("Ingrese el nombre de su nuevo documento: ");
        String titulo = lectura.nextLine();
        print("Ingrese la fecha de creacion: ");
        String[] fecha = lectura.nextLine().split("-");
        print("Ingrese el contenido de su documento: ");
        String contenido = lectura.nextLine();
        plataforma.addDoc(titulo, 
                new date(Integer.parseInt(fecha[0]),
                        Integer.parseInt(fecha[1]),
                        Integer.parseInt(fecha[2])), 
                contenido);
    }
    
    /**
     * comparte un documento de la plataforma seleccionada
     * 
     * @param plataforma plataforma a afectar
     */
    public static void share            (editor plataforma){
        print("=== Compartir documento ===");
        print("Ingrese el id del documento que desea compartir: ");
        int id = Integer.parseInt(lectura.nextLine());
        print("Ingrese los nombres de los usuario con los que desea compartir el documento separado por comas:");
        String[] names = lectura.nextLine().split(",");
        print("Ingrese la letra del permiso que quiere compartir, siendo W-Escritura, R-Lectura, C-Comentar");
        String permiso = lectura.nextLine();
        if(plataforma.shareDoc(names, permiso, id)){
            print("El documento a sido compartido exitosamente");
            return;
        }
        print("No se es due�o del documento que se quiere compartir.\n. . .");
    }
    
    /**
     * agrega contenido a un documento de la plataforma
     * 
     * @param plataforma plataforma a afectar
     */
    public static void add              (editor plataforma){
        print("=== Agregar Contenido ===");
        print("Ingrese el id del documento en el que desea agregar contenido: ");
        int id = Integer.parseInt(lectura.nextLine());
        print("Ingrese el contenido que desea agregar");
        String contenido = lectura.nextLine();
        if(plataforma.addContentToDoc(id, contenido)){
            print("Se agrego el contenido al documento como se esperaba");
            return;
        }
        print("No tienes permisos para agregar contenido a tal documento\n. . .");
    }
    
    /**
     * restaruracion de una version en la plataforma
     * 
     * @param plataforma plataforma a afectar
     */
    public static void rollback         (editor plataforma){
        print("=== Restaurar version ===");
        print("Ingrese el id del documento en el que restaurara la version: ");
        int idDoc = Integer.parseInt(lectura.nextLine());
        print("Ingrese el id de la version a restaurar: ");
        int idversion = Integer.parseInt(lectura.nextLine());
        if(plataforma.restoreVersion(idDoc, idversion)){
            print("Se restaur� la versi�n como se esperaba.");
            return;
        }
        print("No tienes permisos para realizar tal accion\n. . .");
        
    }
    
    /**
     * revoca los accesos dentro de un plataforma
     * 
     * @param plataforma plataforma a afectar
     */
    public static void revokeAccess     (editor plataforma){
        print("=== Revocar accesos ===");
        print("Ingrese el id del documento:");
        int idDoc = Integer.parseInt(lectura.nextLine());
        if(plataforma.revokeAccess(idDoc)){
            print("Se revoco los permisos como se esperaba.");
            return;
        }
        print("No tienes permisos para revocar permisos\n. . .");
    }
    
    /**
     * busqueda de informacion dentro de la plataforma
     * @param plataforma plataforma a afectar
     */
    public static void search           (editor plataforma){
        print("=== Buscar contenido ===");
        print("Ingrese la palabra que desea buscar en los documentos:");
        String word = lectura.nextLine();
        String docs = plataforma.search(word);
        if(docs.equals("")){
            print("Ningun documento encontrado con tal coincidencia");
        }
        print(String.format("Los documentos que se encontraro con tal coincidencia son: %s",docs));
    }
    
    /**
     * genera un comentario en un documento en particular
     * @param plataforma plataforma a afectar
     */
    public static void comment          (editor plataforma){
        print("=== Comentar ===");
        print("Ingrese el id del documento: ");
        int id = Integer.parseInt(lectura.nextLine());
        print("Ingrese el texto que desea comentar");
        String texto = lectura.nextLine();
        print("Ingrese el comentario que desea realizar");
        String comentario = lectura.nextLine();
        if(plataforma.addComment(id,texto,comentario)){
            print("Se realizo el comentario como se esperaba");
            return;
        }
        print("No se logro realizar el comentario\n . . .");
    }
    
    /**
     * Elimina informacion dentro de un documento en particular
     * 
     * @param plataforma plataforma a afectar
     */
    public static void delete           (editor plataforma){
        print("=== Eliminar caracteres ===");
        print("Ingrese el id del documento a afectar: ");
        int id = Integer.parseInt(lectura.nextLine());
        print("Ingrese la cantidad de caracteres que desea eliminar(numero entero): ");
        int numChars = Integer.parseInt(lectura.nextLine());
        if(plataforma.delete(id,numChars)){
            print("Se eliminaron los caracteres correcatmente");
            return;
        }
        print("No se logro borrar contenido . . .");
        
    }
    
    /**
     * Reemplaza contenido dentro de un documento en la plataforma
     * @param plataforma plataforma a afectar
     */
    public static void replace          (editor plataforma){
        print("=== Buscar y Reemplazar ===");
        print("Ingrese el id del documento a afectar: ");
        int id = Integer.parseInt(lectura.nextLine());
        print("Ingrese la cadena de texto a reemplazar: ");
        String oldChar = lectura.nextLine();
        print("Ingrese la cadena de reemplazo: ");
        String newChar = lectura.nextLine();
        if(plataforma.replace(id,oldChar,newChar)){
            print("Se reemplazo la cadena correcatmente");
            return;
        }
        print("No se logro reemplazar contenido . . .");
    }
    
    /**
     * Aplica estilos en un documento en la plataforma
     * 
     * @param plataforma plataforma a afectar
     */
    public static void applyStyles     (editor plataforma){
        print("=== Aplicar Estilos ===");
        print("Ingrese el id del documento a afectar: ");
        int id = Integer.parseInt(lectura.nextLine());
        print("Ingrese la cadena de texto a la que aplicar estilos: ");
        String oldChar = lectura.nextLine();
        print("=== Estilos ===\n1. Italic\n2. Bold\n3. Underlined");
        print("Ingrese los numero de estilos separados por comas: ");
        String[] estilos = lectura.nextLine().split(",");
        String newChar = oldChar;
        for(int i = 0; i < estilos.length; i ++){
            if(estilos[i].equals("1")){
                newChar = "#\\i" + newChar + "#\\i";
            }else if(estilos[i].equals("2")){
                newChar = "#\\b" + newChar + "#\\b";
            }else if(estilos[i].equals("3")){
                newChar = "#\\u" + newChar + "#\\u";
            }
        }
        if(plataforma.replace(id,oldChar,newChar)){
            print("Se reemplazo la cadena correcatmente");
            return;
        }
        print("No se logro reemplazar contenido . . .");
    }
    
    /**
     * Menu de opcioens que eligira el usuario
     * 
     * @param plataforma plataforma a afectar
     * @return entero que representa el numero de la operacion
     */
    public static int menu              (editor plataforma){
        print(String.format("### %s ###\n## Usuario: %s ##\n"+
                      "Escoja su opcion:\n"+
                      "1. Crear nuevo documento\n"+
                      "2. Compartir documento\n" +
                      "3. Agregar contenido a un documento\n"+
                      "4. Restaurar version de un documento\n"+
                      "5. Revocar acceso a un documento\n"+
                      "6. Buscar en los documentos\n"+
                      "7. Borrar caracteres en un documento\n"+
                      "8. Buscar y reemplazar en un documento\n"+
                      "9. Aplicar estilos en un documento\n"+
                      "10. Comentar en un documento\n"+
                      "11. Visualizar documentos\n"+
                      "12. Cerrar sesi�n\n"+
                      "0. Cerrar el programa\n"+
                      "INTRODUZCA SU OPCION:"
                      ,plataforma.getName(),plataforma.getLogged()));
        return Integer.parseInt(lectura.nextLine());
    }
    /**
     * La ejecucion de todo
     */
    public static void index(){
        int prdocele = 0;
        boolean runProgram = true;
        ArrayList<editor> plataformas = new ArrayList<>();
        testGenerator(plataformas);
        
        while(runProgram && prdocele != -1){
            prdocele = choosePlataform(plataformas);
            while(prdocele != -1 && runProgram){
                runProgram = !authentification(plataformas.get(prdocele));
                int opcionoperacion = 1;
                while(opcionoperacion > 0 && opcionoperacion != 12 && prdocele != -1 && runProgram){
                    opcionoperacion = menu(plataformas.get(prdocele));
                    if(opcionoperacion == 1){
                        create(plataformas.get(prdocele));
                        print("Presione enter para continuar. . .");
                        lectura.nextLine();
                    }else if(opcionoperacion == 2){
                        share(plataformas.get(prdocele));
                        print("Presione enter para continuar. . .");
                        lectura.nextLine();
                    }else if(opcionoperacion == 3){
                        add(plataformas.get(prdocele));
                        print("Presione enter para continuar. . .");
                        lectura.nextLine();
                    }else if(opcionoperacion == 4){
                        rollback(plataformas.get(prdocele));
                        print("Presione enter para continuar. . .");
                        lectura.nextLine();
                    }else if(opcionoperacion == 5){
                        revokeAccess(plataformas.get(prdocele));
                        print("Presione enter para continuar. . .");
                        lectura.nextLine();
                    }else if(opcionoperacion == 6){
                        search(plataformas.get(prdocele));
                        print("Presione enter para continuar. . .");
                        lectura.nextLine();
                    }else if(opcionoperacion == 7){
                        delete(plataformas.get(prdocele));
                        print("Presione enter para continuar. . .");
                        lectura.nextLine();
                    }else if(opcionoperacion == 8){
                        replace(plataformas.get(prdocele));
                        print("Presione enter para continuar. . .");
                        lectura.nextLine();
                    }else if(opcionoperacion == 9){
                        applyStyles(plataformas.get(prdocele));
                        print("Presione enter para continuar. . .");
                        lectura.nextLine();
                    }else if(opcionoperacion == 10){
                        comment(plataformas.get(prdocele));
                        print("Presione enter para continuar. . .");
                        lectura.nextLine();
                    }else if(opcionoperacion == 11){
                        print(plataformas.get(prdocele).toStr());
                        print("Presione enter para continuar. . .");
                        lectura.nextLine();
                    }else if(opcionoperacion == 0){
                        runProgram = false;
                    }
                }
            } 
        }
    }
}


