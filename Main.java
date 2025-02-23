import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        Evento concerto;

        System.out.println("Quale evento vuoi proporre: ");
        String nomeEvento = scan.nextLine();

        System.out.println("Scegli l'anno: ");
        int anno = scan.nextInt();

        System.out.println("Scegli il mese: ");
        int mese = scan.nextInt();

        System.out.println("Scegli il giorno: ");
        int giorno = scan.nextInt();

        System.out.println("Scegli l'orario specificando prima l'ora e poi i minuti");
        System.out.print("Ora (0 - 23): ");
        int ora = scan.nextInt();
        System.out.print("Minuti (0 - 59): ");
        int minuti = scan.nextInt();

        System.out.println("Scegli la capienza massima: ");
        int postiTotali = scan.nextInt();

        System.out.println("Scegli il costo del biglietto (##,##): ");
        double costo = scan.nextDouble();

        LocalTime orarioConcerto = LocalTime.of(ora, minuti);
        LocalDate dataConcerto = LocalDate.of(anno, mese, giorno);

        scan.nextLine();
        
        try {
            concerto = new Concerto(nomeEvento, dataConcerto, postiTotali, orarioConcerto, costo);

            System.out.print("Quante prenotazioni vuoi fare: ");
            int numeroPrenotazioni = scan.nextInt();
            scan.nextLine();
            if(numeroPrenotazioni > (concerto.getPostiTotali() - concerto.getPostiPrenotati())){
                throw new IllegalArgumentException("Non ci sono abbastanza posti disponibili");
            }
            for (int i = 0; i < numeroPrenotazioni; i++) {
                concerto.prenotaPosto();
            }

            System.out.print("Quante prenotazioni vuoi disdire: ");
            int numeroDisdette = scan.nextInt();
            scan.nextLine();
            if(numeroDisdette > concerto.getPostiPrenotati()){
                throw new IllegalArgumentException("Le disdette superano le prenotazioni");
            }
            for(int i = 0; i < numeroDisdette; i++){
                concerto.disdiciPosto();
            }

            System.out.println(concerto.toString());

        } catch (IllegalArgumentException | IllegalStateException e) {
            System.out.println("Errore: " + e.getMessage());
        }
    }
}