## exist reason

shardingsphere's example is too old. so add some shardingsphere 5.1.2 example

shardingsphere截止目前的最新版本

use library
- springboot 2.5.x
- shardingsphere 5.1.2
- mybatis


you can use this code online after test


## types

read-write-spite

sharding-tables

sharding-db-tables


### read-write-spite

create database db1, db2 is slave of db1

功能
1. 读写分离
2. 基于注解的强制主键

```sql

create database db1;
CREATE TABLE `user` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `age` int DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

create database db2;
CREATE TABLE `user` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `age` int DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

use db1;
insert user(id,name,age)values(1,'sank',30);

use db2;
insert user(id,name,age)values(2,'tom',13);
```

access-api
- http://127.0.0.1:7312/api/v1/user/db1/1
- http://127.0.0.1:7312/api/v1/user/db2/1


### sharding-tables

支持功能
1. 支持自定义分表算法

create database db1, db2 is slave of db1

```sql
create database db1;
CREATE TABLE `order_1` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `uid` bigint NOT NULL COMMENT 'uid',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `order_2` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `uid` bigint NOT NULL COMMENT 'uid',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE `order_3` (
  `id` bigint NOT NULL COMMENT '主键ID',
  `uid` bigint NOT NULL COMMENT 'uid',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;
```

insert some data

access-api
- http://127.0.0.1:7313/api/v1/order/uid/1
- http://127.0.0.1:7313/api/v1/order/uid/1


### sharding-db-tables

支持功能
1. 支持自定义分表算法
2. 内置算法分片


```sql
create database db3;
create table order_0
(
  id   bigint auto_increment comment '主键ID'
    primary key,
  uid  bigint      not null comment 'uid',
  name varchar(30) null comment '姓名'
);
create table order_2
(
  id   bigint auto_increment comment '主键ID'
    primary key,
  uid  bigint      not null comment 'uid',
  name varchar(30) null comment '姓名'
);

create database db4;
create table order_1
(
  id   bigint auto_increment comment '主键ID'
    primary key,
  uid  bigint      not null comment 'uid',
  name varchar(30) null comment '姓名'
);
create table order_3
(
  id   bigint auto_increment comment '主键ID'
    primary key,
  uid  bigint      not null comment 'uid',
  name varchar(30) null comment '姓名'
);

```

access-api
- http://127.0.0.1:7315/api/v1/order/uid/3





