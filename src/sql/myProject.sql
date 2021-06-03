--user table
create table user_detail(user_id Serial primary Key,
name varchar(70) not null,
age int not null,gender varchar(50) not null,
mobile_number  bigint not null,
user_password varchar(70) not null,
retype_password varchar(70) not null);

--package table
create table package_detail(package_name varchar(70) not null,
package_price int not null,
number_of_days int not null,
start_date Date not null,
end_date date not null,
hotel_name varchar(70) not null);

--flight table
create table flight_detail(country_name varchar(70) not null,
flight_name varchar(70) not null,
source varchar(70) not null,
destination varchar(70) not null,
departure_time time not null,
arrival_time time not null,
status varchar(70),
journey_date date not null);

--contact detail table
create table contact_detail(name varchar(70),
mobile_number bigint not null,
country_name varchar(70),
package_price int not null,
start_date date not null,
end_date date not null);

--booking table
create table booking_detail(user_id int not null,
package_name varchar(70) not null,
package_price int not null,
number_of_days int not null,
start_date date not null,
end_date date not null,
number_of_persons int not null,
total_price float not null);

--cancelled booking table
create table cancelled _booking(user_id int not null,
package_name varchar(70) not null,
package_price int not null,
number_of_days int not null,
start_date date not null,
end_date date not null,
number_of_persons int not null,
total_price float not null,status varchar(70) default 'cancelled');


--package images table
create table image_detail(country_name varchar(70) not null,image bytea);

--hotel image table 
create table hotel_image(hotel_name varchar(70) not null,image bytea);

--Admin table
create table admin_detail(mobile_number bigint,user_password varchar(50));
insert into admin_detail(mobile_number,user_password)values(9865940407,'Admin123');--use this mobile_number and password for login


