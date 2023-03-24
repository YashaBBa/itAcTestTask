insert into new_schema.role (id,role) values (1,'ADMINISTRATOR');
insert into new_schema.role (id,role) values (2,'SALE_USER');
insert into new_schema.role (id,role) values (3,'CUSTOMER_USER');
insert into new_schema.role (id,role) values (4,'SECURE_API_USER');

insert into new_schema.user (id,name,surname,patronymic,email,role_id) values (1,'Yakov','Borovtsov','Vitalyevich','yashaborovcov@gmail.com',1);
insert into new_schema.user (id,name,surname,patronymic,email,role_id) values (2,'Mihail','Kozlov','Artemavich','artemix@mail.com',2);
insert into new_schema.user (id,name,surname,patronymic,email,role_id) values (3,'Nikolay','Kozlov','Sergeevich','nikolaynikolay@bsuir.by',3);
insert into new_schema.user (id,name,surname,patronymic,email,role_id) values (4,'Alexandr','Alexandrov','Vitalyevich','alexxxxx@gmail.com',4);
insert into new_schema.user (id,name,surname,patronymic,email,role_id) values (5,'Nikita','Anreevich','Karasick','bnikckarasick@gmail.com',1);
