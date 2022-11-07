-- 회원
DROP TABLE IF EXISTS `member` RESTRICT;

-- 고객센터 1:1문의
DROP TABLE IF EXISTS `cs1` RESTRICT;

-- 고객센터 공지사항
DROP TABLE IF EXISTS `cs_notice` RESTRICT;

-- 자주묻는질문
DROP TABLE IF EXISTS `qna` RESTRICT;

-- 파티원모집
DROP TABLE IF EXISTS `party` RESTRICT;

-- 참여자
DROP TABLE IF EXISTS `volunteer` RESTRICT;

-- 팔로잉/팔로우
DROP TABLE IF EXISTS `follower` RESTRICT;

-- 고객센터 첨부파일
DROP TABLE IF EXISTS `cs1_file` RESTRICT;

-- 파티원모집좋아요
DROP TABLE IF EXISTS `party_like` RESTRICT;

-- 댓글
DROP TABLE IF EXISTS `party_reply` RESTRICT;

-- 이벤트
DROP TABLE IF EXISTS `event` RESTRICT;

-- 알림
DROP TABLE IF EXISTS `alarm` RESTRICT;

-- 입장권구매
DROP TABLE IF EXISTS `ticket` RESTRICT;

-- 리뷰
DROP TABLE IF EXISTS `review` RESTRICT;

-- 신고
DROP TABLE IF EXISTS `report` RESTRICT;

-- 피드
DROP TABLE IF EXISTS `feed` RESTRICT;

-- 댓글
DROP TABLE IF EXISTS `feed_reply` RESTRICT;

-- 리뷰이미지
DROP TABLE IF EXISTS `rv_file` RESTRICT;

-- 질문유형
DROP TABLE IF EXISTS `q_state` RESTRICT;

-- 좋아요
DROP TABLE IF EXISTS `feed_like` RESTRICT;

-- 태그
DROP TABLE IF EXISTS `tag` RESTRICT;

-- 파티원모집태그
DROP TABLE IF EXISTS `partytag` RESTRICT;

-- 피드 첨부파일
DROP TABLE IF EXISTS `feed_file` RESTRICT;

-- 파티게시판 첨부파일
DROP TABLE IF EXISTS `party_file` RESTRICT;

-- 회원
CREATE TABLE `member` (
  `mno`         INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  `id`          VARCHAR(40)  NOT NULL COMMENT '아이디(이메일)', -- 아이디(이메일)
  `pwd`         VARCHAR(100) NOT NULL COMMENT '비밀번호', -- 비밀번호
  `grade`       BOOLEAN      NOT NULL COMMENT '회원등급(1:일반/2:관리자)', -- 회원등급(1:일반/2:관리자)
  `name`        VARCHAR(50)  NOT NULL COMMENT '이름', -- 이름
  `nick`        VARCHAR(20)  NOT NULL COMMENT '닉네임', -- 닉네임
  `birth`       DATE         NULL     COMMENT '생년월일', -- 생년월일
  `tel`         VARCHAR(30)  NOT NULL COMMENT '휴대폰', -- 휴대폰
  `gender`      VARCHAR(10)  NULL     COMMENT '성별', -- 성별
  `createdDate` DATE         NOT NULL COMMENT '가입일', -- 가입일
  `post`        VARCHAR(15)  NOT NULL COMMENT '우편번호', -- 우편번호
  `addr`        VARCHAR(255) NOT NULL COMMENT '기본주소', -- 기본주소
  `subaddr`     VARCHAR(255) NULL     COMMENT '상세주소', -- 상세주소
  `interest`    VARCHAR(20)  NULL     COMMENT '관심사', -- 관심사
  `outstate`    BOOLEAN      NOT NULL COMMENT '탈퇴여부(0:기존 회원/1:탈퇴한 회원)', -- 탈퇴여부(0:기존 회원/1:탈퇴한 회원)
  `outdate`     DATE         NULL     COMMENT '탈퇴일', -- 탈퇴일
  `profile`     VARCHAR(250) NULL     COMMENT '프로필사진', -- 프로필사진
  `intro`       VARCHAR(150) NULL     COMMENT '자기소개' -- 자기소개
)
COMMENT '회원';

