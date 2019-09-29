# Retrofit Rest Client


> The best for Android Studio.
>
> This plugin is base on [idea-rest-client](https://github.com/danblack/idea-rest-client).


- **Support run retrofit api interface functions directly.**
- Supports GET, POST, PUT, DELETE, PATCH requests
- Multiline request parameters
- Rest file type support
- Internally supported dynamic parameters
- Response auto format (based on response Content-Type header)
- Customized colors and fonts
- Customized keyboard shortcuts
- Comments

### Preview

- Rest file

You can create`rest` file to edit.

Content structure

![PreView](pic/s1.png)

- Gif

![](pic/GIF.gif)

### Demo

- GET

```rest
GET
{BASE_URL}/get
# query params
&id=1
&type=2

# headers
@Content-Type: application/json
@timestamp: {TS_SEC} # 
```

- POST

  - Json

  Apponit content-type: `@Content-Type: application/json`
  ```rest
  POST
  {BASE_URL}/post_json
  
  # request headers
  @Content-Type: application/json
  
  # request body
  {"a":"{a}","b":"{b}"}
  ```

  - Form

  Apponit content-type: `@Content-Type: application/x-www-form-urlencoded`, and last line is form data(can be multiple line)

  ```rest
  POST
  {BASE_URL}/post_form/{user_id}
  
  # Headers:
  @Content-Type: application/x-www-form-urlencoded
  
  # Form data separate with '&'
  id=1&s=hello
  
  # also can be:
  &id=1
  &s=hello
  ```



### Set env params

You can use `{name}` to reference env variable.

1. To create json file `{PROJECT_DIR}/rest-client/rest_env.json`

```json
{
  "test": {
    "BASE_URL": "http://localhost/test",
    "user_id": "1",
    "a" : "a",
    "b" : "b"
  },
  "dev": {
    "BASE_URL": "http://localhost/dev"
  }
}
```

2. chose env to tun

![](pic/s2.png)

### Install

TODO


### Thanks

This plugin is base on [idea-rest-client](https://github.com/danblack/idea-rest-client).

### TODO



- Multiple requests in one file. [like http client]

- Response result output tu run window.  [like http client]

