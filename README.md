# Book API

This is a project for Sourcery Academy Full Stack Program

## Installation
```
1) clone this repository

2) Open Docker Desktop

3) In project root directory run the "docker-compose up -d" command

4) The API should be running on "http://localhost:8080/api/books"
```

## API Reference

#### Get all books

```http
  GET /api/books
```

Query parameters:

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `currentPage` | `int` | Optional. default: 1, min: 1 |
| `pageSize` | `int` | Optional. default: 10, min: 1, max: 100 |
| `title` | `string` | Optional. max length: 100 |
| `author` | `string` | Optional. max length: 100 |
| `releaseYear` | `int` | Optional. min: 1500, max: 2100 |
| `higherOrEqualThanRating` | `double` | Optional. min: 0.0, max: 5.0 |
| `lowerOrEqualThanRating` | `double` | Optional. min: 0.0, max: 5.0 |

---

#### Get one book

```http
  GET /api/books/{id}
```

---

#### Create book

```http
  POST /api/books
```

Body parameters:

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `title` | `string` | Required. max length: 100 |
| `author` | `string` | Required. max length: 100 |
| `releaseYear` | `int` | Required. min: 1500, max: 2100 |
| `price` | `double` | Required. Must be positive |

---
#### Update book

```http
  PUT /api/books/{id}
```

Body parameters:

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `title` | `string` | Required. max length: 100 |
| `author` | `string` | Required. max length: 100 |
| `releaseYear` | `int` | Required. min: 1500, max: 2100 |
| `price` | `double` | Required. Must be positive |

---

#### Delete book

```http
  DELETE /api/books/{id}
```

---

#### Get all reviews

```http
  GET /api/reviews
```

Query parameters:

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `currentPage` | `int` | Optional. default: 1, min: 1 |
| `pageSize` | `int` | Optional. default: 10, min: 1, max: 100 |
| `stars` | `int` | Optional. min: 1, max: 5 |

---
#### Get one review

```http
  GET /api/reviews/{id}
```
---

#### Get book reviews

```http
  GET /api/books/{bookId}/reviews
```

Query parameters:

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `currentPage` | `int` | Optional. default: 1, min: 1 |
| `pageSize` | `int` | Optional. default: 10, min: 1, max: 100 |
| `stars` | `int` | Optional. min: 1, max: 5 |

---

#### Create review

```http
  POST /api/books/{bookId}/reviews
```

Body parameters:

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `title` | `string` | Required. max length: 100 |
| `content` | `string` | Required. max length: 100 |
| `stars` | `int` | Required. min: 1, max: 5 |

---

#### Update review

```http
  PUT /api/reviews/{id}
```

Body parameters:

| Parameter | Type     | Description                |
| :-------- | :------- | :------------------------- |
| `title` | `string` | Required. max length: 100 |
| `content` | `string` | Required. max length: 100 |
| `stars` | `int` | Required. min: 1, max: 5 |

---

#### Delete review

```http
  Delete /api/reviews/{id}
```
