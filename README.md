# VocBackEnd
문의 등록/답변 시스템의 백엔드 프로젝트로 프론트엔드 프로젝트()에서 사용 가능하도록 개발하였습니다.

## 문의 등록 및 답변 시나리오
1. 고객이 고객페이지 - 문의등록 메뉴에서 문의를 등록합니다.
2. 상담사가 관리자 페이지 - 미답변 목록조회 메뉴에서 답변이 이뤄지지 않은 문의를 접수합니다.
3. 상담사가 관리자페이지- 문의 답변 메뉴에서 본인이 접수한 문의에 대한 답변을 작성합니다. 

작성된 답변은 고객페이지 - 문의 목록 메뉴에서 본인이 작성한 문의를 포함한 모든 문의와 답변을 확인 가능합니다.

## 1. 프로젝트 구성
- 개발 환경
  - IDE: Visual Studio Code
  - OS: Windows 10
- Backend
  - JavaSE-11
  - Spring Boot 2.5.1
  - Spring Security 5.1.5, jwt
  - JPA, Hibernate
  - H2
  - Maven
  - Lombok
- UnitTest
  - Junit, Mockito

## 2. 실행 방법
Java와 Git이 설치 된 환경의 터미널에서 아래 명령어를 순서대로 실행한다.
```
$ git clone https://github.com/StarYankie/VocBackEnd.git
$ cd VocBackEnd
$ ./mvnw install
$ java -jar target/servicedesk-0.0.1-SNAPSHOT.jar
```
기본실행 URI : http://localhost:8080

## 3. 문제 해결 전략
- 기본적인 기능들은 REST API로 제공하고 특정 기능들은 프론트엔드에서도 권한 체크를 하지만
추가적으로 로그인을 통해 획득한 jwt Token을 포함한 헤더가 있어야만 접근이 가능하도록 하였다.

- JPA Model에 키 값은 자동으로 생성되게 하고 필수 입력 필드에는 @NotNull 을 사용하여 백엔드에서도
Validation 이 이뤄질 수 있도록 구현하였다.

- 특별한 로직이 없어 Service layer를 사용하지 않아 Repository, Controller에 대한 테스트 코드만 JUnit을 사용하여 작성하였다.

## 4. API 사용 방법 및 예제
- 고객 문의 등록
 ```
 POST http://localhost:8080/v1/customer/vocs
 
 {"userid":"고객ID","title":"제목","content":"문의내용"}
 ```
- 문의 목록 조회

```
GET http://localhost:8080/v1/customer/vocs -- 기본 조회
```
```
GET http://localhost:8080/v1/manager/vocs?replied=N -- 미답변 문의만 조회(권한 필요)
```
```
GET http://localhost:8080/v1/manager/vocs?manager=mng01 -- - mng01 상담사가 접수한 문의만 조회(권한 필요)
```
- 데이터 RETURN Sample
``` JSON
[
 {"id":1,
  "userid":"CustomerID",
  "title":"Subject",
  "content":"Content"
  ,"mngid":null
  ,"replied":"N",
  "crdt":"2021-06-20T14:16:18.21668",
  "responses":[]}
]
```

- 고객 문의 접수
```
PATCH http://localhost:8080/v1/manager/vocs/{문의 id}?mngid=mng01
```
 
- 답변 등록
```
POST http://localhost:8080/v1/manager/responses
{"vocid":1,"mngid":"mng01","content":"답변 내용"}
```
