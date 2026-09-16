package co.edu.uptc.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.Period;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class Person {
    private String name;
    private String lastName;
    private LocalDate birth;
    private String gender;
    private String state;

    @Override
    public String toString() {
        return "Nombre completo: " + name + " " + lastName +
                " | Nacimiento: " + birth +
                " | Género: " + gender +
                " | Estado civil: " + state;
    }
}
