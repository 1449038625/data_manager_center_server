# 用户中心

## 项目准备
### 需求分析
1. 用户登录和注册
2. 用户信息管理(仅管理员可见): 管理员可以对用户信息进行查询或者修改
### 技术选型
#### 前端
- HTML + CSS + JavaScript
- React
- Ant Design 组件库
- Umi 开发框架
- Ant Design Pro （现成的管理系统）
#### 后端：
- Java
- Spring + SpringMVC +SpringBoot
- MyBatis + MyBatis-Plus
- MySql 数据库
- JUnit 单元测试
#### 部署：服务器/容器（平台）

## 项目实施
### 初始化项目
#### 前端初始化

#### 后端初始化
1. 在idea中通过Spring-initial 创建SpringBoot项目
2. 安装MySql数据库
3. 修改配置文件
4. 测试运行

### 开发阶段
#### 前端开发

#### 后端开发
1. 创建数据库和用户表 
```sql
-- 创建库
CREATE database if not exists yupi;

-- 切换库
use yupi;

# 用户表
create table if not exists user
(
    username     varchar(256)                       null comment '用户昵称',
    id           bigint auto_increment comment 'id'
        primary key,
    userAccount  varchar(256)                       null comment '账号',
    avatarUrl    varchar(1024)                      null comment '用户头像',
    gender       tinyint                            null comment '性别',
    userPassword varchar(512)                       not null comment '密码',
    phone        varchar(128)                       null comment '电话',
    email        varchar(512)                       null comment '邮箱',
    userStatus   int      default 0                 not null comment '状态 0 - 正常',
    createTime   datetime default CURRENT_TIMESTAMP null comment '创建时间',
    updateTime   datetime default CURRENT_TIMESTAMP null on update CURRENT_TIMESTAMP,
    isDelete     tinyint  default 0                 not null comment '是否删除',
    userRole     int      default 0                 not null comment '用户角色 0 - 普通用户 1 - 管理员',
    planetCode   varchar(512)                       null comment '星球编号'
)
    comment '用户';

# 导入示例用户
INSERT INTO yupi.user (username, userAccount, avatarUrl, gender, userPassword, phone, email, userStatus, createTime, updateTime, isDelete, userRole, planetCode) VALUES ('鱼皮', 'yupi', 'https://himg.bdimg.com/sys/portraitn/item/public.1.e137c1ac.yS1WqOXfSWEasOYJ2-0pvQ', null, 'b0dd3697a192885d7c055db46155b26a', null, null, 0, '2023-08-06 14:14:22', '2023-08-06 14:39:37', 0, 1, '1');

```
2. 安装MyBatis-Plus依赖
3. 测试Mybatis-Plus框架正确与否
4. 完成注册和登录功能后端接口并测试成功
##### 注册功能实现
- 接收参数：用户账号、密码、校验码 <br>
  请求类型：POST <br>
  请求体：JSON格式 <br>
  返回值：用户id Long类型<br>
- 逻辑<br>
  1. 检验用户账号和密码和校验码是否合法（非空，账号不小于4位，密码不小于8位，帐号不包含特殊字符，密码和校验码相同，账号不能重复）
  2. 对密码加密
  3. 插入数据库中
  4. 返回用户id
  5. 完成controller参数验证和调用
##### 登录功能实现
- 接收参数：用户账号、密码 <br>
  请求类型：POST <br>
  请求体：JSON格式 <br>
  返回值：用户信息(脱敏) <br>
- 逻辑<br>
  1. 检验用户账号和密码是否合法（非空，账号不小于4位，密码不小于8位，帐号不包含特殊字符）
  2. 检验密码是否正确
  3. 用户信息脱敏处理
  4. 记录用户的登录态（ Session ），将其存到服务器上（用后端 SpringBoot 框架封装的服务器 Tomcat 记录）
  5. 返回脱敏后的用户信息
  6. 完成controller参数验证和调用



