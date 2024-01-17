package hello.hellospring.domain;

import jakarta.persistence.*;

@Entity
public class Member {

    // 1. 요구사항에 맞는 변수 선언
    // DB가 알아서 생성: GenerationType.IDENTITY
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    // 2. Getter & Setter 생성
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
