/* 관리자 */
CREATE TABLE admin (
	a_username VARCHAR2(20) NOT NULL, /* 관리자 아이디 */
	a_password VARCHAR2(60), /* 비밀번호 */
	a_irum NVARCHAR2(10), /* 이름 */
	a_enabled NUMBER(1), /* 활성화여부 */
    constraint admin_pk_a_username primary key(a_username)
);

/* 사업자회원 */
CREATE TABLE manager (
	m_num NUMBER(10) NOT NULL, /* 사업자등록번호 */
	m_username VARCHAR2(20), /* 아이디 */
	m_password VARCHAR2(60), /* 비밀번호 */
	m_email VARCHAR2(30), /* 이메일 */
	m_tel VARCHAR2(11), /* 사업자 전화번호 */
	m_account VARCHAR2(20), /* 전용계좌번호 */
	m_irum NVARCHAR2(10), /* 사업자 이름 */
	m_enabled NUMBER(1), /* 활성화 여부 */
	status NUMBER(1), /* 스테이터스 */
    constraint manager_pk_m_num primary key(m_num)
);

/* 일반회원 */
CREATE TABLE users (
	u_username VARCHAR2(20) NOT NULL, /* 아이디 */
	u_irum NVARCHAR2(10), /* 이름 */
	u_email VARCHAR2(30), /* 이메일 */
	u_tel VARCHAR2(11), /* 전화번호 */
	u_address NVARCHAR2(100), /* 주소 */
	u_password VARCHAR2(60), /* 비밀번호 */
	u_enabled NUMBER(1) NOT NULL, /* 활성화여부 */
	u_joindate DATE, /* 가입일자 */
	u_point NUMBER(10), /* 포인트 */
	u_login_fail_cnt NUMBER(1), /* 로그인실패횟수 */
	status NUMBER(1), /* 스테이터스 */
    constraint users_pk_u_username primary key(u_username)
);

/* 포인트 */
CREATE TABLE point (
	u_username VARCHAR2(20) NOT NULL, /* 아이디 */
	order_no NUMBER(10), /* 주문번호 */
	accumulation_sal NUMBER(10), /* 적립금액 */
	totalpoint NUMBER(10) /* 총포인트 */
);

/* 음식점  */
CREATE TABLE store (
	s_num NUMBER(10) NOT NULL, /* 음식점 고유번호 */
	s_sajin VARCHAR2(100), /* 매장사진 */
	s_info NVARCHAR2(500), /* 매장정보 */
	s_name NVARCHAR2(100), /* 상호명 */
	s_tel VARCHAR2(11), /* 음식점 전화번호 */
	s_address NVARCHAR2(100), /* 주소 */
	food_no NUMBER(10), /* 음식 카테고리 번호 */
	s_star_point FLOAT, /* 별점평균 */
	image VARCHAR2(100), /* 메뉴사진 */
	s_review_cnt NUMBER(10), /* 리뷰개수 */
	m_username VARCHAR2(20), /* 사업자아이디 */
    constraint store_pk_s_num primary key(s_num)
);

/* 음식 카테고리 */
CREATE TABLE foodcategory (
	food_no NUMBER(10) NOT NULL, /* 음식 카테고리 번호 */
	food_category NVARCHAR2(10), /* 음식카테고리명 */
    constraint foodcategory_pk_food_no primary key(food_no)
);

/* 문의 답변 카테고리 */
CREATE TABLE qnacategory (
	q_cano NUMBER(10) NOT NULL, /* 문의카테고리 번호 */
	q_category NVARCHAR2(60), /* 문의카테고리명 */
    constraint qnacategory_pk_q_cano primary key(q_cano)
);

/* 문의답변 */
CREATE TABLE qna (
	q_no NUMBER(10) NOT NULL, /* 문의 글번호 */
	q_title NVARCHAR2(50), /* 제목 */
	q_content CLOB, /* 내용 */
	q_write_time DATE, /* 작성시간 */
	q_iscomment NUMBER(1), /* 답변유무 */
	m_num NUMBER(10), /* 사업자등록번호 */
	q_cano NUMBER(10), /* 문의카테고리 번호 */
    constraint qna_pk_q_no primary key(q_no)
);

/* 공지사항 */
CREATE TABLE notice (
	n_no NUMBER(10) NOT NULL, /* 공지사항 글번호 */
	a_username VARCHAR2(20), /* 관리자 아이디 */
	n_title NVARCHAR2(50), /* 제목 */
	n_write_time DATE, /* 작성일 */
	n_readcnt NUMBER(7), /* 조회수 */
	content CLOB, /* 내용 */
    constraint notice_pk_n_no primary key(n_no)
);

/* 리뷰 */
CREATE TABLE review (
	r_no NUMBER(10) NOT NULL, /* 리뷰번호 */
	r_content CLOB, /* 내용 */
	r_star_point NUMBER(10), /* 별점 */
	r_write_time DATE, /* 작성시간 */
	order_no NUMBER(10), /* 주문번호 */
	r_title NVARCHAR2(50), /* 제목 */
	r_report NUMBER(10), /* 신고수 */
	u_username VARCHAR2(20), /* 아이디 */
	s_num NUMBER(10), /* 음식점 고유번호 */
    constraint review_pk_r_no primary key(r_no)
);

