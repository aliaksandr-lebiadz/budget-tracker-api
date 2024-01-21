create table accounts (
    id           bigserial      not null,
    user_id      bigint         not null,
    bank_id      bigint         null,
    currency_id  bigint         not null,
    card_type_id bigint         null,
    type         varchar(4)     not null,
    card_number  varchar(16)    null,
    balance      numeric(10, 2) not null,
    name         varchar(16)    null,

    constraint accounts_id_pkey            primary key (id),
    constraint accounts_user_id_fkey       foreign key (user_id)      references users (id),
    constraint accounts_bank_id_fkey       foreign key (bank_id)      references banks (id),
    constraint accounts_currency_id_fkey   foreign key (currency_id)  references currencies (id),
    constraint accounts_card_type_id_fkey  foreign key (card_type_id) references card_types (id)
);

create unique index card_accounts_user_id_and_name_unique on accounts (user_id, name) where type = 'card';
create unique index card_accounts_user_id_and_card_number_unique on accounts (user_id, card_number) where type = 'card';
create unique index cash_accounts_user_id_and_currency_id_unique on accounts (user_id, currency_id) where type = 'cash';
