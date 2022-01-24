package modelos;

/**
 * Clase que da formato al TDA fecha guardando dia, mes y año
 * @author Alex Pacheco Laos
 */
public class date {
    private int day;
    private int month;
    private int year;
    
    /**
     * Constructor de la clase fecha
     * @param day dia de la fecha
     * @param month mes de la fecha
     * @param year anio de la fecha
     */
    public date(int day, int month, int year){
        this.day = day;
        this.month = month;
        this.year = year;
    }
    
    /**
     * getter de dia de la fecha
     * @return retorna el dia de la fecha
     */
    int getDay(){
        return this.day;
    }
    /**
     * getter de mes de la fecha
     * @return retorna el mes de la fecha
     */
    int getMonth(){
        return this.month;
    }
    /**
     * getter del anio de la fecha
     * @return retorna el anio de la fecha
     */
    int getYear(){
        return this.year;
    }
    /**
     * Transforma la fecha en String
     * @return retorna la fecha como string
     */
    public String toStr(){
        return String.format("%d-%d-%d", this.getDay(), this.getMonth(), this.getYear());
    }
}
