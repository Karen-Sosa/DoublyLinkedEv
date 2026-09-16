package co.edu.uptc.model;

import co.edu.uptc.pojo.Node;
import co.edu.uptc.pojo.Person;
import lombok.Getter;

import java.time.LocalDate;
import java.time.Period;

@Getter
public class DoublyListManager {
    private DoublyLinked<Person> linked = new DoublyLinked<>();
    private DoublyLinked<DoublyLinked<Person>> lists = new DoublyLinked<>();

    public void addFirst(int position, Person person) {
        getList(position).addFirst(person);
    }

    public void addLast(int position, Person person) {
        getList(position).addLast(person);
    }

    public void addCentered(int position, Person person) {
        getList(position).addCentered(person);
    }

    public void showLinkedList(int position) {
        getList(position).showLinkedList();
    }

    public void split(int position) {

        DoublyLinked<Person> selectedList = getList(position);

        DoublyLinked<Person> original = selectedList.copy();

        DoublyLinked<Person>[] result = selectedList.split();

        lists.getNode(position).setData(original);

        lists.addLast(result[0]);
        lists.addLast(result[1]);
    }

    public DoublyLinked<Person>[] asignationByRetirement(int position) {
        DoublyLinked<Person> selectedList = getList(position);
        DoublyLinked<Person> listPass1 = new DoublyLinked<>();
        DoublyLinked<Person> listNotPass2 = new DoublyLinked<>();

        int retirementAge = 25;
        Node<Person> current = selectedList.getHead();

        while (current != null) {
            Person person = current.getData();
            int age = calculateAge(person.getBirth());

            if (age > retirementAge) {
                listPass1.addLast(person);
            } else {
                listNotPass2.addLast(person);
            }
            current = current.getNext();
        }

        return new DoublyLinked[]{selectedList, listPass1, listNotPass2};
    }

    public DoublyLinked<Person>[] deleteByRetirement(int position) {
        DoublyLinked<Person> selectedList = getList(position);
        DoublyLinked<Person> listPass1 = new DoublyLinked<>();
        DoublyLinked<Person> listNotPass2 = new DoublyLinked<>();

        int retirementAge = 25;
        Node<Person> current = selectedList.getHead();

        while (current != null) {
            Person person = current.getData();
            int age = calculateAge(person.getBirth());

            if (age > retirementAge) {
                listPass1.addLast(person);
            } else {
                listNotPass2.addLast(person);
            }
            current = current.getNext();
        }

        lists.addLast(listPass1);
        lists.addLast(listNotPass2);

        return new DoublyLinked[]{selectedList, listPass1, listNotPass2};
    }

    public void sort(int position, int[] attributes, int direction) {
        getList(position).sort((p1, p2) -> {
            int result = comparatePerson(p1, p2, attributes);
            if (direction == 2) {
                result = result * -1;
            }
            return result;
        });
    }

    public void addInOrder(int position, Person person, int[] attributes, int direction) {
        getList(position).addInOrder(person, (p1, p2) -> {
            int result = comparatePerson(p1, p2, attributes);
            if (direction == 2) {
                result = result * -1;
            }
            return result;
        });
    }

    public int comparatePerson(Person p1, Person p2, int[] attributes) {
        int amount = attributes[0];

        for (int i = 1; i <= amount; i++) {
            int attribute = attributes[i];

            switch (attribute) {
                case 1:
                    int comparateName =
                            p1.getName().compareToIgnoreCase(p2.getName());
                    if (comparateName != 0) {
                        return comparateName;
                    }
                    break;

                case 2:
                    int comparateLastName =
                            p1.getLastName().compareToIgnoreCase(p2.getLastName());
                    if (comparateLastName != 0) {
                        return comparateLastName;
                    }
                    break;

                case 3:
                    int age1 = calculateAge(p1.getBirth());
                    int age2 = calculateAge(p2.getBirth());

                    int comparateAge = Integer.compare(age1, age2);

                    if (comparateAge != 0) {
                        return comparateAge;
                    }
                    break;

                case 4:
                    int comparateGender =
                            p1.getGender().compareToIgnoreCase(p2.getGender());
                    if (comparateGender != 0) {
                        return comparateGender;
                    }
                    break;

                case 5:
                    int comparateState =
                            p1.getState().compareToIgnoreCase(p2.getState());
                    if (comparateState != 0) {
                        return comparateState;
                    }
                    break;
            }
        }
        return 0;
    }

    public int calculateAge(LocalDate birth) {
        return Period.between(birth, LocalDate.now()).getYears();
    }

    public void removeList(int position){
        if (position < 0 || position >= lists.getSize()) {
            throw new IndexOutOfBoundsException("Lista fuera del rango");
        }
        lists.removeAt(position);
    }

    public DoublyLinked<Person> getList(int position) {
        return lists.getNode(position).getData();
    }

    public void setLinked(DoublyLinked<Person> linked) {
        this.linked = linked;

        lists = new DoublyLinked<>();
        lists.addLast(linked);
    }
}
