create table currencies (
    id   bigserial   not null,
    name varchar(64) not null,
    code varchar(3)  not null,

    constraint currencies_id_pkey primary key (id),
    constraint currencies_name_unique unique (name),
    constraint currencies_code_unique unique (code)
);

insert into currencies (name, code)
values ('Belarussian Ruble', 'BYN'),
       ('US Dollar', 'USD'),
       ('Euro', 'EUR');