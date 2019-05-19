create schema gitblog collate utf8mb4_0900_ai_ci;

create table role
(
	id int(11) unsigned auto_increment
		primary key,
	role varchar(50) not null comment '角色名',
	permission varchar(50) null comment '权限',
	constraint role_role_uindex
		unique (role)
)
comment '角色表';

create table user
(
	id int(11) unsigned auto_increment
		primary key,
	username varchar(50) not null comment '用户名',
	password varchar(100) not null comment '密码',
	role_id int not null comment '关联角色表',
	ban varchar(3) default '0' not null comment '状态: 0正常, 1禁用',
	create_datetime datetime not null,
	constraint user_username_uindex
		unique (username)
)
comment '用户表';


create table role
(
    id         int(11) unsigned auto_increment
        primary key,
    role       varchar(50) not null comment '角色名',
    permission varchar(50) null comment '权限',
    constraint role_role_uindex
        unique (role)
)
    comment '角色表';


create table user
(
	id int(11) unsigned auto_increment
		primary key,
	username varchar(50) not null comment '用户名',
	password varchar(100) not null comment '密码',
	role_id int not null comment '关联角色表',
	ban varchar(3) default '0' not null comment '状态: 0正常, 1禁用',
	create_datetime datetime not null,
	constraint user_username_uindex
		unique (username)
)
comment '用户表';



INSERT INTO gitblog.role (id, role, permission) VALUES (1, 'user', 'normal');
INSERT INTO gitblog.role (id, role, permission) VALUES (2, 'admin', 'vip');
INSERT INTO gitblog.user (id, username, password, role_id, ban, create_datetime) VALUES (4, 'ckh', '0000', 2, '0', '2019-05-16 14:39:22');
INSERT INTO gitblog.user (id, username, password, role_id, ban, create_datetime) VALUES (5, 'ckh2', '0000', 1, '0', '2019-05-16 14:39:42');
<<<<<<< Updated upstream
INSERT INTO gitblog.user (id, username, password, role_id, ban, create_datetime) VALUES (6, 'ckh3', '0000', 1, '0', '2017-05-16 14:40:02');
=======
INSERT INTO gitblog.user (id, username, password, role_id, ban, create_datetime) VALUES (6, 'ckh3', '0000', 1, '0', '2017-05-16 14:40:02');

COMMIT;
>>>>>>> Stashed changes
