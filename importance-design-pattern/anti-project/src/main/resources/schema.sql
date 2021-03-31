drop table if exists account CASCADE;
create table account (id bigint not null, username varchar(255), age integer, primary key (id));