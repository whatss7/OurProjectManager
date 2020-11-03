- [Models](#models)
  - [ApiResponse](#apiresponse)
  - [UserLoginInfo](#userlogininfo)
  - [UserSignUpInfo](#usersignupinfo)
  - [User](#user)
  - [Notification](#notification)
  - [Project](#project)
  - [Role](#role)
  - [Task](#task)
  - [Comment](#comment)
- [用户](#用户)
  - [POST /api/user/login 登录](#post-apiuserlogin-登录)
    - [请求体](#请求体)
    - [应答](#应答)
      - [登录成功](#登录成功)
      - [密码或用户名不正确](#密码或用户名不正确)
  - [GET /api/user/logout 登出](#get-apiuserlogout-登出)
    - [请求头](#请求头)
    - [应答](#应答-1)
      - [登出成功](#登出成功)
      - [身份验证失败](#身份验证失败)
  - [POST /api/users 注册新用户](#post-apiusers-注册新用户)
    - [请求体](#请求体-1)
    - [应答](#应答-2)
      - [注册成功](#注册成功)
      - [同名用户已存在](#同名用户已存在)
  - [GET /api/users/{username} 获取用户信息](#get-apiusersusername-获取用户信息)
    - [应答](#应答-3)
      - [获取用户信息成功](#获取用户信息成功)
      - [用户不存在](#用户不存在)
  - [PUT /api/users/{username} 修改用户信息](#put-apiusersusername-修改用户信息)
    - [请求头](#请求头-1)
    - [请求体](#请求体-2)
    - [应答](#应答-4)
      - [修改用户信息成功](#修改用户信息成功)
      - [身份验证失败](#身份验证失败-1)
  - [DELETE /api/users/{username} 注销用户](#delete-apiusersusername-注销用户)
    - [请求头](#请求头-2)
    - [应答](#应答-5)
      - [注销成功](#注销成功)
      - [身份验证失败](#身份验证失败-2)
  - [GET /api/users/{username}/projects 获取该用户参加的项目](#get-apiusersusernameprojects-获取该用户参加的项目)
    - [请求头](#请求头-3)
    - [应答](#应答-6)
      - [获取项目数组成功](#获取项目数组成功)
      - [身份验证失败](#身份验证失败-3)
  - [GET /api/users/{username}/recvNotifications 获取收到的通知](#get-apiusersusernamerecvnotifications-获取收到的通知)
    - [请求头](#请求头-4)
    - [应答](#应答-7)
      - [获取通知成功](#获取通知成功)
      - [身份验证失败](#身份验证失败-4)
  - [GET /api/users/{username}/recvNotifications/{id} 获取收到的某条通知](#get-apiusersusernamerecvnotificationsid-获取收到的某条通知)
    - [请求头](#请求头-5)
    - [应答](#应答-8)
      - [获取通知成功](#获取通知成功-1)
      - [身份验证失败](#身份验证失败-5)
  - [PATCH /api/users/{username}/recvNotifications/{id} 更新通知已读状态](#patch-apiusersusernamerecvnotificationsid-更新通知已读状态)
  - [GET /api/users/{username}/sendNotifications 获取发送的通知](#get-apiusersusernamesendnotifications-获取发送的通知)
    - [请求头](#请求头-6)
    - [应答](#应答-9)
      - [获取通知成功](#获取通知成功-2)
      - [身份验证失败](#身份验证失败-6)
  - [POST /api/users/{username}/sendNotifications 给别人发通知](#post-apiusersusernamesendnotifications-给别人发通知)
    - [请求头](#请求头-7)
    - [请求体](#请求体-3)
    - [应答](#应答-10)
      - [通知发送成功](#通知发送成功)
      - [未登录或 token 无效](#未登录或-token-无效)
  - [GET /api/users/{username}/sendNotifications/{id} 获取发送的某条通知](#get-apiusersusernamesendnotificationsid-获取发送的某条通知)
    - [请求头](#请求头-8)
    - [应答](#应答-11)
      - [获取通知成功](#获取通知成功-3)
      - [身份验证失败](#身份验证失败-7)
- [项目](#项目)
  - [POST /api/projects 创建新项目](#post-apiprojects-创建新项目)
  - [GET /api/projects/{id} 获取项目信息](#get-apiprojectsid-获取项目信息)
  - [PUT /api/projects/{id} 修改项目信息](#put-apiprojectsid-修改项目信息)
  - [DELETE /api/projects/{id} 删除项目](#delete-apiprojectsid-删除项目)
  - [GET /api/projects/{id}/members 获取项目成员（非主管和管理员）列表](#get-apiprojectsidmembers-获取项目成员非主管和管理员列表)
  - [POST /api/project/{id}/members 添加成员到项目中](#post-apiprojectidmembers-添加成员到项目中)
  - [GET /api/projects/{projectId}/tasks 获取项目中所有的任务](#get-apiprojectsprojectidtasks-获取项目中所有的任务)
  - [POST /api/projects/{projectId}/tasks 新建任务](#post-apiprojectsprojectidtasks-新建任务)
  - [GET /api/projects/{projectId}/tasks/{id} 获取某个任务的信息](#get-apiprojectsprojectidtasksid-获取某个任务的信息)
  - [PATCH /api/projects/{projectId}/tasks/{id} 修改任务](#patch-apiprojectsprojectidtasksid-修改任务)
  - [DELETE /api/projects/{projectId}/tasks/{id} 删除任务](#delete-apiprojectsprojectidtasksid-删除任务)
  - [GET /api/projects/{projectId}/tasks/{taskId}/comments 获取某项任务下的评论](#get-apiprojectsprojectidtaskstaskidcomments-获取某项任务下的评论)
  - [POST /api/projects/{projectId}/tasks/{taskId}/comments 在某项任务下发评论](#post-apiprojectsprojectidtaskstaskidcomments-在某项任务下发评论)
  - [GET /api/projects/{projectId}/tasks/{taskId}/comments/{id} 获取某条评论的内容](#get-apiprojectsprojectidtaskstaskidcommentsid-获取某条评论的内容)
  - [DELETE /api/projects/{projectId}/tasks/{taskId}/comments/{id} 删除某条评论](#delete-apiprojectsprojectidtaskstaskidcommentsid-删除某条评论)

# Models
## ApiResponse
```json
{
    "type": "string",
    "message": "string"
}
```

发生错误时，设置相应的 HTTP 状态码，并发回一条格式像这样的 JSON。

属性：

1. type - 消息类型
1. message - 详细信息

`type` 字段可以有以下取值（如有必要可继续增加）

1. WrongPasswordOrUsername 用户名或密码错误
1. UserAlreadyExist 同名用户已存在
1. UserNotFound 找不到用户

## UserLoginInfo
```json
{
    "username": "string",
    "password": "string"
}
```

用户登录时发送的 JSON。

属性：

1. username - 用户名
1. password - 密码

## UserSignUpInfo
```json
{
    "username": "string",
    "nickname": "string",
    "password": "string"
}
```

用户注册时发送的 JSON。

属性：

1. username - 用户名，必须唯一
1. nickname - 昵称，可不唯一
1. password - 密码

## User
```json
{
    "id": 5000,
    "username": "string",
    "nickname": "string",
    "createTime": "string",
    "updateTime": "string",
    "projectCount": 10
}
```

用来表示用户信息的 JSON。

属性：

1. id - 用户 ID，必须唯一
1. username - 用户名，必须唯一
1. nickname - 昵称，可不唯一
1. createTime - 用户注册时间
1. updateTime - 用户信息更新时间
1. projectCount - 参加项目的总数

注：

1. 要想获取该用户加入的仓库，需要通过 /api/users/{id}/projects。
1. 要想获取收件人为该用户的通知，需要通过 /api/users/{id}/recvNotifications
1. 要想获取发件人为该用户的通知，需要通过 /api/users/{id}/sendNotifications

## Notification
```json
{
    "id": 500,
    "isRead": false,
    "title": "string",
    "detail": "string",
    "createTime": "string",
    "sender": {
        "id": 5000,
        "username": "string",
        "nickname": "string",
        "createTime": "string",
        "updateTime": "string",
        "projectCount": 10
    },
    "receiver": {
        "id": 5001,
        "username": "string1",
        "nickname": "string",
        "createTime": "string",
        "updateTime": "string",
        "projectCount": 10
    }
}
```

用来表示一条通知的 JSON。

属性：

1. id - 通知 ID
1. isRead - 是否已读
1. title - 通知标题
1. detail - 通知详情
1. createTime - 发送时间
1. sender - 发件人，一个 User 对象
1. receiver - 收件人，一个 User 对象

若用户 A 邀请用户 B 加入某项目，则用户 B 会收到一条通知，发件人是用户 A。

## Project
```json
{
    "id": 500,
    "name": "string",
    "createTime": "string",
    "owner": {
        "id": 5000,
        "username": "string",
        "nickname": "string",
        "createTime": "string",
        "updateTime": "string",
        "projectCount": 10
    },
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

属性：

1. id - 项目 ID
1. name - 名称
1. createTime - 创建时间
1. owner - 项目主管，一个 User 对象
1. admins - 项目管理员（除了主管之外的），一个 User 数组

注：

1. 若要获取项目内成员列表（非主管或管理员），需要通过 /api/projects/{id}/members
1. 若要获取项目内任务列表，需要通过 /api/projects/{id}/tasks

## Role
TODO 用户在项目内的角色

## Task
```json
{
    "id": 200,
    "title": "string",
    "detail": "string",
    "commentNum": 10,
    "createTime": "string",
    "completeTime": "string",
    "creator": {
        "id": 5000,
        "username": "string",
        "nickname": "string",
        "createTime": "string",
        "updateTime": "string",
        "projectCount": 10
    },
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

表示任务的 JSON。

属性：

1. id - 任务 ID
1. title - 标题
1. detail - 详情
1. commentNum - 评论条数
1. createTime - 创建时间
1. completeTime - 完成时间
    - 若未完成则为 null
1. creator - 任务的创建者，一个 User 对象
1. executor - 任务执行人，一个 User 对象
    - 若未分配执行人则为 null

## Comment
```json
{
    "id": 777,
    "content": "string",
    "createTime": "string",
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

表示评论的 JSON 对象。

属性：

1. id - 评论 ID
1. content - 内容
1. createTime - 评论时间
1. author - 评论的作者，一个 User 对象

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
    "type": "WrongPasswordOrUsername",
    "message": "..."
}
```

## GET /api/user/logout 登出
### 请求头
需要在 Authorization 头信息中包含 token，标识当前用户。

### 应答
#### 登出成功
HTTP 200 OK

#### 身份验证失败
HTTP 401 Unauthorized

## POST /api/users 注册新用户
### 请求体
一个 UserSignUpInfo。

```json
{
    "username": "Alice123",
    "nickname": "Alice",
    "password": "123456"
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
    "type": "UserAlreadyExist",
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
    "projectCount": 10
}
```

#### 用户不存在
HTTP 404 Not Found

返回一个 ApiResponse。

```json
{
    "type": "UserNotFound",
    "message": "..."
}
```

## PUT /api/users/{username} 修改用户信息
需要验证身份，只有自己才能修改自己的信息。

### 请求头
需要在 Authorization 头信息中包含 token。

### 请求体
TODO 修改用户信息 请求体

### 应答
#### 修改用户信息成功
HTTP 200 OK

#### 身份验证失败
HTTP 401 Unauthorized

## DELETE /api/users/{username} 注销用户
只有自己才能注销自己。

### 请求头
需要放入该用户的 token，用于验证身份。

### 应答
#### 注销成功
HTTP 200 OK

#### 身份验证失败
HTTP 401 Unauthorized

##  GET /api/users/{username}/projects 获取该用户参加的项目
需要验证身份，只有自己才能查看自己参加的项目。

### 请求头
需要放入该用户的 token，用于验证身份。

### 应答
#### 获取项目数组成功
HTTP 200 OK

返回一个装有 Project 对象的数组。

#### 身份验证失败
HTTP 401 Unauthorized

## GET /api/users/{username}/recvNotifications 获取收到的通知
需要验证身份，只有自己才能查看自己的通知。

### 请求头
需要在 Authorization 头信息中包含 token。

### 应答
#### 获取通知成功
HTTP 200 OK

返回一个装有 Notification 对象的数组。

#### 身份验证失败
HTTP 401 Unauthorized

## GET /api/users/{username}/recvNotifications/{id} 获取收到的某条通知
需要验证身份，只有自己才能查看自己的通知。

### 请求头
需要在 Authorization 头信息中包含 token。

### 应答
#### 获取通知成功
HTTP 200 OK

返回一个 Notification 对象。

#### 身份验证失败
HTTP 401 Unauthorized

## PATCH /api/users/{username}/recvNotifications/{id} 更新通知已读状态
TODO 更新通知已读状态

## GET /api/users/{username}/sendNotifications 获取发送的通知
需要验证身份，只有自己才能查看自己的通知。

### 请求头
需要在 Authorization 头信息中包含 token。

### 应答
#### 获取通知成功
HTTP 200 OK

返回一个装有 Notification 对象的数组。

#### 身份验证失败
HTTP 401 Unauthorized

## POST /api/users/{username}/sendNotifications 给别人发通知
必须先登录，未登录的用户不能发通知。

### 请求头
需要在 Authorization 头信息中包含 token。

### 请求体
一个 Notification 对象。

### 应答
#### 通知发送成功
HTTP 201 Create

#### 未登录或 token 无效
HTTP 401 Unauthorized

## GET /api/users/{username}/sendNotifications/{id} 获取发送的某条通知
需要验证身份，只有自己才能查看自己的通知。

### 请求头
需要在 Authorization 头信息中包含 token。

### 应答
#### 获取通知成功
HTTP 200 OK

返回一个 Notification 对象。

#### 身份验证失败
HTTP 401 Unauthorized

# 项目
## POST /api/projects 创建新项目
TODO

## GET /api/projects/{id} 获取项目信息
TODO

## PUT /api/projects/{id} 修改项目信息
TODO

## DELETE /api/projects/{id} 删除项目
TODO

## GET /api/projects/{id}/members 获取项目成员（非主管和管理员）列表
TODO

## POST /api/project/{id}/members 添加成员到项目中
TODO

## GET /api/projects/{projectId}/tasks 获取项目中所有的任务
TODO

## POST /api/projects/{projectId}/tasks 新建任务
TODO

## GET /api/projects/{projectId}/tasks/{id} 获取某个任务的信息
TODO

## PATCH /api/projects/{projectId}/tasks/{id} 修改任务
TODO

## DELETE /api/projects/{projectId}/tasks/{id} 删除任务
TODO

## GET /api/projects/{projectId}/tasks/{taskId}/comments 获取某项任务下的评论
TODO

## POST /api/projects/{projectId}/tasks/{taskId}/comments 在某项任务下发评论
TODO

## GET /api/projects/{projectId}/tasks/{taskId}/comments/{id} 获取某条评论的内容
TODO

## DELETE /api/projects/{projectId}/tasks/{taskId}/comments/{id} 删除某条评论
TODO
