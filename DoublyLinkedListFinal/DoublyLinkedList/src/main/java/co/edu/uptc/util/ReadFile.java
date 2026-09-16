package co.edu.uptc.util;

import co.edu.uptc.model.DoublyLinked;
import co.edu.uptc.pojo.Person;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.time.LocalDate;

public class ReadFile {
    private final String PATH = "TestDoublyList.txt";
    private DoublyLinked<Person> linked;

    public ReadFile(){
    }

    public DoublyLinked<Person> readWrite(){
        linked = new DoublyLinked<>();
        InputStream inputStream = getClass()
                .getClassLoader()
                .getResourceAsStream(PATH);

         try(BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))){
             String line;

             while((line = br.readLine()) != null){
                 String[] data = line.split(",");
                 Person newPerson = new Person(
                         data[0],
                         data[1],
                         LocalDate.parse(data[2]),
                         data[3],
                         data[4]
                 );
                 linked.addLast(newPerson);
             }
         } catch (Exception e) {
             throw new RuntimeException(e);
         }
            return linked;
    }
}
