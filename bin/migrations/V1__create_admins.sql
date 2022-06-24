create table admins(
    id int primary key auto_increment,
    mobile varchar(15) unique,
    encrypted_password varchar(191),
    salt varchar(191),
    role varchar(15),
    created_at datetime,
    updated_at datetime
)