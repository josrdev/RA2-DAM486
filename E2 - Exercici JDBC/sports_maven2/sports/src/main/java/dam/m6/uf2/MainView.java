package dam.m6.uf2;

import java.util.List;
import java.util.Scanner;

public class MainView {
    private final Scanner scanner = new Scanner(System.in);

    public int mainMenu() {
        System.out.println("\n[1] Afegir esport");
        System.out.println("[2] Afegir atleta");
        System.out.println("[3] Llistar atletes per esport");
        System.out.println("[0] Sortir");
        System.out.print("Opcio: ");

        while (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.print("Opcio: ");
        }
        int option = scanner.nextInt();
        scanner.nextLine();
        return option;
    }

    public Esport esportForm() {
        System.out.print("Nom de l'esport: ");
        String name = scanner.nextLine().trim();
        return name.isEmpty() ? null : new Esport(name);
    }

    public Atleta atletaForm(List<Esport> esports) {
        if (esports.isEmpty()) {
            System.out.println("Primer cal afegir un esport.");
            return null;
        }

        llistaEsports(esports);
        int esportId = demanaEsport(esports);
        if (esportId < 0) {
            return null;
        }

        System.out.print("Nom de l'atleta: ");
        String name = scanner.nextLine().trim();
        return name.isEmpty() ? null : new Atleta(name, esportId);
    }

    public int demanaEsport(List<Esport> esports) {
        if (esports.isEmpty()) {
            return -1;
        }

        System.out.print("Selecciona l'ID de l'esport: ");
        while (!scanner.hasNextInt()) {
            scanner.nextLine();
            System.out.print("Selecciona l'ID de l'esport: ");
        }
        int selectedId = scanner.nextInt();
        scanner.nextLine();

        for (Esport esport : esports) {
            if (esport.getId() == selectedId) {
                return selectedId;
            }
        }

        System.out.println("ID d'esport no valid.");
        return -1;
    }

    public void llistaEsports(List<Esport> esports) {
        System.out.println("ESPORTS");
        for (Esport esport : esports) {
            System.out.println(esport.getId() + " - " + esport.getName());
        }
    }

    public void llistaAtletes(List<Atleta> atletes) {
        System.out.println("ATLETES");
        if (atletes.isEmpty()) {
            System.out.println("No hi ha atletes per aquest esport.");
            return;
        }
        int position = 1;
        for (Atleta atleta : atletes) {
            System.out.println(position + ". " + atleta.getName());
            position++;
        }
    }
}
