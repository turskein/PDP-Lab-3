package Codigo_Fuente_Lab3_23537297_PachecoLaos;

public class App {
    public static void print(String things){
        System.out.println(things);
    }
    public static void jumpline(int cantidad){
        for(int i = 0; i < cantidad; i++)
        print("\n");
    }
    
    public static void main(String[] args) {
        date fecha = new date(1,2,3202);
        print(fecha.toStr());
        jumpline(1);
        print(fecha.toStr());
    }
}
