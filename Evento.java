
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Evento{
    private String titolo;
    private LocalDate data;
    private int postiTotali;
    private int postiPrenotati;

    public Evento(String titolo, LocalDate data, int postiTotali) throws IllegalAccessException, IllegalArgumentException{
        if(postiTotali <= 0){
            throw new IllegalAccessException("Il numero di posti prenotati dev'essere maggiore di zero");
        }
        if (data.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("Non si può inserire una data riferita al passato");
        }
        this.titolo = titolo;
        this.data = data;
        this.postiTotali = postiTotali;
        this.postiPrenotati = 0;
    }

    public String getTitolo() {
        return titolo;
    }

    public LocalDate getData() {
        return data;
    }

    public int getPostiTotali() {
        return postiTotali;
    }

    public int getPostiPrenotati() {
        return postiPrenotati;
    }

    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }

    public void setData(LocalDate data) {
        this.data = data;
    }

    /*Metodo per la Prenotazioni */
    public int prenotaPosto(int numeroPrenotazioni) throws IllegalAccessException, IllegalStateException{
        if(postiPrenotati == postiTotali){
            throw new IllegalAccessException("Si è raggiunta la capienza massima, non si possono fare altre prenotazioni");
        } else if (data.isBefore(LocalDate.now())) {
            throw new IllegalStateException("Non è possibile prenotare per un evento già passato");
        }

        postiPrenotati = postiPrenotati + numeroPrenotazioni;

        if(numeroPrenotazioni <= 0){
            throw new IllegalAccessException("Il numero dei posti prenotati dev'essere maggiore di zero");
        } else if(postiPrenotati > postiTotali){
            throw new IllegalAccessException("Il numero dei posti prenotati è maggiore del numero di posti totali");
        }

       return postiPrenotati;
    }

    /*Metodo per la Disdetta */
    public int disdiciPosti(int numeroDisdette) throws IllegalAccessException, IllegalStateException{
        if(postiPrenotati == 0){
            throw new IllegalAccessException("Non si possono fare disdette perchè non ci sono prenotazioni");
        } else if (data.isBefore(LocalDate.now())) {
            throw new IllegalStateException("Non è possibile disdire per un evento già passato");
        }

        postiPrenotati = postiPrenotati - numeroDisdette;
        
        if(numeroDisdette <= 0){
            throw new IllegalAccessException("Il numeroDisdette dev'essere maggiore di zero");
        } else if(postiPrenotati < 0){
            throw new IllegalAccessException("Il numeroDisdette è maggiore dei postiPrenotati");
        }

        return postiPrenotati;
    }

    public void dettagli(){
        System.out.println("Posti prenotati: " + postiPrenotati + ", Posti disponibili: " + (postiTotali - postiPrenotati));
    }

    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formato) + " - " + titolo;
    }

    

}

/*
 * Step 3
    Creare una classe Concerto che estende Evento, che ha anche gli attributi :
    ora (LocalTime)
    prezzo
    Aggiungere questi attributi nel costruttore e implementarne getter e setter.
    Aggiungere i metodi per restituire data e ora formattata e prezzo formattato (##,##€) 
    Fare l’ override del metodo toString() in modo che venga restituita una stringa del tipo: 
    data e ora formattata - titolo - prezzo formattato
 */