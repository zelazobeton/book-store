create table authorities (id bigint not null auto_increment, authority varchar(255), user_id bigint, primary key (id)) engine=InnoDB
create table cart (id bigint not null auto_increment, user_id bigint, primary key (id)) engine=InnoDB
create table cart_item (id bigint not null auto_increment, amount integer, cart_id bigint, item_id bigint, primary key (id)) engine=InnoDB
create table categories (id bigint not null auto_increment, name varchar(255), supercategory_id bigint, primary key (id)) engine=InnoDB
create table item (id bigint not null auto_increment, description_full longtext, description_short longtext, image longblob, name varchar(255), price double precision not null, primary key (id)) engine=InnoDB
create table item_category (item_id bigint not null, category_id bigint not null, primary key (item_id, category_id)) engine=InnoDB
create table review (id bigint not null auto_increment, date date, stars integer, text longtext, user_id bigint, item_id bigint, primary key (id)) engine=InnoDB
create table user (id bigint not null auto_increment, password varchar(255), username varchar(255), cart_id bigint, primary key (id)) engine=InnoDB
alter table categories add constraint UK_t8o6pivur7nn124jehx7cygw5 unique (name)
alter table authorities add constraint FKov2uw9bvmedktrvmk2qchv198 foreign key (user_id) references user (id)
alter table cart add constraint FKl70asp4l4w0jmbm1tqyofho4o foreign key (user_id) references user (id)
alter table cart_item add constraint FK1uobyhgl1wvgt1jpccia8xxs3 foreign key (cart_id) references cart (id)
alter table cart_item add constraint FKdljf497fwm1f8eb1h8t6n50u9 foreign key (item_id) references item (id)
alter table categories add constraint FK94vfu4xtq7oo5dce4n6ccyy6t foreign key (supercategory_id) references categories (id)
alter table item_category add constraint FKqvbfcyp35qkwxoif4sbpdb9us foreign key (category_id) references categories (id)
alter table item_category add constraint FKjlvge1dms1hf66yi8dkard983 foreign key (item_id) references item (id)
alter table review add constraint FKiyf57dy48lyiftdrf7y87rnxi foreign key (user_id) references user (id)
alter table review add constraint FK6hb6qqehnsm7mvfgv37m66hd7 foreign key (item_id) references item (id)
alter table user add constraint FKtqa69bib34k2c0jhe7afqsao6 foreign key (cart_id) references cart (id)