-- 회원
ALTER TABLE `member`
  ADD CONSTRAINT `PK_member` -- 회원 기본키
    PRIMARY KEY (
      `mno` -- 회원번호
    );

ALTER TABLE `member`
  MODIFY COLUMN `mno` INTEGER NOT NULL AUTO_INCREMENT COMMENT '회원번호';

-- 고객센터 1:1문의
CREATE TABLE `cs1` (
  `cs1no`      INTEGER      NOT NULL COMMENT '문의번호', -- 문의번호
  `mno`        INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  `qsno`       INTEGER      NOT NULL COMMENT '질문유형번호', -- 질문유형번호
  `title`      VARCHAR(200) NOT NULL COMMENT '제목', -- 제목
  `cont`       VARCHAR(200) NOT NULL COMMENT '내용', -- 내용
  `createdate` DATE         NOT NULL COMMENT '작성일', -- 작성일
  `answer`     VARCHAR(200) NULL     COMMENT '답변', -- 답변
  `answerdate` DATE         NULL     COMMENT '답변일' -- 답변일
)
COMMENT '고객센터 1:1문의';

-- 고객센터 1:1문의
ALTER TABLE `cs1`
  ADD CONSTRAINT `PK_cs1` -- 고객센터 1:1문의 기본키
    PRIMARY KEY (
      `cs1no` -- 문의번호
    );

-- 고객센터 공지사항
CREATE TABLE `cs_notice` (
  `csno`       INTEGER      NOT NULL COMMENT '공지번호', -- 공지번호
  `title`      VARCHAR(200) NOT NULL COMMENT '제목', -- 제목
  `cont`       VARCHAR(200) NOT NULL COMMENT '내용', -- 내용
  `createdate` DATE         NOT NULL COMMENT '작성일', -- 작성일
  `filename`   VARCHAR(200) NULL     COMMENT '첨부파일명' -- 첨부파일명
)
COMMENT '고객센터 공지사항';

-- 고객센터 공지사항
ALTER TABLE `cs_notice`
  ADD CONSTRAINT `PK_cs_notice` -- 고객센터 공지사항 기본키
    PRIMARY KEY (
      `csno` -- 공지번호
    );

-- 자주묻는질문
CREATE TABLE `qna` (
  `qno`    INTEGER      NOT NULL COMMENT '질문번호', -- 질문번호
  `cont`   VARCHAR(200) NOT NULL COMMENT '질문내용', -- 질문내용
  `answer` VARCHAR(200) NOT NULL COMMENT '답변내용' -- 답변내용
)
COMMENT '자주묻는질문';

-- 자주묻는질문
ALTER TABLE `qna`
  ADD CONSTRAINT `PK_qna` -- 자주묻는질문 기본키
    PRIMARY KEY (
      `qno` -- 질문번호
    );

-- 파티원모집
CREATE TABLE `party` (
  `pno`        INTEGER      NOT NULL COMMENT '파티원모집번호', -- 파티원모집번호
  `mno`        INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  `meal`       VARCHAR(50)  NOT NULL COMMENT '모임시간대', -- 모임시간대
  `food`       VARCHAR(200) NOT NULL COMMENT '음식종류', -- 음식종류
  `title`      VARCHAR(200) NOT NULL COMMENT '제목', -- 제목
  `cont`       VARCHAR(200) NOT NULL COMMENT '내용', -- 내용
  `nick`       VARCHAR(50)  NOT NULL COMMENT '작성자_닉네임', -- 작성자_닉네임
  `gender`     VARCHAR(10)  NOT NULL COMMENT '성별', -- 성별
  `max`        INTEGER      NOT NULL COMMENT '최대모집인원', -- 최대모집인원
  `time`       DATETIME     NOT NULL COMMENT '시간', -- 시간
  `age`        INTEGER      NOT NULL COMMENT '연령', -- 연령
  `limit`      INTEGER      NOT NULL COMMENT '인원제한', -- 인원제한
  `loc`        VARCHAR(255) NOT NULL COMMENT '장소명', -- 장소명
  `post`       VARCHAR(15)  NOT NULL COMMENT '장소우편번호', -- 장소우편번호
  `addr`       VARCHAR(255) NOT NULL COMMENT '장소주소', -- 장소주소
  `viewcnt`    INTEGER      NOT NULL COMMENT '조회수', -- 조회수
  `img`        VARCHAR(250) NOT NULL COMMENT '대표이미지', -- 대표이미지
  `public`     BOOLEAN      NOT NULL COMMENT '공개여부', -- 공개여부
  `createdate` DATETIME     NOT NULL COMMENT '게시글 작성 시간' -- 게시글 작성 시간
)
COMMENT '파티원모집';

