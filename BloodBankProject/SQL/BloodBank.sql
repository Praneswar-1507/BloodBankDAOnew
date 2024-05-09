create database BloodBank;
use BloodBank;
create table Bank(
donor_id  varchar(20) PRIMARY KEY ,
donor_name varchar(20),
blood_type varchar(20),
quantity int(10),
donatedBefore boolean
 
);
insert into Bank(donor_id,donor_name,blood_type,quantity,donatedBefore) values(1,"sachin","A+ve",1,true);
insert into Bank(donor_id,donor_name,blood_type,quantity,donatedBefore) values(2,"virat","Ab+ve",2,false);
insert into Bank(donor_id,donor_name,blood_type,quantity,donatedBefore) values(3,"kholi","A-ve",3,false);
insert into Bank(donor_id,donor_name,blood_type,quantity,donatedBefore) values(4,"gill","O-ve",4,true);
select * from Bank;
alter table Bank
drop column Noofday;
alter table Bank
drop column donatedBefore;
create table stock(
blood_type varchar(20) primary key,
quantity_available int(10)
);
insert into stock(blood_type,quantity_available)values("A+",20);
insert into stock(blood_type,quantity_available)values("B+",30);
insert into stock(blood_type,quantity_available)values("o+",40);
select * from stock;
alter table stock;
drop table  stock;
create table stock(
blood_type varchar(20) primary key,
quantity_available int(10),
admin_name varchar(10),
admin_password varchar(10)
);
insert into stock(admin_name,admin_password,blood_type,quantity_available)values("abc","abc123","A+",20);
insert into stock(blood_type,quantity_available)values("B+",30);
insert into stock(blood_type,quantity_available)values("o+",40);
select * from stock;



