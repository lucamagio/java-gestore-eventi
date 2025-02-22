import java.time.LocalDate;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Evento evento;
        Scanner scan = new Scanner(System.in);

        System.out.println("Quale evento vuoi proporre: ");
        String nomeEvento = scan.nextLine();

        System.out.println("Scegli l'anno: ");
        int anno = scan.nextInt();

        System.out.println("Scegli il mese: ");
        int mese = scan.nextInt();

        System.out.println("Scegli il giorno: ");
        int giorno = scan.nextInt();

        System.out.println("Scegli la capienza massima: ");
        int postiTotali = scan.nextInt();

        scan.nextLine();
        
        try {

            evento = new Evento(nomeEvento, LocalDate.of(anno, mese, giorno), postiTotali);

            System.out.print("Quante prenotazioni vuoi fare: ");
            int numeroPrenotazioni = scan.nextInt();
            scan.nextLine();
            if(numeroPrenotazioni > (evento.getPostiTotali() - evento.getPostiPrenotati())){
                throw new IllegalArgumentException("Non ci sono abbastanza posti disponibili");
            }
            for (int i = 0; i < numeroPrenotazioni; i++) {
                evento.prenotaPosto();
            }

            System.out.print("Quante prenotazioni vuoi disdire: ");
            int numeroDisdette = scan.nextInt();
            scan.nextLine();
            if(numeroDisdette > evento.getPostiPrenotati()){
                throw new IllegalArgumentException("Le disdette superano le prenotazioni");
            }
            for(int i = 0; i < numeroDisdette; i++){
                evento.disdiciPosto();
            }

            System.out.println(evento.toString());

        } catch (IllegalAccessException | IllegalArgumentException | IllegalStateException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }
}