# training-jpa
JPA 기본 트레이닝 프로젝트

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




