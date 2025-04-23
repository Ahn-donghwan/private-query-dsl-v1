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
    - 문자열 기반의 한계
        - 타입 안정성 미보장
        - 컴파일 에러가 아닌 런타임 에러 유발
        - 오타, 잘못된 컬럼명, 잘못된 경로 등이 모두 런타임 에러로 발생하기 때문에 실행해보기 전까지 알 수 없어서 디버깅이 어려움
        - 클래스나 필드명이 바뀌어도 문자열로 작성된 JPQL 에 자동 반영 되지 않아서 리팩토링에 취약함
    - 띄워쓰기 이슈
        - 대부분의 쿼리 조각 맨 끝에 공백을 항상 추가해줘야 하는데, 이는 생산성 떨어지는 요인이 될 수 있음
    - 동적 쿼리에 불리
        - 동적 쿼리를 작성하기 까다롭고,
        - 조건이 3~4개만 넘어가도 코드가 지저분해지고 가독성이 떨어짐
        - 작성을 해도 유지보수에 취약함
    - 코드 재사용성이 낮음

- Query DSL 로 JPQL 의 한계를 보완 가능
    - 쿼리를 문자열이 아닌 코드로 작성
        - 타입 안정성 (type-safe) 보장
        - 런타임 에러가 아닌 컴파일 에러를 띄워줌
    - 문자열을 이어붙이는 방식이 아닌 메서드 체이닝 방식
        - 문자열을 이어 붙일 일이 없음
    - 동적 쿼리에 유리
        - BooleanBuilder, BooleanExpression 등을 통해 쉬운 동적 쿼리 조립 가능
        - where 을 사용해 조건 다중 처리 해결 가능
        - 가독성도 있고, 유지보수도 쉬워짐
    - 메서드 추출을 통한 공통 조건 재활용 가능

</details>

<details>
  <summary>[3] QueryDSL 의 핵심 개념 🎯 </summary>

1. JPA Repository 가 아닌 CustomRepository 구현
2. QueryDslConfig 설정
3. src.main.generated 에서 QEntity 생성
4. BooleanBuilder 및 BooleanExpression 사용

</details>

<details>
  <summary>[4] QueryDSL 의 장점과 단점 💡 </summary>

- 장점은 위에서 언급했듯이
    - 타입 안정성 보장
    - 쿼리를 코드로 작성
    - 동적 쿼리에 유리
    - 공통 조건 재활용 가능
- 단점
    - Q타입의 유지 보수 문제
        - Q타입은 Entity 구조에 강하게 의존함
        - 엔티티가 바뀌면 Q타입도 다시 생성되어야 하고, 기존 QueryDSL 도 수정되어야 함
        - clean > build
    - QueryDSL 은 외부 라이브러리이기 때문에 호환성 문제가 발생할 수 있음
        - Spring 의 내부 라이브러리인 Spring Data JPA 의 Specification 을 대안으로 고려 가능
    - 러닝 커브가 긴 편

</details>

<details>
  <summary>[5] 현재 코드에서의 적용 📌 </summary>

