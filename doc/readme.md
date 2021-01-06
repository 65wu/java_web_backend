## User

#### 登录

+ Url: /user/login

+ Method: POST

+ Request Body Example

  ```json
  {
      "username": "test_user",
      "password": "test_password"
  }
  ```

+ Response Body Example

  ```json
  {
      "code": 1,
      "message": "登录成功",
      "data": {
          "token": "92b4a6f2121d5be57368881aa814fcd8"
      }
  }
  ```

---

#### 注册

+ Url: /user/register

+ Method: POST

+ Request Body Example

  ```json
  {
      "password": "test_password",
      "username": "test_user",
      "email": "test@test.com",
      "nickname": "test_nickname"
  }
  ```

+ Response Body Example

  ```json
  {
      "code": 1,
      "message": "注册成功",
      "data": null
  }
  ```

---

#### 修改密码

+ Url: /user/edit/password

+ Method: PUT

+ Request Body Example

  ```json
  {
      "password": "new_password"
  }
  ```

+ Response Body Example

  ```json
  {
      "code": 1,
      "message": "密码修改成功",
      "data": null
  }
  ```

---

#### 修改基本信息

+ Url: /user/edit/basic

+ Method: PUT

+ Request Body Example

  ```json
  {
      "nickname": "new_nickname",
      "email": "new@email.com"
  }
  ```

+ Response Body Example

  ```json
  {
      "code": 1,
      "message": "基本信息修改成功",
      "data": null
  }
  ```

---

#### 获取用户信息

+ Url: /user/info

+ Method: GET

+ Response Body Example

  ```json
  {
      "code": 1,
      "message": "获取成功",
      "data": {
          "create_time": "2021-01-06T12:58:13.966+00:00",
          "nickname": "nickname",
          "email": "test@email.com",
          "username": "test_username",
          "status": 0
      }
  }
  ```

