package hello.hellospring.domain;

public class Member {

    // 1. 요구사항에 맞는 변수 선언
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
