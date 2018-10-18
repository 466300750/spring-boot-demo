insert into user(account,password,name,email,phone) values ('user1','$2a$10$69uF8J5HJ2AYSIRVaALXM.162zjD7DvzGMd4QLCtY/Lstk9/vJh5O','u1','u1','u1');
insert into user(account,password,name,email,phone) values ('admin1','$2a$10$LhlZmgMyDj04g52ICzVS2eQ7oH3xuzS5Po8rh.p31q4Z0Ibwmy3ay','u2','u2','u2');
insert into user(account,password,name,email,phone) values ('admin2','$2a$10$t6QxdDqYLD4ftYFmuABxqenegx7e7qTj4gefM0Ul/qxD1uv3XwS7.','u3','u3','u3');

insert into goods_category (name) values ('c1');

insert into goods(category_id,name,price,introduce) values (1,'g1',100,'g1-intro');
insert into goods(category_id,name,price,introduce) values (1,'g2',200,'g2-intro');
insert into goods(category_id,name,price,introduce) values (1,'g3',300,'g3-intro');

insert into role(name) values ('USER');
insert into role(name) values ('ADMIN');

insert into user_in_role (uid, rid) values (1,1);
insert into user_in_role (uid, rid) values (2,2);
insert into user_in_role (uid, rid) values (3,2);

insert into orders (uid, payment, address, status) values ('1',1000,'address1',0);

insert into order_item(oid,gid,num) values (1,1,10);
insert into order_item(oid,gid,num) values (1,2,15);