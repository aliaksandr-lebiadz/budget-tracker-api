alter table refresh_tokens
    drop constraint refresh_tokens_username_unique;

alter table refresh_tokens drop column username;

delete from refresh_tokens;

alter table refresh_tokens
    add column user_id bigint not null;

alter table refresh_tokens
    add constraint refresh_tokens_user_id_fkey foreign key (user_id) references users (id);
