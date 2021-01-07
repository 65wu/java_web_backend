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



## News

#### 获取所有新闻

+ Url: /news/all

+ Method: GET

+ Request Params

  | Key      | Description                            |
  | :------- | -------------------------------------- |
  | pageNo   | 当前页面id，以1开始                    |
  | pageSize | 一张页面最多显示的新闻数量，默认值为10 |

+ Response Params

  | Key   | Description        |
  | ----- | ------------------ |
  | total | 所有新闻纪录的数量 |
  | pages | 页面总数           |
  | list  | 存取新闻的列表     |

  

+ Response Body Example

  ```json
  {
      "code": 1,
      "message": "获取成功",
      "data": {
          "pageNum": 1,
          "pageSize": 10,
          "size": 2,
          "startRow": 1,
          "endRow": 2,
          "total": 2,
          "pages": 1,
          "list": [
              {
                  "newsId": 1,
                  "content": "修改内容0",
                  "type": "国内",
                  "publish_time": "2021-01-07 13:03:21"
              },
              {
                  "newsId": 2,
                  "content": "新内容",
                  "type": "国际",
                  "publish_time": "2021-01-07 13:22:06"
              }
          ],
          "prePage": 0,
          "nextPage": 0,
          "isFirstPage": true,
          "isLastPage": true,
          "hasPreviousPage": false,
          "hasNextPage": false,
          "navigatePages": 8,
          "navigatepageNums": [
              1
          ],
          "navigateFirstPage": 1,
          "navigateLastPage": 1,
          "lastPage": 1,
          "firstPage": 1
      }
  }
  ```

---

#### 获取某一新闻详细信息

+ Url: /news/detail

+ Method: GET

+ Request Params

  | Key     | Description |
  | ------- | ----------- |
  | news_id | 新闻的主键  |

+ Response Body Params

  | Key      | Description    |
  | -------- | -------------- |
  | user_id  | 新闻作者的主键 |
  | nickname | 新闻作者的昵称 |

+ Response Body Example

  ```json
  {
      "code": 1,
      "message": "获取成功",
      "data": {
          "news_detail": {
              "title": "修改标题0",
              "content": "修改内容0",
              "publish_time": "2021-01-07 13:03:21",
              "type": "国内",
              "user_id": 4,
              "nickname": "hhh"
          }
      }
  }
  ```


---

#### 修改某一新闻

+ Url: /news/edit

+ Method: PUT

+ Request Body Example

  ```json
  {
      "newsId": 1,
      "title": "修改标题0",
      "content": "修改内容0",
      "typeId": 1
  }
  ```

+ Response Body Example

  ```json
  {
      "code": 1,
      "message": "新闻修改成功",
      "data": null
  }
  ```

  

