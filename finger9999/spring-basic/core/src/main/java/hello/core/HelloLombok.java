package hello.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
public class HelloLombok {

    private String name;
    private int age;

    public HelloLombok(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public static void main(String[] args) {
        HelloLombok h = new HelloLombok();
        h.setName("meow");

        System.out.println("helloLombok =" + h );
    }
}