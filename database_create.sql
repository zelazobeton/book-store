create table categories (id bigint not null auto_increment, name varchar(255), primary key (id)) engine=InnoDB
create table item (id bigint not null auto_increment, description longtext, name varchar(255), price double precision not null, primary key (id)) engine=InnoDB
create table item_category (item_id bigint not null, category_id bigint not null, primary key (item_id, category_id)) engine=InnoDB
alter table categories add constraint UK_t8o6pivur7nn124jehx7cygw5 unique (name)
alter table item_category add constraint FKqvbfcyp35qkwxoif4sbpdb9us foreign key (category_id) references categories (id)
alter table item_category add constraint FKjlvge1dms1hf66yi8dkard983 foreign key (item_id) references item (id)
