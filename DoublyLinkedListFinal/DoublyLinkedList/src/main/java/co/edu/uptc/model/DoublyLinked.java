package co.edu.uptc.model;

import co.edu.uptc.pojo.Node;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.Comparator;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class DoublyLinked<T> {
    private Node<T> head;
    private Node<T> tail;
    private int size;

    public void addFirst(T element){
        Node<T> newNode = new Node<>(element);

        if(head == null){
            head = newNode;
            tail = newNode;
        }else{
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
        }
        size++;
    }

    public void addLast(T element){
        Node<T> newNode = new Node<>(element);

        if (tail == null) {
            tail = newNode;
            head = newNode;
        }else{
            tail.setNext(newNode);
            newNode.setPrevious(tail);
            tail = newNode;
        }
        size++;
    }

    public void addCentered(T element){
        Node<T> newNode =  new Node<>(element);

        if(head == null){
            head = newNode;
            tail = newNode;
        }else {
            Node<T> current = head;
            int center = size / 2;

            for (int i = 0; i < center; i++) {
                current = current.getNext();
            }

            newNode.setNext(current);
            newNode.setPrevious(current.getPrevious());

            if (current.getPrevious() != null) {
                current.getPrevious().setNext(newNode);
            } else {
                head = newNode;
            }
            current.setPrevious(newNode);
        }
            size++;
    }

    public DoublyLinked<T>[] split(){

        DoublyLinked<T> list2 = new DoublyLinked<>();

        if (head == null || size<=1) {
            return new DoublyLinked[]{this, new DoublyLinked<T>()};
        }else{
            Node<T> current = head;
            int center = (size + 1)/2;

            for (int i = 0; i < center-1 ; i++) {
                current = current.getNext();
            }

            Node<T> headList2 = current.getNext();
            //punto de corte
            current.setNext(null);
            headList2.setPrevious(null);

            //acomodamos la lista 2

            list2.head = headList2;
            list2.tail = tail;
            list2.size = size-center;
            //acomodamos la lista 1
            this.tail = current;
            this.size = center;

        }
        return new DoublyLinked[]{this, list2};
    }

    public void sort(Comparator<T> comparator){
        Node<T> current = head;

        while(current != null){
            Node<T> nextNode = current.getNext();

            while(nextNode != null){
                if (comparator.compare(current.getData(),nextNode.getData())>0) {
                    T aux = current.getData();
                    current.setData(nextNode.getData());
                    nextNode.setData(aux);
                }
                nextNode = nextNode.getNext();
            }
            current = current.getNext();
        }
    }

    public void addInOrder(T data, Comparator<T> comparator){
        Node<T> newNode = new Node<>(data);

        if (head == null) {
            head = newNode;
            tail = newNode;
            size++;
            return;
        }

        if (comparator.compare(data, head.getData()) < 0) {
            newNode.setNext(head);
            head.setPrevious(newNode);
            head = newNode;
            size++;
            return;
        }

        Node<T> current = head;

        while(current.getNext() != null && comparator.compare(data,current.getNext().getData())>=0){
            current = current.getNext();
        }

        if (current.getNext() == null) {
            current.setNext(newNode);
            newNode.setPrevious(current);
            tail = newNode;
        } else{
            Node<T> next = current.getNext();

            newNode.setPrevious(current);
            newNode.setNext(next);

            current.setNext(newNode);
            next.setPrevious(newNode);
        }
        size++;
    }

    public void showLinkedList(){
        Node<T> current = head;
        while(current != null){
            System.out.println(current.getData());
            current = current.getNext();
        }
    }

    public DoublyLinked<T> copy() {
        DoublyLinked<T> copy = new DoublyLinked<>();

        Node<T> current = head;

        while (current != null) {
            copy.addLast(current.getData());
            current = current.getNext();
        }

        return copy;
    }

    public Node<T> getNode(int index) {

        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }

        Node<T> current = head;

        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current;
    }

    public T removeAt(int index){
        if(index < 0 || index >= size){
            throw new IndexOutOfBoundsException();
        } else if(index == 0) {
            return removeFirst();
        } else if (index == size - 1) {
            return removeLast();
        }

        Node<T> current = head;

        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }

        T data = current.getData();

        Node<T> previous = current.getPrevious();
        Node<T> next = current.getNext();

        previous.setNext(next);
        next.setPrevious(previous);

        size--;
        return data;
    }

    //Métodos adicionales para crecimiento del código

    public void addAt(int index, T data){
        if (index < 0 || index > size) {
            throw new IndexOutOfBoundsException("Índice fuera de rango");
        }

        if (index == 0) {
            addFirst(data);
        } else if (index == size) {
            addLast(data);
        } else{
            Node<T> newNode = new Node<>(data);
            Node<T> current = head;
            for (int i = 0; i < index; i++) {
                current = current.getNext();
            }

            newNode.setNext(current);
            newNode.setPrevious(current.getPrevious());

            current.getPrevious().setNext(newNode);
            current.setPrevious(newNode);

            size++;
        }
    }

    public void setAt(int index, T data){
        Node<T> current = head;

        for (int i = 0; i < index ; i++) {
            current = current.getNext();
        }
        current.setData(data);
    }

    public T removeFirst(){

        if (head == null) {
            return null;
        }

        T data = head.getData();

        if (size == 1) {
            head = null;
            tail = null;
        } else{
            head = head.getNext();
            head.setPrevious(null);
        }
        size--;
        return data;
    }

    public T removeLast(){
        if(tail == null){
            return null;
        }

        T data = tail.getData();

        if (size == 1) {
            head = null;
            tail = null;
        } else{
            tail = tail.getPrevious();
            tail.setNext(null);
        }
        size--;
        return data;
    }

}