-- 파티원모집
ALTER TABLE `party`
  ADD CONSTRAINT `PK_party` -- 파티원모집 기본키
    PRIMARY KEY (
      `pno` -- 파티원모집번호
    );

-- 참여자
CREATE TABLE `volunteer` (
  `pno`   INTEGER      NOT NULL COMMENT '파티원모집번호', -- 파티원모집번호
  `mno`   INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  `date`  DATE         NOT NULL COMMENT '참여신청일', -- 참여신청일
  `state` BOOLEAN      NULL     COMMENT '상태(참여여부 참:1 미참:2)', -- 상태(참여여부 참:1 미참:2)
  `etc`   VARCHAR(200) NULL     COMMENT '비고(거절사유)' -- 비고(거절사유)
)
COMMENT '참여자';

-- 참여자
ALTER TABLE `volunteer`
  ADD CONSTRAINT `PK_volunteer` -- 참여자 기본키
    PRIMARY KEY (
      `pno`, -- 파티원모집번호
      `mno`  -- 회원번호
    );

-- 팔로잉/팔로우
CREATE TABLE `follower` (
  `follow`    INTEGER NOT NULL COMMENT '팔로우', -- 팔로우
  `following` INTEGER NOT NULL COMMENT '팔로잉' -- 팔로잉
)
COMMENT '팔로잉/팔로우';

-- 팔로잉/팔로우
ALTER TABLE `follower`
  ADD CONSTRAINT `PK_follower` -- 팔로잉/팔로우 기본키
    PRIMARY KEY (
      `follow`,    -- 팔로우
      `following`  -- 팔로잉
    );

-- 고객센터 첨부파일
CREATE TABLE `cs1_file` (
  `cs1fileno` INTEGER      NOT NULL COMMENT '첨부파일 번호', -- 첨부파일 번호
  `cs1no`     INTEGER      NOT NULL COMMENT '문의번호', -- 문의번호
  `filename`  VARCHAR(200) NOT NULL COMMENT '첨부파일명', -- 첨부파일명
  `path`      VARCHAR(200) NOT NULL COMMENT '파일 경로', -- 파일 경로
  `savename`  VARCHAR(200) NOT NULL COMMENT '저장파일명' -- 저장파일명
)
COMMENT '고객센터 첨부파일';

-- 고객센터 첨부파일
ALTER TABLE `cs1_file`
  ADD CONSTRAINT `PK_cs1_file` -- 고객센터 첨부파일 기본키
    PRIMARY KEY (
      `cs1fileno` -- 첨부파일 번호
    );

-- 파티원모집좋아요
CREATE TABLE `party_like` (
  `mno` INTEGER NOT NULL COMMENT '회원번호' -- 회원번호
)
COMMENT '파티원모집좋아요';

-- 파티원모집좋아요
ALTER TABLE `party_like`
  ADD CONSTRAINT `PK_party_like` -- 파티원모집좋아요 기본키
    PRIMARY KEY (
      `mno` -- 회원번호
    );

-- 댓글
CREATE TABLE `party_reply` (
  `prno`   INTEGER      NOT NULL COMMENT '댓글 번호', -- 댓글 번호
  `mno`    INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  `pno`    INTEGER      NOT NULL COMMENT '파티원모집번호', -- 파티원모집번호
  `cont`   VARCHAR(200) NULL     COMMENT '내용', -- 내용
  `date`   DATE         NULL     COMMENT '작성일', -- 작성일
  `public` BOOLEAN      NOT NULL COMMENT '공개여부' -- 공개여부
)
COMMENT '댓글';

