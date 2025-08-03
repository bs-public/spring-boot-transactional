# Spring Boot Transactional

---
## 1. Basic Transaction case

### Save User Success
```bash
   curl -X POST http://localhost:8080/api/users/success \
      -H "Content-Type: application/json" \
      -d '{"username":"Alice"}'
```

### Save User Failure
```bash
    curl -X POST http://localhost:8080/api/users/failure \
      -H "Content-Type: application/json" \
     -d '{"username":"Bob"}'
```

### Get Users
```bash
  curl http://localhost:8080/api/users
```
---

## 2. ReadOnly Transaction case

### Attempt WriteInReadOnly
```bash
    curl -X POST http://localhost:8080/api/users/readonly-write\
      -H "Content-Type: application/json" \
     -d '{"username":"Bob"}'
```

### Get Users ReadOnly
```bash
  curl http://localhost:8080/api/users/readonly
```
---
