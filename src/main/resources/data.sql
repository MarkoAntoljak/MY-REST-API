insert into user_info(id, username, birth_date)
values(10001, 'Marko', current_date());

insert into user_info(id, username, birth_date)
values(10002, 'Pero', current_date());

insert into user_info(id, username, birth_date)
values(10003, 'Joza', current_date());

insert into post(id, description, author_id)
values(234, 'This looks amazing!', 10001);

insert into post(id, description, author_id)
values(1231, 'View is soo cool!', 10002);

insert into post(id, description, author_id)
values(3234, 'Eating breakfast', 10002);