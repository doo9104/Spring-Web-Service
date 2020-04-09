package org.boot.study.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
/*
Posts 클래스로 Database를 접근하게 해준다.
Dao 역할하는 DB Layer 접근자
인터페이스 생성후 JpaRepository<Entity 클래스,PK 타입>을 상속하면 기본 CRUD 메소드가 자동 생성
@Repository를 추가할 필요 없음.
주의사항은 Entity클래스와 기본 Entity Repository는 함께 위치해야함.
Entity클래스와 기본 Repository는 함께 움직여야 하므로 도메인 패키지에서 함께 관리
 */

public interface PostsRepository extends JpaRepository<Posts,Long> {

    @Query("SELECT p FROM Posts p ORDER BY p.id DESC")
    List<Posts> findAllDesc();

}
