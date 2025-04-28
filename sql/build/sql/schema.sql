drop schema if exists zhul_erp;
create schema zhul_erp default character set utf8mb4 collate utf8mb4_general_ci;
use zhul_erp;

-- ----------------------------
-- Table structure for resource
-- ----------------------------
DROP TABLE IF EXISTS `resource`;
CREATE TABLE `resource`
(
    `id`                  int(11)      NOT NULL AUTO_INCREMENT COMMENT '主键',
    `pid`                 int(11)      NOT NULL DEFAULT 0 COMMENT '父ID',
    `code`                varchar(14)  NOT NULL DEFAULT '' COMMENT '资源编码（RS+类型+主键，形如RS100000）',
    `name`                varchar(32)  NOT NULL DEFAULT '' COMMENT '资源名称',
    `type`                tinyint(2)   NOT NULL DEFAULT 0 COMMENT '资源类型(1-目录、2-菜单、3-按钮)',
    `sort`                int(11)      NOT NULL DEFAULT 0 COMMENT '排序',
    `light_icon`          varchar(164) NOT NULL DEFAULT '' COMMENT '浅色默认图标地址',
    `light_selected_icon` varchar(164) NOT NULL DEFAULT '' COMMENT '浅色选中状态图标地址',
    `dark_icon`           varchar(164) NOT NULL DEFAULT '' COMMENT '深色默认图标地址',
    `dark_selected_icon`  varchar(164) NOT NULL DEFAULT '' COMMENT '深色选中状态图标地址',
    `path`                varchar(164) NOT NULL DEFAULT '' COMMENT '路径',
    `status`              tinyint(2)   NOT NULL DEFAULT '1' COMMENT '状态（0-禁用、1-启用）',
    `micro_app`           varchar(32)  NOT NULL DEFAULT '' COMMENT '微应用标识（字典）',
    `create_time`         datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`           varchar(32)  NOT NULL DEFAULT 'sys' COMMENT '创建人',
    `update_time`         datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`           varchar(32)  NOT NULL DEFAULT 'sys' COMMENT '更新人',
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT=100000 DEFAULT CHARSET=utf8mb4 COMMENT ='资源表';

-- ----------------------------
-- Table structure for role
-- ----------------------------
drop table if exists `role`;
create table role
(
    id               int auto_increment comment '主键'
        primary key,
    code             varchar(12)  default ''                not null comment '角色编码',
    name             varchar(32)  default ''                not null comment '角色名称',
    permission_scope tinyint(1)   default 0                 not null comment '数据权限范围(0-无权限、1-全部数据、2-自定义数据、3-仅本人数据）',
    status           tinyint(1)   default 1                 not null comment '状态（0-禁用、1-启用）',
    remark           varchar(128) default ''                not null comment '备注',
    create_time      datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    create_by        varchar(32)  default 'sys'             not null comment '创建者',
    update_time      datetime     default CURRENT_TIMESTAMP not null comment '更新时间',
    update_by        varchar(32)  default 'sys'             not null comment '更新者'
) ENGINE = InnoDB AUTO_INCREMENT=100 DEFAULT CHARSET=utf8mb4 COMMENT ='角色表';

-- ----------------------------
-- Table structure for role_resource
-- ----------------------------
drop table if exists `role_resource`;
create table role_resource
(
    id            int auto_increment comment '主键'
        primary key,
    role_code     varchar(12) default ''                not null comment '角色编码',
    resource_id   int         default 0                 not null comment '资源编号',
    resource_code varchar(14) default ''                not null comment '资源编码',
    create_time   datetime    default CURRENT_TIMESTAMP not null comment '创建时间',
    create_by     varchar(32) default 'sys'             not null comment '创建人',
    update_time   datetime    default CURRENT_TIMESTAMP not null comment '更新时间',
    update_by     varchar(32) default 'sys'             not null comment '更新人'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色资源表';

-- ----------------------------
-- Table structure for role_org
-- ----------------------------
DROP TABLE IF EXISTS `role_org`;
CREATE TABLE `role_org`
(
    `id`          int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
    `role_code`   varchar(12) NOT NULL DEFAULT '' COMMENT '角色编码',
    `org_code`    varchar(12) NOT NULL DEFAULT '' COMMENT '机构编码',
    `create_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   varchar(32) NOT NULL DEFAULT 'sys' COMMENT '创建人',
    `update_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`   varchar(32) NOT NULL DEFAULT 'sys' COMMENT '更新人',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='角色机构表';

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`
(
    `id`          int(11)     NOT NULL AUTO_INCREMENT COMMENT '主键',
    `pid`         int(11)     NOT NULL DEFAULT 0 COMMENT '上级部门ID',
    `code`        varchar(13) NOT NULL DEFAULT '' COMMENT '部门编码（DP+主键，形如DP10000）',
    `name`        varchar(32) NOT NULL DEFAULT '' COMMENT '部门名称',
    `all_name`    varchar(32) NOT NULL DEFAULT '' COMMENT '部门全称',
    `leader_id`   int(11)     NOT NULL DEFAULT '0' COMMENT '部门负责人ID',
    `level`       tinyint(2)  NOT NULL DEFAULT '0' COMMENT '所处层级',
    `headcount`   int(11)     NOT NULL DEFAULT '0' COMMENT '当前部门人数统计',
    `sort`        int(11)     NOT NULL DEFAULT 0 COMMENT '排序',
    `status`      tinyint(2)  NOT NULL DEFAULT '1' COMMENT '状态（0-禁用、1-启用）',
    `create_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   varchar(32) NOT NULL DEFAULT 'sys' COMMENT '创建人',
    `update_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`   varchar(32) NOT NULL DEFAULT 'sys' COMMENT '更新人',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000 DEFAULT CHARSET=utf8mb4 COMMENT='部门表';

-- ----------------------------
-- Table structure for user_basic
-- ----------------------------
drop table if exists `user_basic`;
create table user_basic
(
    id          int auto_increment comment '主键'
        primary key,
    pid         int          default 0                 not null comment '上级领导ID',
    name        varchar(16)  default ''                not null comment '用户姓名',
    type        tinyint      default 1                 not null comment '用户类型（1-员工）',
    username    varchar(20)  default ''                not null comment '用户名',
    phone       varchar(16)  default ''                not null comment '手机号',
    dept_id     int          default 0                 not null comment '部门编号',
    role_code   varchar(12)  default ''                not null comment '角色编码',
    email       varchar(100) default ''                not null comment '邮箱地址',
    avatar_url  varchar(164) default ''                not null comment '头像地址',
    nickname    varchar(64)  default ''                not null comment '昵称',
    status      tinyint      default 1                 not null comment '状态（0-禁用、1-启用）',
    create_time datetime     default CURRENT_TIMESTAMP not null comment '创建时间',
    create_by   varchar(32)  default 'sys'             not null comment '创建人',
    update_time datetime     default CURRENT_TIMESTAMP not null comment '更新时间',
    update_by   varchar(32)  default 'sys'             not null comment '更新人'
) ENGINE=InnoDB AUTO_INCREMENT=1000000000 DEFAULT CHARSET=utf8mb4 COMMENT='用户基本信息表';

-- ----------------------------
-- Table structure for user_department
-- ----------------------------
DROP TABLE IF EXISTS `user_department`;
CREATE TABLE `user_department`
(
    `id`             int(11)       NOT NULL AUTO_INCREMENT COMMENT '主键',
    `user_id`        int(11)       NOT NULL DEFAULT 0 COMMENT '用户ID',
    `dept_code`      varchar(13)   NOT NULL DEFAULT '' COMMENT '所属部门编码',
    `create_time`    datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`      varchar(32)   NOT NULL DEFAULT 'sys' COMMENT '创建人',
    `update_time`    datetime      NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`      varchar(32)   NOT NULL DEFAULT 'sys' COMMENT '更新人',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='用户部门表';

-- ----------------------------
-- Table structure for account
-- ----------------------------
DROP TABLE IF EXISTS `account`;
CREATE TABLE `account`
(
    `id`               int(11)      NOT NULL AUTO_INCREMENT COMMENT '账号ID',
    `user_id`          int(11)      NOT NULL DEFAULT '0' COMMENT '用户ID',
    `username`         varchar(20)  NOT NULL DEFAULT '' COMMENT '用户名',
    `phone`            varchar(16)  NOT NULL DEFAULT '' COMMENT '手机号',
    `email`            varchar(100) NOT NULL DEFAULT '' COMMENT '邮箱地址',
    `admin_flag`       tinyint(2)   NOT NULL DEFAULT '0' COMMENT '管理员标记（0-否、1-是）',
    `last_login_time`  varchar(19)  NOT NULL DEFAULT '' COMMENT '最近登录时间',
    `last_logout_time` varchar(19)  NOT NULL DEFAULT '' COMMENT '最后登出时间',
    `login_status`     tinyint(2)   NOT NULL DEFAULT '0' COMMENT '当前登录状态（0-登出、1-登录）',
    `status`           tinyint(2)   NOT NULL DEFAULT '1' COMMENT '状态（0-禁用、1-启用）',
    `create_time`      datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`        varchar(32)  NOT NULL DEFAULT 'sys' COMMENT '创建人',
    `update_time`      datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`        varchar(32)  NOT NULL DEFAULT 'sys' COMMENT '更新人',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000000 DEFAULT CHARSET=utf8mb4 COMMENT='账号表';

-- ----------------------------
-- Table structure for account_local_auth
-- ----------------------------
DROP TABLE IF EXISTS `account_local_auth`;
CREATE TABLE `account_local_auth`
(
    `id`          int(11)      NOT NULL AUTO_INCREMENT COMMENT '主键',
    `account_id`  int(11)      NOT NULL DEFAULT 0 COMMENT '账号ID',
    `username`    varchar(20)  NOT NULL DEFAULT '' COMMENT '用户名（冗余）',
    `password`    varchar(128) NOT NULL DEFAULT '' COMMENT '密码',
    `salt`        varchar(32)  NOT NULL DEFAULT '' COMMENT '盐（用户加密混淆）',
    `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   varchar(32)  NOT NULL DEFAULT 'sys' COMMENT '创建人',
    `update_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`   varchar(32)  NOT NULL DEFAULT 'sys' COMMENT '更新人',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='账号本地认证表';

-- ----------------------------
-- Table structure for account_access_token
-- ----------------------------
drop table if exists `account_access_token`;
create table account_access_token
(
    id           int auto_increment comment '主键'
        primary key,
    user_id      int          default 0                     not null comment '用户ID',
    account_id   int          default 0                     not null comment '账号ID',
    access_token varchar(128) default ''                    not null comment '认证Token',
    status       tinyint      default 1                     not null comment '状态(0-失效、1-有效)',
    expiry_time  datetime     default '2199-01-01 00:00:00' not null comment '到期时间',
    create_time  datetime     default CURRENT_TIMESTAMP     not null comment '创建时间',
    create_by    varchar(32)  default 'sys'                 not null comment '创建人',
    update_time  datetime     default CURRENT_TIMESTAMP     not null comment '更新时间',
    update_by    varchar(32)  default 'sys'                 not null comment '更新人'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='账号访问Token表';

-- ----------------------------
-- Table structure for account_role
-- ----------------------------
DROP TABLE IF EXISTS `account_role`;
CREATE TABLE `account_role`
(
    `id`          int(11)     NOT NULL AUTO_INCREMENT COMMENT '主键',
    `account_id`  int(11)     NOT NULL DEFAULT 0 COMMENT '账号ID',
    `role_code`   varchar(12) NOT NULL DEFAULT '' COMMENT '角色编码',
    `create_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   varchar(32) NOT NULL DEFAULT 'sys' COMMENT '创建人',
    `update_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`   varchar(32) NOT NULL DEFAULT 'sys' COMMENT '更新人',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='账号角色表';

-- ----------------------------
-- Table structure for account_org
-- ----------------------------
DROP TABLE IF EXISTS `account_org`;
CREATE TABLE `account_org`
(
    `id`          int(11)     NOT NULL AUTO_INCREMENT COMMENT '主键',
    `account_id`  int(11)     NOT NULL DEFAULT 0 COMMENT '账号ID',
    `org_code`   varchar(12) NOT NULL DEFAULT '' COMMENT '机构编码',
    `create_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   varchar(32) NOT NULL DEFAULT 'sys' COMMENT '创建人',
    `update_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`   varchar(32) NOT NULL DEFAULT 'sys' COMMENT '更新人',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='账号机构表';

-- ----------------------------
-- Table structure for chinese_area_code
-- ----------------------------
DROP TABLE IF EXISTS `chinese_area_code`;
CREATE TABLE `chinese_area_code`
(
    `id`          bigint(20)  NOT NULL DEFAULT '0' COMMENT '行政区编码',
    `pid`         bigint(20)  NOT NULL DEFAULT '0' COMMENT '父编码',
    `name`        varchar(64) NOT NULL DEFAULT '' COMMENT '名称',
    `type`        tinyint(4)  NOT NULL DEFAULT '1' COMMENT '类型（1-省级、2-市(地级)、3-区县(市级)、4-乡镇(乡级)、5-街道/村(村级)）',
    `status`      tinyint(4)  NOT NULL DEFAULT '1' COMMENT '状态（0-禁用、1-启用）',
    `create_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   varchar(32) NOT NULL DEFAULT 'sys' COMMENT '创建人',
    `update_time` datetime    NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`   varchar(32) NOT NULL DEFAULT 'sys' COMMENT '更新人',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='中国行政区编码表';

-- ----------------------------
-- Table structure for dict
-- ----------------------------
DROP TABLE IF EXISTS `dict`;
CREATE TABLE `dict`
(
    `id`          int(11)      NOT NULL AUTO_INCREMENT COMMENT '主键',
    `pid`         int(11)      NOT NULL DEFAULT '0' COMMENT '父ID',
    `type`        tinyint(2)   NOT NULL DEFAULT '1' COMMENT '类型（1-字典、2-子目录、3-字典项）',
    `code`        varchar(32)  NOT NULL DEFAULT '' COMMENT '编码',
    `name`        varchar(64)  NOT NULL DEFAULT '' COMMENT '名称',
    `value`       varchar(64)  NOT NULL DEFAULT '' COMMENT '字典值',
    `sort`        int(11)      NOT NULL DEFAULT '0' COMMENT '排序',
    `remark`      varchar(256) NOT NULL DEFAULT '' COMMENT '备注',
    `status`      tinyint(2)   NOT NULL DEFAULT '1' COMMENT '状态（0-禁用、1-启用）',
    `create_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`   varchar(32)  NOT NULL DEFAULT 'sys' COMMENT '创建人',
    `update_time` datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`   varchar(32)  NOT NULL DEFAULT 'sys' COMMENT '更新人',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='字典表';

-- ----------------------------
-- Table structure for sys_log
-- ----------------------------
DROP TABLE IF EXISTS `sys_log`;
CREATE TABLE `sys_log`
(
    `id`            bigint(20)   NOT NULL AUTO_INCREMENT COMMENT '主键',
    `belong_code`   varchar(16)  NOT NULL DEFAULT '' COMMENT '所属对象编码',
    `belong_name`   varchar(64)  NOT NULL DEFAULT '' COMMENT '所属对象名称',
    `type`          tinyint(2)   NOT NULL DEFAULT '0' COMMENT '操作分类（1-登录，2-操作， 9-其他）',
    `operator_code` varchar(13)  NOT NULL DEFAULT '0' COMMENT '操作人编码',
    `operator_name` varchar(16)  NOT NULL DEFAULT '' COMMENT '操作人姓名',
    `operate_time`  datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '操作时间',
    `operation`     varchar(32)  NOT NULL DEFAULT '' COMMENT '操作名称',
    `content`       text         COMMENT '操作内容',
    `result`        varchar(64)  NOT NULL DEFAULT '' COMMENT '操作结果',
    `menu`          varchar(32)  NOT NULL DEFAULT '' COMMENT '操作模块',
    `ip`            varchar(128) NOT NULL DEFAULT '' COMMENT '客户端ip地址',
    `create_time`   datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
    `create_by`     varchar(32)  NOT NULL DEFAULT 'sys' COMMENT '创建人',
    `update_time`   datetime     NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '更新时间',
    `update_by`     varchar(32)  NOT NULL DEFAULT 'sys' COMMENT '更新人',
    PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COMMENT='系统日志表';