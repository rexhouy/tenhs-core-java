create table users(
    id int primary key auto_increment,
    openid varchar(32),
    nickname varchar(191),
    sex varchar(10),
    province varchar(191),
    city varchar(191),
    country varchar(191),
    headimgurl varchar(191),
    privilege varchar(191),
    unionid varchar(191),
    role varchar(191)
)