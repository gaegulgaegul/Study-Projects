drop table if exists account CASCADE;
drop sequence if exists hibernate_sequence;
create table account (id bigint not null, password varchar(255), username varchar(255), primary key (id));
create sequence hibernate_sequence start with 1 increment by 1;