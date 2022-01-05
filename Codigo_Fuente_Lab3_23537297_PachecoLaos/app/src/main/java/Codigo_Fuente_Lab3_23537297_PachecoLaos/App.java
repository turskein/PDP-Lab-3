package Codigo_Fuente_Lab3_23537297_PachecoLaos;

import java.util.ArrayList;
import java.util.Scanner;

public class App {
    
    public static void print(String things){
        System.out.println(things);
    }
    public static void jumpline(int cantidad){
        for(int i = 0; i < cantidad; i++)
        print("\n");
    }
    
    public static int choosePlataform(Scanner lectura, ArrayList<prdoc> plataformas){
        int i;
        print("Ingrese la plataforma que desea utilizar(ingrese el numero y luego presione enter): ");
        for(i = 0; i < plataformas.size();i++){
            print(String.format("%d. %s\n",i+1,plataformas.get(i).getName()));
        }
        print(String.format("%d. Crear plataforma\n",i+1));
        String opcion = lectura.nextLine();
        if(opcion.equals(String.format("%d",i+1))){
            print("Ingrese el nombre de la nueva plataforma: ");
            String nombre = lectura.next();
            print("Ingrese la fecha separado por guiones de la forma dia-mes-agnio: ");
            String[] fechacreacion = lectura.next().split("-");
            int dia = Integer.parseInt(fechacreacion[0]);
            int mes = Integer.parseInt(fechacreacion[1]);
            int agnio = Integer.parseInt(fechacreacion[2]);
            date prdocmaked = new date(dia,mes,agnio);
            prdoc newprdoc = new prdoc(nombre,prdocmaked);
            plataformas.add(newprdoc);
            return plataformas.size()-1;
        }else{
            return Integer.parseInt(opcion);
        }
    }
    public static void authentification(Scanner lectura, prdoc plataforma){
        boolean logueado = true;
        String opcionlogueo = "0";
        while(logueado){
            print("Escoja su opcion:\n1.Registrarse\n2.Loguearse");
            opcionlogueo = lectura.next();
            if(opcionlogueo.equals("1")){
                print("Ingrese su nombre de usuario:");
                String username = lectura.next();
                while(plataforma.existUsername(username)){
                    print("Tal nombre de usuario ya existe\n Ingrese otro nombre de usuario:");
                    username = lectura.next();
                }
                print("Ingrese su contrasenia:");
                String password = lectura.next();
                print("Ingrese la fecha separado por guiones de la forma dia-mes-agnio: ");
                String[] fechacreacion = lectura.next().split("-");
                int dia = Integer.parseInt(fechacreacion[0]);
                int mes = Integer.parseInt(fechacreacion[1]);
                int agnio = Integer.parseInt(fechacreacion[2]);
                date fechausuario = new date(dia,mes,agnio);
                plataforma.addUser(username, password, fechausuario);
                plataforma.log(username);
                logueado = false;
            }else if(opcionlogueo.equals("2")){
                print("Ingrese su nombre de usuario:");
                String username = lectura.next();
                print("Ingrese su contrasenia:");
                String password = lectura.next();
                if(plataforma.authenticateUser(username,password)){
                    plataforma.log(username);
                    logueado = false;
                }else{
                    print("No ha logrado loguearse correctamente\n");
                }
            }
        } 
    }
    public static void create(Scanner lectura, prdoc plataforma){
        print("Ingrese el nombre de su nuevo documento: ");
        String titulo = lectura.next();
        print("Ingrese la fecha de creacion: ");
        String[] fecha = lectura.next().split("-");
        print("Ingrese el contenido de su documento: ");
        String contenido = lectura.next();
        plataforma.addDoc(titulo, 
                new date(Integer.parseInt(fecha[0]),
                        Integer.parseInt(fecha[1]),
                        Integer.parseInt(fecha[2])), 
                contenido);
        
    }
    public static void share(Scanner lectura, prdoc plataforma){
        print("Ingrese el id del documento que desea compartir: ");
        int id = Integer.parseInt(lectura.next());
        print("Ingrese los nombres de los usuario con los que desea compartir el documento separado por comas:");
        String[] names = lectura.next().split(",");
        print("Ingrese la letra del permiso que quiere compartir, siendo W-Escritura, R-Lectura, C-Comentar");
        String permiso = lectura.next();
        if(plataforma.shareDoc(names, permiso, id)){
            print("El documento a sido compartido exitosamente");
            return;
        }
        print("No se es dueño del documento que se quiere compartir.\n. . .");
    }
    public static void add(Scanner lectura, prdoc plataforma){
        print("Ingrese el id del documento en el que desea agregar contenido");
        int id = Integer.parseInt(lectura.next());
        print("Ingrese el contenido que desea ");
        String contenido = lectura.next();
        if(plataforma.addContentToDoc(id, contenido)){
            print("Se agrego contenido al documento como se esperaba");
            return;
        }
        print("No tienes permisos para agregar contenido a tal documento\n. . .");
    }
    public static int menu(Scanner lectura, prdoc plataforma){
        print(String.format("### %s ###\n## Usuario: %s ##\n"+
                      "Escoja su opcion:\n"+
                      "1. Crear nuevo documento\n"+
                      "2. Compartir documento\n" +
                      "3. Agregar contenido a un documento\n"+
                      "4. Restaurar version de un documento\n"+
                      "5. Revocar acceso a un documento\n"+
                      "6. Buscar en los documentos\n"+
                      "7. Visualizar documentos\n"+
                      "8. Cerrar sesión\n"+
                      "0. Cerrar el programa\n"+
                      "INTRODUZCA SU OPCION:\n"
                      ,plataforma.getName(),plataforma.getLogged()));
        return Integer.parseInt(lectura.next());
    }
    public static void main(String[] args) {
        
        Scanner lectura = new Scanner(System.in).useDelimiter("\n");
        int prdocele = 0;
        boolean eleccionplataforma = true;
        while(eleccionplataforma){
            //generacion de array de plataformas
            ArrayList<prdoc> plataformas = new ArrayList<>();

            prdocele = choosePlataform(lectura, plataformas);
            
            authentification(lectura, plataformas.get(prdocele));
            /*
            Bloque de funciones
            */
            int opcionoperacion = 1;
            while(opcionoperacion > 0){
                opcionoperacion = menu(lectura, plataformas.get(prdocele));
                if(opcionoperacion == 1){
                    create(lectura, plataformas.get(prdocele));
                }else if(opcionoperacion == 2){
                    share(lectura, plataformas.get(prdocele));
                }else if(opcionoperacion == 7){
                    print(plataformas.get(prdocele).toStr());
                }
                 
            }
            eleccionplataforma = false;
        }
        
    }
}
