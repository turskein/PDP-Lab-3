package Codigo_Fuente_Lab3_23537297_PachecoLaos;

public class date {
    private int day;
    private int month;
    private int year;
    
    date(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }
    
    int getDay(){
        return this.day;
    }
    int getMonth(){
        return this.month;
    }
    int getYear(){
        return this.year;
    }
    String toStr(){
        return String.format("%d-%d-%d", this.getDay(), this.getMonth(), this.getYear());
    }
}
