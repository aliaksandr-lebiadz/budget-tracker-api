create table refresh_tokens (
    id       bigserial   not null,
    username varchar(32) not null,
    token    text        not null,

    constraint refresh_tokens_id_pkey         primary key (id),
    constraint refresh_tokens_username_unique unique (username)
);