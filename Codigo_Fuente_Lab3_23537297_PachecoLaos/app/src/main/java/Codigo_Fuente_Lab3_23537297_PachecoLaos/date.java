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
    
    int getday(){
        return this.day;
    }
    int getmonth(){
        return this.month;
    }
    int getyear(){
        return this.year;
    }
    String toStr(){
        return String.format("%d-%d-%d", this.getday(), this.getmonth(), this.getyear());
    }
}
