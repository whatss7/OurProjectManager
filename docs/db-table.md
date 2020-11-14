# 数据库名
OurProjectManagerDb

# Users 表
| 字段           | 说明         |
| -------------- | ------------ |
| id             | 用户 ID      |
| username       | 用户名       |
| nickname       | 用户昵称     |
| hashedPassword | 哈希后的密码 |
| salt           | 盐           |
| createAt       | 注册时间     |
| updateAt       | 更新时间     |

```sql
CREATE TABLE User(
    id INT UNSIGNED AUTO_INCREMENT, -- 用户 ID（主键）
    username VARCHAR(255) NOT NULL, -- 用户名
    nickname VARCHAR(255) NOT NULL, -- 用户昵称
    hashedPassword VARCHAR(255) NOT NULL, -- 哈希后的密码
    salt VARCHAR(255) NOT NULL, -- 盐
    createAt Date, -- 注册时间
    updateAt Date, -- 更新时间
    PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

# Notifications 表
| 字段       | 说明          |
| ---------- | ------------- |
| id         | 通知 ID       |
| read       | 是否已读      |
| title      | 通知标题      |
| body       | 通知内容      |
| createAt   | 创建时间      |
| senderId   | 发送者用户 ID |
| receiverId | 接收者用户 ID |

# Projects 表
| 字段        | 说明     |
| ----------- | -------- |
| id          | 项目 ID  |
| name        | 项目名称 |
| description | 项目描述 |
| createAt    | 创建时间 |
| updateAt    | 更新时间 |

# Members 表
| 字段      | 说明               |
| --------- | ------------------ |
| userId    | 用户 ID            |
| projectId | 项目 ID            |
| role      | 用户在项目中的角色 |
| joinAt    | 加入项目的时间     |

# Tasks 表
| 字段        | 说明          |
| ----------- | ------------- |
| id          | 任务 ID       |
| projectId   | 项目 ID       |
| title       | 任务标题      |
| body        | 任务内容      |
| createAt    | 创建时间      |
| creatorId   | 创建者用户 ID |
| complete    | 是否完成      |
| completeAt  | 完成时间      |
| completerId | 完成者用户 ID |

# TaskExecutors 表
| 字段       | 说明      |
| ---------- | --------- |
| taskId     | 任务 ID   |
| executorId | 执行人 ID |

# Comments 表
| 字段     | 说明        |
| -------- | ----------- |
| id       | 评论 ID     |
| taskId   | 任务 ID     |
| body     | 评论内容    |
| createAt | 创建时间    |
| userId   | 作者用户 ID |

# Invitations 表
| 字段       | 说明          |
| ---------- | ------------- |
| id         | 邀请 ID       |
| createAt   | 创建时间      |
| endAt      | 终结时间      |
| status     | 状态          |
| senderId   | 发送者用户 ID |
| receiverId | 接收者用户 ID |
| projectId  | 项目 ID       |