/* 자유게시판 */
CREATE TABLE board (
	b_no NUMBER(10) NOT NULL, /* 자유글번호 */
	b_title NVARCHAR2(50), /* 제목 */
	b_content CLOB, /* 내용 */
	b_write_time DATE, /* 작성시간 */
	b_read_cnt NUMBER(7), /* 조회수 */
	b_good_cnt NUMBER(7), /* 추천수 */
	b_bad_cnt NUMBER(7), /* 비추수 */
	b_comment_cnt NUMBER(7), /* 댓글수 */
	b_attachment_cnt NUMBER(7), /* 첨부파일갯수 */
	b_delete_comment_cnt NUMBER(7), /* 댓삭수 */
	b_cateno NUMBER(5), /* 게시판카테고리번호 */
	f_no NUMBER(10), /* 파일 번호 */
	u_username VARCHAR2(20), /* 아이디 */
    constraint board_pk_b_no primary key(b_no)
);

/* 댓글 */
CREATE TABLE comments (
	c_no NUMBER(10) NOT NULL, /* 댓글번호 */
	b_no NUMBER(10), /* 글번호 */
	c_writer NVARCHAR2(20), /* 작성자 */
	c_content CLOB, /* 내용 */
	c_write_time DATE, /* 작성시간 */
    constraint comments_pk_c_no primary key(c_no)
);

/* 권한 */
CREATE TABLE authority (
	username VARCHAR2(50) NOT NULL, /* 아이디 */
	authority NVARCHAR2(100), /* 권한이름 */
    constraint authority_pk_username primary key(username)
);

/* 메뉴 */
CREATE TABLE menu (
	menuno NUMBER(10) NOT NULL, /* 메뉴번호 */
	menusal NUMBER(10), /* 가격 */
	menusajin VARCHAR2(100), /* 사진 */
	menuname NVARCHAR2(30), /* 이름 */
	s_num NUMBER(10), /* 음식점 고유번호 */
    constraint menu_pk_menuno primary key(menuno)
);

/* 즐겨찾기 */
CREATE TABLE favorites (
	u_username VARCHAR2(20), /* 아이디 */
	s_num NUMBER(10) /* 음식점 고유번호 */
);

/* 리뷰답글 */
CREATE TABLE reviewcomment (
	rc_no NUMBER(10) NOT NULL, /* 리뷰답글번호 */
	r_no NUMBER(10), /* 리뷰번호 */
	m_num NUMBER(10), /* 사업자등록번호 */
	rc_content CLOB, /* 답글내용 */
	rc_date_time DATE, /* 답글시간 */
	m_username VARCHAR2(20), /* 사업자이름 */
    constraint reviewcomment_pk_rc_no primary key(rc_no)
);

/* 문의답변댓글 */
CREATE TABLE qnacomment (
	qc_no NUMBER(10) NOT NULL, /* 문의댓글 글번호 */
	q_no NUMBER(10), /* 문의 글번호 */
	a_username VARCHAR2(20), /* 관리자 아이디 */
	qc_content CLOB, /* 댓글내용 */
	qc_write_time DATE, /* 댓글시간 */
    constraint qnacomment_pk_qc_no primary key(qc_no)
);

/* 자유게시판 카테고리 */
CREATE TABLE boardcate (
	b_cateno NUMBER(5) NOT NULL, /* 게시판카테고리번호 */
	b_category NVARCHAR2(50), /* 카테고리목록 */
    constraint boardcate_pk_b_cateno primary key(b_cateno)
);

/* 중복확인 */
CREATE TABLE user_board (
	username NVARCHAR2(20), /* 아이디 */
	bno NUMBER(10) /* 글번호 */
);

/* 신고 */
CREATE TABLE manager_board (
	username NVARCHAR2(20) NOT NULL, /* 사업자아이디 */
	r_no NUMBER(10) /* 리뷰번호 */
);

/* 주문상세 */
CREATE TABLE orders (
	cart_price NUMBER(10), /* 메뉴가격 */
	cart_count NUMBER(10), /* 메뉴수량 */
	cart_name NVARCHAR2(30), /* 메뉴이름 */
	cart_day DATE, /* 주문일 */
	image  VARCHAR2(100), /* 메뉴사진 */
	 cart_jumunMoney NUMBER(10), /* 메뉴총금액 */
	menuno NUMBER(10), /* 메뉴번호 */
	u_username VARCHAR2(20), /* 아이디 */
	s_num NUMBER(10), /* 음식점 고유번호 */
	order_no NUMBER(10)/* 주문번호 */
);

