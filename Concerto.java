import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Concerto extends Evento{
    private LocalTime ora;
    private double prezzo;

    public Concerto(String titolo, LocalDate data, int postiTotali, LocalTime ora, double prezzo) throws IllegalArgumentException{
        super(titolo, data, postiTotali);

        if(prezzo < 0) {
            throw new IllegalArgumentException("Il prezzo non può essere negativo.");
        }
        
        this.ora = ora;
        this.prezzo = prezzo;
    }

    public LocalTime getOra() {
        return ora;
    }

    public void setOra(LocalTime ora) {
        this.ora = ora;
    }

    public double getPrezzo() {
        return prezzo;
    }

    public void setPrezzo(double prezzo) {
        if (prezzo < 0) {
            throw new IllegalArgumentException("Il prezzo non può essere negativo.");
        }
        this.prezzo = prezzo;
    }

    public String dataOraFormattate(){
        if (getData() == null || ora == null) {
            return "Inserire una data o un orario valido";
        }
        DateTimeFormatter formatoTempo = DateTimeFormatter.ofPattern("HH:mm");
        DateTimeFormatter formatoData = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return (getData().format(formatoData) + " - " + ora.format(formatoTempo));
    }

    public String prezzoFormattato(){
        NumberFormat formatoPrezzo = NumberFormat.getCurrencyInstance(Locale.ITALY);
        return formatoPrezzo.format(prezzo);
    }

    @Override
    public String toString() {
        return (dataOraFormattate() + " - " + getTitolo() + " - " + prezzoFormattato() + "€");
    }
    
    
}
