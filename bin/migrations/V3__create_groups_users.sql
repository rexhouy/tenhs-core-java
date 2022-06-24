create table `groups_admins`(
    group_id int,
    admin_id int,
    foreign key (group_id) references `groups`(id),
    foreign key (admin_id) references admins(id)
)