-- 댓글
ALTER TABLE `party_reply`
  ADD CONSTRAINT `PK_party_reply` -- 댓글 기본키
    PRIMARY KEY (
      `prno` -- 댓글 번호
    );

-- 이벤트
CREATE TABLE `event` (
  `eno`     INTEGER      NOT NULL COMMENT '게시글 번호', -- 게시글 번호
  `mno`     INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  `title`   VARCHAR(200) NOT NULL COMMENT '제목', -- 제목
  `cont`    VARCHAR(200) NOT NULL COMMENT '내용', -- 내용
  `gender`  VARCHAR(10)  NULL     COMMENT '성별', -- 성별
  `date`    DATE         NOT NULL COMMENT '날짜', -- 날짜
  `loc`     VARCHAR(255) NOT NULL COMMENT '장소명', -- 장소명
  `post`    VARCHAR(15)  NOT NULL COMMENT '장소우편번호', -- 장소우편번호
  `addr`    VARCHAR(255) NOT NULL COMMENT '장소주소', -- 장소주소
  `viewcnt` INTEGER      NOT NULL COMMENT '조회수', -- 조회수
  `img`     VARCHAR(250) NOT NULL COMMENT '대표이미지', -- 대표이미지
  `public`  BOOLEAN      NOT NULL COMMENT '공개여부' -- 공개여부
)
COMMENT '이벤트';

-- 이벤트
ALTER TABLE `event`
  ADD CONSTRAINT `PK_event` -- 이벤트 기본키
    PRIMARY KEY (
      `eno` -- 게시글 번호
    );

-- 알림
CREATE TABLE `alarm` (
  `ano`      INTEGER      NOT NULL COMMENT '알림번호', -- 알림번호
  `cont`     VARCHAR(200) NOT NULL COMMENT '알림내용', -- 알림내용
  `time`     DATETIME     NOT NULL COMMENT '알림시간', -- 알림시간
  `readtime` DATETIME     NOT NULL COMMENT '읽은시간', -- 읽은시간
  `mno`      INTEGER      NOT NULL COMMENT '회원번호' -- 회원번호
)
COMMENT '알림';

-- 알림
ALTER TABLE `alarm`
  ADD CONSTRAINT `PK_alarm` -- 알림 기본키
    PRIMARY KEY (
      `ano` -- 알림번호
    );

-- 입장권구매
CREATE TABLE `ticket` (
  `tno`     INTEGER     NOT NULL COMMENT '입장권구매번호', -- 입장권구매번호
  `mno`     INTEGER     NOT NULL COMMENT '회원번호', -- 회원번호
  `eno`     INTEGER     NOT NULL COMMENT '게시글 번호', -- 게시글 번호
  `pay`     VARCHAR(30) NOT NULL COMMENT '결제수단', -- 결제수단
  `paydate` DATETIME    NOT NULL COMMENT '결제일', -- 결제일
  `paycnt`  INTEGER     NOT NULL COMMENT '결제수량', -- 결제수량
  `cancel`  BOOLEAN     NULL     COMMENT '결제취소' -- 결제취소
)
COMMENT '입장권구매';

-- 입장권구매
ALTER TABLE `ticket`
  ADD CONSTRAINT `PK_ticket` -- 입장권구매 기본키
    PRIMARY KEY (
      `tno` -- 입장권구매번호
    );

-- 리뷰
CREATE TABLE `review` (
  `tno`   INTEGER      NOT NULL COMMENT '입장권구매번호', -- 입장권구매번호
  `title` VARCHAR(200) NULL     COMMENT '제목', -- 제목
  `cont`  VARCHAR(200) NULL     COMMENT '내용', -- 내용
  `date`  DATE         NULL     COMMENT '등록일' -- 등록일
)
COMMENT '리뷰';

-- 리뷰
ALTER TABLE `review`
  ADD CONSTRAINT `PK_review` -- 리뷰 기본키
    PRIMARY KEY (
      `tno` -- 입장권구매번호
    );