![스타벅스 상품 조회 화면](https://github.com/user-attachments/assets/cff70174-e8ab-486b-a02a-564b1097bb4c)

- 아래 5가지 조건이 각각 있을 수도 있고, 없을 수도 있음
- 이에 따른 동적 쿼리 작성에 Query DSL 적용

    - Main-Category : 텀블러/보온병 , 머그/컵 , 라이프스타일 등등
    - Sub-Category : 플라스틱 텀블러, 스테인리스 텀블러 , 보온병 , 콜드컵 등등
    - Special : 코어 MD 리뉴얼 , 액티브 , 버디위크 페스티벌 등등
    - Size : SHORT , TALL , GRANDE , VENTI , TRENTA
    - PriceRange :
        - BELOW_10K (1만원 미만)
        - TEN_K (1만원 대)
        - TWENTY_K (2만원 대)
        - THIRTY_K (3만원 대)
        - FORTY_K (4만원 대)
        - ABOVE_50K (5만원 이상)

</details>

## 📝 Let's Practice !

<details>
  <summary>🔽 **[ STEP 1 - build.gradle 에서 dependencies 설정 ]** 🔽</summary>

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

- querydsl-core : QueryDSL 의 핵심 로직이 담겨있음
- querydsl-jpa
    - QueryDSL 의 JPA 지원 모듈
    - JPA 를 사용해서 JPAQuery, BooleanBuilder, JPQLQuery 등을 사용 가능
- querydsl-apt
    - APT == Annotation Processing Tool
    - JPA 엔티티 클래스를 기반으로 QClass 를 생성
- annotationProcessor 3가지 : Q타입 생성의 필수 도구

</details>

<details>
  <summary>🔽 **[ STEP 2 - Enable annotation processing ✅ ]** 🔽</summary>

![annotationprocessors](https://github.com/user-attachments/assets/4a936fcb-6aa2-409e-bc4b-7e85b3e9610e)

File > Settings > Build, Execution, Deployment > Compiler > Annotation Processors

- Enable annotation processing ✅
    - 체크하지 않으면 Q클래스가 생성되지 않는다.

</details>

<details>
  <summary>🔽 **[ STEP 3 - Q타입 클래스들이 생성될 경로를 직접 지정 ]** 🔽</summary>

<pre>

```java  


def generated = 'src/main/generated'

tasks.withType(JavaCompile) {
	options.getGeneratedSourceOutputDirectory().set(file(generated))
}

sourceSets {
	main.java.srcDirs += [ generated ]
}

clean {
	delete file(generated)
}
  
</pre>

- 코드 설명 🔧

![QueryDSL 빌드 옵션 설정](https://github.com/user-attachments/assets/56e76da5-bebe-4f7e-a1b0-786bd13e112e)

- def generated = 'src/main/generated'
    - Q클래스를 생성할 경로를 커스터마이징하는 설정
    - default 는 build/gradle
    - 이를 src/main/generated 로 바꿔 소스 트리 내에서 관리하기 쉽게 만드는 것

- tasks.withType(JavaCompile){...}
    - Q클래스가 지정된 경로에 생성되도록 명시
    - 없으면 기본 위치에서 생성될 수 있음

- sourceSets{...}
    - src 에 Q클래스 포함
    - src/main/generated 안의 Q클래스를 Java 소스 코드로 인식하게 만듬
    - 이게 없으면 컴파일러가 Q타입을 찾지 못해서 오류 발생하게 됨

- clean{...}
    - ./gradlew clean 시에 Q클래스도 깨끗하게 삭제되도록 함
    - 엔티티가 변경되었을 때 clean > build 를 함으로써 최신 Q타입 동기화
    - 재빌드 시 최신 Q타입 보장

</details>

<details>
  <summary>🔽 **[ STEP 4 - CustomRepository 인터페이스 및 구현체 생성 ]** 🔽</summary>

![productRepositoryCustomImpl 구현](https://github.com/user-attachments/assets/e2631a7c-0295-461f-95c2-cdcc01e6a3c4)

<pre>
```java
public interface ProductRepository extends JpaRepository&lt;Product, Long&gt;, ProductRepositoryCustom {...}
  
</pre>

<pre>
```java
@Repository
@RequiredArgsConstructor
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {...}
  
</pre>

- CustomRepository 를 따로 만드는 이유 ?
    - Spring Data JPA 의 확장성 과 관심사 분리 때문
    - JpaRepository 는 기본적인 CRUD 만 제공
    - 복잡한 동적 쿼리를 작성해야 하기 때문에 인터페이스 사용 후 구현체에서 작성
    - 관심사 분리를 통해 책임이 명확해지고, 코드 유지보수가 쉬워짐
        - Custom 구현만 따로 테스트가 가능해진다.

</details>

<details>
  <summary>🔽 **[ STEP 5 - QueryDslConfig 생성 ]** 🔽</summary>

<pre>
```java
@Configuration
public class QueryDslConfig {

    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(JPQLTemplates.DEFAULT, entityManager);
    }
}
  
</pre>

<pre>
```java
@Repository
@RequiredArgsConstructor
public class ProductRepositoryImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory queryFactory;

... }
  
</pre>

- @Bean 등록
    - 매번 new JPAQueryFactory 를 만들 필요 없어짐
    - 한 번만 생성해두면, 여러 클래스에서 재사용할 수 있음

- JPAQueryFactory
    - .select() , .from() , .where() 같은 메서드를 사용할 수 있게 해줌

- JPQLTemplates.DEFAULT 는 생략해도 기본 값으로 들어가기 때문에 안넣어줘도 됨
- 아래와 같이만 해줘도 가능 !

<pre>
```java
    @Bean
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
  
</pre>

</details>

<details>
  <summary>🔽 **[ STEP 6 - RepositoryCustomImpl 직접 구현 ]** 🔽</summary>

- 의존성 주입
    - private final JPAQueryFactory queryFactory; 와 RequiredArgsConstructor

- Q클래스 생성

<pre>
```java
QProductCategory productCategoryQ = QProductCategory.productCategory;
QProduct productQ = QProduct.product;
QMainCategory mainCategoryQ = QMainCategory.mainCategory;
QSubCategory subCategoryQ = QSubCategory.subCategory;
QSpecial specialQ = QSpecial.special;
</pre>

- Projections.constructor(ProductSearchResDto.class, productQ.name, productQ.price)
    - Projections 사용 시, Dto 에 딱 맞게 가공 가능
    - Dto 에 반드시 해당 생성자가 있어야 사용 가능
    - 위 코드는 다음과 같은 생성자 호출을 의미한다.

<pre>
```java
new ProductSearchResDto(product.getName(), product.getPrice());
</pre>

- Projections 정리
    - Projections.constructor(...)
        - 설명 : 생성자로 매핑
        - 필요 조건 : 생성자 필수
    - Projections.fields(...)
        - 설명 : 필드에 직접 주입
        - 필요 조건 : public 필드 or 필드에 setter 있어야 함
    - Projections.bean(...)
        - 설명 : setter 로 매핑
        - 필요 조건 : 기본 생성자 + setter 필요

- QueryDSL 구조

<pre>
```java
queryFactory
.select(...)               : 반환할 컬럼 또는 Projects 를 사용해 Dto 로 반환 가능
.from(productQ)            : 기준 테이블
.join(productCategoryQ)... : 내부 조인
.leftJoin(...)...          : 외부 조인
.where(...)                : 조건절
.orderBy(...)              : 정렬
.fetch()                   : 쿼리 실행 + 결과 가져오기
</pre> 

- QueryDSL 작성
    - 만약 엔티티 전체를 조회해야 한다면,
        - select(productQ).from(productQ) 를
        - selectFrom(productQ) 로 축약 가능
    - 내부조인과 외부조인
        - .join(...) (내부조인) : 두 테이블에서 일치하는 데이터만 가져온다.
        - .leftJoin(...) (외부조인) : 왼쪽 테이블의 모든 데이터는 가져오고, 오른쪽 테이블은 조건에 맞는 데이터가 있을 경우에만 가져온다.
            - on() 안을 기준으로 왼쪽 테이블과 오른쪽 테이블을 나누고 의미한다.
            - .rightJoin(...) 과 반대
    - on(...)
        - 조건
        - on(productQ.uuid.eq(productCategoryQ.productUuid)) == productCategory 의 productUuid 와 product 의 uuid 가 같은 경우에

- BooleanExpression 과 BooleanBuilder 비교
    - BooleanExpression
        - 핵심 목적 : 단일 조건 표현
        - 사용 방식 : where(조건) 에 직접 넣음
        - 특징 : null 을 반환하면 where 에서 무시됨
    - BooleanBuilder
        - 핵심 목적 : 다중 조건 동적 조립
        - 사용 방식 : 여러 조건을 .and() 와 .or() 로 이어붙여 조립
        - 특징 : 조건을 누적하면서 유연하게 추가 가능

</details>

## 💻 구현 코드 설명

<details>
  <summary>⏬ **[ ProductRepositoryCustomImpl (QueryDSL 구현체) ]** ⏬</summary>

<pre>
```java
@Repository   : DAO (Persistence Layer, 데이터 액세스 계층) 임을 나타내는 애노테이션, @Component 를 포함하고 있음
@RequiredArgsConstructor
public class ProductRepositoryCustomImpl implements ProductRepositoryCustom {

    private final JPAQueryFactory queryFactory;    : 의존성 주입(DI), @RequiredArgsConstructor 와 함께 사용됨

    QProductCategory productCategoryQ = QProductCategory.productCategory;
    QProduct productQ = QProduct.product;
    QMainCategory mainCategoryQ = QMainCategory.mainCategory;
    QSubCategory subCategoryQ = QSubCategory.subCategory;
    QSpecial specialQ = QSpecial.special;                                     : Q 클래스 선언

    @Override
    public List&lt;ProductSearchResDto&gt; searchProducts(ProductSearchParamDto productSearchParamDto) {

        return queryFactory
                .select(Projections.constructor(           : 쿼리 결과를 해당 Dto 생성자에 맞게 매핑
                        ProductSearchResDto.class,
                        productQ.name,
                        productQ.price
                ))
                .from(productQ)                            : data 를 가져올 기준 테이블
                .join(productCategoryQ).on(productQ.uuid.eq(productCategoryQ.productUuid))               : product 와 productCategory 내부조인 (on 조건)
                .leftJoin(mainCategoryQ).on(productCategoryQ.mainCategoryUuid.eq(mainCategoryQ.uuid))    : productCategory 와 mainCategory 외부조인 (on 조건)
                .leftJoin(subCategoryQ).on(productCategoryQ.subCategoryUuid.eq(subCategoryQ.uuid))       : productCategory 와 subCategory 외부조인 (on 조건)
                .leftJoin(specialQ).on(productCategoryQ.subCategoryUuid.eq(specialQ.uuid))               : productCategory 와 special 외부조인 (on 조건)
                .where(
                        mainCategoryEquals(productSearchParamDto.getMainCategory(), mainCategoryQ),      : 조건 1
                        subCategoryEquals(productSearchParamDto.getSubCategory(), subCategoryQ),         : 조건 2
                        specialEquals(productSearchParamDto.getSpecial(), specialQ),                     : 조건 3
                        sizeEquals(productSearchParamDto.getSize(), productQ),                           : 조건 4
                        priceRangeEquals(productSearchParamDto.getPriceRange(), productQ)                : 조건 5
                )
                .orderBy(productQ.price.asc())                                                           : 정렬 기준 == product 의 price 기준 오름차순
                .fetch();                                                                                : 쿼리 실행
    }

    private BooleanExpression priceRangeEquals(PriceRange priceRange, QProduct productQ) {
        return priceRange != null ? productQ.price.between(priceRange.getMin(), priceRange.getMax()) : null;
    }


    private BooleanExpression sizeEquals(Size size, QProduct productQ) {
        return size != null ? productQ.size.eq(size) : null;
    }

    private BooleanExpression specialEquals(String special, QSpecial specialQ) {
        return special != null ? specialQ.name.eq(special) : null;
    }


    private BooleanExpression subCategoryEquals(String subCategory, QSubCategory subCategoryQ) {
        return subCategory != null ? subCategoryQ.name.eq(subCategory) : null;
    }

    private BooleanExpression mainCategoryEquals(String mainCategory, QMainCategory mainCategoryQ) {
        return mainCategory != null ? mainCategoryQ.name.eq(mainCategory) : null;
    }
}
</pre>
</details>

## 🐾 한 걸음 더

<details>
<summary> ⏬ Offset-based Pagination (/pagination) ⏬ </summary>

> 1️⃣ Page 와 Pageable

| 객체       | 의미    | 방향         | 포함 정보            |
 |----------|-------|------------|------------------|
| Pageable | 요청 정보 | 클라이언트 ⇒ 서버 | page, size, sort |
| Page     | 응답 정보 | 서버 ⇒ 클라이언트 | 데이터 + 메타정보       |

> 2️⃣ Pageable Parameter

| 파라미터 | 설명                                    |
 |------|---------------------------------------|
| page | 보여줄 페이지 번호 (1부터 시작)                   |
| size | 한 페이지당 항목 개수                          |
| sort | 정렬 기준 필드 (여러 개 지정 가능)와 방향 (asc, desc) |

> 📌 Example Code ⬇️

```java

@GetMapping("/searches/pagination")
public Page<ProductSearchResVo> searchProducts(
        @RequestParam(required = false) String mainCategory,
        @RequestParam(required = false) String subCategory,
        @RequestParam(required = false) String special,
        @RequestParam(required = false) Size size,
        @RequestParam(required = false) PriceRange priceRange,
        @RequestParam(required = false, defaultValue = "1") int page,
        @RequestParam(required = false, defaultValue = "10") int pageSize
) {
    return productService.searchProducts(
            ProductSearchParamDto.of(mainCategory, subCategory, special, size, priceRange),
            PaginationParamDto.of(page, pageSize)
    ).map(ProductSearchResDto::toVo);
}
```

> 3️⃣ QueryDSL 에서 Pagination 적용

> 🎯 PageImpl<>(List&lt;T&gt; content, Pageable pageable, long total)
> > 1. content : 현재 페이지에 포함된 데이터 목록
> > 2. pageable : 요청한 페이징 정보 (page, size, sort, offset)
> > 3. total : 전체 데이터 개수 (페이징 되기 전 전체 개수)

> [1] content 에서 .offset(pageable.getoffset()) 추가
> > 

> [2] content 에서 .limit(pageable.getPageSize()) 추가
> > 

> [3] 
> >



> 4️⃣ Page&lt;T&gt; 응답 필드 상세 설명

> 📄 Example Response ⬇️

```json
{
        "content": [
        {
        "productName": "SS 시그니처 나수 텀블러 355ml",
        "productPrice": 34000
        },
        {
        "productName": "SS 시그니처 데비 텀블러 473ml",
        "productPrice": 35000
        },
        {
        "productName": "SS 시크니처 탱크 텀블러 503ml",
        "productPrice": 38000
        }
        ],
        "pageable": {
        "pageNumber": 0,
        "pageSize": 10,
        "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
        },
        "offset": 0,
        "paged": true,
        "unpaged": false
        },
        "last": true,
        "totalPages": 1,
        "totalElements": 3,
        "size": 10,
        "number": 0,
        "sort": {
        "empty": true,
        "sorted": false,
        "unsorted": true
        },
        "first": true,
        "numberOfElements": 3,
        "empty": false
        }
```

> 💡 Page&lt;T&gt; = List&lt;T&gt; + 페이징 메타데이터
> > - Page&lt;T&gt; 는 결국 List&lt;T&gt; 에서 페이징 메타 정보가 추가된 형태일 뿐이다.
> > - 아래에서 페이징 필드에 대해 하나 하나 확인해보자.

| 파라미터             | 타입            | 설명                                          |
 |------------------|---------------|---------------------------------------------|
| content 📌       | List&lt;T&gt; | Response Data 객체                            |
| pageable 📌      | pageable      | Paging Data 객체                              |
| ├ pageNumber     | int           | 현재 페이지 번호                                   |
| ├ pageSize       | int           | 요청된 한 페이지당 항목 개수                            |
| ├ sort           | Sort          | 정렬 정보 (정렬 기준 필드와 방향 포함)                     |
| ├ offset 💡      | long          | = page * size                               |
| ├ paged          | boolean       | 페이징이 적용된 상태인지 여부                            |
| └ unpaged        | boolean       | 페이징이 적용되지 않은 경우                             |
| last             | boolean       | 현재 페이지가 마지막 페이지인지 여부<br/>(true 면 다음 페이지 없음) |
| totalPages 📌    | int           | 전체 데이터 기준으로 계산된 전체 페이지 수                    |
| totalElements    | long          | 전체 데이터 개수 (페이징 전 기준)                        |
| size             | int           | 한 페이지당 데이터 수 (pageSize)                     |
| number           | int           | 현재 페이지 번호                                   |
| sort             |               |                                             |
| ├ empty          | boolean       | 정렬 기준이 비어 있는지 여부                            |
| ├ sorted         | boolean       | 정렬 기준이 적용되었는지 여부                            |
| └ unsorted       | boolean       | 정렬 기준이 없음을 의미                               |
| first            | boolean       | 현재 페이지가 첫 페이지인지 여부                          |
| numberOfElements | int           | 현재 페이지가 들어있는 데이터 개                          |
| empty            | boolean       | content 가 비어 있는지 여부<br/>(true 면 데이터 없음)     |

> 💡 offset 이란?
> > - 데이터 조회 시 어디서부터 시작할지를 의미하는 숫자
> > - 건너뛸 데이터의 개수
> > - offset = page * size

</details>

<details>
<summary>⏬ Cursor-based Pagination (/cursor) ⏬</summary>
</details>

<details>
<summary>⏬ Pagination (/pagination) ⏬</summary>
</details>

<details>
<summary>⏬ Boolean Builder 와 Boolean Expression ⏬</summary>
</details>