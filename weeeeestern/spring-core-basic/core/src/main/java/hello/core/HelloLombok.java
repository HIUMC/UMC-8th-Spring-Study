package hello.core;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
// 다른 생성자가 있기에 기본 생성자가 필요하면 명시적으로 만들어야 한다.
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