-- 신고
CREATE TABLE `report` (
  `rno`     INTEGER      NOT NULL COMMENT '신고번호', -- 신고번호
  `repoter` INTEGER      NOT NULL COMMENT '고발자', -- 고발자
  `acsd`    INTEGER      NOT NULL COMMENT '피고발자', -- 피고발자
  `reason`  VARCHAR(200) NOT NULL COMMENT '신고사유', -- 신고사유
  `rcnt`    INTEGER      NOT NULL COMMENT '상태' -- 상태
)
COMMENT '신고';

-- 신고
ALTER TABLE `report`
  ADD CONSTRAINT `PK_report` -- 신고 기본키
    PRIMARY KEY (
      `rno`,     -- 신고번호
      `repoter`, -- 고발자
      `acsd`     -- 피고발자
    );

-- 피드
CREATE TABLE `feed` (
  `fno`     INTEGER      NOT NULL COMMENT '피드번호', -- 피드번호
  `mno`     INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  `title`   VARCHAR(200) NOT NULL COMMENT '제목', -- 제목
  `cont`    VARCHAR(200) NOT NULL COMMENT '내용', -- 내용
  `date`    DATE         NOT NULL COMMENT '작성일', -- 작성일
  `viewcnt` INTEGER      NOT NULL COMMENT '조회수' -- 조회수
)
COMMENT '피드';

-- 피드
ALTER TABLE `feed`
  ADD CONSTRAINT `PK_feed` -- 피드 기본키
    PRIMARY KEY (
      `fno` -- 피드번호
    );

-- 댓글
CREATE TABLE `feed_reply` (
  `frno`   INTEGER      NOT NULL COMMENT '댓글 번호', -- 댓글 번호
  `fno`    INTEGER      NOT NULL COMMENT '피드번호', -- 피드번호
  `mno`    INTEGER      NOT NULL COMMENT '회원번호', -- 회원번호
  `cont`   VARCHAR(200) NULL     COMMENT '내용', -- 내용
  `date`   DATE         NULL     COMMENT '작성일', -- 작성일
  `public` BOOLEAN      NOT NULL COMMENT '공개여부' -- 공개여부
)
COMMENT '댓글';

-- 댓글
ALTER TABLE `feed_reply`
  ADD CONSTRAINT `PK_feed_reply` -- 댓글 기본키
    PRIMARY KEY (
      `frno` -- 댓글 번호
    );

-- 리뷰이미지
CREATE TABLE `rv_file` (
  `rwfileno` INTEGER      NOT NULL COMMENT '리뷰이미지번호', -- 리뷰이미지번호
  `tno`      INTEGER      NOT NULL COMMENT '입장권구매번호', -- 입장권구매번호
  `filename` VARCHAR(200) NOT NULL COMMENT '첨부파일명', -- 첨부파일명
  `path`     VARCHAR(200) NOT NULL COMMENT '파일경로', -- 파일경로
  `savename` VARCHAR(200) NOT NULL COMMENT '저장파일명' -- 저장파일명
)
COMMENT '리뷰이미지';

-- 리뷰이미지
ALTER TABLE `rv_file`
  ADD CONSTRAINT `PK_rv_file` -- 리뷰이미지 기본키
    PRIMARY KEY (
      `rwfileno` -- 리뷰이미지번호
    );

-- 질문유형
CREATE TABLE `q_state` (
  `qsno`   INTEGER      NOT NULL COMMENT '질문유형번호', -- 질문유형번호
  `qsname` VARCHAR(255) NOT NULL COMMENT '질문유형명' -- 질문유형명
)
COMMENT '질문유형';

-- 질문유형
ALTER TABLE `q_state`
  ADD CONSTRAINT `PK_q_state` -- 질문유형 기본키
    PRIMARY KEY (
      `qsno` -- 질문유형번호
    );

-- 좋아요
CREATE TABLE `feed_like` (
  `fno` INTEGER NOT NULL COMMENT '피드번호', -- 피드번호
  `mno` INTEGER NOT NULL COMMENT '회원번호' -- 회원번호
)
COMMENT '좋아요';

