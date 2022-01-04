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
    
    public static void main(String[] args) {
        
        Scanner lectura = new Scanner(System.in);
        int prdocele = 0;
        boolean eleccionplataforma = true;
        while(eleccionplataforma){
            //generacion de array de plataformas
            
            /*
            Bloque de eleccion de plataforma
            */
            ArrayList<prdoc> plataformas = new ArrayList<>();
            String plataformasStr = "";
            int i;
            print("Ingrese la plataforma que desea utilizar(ingrese el numero y luego presione enter): ");
            for(i = 0; i < plataformas.size();i++){
                print(String.format("%d. %s\n",i+1,plataformas.get(i).getName()));
            }
            print(String.format("%d. Crear plataforma\n",i+1));
            String opcion = lectura.next();
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
                prdocele = plataformas.size()-1;
            }else{
                prdocele = Integer.parseInt(opcion);
            }
            /*
            Bloque de logueo de usuario
            */
            boolean logueado = true;
            String opcionlogueo = "0";
            while(logueado){
                print("Escoja su opcion:\n1.Registrarse\n2.Loguearse");
                opcionlogueo = lectura.next();
                if(opcionlogueo.equals("1")){
                    print("Ingrese su nombre de usuario:");
                    String username = lectura.next();
                    while(plataformas.get(prdocele).existUsername(username)){
                        print("Tal nombre de usuario ya existe\n Ingrese otro nombre de usuario:");
                        username = lectura.next();
                    }
                    print("Ingrese su nombre de contrasenia:");
                    String password = lectura.next();
                    print("Ingrese la fecha separado por guiones de la forma dia-mes-agnio: ");
                    String[] fechacreacion = lectura.next().split("-");
                    int dia = Integer.parseInt(fechacreacion[0]);
                    int mes = Integer.parseInt(fechacreacion[1]);
                    int agnio = Integer.parseInt(fechacreacion[2]);
                    date fechausuario = new date(dia,mes,agnio);
                    plataformas.get(prdocele).addUser(username, password, fechausuario);
                    plataformas.get(prdocele).log(username);
                    logueado = false;
                }else if(opcionlogueo.equals("2")){
                    print("Ingrese su nombre de usuario:");
                    String username = lectura.next();
                    print("Ingrese su nombre de contraseña:");
                    String password = lectura.next();
                    if(plataformas.get(prdocele).authenticateUser(username,password)){
                        plataformas.get(prdocele).log(username);
                        logueado = false;
                    }else{
                        print("No ha logrado loguearse correctamente\n");
                    }
                }
            }
            /*
            Bloque de funciones
            */
            int opcionoperacion = 1;
            while(opcionoperacion >0){
                print(String.format("### %s ###\n## Usuario: %s ##\n"+
                      "Escoja su opción:\n"+
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
                      ,plataformas.get(prdocele).getName(),plataformas.get(prdocele).getLogged()));
                
                opcionoperacion = 0;
            }
            eleccionplataforma = false;
        }
        
    }
}
