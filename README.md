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
