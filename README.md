[![Build Status](https://travis-ci.org/doo9104/web-test.svg?branch=master)](https://travis-ci.org/doo9104/web-test)

### **개발환경**

### **Front-End**

- Mustache Template Engine
- bootstrap

### **Back-End**

- Spring Boot
- oAuth2
- JDK 1.8
- H2 imMemory DB

### infrastructure

- AWS EC2
- AWS RDS
- AWS S3
- AWS CodeDeploy
- NginX
- TRAVIS CI

스프링 부트와 JPA 를 이용한 기본적인 CRUD 게시판과 OAuth2를 이용한 구글,네이버 소셜 로그인 기능을 구현하였습니다.

완성된 소스는 AWS EC2 서버 환경에 배포하였고 AWS RDS에 DB를 저장하였습니다. 

코드가 푸시되면 자동으로 배포하는 Travis CI와 AWS S3,AWS CodeDeploy를 연동하여 무중단 배포 시스템을 구축하였습니다.

실제 배포 과정을 직접 설정,구축 함으로써 애플리케이션 개발에서부터 클라우드 서비스 환경까지 다양하게 경험할 수 있었던 프로젝트였습니다.
