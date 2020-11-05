- [POST /api/projects 创建新项目](#post-apiprojects-创建新项目)
  - [请求头](#请求头)
  - [请求体](#请求体)
  - [应答](#应答)
    - [创建项目成功](#创建项目成功)
    - [无 token 或 token 无效](#无-token-或-token-无效)
- [GET /api/projects/{id} 获取项目信息](#get-apiprojectsid-获取项目信息)
  - [应答](#应答-1)
    - [获取项目信息成功](#获取项目信息成功)
    - [项目不存在](#项目不存在)
- [PUT /api/projects/{id} 修改项目信息](#put-apiprojectsid-修改项目信息)
  - [请求头](#请求头-1)
  - [请求体](#请求体-1)
  - [应答](#应答-2)
    - [修改成功](#修改成功)
    - [项目不存在](#项目不存在-1)
    - [无 token 或 token 无效](#无-token-或-token-无效-1)
    - [不是本项目的 Admin](#不是本项目的-admin)
- [DELETE /api/projects/{id} 删除项目](#delete-apiprojectsid-删除项目)
  - [请求头](#请求头-2)
  - [应答](#应答-3)
    - [删除成功](#删除成功)
    - [项目不存在](#项目不存在-2)
    - [无 token 或 token 无效](#无-token-或-token-无效-2)
    - [不是本项目的 SuperAdmin](#不是本项目的-superadmin)
- [GET /api/projects/{projectId}/members 获取项目成员列表](#get-apiprojectsprojectidmembers-获取项目成员列表)
  - [请求头](#请求头-3)
  - [应答](#应答-4)
    - [获取成员列表成功](#获取成员列表成功)
    - [项目不存在](#项目不存在-3)
    - [无 token 或 token 无效](#无-token-或-token-无效-3)
    - [不是本项目的 Member](#不是本项目的-member)
- [GET /api/projects/{projectId}/tasks 获取项目中所有的任务](#get-apiprojectsprojectidtasks-获取项目中所有的任务)
  - [请求头](#请求头-4)
  - [应答](#应答-5)
    - [获取任务成功](#获取任务成功)
    - [项目不存在](#项目不存在-4)
    - [无 token 或 token 无效](#无-token-或-token-无效-4)
    - [不是本项目的 Member](#不是本项目的-member-1)
- [POST /api/projects/{projectId}/tasks 新建任务](#post-apiprojectsprojectidtasks-新建任务)
  - [请求头](#请求头-5)
  - [请求体](#请求体-2)
  - [应答](#应答-6)
    - [新建任务成功](#新建任务成功)
    - [项目不存在](#项目不存在-5)
    - [无 token 或 token 无效](#无-token-或-token-无效-5)
    - [不是本项目的 Member](#不是本项目的-member-2)
- [GET /api/projects/{projectId}/tasks/{id} 获取某个任务](#get-apiprojectsprojectidtasksid-获取某个任务)
  - [请求头](#请求头-6)
  - [应答](#应答-7)
    - [获取任务成功](#获取任务成功-1)
    - [任务不存在](#任务不存在)
    - [无 token 或 token 无效](#无-token-或-token-无效-6)
    - [不是本项目的 Member](#不是本项目的-member-3)
- [PATCH /api/projects/{projectId}/tasks/{id} 修改任务](#patch-apiprojectsprojectidtasksid-修改任务)
  - [请求头](#请求头-7)
  - [请求体](#请求体-3)
  - [应答](#应答-8)
    - [修改成功](#修改成功-1)
    - [任务不存在](#任务不存在-1)
    - [无 token 或 token 无效](#无-token-或-token-无效-7)
    - [不是本项目的 Admin](#不是本项目的-admin-1)
- [DELETE /api/projects/{projectId}/tasks/{id} 删除任务](#delete-apiprojectsprojectidtasksid-删除任务)
  - [请求头](#请求头-8)
  - [应答](#应答-9)
    - [删除成功](#删除成功-1)
    - [任务不存在](#任务不存在-2)
    - [无 token 或 token 无效](#无-token-或-token-无效-8)
    - [不是本项目的 Admin](#不是本项目的-admin-2)
- [GET /api/projects/{projectId}/tasks/{taskId}/comments 获取某项任务下的评论](#get-apiprojectsprojectidtaskstaskidcomments-获取某项任务下的评论)
  - [请求头](#请求头-9)
  - [应答](#应答-10)
    - [获取评论成功](#获取评论成功)
    - [任务不存在](#任务不存在-3)
    - [无 token 或 token 无效](#无-token-或-token-无效-9)
    - [不是本项目的 Member](#不是本项目的-member-4)
- [POST /api/projects/{projectId}/tasks/{taskId}/comments 在某项任务下发评论](#post-apiprojectsprojectidtaskstaskidcomments-在某项任务下发评论)
  - [请求头](#请求头-10)
  - [请求体](#请求体-4)
  - [应答](#应答-11)
    - [发表评论成功](#发表评论成功)
    - [任务不存在](#任务不存在-4)
    - [无 token 或 token 无效](#无-token-或-token-无效-10)
    - [不是本项目的 Member](#不是本项目的-member-5)
- [GET /api/projects/{projectId}/tasks/{taskId}/comments/{id} 获取某条评论的内容](#get-apiprojectsprojectidtaskstaskidcommentsid-获取某条评论的内容)
  - [请求头](#请求头-11)
  - [应答](#应答-12)
    - [获取评论成功](#获取评论成功-1)
    - [评论不存在](#评论不存在)
    - [无 token 或 token 无效](#无-token-或-token-无效-11)
    - [不是本项目的 Member](#不是本项目的-member-6)
- [DELETE /api/projects/{projectId}/tasks/{taskId}/comments/{id} 删除某条评论](#delete-apiprojectsprojectidtaskstaskidcommentsid-删除某条评论)
  - [请求头](#请求头-12)
  - [应答](#应答-13)
    - [删除成功](#删除成功-2)
    - [获取评论成功](#获取评论成功-2)
    - [评论不存在](#评论不存在-1)
    - [无 token 或 token 无效](#无-token-或-token-无效-12)
    - [不是本项目的 Admin](#不是本项目的-admin-3)
- [POST /api/projects/{projectId}/invitations 发送邀请](#post-apiprojectsprojectidinvitations-发送邀请)
  - [请求头](#请求头-13)
  - [请求体](#请求体-5)
  - [应答](#应答-14)
    - [发送邀请成功](#发送邀请成功)
    - [项目不存在](#项目不存在-6)
    - [无 token 或 token 无效](#无-token-或-token-无效-13)
    - [不是本项目的 Admin](#不是本项目的-admin-4)
- [GET /api/projects/{projectId}/invitations 获取本项目中已发送的邀请](#get-apiprojectsprojectidinvitations-获取本项目中已发送的邀请)
  - [请求头](#请求头-14)
  - [应答](#应答-15)
    - [获取邀请成功](#获取邀请成功)
    - [项目不存在](#项目不存在-7)
    - [无 token 或 token 无效](#无-token-或-token-无效-14)
    - [不是本项目的 Admin](#不是本项目的-admin-5)
- [GET /api/projects/{projectId}/invitations/{id} 查看某个已发送的邀请](#get-apiprojectsprojectidinvitationsid-查看某个已发送的邀请)
  - [请求头](#请求头-15)
  - [应答](#应答-16)
    - [获取邀请成功](#获取邀请成功-1)
    - [邀请不存在](#邀请不存在)
    - [无 token 或 token 无效](#无-token-或-token-无效-15)
    - [不是本项目的 Admin](#不是本项目的-admin-6)
- [GET /api/projects/{projectId}/invitations/{id}/cancel 取消邀请](#get-apiprojectsprojectidinvitationsidcancel-取消邀请)
  - [请求头](#请求头-16)
  - [应答](#应答-17)
    - [取消邀请成功](#取消邀请成功)
    - [邀请不存在](#邀请不存在-1)
    - [无 token 或 token 无效](#无-token-或-token-无效-16)
    - [不是本项目的 Admin](#不是本项目的-admin-7)
- [GET /api/projects/{projectId}/invitations/{id}/accept 接受邀请](#get-apiprojectsprojectidinvitationsidaccept-接受邀请)
  - [请求头](#请求头-17)
  - [应答](#应答-18)
    - [接受邀请成功](#接受邀请成功)
    - [邀请不存在](#邀请不存在-2)
    - [无 token 或 token 无效](#无-token-或-token-无效-17)
- [GET /api/projects/{projectId}/invitations/{id}/reject 拒绝邀请](#get-apiprojectsprojectidinvitationsidreject-拒绝邀请)
  - [请求头](#请求头-18)
  - [应答](#应答-19)
    - [拒绝邀请成功](#拒绝邀请成功)
    - [邀请不存在](#邀请不存在-3)
    - [无 token 或 token 无效](#无-token-或-token-无效-18)

# POST /api/projects 创建新项目
需要登录才能创建项目。

## 请求头
需要在 Authorization 头信息中包含 token。

## 请求体
一个 Project 对象，其实只需要使用其中的 name 字段。

## 应答
### 创建项目成功
HTTP 201 Created

返回创建的 Project 对象。

### 无 token 或 token 无效
HTTP 401 Unauthorized

返回一个 ApiResponse。

```json
{
    "type": "NotLogin",
    "message": "..."
}
```

# GET /api/projects/{id} 获取项目信息
无请求头、无请求体。

## 应答
### 获取项目信息成功
HTTP 200 OK

返回 Project 对象。

### 项目不存在
HTTP 404 Not Found

返回 ApiResponse。

```json
{
    "type": "ProjectNotFound",
    "message": "..."
}
```

# PUT /api/projects/{id} 修改项目信息
本项目 Admin 及以上才能修改项目信息。
## 请求头
需要在 Authorization 头信息中包含 token。

## 请求体
TODO

## 应答
### 修改成功
HTTP 200 OK

### 项目不存在
HTTP 404 Not Found

返回 ApiResponse。

```json
{
    "type": "ProjectNotFound",
    "message": "..."
}
```

### 无 token 或 token 无效
HTTP 401 Unauthorized

返回一个 ApiResponse。

```json
{
    "type": "NotLogin",
    "message": "..."
}
```

### 不是本项目的 Admin
HTTP 403 Forbidden

返回一个 ApiResponse。

```json
{
    "type": "PermissionDenied",
    "message": "..."
}
```

# DELETE /api/projects/{id} 删除项目
本项目 SuperAdmin 才能删除项目。

## 请求头
需要在 Authorization 头信息中包含 token。

## 应答
### 删除成功
HTTP 200 OK

### 项目不存在
HTTP 404 Not Found

返回 ApiResponse。

```json
{
    "type": "ProjectNotFound",
    "message": "..."
}
```

### 无 token 或 token 无效
HTTP 401 Unauthorized

返回一个 ApiResponse。

```json
{
    "type": "NotLogin",
    "message": "..."
}
```

### 不是本项目的 SuperAdmin
HTTP 403 Forbidden

返回一个 ApiResponse。

```json
{
    "type": "PermissionDenied",
    "message": "..."
}
```

# GET /api/projects/{projectId}/members 获取项目成员列表
本项目 Member 及以上才能获取成员列表，未加入本项目的用户禁止获取。

## 请求头
需要在 Authorization 头信息中包含 token。

## 应答
### 获取成员列表成功
HTTP 200 OK

返回一个 Member 数组。

### 项目不存在
HTTP 404 Not Found

返回 ApiResponse。

```json
{
    "type": "ProjectNotFound",
    "message": "..."
}
```

### 无 token 或 token 无效
HTTP 401 Unauthorized

返回一个 ApiResponse。

```json
{
    "type": "NotLogin",
    "message": "..."
}
```

### 不是本项目的 Member
HTTP 403 Forbidden

返回一个 ApiResponse。

```json
{
    "type": "PermissionDenied",
    "message": "..."
}
```

TODO 修改成员角色、移除成员

TODO 转让项目

# GET /api/projects/{projectId}/tasks 获取项目中所有的任务
本项目 Member 及以上才能获取任务，未加入本项目的用户禁止获取任务。

## 请求头
需要在 Authorization 头信息中包含 token。

## 应答
### 获取任务成功
HTTP 200 OK

返回一个 Task 数组。

### 项目不存在
### 无 token 或 token 无效
### 不是本项目的 Member

# POST /api/projects/{projectId}/tasks 新建任务
本项目 Admin 及以上才能新建任务。

## 请求头
需要在 Authorization 头信息中包含 token。

## 请求体
Task 对象。

## 应答
### 新建任务成功
HTTP 201 Created

返回创建的 Task 对象。

### 项目不存在
### 无 token 或 token 无效
### 不是本项目的 Member

# GET /api/projects/{projectId}/tasks/{id} 获取某个任务
本项目 Member 及以上才能获取任务，未加入本项目的用户禁止获取任务。

## 请求头
需要在 Authorization 头信息中包含 token。

## 应答
### 获取任务成功
HTTP 200 OK

返回一个 Task 对象。

### 任务不存在
HTTP 404 Not Found

返回一个 ApiResponse。

```json
{
    "type": "TaskNotFound",
    "message": "..."
}
```

### 无 token 或 token 无效
### 不是本项目的 Member

# PATCH /api/projects/{projectId}/tasks/{id} 修改任务
本项目 Admin 及以上才能修改任务。

## 请求头
需要在 Authorization 头信息中包含 token。

## 请求体
TODO

## 应答
### 修改成功
HTTP 200 OK。

### 任务不存在
### 无 token 或 token 无效
### 不是本项目的 Admin

# DELETE /api/projects/{projectId}/tasks/{id} 删除任务
本项目 Admin 及以上才能删除任务。

## 请求头
需要在 Authorization 头信息中包含 token。

## 应答
### 删除成功
HTTP 200 OK

### 任务不存在
### 无 token 或 token 无效
### 不是本项目的 Admin

# GET /api/projects/{projectId}/tasks/{taskId}/comments 获取某项任务下的评论
本项目 Member 及以上才能获取评论，未加入本项目的用户禁止获取评论。

## 请求头
需要在 Authorization 头信息中包含 token。

## 应答
### 获取评论成功
HTTP 200 OK

返回一个 Comment 数组。

### 任务不存在
### 无 token 或 token 无效
### 不是本项目的 Member

# POST /api/projects/{projectId}/tasks/{taskId}/comments 在某项任务下发评论
本项目 Member 及以上才能发评论，未加入本项目的用户禁止发评论。

## 请求头
需要在 Authorization 头信息中包含 token。

## 请求体
Comment 对象。

## 应答
### 发表评论成功
HTTP 201 Created

返回 Comment 对象。

### 任务不存在
### 无 token 或 token 无效
### 不是本项目的 Member

# GET /api/projects/{projectId}/tasks/{taskId}/comments/{id} 获取某条评论的内容
本项目 Member 及以上才能获取评论，未加入本项目的用户禁止获取评论。

## 请求头
需要在 Authorization 头信息中包含 token。

## 应答
### 获取评论成功
HTTP 200 OK

返回一个 Comment 对象。

### 评论不存在
HTTP 404 Not Found

```json
{
    "type": "CommentNotFound",
    "message": "..."
}
```

### 无 token 或 token 无效
### 不是本项目的 Member

# DELETE /api/projects/{projectId}/tasks/{taskId}/comments/{id} 删除某条评论
本项目 Admin 及以上才能删评论。

## 请求头
需要在 Authorization 头信息中包含 token。

## 应答
### 删除成功
### 获取评论成功
HTTP 200 OK

### 评论不存在
### 无 token 或 token 无效
### 不是本项目的 Admin

# POST /api/projects/{projectId}/invitations 发送邀请
只有本项目 Admin 以上才能发送邀请。

## 请求头
需要在 Authorization 头信息中包含 token。

## 请求体
Invitation 对象，只需要 receiver 字段。

## 应答
### 发送邀请成功
HTTP 201 Created。

返回 Invitation 对象。

### 项目不存在
### 无 token 或 token 无效
### 不是本项目的 Admin

# GET /api/projects/{projectId}/invitations 获取本项目中已发送的邀请
只有本项目 Admin 以上才能查看邀请。

## 请求头
需要在 Authorization 头信息中包含 token。

## 应答
### 获取邀请成功
HTTP 200 OK

返回一个 Invitation 数组。

### 项目不存在
### 无 token 或 token 无效
### 不是本项目的 Admin

# GET /api/projects/{projectId}/invitations/{id} 查看某个已发送的邀请
只有本项目 Admin 以上才能查看邀请。

## 请求头
需要在 Authorization 头信息中包含 token。

## 应答
### 获取邀请成功
HTTP 200 OK

返回一个 Invitation 对象。

### 邀请不存在
HTTP 404 Not Found

```json
{
    "type": "InvitationNotFound",
    "message": "..."
}
```

### 无 token 或 token 无效
### 不是本项目的 Admin

# GET /api/projects/{projectId}/invitations/{id}/cancel 取消邀请
只有本项目 Admin 以上才能取消邀请。

## 请求头
需要在 Authorization 头信息中包含 token。

## 应答
### 取消邀请成功
HTTP 200 OK

### 邀请不存在
HTTP 404 Not Found

```json
{
    "type": "InvitationNotFound",
    "message": "..."
}
```

### 无 token 或 token 无效
### 不是本项目的 Admin

# GET /api/projects/{projectId}/invitations/{id}/accept 接受邀请
需要登录才能接受邀请。

## 请求头
需要在 Authorization 头信息中包含 token。

## 应答
### 接受邀请成功
HTTP 200 OK

### 邀请不存在
HTTP 404 Not Found

```json
{
    "type": "InvitationNotFound",
    "message": "..."
}
```

### 无 token 或 token 无效

# GET /api/projects/{projectId}/invitations/{id}/reject 拒绝邀请
需要登录才能拒绝邀请。

## 请求头
需要在 Authorization 头信息中包含 token。

## 应答
### 拒绝邀请成功
HTTP 200 OK

### 邀请不存在
HTTP 404 Not Found

```json
{
    "type": "InvitationNotFound",
    "message": "..."
}
```

### 无 token 或 token 无效
