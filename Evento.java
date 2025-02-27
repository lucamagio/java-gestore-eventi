
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Evento{
    private String titolo;
    private LocalDate data;
    private int postiTotali;
    private int postiPrenotati;

    public Evento(String titolo, LocalDate data, int postiTotali) throws IllegalArgumentException{
        if(data == null || data.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("La data non può essere nulla o passata");
        }
        if(titolo == null || titolo.trim().isEmpty()){
            throw new IllegalArgumentException("Il titolo non può essere vuoto");
        }
        if(postiTotali <= 0){
            throw new IllegalArgumentException("Il numero di posti totali non può essere uguale o minore di zero");
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
    public void setTitolo(String titolo) throws IllegalArgumentException{
        if(titolo == null || titolo.trim().isEmpty()){
            throw new IllegalArgumentException("Il titolo non può essere vuoto");
        }
        this.titolo = titolo;
    }
    public void setData(LocalDate data) throws IllegalArgumentException{
        if(data == null || data.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("La data non può essere nulla o passata");
        }
        this.data = data;
    }
    public int postiDisponibili(){
        return postiTotali - postiPrenotati;
    }

    public int prenota() throws IllegalArgumentException{
        if(data == null || data.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("La data non può essere nulla o passata");
        }
        if(postiDisponibili() == 0){
            throw new IllegalArgumentException("Non ci sono più posti disponibili");
        }
        return postiPrenotati++;
    }

    public int disdici() throws IllegalArgumentException{
        if(data == null || data.isBefore(LocalDate.now())){
            throw new IllegalArgumentException("La data non può essere nulla o passata");
        }
        if(postiPrenotati == 0){
            throw new IllegalArgumentException("Non ci sono posti prenotati");
        }
        return postiPrenotati--;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return data.format(formatoData) + " - " + titolo;
    }
}