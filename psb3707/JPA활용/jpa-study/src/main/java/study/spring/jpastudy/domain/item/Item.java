package study.spring.jpastudy.domain.item;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import study.spring.jpastudy.domain.Category;
import study.spring.jpastudy.exception.NotEnoughStockException;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
public abstract class Item {

    @Id @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    private int price;

    private int stockQuantity;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList<>();

    public void addStockQuantity(int quantity) {
        stockQuantity += quantity;
    }

    public void removeStockQuantity(int quantity) {
        if(stockQuantity < quantity) {
            throw new NotEnoughStockException("Not enough stock");
        }
        stockQuantity -= quantity;
    }

}
