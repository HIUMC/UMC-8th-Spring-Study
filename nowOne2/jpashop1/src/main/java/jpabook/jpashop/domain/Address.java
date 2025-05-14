package jpabook.jpashop.domain;


import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;


@Getter
@Embeddable
public class Address {
    private String city;
    private String street;
    private String zipcode;

    protected Address(){ //값 타입은 변경 불가능하게 설계해야 한다

    }

    public Address(String street, String city, String zipcode) {
        this.street = street;
        this.city = city;
        this.zipcode = zipcode;
    }
}
