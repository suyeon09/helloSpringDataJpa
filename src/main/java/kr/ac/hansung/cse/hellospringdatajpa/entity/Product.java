package kr.ac.hansung.cse.hellospringdatajpa.entity;

// ✅ [필수] User 클래스 참조를 위해 import 추가
import kr.ac.hansung.cse.hellospringdatajpa.entity.User;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String brand;
    private String madeIn;
    private double price;

    // ✅ [추가] 이 상품이 어떤 사용자(user)에 의해 생성되었는지를 나타냄
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id") // DB 테이블에 user_id라는 외래키가 생성됨
    private User user;

    public Product(String name, String brand, String madeIn, double price) {
        this.name = name;
        this.brand = brand;
        this.madeIn = madeIn;
        this.price = price;
    }
}
