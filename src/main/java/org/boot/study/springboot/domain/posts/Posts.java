package org.boot.study.springboot.domain.posts;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.boot.study.springboot.domain.BaseTimeEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
/*
Setter가 없는데 어떻게 값을 채워 DB에 삽입될까?
원래는 생성자를 통해 값을 채운후 DB에 삽입하고 값 변경이 필요한 경우 해당 이벤트에 맞는 public 메소드를 호출하여 변경
하지만 Builder를 통해 제공되는 빌더 클래스를 사용함
*/
@Getter
@NoArgsConstructor // 기본 생성자 자동추가 public Posts() {} 자동 생성
@Entity
// Entity : 테이블과 링크될 클래스임을 나타냄, 기본값으로 클래스의 카멜케이스 이름을 언더스코어 네이밍(_)으로 테이블 이름을 매칭
// Entity 클래스에서는 Setter 메소드를 만들지 말것. 해당 클래스의 인스턴스 값들이 언제 어디서 변해얗하는지 코드상으로 명확하게 구분할수가 없어 후에 복잡해짐
public class Posts extends BaseTimeEntity {

    @Id // 테이블의 PK 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK의 생성 규칙, GenerationType.IDENTITY을 추가해야만 auto_increment가 됨
    private Long id;

    @Column(length = 500, nullable = false) // 테이블의 칼럼, 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 됨
    // 사용하는 이유는 기본값 이외에 추가로 변경이 필요할때. 기본값 255인데 늘리고 싶거나 타입을 TEXT로 변경하거나 할 때 사용
    private String title;

    @Column(columnDefinition = "TEXT",nullable = false)
    private String content;

    private String author;

    @Builder // 해당 클래스의 빌더 패턴 클래스를 생성, 생성자 상단에 선언시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
