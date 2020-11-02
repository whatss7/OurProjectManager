[TOC]
# Models
## ApiResponse
```json
{
    "type": "string",//类型
    "message": "string"//详细信息
}
```

todo http 状态码

`type` 字段可以有以下取值（如有必要可继续增加）：

1. WrongPasswordOrUsername 用户名或密码错误
1. UserAlreadyExist 同名用户已存在
1. UserNotFound 找不到用户

## UserLoginInfo
```json
{
    "username": "string",//用户名
    "password": "string"//密码
}
```

用户登录时发送的 JSON。

## UserSignUpInfo
```json
{
    "username": "string",//用户名，必须唯一
    "nickname": "string",//昵称，可不唯一
    "password": "string"//密码
}
```

用户注册时发送的 JSON。

todo 发送密码

## User
```json
{
    "id": 5000,//用户 ID，必须唯一
    "username": "string",//用户名，必须唯一
    "nickname": "string",//昵称，可不唯一
    "createTime": "string",//用户注册时间
    "updateTime": "string",//用户信息更新时间
    "projectCount": 10//参加项目的总数
}
```

用来表示用户信息的 JSON。

注：

1. 要想获取该用户加入的仓库，需要通过 /api/users/{id}/projects。
1. 要想获取收件人为该用户的通知，需要通过 /api/users/{id}/notifications
1. 要想获取发件人为该用户的通知，todo

## Notification
```json
{
    "id": 500,//通知 ID
    "isRead": false,//是否已读
    "title": "string",//标题
    "detail": "string",//详情
    "createTime": "string",//发送时间
    //发件人
    //一个 User 对象
    "sender": {
        "id": 5000,
        "username": "string",
        "nickname": "string",
        "createTime": "string",
        "updateTime": "string",
        "projectCount": 10
    }
}
```

用来表示一条通知的 JSON。

例如，用户 A 邀请用户 B 加入某项目，则用户 B 会收到一条通知，发件人是用户 A。

## Project
```json
{
    "id": 500,//项目 ID
    "name": "string",//名称
    "createTime": "string",//创建时间
    //项目主管
    //一个 User 对象
    "owner": {
        "id": 5000,
        "username": "string",
        "nickname": "string",
        "createTime": "string",
        "updateTime": "string",
        "projectCount": 10
    },
    //项目管理员（除了主管之外的）
    //一个 User 数组
    "admins": [
        {
            "id": 5001,
            "username": "string1",
            "nickname": "string",
            "createTime": "string",
            "updateTime": "string",
            "projectCount": 10
        },
        {
            "id": 5002,
            "username": "string2",
            "nickname": "string",
            "createTime": "string",
            "updateTime": "string",
            "projectCount": 10
        },
    ]
}
```

用来表示一个项目的 JSON。

注：

1. 若要获取项目内成员列表（非主管或管理员），需要通过 /api/projects/{id}/members
1. 若要获取项目内任务列表，需要通过 /api/projects/{id}/tasks

## Task
```json
{
    "id": 200,//任务 ID
    "title": "string",//标题
    "detail": "string",//详情
    "commentNum": 10,//评论条数
    "createTime": "string",//创建时间
    "completeTime": "string",//完成时间，若未完成则为 null
    //任务的创建者
    //一个 User 对象
    "creator": {
        "id": 5000,
        "username": "string",
        "nickname": "string",
        "createTime": "string",
        "updateTime": "string",
        "projectCount": 10
    },
    //任务执行人
    //一个 User 对象
    //若未分配执行人则为 null
    "executor": {
        "id": 5001,
        "username": "string1",
        "nickname": "string",
        "createTime": "string",
        "updateTime": "string",
        "projectCount": 10
    }
}
```

## Comment
```json
{
    "id": 777,//评论 ID
    "content": "string",//内容
    "createTime": "string",//评论时间
    //评论的作者
    //一个 User 对象
    "author": {
        "id": 5000,
        "username": "string",
        "nickname": "string",
        "createTime": "string",
        "updateTime": "string",
        "projectCount": 10
    }
}
```

# 用户
## POST /api/user/login 登录
### 请求体
一个 UserLoginInfo。

```json
{
    "username": "Alice123",
    "password": "123456"
}
```

### 应答
#### 登录成功
HTTP 200 OK

返回一个字符串，这个字符串是该用户的 JSON Web Token。其形式类似下面这种。

```
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c
```

#### 密码或用户名不正确
HTTP 401 Unauthorized

返回一个 ApiResponse。

```json
{
    "status": "WrongPasswordOrUsername",
    "message": "..."
}
```

## GET /api/user/logout 登出
### 请求头
todo token 标识当前用户

### 应答
#### 登出成功
HTTP 200 OK

## POST /api/users 注册新用户
### 请求体
一个 UserSignUpInfo。

```json
{
    "username": "Alice123",//用户名，必须唯一
    "nickname": "Alice",//昵称
    "password": "123456"//密码
}
```

### 应答
#### 注册成功
HTTP 201 Created

#### 同名用户已存在
HTTP 409 Conflict

返回一个 ApiResponse。

```json
{
    "status": "UserAlreadyExist",
    "message": "..."
}
```

## GET /api/users/{username} 获取用户信息
### 应答
#### 获取用户信息成功
HTTP 200 OK

返回一个 User。

```json
{
    "id": 5000,
    "username": "Alice123",
    "nickname": "Alice",
    "createTime": "...",
    "updateTime": "...",
    "projectCount": 10//参加项目的总数
}
```

#### 用户不存在
HTTP 404 Not Found

返回一个 ApiResponse。

```json
{
    "status": "UserNotFound",
    "message": "..."
}
```

## PUT /api/users/{username} 修改用户信息
### 请求头
### 请求体
### 应答

## DELETE /api/users/{username} 注销用户
### 请求头
### 应答

##  GET /api/users/{username}/projects 获取该用户参加的项目
### 请求头
### 应答

## GET /api/users/{username}/notifications 获取该用户的通知
### 请求头
### 应答

## GET /api/users/{username}/notifications/{id} 获取该用户的某条通知
### 请求头
### 应答

## PATCH /api/users/{username}/notifications/{id} 更新通知已读状态
todo 更新通知已读状态

# 项目
## POST /api/projects 创建新项目

## GET /api/projects/{id} 获取项目信息
todo 是否加入有所不同

## PUT /api/projects/{id} 修改项目信息
## DELETE /api/projects/{id} 删除项目

## GET /api/projects/{projectId}/tasks 获取项目中所有的任务
## POST /api/projects/{projectId}/tasks 新建任务
## GET /api/projects/{projectId}/tasks/{id} 获取某个任务的信息
## PUT /api/projects/{projectId}/tasks/{id} 修改任务
## DELETE /api/projects/{projectId}/tasks/{id} 删除任务

todo
comments
task日志

全局异常处理
