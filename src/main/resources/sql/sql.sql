CREATE TABLE manager(
  id VARCHAR2(20) CONSTRAINT pk_id PRIMARY KEY ,
  pwd VARCHAR2(20) NOT NULL ,
  email VARCHAR2(50) NOT NULL ,
  phone char(13) NOT NULL ,
  address VARCHAR2(100) NOT NULL ,
  storename VARCHAR2(20) NOT NULL
);
select * from manager;

update manager set email = 'abc@def.com' where email = '123456';
UPDATE manager SEt phone = '01012345678' where id = 'root';

select id from manager where email = '01012345678' and phone = 'abc@def.com';


select id from manager where id = 'asdf';



select id from manager where id ='root';


select id from manager where EMAIL = 'abc@def.com' ;


create TABLE member(
mno NUMBER CONSTRAINT pk_mno PRIMARY KEY ,
name VARCHAR2(10) not null,
phone varchar2(14) not null,
cellphone VARCHAR2(14) not null,
birthdate date not null,
addr varchar2(50) not null,
email VARCHAR2(30) not null
);




UPDATE member set name = '개개개', phone ='99999999' where mno = 1;

select * from member;

insert into member VALUES (1,'개나리','031-962-4893','010-5045-4893','1990-9-20','경기도 고양시', 'taekung92@naver.com');

insert into book VALUES ('9788996094029','sdfsdf','sdfsdf','sdfsdf','sdfsdf');

CREATE TABLE book(
  bno VARCHAR2(30) CONSTRAINT pk_bno PRIMARY KEY ,
  bname VARCHAR2(80) NOT NULL ,
  genre VARCHAR2(30) NOT NULL ,
  author VARCHAR2(30) NOT NULL ,
  publisher VARCHAR2(40) NOT NULL,
  bimg VARCHAR2(30) DEFAULT 'x.png'

);
ALTER table book modify bname VARCHAR2(50) not null;

CREATE table rent (
  rno NUMBER CONSTRAINT pk_rno PRIMARY KEY ,
  mno NUMBER CONSTRAINT fk_mno REFERENCES member(mno) ,
  bno VARCHAR2(30) CONSTRAINT fk_bno REFERENCES book(bno)  ,
  regdate date DEFAULT sysdate
);

SELECT * from rent;

SELECT bno, (regdate+7)FROM rent;
drop SEQUENCE mno;
drop SEQUENCE rno;

create SEQUENCE mno;
create SEQUENCE rno;

select rno,mno,bno, regdate, (regdate+7) as duedate from rent order by rno desc;

SELECT * from manager;



select rno, mno ,bno , name from rent JOIN member USING (mno) where rno = 6;

select rno, mno, bno, name ,bname from rent join member USING (mno) join book USING (bno) where rno = 6;


drop table book CASCADE CONSTRAINTs;
drop table member CASCADE CONSTRAINTs;
drop table manager CASCADE CONSTRAINTs;
drop table rent CASCADE CONSTRAINTs;