create table histories(
    id int primary key auto_increment,
    user_id int,
    memo varchar(191),
    archiveable_id int,
    archiveable_type varchar(191),
    archive blob,
    created_at timestamp,
    updated_at timestamp
)