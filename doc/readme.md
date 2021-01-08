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
          "size": 10,
          "startRow": 1,
          "endRow": 10,
          "total": 52,
          "pages": 6,
          "list": [
              {
                  "newsId": 1,
                  "title": "发布标题",
                  "type": "国内",
                  "publish_time": "2021-01-08 16:01:58.407000"
              },
              {
                  "newsId": 2,
                  "title": "发布标题0",
                  "type": "国内",
                  "publish_time": "2021-01-08 16:02:03.493000"
              },
              {
                  "newsId": 3,
                  "title": "发布标题1",
                  "type": "国内",
                  "publish_time": "2021-01-08 16:02:06.227000"
              },
              {
                  "newsId": 4,
                  "title": "发布标题2",
                  "type": "国内",
                  "publish_time": "2021-01-08 16:02:08.806000"
              },
              {
                  "newsId": 5,
                  "title": "发布标题3",
                  "type": "国内",
                  "publish_time": "2021-01-08 16:02:11.604000"
              },
              {
                  "newsId": 6,
                  "title": "发布标题4",
                  "type": "国内",
                  "publish_time": "2021-01-08 16:02:15.248000"
              },
              {
                  "newsId": 7,
                  "title": "发布标题5",
                  "type": "国内",
                  "publish_time": "2021-01-08 16:02:18.156000"
              },
              {
                  "newsId": 8,
                  "title": "发布标题6",
                  "type": "国内",
                  "publish_time": "2021-01-08 16:02:20.846000"
              },
              {
                  "newsId": 9,
                  "title": "发布标题7",
                  "type": "国内",
                  "publish_time": "2021-01-08 16:02:23.533000"
              },
              {
                  "newsId": 10,
                  "title": "发布标题8",
                  "type": "国内",
                  "publish_time": "2021-01-08 16:02:25.924000"
              }
          ],
          "prePage": 0,
          "nextPage": 2,
          "isFirstPage": true,
          "isLastPage": false,
          "hasPreviousPage": false,
          "hasNextPage": true,
          "navigatePages": 8,
          "navigatepageNums": [
              1,
              2,
              3,
              4,
              5,
              6
          ],
          "navigateFirstPage": 1,
          "navigateLastPage": 6,
          "lastPage": 6,
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
  | newsId | 新闻的主键  |

+ Response Body Params

  | Key      | Description                           |
  | -------- | ------------------------------------- |
  | user_id  | 新闻作者的主键                        |
  | nickname | 新闻作者的昵称                        |
  | own      | 布尔值，1代表查询的新闻作者是登录用户 |

+ Response Body Example

  ```json
  {
      "code": 1,
      "message": "获取成功",
      "data": {
          "news_detail": {
              "title": "发布标题",
              "content": "发布内容",
              "publish_time": "2021-01-08 16:01:58.407000",
              "type": "国内",
              "user_id": 4,
              "nickname": "hhh"
          },
          "own": 1
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


---

#### 发布新闻

+ Url: /news/publish

+ Method: POST

+ Request Body Example

  ```json
  {
      "title": "发布标题",
      "content": "发布内容",
      "typeId": 1
  }
  ```

+ Response Body Example

  ```json
  {
      "code": 1,
      "message": "发布成功",
      "data": null
  }
  ```


---

#### 删除新闻

+ Url: /news/delete

+ Method: DELETE

+ Request Body Example

  ```json
  {
      "newsId":24
  }
  ```

+ Response Body Example

  ```json
  {
      "code": 1,
      "message": "新闻删除成功",
      "data": null
  }
  ```