-- 좋아요
ALTER TABLE `feed_like`
  ADD CONSTRAINT `PK_feed_like` -- 좋아요 기본키
    PRIMARY KEY (
      `fno`, -- 피드번호
      `mno`  -- 회원번호
    );

-- 태그
CREATE TABLE `tag` (
  `tno` INTEGER     NOT NULL COMMENT '태그번호', -- 태그번호
  `tag` VARCHAR(50) NOT NULL COMMENT '태그명' -- 태그명
)
COMMENT '태그';

-- 태그
ALTER TABLE `tag`
  ADD CONSTRAINT `PK_tag` -- 태그 기본키
    PRIMARY KEY (
      `tno` -- 태그번호
    );

-- 파티원모집태그
CREATE TABLE `partytag` (
  `pno` INTEGER NOT NULL COMMENT '파티원모집번호', -- 파티원모집번호
  `tno` INTEGER NOT NULL COMMENT '태그번호' -- 태그번호
)
COMMENT '파티원모집태그';

-- 파티원모집태그
ALTER TABLE `partytag`
  ADD CONSTRAINT `PK_partytag` -- 파티원모집태그 기본키
    PRIMARY KEY (
      `pno`, -- 파티원모집번호
      `tno`  -- 태그번호
    );

-- 피드 첨부파일
CREATE TABLE `feed_file` (
  `feedfileno` INTEGER      NOT NULL COMMENT '첨부파일 번호', -- 첨부파일 번호
  `fno`        INTEGER      NULL     COMMENT '피드번호', -- 피드번호
  `filename`   VARCHAR(200) NOT NULL COMMENT '첨부파일명', -- 첨부파일명
  `path`       VARCHAR(200) NOT NULL COMMENT '파일 경로', -- 파일 경로
  `savename`   VARCHAR(200) NOT NULL COMMENT '저장파일명' -- 저장파일명
)
COMMENT '피드 첨부파일';

-- 피드 첨부파일
ALTER TABLE `feed_file`
  ADD CONSTRAINT `PK_feed_file` -- 피드 첨부파일 기본키
    PRIMARY KEY (
      `feedfileno` -- 첨부파일 번호
    );

-- 파티게시판 첨부파일
CREATE TABLE `party_file` (
  `pno`      INTEGER      NOT NULL COMMENT '파티원모집번호', -- 파티원모집번호
  `pfileno`  INTEGER      NOT NULL COMMENT '첨부파일 번호', -- 첨부파일 번호
  `filename` VARCHAR(200) NOT NULL COMMENT '첨부파일명', -- 첨부파일명
  `path`     VARCHAR(200) NOT NULL COMMENT '파일 경로', -- 파일 경로
  `savename` VARCHAR(200) NOT NULL COMMENT '저장파일명' -- 저장파일명
)
COMMENT '파티게시판 첨부파일';

-- 파티게시판 첨부파일
ALTER TABLE `party_file`
  ADD CONSTRAINT `PK_party_file` -- 파티게시판 첨부파일 기본키
    PRIMARY KEY (
      `pfileno` -- 첨부파일 번호
    );

-- 고객센터 1:1문의
ALTER TABLE `cs1`
  ADD CONSTRAINT `FK_member_TO_cs1` -- 회원 -> 고객센터 1:1문의
    FOREIGN KEY (
      `mno` -- 회원번호
    )
    REFERENCES `member` ( -- 회원
      `mno` -- 회원번호
    );

-- 고객센터 1:1문의
ALTER TABLE `cs1`
  ADD CONSTRAINT `FK_q_state_TO_cs1` -- 질문유형 -> 고객센터 1:1문의
    FOREIGN KEY (
      `qsno` -- 질문유형번호
    )
    REFERENCES `q_state` ( -- 질문유형
      `qsno` -- 질문유형번호
    );

-- 파티원모집
ALTER TABLE `party`
  ADD CONSTRAINT `FK_member_TO_party` -- 회원 -> 파티원모집
    FOREIGN KEY (
      `mno` -- 회원번호
    )
    REFERENCES `member` ( -- 회원
      `mno` -- 회원번호
    );

