--用户表
CREATE TABLE IF NOT EXISTS `user`(
   `id` INT UNSIGNED AUTO_INCREMENT,    
   `userid` VARCHAR(100) NOT NULL,
   `username` VARCHAR(100) NOT NULL,
   `gender` CHAR(1) NOT NULL,
   `age` INT NOT NULL,
   PRIMARY KEY ( `id` )
)ENGINE=InnoDB DEFAULT CHARSET=utf8;

--用户表插入数据
insert into user(userid ,username, gender, age) values('dashu','大叔','M',18);

--当遇到 authentication plugin caching_sha2_password 错误
ALTER USER 'root'@'localhost' IDENTIFIED WITH mysql_native_password BY 'root';

--用户表增加手机号字段
ALTER TABLE user ADD mobilephone varchar(15) ;

--修改手机号
update user set mobilephone = '13800000000' where userid = '1';