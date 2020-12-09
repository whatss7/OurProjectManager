- [POST /api/users 注册新用户](#post-apiusers-注册新用户)
  - [请求体](#请求体)
  - [应答](#应答)
    - [注册成功](#注册成功)
    - [同名用户已存在](#同名用户已存在)
- [GET /api/users/{username} 获取用户信息](#get-apiusersusername-获取用户信息)
  - [应答](#应答-1)
    - [获取用户信息成功](#获取用户信息成功)
    - [用户不存在](#用户不存在)
- [PUT /api/users/{username} 修改用户名和昵称](#put-apiusersusername-修改用户名和昵称)
  - [请求头](#请求头)
  - [请求体](#请求体-1)
  - [应答](#应答-2)
    - [修改成功](#修改成功)
    - [用户不存在](#用户不存在-1)
    - [无 token 或 token 非法](#无-token-或-token-非法)
    - [操作者不是对应的用户](#操作者不是对应的用户)
    - [同名用户已存在（username 同）](#同名用户已存在username-同)
- [PUT /api/users/{username}/password 修改密码](#put-apiusersusernamepassword-修改密码)
  - [请求头](#请求头-1)
  - [请求体](#请求体-2)
  - [应答](#应答-3)
    - [修改密码成功](#修改密码成功)
    - [旧密码错误](#旧密码错误)
    - [用户不存在](#用户不存在-2)
    - [无 token 或 token 非法](#无-token-或-token-非法-1)
    - [操作者不是对应的用户](#操作者不是对应的用户-1)
- [DELETE /api/users/{username} 注销用户](#delete-apiusersusername-注销用户)
  - [请求头](#请求头-2)
  - [应答](#应答-4)
    - [注销成功](#注销成功)
    - [用户不存在](#用户不存在-3)
    - [无 token 或 token 非法](#无-token-或-token-非法-2)
    - [操作者不是对应的用户](#操作者不是对应的用户-2)
- [GET /api/users/{username}/projects 获取该用户参加的项目](#get-apiusersusernameprojects-获取该用户参加的项目)
  - [请求头](#请求头-3)
  - [应答](#应答-5)
    - [获取项目数组成功](#获取项目数组成功)
    - [用户不存在](#用户不存在-4)
    - [无 token 或 token 非法](#无-token-或-token-非法-3)
    - [操作者不是对应的用户](#操作者不是对应的用户-3)
- [GET /api/users/{username}/recvNotifications 获取收到的通知](#get-apiusersusernamerecvnotifications-获取收到的通知)
  - [请求头](#请求头-4)
  - [应答](#应答-6)
    - [获取通知成功](#获取通知成功)
    - [用户不存在](#用户不存在-5)
    - [无 token 或 token 非法](#无-token-或-token-非法-4)
    - [操作者不是对应的用户](#操作者不是对应的用户-4)
- [GET /api/users/{username}/recvNotifications/{id} 获取收到的某条通知](#get-apiusersusernamerecvnotificationsid-获取收到的某条通知)
  - [请求头](#请求头-5)
  - [应答](#应答-7)
    - [获取通知成功](#获取通知成功-1)
    - [通知不存在](#通知不存在)
    - [无 token 或 token 非法](#无-token-或-token-非法-5)
    - [操作者不是对应的用户](#操作者不是对应的用户-5)
- [PATCH /api/users/{username}/recvNotifications/{id} 修改通知已读状态](#patch-apiusersusernamerecvnotificationsid-修改通知已读状态)
  - [请求头](#请求头-6)
  - [请求体](#请求体-3)
  - [应答](#应答-8)
    - [修改已读状态成功](#修改已读状态成功)
    - [通知不存在](#通知不存在-1)
    - [无 token 或 token 无效](#无-token-或-token-无效)
    - [操作者不是对应用户](#操作者不是对应用户)
- [GET /api/users/{username}/sendNotifications 获取发送的通知](#get-apiusersusernamesendnotifications-获取发送的通知)
  - [请求头](#请求头-7)
  - [应答](#应答-9)
    - [获取通知成功](#获取通知成功-2)
    - [无 token 或 token 非法](#无-token-或-token-非法-6)
    - [操作者不是对应的用户](#操作者不是对应的用户-6)
- [POST /api/users/{username}/sendNotifications 给别人发通知](#post-apiusersusernamesendnotifications-给别人发通知)
  - [请求头](#请求头-8)
  - [请求体](#请求体-4)
  - [应答](#应答-10)
    - [通知发送成功](#通知发送成功)
    - [用户不存在](#用户不存在-6)
    - [无 token 或 token 非法](#无-token-或-token-非法-7)
- [GET /api/users/{username}/sendNotifications/{id} 获取发送的某条通知](#get-apiusersusernamesendnotificationsid-获取发送的某条通知)
  - [请求头](#请求头-9)
  - [应答](#应答-11)
    - [获取通知成功](#获取通知成功-3)
    - [通知不存在](#通知不存在-2)
    - [无 token 或 token 非法](#无-token-或-token-非法-8)
    - [操作者不是对应的用户](#操作者不是对应的用户-7)

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

# PUT /api/users/{username} 修改用户名和昵称
需要验证身份，只有自己才能修改自己的用户信息。

## 请求头
需要在 Authorization 头信息中包含 token。

## 请求体
一个 User 对象，只使用 username 和 nickname 字段。

## 应答
### 修改成功
HTTP 200 OK

### 用户不存在
HTTP 404 Not Found

返回一个 ApiResponse。

```json
{
    "type": "UserNotFound",
    "message": "..."
}
```

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

### 同名用户已存在（username 同）
HTTP 409 Conflict

返回一个 ApiResponse。

```json
{
    "type": "UserAlreadyExist",
    "message": "..."
}
```

# PUT /api/users/{username}/password 修改密码
只有自己才能修改自己的密码。

## 请求头
需要放入该用户的 token，用于验证身份。

## 请求体
一个 UpdatePassword 对象。

## 应答
### 修改密码成功
HTTP 200 OK

### 旧密码错误
HTTP 403 Forbidden

返回一个 ApiResponse。

```json
{
    "type": "WrongOldPassword",
    "message": "..."
}
```

### 用户不存在
### 无 token 或 token 非法
### 操作者不是对应的用户
# DELETE /api/users/{username} 注销用户
只有自己才能注销自己。

## 请求头
需要放入该用户的 token，用于验证身份。

## 应答
### 注销成功
HTTP 200 OK

### 用户不存在
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

### 用户不存在
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

### 用户不存在
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

### 通知不存在
HTTP 404 NotFound

返回一个 ApiResponse。

```json
{
    "type": "NotificationNotFound",
    "message": "..."
}
```

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

# PATCH /api/users/{username}/recvNotifications/{id} 修改通知已读状态
需要验证身份，只有自己才能修改自己收到的通知的已读状态。

## 请求头
需要在 Authorization 头信息中包含 token。

## 请求体
一个 Notification 对象，只使用 read 字段。

## 应答
### 修改已读状态成功
HTTP 200 OK

### 通知不存在
### 无 token 或 token 无效
### 操作者不是对应用户

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

### 用户不存在
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

### 通知不存在
HTTP 404 NotFound

返回一个 ApiResponse。

```json
{
    "type": "NotificationNotFound",
    "message": "..."
}
```

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