/* 임시주문 */
CREATE TABLE cart (
	order_no NUMBER(10) NOT NULL, /* 주문번호 */
	u_username VARCHAR2(20), /* 아이디 */
	menuno NUMBER(10), /* 메뉴번호 */
	cart_name NVARCHAR2(30), /* 메뉴이름 */
	cart_price NUMBER(10), /* 메뉴가격 */
	cart_count NUMBER(10), /* 메뉴수량 */
	cart_day DATE, /* 주문일 */
	cart_jumunMoney NUMBER(10), /* 메뉴총금액 */
	s_num NUMBER(10), /* 음식점 고유번호 */
	image  VARCHAR2(100) /* 메뉴사진 */
);
create SEQUENCE QNA_SEQ MINVALUE 0;
create SEQUENCE QNACOMMENT_SEQ MINVALUE 0;
create SEQUENCE STORE_SEQ MINVALUE 0;
create SEQUENCE MENU_SEQ MINVALUE 0;
create SEQUENCE CART_SEQ MINVALUE 0;
create SEQUENCE REVIEW_SEQ MINVALUE 0;
create SEQUENCE REVIEWCOMMENT_SEQ MINVALUE 0;
create SEQUENCE BOARD_SEQ MINVALUE 0;
create SEQUENCE NOTICE_SEQ MINVALUE 0;
create SEQUENCE COMMENTS_SEQ MINVALUE 0;
select QNA_SEQ.nextval from dual;
select QNACOMMENT_SEQ.nextval from dual;
select STORE_SEQ.nextval from dual;
select MENU_SEQ.nextval from dual;
select REVIEW_SEQ.nextval from dual;
select REVIEWCOMMENT_SEQ.nextval from dual;
select BOARD_SEQ.nextval from dual;
select CART_SEQ.nextval from dual;
select NOTICE_SEQ.nextval from dual;
select COMMENTS_SEQ.nextval from dual;
SELECT
    *
FROM foodcategory;
SELECT
    *
FROM qnacategory;
SELECT
    *
FROM boardcate;
insert into foodcategory VALUES(1,'분식');
insert into foodcategory VALUES(2,'중국집');
insert into foodcategory VALUES(3,'치킨');
insert into foodcategory VALUES(4,'한식');
insert into foodcategory VALUES(5,'족발/보쌈');
insert into foodcategory VALUES(6,'피자/양식');
insert into foodcategory VALUES(7,'일식/돈까스');
insert into foodcategory VALUES(8,'카페/디저트');
insert into foodcategory VALUES(9,'프렌차이즈');
insert into qnacategory VALUES(1,'문의');
insert into qnacategory VALUES(2,'신고');
insert into qnacategory VALUES(3,'기타');
insert into boardcate VALUES(1,'맛집추천');
insert into boardcate VALUES(2,'나만의 레시피');
SELECT 'DROP TABLE "' || TABLE_NAME || '" CASCADE CONSTRAINTS;' FROM user_tables;
commit;
drop SEQUENCE QNA_SEQ;
drop SEQUENCE CART_SEQ;
drop SEQUENCE QNACOMMENT_SEQ;
drop SEQUENCE STORE_SEQ;
drop SEQUENCE MENU_SEQ;
drop SEQUENCE REVIEW_SEQ;
drop SEQUENCE REVIEWCOMMENT_SEQ;
drop SEQUENCE BOARD_SEQ;
drop SEQUENCE NOTICE_SEQ;
--DROP TABLE "MANAGER_BOARD" CASCADE CONSTRAINTS;
--DROP TABLE "QNACATEGORY" CASCADE CONSTRAINTS;
--DROP TABLE "QNA" CASCADE CONSTRAINTS;
--DROP TABLE "CART" CASCADE CONSTRAINTS;
--DROP TABLE "REVIEW" CASCADE CONSTRAINTS;
--DROP TABLE "BOARD" CASCADE CONSTRAINTS;
--DROP TABLE "COMMENTS" CASCADE CONSTRAINTS;
--DROP TABLE "BOARDCATE" CASCADE CONSTRAINTS;
--DROP TABLE "USER_BOARD" CASCADE CONSTRAINTS;
--DROP TABLE "ORDERS" CASCADE CONSTRAINTS;
--DROP TABLE "NOTICE" CASCADE CONSTRAINTS;
--DROP TABLE "ADMIN" CASCADE CONSTRAINTS;
--DROP TABLE "MANAGER" CASCADE CONSTRAINTS;
--DROP TABLE "USERS" CASCADE CONSTRAINTS;
--DROP TABLE "POINT" CASCADE CONSTRAINTS;
--DROP TABLE "STORE" CASCADE CONSTRAINTS;
--DROP TABLE "FOODCATEGORY" CASCADE CONSTRAINTS;
--DROP TABLE "AUTHORITY" CASCADE CONSTRAINTS;
--DROP TABLE "MENU" CASCADE CONSTRAINTS;
--DROP TABLE "FAVORITES" CASCADE CONSTRAINTS;
--DROP TABLE "REVIEWCOMMENT" CASCADE CONSTRAINTS;
--DROP TABLE "QNACOMMENT" CASCADE CONSTRAINTS;
--CHEAT	QNA_SEQ
--CHEAT	QNACOMMENT_SEQ
--CHEAT	STORE_SEQ
--CHEAT	MENU_SEQ
--CHEAT	CART_SEQ
--CHEAT	REVIEW_SEQ
--CHEAT	REVIEWCOMMENT_SEQ
--CHEAT	BOARD_SEQ
--CHEAT	NOTICE_SEQ