# science-innovate

#### 项目介绍
科创管理信息系统

#### 软件架构
技术要求：
>spring boot，spring mvc，mybatis，mybatis plus

#### 安装教程

1.idea自带数据库管理工具导入方式 创建science_innovate数据库命令：
```sql
CREATE SCHEMA science_innovate DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci;
```
2.maven引入jar包
```text
选择maven插件中的install 或 mvn install
```
3.安装redis
```text
docker下拉redis镜像：
    docker pull docker.io/redis
启动容器：
    docker run -d  --name redis1  -p 6379:6379  redis --requirepass "Panda15677408298"
参数解释：
    -d : 后台运行   -name 为容器的名字    -p 端口映射   --requirepass：redis密码    redis为镜像名
远程连接：
    redis-cli -h 47.112.103.217 -p 6379 -a Panda15677408298
```
4.运行Application

#### 使用说明

1. 构建镜像： `sudo mvn clean package docker:build`
2. 部署应用： `docker run --name science-innovate-admin --privileged=true -d -p 8080:8080 -v /root/science-innovate-admin/:/home/mikey/MIKEY 90fbb84d9eb1`
3. 推到仓库： `docker push registry.cn-hangzhou.aliyuncs.com/soldier-hub/science-innovate-admin`
4. 拉取镜像： `docker pull registry.cn-hangzhou.aliyuncs.com/soldier-hub/science-innovate-admin`
5. 启动服务： `docker-compose -f docker-compose.yml -d`

#### 持续集成平台

>Jenkins

**项目结构** 
```
science-innovate-admin
├─db  项目SQL语句
│
├─common 公共模块
│  ├─aspect 系统日志
│  ├─exception 异常处理
│  ├─validator 后台校验
│  └─xss XSS过滤
│ 
├─config 配置信息
│ 
├─modules 功能模块
│  ├─business 高校教师链式业绩管理信息
│  ├─app API接口模块(APP调用)
│  ├─job 定时任务模块
│  ├─oss 文件服务模块
│  └─sys 权限模块
│ 
├─RenrenApplication 项目启动类
│  
├──resources 
│  ├─mapper SQL对应的XML文件
│  └─static 静态资源

```
<br> 

**系统结构** 
```
教师系统
├─结构划分
│  └─梧州学院               学校school
│     └─大数据与软件工程学院  学院college
│        └─软件开发中心      教研室/实验室laboratory
│
├─角色划分
│  └─超级管理员                  soldier
│     └─学校管理员               wzxyGLY
│        └─二级学院管理员         dsjxyGLY
│           └─教研室/实验室管理员  laboratory_admin(后期可能会做)
│              └─教师           teacher
│ 
├─功能划分
│  ├─教师信息 teacher_info              ok
│  ├─学术论文 academic_papers           ok
│  ├─大创项目 innovate_project          ok
│  ├─项目立项 project_create            ok
│  ├─项目结题 project_finish            ok
│  ├─科研成果 research_gain             ok
│  ├─学生竞赛 student_competition       ok
│  ├─教师竞赛 teacher_competition       ok
│  ├─教学成果 teaching_achievements     ok
│  ├─教学荣誉 teaching_honor            ok
│  ├─自编教材管理 teaching_material      ok
│  ├─系统附件 sys_attach                ok
│  ├─部门管理 sys_dept                  ok
│  ├─二级学院管理员 sys_dept_admin_info  ok
│  ├─基础数据 tb_competition_grade、tb_competition_rank、tb_gain_type、tb_match_info、tb_papers_grade、tb_project_category
│  └─中间表 tb_teacher_of_**

```
<br> 