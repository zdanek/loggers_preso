create table billing
(
  id bigint unsigned auto_increment,
  phone_no varchar(255) not null,
  party varchar(255) not null,
  length int not null,
  date_time int null,
  constraint id
  unique (id)
)
  engine=InnoDB
;

