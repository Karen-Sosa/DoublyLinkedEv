package co.edu.uptc.view;
import co.edu.uptc.pojo.Person;

import java.time.LocalDate;
import java.util.Scanner;

public class View {
    private Scanner sc = new Scanner(System.in);

    public void showMessage(String message){
        System.out.println(message);
    }

    public int readInt(){
        return sc.nextInt();
    }

    public Person createPerson(){
        System.out.print("Nombre: ");
        String name = sc.next();

        System.out.print("Apellido: ");
        String lastName = sc.next();

        System.out.print("Fecha de nacimiento (yyyy-MM-dd): ");
        LocalDate birth = LocalDate.parse(sc.next());

        System.out.print("Género: ");
        String gender = sc.next();

        System.out.print("Estado civil: ");
        String state = sc.next();

        return new Person(
                name,
                lastName,
                birth,
                gender,
                state
        );
    }

    public int selectDirection(){
        System.out.println("\n¿En qué dirección desea ordenar?");
        System.out.println("1. Ascendente");
        System.out.println("2. Descendente");

        System.out.print("Seleccione: ");

        return sc.nextInt();
    }

    public int selectAttributes(){
        System.out.println("¿Por qué atributo ordenarás?\n" +
                "1. Nombre\n" +
                "2. Apellido\n" +
                "3. Edad\n" +
                "4. Género\n" +
                "5. Estado civil\n" +
                "6. Dejar de seleccionar\n");
        System.out.println("Ingresa un solo número en orden de prioridad");
        return sc.nextInt();
    }

    public int selectList(int amount) {

        System.out.println("\nListas disponibles:");

        for (int i = 0; i < amount; i++) {
            System.out.println(i + ". Lista " + i);
        }

        System.out.print("Seleccione la lista: ");

        return sc.nextInt();
    }
}
