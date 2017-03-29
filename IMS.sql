SET character_set_client = utf8 ;  
SET character_set_connection = utf8 ;   
SET character_set_database = utf8 ;   
SET character_set_results = utf8 ;    
SET character_set_server = utf8 ;   
SET collation_connection = utf8 ;  
SET collation_database = utf8 ;   
SET collation_server = utf8 ;

create database ims;
GRANT USAGE ON *.* TO 'ims'@'%' IDENTIFIED BY '123456' WITH GRANT OPTION;
grant all privileges on ims.* to ims@'%' identified by '123456';
flush privileges;


CREATE TABLE s_user (
  id bigint(225) NOT NULL auto_increment,
  name varchar(20)NOT NULL,
  login_name varchar(80)NOT NULL,
  login_passwd varchar(80)NOT NULL,
  PRIMARY KEY  (`id`)
);

CREATE TABLE s_role (
  id bigint(225) NOT NULL auto_increment,
  name varchar(20)NOT NULL,
  descption varchar(80)NOT NULL,
  PRIMARY KEY  (`id`)
);


CREATE TABLE s_resource (
  id bigint(225) NOT NULL auto_increment,
  name varchar(20)NOT NULL,
  url varchar(200)NOT NULL,
  parent_id bigint(225),
  level int,
  order_id int,
  PRIMARY KEY  (`id`)
);

insert into s_resource(name,url,parent_id,level,order_id) values('用户管理','','0','1','1');


CREATE TABLE s_roleright (
  role_id bigint(225) NOT NULL,
  resource_id bigint(225) NOT NULL,
  op_flg int
);