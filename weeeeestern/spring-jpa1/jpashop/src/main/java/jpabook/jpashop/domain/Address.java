package jpabook.jpashop.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Embeddable
@Getter

public class Address {
    private String city;
    private String street;
    private String zipcode;

    protected Address() {
    } // 값타입 Address 클래스를 함부로 생성하지 말 것

    public Address(String city, String street, String zipcode){
        this.city = city;
        this.street = street;
        this.zipcode = zipcode;
    }
}
