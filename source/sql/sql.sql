CREATE DATABASE food_reservation_db
CHARACTER SET utf8mb4
COLLATE utf8mb4_general_ci 

USE food_reservation_db

CREATE TABLE users(
 
 id BIGINT NOT NULL auto_increment PRIMARY KEY,
 `name` VARCHAR(200),
 username VARCHAR(200),
 
 created_at TIMESTAMP NULL DEFAULT(CURRENT_TIMESTAMP),
 updated_at TIMESTAMP NULL DEFAULT NULL,
 deleted_at TIMESTAMP NULL DEFAULT NULL

);

CREATE TABLE foods(

 id BIGINT NOT NULL auto_increment PRIMARY KEY,
 `name` VARCHAR(200),
 
 created_at TIMESTAMP NULL DEFAULT(CURRENT_TIMESTAMP),
 updated_at TIMESTAMP NULL DEFAULT NULL,
 deleted_at TIMESTAMP NULL DEFAULT NULL
);

CREATE TABLE orders(

 id BIGINT NOT NULL auto_increment PRIMARY KEY,
 
 user_id BIGINT,
 
 INDEX order_user_index (user_id),
 CONSTRAINT order_user_fk FOREIGN KEY (user_id) REFERENCES users(id),
 
 created_at TIMESTAMP NULL DEFAULT(CURRENT_TIMESTAMP),
 updated_at TIMESTAMP NULL DEFAULT NULL,
 deleted_at TIMESTAMP NULL DEFAULT NULL
);

CREATE TABLE orderdetails(
 
  id BIGINT NOT NULL AUTO_INCREMENT PRIMARY KEY,
 
 order_id BIGINT,
 food_id BIGINT,
 
 INDEX order_orderdetail_index (order_id),
 CONSTRAINT order_orderdetail_fk FOREIGN KEY (order_id) REFERENCES orders(id),
 
 INDEX food_orderdetail_index (food_id),
 CONSTRAINT food_orderdetail_fk FOREIGN KEY (food_id) REFERENCES foods(id)
 
);