-- 참여자
ALTER TABLE `volunteer`
  ADD CONSTRAINT `FK_party_TO_volunteer` -- 파티원모집 -> 참여자
    FOREIGN KEY (
      `pno` -- 파티원모집번호
    )
    REFERENCES `party` ( -- 파티원모집
      `pno` -- 파티원모집번호
    );

-- 참여자
ALTER TABLE `volunteer`
  ADD CONSTRAINT `FK_member_TO_volunteer` -- 회원 -> 참여자
    FOREIGN KEY (
      `mno` -- 회원번호
    )
    REFERENCES `member` ( -- 회원
      `mno` -- 회원번호
    );

-- 팔로잉/팔로우
ALTER TABLE `follower`
  ADD CONSTRAINT `FK_member_TO_follower` -- 회원 -> 팔로잉/팔로우
    FOREIGN KEY (
      `follow` -- 팔로우
    )
    REFERENCES `member` ( -- 회원
      `mno` -- 회원번호
    );

-- 팔로잉/팔로우
ALTER TABLE `follower`
  ADD CONSTRAINT `FK_member_TO_follower2` -- 회원 -> 팔로잉/팔로우2
    FOREIGN KEY (
      `following` -- 팔로잉
    )
    REFERENCES `member` ( -- 회원
      `mno` -- 회원번호
    );

-- 고객센터 첨부파일
ALTER TABLE `cs1_file`
  ADD CONSTRAINT `FK_cs1_TO_cs1_file` -- 고객센터 1:1문의 -> 고객센터 첨부파일
    FOREIGN KEY (
      `cs1no` -- 문의번호
    )
    REFERENCES `cs1` ( -- 고객센터 1:1문의
      `cs1no` -- 문의번호
    );

-- 파티원모집좋아요
ALTER TABLE `party_like`
  ADD CONSTRAINT `FK_member_TO_party_like` -- 회원 -> 파티원모집좋아요
    FOREIGN KEY (
      `mno` -- 회원번호
    )
    REFERENCES `member` ( -- 회원
      `mno` -- 회원번호
    );

-- 댓글
ALTER TABLE `party_reply`
  ADD CONSTRAINT `FK_member_TO_party_reply` -- 회원 -> 댓글
    FOREIGN KEY (
      `mno` -- 회원번호
    )
    REFERENCES `member` ( -- 회원
      `mno` -- 회원번호
    );

-- 댓글
ALTER TABLE `party_reply`
  ADD CONSTRAINT `FK_party_TO_party_reply` -- 파티원모집 -> 댓글
    FOREIGN KEY (
      `pno` -- 파티원모집번호
    )
    REFERENCES `party` ( -- 파티원모집
      `pno` -- 파티원모집번호
    );

-- 이벤트
ALTER TABLE `event`
  ADD CONSTRAINT `FK_member_TO_event` -- 회원 -> 이벤트
    FOREIGN KEY (
      `mno` -- 회원번호
    )
    REFERENCES `member` ( -- 회원
      `mno` -- 회원번호
    );

-- 알림
ALTER TABLE `alarm`
  ADD CONSTRAINT `FK_member_TO_alarm` -- 회원 -> 알림
    FOREIGN KEY (
      `mno` -- 회원번호
    )
    REFERENCES `member` ( -- 회원
      `mno` -- 회원번호
    );

-- 입장권구매
ALTER TABLE `ticket`
  ADD CONSTRAINT `FK_event_TO_ticket` -- 이벤트 -> 입장권구매
    FOREIGN KEY (
      `eno` -- 게시글 번호
    )
    REFERENCES `event` ( -- 이벤트
      `eno` -- 게시글 번호
    );

-- 입장권구매
ALTER TABLE `ticket`
  ADD CONSTRAINT `FK_member_TO_ticket` -- 회원 -> 입장권구매
    FOREIGN KEY (
      `mno` -- 회원번호
    )
    REFERENCES `member` ( -- 회원
      `mno` -- 회원번호
    );

