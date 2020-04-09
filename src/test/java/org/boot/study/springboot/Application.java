package org.boot.study.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing // JPA Auditing 활성화
@SpringBootApplication // 스프링 부트의 자동 설정, 스프링 빈 읽기와 생성을 자동으로 수행 / 프로젝트 최상단에 위치해야함
public class Application { // 메인클래스
    public static void main(String[] args) {
        //내장 WAS를 실행. 언재 어디서나 같은 환경에서 배포 가능
        SpringApplication.run(Application.class, args);
    }
}
