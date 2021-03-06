create table user(
    id bigint primary key auto_increment,
    account varchar(20) not null comment '用户名',
    password varchar(128) not null comment '密码',
    name varchar(20) not null comment '姓名',
    email varchar(50) not null,
    phone varchar(11) not null comment '电话',
    createtime timestamp not null default current_timestamp comment '创建时间',
    updatetime timestamp not null default current_timestamp on update current_timestamp comment '更新时间'
)comment '用户表';

create table goods_category(
    id bigint primary key auto_increment,
    name varchar(64) not null comment '类别名称',
    createtime timestamp not null default current_timestamp comment '创建时间',
    updatetime timestamp not null default current_timestamp on update current_timestamp comment '更新时间'
) comment '商品类别';

create table goods(
    id bigint primary key auto_increment,
    category_id bigint comment '类别外键',
    name varchar(64) not null comment '商品名称',
    price decimal(8,2) not null comment '价格',
    introduce varchar(1024) not null comment '描述',
    createtime timestamp not null default current_timestamp comment '创建时间',
    updatetime timestamp not null default current_timestamp on update current_timestamp comment '更新时间'
) comment '商品表';

create table orders(
    id bigint primary key auto_increment,
    uid bigint not null comment '用户外键',
    payment decimal(8,2) not null comment '订单花费',
    address varchar(256) not null comment '地址',
    status tinyint(7) not null default 0 comment '订单状态 0-出库中，1-配送中，2-已签收,3-已拒收,4-申请退款中，5-申请退货中,6-完成',
    createtime timestamp not null default current_timestamp comment '创建时间',
    updatetime timestamp not null default current_timestamp on update current_timestamp comment '更新时间'
);

create table order_item(
    id bigint primary key auto_increment,
    oid bigint not null comment '订单外键',
    gid bigint not null comment '商品外键',
    num int not null comment '数量',
    createtime timestamp not null default current_timestamp comment '创建时间',
    updatetime timestamp not null default current_timestamp on update current_timestamp comment '更新时间'
);

create table role (
  id bigint primary key auto_increment,
  name varchar(20) not null comment '角色名称',
  createtime timestamp not null default current_timestamp comment '创建时间',
  updatetime timestamp not null default current_timestamp on update current_timestamp comment '更新时间'
);

create table user_in_role(
  id bigint primary key auto_increment,
  uid bigint not null comment '用户外键',
  rid bigint not null comment '角色外键',
  createtime timestamp not null default current_timestamp comment '创建时间',
  updatetime timestamp not null default current_timestamp on update current_timestamp comment '更新时间'
);