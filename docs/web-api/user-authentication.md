- [POST /api/login 登录](#post-apilogin-登录)
  - [请求体](#请求体)
  - [应答](#应答)
    - [登录成功](#登录成功)
    - [密码或用户名不正确](#密码或用户名不正确)
- [GET /api/logout 登出](#get-apilogout-登出)
  - [请求头](#请求头)
  - [应答](#应答-1)
    - [登出成功](#登出成功)
    - [无 token 或 token 非法](#无-token-或-token-非法)
- [GET /api/whoami 获取当前用户信息](#get-apiwhoami-获取当前用户信息)
  - [请求头](#请求头-1)
  - [应答](#应答-2)
    - [获取用户信息成功](#获取用户信息成功)
    - [无 token 或 token 非法](#无-token-或-token-非法-1)

# POST /api/login 登录
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

# GET /api/logout 登出
已登录的用户才能登出。

## 请求头
需要在 Authorization 头信息中包含 token，标识当前用户。

## 应答
### 登出成功
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

# GET /api/whoami 获取当前用户信息
根据请求头中的 token 返回表示当前用户的 JSON。

## 请求头
需要在 Authorization 头信息中包含 token，标识当前用户。

## 应答
### 获取用户信息成功
HTTP 200 OK

返回当前用户的 User JSON。

### 无 token 或 token 非法
HTTP 401 Unauthorized

返回一个 ApiResponse。

```json
{
    "type": "NotLogin",
    "message": "..."
}
```
