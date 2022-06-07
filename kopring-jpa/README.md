### [코프링 JPA 궁금한점 테스트하기]

1. @Id 값으로 val id: Long = 0 vs var id: Long? = null 형태중 어떤걸 쓸까? 

   - @GeneratedValue(strategy = GenerationType.IDENTITY) 이 전략을 사용하면 생성자로 생성시 id 값을 줘도 `IdentifierGeneratorHelper` 에 의해 생성된 값으로 들어간다.
   - save 하고 아이디 찍어보면 증가된 값이 찍힌다.
   - var nullable 보단 그냥 0으로 세팅 해두는게 낫지 않을까?

2. 생성자 프로퍼티외에 다른 값들도 컬럼이 생성될까?

   - () 생성자가 아닌 {} 본문 안에 쓰는 프로퍼티는 반드시 값을 초기화 해주어야 한다. 
   - 값이 초기화 된 프로퍼티는 컬럼이 된다.
   - 커스텀 get() 을 사용하고 초기화 하지 않은 프로퍼티는 컬럼으로 추가되지 않는다.
   - ~~~kotlin
     val test1: Int = 10  // 컬럼 추가
         get() = if (field > 0) field else 10

     val test2: Int = 0 // 컬럼 추가

     val test3: Int // 컬럼 추가 안됨
         get() = 30
     ~~~
   - 하지만 테이블 스키마가 이미 정의 되어 있고 엔티티에 디비 칼럼과 같은 프로퍼티명이 있다면 값을 읽을 수 있다.

3. 연관관계 매핑을 모든 엔티티를 해야할까? 아니면 그냥 fk id 로 써도 될까? insert 문 차이가 있나?

   - 둘다 동일하게 fk id 를 insert 한다.
   - 연관관계 매핑을 통해 insert/update 시 insertable = false, updatable = false 옵션이 false 이면 오류는 발생하지 않고, fk id 가 insert 되지 않는다.
   - fk id 를 생성자로 그냥 넣은 경우는 위의 옵션에 상관없이 insert 가 된다. fk id 를 명시적으로 줬기 때문에... 
   - 객체 그래프 탐색이 필요하다면 연관관계를 맺어서 디비에 넣는게 맞을거 같은데... 그게 아니라 단순히 저장 용도라면 fk id 를 받아서 바로 넣어도 되지 않을까 싶다.

4. 연관관계 매핑시 lateinit var vs val

   - 단건의 entity 라면 lateinit var
     - 단건의 entity 가 필수값이라면 lateinit var
     - nullable 이라면 null 로 초기화
   - 컬렉션이라면 val members : List<Member> = listOf() 이런식으로 빈 컬렉션으로 초기화를 해두면 되지 않을까?
   - 근데 단건도 var 보다는 빈 객체로 생성해 두는게 나으려나?? 근데 컬렉션은 컬렉션이 empty 인지로 쉽게 확인이 되지만 단건 엔티티는 값을 확인해야 하니까 lateinit var 가 더 나은거 같기도 하다.

5. 잡다구리

   - h2 사용하려면 spring-boot-starter-web 이 있어야 한다;;;