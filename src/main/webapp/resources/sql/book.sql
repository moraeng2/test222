create database spring2512;
use spring2512;

create table if not exists book(
	b_bookId varchar(10) not null,
    b_name varchar(30),
    b_unitPrice integer,
    b_author varchar(50),
    b_description text,
    b_publisher varchar(20),
    b_category varchar(20),
    b_unitsInStock long,
    b_releaseDate varchar(20),
    b_condition varchar(20),
    b_fileName varchar(20),
    primary key (b_bookId)
)default charset=utf8;

delete from book;

insert into book values('ISBN1234', 'C# 프로그래밍', 27000, '박용준', 'C#을 처음 접하는 독자를 대상으로 일대일 수업처럼 자세히 설명한 책이다. 꼭 알아야 할 핵심 개념은 기본 예제로 최대한 쉽게 설명했으며, 중요한 내용은 응용 예제, 퀴즈, 셀프 스터디, 예제 모음으로 한번 더 복습할 수 있다.', '길벗', 'IT전문서', 1000, '2020-05-29', '', 'ISBN1234.png');
insert into book values('ISBN1235', 'Node.js', 36000, '조현영', '자바를 처음 배우는 학생을 위해 자바의 기본 개념과 실습 예제를 그림을 이용하여 쉽게 설명합니다. 자바의 이론적 개념→기본 예제→프로젝트 순으로 단계별 학습이 가능하며, 각 챕터의 프로젝트를 실습하면서 온라인 서점을 완성할 수 있도록 구성하였습니다.', '길벗', 'IT전문서', 1000, '2020-07-25', '', 'ISBN1235.png');
insert into book values('ISBN1236', '어도비 XD CC 2020', 25000, '김두한', '파이썬으로 프로그래밍을 시작하는 입문자가 쉽게 이해할 수 있도록 기본 개념을 상세하게 설명하며, 다양한 예제를 제시합니다. 또한 프로그래밍의 기초 원리를 이해하면서 파이썬으로 데이터를 처리하는 기법도 배웁니다.', '길벗', 'IT활용서', 1000, '2019-05-29', '', 'ISBN1236.png');

select * from book;