# toy_commerce_springboot2
SNS를 이용한 결제 웹 애플리케이션

## 2020/10/06 회고록:
* 오랜만이라 간만에 ERD랑 도메인 맞는지 확인
* Comment 엔티티 클래스 및 레포지토리 및 관련 dto 및 Service 추가
* Board 에 `List<Comment>` 추가 및 생성자 수정   
* BoardResponse 클래스 추가
* BoardService `List<>`로 반환했는데 `Page<>`가 가독성 및 관련되었으므로 더 좋음
* BoardService 와 CommentService 와 Paging 처리 달라서 CommentService 맞춰서 DESC 방법 추가   
   * findAll 은 내부적으로 만든 것이라 Paging 처리 관련 쿼리는 검색해도 안나옴 -> findAll 전에 Sort Desc 로 처리   
* login 관련 html 추가
* SecurityConfig 수정
   * defaultSuccessUrl 로 넘기면 사용자 인증을 못하고 넘긴다.   
   * `defaultSuccessUrl('/main')` 으로 처리하면 `main`에서 role 처리 못함  
      * 그리고 이전에 저장을 `ROLE.GUEST`로 하고있었음; 
   * 즉, `.antMatchers("/api/v1/**", "/main/**").hasRole(Role.USER.name())` 안먹어서 안넘어감 
   * 우선 main 그대로 두고 main2로 인증을 바꿔서 처리해보니 됨 -> Controller를 거치고 가야하나보다.    
   * 한가지 안한 방법이 Controller 에 `/main` 처리하면 어떻게 될지 모름 -> 될 것 같음
* 추가적으로 MoneyConverter 가 문제가 생김
   * 스프링 서버가 재시동될 때 있는데 이미 기존에 converter가 있어서 mutiple time 으로 시간초과 에러 발생
   * 해결방법으로 모듈중복인지 검사, jar 중복인지 검사, @Converter, @Convert 혼용하는지 검사, 변환 클래스와 경로에 있나 검사   
   * 경로는 누가 위에 있는지 같은 경로인지 등등 확인해야겠다.  
* build 에서 갱신 에러가 있는 것 같던데 확인해야겠다.       
* 기타 배운점 
   * hasRole() 하면 `ROLE_`이 붙어서 처리되므로 저장할때 GUEST,USER,ADMIN 으로 저장 바람   
   * `defaultSuccessUrl()`는 직접 넘기지만 `.loginPage("/login")` 은 Controller에 해당 경로 처리가 있어야한다.   
   * `return this` 로 해서 체이닝 기법을 이용하는 방법도 좋다.   

## 2020/10/07 회고록:
* Convert 문제는 일단 https://xbuba.com/questions/52822568 참고해서 고쳤습니다.
   * 정확히 말하면 아직 에러는 발생하지 않습니다.    
   * 기존에 build 파일을 임의로 delete 하고 rub한게 문제가 되었던 것 같습니다. 
   * `rm -rf build out .gradle` 입력하여 관련 디렉토리를 모두 지움 (저는 build만 지웠었습니다)   
   * 그리고 기존 Cache가 남아있으므로 `File > Invalidate Caches / Restart`를 통해 처음부터 재시작합니다.
* defaultSuccessUrl 관련 인증 사항 고침
   * 내가 큰 착각을 했다.   
   * Controller 에 등록을 하지 않으면 해당 주소는 매핑이 되어있지 않다.  
   * 즉, Role 문제가 아니라 `/main`을 보내도 관련된 컨트롤러가 없으니 404에러가 뜬것이다.
   * 권한 문제는 403 에러가 떠야하지만 404는 그냥 해당 경로의 처리가 없다는 뜻이다.
   * 그리고 우선 로직정리부터 해야겠다.
      * OAuth2 로그인 -> 해당 정보를 가지고 User 데이터와 SessionUser 생성됨    
      * OAuth2는 OAuth2User 이용하여 UserDetails 역할을 대신하여 값을 저장하는 것 같다.       
      * Controller가 있었다면 @LoginUser 아마 작동가능
      * 어제는 main 에러 -> main2 가능 이렇게 되었는데 다시 생각해보면 main view 지정안해주니 에러났던것
      * 사실 Controller 를 만들어주자니 @LoginUser 검증을 하면 필터역할이 불필요하다 생각이들어서 생긴 문제 
      * 즉, 스프링 시큐리티에서 역할 검증 다해주는데 또 검증해야하나 이 생각이들어서 해결하려다보니 이렇게 된것 같다....
      * WebMvcConfigurer 클래스를 이용하여 뷰만 지정해줄 수 있다는 방법을 배워서 이것으로 해결
      * **그리고 무엇보다**
      * `@LoginUser`에 대해서 내가 헷갈린게 있는데 해당 어노테이션은 로그인 검증을 위한것이 아니라 **로그인 정보를 model로 사용하기 위한것이다**   
      * 즉, 필터역할이 아니라 모델값을 넣기위해 존재했다는것이로 생각하자.... 이걸 필터로 사용했어서 2중 필터로 생각되었다.    
