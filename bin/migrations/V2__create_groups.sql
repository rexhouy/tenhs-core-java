create table `groups`(
    id int primary key auto_increment,
    name varchar(191),
    type varchar(50),
    ancestry varchar(50),
    ancestry_depth int,
    status int default 1,
    memo varchar(191),
    created_at datetime,
    updated_at datetime
)