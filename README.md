private-query-dsl-v1

## 📚 Query DSL 이란?
<details>
  <summary>[1] 정의 🔖 </summary>

- Query DSL == Query Domain Specific Language
  - 쿼리 : 데이터베이스에서 데이터를 조회하는 명령
  - Query DSL : 쿼리를 작성하는 데 특화된 언어
  - 자바 코드로 SQL 을 안전하게 작성할 수 있도록 도와준다.
- type-safe 한 방식으로 SQL과 유사한 쿼리문을 자바 코드로 작성할 수 있게 도와주는 Java 기반의 ORM 쿼리 빌더 라이브러리
  - type-safe : 런타임 에러가 아닌 컴파일 에러를 일으키기 때문에 컴파일 시점에 타입 오류 감지 가능
- JPQL 을 대체할 수 있는 코드 기반 DSL
</details>

<details>
  <summary>[2] 사용 배경 📖 </summary>

- 기존 JPQL 의 한계
  ![JPQL 사용 예시](https://github.com/user-attachments/assets/781ec07a-d4e0-4f27-9f48-8a6cc27f2242)  
  - 1️ 문자열 기반의 한계
    - 타입 안정성 미보장
    - 컴파일 에러가 아닌 런타임 에러 유발
    - 오타, 잘못된 컬럼명, 잘못된 경로 등이 모두 런타임 에러로 발생하기 때문에 실행해보기 전까지 알 수 없어서 디버깅이 어려움
    - 클래스나 필드명이 바뀌어도 문자열로 작성된 JPQL 에 자동 반영 되지 않아서 리팩토링에 취약함  
  - 2️⃣ 띄워쓰기 이슈
    - 대부분의 쿼리 조각 맨 끝에 공백을 항상 추가해줘야 하는데, 이는 생산성 떨어지는 요인이 될 수 있음  
  - 3️⃣ 동적 쿼리에 불리
    - 동적 쿼리를 작성하기 까다롭고,
    - 조건이 3~4개만 넘어가도 코드가 지저분해지고 가독성이 떨어짐
    - 작성을 해도 유지보수에 취약함  
  - 4️⃣ 코드 재사용성이 낮음

- Query DSL 로 JPQL 의 한계를 보완 가능  
  1️ 쿼리를 문자열이 아닌 코드로 작성
    - 타입 안정성 (type-safe) 보장
    - 런타임 에러가 아닌 컴파일 에러를 띄워줌  
  2️⃣ 문자열을 이어붙이는 방식이 아닌 메서드 체이닝 방식
    - 문자열을 이어 붙일 일이 없음  
  3️⃣ 동적 쿼리에 유리
    - BooleanBuilder, BooleanExpression 등을 통해 쉬운 동적 쿼리 조립 가능
    - where 을 사용해 조건 다중 처리 해결 가능
    - 가독성도 있고, 유지보수도 쉬워짐  
  4️⃣ 메서드 추출을 통한 공통 조건 재활용 가능
</details>

<details>
  <summary>[3] QueryDSL 의 핵심 개념</summary>

1️ JPA Repository 가 아닌 CustomRepository 구현  
2️⃣ QueryDslConfig 설정  
3️⃣ src.main.generated 에서 QEntity 생성  
4️⃣ BooleanBuilder 및 BooleanExpression 사용  

</details>

[2] 

[3] 장점과 단점

[4] 현재 코드에서의 적용

## 📝 Let's Practice !

<details>
  <summary>🔽 **[ STEP 1 - 필수 설정 가이드 ]** 🔽</summary>

  여기에 내용이 들어갑니다!
</details>

1️ build.gradle 에서 dependencies 설정
<pre>
```java
	// QueryDSL
	implementation 'com.querydsl:querydsl-apt:5.0.0'
	implementation 'com.querydsl:querydsl-jpa:5.0.0:jakarta'
	implementation 'com.querydsl:querydsl-core:5.0.0'

	annotationProcessor "com.querydsl:querydsl-apt:5.0.0:jakarta"
	annotationProcessor "jakarta.annotation:jakarta.annotation-api"
	annotationProcessor "jakarta.persistence:jakarta.persistence-api"
  
</pre>
