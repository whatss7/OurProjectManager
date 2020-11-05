- [UserLoginInfo 用户登录信息](#userlogininfo-用户登录信息)
- [UserSignUpInfo 用户注册信息](#usersignupinfo-用户注册信息)
- [User 用户](#user-用户)
- [Notification 通知](#notification-通知)
- [Project 项目](#project-项目)
- [Member 项目成员](#member-项目成员)
- [Task 任务](#task-任务)
- [Comment 评论](#comment-评论)
- [ApiResponse 响应消息](#apiresponse-响应消息)

# UserLoginInfo 用户登录信息
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

# UserSignUpInfo 用户注册信息
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

# User 用户
```json
{
    "id": 5000,
    "username": "string",
    "nickname": "string",
    "createAt": "string",
    "updateAt": "string",
    "projectCount": 10
}
```

用来表示用户信息的 JSON。

属性：

1. id - 用户 ID，必须唯一
1. username - 用户名，必须唯一
1. nickname - 昵称，可不唯一
1. createAt - 用户注册时间
1. updateAt - 用户信息更新时间
1. projectCount - 参加项目的总数

注：

1. 要想获取该用户加入的仓库，需要通过 /api/users/{id}/projects。
1. 要想获取收件人为该用户的通知，需要通过 /api/users/{id}/recvNotifications
1. 要想获取发件人为该用户的通知，需要通过 /api/users/{id}/sendNotifications

# Notification 通知
```json
{
    "id": 500,
    "isRead": false,
    "title": "string",
    "body": "string",
    "createAt": "string",
    "sender": {
        "id": 5000,
        "username": "string",
        "nickname": "string",
        "createAt": "string",
        "updateAt": "string",
        "projectCount": 10
    },
    "receiver": {
        "id": 5001,
        "username": "string1",
        "nickname": "string",
        "createAt": "string",
        "updateAt": "string",
        "projectCount": 10
    }
}
```

用来表示一条通知的 JSON。

属性：

1. id - 通知 ID
1. isRead - 是否已读
1. title - 通知标题
1. body - 通知详情
1. createAt - 发送时间
1. sender - 发件人，一个 User 对象
1. receiver - 收件人，一个 User 对象

若用户 A 邀请用户 B 加入某项目，则用户 B 会收到一条通知，发件人是用户 A。

# Project 项目
```json
{
    "id": 500,
    "name": "string",
    "createAt": "string",
    "updateAt": "string",
    "superAdmin": {
        "id": 5000,
        "username": "string",
        "nickname": "string",
        "createAt": "string",
        "updateAt": "string",
        "projectCount": 10
    },
    "admins": [
        {
            "id": 5001,
            "username": "string1",
            "nickname": "string",
            "createAt": "string",
            "updateAt": "string",
            "projectCount": 10
        },
        {
            "id": 5002,
            "username": "string2",
            "nickname": "string",
            "createAt": "string",
            "updateAt": "string",
            "projectCount": 10
        },
    ]
}
```

用来表示一个项目的 JSON。

属性：

1. id - 项目 ID
1. name - 名称
1. createAt - 创建时间
1. updateAt - 更新时间
1. superAdmin - 项目主管，一个 User 对象
1. admins - 项目管理员（除了主管之外的），一个 User 数组

注：

1. 若要获取项目中的任务，需要通过 /api/projects/{id}/tasks
1. 若要获取项目成员列表，需要通过 /api/projects/{id}/members

TODO 邀请成员

# Member 项目成员
```json
{
    "user":{

    },
    "role":"string",
    "joinAt": "string"
}
```

表示项目中的成员及其角色。

属性：

1. user - 用户，一个 User 对象
1. role - 在项目中的角色，用字符串表示
1. joinAt - 加入本项目的时间

注：角色用字符串表示好像不太好，不清楚有没有更好的解决方法。

# Task 任务
```json
{
    "id": 200,
    "title": "string",
    "body": "string",
    "commentNum": 10,
    "createAt": "string",
    "creator": {
        "id": 5000,
        "username": "string",
        "nickname": "string",
        "createAt": "string",
        "updateAt": "string",
        "projectCount": 10
    },
    "executors": [
        {
            "id": 5001,
            "username": "string1",
            "nickname": "string",
            "createAt": "string",
            "updateAt": "string",
            "projectCount": 10
        },
        {
            "id": 5002,
            "username": "string2",
            "nickname": "string",
            "createAt": "string",
            "updateAt": "string",
            "projectCount": 10
        }
    ],
    "completeAt": "string",
    "completer": {
        "id": 5001,
        "username": "string1",
        "nickname": "string",
        "createAt": "string",
        "updateAt": "string",
        "projectCount": 10
    }
}
```

表示任务的 JSON。

属性：

1. id - 任务 ID
1. title - 标题
1. body - 详情
1. commentNum - 评论条数
1. createAt - 创建时间
1. creator - 创建者，一个 User 对象
1. executors - 执行人，一个 User 数组
    - 若未分配执行人则为空数组 `[]`
1. completeAt - 完成时间
    - 若未完成则为 null
1. completer - 完成者，一个 User 对象
    - 若未完成则为 null

注：

1. 若要获取某个任务下的评论，需通过 /api/projects/{projectId}/tasks/{taskId}/comments

# Comment 评论
```json
{
    "id": 777,
    "body": "string",
    "createAt": "string",
    "user": {
        "id": 5000,
        "username": "string",
        "nickname": "string",
        "createAt": "string",
        "updateAt": "string",
        "projectCount": 10
    }
}
```

表示评论的 JSON 对象。

属性：

1. id - 评论 ID
1. body - 内容
1. createAt - 评论时间
1. user - 评论的作者，一个 User 对象

# ApiResponse 响应消息
```json
{
    "type": "string",
    "message": "string"
}
```

发生错误时，设置相应的 HTTP 状态码，并发回一条格式像这样的 JSON。

属性：

1. type - 错误类型
1. message - 详细信息

`type` 字段可以有以下取值（可继续增加）

1. WrongPasswordOrUsername 用户名或密码错误
1. UserAlreadyExist 同名用户已存在
1. UserNotFound 找不到用户
1. NotLogin 未登录
1. PermissionDenied 没有权限
