
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Evento{
    private String titolo;
    private LocalDate data;
    private final int postiTotali;
    private int postiPrenotati;

    public Evento(String titolo, LocalDate data, int postiTotali) throws IllegalArgumentException, IllegalStateException{
        if (titolo == null || titolo.trim().isEmpty()) {
            throw new IllegalArgumentException("Il titolo non può essere vuoto.");
        }
        if(postiTotali <= 0){
            throw new IllegalArgumentException("Il numero di posti totali dev'essere maggiore di zero");
        }
        if (data == null || data.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("Devi inserire una data riferita ad oggi o ad una data futura");
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
        if (titolo == null || titolo.trim().isEmpty()) {
            throw new IllegalArgumentException("Il titolo non può essere vuoto.");
        }
        this.titolo = titolo;
    }

    public void setData(LocalDate data) throws IllegalArgumentException{
        if (data == null) {
            throw new IllegalArgumentException("La data non può essere nulla.");
        }
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalArgumentException("Non puoi impostare una data nel passato.");
        }
        this.data = data;
    }

    /*Metodo per la Prenotazioni */
    public void prenotaPosto() throws IllegalStateException{
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalStateException("Non è possibile prenotare per un evento già passato");
        }
        if (postiPrenotati >= postiTotali) {
            throw new IllegalStateException("Non ci sono più posti disponibili per la prenotazione");
        }

        postiPrenotati++;
    }

    /*Metodo per la Disdetta */
    public void disdiciPosto() throws IllegalStateException{
        if (data.isBefore(LocalDate.now())) {
            throw new IllegalStateException("Non è possibile disdire per un evento già passato");
        }
        if (postiPrenotati <= 0) {
            throw new IllegalStateException("Non ci sono posti prenotati da disdire");
        }

        postiPrenotati--;
    }

    public String dettagli(){
        return "Posti prenotati: " + postiPrenotati + ", Posti disponibili: " + (postiTotali - postiPrenotati);
    }

    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formato) + " - " + titolo;
    }
}