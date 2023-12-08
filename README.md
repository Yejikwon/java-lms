🚀 2단계 - 수강신청(도메인 모델)
==========================
--------------------------

요구사항 작성
---------------------------------

- Course(과정)
    - [x] 기수 단위로 운영한다.
    - [x] 여러개의 강의를 가진다.


- Session(강의)
    - [x] 시작일과 종료일을 가진다.
    - [x] 강의커버 이미지를 가진다.
    - [x] 무료 강의와 유료 강의로 나뉜다.
        - [x] 무료 강의는 최대 수강 인원 제한이 없다.
        - [x] 유료 강의는 강의 최대 수강 인원을 초과할 수 없다.
    - [x] 강의 상태는 준비중, 모집중, 종료 3가지 상태를 가진다.


- Image(강의 커버 이미지)
    - [x] 이미지 용량은 1MB 이하여야 한다.
    - [x] 이미지 타입은 gif, jpg, jpeg, png, svg만 허용한다.
    - [x] 이미지 크기는 width 300, height 200 이상이어야 하며, 비율은 3:2 이다.


- CourseRegistration(수강 신청)
    - [x] 유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다.
    - [x] 강의 수강신청은 강의 상태가 모집중일 때만 가능하다.


- Payment(결제)
    - [x] 유료 강의의 경우 결제는 이미 완료한 것으로 가정하고 이후 과정을 구현한다.
    - [x] 결제를 완료한 결제 정보는 payments 모듈을 통해 관리되며, 결제 정보는 Payment 객체에 담겨 반한된다.

🚀 3단계 - 수강신청(DB 적용)
==========================
--------------------------

테이블
---------------------------------

- course : 과정
- course_session : 과정 강의 목록
- session : 강의
- session_student : 강의 수강생 목록
- image : 강의 커버 이미지
