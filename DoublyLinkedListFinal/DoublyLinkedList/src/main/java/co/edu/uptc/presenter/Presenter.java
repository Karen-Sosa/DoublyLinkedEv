package co.edu.uptc.presenter;

import co.edu.uptc.model.DoublyLinked;
import co.edu.uptc.model.DoublyListManager;
import co.edu.uptc.pojo.Person;
import co.edu.uptc.util.ReadFile;
import co.edu.uptc.view.View;

public class Presenter {

    private View view = new View();
    private DoublyListManager manager = new DoublyListManager();

    public void start(){
        ReadFile reader = new ReadFile();
        manager.setLinked(reader.readWrite());
        menu();
    }

    public void menu() {
        int option;
        do {
        view.showMessage("Bienvenido al menú de su lista doblemente enlazada\n" +
                "OPCIONES--->\n" +
                "1. Salir\n" +
                "2. Mostrar lista\n" +
                "3. Añadir al inicio\n" +
                "4. Añadir al final\n" +
                "5. Añadir en el centro\n" +
                "6. Dividir lista\n" +
                "7. Ordenar\n" +
                "8. Adicionar ordenado\n" +
                "9. Dividir la lista según la edad de retiro\n" +
                "10. Eliminar de la lista si no pasa la edad de retiro\n" +
                "Seleccione una opción de las anteriores\n");
        option = view.readInt();

            switch (option) {
                case 1:
                    view.showMessage("Ha decicido salir del sistema");
                    break;
                case 2:
                    view.showMessage("Listas disponibles:");
                    showLists();
                    break;
                case 3:
                    view.showMessage("Ha decicido añadir al inicio de la lista");
                    addFirst();
                    break;
                case 4:
                    view.showMessage("Ha decicido añadir al final de la lista");
                    addLast();
                    break;
                case 5:
                    view.showMessage("Ha decicido añadir en el centro de la lista");
                    addCentered();
                    break;
                case 6:
                    view.showMessage("Su lista será dividida en dos");
                    split();
                    break;
                case 7:
                    view.showMessage("Ha decicido ordenar");
                    sort();
                    break;
                case 8:
                    view.showMessage("Ha decicido adicionar de forma ordenada");
                    addtInOrder();
                    break;
                case 9:
                    view.showMessage("Ha decicido dividir la lista en dos sublistas según la edad de retiro");
                    asignationByRetirement();
                    break;
                case 10:
                    view.showMessage("Ha decicido eliminar de la lista a las personas cuya edad sea inferior a la edad de retiro");
                    deleteByRetirement();
                    break;
                default:
                    view.showMessage("Opción inválida");
            }
        } while (option != 1);

    }

    private void showLists() {
        for (int i = 0; i < manager.getLists().getSize(); i++) {
            view.showMessage("\nLista " + i + ":\n");
            manager.showLinkedList(i);
        }
    }

    private void addFirst() {
        int position = selectList();
        view.showMessage("Ingrese los datos solicitados:");
        Person person = view.createPerson();

        manager.addFirst(position, person);
        view.showMessage("Persona agregada al inicio");
    }

    private void addLast() {
        int position = selectList();
        view.showMessage("Ingrese el dato: ");
        Person person = view.createPerson();

        manager.addLast(position, person);
        view.showMessage("Persona agregada al final");
    }

    private void addCentered() {
        int position = selectList();
        view.showMessage("Ingrese el dato: ");
        Person person = view.createPerson();

        manager.addCentered(position, person);
        view.showMessage("Persona agregada en el centro de la lista");
    }

    public void split() {
        int position = selectList();

        manager.split(position);

        view.showMessage("La lista ha sido dividida");
    }

    public  void sort(){
        int position = selectList();
        int[] attributes = selectedAttributes();
        int direction = view.selectDirection();

        manager.sort(position, attributes, direction);

        view.showMessage("\nLista ordenada:");
        manager.showLinkedList(position);
    }

    public void addtInOrder(){
        int position = selectList();
        Person person = view.createPerson();
        int[] attributes = selectedAttributes();
        int direction = view.selectDirection();

        manager.addInOrder(position, person, attributes, direction);

        view.showMessage("\nLa persona se añadido correctamente");
        manager.showLinkedList(position);
    }

    public void asignationByRetirement(){
        int position = selectList();
        DoublyLinked<Person>[] lists = manager.asignationByRetirement(position);

        view.showMessage("\nLista original:");
        lists[0].showLinkedList();

        view.showMessage("\nLista de personas que superan la edad de retiro:");
        lists[1].showLinkedList();

        view.showMessage("\nLista de personas que no superan la edad de retiro:");
        lists[2].showLinkedList();
    }

    private void deleteByRetirement() {
        int position = selectList();
        DoublyLinked<Person>[] lists = manager.deleteByRetirement(position);

        view.showMessage("\nLista original:");
        lists[0].showLinkedList();

        view.showMessage("\nPersonas eliminadas:");
        lists[2].showLinkedList();

        manager.removeList(2);
    }

    public int[] selectedAttributes(){
        int[] attributes = new int[6];
        int amount = 0;
        int option;

        do {
            option = view.selectAttributes();

            if (option >= 1 && option <= 5) {
                amount++;
                attributes[amount] = option;
            }

        }while(option != 6 && amount < 5);

        attributes[0] = amount;
        return attributes;
    }

    public int selectList(){
        return view.selectList(manager.getLists().getSize());
    }
}
