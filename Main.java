import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
            //Scanner iniziato e chiuso in automatico alla chiusura del blocco try, non si può aprire un nuovo scanner
            try (Scanner scan = new Scanner(System.in)) {

                String titolo = titoloInput(scan);
                LocalDate data = dataInput(scan);
                int postiTotali = postiTotaliInput(scan);
                LocalTime orario = orarioInput(scan);
                double prezzo = prezzoInput(scan);
                //Evento evento = new Evento(titolo, data, postiTotali);

                
                //prenotaInput(scan, evento);
                //System.out.println("Numero posti prenotati: " + evento.getPostiPrenotati());
                //System.out.println("Numero posti disponibili: " + evento.postiDisponibili());

                //disdetteInput(scan, evento);
                //System.out.println("Numero posti prenotati: " + evento.getPostiPrenotati());
                //System.out.println("Numero posti disponibili: " + evento.postiDisponibili());
                
                //System.out.println(evento.toString());

                Concerto concerto = new Concerto(titolo, data, postiTotali, orario, prezzo);

                prenotaInput(scan, concerto);
                System.out.println("Numero posti prenotati: " + concerto.getPostiPrenotati());
                System.out.println("Numero posti disponibili: " + concerto.postiDisponibili());

                disdetteInput(scan, concerto);
                System.out.println("Numero posti prenotati: " + concerto.getPostiPrenotati());
                System.out.println("Numero posti disponibili: " + concerto.postiDisponibili());

                System.out.println(concerto.toString());

            }
    
        }
        /*
         * Usato la conversione da string a int con Integer.parseInt(scan.nextLine().trim())
         * perchè sennò con scan.nextInt venivano lasciate /n righe vuote
         * di conseguenza dovevo usare invio più volte per far partire il medoto.
         * Stessa cosa per il metodo prezzo.
         */

        //Metodo per la scelta del titolo in input
        private static String titoloInput(Scanner scan){
            System.out.println("Scegli il titolo dell'evento:");
            String titolo = scan.nextLine();
            if(titolo == null || titolo.trim().isEmpty()){
                throw new IllegalArgumentException("Il titolo non può essere vuoto");
            }
            return titolo;
        }

        //Metodo per la scelta della data in input 
        private static LocalDate dataInput(Scanner scan){
            try{
                System.out.println("Inserisci l'anno dell'evento:");
                int anno = Integer.parseInt(scan.nextLine().trim());
                    
                System.out.println("Inserisci il mese dell'evento:");
                int mese = Integer.parseInt(scan.nextLine().trim());
                    
                System.out.println("Inserisci il giorno dell'evento:");
                int giorno = Integer.parseInt(scan.nextLine().trim());
    
                LocalDate data = LocalDate.of(anno, mese, giorno);
                if(data.isBefore(LocalDate.now())){
                    throw new IllegalArgumentException("La data dell'evento non può essere nel passato");
                }
                return data;
            } catch (InputMismatchException e){
                throw new IllegalArgumentException("Inserire un numero valido per la data");
            } catch (DateTimeException | NumberFormatException e) {
                throw new IllegalArgumentException("Data non valida, assicurati di inserire valori corretti");
            }

        }

        //Metodo per la scelta del numero di posti totali in input
        private static int postiTotaliInput(Scanner scan){
            System.out.println("Inserisci il numero di posti totali dell'evento:");
            int postiTotali = Integer.parseInt(scan.nextLine().trim());
            if(postiTotali <= 0){
                throw new IllegalArgumentException("Inserire un numero maggiore di zero");
            }
            return postiTotali;
        }

        //Metodo per la scelta dell'orario in input
        private static LocalTime orarioInput(Scanner scan){
            try{
                System.out.println("Scegli l'orario specificando prima l'ora e poi i minuti");
                System.out.print("Ora (0 - 23): ");
                int ora = Integer.parseInt(scan.nextLine().trim());
                    
                System.out.print("Minuti (0 - 59): ");
                int minuti = Integer.parseInt(scan.nextLine().trim());
    
                LocalTime orario = LocalTime.of(ora, minuti);
                if(orario.isBefore(LocalTime.now())){
                    throw new IllegalArgumentException("Non si può inserire un orario passato");
                }
                return orario;
            } catch (InputMismatchException e){
                throw new IllegalArgumentException("Inserire un numero valido per le ore e/o minuti");
            } catch (DateTimeException e) {
                throw new IllegalArgumentException("Orario non valida, assicurati di inserire valori corretti");
            }
        }

        //Metodo per la scelta del prezzo in input
        private static double prezzoInput(Scanner scan){
            while(true){
                try {
                    System.out.println("Scegli il costo del biglietto (##.##): ");
                    double prezzo = Double.parseDouble(scan.nextLine().trim());
                    
                    if (prezzo < 0) {
                    throw new IllegalArgumentException("Il prezzo non può essere negativo.");
                    }
                    return prezzo;
                } catch (NumberFormatException e) {
                    System.out.println("Errore: inserisci un numero valido.");
                }
            }
            
        }

        //Metodo per la scelta di un numero intero positivo
        private static int sceltaNumero(Scanner scan){
            while (true) {
                try {
                    String input = scan.nextLine().trim(); 
                    int numeroScelto = Integer.parseInt(input);
                    
                    if (numeroScelto < 0) {
                        System.out.println("Errore: inserire un numero positivo.");
                    } else {
                        return numeroScelto;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Errore: inserisci un numero valido.");
                }
            }
        }

        //Metodo per prenotare in input
        private static void prenotaInput(Scanner scan, Evento evento){
            while (true) { 
                try {
                    System.out.println("Vuoi effetturare delle prenotazioni? (inserisci y per si, n per no)");
                    String scelta = scan.nextLine().trim().toLowerCase();
    
        
                    if(!scelta.equals("y") && !scelta.equals("n")){
                        throw new IllegalArgumentException("Valore non valido. Inserire y per si o n per no");
                            
                    }
                    if(scelta.equals("y")){
                        System.out.println("Quante prenotazioni vuoi effettuare: ");
                        int numeroPrenotazioni = sceltaNumero(scan);
    
                        if(numeroPrenotazioni > evento.postiDisponibili()){
                            throw new IllegalArgumentException("Il numero di posti disponibili è inferiore al numero di prenotazioni"
                                                                + ", numero posti disponibili: "
                                                                + evento.postiDisponibili());
                        }
                        if(numeroPrenotazioni <= 0){
                            throw new IllegalArgumentException("Inserire un valore valido maggiore o uguale a zero");
                        }
                        for(int i = 0; i < numeroPrenotazioni; i++){
                            evento.prenota();
                        }
                        break;
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Errore: " + e.getMessage());
                }
            }
            
        }

        //Metodo per disdette in input
        private static void disdetteInput(Scanner scan, Evento evento){
            while (true) { 
                try {
                    System.out.println("Vuoi effetturare delle disdette? (inserisci y per si, n per no)");
                    String scelta = scan.nextLine().trim().toLowerCase();
        
                    if(!scelta.equals("y") && !scelta.equals("n") ){
                        throw new IllegalArgumentException("Valore non valido. Inserire y per si o n per no");
                    }
                    if(scelta.equals("y")){
                        if(evento.getPostiPrenotati() == 0){
                            throw new IllegalArgumentException("Non sono presenti prenotazioni");
                        }
                        System.out.println("Quante disdette vuoi effettuare: ");
                        int numeroDisdette = sceltaNumero(scan);
                        
                        if(numeroDisdette > evento.getPostiPrenotati()){
                            throw new IllegalArgumentException("Il numero delle disdette è maggiore del numero delle prenotazioni"
                                    + ", numero posti prenotati: " + evento.getPostiPrenotati());
                        }
                        if(numeroDisdette <= 0){
                            throw new IllegalArgumentException("Inserire un valore valido maggiore o uguale a zero");
                        }
                        for(int i = 0; i < numeroDisdette; i++){
                            evento.disdici();
                        }
                        break;
                    }
                } catch (IllegalArgumentException e) {
                    System.out.println("Errore: " + e.getMessage());
                }
            }
        }
}