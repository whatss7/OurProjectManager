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
    createAt DATETIME, -- 注册时间
    updateAt DATETIME, -- 更新时间
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

```sql
CREATE TABLE Notification(
    id  INT UNSIGNED AUTO_INCREMENT, 
    `read` BOOLEAN NOT NULL,
    title VARCHAR(255) NOT NULL,
    body VARCHAR(255),
    createAt DATETIME,
    senderId INT UNSIGNED NOT NULL,
    receiverId INT UNSIGNED NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(senderId) REFERENCES User(id) ON DELETE CASCADE,
    FOREIGN KEY(receiverId) REFERENCES User(id) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

# Projects 表

| 字段        | 说明     |
| ----------- | -------- |
| id          | 项目 ID  |
| name        | 项目名称 |
| description | 项目描述 |
| createAt    | 创建时间 |
| updateAt    | 更新时间 |

```sql
CREATE TABLE Project(
    id INT UNSIGNED AUTO_INCREMENT, 
    name VARCHAR(255) NOT NULL, 
    description VARCHAR(255), 
    createAt DATETIME,
    updateAt DATETIME,
    PRIMARY KEY(id)
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
```
# Members 表
| 字段      | 说明               |
| --------- | ------------------ |
| userId    | 用户 ID            |
| projectId | 项目 ID            |
| role      | 用户在项目中的角色 |
| joinAt    | 加入项目的时间     |

```sql
CREATE TABLE Member(
    userId INT UNSIGNED NOT NULL,
    projectId INT UNSIGNED NOT NULL,
    role VARCHAR(255) NOT NULL, 
    joinAt DATETIME,
    PRIMARY KEY(userId, projectId),
    FOREIGN KEY(userId) REFERENCES User(id) ON DELETE CASCADE,
    FOREIGN KEY(projectId) REFERENCES Project(id) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

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

```sql
CREATE TABLE Task(
    id INT UNSIGNED AUTO_INCREMENT, 
    projectId INT UNSIGNED NOT NULL,
    title  VARCHAR(255) NOT NULL, 
    body  VARCHAR(255),
    createAt DATETIME,
    creatorId INT UNSIGNED NOT NULL,
    complete BOOLEAN NOT NULL,
    completeAt DATETIME,
    completerId INT UNSIGNED,
    PRIMARY KEY(id),
    FOREIGN KEY(projectId) REFERENCES Project(id) ON DELETE CASCADE,
    FOREIGN KEY(creatorId) REFERENCES User(id) ON DELETE CASCADE,
    FOREIGN KEY(completerId) REFERENCES User(id) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

# TaskExecutors 表
| 字段       | 说明      |
| ---------- | --------- |
| taskId     | 任务 ID   |
| executorId | 执行人 ID |

```sql
CREATE TABLE TaskExecutor(
    taskId INT UNSIGNED NOT NULL,
    executorId INT UNSIGNED NOT NULL,
    PRIMARY KEY(taskId, executorId),
    FOREIGN KEY(taskId) REFERENCES Task(id) ON DELETE CASCADE,
    FOREIGN KEY(executorId) REFERENCES User(id) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

# Comments 表
| 字段     | 说明        |
| -------- | ----------- |
| id       | 评论 ID     |
| taskId   | 任务 ID     |
| body     | 评论内容    |
| createAt | 创建时间    |
| userId   | 作者用户 ID |

```sql
CREATE Table Comment(
    id INT UNSIGNED AUTO_INCREMENT, 
    taskId INT UNSIGNED NOT NULL,
    body VARCHAR(255) NOT NULL,
    createAt DATETIME NOT NULL,
    userId INT UNSIGNED NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(taskId) REFERENCES Task(id) ON DELETE CASCADE,
    FOREIGN KEY(userId) REFERENCES User(id) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
```

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

```sql
CREATE Table Invitation(
    id INT UNSIGNED AUTO_INCREMENT, 
    createAt DATETIME NOT NULL,
    endAt DATETIME NOT NULL,
    status VARCHAR(255) NOT NULL, 
    senderId INT UNSIGNED NOT NULL,
    receiverId INT UNSIGNED NOT NULL,
    projectId INT UNSIGNED NOT NULL,
    PRIMARY KEY(id),
    FOREIGN KEY(senderId) REFERENCES User(id) ON DELETE CASCADE,
    FOREIGN KEY(receiverId) REFERENCES User(id) ON DELETE CASCADE,
    FOREIGN KEY(projectId) REFERENCES Project(id) ON DELETE CASCADE
)ENGINE=InnoDB DEFAULT CHARSET=utf8;
```