-- 리뷰
ALTER TABLE `review`
  ADD CONSTRAINT `FK_ticket_TO_review` -- 입장권구매 -> 리뷰
    FOREIGN KEY (
      `tno` -- 입장권구매번호
    )
    REFERENCES `ticket` ( -- 입장권구매
      `tno` -- 입장권구매번호
    );

-- 신고
ALTER TABLE `report`
  ADD CONSTRAINT `FK_member_TO_report` -- 회원 -> 신고
    FOREIGN KEY (
      `repoter` -- 고발자
    )
    REFERENCES `member` ( -- 회원
      `mno` -- 회원번호
    );

-- 신고
ALTER TABLE `report`
  ADD CONSTRAINT `FK_member_TO_report2` -- 회원 -> 신고2
    FOREIGN KEY (
      `acsd` -- 피고발자
    )
    REFERENCES `member` ( -- 회원
      `mno` -- 회원번호
    );

-- 피드
ALTER TABLE `feed`
  ADD CONSTRAINT `FK_member_TO_feed` -- 회원 -> 피드
    FOREIGN KEY (
      `mno` -- 회원번호
    )
    REFERENCES `member` ( -- 회원
      `mno` -- 회원번호
    );

-- 댓글
ALTER TABLE `feed_reply`
  ADD CONSTRAINT `FK_feed_TO_feed_reply` -- 피드 -> 댓글
    FOREIGN KEY (
      `fno` -- 피드번호
    )
    REFERENCES `feed` ( -- 피드
      `fno` -- 피드번호
    );

-- 댓글
ALTER TABLE `feed_reply`
  ADD CONSTRAINT `FK_member_TO_feed_reply` -- 회원 -> 댓글
    FOREIGN KEY (
      `mno` -- 회원번호
    )
    REFERENCES `member` ( -- 회원
      `mno` -- 회원번호
    );

-- 리뷰이미지
ALTER TABLE `rv_file`
  ADD CONSTRAINT `FK_review_TO_rv_file` -- 리뷰 -> 리뷰이미지
    FOREIGN KEY (
      `tno` -- 입장권구매번호
    )
    REFERENCES `review` ( -- 리뷰
      `tno` -- 입장권구매번호
    );

-- 좋아요
ALTER TABLE `feed_like`
  ADD CONSTRAINT `FK_feed_TO_feed_like` -- 피드 -> 좋아요
    FOREIGN KEY (
      `fno` -- 피드번호
    )
    REFERENCES `feed` ( -- 피드
      `fno` -- 피드번호
    );

-- 좋아요
ALTER TABLE `feed_like`
  ADD CONSTRAINT `FK_member_TO_feed_like` -- 회원 -> 좋아요
    FOREIGN KEY (
      `mno` -- 회원번호
    )
    REFERENCES `member` ( -- 회원
      `mno` -- 회원번호
    );

-- 파티원모집태그
ALTER TABLE `partytag`
  ADD CONSTRAINT `FK_party_TO_partytag` -- 파티원모집 -> 파티원모집태그
    FOREIGN KEY (
      `pno` -- 파티원모집번호
    )
    REFERENCES `party` ( -- 파티원모집
      `pno` -- 파티원모집번호
    );

-- 파티원모집태그
ALTER TABLE `partytag`
  ADD CONSTRAINT `FK_tag_TO_partytag` -- 태그 -> 파티원모집태그
    FOREIGN KEY (
      `tno` -- 태그번호
    )
    REFERENCES `tag` ( -- 태그
      `tno` -- 태그번호
    );

-- 피드 첨부파일
ALTER TABLE `feed_file`
  ADD CONSTRAINT `FK_feed_TO_feed_file` -- 피드 -> 피드 첨부파일
    FOREIGN KEY (
      `fno` -- 피드번호
    )
    REFERENCES `feed` ( -- 피드
      `fno` -- 피드번호
    );

-- 파티게시판 첨부파일
ALTER TABLE `party_file`
  ADD CONSTRAINT `FK_party_TO_party_file` -- 파티원모집 -> 파티게시판 첨부파일
    FOREIGN KEY (
      `pno` -- 파티원모집번호
    )
    REFERENCES `party` ( -- 파티원모집
      `pno` -- 파티원모집번호
    );