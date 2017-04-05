create database ims;
GRANT USAGE ON *.* TO 'ims'@'%' IDENTIFIED BY '123456' WITH GRANT OPTION;
grant all privileges on ims.* to ims@'%' identified by '123456';
flush privileges;