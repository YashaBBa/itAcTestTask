create table hibernate_sequence
(
    next_val bigint null
);

create table role
(
    id   int                         not null
        primary key,
    role varchar(45) charset utf8mb4 null
)
    charset = latin1;

create table user
(
    id         int auto_increment
        primary key,
    email      varchar(50)  null,
    name       varchar(50)  null,
    patronymic varchar(50)  null,
    surname    varchar(50)  null,
    role_id    int default 1 null,
    constraint fk_user_roles
        foreign key (role_id) references role (id)
);

create index fk_user_roles_idx
    on user (role_id);

