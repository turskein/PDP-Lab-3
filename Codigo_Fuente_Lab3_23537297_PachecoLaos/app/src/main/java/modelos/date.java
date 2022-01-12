package modelos;

public class date {
    private int day;
    private int month;
    private int year;
    
    public date(int day, int month, int year){
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
    public String toStr(){
        return String.format("%d-%d-%d", this.getDay(), this.getMonth(), this.getYear());
    }
}
