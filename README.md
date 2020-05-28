# 영속성 컨텍스트로 인한 이점
- 1차 캐시
- 동일성보장
- 쓰기 지연
    - 트랜잭션이 끝나는 시점(커밋/롤백)에 쿼리 실행
- 변경감지
    - 영속성에 들어온 시점의 스냅샷을 떠둔다.

# @Entity 매핑
- @Column
    - unique, length, nullable
- @Transient
- @Lob
    - 문자열인 경우, clob
    - 그외인 경우,  blob
- LocalDate일 경우, date
- LocalDateTime일 경우, timestamp로 생성됨.
- 가급적이면 매핑(인덱스,유니크)도 적는게 좋을듯

# @Id 
- Identity
    - DB에 위임
    - DB에 저장해야 PK를 알 수 있음.
    - 따라서 persist할 때 바로 저장됨

- sequence
    - persist할 때 sequence만 먼저 가져옴.
    - 지연 전략 사용 가능

- table


# 연관관계 매핑
## 단방향 매핑
- ManyToOne

## 양방향 매핑
- mappedBy: 연관관계 주인
- FK가 있는 쪽에 연관관계의 주인이 된다.
- 왠만하면 단방향으로 설계, 꼭 필요한 경우에만 양방향 추가

## @OneToMany & @ManyToOne
- 보통 ManyToOne이 연관관계의 주인이 된다. (FK 소유 테이블이 연관 관계의 주인)
- OneToMany측에서는 mappedBy를 지정하여 readOnly임을 명시한다.
- 객체 설계 시에는 왠만하면 단방향으로 진행하는 것을 추천 (개발할 때 꼭 필요한 경우에, 양방향 매핑 추가한다.)

## @OneToOne
- DB 상으로 어느곳에 FK있어도 되지만 비즈니스 관점으로 주/부테이블을 정하여 FK(연관관계)의 주인을 정하도록 한다.
- 주테이블에 @JoinColumn을 명시하도록 한다.
- 마찬가지로 부테이블에는 mappedBy를 지정하여 readOnly임을 명시한다.

## @ManyToMany
- 실무에서는 비권장하는 매핑
- 차라리 OneToMany <- ManyToOne <Entity> ManyToOne -> OneToMany
    - 중간 Entity 객체를 풀어서 사용하는 것을 권장한다.
    
- 이유는 보통 중간 매핑 엔티티에 부가 정보를 넣고 싶어하는 것이 현업이기 때문.
    - 실무에서는 ManyToMany 관계로 단순 매핑만 하지 않는다.

# JPQL
- 객체지향 쿼리
- 별칭 필수로 지정
- new 명령으로 DTO 생성 가능
- @NamedQuery를 사용할 경우, 실행시점에 문법오류를 잡아줌.


# Spring-Data-JPA
- 인터페이스만 생성해도 자동으로 구현체를 생성
    - JpaRepository
- NamedMethod
- Pagable
    - PageRequest.of
    - Page


# QueryDSL
- JPQL의 문제점은 잘못된 쿼리일 경우, 실행 시점에 알 수 있다는 것.
- 따라서, 컴파일 시점에 문제를 알 수 있는 QueryDSL을 권장
- 자바와 동일하게 쿼리를 리팩토링할 수 있음

# 기술 패키지
- Java8
- spring (with springboot)
- JPA (with Spring-DATA-JPA)
- QueryDSL
- JUnit, Spock


# 상속 관계
- @Inheritance
    - JOINED: (default 옵션) 상위 테이블, 하위테이블로 상속 구조를 구현함.
    - SINGLE_TABLE: 한 테이블에 모든 컬럼을 지정함.
    - TABLE_PER_CLASS: 각각 테이블 별로 구현.

## JOINED
- 정규화 관점으로 테이블을 구현할 수 있으므로 불필요한 컬럼을 나눌 수 있음
- 단점
    - 조회할 때 JOIN이 매번 일어나기 때문에 성능 이슈가 발생할 수 있음.
    - 하위 테이블에 추가될 때 insert가 두번일어나게됨.

## SINGLE_TABLE
- 한 테이블에 모든 컬럼이 있기 때문에 조회, 추가 로직이 단순해진다.
- 단점
    - 모든 컬럼이 nullable해야한다는 것.
    - 정규화 관점에서 좋은 설계는 아니게 됨.

## TABLE_PER_CLASS
- 객체 관점/DB 설계 관점에서도 비추천함.
- 모든 항목 조회시 UNION ALL이 일어나게 되기 때문에 성능상으로도 문제됨.

# @MappedSuperClass
- 공통 항목을 정의할 수 있음.
    - ex) 작성일자, 수정일자 등.
- abstract Class로 지정하는 것을 추천함. (실수하지 않도록)

# Proxy & FetchType
- 기본적으로 LAZY로 설정하도록 한다. 
  - 성능상 한번에 조회가 필요한 경우에는 JPQL을 이용해 join fetch 전략을 사용한다.

# CASCADE (영속성 전이)
- 상위 객체에서 독립적으로 참조하고 있는 경우에만 사용하도록 한다.
  - 연관된 객체의 영속성 관리(라이프 사이클)를 상위 객체에서 하겠다는 의미이므로.
- CASCADE.ALL, PERSIST, REMOVE를 보통 사용한다.
  - CASCADE.ALL로 지정할 경우, orphanRemoval=true를 지정한다.
- orphanRemoval=true
  - 상위 객체의 연결관계가 끊어지면 하위 엔티티도 삭제하겠다는 의미임.

# 경로 표현식
- JPQL을 .으로 쿼리를 사용할 때
- 상태 필드
  - 엔티티 안에 있는 단일 필드
- 연관 필드
  - 단일 값 연관 필드: 연관 관계가 1:1, N:1인 경우, 추가 탐색 가능
  - 컬렉션 값 연관필드: 연관 관계가 1:N, M:N인 경우, 추가 탐색 불가능 (여러개의 값이므로)
    - 컬렉션값 연관필드는 탐색 X
- 연관 필드를 경로표현식으로 접근 시 묵시적 조인이 발생함. (주의)
- 명시적 조인을 하여 별칭을 사용하는 것을 권장

# fetch Join
- JPQL에서 성능 최적화를 위해 제공하는 기능
- SQL에서 한번에 함께 조회하는 기능
- ( LEFT | INNER ) join fetch
- Collection fetch join
  - 연관관계가 1:N인 경우, 1:N조인이라 데이터 중복 발생함. (SQL관점으로 생각해보면 당연한 결과임)
  - 따라서, 중복 제거를 위해 distinct를 사용함.
  
# distinct
- SQL에서 distinct를 추가함.
  - SQL입장에서 데이터가 모두 똑같아야 제거된다.
- 어플리케이션에서 엔티티 중복 제거
  - 어플리케이션에서 같은 식별자를 가진 엔티티를 제거함.

# fetch join의 한계
- fetch join에서는 별칭 줄 수 없음.
- 2개 이상의 컬렉션은 fetch join할 수 없다.
- 컬렉션을 fetch join하면 페이징 API(maxResult, firstResult) 사용 못함.
  - 가능은한데 메모리로 페이징을 하기 때문에 사용을 지양함.

@BatchSize 또는 hibernate.default_batch_fetch_size로 지정하여 fetch해서 가져올 데이터를 bulk로 가져옴.
