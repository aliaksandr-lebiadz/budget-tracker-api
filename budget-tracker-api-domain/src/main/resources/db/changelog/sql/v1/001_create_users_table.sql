create table users (
    id       bigserial   not null,
    username varchar(32) not null,
    password varchar(72) not null,

    constraint users_id_pkey         primary key (id),
    constraint users_username_unique unique (username)
);