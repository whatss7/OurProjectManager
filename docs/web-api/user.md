- [POST /api/user/login 登录](#post-apiuserlogin-登录)
  - [请求体](#请求体)
  - [应答](#应答)
    - [登录成功](#登录成功)
    - [密码或用户名不正确](#密码或用户名不正确)
- [GET /api/user/logout 登出](#get-apiuserlogout-登出)
  - [请求头](#请求头)
  - [应答](#应答-1)
    - [登出成功](#登出成功)
    - [无 token 或 token 非法](#无-token-或-token-非法)
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
    - [无 token 或 token 非法](#无-token-或-token-非法-1)
    - [操作者不是对应的用户](#操作者不是对应的用户)
- [DELETE /api/users/{username} 注销用户](#delete-apiusersusername-注销用户)
  - [请求头](#请求头-2)
  - [应答](#应答-5)
    - [注销成功](#注销成功)
    - [无 token 或 token 非法](#无-token-或-token-非法-2)
    - [操作者不是对应的用户](#操作者不是对应的用户-1)
- [GET /api/users/{username}/projects 获取该用户参加的项目](#get-apiusersusernameprojects-获取该用户参加的项目)
  - [请求头](#请求头-3)
  - [应答](#应答-6)
    - [获取项目数组成功](#获取项目数组成功)
    - [无 token 或 token 非法](#无-token-或-token-非法-3)
    - [操作者不是对应的用户](#操作者不是对应的用户-2)
- [GET /api/users/{username}/recvNotifications 获取收到的通知](#get-apiusersusernamerecvnotifications-获取收到的通知)
  - [请求头](#请求头-4)
  - [应答](#应答-7)
    - [获取通知成功](#获取通知成功)
    - [无 token 或 token 非法](#无-token-或-token-非法-4)
    - [操作者不是对应的用户](#操作者不是对应的用户-3)
- [GET /api/users/{username}/recvNotifications/{id} 获取收到的某条通知](#get-apiusersusernamerecvnotificationsid-获取收到的某条通知)
  - [请求头](#请求头-5)
  - [应答](#应答-8)
    - [获取通知成功](#获取通知成功-1)
    - [无 token 或 token 非法](#无-token-或-token-非法-5)
    - [操作者不是对应的用户](#操作者不是对应的用户-4)
- [PATCH /api/users/{username}/recvNotifications/{id} 更新通知已读状态](#patch-apiusersusernamerecvnotificationsid-更新通知已读状态)
- [GET /api/users/{username}/sendNotifications 获取发送的通知](#get-apiusersusernamesendnotifications-获取发送的通知)
  - [请求头](#请求头-6)
  - [应答](#应答-9)
    - [获取通知成功](#获取通知成功-2)
    - [无 token 或 token 非法](#无-token-或-token-非法-6)
    - [操作者不是对应的用户](#操作者不是对应的用户-5)
- [POST /api/users/{username}/sendNotifications 给别人发通知](#post-apiusersusernamesendnotifications-给别人发通知)
  - [请求头](#请求头-7)
  - [请求体](#请求体-3)
  - [应答](#应答-10)
    - [通知发送成功](#通知发送成功)
    - [无 token 或 token 非法](#无-token-或-token-非法-7)
- [GET /api/users/{username}/sendNotifications/{id} 获取发送的某条通知](#get-apiusersusernamesendnotificationsid-获取发送的某条通知)
  - [请求头](#请求头-8)
  - [应答](#应答-11)
    - [获取通知成功](#获取通知成功-3)
    - [无 token 或 token 非法](#无-token-或-token-非法-8)
    - [操作者不是对应的用户](#操作者不是对应的用户-6)

# POST /api/user/login 登录
## 请求体
一个 UserLoginInfo。

```json
{
    "username": "Alice123",
    "password": "123456"
}
```

## 应答
### 登录成功
HTTP 200 OK

返回一个字符串，这个字符串是该用户的 JSON Web Token。其形式类似下面这种。

```
eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c
```

### 密码或用户名不正确
HTTP 401 Unauthorized

返回一个 ApiResponse。

```json
{
    "type": "WrongPasswordOrUsername",
    "message": "..."
}
```

# GET /api/user/logout 登出
## 请求头
需要在 Authorization 头信息中包含 token，标识当前用户。

## 应答
### 登出成功
HTTP 200 OK

### 无 token 或 token 非法
HTTP 401 Unauthorized

# POST /api/users 注册新用户
## 请求体
一个 UserSignUpInfo。

```json
{
    "username": "Alice123",
    "nickname": "Alice",
    "password": "123456"
}
```

## 应答
### 注册成功
HTTP 201 Created

### 同名用户已存在
HTTP 409 Conflict

返回一个 ApiResponse。

```json
{
    "type": "UserAlreadyExist",
    "message": "..."
}
```

# GET /api/users/{username} 获取用户信息
## 应答
### 获取用户信息成功
HTTP 200 OK

返回一个 User。

```json
{
    "id": 5000,
    "username": "Alice123",
    "nickname": "Alice",
    "createAt": "...",
    "updateAt": "...",
    "projectCount": 10
}
```

### 用户不存在
HTTP 404 Not Found

返回一个 ApiResponse。

```json
{
    "type": "UserNotFound",
    "message": "..."
}
```

# PUT /api/users/{username} 修改用户信息
需要验证身份，只有自己才能修改自己的信息。

## 请求头
需要在 Authorization 头信息中包含 token。

## 请求体
TODO 修改用户信息 请求体

## 应答
### 修改用户信息成功
HTTP 200 OK

### 无 token 或 token 非法
HTTP 401 Unauthorized

返回一个 ApiResponse。

```json
{
    "type": "NotLogin",
    "message": "..."
}
```

### 操作者不是对应的用户
HTTP 403 Forbidden

返回一个 ApiResponse。

```json
{
    "type": "PermissionDenied",
    "message": "..."
}
```

# DELETE /api/users/{username} 注销用户
只有自己才能注销自己。

## 请求头
需要放入该用户的 token，用于验证身份。

## 应答
### 注销成功
HTTP 200 OK

### 无 token 或 token 非法
HTTP 401 Unauthorized

返回一个 ApiResponse。

```json
{
    "type": "NotLogin",
    "message": "..."
}
```

### 操作者不是对应的用户
HTTP 403 Forbidden

返回一个 ApiResponse。

```json
{
    "type": "PermissionDenied",
    "message": "..."
}
```

#  GET /api/users/{username}/projects 获取该用户参加的项目
需要验证身份，只有自己才能查看自己参加的项目。

## 请求头
需要放入该用户的 token，用于验证身份。

## 应答
### 获取项目数组成功
HTTP 200 OK

返回一个装有 Project 对象的数组。

### 无 token 或 token 非法
HTTP 401 Unauthorized

返回一个 ApiResponse。

```json
{
    "type": "NotLogin",
    "message": "..."
}
```

### 操作者不是对应的用户
HTTP 403 Forbidden

返回一个 ApiResponse。

```json
{
    "type": "PermissionDenied",
    "message": "..."
}
```

# GET /api/users/{username}/recvNotifications 获取收到的通知
需要验证身份，只有自己才能查看自己的通知。

## 请求头
需要在 Authorization 头信息中包含 token。

## 应答
### 获取通知成功
HTTP 200 OK

返回一个装有 Notification 对象的数组。

### 无 token 或 token 非法
HTTP 401 Unauthorized

返回一个 ApiResponse。

```json
{
    "type": "NotLogin",
    "message": "..."
}
```

### 操作者不是对应的用户
HTTP 403 Forbidden

返回一个 ApiResponse。

```json
{
    "type": "PermissionDenied",
    "message": "..."
}
```

# GET /api/users/{username}/recvNotifications/{id} 获取收到的某条通知
需要验证身份，只有自己才能查看自己的通知。

## 请求头
需要在 Authorization 头信息中包含 token。

## 应答
### 获取通知成功
HTTP 200 OK

返回一个 Notification 对象。

### 无 token 或 token 非法
HTTP 401 Unauthorized

返回一个 ApiResponse。

```json
{
    "type": "NotLogin",
    "message": "..."
}
```

### 操作者不是对应的用户
HTTP 403 Forbidden

返回一个 ApiResponse。

```json
{
    "type": "PermissionDenied",
    "message": "..."
}
```

# PATCH /api/users/{username}/recvNotifications/{id} 更新通知已读状态
TODO 更新通知已读状态

# GET /api/users/{username}/sendNotifications 获取发送的通知
需要验证身份，只有自己才能查看自己的通知。

## 请求头
需要在 Authorization 头信息中包含 token。

## 应答
### 获取通知成功
HTTP 200 OK

返回一个装有 Notification 对象的数组。

### 无 token 或 token 非法
HTTP 401 Unauthorized

返回一个 ApiResponse。

```json
{
    "type": "NotLogin",
    "message": "..."
}
```

### 操作者不是对应的用户
HTTP 403 Forbidden

返回一个 ApiResponse。

```json
{
    "type": "PermissionDenied",
    "message": "..."
}
```

# POST /api/users/{username}/sendNotifications 给别人发通知
必须先登录，未登录的用户不能发通知。

## 请求头
需要在 Authorization 头信息中包含 token。

## 请求体
一个 Notification 对象。

## 应答
### 通知发送成功
HTTP 201 Create

### 无 token 或 token 非法
HTTP 401 Unauthorized

返回一个 ApiResponse。

```json
{
    "type": "NotLogin",
    "message": "..."
}
```

# GET /api/users/{username}/sendNotifications/{id} 获取发送的某条通知
需要验证身份，只有自己才能查看自己的通知。

## 请求头
需要在 Authorization 头信息中包含 token。

## 应答
### 获取通知成功
HTTP 200 OK

返回一个 Notification 对象。

### 无 token 或 token 非法
HTTP 401 Unauthorized

返回一个 ApiResponse。

```json
{
    "type": "NotLogin",
    "message": "..."
}
```

### 操作者不是对应的用户
HTTP 403 Forbidden

返回一个 ApiResponse。

```json
{
    "type": "PermissionDenied",
    "message": "..."
}
```
