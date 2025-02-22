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
        
        try {

            evento = new Evento(nomeEvento, LocalDate.of(anno, mese, giorno), postiTotali);
            evento.dettagli();

            System.out.println("Scegli quanti posti prenotare: ");
            int numeroPrenotazioni = scan.nextInt();
            evento.prenotaPosto(numeroPrenotazioni);
            evento.dettagli();

            System.out.println("Scegli quanti posti prenotare: ");
            numeroPrenotazioni = scan.nextInt();
            evento.prenotaPosto(numeroPrenotazioni);
            evento.dettagli();

            System.out.println("Scegli quanti posti disdire: ");
            int numeroDisdette = scan.nextInt();
            evento.disdiciPosti(numeroDisdette);
            evento.dettagli();

            System.out.println(evento.toString());

        } catch (IllegalAccessException | IllegalArgumentException | IllegalStateException e) {
            System.out.println("Errore: " + e.getMessage());
        }


    }
}
