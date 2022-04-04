## carbon API

### Add key

#### POST `/api/key`

##### Request

```json
{
  "appId": "carbon",
  "pageCode": "home_page",
  "key": "key.home.greeting",
  "original": [
    {
      "locale": "zh",
      "text": "你好，很高兴见到你！"
    }
  ]
}
```

##### Response

- HTTP 200 OK

---

### Update key / Translate key

#### PUT `/api/key`

##### Request

```json
{
  "appId": "carbon",
  "key": "key.home.greeting",
  "translation": [
    {
      "locale": "en-US",
      "text": "Hi, nice to meet you!"
    }
  ]
}
```

##### Response

- HTTP 200 OK

---

### Get key translation

#### GET `/api/key`

##### Request

###### Query

- appId: `carbon`
- key: `key.home.greeting`
- locale: `en-US`

##### Response

```json
{
  "locale": "en-US",
  "text": "Hi, nice to meet you!"
}
```