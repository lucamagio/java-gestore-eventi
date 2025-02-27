import java.util.ArrayList;
import java.util.List;

public class ProgrammaEventi {
    private String titolo;
    private List<Evento> eventi;

    public ProgrammaEventi(String titolo) {
        this.titolo = titolo;
        this.eventi = new ArrayList<>();
    }
    

    public String getTitolo() {
        return titolo;
    }

    public List<Evento> getEventi() {
        return eventi;
    }


    public void setTitolo(String titolo) {
        this.titolo = titolo;
    }


    public void aggiungiEvento(Evento evento){
        eventi.add(evento);
    }

    public int numeroEventi(){
        return eventi.size();
    }

    public void eliminaLista(){
        eventi.clear();
    }

}

/*
 * 
    un metodo che restituisce una lista con tutti gli eventi presenti in una certa data
    un metodo che restituisce una stringa che mostra il titolo del programma 
    e tutti gli eventi ordinati per data nella forma: 
        data1 - titolo1
        data2 - titolo2 
        …

 */
