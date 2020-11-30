-- 菜单
CREATE TABLE `sys_menu`
(
  `menu_id`   bigint NOT NULL AUTO_INCREMENT,
  `parent_id` bigint COMMENT '父菜单ID，一级菜单为0',
  `name`      varchar(50) COMMENT '菜单名称',
  `url`       varchar(200) COMMENT '菜单URL',
  `perms`     varchar(500) COMMENT '授权(多个用逗号分隔，如：user:list,user:create)',
  `type`      int COMMENT '类型   0：目录   1：菜单   2：按钮',
  `icon`      varchar(50) COMMENT '菜单图标',
  `order_num` int COMMENT '排序',
  PRIMARY KEY (`menu_id`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8 COMMENT ='菜单管理';

-- 系统用户
CREATE TABLE `sys_user`
(
  `user_id`        bigint      NOT NULL AUTO_INCREMENT,
  `username`       varchar(50) NOT NULL COMMENT '用户名',
  `password`       varchar(100) COMMENT '密码',
  `salt`           varchar(20) COMMENT '盐',
  `email`          varchar(100) COMMENT '邮箱',
  `mobile`         varchar(100) COMMENT '手机号',
  `status`         tinyint COMMENT '状态  0：禁用   1：正常',
  `create_user_id` bigint(20) COMMENT '创建者ID',
  `create_time`    datetime COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX (`username`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8 COMMENT ='系统用户';

-- 系统用户Token
CREATE TABLE `sys_user_token`
(
  `user_id`     bigint(20)   NOT NULL,
  `token`       varchar(100) NOT NULL COMMENT 'token',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  `update_time` datetime DEFAULT NULL COMMENT '更新时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `token` (`token`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8 COMMENT ='系统用户Token';

-- 系统验证码
CREATE TABLE `sys_captcha`
(
  `uuid`        char(36)   NOT NULL COMMENT 'uuid',
  `code`        varchar(6) NOT NULL COMMENT '验证码',
  `expire_time` datetime DEFAULT NULL COMMENT '过期时间',
  PRIMARY KEY (`uuid`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8 COMMENT ='系统验证码';

-- 角色
CREATE TABLE `sys_role`
(
  `role_id`        bigint NOT NULL AUTO_INCREMENT,
  `role_name`      varchar(100) COMMENT '角色名称',
  `remark`         varchar(100) COMMENT '备注',
  `create_user_id` bigint(20) COMMENT '创建者ID',
  `create_time`    datetime COMMENT '创建时间',
  PRIMARY KEY (`role_id`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8 COMMENT ='角色';

-- 用户与角色对应关系
CREATE TABLE `sys_user_role`
(
  `id`      bigint NOT NULL AUTO_INCREMENT,
  `user_id` bigint COMMENT '用户ID',
  `role_id` bigint COMMENT '角色ID',
  PRIMARY KEY (`id`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8 COMMENT ='用户与角色对应关系';

-- 角色与菜单对应关系
CREATE TABLE `sys_role_menu`
(
  `id`      bigint NOT NULL AUTO_INCREMENT,
  `role_id` bigint COMMENT '角色ID',
  `menu_id` bigint COMMENT '菜单ID',
  PRIMARY KEY (`id`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8 COMMENT ='角色与菜单对应关系';

-- 系统配置信息
CREATE TABLE `sys_config`
(
  `id`          bigint NOT NULL AUTO_INCREMENT,
  `param_key`   varchar(50) COMMENT 'key',
  `param_value` varchar(2000) COMMENT 'value',
  `status`      tinyint DEFAULT 1 COMMENT '状态   0：隐藏   1：显示',
  `remark`      varchar(500) COMMENT '备注',
  PRIMARY KEY (`id`),
  UNIQUE INDEX (`param_key`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8 COMMENT ='系统配置信息表';

-- 系统日志
CREATE TABLE `sys_log`
(
  `id`          bigint(20) NOT NULL AUTO_INCREMENT,
  `username`    varchar(50) COMMENT '用户名',
  `operation`   varchar(50) COMMENT '用户操作',
  `method`      varchar(200) COMMENT '请求方法',
  `params`      varchar(5000) COMMENT '请求参数',
  `time`        bigint     NOT NULL COMMENT '执行时长(毫秒)',
  `ip`          varchar(64) COMMENT 'IP地址',
  `create_date` datetime COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8 COMMENT ='系统日志';

-- 文件上传
CREATE TABLE `sys_oss`
(
  `id`          bigint(20) NOT NULL AUTO_INCREMENT,
  `url`         varchar(200) COMMENT 'URL地址',
  `create_date` datetime COMMENT '创建时间',
  PRIMARY KEY (`id`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8 COMMENT ='文件上传';

-- 用户表
CREATE TABLE `tb_user`
(
  `user_id`     bigint      NOT NULL AUTO_INCREMENT,
  `username`    varchar(50) NOT NULL COMMENT '用户名',
  `mobile`      varchar(20) NOT NULL COMMENT '手机号',
  `password`    varchar(64) COMMENT '密码',
  `create_time` datetime COMMENT '创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE INDEX (`username`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8 COMMENT ='用户';

-- 系统部门
CREATE TABLE `sys_dept`
(
  `dept_id`   bigint NOT NULL AUTO_INCREMENT,
  `parent_id` bigint COMMENT '父部门ID，一级部门(学校)为0',
  `person_id` bigint COMMENT '部门负责人ID，为教师ID',
  `name`      varchar(50) COMMENT '部门名称',
  `phone`     varchar(50) COMMENT '部门电话',
  `type`      tinyint DEFAULT 1 COMMENT '类型     0：二级学院   1：教研室/实验室',
  PRIMARY KEY (`dept_id`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8 COMMENT ='系统部门表';

-- 系统部门管理员信息
CREATE TABLE `sys_dept_admin_info`
(
  `dept_admin_id` bigint NOT NULL AUTO_INCREMENT,
  `dept_id`       bigint COMMENT '管理部门ID',
  `user_id`       bigint COMMENT '用户ID',
  PRIMARY KEY (`dept_admin_id`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8 COMMENT ='系统部门管理员信息表';

-- 教师信息
CREATE TABLE `tb_teacher_info`
(
  `teacher_id`      bigint NOT NULL AUTO_INCREMENT,
  `user_id`         bigint COMMENT '用户ID',
  `teacher_code`    varchar(255) COMMENT '工号',
  `name`            varchar(255) COMMENT '姓名',
  `sex`             varchar(255) COMMENT '性别',
  `birth_day`       timestamp default CURRENT_TIMESTAMP COMMENT '出生日期',
  `entry_time`      timestamp default CURRENT_TIMESTAMP COMMENT '入职时间',
  `img_path`        varchar(255) COMMENT '个人照片',
  `high_edu`        varchar(255) COMMENT '最高学历',
  `first_edu`       varchar(255) COMMENT '第一学历',
  `technical_post`  varchar(255) COMMENT '专业技术职称',
  `administer_post` varchar(255) COMMENT '行政职务',
  `resume`          varchar(2000) COMMENT '简历',
  `other`           varchar(2000) COMMENT '主要从事工作及研究方向',
  `create_time`     timestamp default CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`     timestamp default CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`teacher_id`),
  UNIQUE INDEX (`teacher_code`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8 COMMENT ='教师信息';

-- 教师与部门对应关系
CREATE TABLE `tb_teacher_of_dept`
(
  `id`      bigint(20) NOT NULL AUTO_INCREMENT,
  `tea_id`  bigint(20) DEFAULT NULL COMMENT '教师ID',
  `dept_id` bigint(20) DEFAULT NULL COMMENT '部门ID',
  PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8 COMMENT ='教师(部门成员)与部门对应关系';

-- 学术论文
CREATE TABLE `tb_academic_papers`
(
  `ap_id`           bigint NOT NULL AUTO_INCREMENT,
  `name`            varchar(255) COMMENT '论文名称',
  `periodical_name` varchar(255) COMMENT '发布期刊名称',
  `project_name`    varchar(255) COMMENT '归属项目名称及编号',
  `author_type`     int COMMENT '作者类型:1-第一作者或通讯作者 2-独立作者 3-其他',
  `paper_type`      int COMMENT '论文类型:1-教改类 2-科研类',
  `grade`           bigint COMMENT '论文等级:国家级等',
  `publish_time`    timestamp default CURRENT_TIMESTAMP COMMENT '发表时间',
  `create_time`     timestamp default CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`     timestamp default CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`ap_id`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8 COMMENT ='学术论文';

-- 教师-of-论文表
CREATE TABLE `tb_teacher_of_ap`
(
  `toap_id` bigint NOT NULL AUTO_INCREMENT,
  `tea_id`  bigint comment '教师id',
  `ap_id`   bigint comment '论文id',
  `type`    int comment '作者类型:1-主持人 2-成员',
  PRIMARY KEY (`toap_id`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8 comment '教师-of-论文表';

-- 学生竞赛
CREATE TABLE `tb_student_competition`
(
  `sc_id`             bigint NOT NULL AUTO_INCREMENT,
  `works_name`        varchar(255) COMMENT '作品名称',
  `entry_stu`         varchar(255) COMMENT '参赛学生',
  `grade`             int(11) COMMENT '获奖级别 国家级等',
  `rank`              int(11) COMMENT '获奖等次 一等奖等',
  `match_id`          bigint COMMENT '赛事id',
  `first_teacher_id`  bigint COMMENT '第一指导老师id',
  `second_teacher_id` bigint COMMENT '第二指导老师id',
  `prize_time`        timestamp default CURRENT_TIMESTAMP COMMENT '获奖时间',
  `create_time`       timestamp default CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`       timestamp default CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`sc_id`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8 COMMENT ='学生竞赛';

-- 指导老师-of-学生竞赛表
CREATE TABLE `tb_teacher_of_stuc`
(
  `tosc_id`  bigint NOT NULL AUTO_INCREMENT,
  `tea_id`   bigint comment '教师id',
  `sc_id`    bigint comment '学生竞赛id',
  `match_id` bigint COMMENT '赛事id',
  `type`     int comment '教师类型:1-第一指导老师 2-其它指导老师',
  PRIMARY KEY (`tosc_id`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8 comment '指导老师-of-学生竞赛表';

-- 教师竞赛
CREATE TABLE `tb_teacher_competition`
(
  `tc_id`       bigint NOT NULL AUTO_INCREMENT,
  `name`        varchar(255) COMMENT '竞赛名称',
  `grade`       int(11) COMMENT '获奖级别 国家级等',
  `rank`        int(11) COMMENT '获奖等次 一等奖等',
  `prize_time`  timestamp default CURRENT_TIMESTAMP COMMENT '获奖时间',
  `create_time` timestamp default CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp default CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`tc_id`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8 COMMENT ='教师竞赛';

-- 教师-of-教师竞赛表
CREATE TABLE `tb_teacher_of_teac`
(
  `totc_id`  bigint NOT NULL AUTO_INCREMENT,
  `tea_id`   bigint comment '教师id',
  `tc_id`    bigint comment '教师竞赛id',
  `match_id` bigint COMMENT '赛事id',
  `type`     int comment '教师类型:1-队长 2-成员',
  PRIMARY KEY (`totc_id`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8 comment '指导老师-of-教师竞赛表';

-- 教师项目立项
CREATE TABLE `tb_project_create`
(
  `pc_id`         bigint NOT NULL AUTO_INCREMENT,
  `name`          varchar(255) COMMENT '项目名称',
  `number`        varchar(255) COMMENT '项目编号',
  `type`          int(11)   DEFAULT NULL COMMENT '项目类型:1-科研类 2-教改类',
  `category`      int(11) COMMENT '项目类别：横向等',
  `level`         int(11) COMMENT '项目级别：一般项目等',
  `money`         float COMMENT '经费/万元',
  `create_time`   timestamp default CURRENT_TIMESTAMP COMMENT '立项时间',
  `start_time`    timestamp default CURRENT_TIMESTAMP COMMENT '开始时间',
  `end_time`      timestamp default CURRENT_TIMESTAMP COMMENT '结束时间',
  `is_finish`     int(11)   default 0 COMMENT '结题状态',
  `creation_time` timestamp default CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`   timestamp default CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`pc_id`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8 COMMENT ='教师项目立项';

-- 教师-of-项目立项表
CREATE TABLE `tb_teacher_of_proc`
(
  `topc_id` bigint NOT NULL AUTO_INCREMENT,
  `tea_id`  bigint comment '教师id',
  `pc_id`   bigint comment '项目立项id',
  `type`    int comment '教师类型:1-主持人 2-成员',
  PRIMARY KEY (`topc_id`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8 comment '教师-of-项目立项表';

-- 项目结题表project_finish
CREATE TABLE `tb_project_finish`
(
  `pf_id`       bigint NOT NULL AUTO_INCREMENT,
  `pc_id`       bigint comment '立项项目id',
  `tea_id`      bigint comment '结题教师id',
  `finish_time` timestamp default CURRENT_TIMESTAMP COMMENT '结题时间',
  `remarks`     varchar(255) COMMENT '备注',
  `create_time` timestamp default CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp default CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`pf_id`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8 COMMENT ='项目结题表';

-- 科研成果表
CREATE TABLE `tb_research_gain`
(
  `rg_id`       bigint NOT NULL AUTO_INCREMENT,
  `name`        varchar(255) COMMENT '成果名称',
  `entry_stu`   varchar(255) COMMENT '参与学生(可为空)',
  `award_time`  timestamp default CURRENT_TIMESTAMP COMMENT '获得时间',
  `gain`        int(11) COMMENT '成果类型',
  `grade`       int(11) COMMENT '成果级别 国家级等',
  `rank`        int(11) COMMENT '成果等次 一等奖等',
  `create_time` timestamp default CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp default CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`rg_id`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8 COMMENT ='科研成果表';

-- 教师-of-科研成果表
CREATE TABLE `tb_teacher_of_rg`
(
  `torg_id` bigint NOT NULL AUTO_INCREMENT,
  `tea_id`  bigint comment '教师id',
  `rg_id`   bigint comment '科研成果id',
  `type`    int comment '教师类型:1-主持人 2-成员',
  PRIMARY KEY (`torg_id`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8 comment '教师-of-科研成果表';

-- 大创项目表
CREATE TABLE `tb_innovate_project`
(
  `ip_id`       bigint NOT NULL AUTO_INCREMENT,
  `name`        varchar(255) COMMENT '项目名称',
  `entry_stu`   varchar(255) COMMENT '项目组成员',
  `money`       float COMMENT '经费/万元',
  `award_time`  timestamp default CURRENT_TIMESTAMP COMMENT '立项时间',
  `grade`       int(11) COMMENT '立项级别 国家级等',
  `create_time` timestamp default CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp default CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`ip_id`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8 COMMENT ='大创项目表';

-- 教师-of-大创项目表
CREATE TABLE `tb_teacher_of_ip`
(
  `toip_id` bigint NOT NULL AUTO_INCREMENT,
  `tea_id`  bigint comment '教师id',
  `ip_id`   bigint comment '大创项目id',
  `type`    int comment '教师类型:1-第一指导老师 2-第二指导老师',
  PRIMARY KEY (`toip_id`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8 comment '教师-of-大创项目表';

-- 教材表
CREATE TABLE `tb_teaching_material`
(
  `material_id`  bigint NOT NULL AUTO_INCREMENT,
  `name`         varchar(255) COMMENT '教材名称',
  `press`        varchar(255) COMMENT '出版社',
  `publish_time` varchar(255) COMMENT '出版时间',
  `remarks`      varchar(255) COMMENT '备注',
  `create_time`  timestamp default CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time`  timestamp default CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`material_id`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8 COMMENT ='教材表';

-- 教师-of-教材表
CREATE TABLE `tb_teacher_of_tm`
(
  `totm_id` bigint NOT NULL AUTO_INCREMENT,
  `tea_id`  bigint comment '教师id',
  `tm_id`   bigint comment '教材id',
  `type`    int comment '教师类型:1-主编 2-成员',
  PRIMARY KEY (`totm_id`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8 comment '教师-of-教材表';

-- 教学成果表
CREATE TABLE `tb_teaching_achievements`
(
  `ta_id`       bigint NOT NULL AUTO_INCREMENT,
  `name`        varchar(255) COMMENT '名称',
  `award_time`  timestamp default CURRENT_TIMESTAMP COMMENT '获奖时间',
  `grade`       int(11) COMMENT '获奖级别 国家级等',
  `rank`        int(11) COMMENT '获奖等次 一等奖等',
  `create_time` timestamp default CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp default CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`ta_id`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8 COMMENT ='教学成果表';

-- 教师-of-教学成果表
CREATE TABLE `tb_teacher_of_ta`
(
  `tota_id` bigint NOT NULL AUTO_INCREMENT,
  `tea_id`  bigint comment '教师id',
  `ta_id`   bigint comment '教学成果id',
  `type`    int comment '教师类型:1-主持人 2-成员',
  PRIMARY KEY (`tota_id`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8 comment '教师-of-教学成果表';

-- 教学荣誉表
CREATE TABLE `tb_teaching_honor`
(
  `th_id`       bigint NOT NULL AUTO_INCREMENT,
  `name`        varchar(255) DEFAULT NULL COMMENT '荣誉称号',
  `grant_unit`  varchar(255) DEFAULT NULL COMMENT '授予单位',
  `honor_time`  varchar(255) DEFAULT NULL COMMENT '获得时间',
  `grade`       int(11) COMMENT '授予等级 国家级等',
  `rank`        int(11) COMMENT '授予等次 一等奖等',
  `create_time` timestamp    default CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` timestamp    default CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`th_id`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8 COMMENT ='教学荣誉表';

-- 教师-of-教学荣誉表
CREATE TABLE `tb_teacher_of_th`
(
  `tota_id` bigint NOT NULL AUTO_INCREMENT,
  `tea_id`  bigint comment '教师id',
  `th_id`   bigint comment '教学荣誉id',
  `type`    int comment '教师类型:1-主持人 2-成员',
  PRIMARY KEY (`tota_id`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8 comment '教师-of-教学荣誉表';

-- 系统附件
CREATE TABLE `tb_sys_attach`
(
  attach_id   bigint        NOT NULL AUTO_INCREMENT comment '附件id',
  function_id varchar(255) comment '功能id：如论文附件=AP+paper_id',
  attach_name varchar(100) comment '附件名称',
  attach_path varchar(250) comment '附件路径',
  is_del      int default 0 not null,
  PRIMARY KEY (`attach_id`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8 COMMENT ='系统附件';

-- 基础数据
-- 1、赛事信息
CREATE TABLE `tb_match_info`
(
  `match_id`   bigint NOT NULL AUTO_INCREMENT,
  `organizer`  varchar(255) COMMENT '主办单位',
  `contractor` varchar(255) COMMENT '承办单位',
  `name`       varchar(255) COMMENT '赛事名称',
  `grade`      int(11) COMMENT '赛事等级：1-国家级 2-省级 3-校级',
  `type`       int(11) COMMENT '赛事类型：1-学生竞赛 2-教师竞赛',
  PRIMARY KEY (`match_id`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8 COMMENT ='赛事信息';
-- 2、论文等级
CREATE TABLE `tb_papers_grade`
(
  value bigint NOT NULL AUTO_INCREMENT comment 'id',
  label varchar(255) comment '论文等级',
  PRIMARY KEY (`value`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8 COMMENT ='论文等级';
-- 3、获奖级别
CREATE TABLE `tb_competition_grade`
(
  value bigint NOT NULL AUTO_INCREMENT comment 'id',
  label varchar(255) comment '获奖级别',
  PRIMARY KEY (`value`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8 COMMENT ='获奖级别';
-- 4、竞赛获奖等次
CREATE TABLE `tb_competition_rank`
(
  value bigint NOT NULL AUTO_INCREMENT comment 'id',
  label varchar(255) comment '获奖等次',
  PRIMARY KEY (`value`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8 COMMENT ='竞赛获奖等次';
-- 5、教师项目类别
CREATE TABLE `tb_project_category`
(
  value bigint NOT NULL AUTO_INCREMENT comment 'id',
  label varchar(255) comment '项目类别',
  PRIMARY KEY (`value`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8 COMMENT ='项目类别';
-- 6、教师项目级别
CREATE TABLE `tb_project_level`
(
  value bigint NOT NULL AUTO_INCREMENT comment 'id',
  label varchar(255) comment '项目级别',
  PRIMARY KEY (`value`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8 COMMENT ='项目级别';
-- 7、成果类型
CREATE TABLE `tb_gain_type`
(
  value bigint NOT NULL AUTO_INCREMENT comment 'id',
  label varchar(255) comment '成果类型',
  PRIMARY KEY (`value`)
) ENGINE = `InnoDB`
  DEFAULT CHARACTER SET utf8 COMMENT ='成果类型';


-- 初始数据
INSERT INTO `sys_user` (`user_id`, `username`, `password`, `salt`, `email`, `mobile`, `status`, `create_user_id`,
                        `create_time`)
VALUES ('1', 'admin', '9ec9750e709431dad22365cabc5c625482e574c74adaebba7dd02f1129e4ce1d', 'YzcmCZNvbXocrsz9dm8e',
        'root@renren.io', '13612345678', '1', '1', '2016-11-11 11:11:11');

INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
VALUES (1, 0, '系统管理', NULL, NULL, 0, 'system', 0);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
VALUES (2, 1, '管理员列表', 'sys/user', NULL, 1, 'admin', 1);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
VALUES (3, 1, '角色管理', 'sys/role', NULL, 1, 'role', 2);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
VALUES (4, 1, '菜单管理', 'sys/menu', NULL, 1, 'menu', 3);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
VALUES (5, 1, 'SQL监控', 'http://localhost:8080/science-innovate-admin/druid/sql.html', NULL, 1, 'sql', 4);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
VALUES (6, 1, '定时任务', 'job/schedule', NULL, 1, 'job', 5);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
VALUES (7, 6, '查看', NULL, 'sys:schedule:list,sys:schedule:info', 2, NULL, 0);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
VALUES (8, 6, '新增', NULL, 'sys:schedule:save', 2, NULL, 0);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
VALUES (9, 6, '修改', NULL, 'sys:schedule:update', 2, NULL, 0);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
VALUES (10, 6, '删除', NULL, 'sys:schedule:delete', 2, NULL, 0);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
VALUES (11, 6, '暂停', NULL, 'sys:schedule:pause', 2, NULL, 0);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
VALUES (12, 6, '恢复', NULL, 'sys:schedule:resume', 2, NULL, 0);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
VALUES (13, 6, '立即执行', NULL, 'sys:schedule:run', 2, NULL, 0);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
VALUES (14, 6, '日志列表', NULL, 'sys:schedule:log', 2, NULL, 0);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
VALUES (15, 2, '查看', NULL, 'sys:user:list,sys:user:info', 2, NULL, 0);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
VALUES (16, 2, '新增', NULL, 'sys:user:save,sys:role:select', 2, NULL, 0);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
VALUES (17, 2, '修改', NULL, 'sys:user:update,sys:role:select', 2, NULL, 0);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
VALUES (18, 2, '删除', NULL, 'sys:user:delete', 2, NULL, 0);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
VALUES (19, 3, '查看', NULL, 'sys:role:list,sys:role:info', 2, NULL, 0);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
VALUES (20, 3, '新增', NULL, 'sys:role:save,sys:menu:list', 2, NULL, 0);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
VALUES (21, 3, '修改', NULL, 'sys:role:update,sys:menu:list', 2, NULL, 0);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
VALUES (22, 3, '删除', NULL, 'sys:role:delete', 2, NULL, 0);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
VALUES (23, 4, '查看', NULL, 'sys:menu:list,sys:menu:info', 2, NULL, 0);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
VALUES (24, 4, '新增', NULL, 'sys:menu:save,sys:menu:select', 2, NULL, 0);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
VALUES (25, 4, '修改', NULL, 'sys:menu:update,sys:menu:select', 2, NULL, 0);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
VALUES (26, 4, '删除', NULL, 'sys:menu:delete', 2, NULL, 0);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
VALUES (27, 1, '参数管理', 'sys/config',
        'sys:config:list,sys:config:info,sys:config:save,sys:config:update,sys:config:delete', 1, 'config', 6);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
VALUES (29, 1, '系统日志', 'sys/log', 'sys:log:list', 1, 'log', 7);
INSERT INTO `sys_menu`(`menu_id`, `parent_id`, `name`, `url`, `perms`, `type`, `icon`, `order_num`)
VALUES (30, 1, '文件上传', 'oss/oss', 'sys:oss:all', 1, 'oss', 6);

INSERT INTO `sys_config` (`param_key`, `param_value`, `status`, `remark`)
VALUES ('CLOUD_STORAGE_CONFIG_KEY',
        '{\"aliyunAccessKeyId\":\"\",\"aliyunAccessKeySecret\":\"\",\"aliyunBucketName\":\"\",\"aliyunDomain\":\"\",\"aliyunEndPoint\":\"\",\"aliyunPrefix\":\"\",\"qcloudBucketName\":\"\",\"qcloudDomain\":\"\",\"qcloudPrefix\":\"\",\"qcloudSecretId\":\"\",\"qcloudSecretKey\":\"\",\"qiniuAccessKey\":\"NrgMfABZxWLo5B-YYSjoE8-AZ1EISdi1Z3ubLOeZ\",\"qiniuBucketName\":\"ios-app\",\"qiniuDomain\":\"http://7xqbwh.dl1.z0.glb.clouddn.com\",\"qiniuPrefix\":\"upload\",\"qiniuSecretKey\":\"uIwJHevMRWU0VLxFvgy0tAcOdGqasdtVlJkdy6vV\",\"type\":1}',
        '0', '云存储配置信息');

-- 账号：13612345678  密码：admin
INSERT INTO `tb_user` (`username`, `mobile`, `password`, `create_time`)
VALUES ('mark', '13612345678', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918',
        '2017-03-23 22:37:41');
