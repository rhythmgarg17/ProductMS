drop schema productdb;
create schema productdb;
use productdb;

create table products(
prodid varchar(50) NOT NULL,
product_name varchar(100) NOT NULL,
price integer NOT NULL check (price>=200),
stock integer NOT NULL check (stock>=10),
prod_description varchar(500),
image varchar(100),
seller_id varchar(50) NOT NULL,
category varchar(50) NOT NULL,
sub_category varchar(50),
product_rating integer check (product_rating Between 0 and 10),
PRIMARY KEY (prodid)
);

insert into products values('p101','Sparx',700,200,'durable shoes','Sparx.jpeg','s104','footwear','sneakers',7);
insert into products values('p102','Bajaj MW',5000,150,'electricity efficient','Microwave.jpeg','s105','electronics','microwave',8);
insert into products values('p103','Redmi X',12000,250,'best in 12k','RedmiX.png','s106','mobiles','xiaomi',9);
insert into products values('p104','Realme X',10000,300,'best in 10k','RealMeX.png','s107','mobiles','real me',10);
insert into products values('p105','lenovo vivobook',50000,130,'nvidia Gt supportted','lenovoLaptops.jpeg','s108','electronics','laptops',8);
insert into products values('p106','denim jeans',1000,400,' blue colored striped jeans','Jeans.png','s109','clothing','jeans',9);

select * from products;

create table subscribed_products(
buyerid varchar(50) NOT NULL,
prodid varchar(50) NOT NULL,
quantity integer,
PRIMARY KEY(buyerid,prodid)
);

insert into subscribed_products values('B103','P101',5);
insert into subscribed_products values('B104','P104',5);
insert into subscribed_products values('B105','P103',4);
insert into subscribed_products values('B106','P102',5);

select * from subscribed_products;
