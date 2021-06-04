 -- 관리자 테이블
CREATE TABLE `admin` (
  `managerSeq` int NOT NULL AUTO_INCREMENT,
  `managerId` varchar(100) NOT NULL,
  `managerPw` varchar(100) NOT NULL,
  `managerName` varchar(100) NOT NULL,
  PRIMARY KEY (`managerSeq`,`managerId`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 -- 유저의 설문조사 참여 테이블(게시판)
CREATE TABLE `bbs` (
  `seq` int NOT NULL AUTO_INCREMENT,
  `content` text,
  `title` varchar(100) DEFAULT NULL,
  `last_update` datetime DEFAULT NULL,
  `user_seq` int NOT NULL,
  `boardHit` int NOT NULL,
  `responsegradle1` varchar(50) NOT NULL,
  `responsegradle2` varchar(50) NOT NULL,
  `responsegradle3` varchar(50) NOT NULL,
  `event_seq` int DEFAULT NULL,
  `event_name` varchar(45) DEFAULT NULL,
  `question1` varchar(100) DEFAULT NULL,
  `question2` varchar(100) DEFAULT NULL,
  `question3` varchar(100) DEFAULT NULL,
  `question4` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`seq`),
  KEY `bbs_ibfk_1_idx` (`event_seq`),
  CONSTRAINT `bbs_ibfk_1` FOREIGN KEY (`event_seq`) REFERENCES `event` (`seq`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 -- 장바구니 테이블
CREATE TABLE `CARTTABLE` (
  `CARTNO` int NOT NULL AUTO_INCREMENT,
  `CARTPRODUCTID` int NOT NULL,
  `CARTDATE` date NOT NULL,
  `CARTUSERID` varchar(45) NOT NULL,
  PRIMARY KEY (`CARTNO`),
  KEY `CARTPRODUCTID` (`CARTPRODUCTID`),
  KEY `carttable_ibfk_2_idx` (`CARTUSERID`),
  CONSTRAINT `carttable_ibfk_1` FOREIGN KEY (`CARTPRODUCTID`) REFERENCES `PRODUCTTABLE` (`PRODUCTNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 -- 이벤트 테이블(카테고리, 설문 설정)
CREATE TABLE `event` (
  `seq` int NOT NULL AUTO_INCREMENT,
  `event_name` varchar(45) DEFAULT NULL COMMENT '이벤트 명',
  `expired_date` date DEFAULT NULL COMMENT '이벤트 종료일자',
  `reg_date` date DEFAULT NULL COMMENT '이벤트 생성일자',
  `question1` varchar(100) DEFAULT NULL,
  `question2` varchar(100) DEFAULT NULL,
  `question3` varchar(100) DEFAULT NULL,
  `question4` varchar(100) DEFAULT NULL,
  `category` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`seq`),
  UNIQUE KEY `eventcol_UNIQUE` (`event_name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='이벤트 참여 테이블';

 -- 상품 테이블
CREATE TABLE `PRODUCTTABLE` (
  `PRODUCTNO` int NOT NULL AUTO_INCREMENT,
  `PRODUCTIMAGEFILE` varchar(500) NOT NULL,
  `PRODUCTIMAGENAME` varchar(500) NOT NULL,
  `PRODUCTIMAGEORINAME` varchar(500) NOT NULL,
  `PRODUCTIMAGEURL` varchar(500) NOT NULL,
  `PRODUCTNAME` varchar(500) NOT NULL,
  `PRODUCTPRICE` int NOT NULL,
  `PRODUCTSALESCNT` int NOT NULL,
  `PRODUCTCID` int NOT NULL,
  PRIMARY KEY (`PRODUCTNO`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 -- 댓글테이블(설문조사 답변에 사용)
CREATE TABLE `reply` (
  `reseq` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `seq` int DEFAULT NULL,
  `lastUpdate` datetime DEFAULT NULL,
  `recontent` varchar(500) DEFAULT NULL,
  `event_seq` int DEFAULT NULL,
  PRIMARY KEY (`reseq`),
  KEY `reply_ibfk_1` (`seq`),
  CONSTRAINT `reply_ibfk_1` FOREIGN KEY (`seq`) REFERENCES `bbs` (`seq`) ON DELETE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

 -- 유저테이블
CREATE TABLE `user` (
  `seq` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `last_update` datetime DEFAULT NULL,
  `password` varchar(200) NOT NULL,
  `tel` varchar(45) DEFAULT NULL,
  `id` varchar(45) DEFAULT NULL,
  `event_seq` int DEFAULT NULL,
  `zipcode` int DEFAULT NULL,
  `address01` varchar(100) DEFAULT NULL,
  `address02` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`seq`,`name`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;