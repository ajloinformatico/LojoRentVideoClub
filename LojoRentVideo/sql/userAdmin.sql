drop user if exists 'admin'@'localhost';
create user 'admin'@'localhost' identified by 'admin';
grant all privileges on instituto to 'admin'@'localhost';
flush privileges ;