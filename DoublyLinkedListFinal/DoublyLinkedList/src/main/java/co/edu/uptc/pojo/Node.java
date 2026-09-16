package co.edu.uptc.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Node<T> {
    private Node<T> next;
    private Node<T> previous;
    private T data;

    public Node(T data){
        this.data = data;
        this.next = null;
        this.previous = null;
    }
}
