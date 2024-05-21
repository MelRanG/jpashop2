# 1일차
- 컨트롤러에서 엔티티 타입으로 바로 받으면 안되고 별도의 DTO를 만들어서 사용해야한다.
  1. 컨트롤러에 @Valid로 검증로직을 넣으면 엔티티에도 @NotEmpty를 넣어야한다. 만약 해당 엔티티를 사용하는 다른 API에서는 @NotEmpty가 필요없다면? 문제가 발생한다.
     -> 응답 스펙을 맞추기 위한 로직추가
  2. name이 userName으로 바뀌는(API 스펙이 변경되는) 경우 API 스펙이 깨진다. 엔티티는 여러곳에서 사용하는건데 엔티티를 바꾸면 많은 API스펙이 깨지는 문제가 있다.
- update문을 굳이 리턴하지 않는 이유는 커맨드와 쿼리가 같이 있는 꼴로 바뀌기 때문이다. 무슨말이냐면 id를 가지고 Member를 변경했는데 Member를 리턴한다면 Member를 조회한 것 처럼 보이기 때문에 변경의 경우 id만 리턴하거나 리턴하지 않는다. 다만 Member를 리턴하면 클라이언트가 갱신된 최신 데이터를 알 수 있다는 장점이 있다.

# 2일차
- Member조회에 제네릭이 있는 이유는 클라이언트 요구에 따라 dto가 언제든지 바뀌기 때문에 한 번 감싼다.
- 지연로딩은 해당 칼럼을 호출할 때 값을 가져온다. 그 전까지는 ByteBuddy라는 프록시로 값을 감싸놓는다. xToOne 연관관계 호출시 ByteBuddy 에러가 발생하면 프록시 문제다.