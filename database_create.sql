create table address (id bigint generated by default as identity, city varchar(255), name varchar(255), number varchar(255), postal_code varchar(255), street varchar(255), surname varchar(255), user_id bigint, user_order_id bigint, primary key (id))
create table authorities (id bigint generated by default as identity, authority varchar(255), user_id bigint, primary key (id))
create table cart (id bigint generated by default as identity, user_id bigint, primary key (id))
create table cart_item (id bigint generated by default as identity, amount integer, item_id bigint, cart_id bigint, primary key (id))
create table categories (id bigint generated by default as identity, name varchar(255), supercategory_id bigint, primary key (id))
create table item (id bigint generated by default as identity, description_full clob, description_short clob, image blob, name varchar(255), price double not null, primary key (id))
create table item_category (item_id bigint not null, category_id bigint not null, primary key (item_id, category_id))
create table order_item (id bigint generated by default as identity, amount integer, item_id bigint, user_order_id bigint, primary key (id))
create table review (id bigint generated by default as identity, date date, stars integer, text clob, item_id bigint, user_id bigint, primary key (id))
create table user (id bigint generated by default as identity, password varchar(255), username varchar(255), cart_id bigint, primary key (id))
create table user_order (id bigint generated by default as identity, address_id bigint, user_id bigint, primary key (id))
alter table categories add constraint UK_t8o6pivur7nn124jehx7cygw5 unique (name)
alter table address add constraint FKda8tuywtf0gb6sedwk7la1pgi foreign key (user_id) references user
alter table address add constraint FKaxuguqtcnc74oy60kgn1cubm6 foreign key (user_order_id) references user_order
alter table authorities add constraint FKov2uw9bvmedktrvmk2qchv198 foreign key (user_id) references user
alter table cart add constraint FKl70asp4l4w0jmbm1tqyofho4o foreign key (user_id) references user
alter table cart_item add constraint FKdljf497fwm1f8eb1h8t6n50u9 foreign key (item_id) references item
alter table cart_item add constraint FK1uobyhgl1wvgt1jpccia8xxs3 foreign key (cart_id) references cart
alter table categories add constraint FK94vfu4xtq7oo5dce4n6ccyy6t foreign key (supercategory_id) references categories
alter table item_category add constraint FKqvbfcyp35qkwxoif4sbpdb9us foreign key (category_id) references categories
alter table item_category add constraint FKjlvge1dms1hf66yi8dkard983 foreign key (item_id) references item
alter table order_item add constraint FKija6hjjiit8dprnmvtvgdp6ru foreign key (item_id) references item
alter table order_item add constraint FKo4rnv9xtlww92em3wbd3xk3gx foreign key (user_order_id) references user_order
alter table review add constraint FK6hb6qqehnsm7mvfgv37m66hd7 foreign key (item_id) references item
alter table review add constraint FKiyf57dy48lyiftdrf7y87rnxi foreign key (user_id) references user
alter table user add constraint FKtqa69bib34k2c0jhe7afqsao6 foreign key (cart_id) references cart
alter table user_order add constraint FKlguxu9qa64s58y51e1371sxks foreign key (address_id) references address
alter table user_order add constraint FKj86u1x7csa8yd68ql2y1ibrou foreign key (user_id) references user
