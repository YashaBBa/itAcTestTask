create table hibernate_sequence
(
    next_val bigint null
);

create table user
(
    id         bigint auto_increment
        primary key,
    email      varchar(50)                                                             null,
    name       varchar(50)                                                             null,
    patronymic varchar(50)                                                             null,
    surname    varchar(50)                                                             null,
    role       enum ('ADMINISTRATOR', 'SALE_USER', 'CUSTOMER_USER', 'SECURE_API_USER') null
);

