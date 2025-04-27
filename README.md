# 한 줄 리뷰
한 줄 리뷰는 한 두줄의 짧은 독서 평을 위한 웹 사이트입니다. 

문학 동아리 부원들과 함께 독서 내용을 나누면 좋을 것 같아 만들게 되었습니다.

# 개발 환경
  spring boot 3.4.2
  JDK 21

# 사용 기술
- spring
- MyBatis

  SQL과 코드가 분리된 SQLMapper를 사용하기 위해 선정하였습니다.
- MySQL

  기본적인 CRUD db 구축에 이용하였습니다.
- Thymeleaf

  bootstrap을 이용한 간단한 UI를 구현하였습니다.

# 주요 기능
- ### 메인 페이지
<img width="700" height="350" alt="스크린샷 2025-04-27 22 24 10" src="https://github.com/user-attachments/assets/939f4dd2-5bd8-4799-97d9-8dec7de243cc" />

웹사이트에 접속 시 사용자의 리뷰를 볼 수 있습니다.

클릭 시 리뷰를 자세히 볼 수 있습니다.

최근순으로 정렬되며 한 번에 10개씩 페이지네이션 됩니다.

스크롤을 내리면 추가로 10개가 보입니다.

- ### 도서 검색
<img width="700" height="350" alt="스크린샷 2025-04-27 22 24 35" src="https://github.com/user-attachments/assets/e8755d44-a145-4f5d-81a8-4e887dd40935" />

상단 도서 검색 바를 통해 검색할 수 있습니다.

네이버 API를 기반으로 정보를 불러옵니다.

도서를 클릭 시 정보 페이지로 이동합니다.

- ### 도서 정보

<p>
  <img width="450" alt="스크린샷 2025-04-27 22 29 03" src="https://github.com/user-attachments/assets/3049aabb-9de5-4113-88a5-7991ebdd431b" />
  <img width="450" alt="스크린샷 2025-04-27 22 29 03" src="https://github.com/user-attachments/assets/59ba66c6-b8a1-4245-be3d-be60391a57f5" />
</p>
도서 정보와 도서의 리뷰를 불러옵니다. 만약 자신의 리뷰가 있으면 자신의 리뷰를 보여주며, 그렇지 않을 경우 리뷰 작성 버튼을 보여줍니다.

- ### 리뷰 상세 정보
<img  width="700" height="350" alt="스크린샷 2025-04-27 22 34 53" src="https://github.com/user-attachments/assets/31c847ac-4bf4-454e-84fb-26542654d530" />

리뷰에 대한 상세 정보를 불러옵니다.
좋아요를 누르거나 댓글을 쓸 수 있습니다.

- ### 로그인 및 회원가입
<p>
  <img width="410" height="410" alt="스크린샷 2025-04-28 00 34 00" src="https://github.com/user-attachments/assets/332a051c-0c30-492a-b649-c905476a87da" />
<img width="410"  height="410" alt="스크린샷 2025-04-28 00 34 17" src="https://github.com/user-attachments/assets/c7d72e47-8d79-4099-a111-77a1f7e89a20" />  
</p>


# Use Case
![유스케이스3](https://github.com/user-attachments/assets/61fd6554-0650-44e4-b436-62bd53ec9ccb)

# ERD
![One Review Book (2)](https://github.com/user-attachments/assets/5c9d6ef9-efb1-452a-8e27-9eb26f8b2798)



# 고민했던 점

- **게시글/댓글 유저 정보 확인**
  
  게시글을 수정하거나 삭제시 소유 유저와 요청한 유저가 일치하는지 확인하기 위하여 인터셉터를 사용하였습니다.
  
  유저 확인은 게시글, 댓글 등 여러 컨트롤러에서 일어날 수 있으므로 유지보수를 위해 따로 만들어 관리하는 것이 낫다고 판단해서였습니다.
  
  ValidateOwnership 어노테이션을 만들어 어노테이션이 존재할 경우 인터셉터를 진행하는 방식으로 했습니다.

- **예외 전역 처리 / 커스텀 예외 처리**

  예외 처리를 하는 GlobalExceptionHandler를 ControllerAdvice를 사용하여 만들었습니다.
  
  컨트롤러에서 오류가 발생 시 dispatcherServlet을 거쳐 was까지 전달된다.

  이후 에러 페이지로 다시 요청을 보내게 되는데, 다시 거쳐야 하는 작업이 많으므로 비효율적입니다.
  
  이를 해결하기 위하여 ExceptionHandler를 사용하면 dispatcherServlet까지 예외를 전달하지 않아도 됩니다.
  
  이때 예외 처리를 쉽게 하기 위해 ControllerAdvice빈에 ExceptionHandler를 만들어 관리하였습니다.
  
  언 체크 예외는 놓치는 경우가 발생할 수 있습니다.
  이를 해결하기 위해 ControllerAdvice빈을 만들어 예외 처리의 공통된 handler를 만들었습니다.
  또한 커스텀 예외를 만들어 예외를 명확히 표현하였습니다.

- **예외 로깅**
  
  예외 발생 시 어떤 예외가 발생했는지 관리하기 위하여 로깅 aop를 만들었습니다.
  
  예외에 대한 로그 기록이라는 공통된 관심사를 처리하기 위해서였습니다.

- **도서 정보 db 저장**

  수많은 책을 db에 모두 저장한다는 것은 불가능합니다.

  이를 해결하기 위해 사용자가 찾는 책만 저장하도록 하였습니다.
  
  사용자가 책을 검색하는 과정은 네이버 API를 사용하여 정보를 불러올 뿐, db에 저장하지 않았습니다.
  
  이후 **책에 대한 상세 정보를 검색할 경우 db에 저장**하였습니다.
  
- **MyBatis**

  JdbcTemplate 사용 시 코드와 SQL **쿼리가 밀접하게 결합되는 문제**가 발생하였습니다.

  이를 개선하기 위해 MyBatis를 도입하여, Mapper 인터페이스와 XML 파일을 분리 구성함으로써 **관심사를 명확히 분리**하였습니다.
  
- 페이지네이션

  리뷰가 많아지게 될 경우 목록을 불러오기까지 소요되는 자원이 많아질 것을 염려하여 페이지네이션 처리하였습니다.

  한 번에 10개씩 불러오며 스크롤을 끝까지 내릴시 다시 10개를 불러옵니다.
