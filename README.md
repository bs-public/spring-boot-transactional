# Spring Boot Transactional

---
## Basic Transactional case

### 1. Save User Success
```bash
   curl -X POST http://localhost:8080/api/users/success \
      -H "Content-Type: application/json" \
      -d '{"username":"Alice"}'
```

### 2. Save User Failure
```bash
    curl -X POST http://localhost:8080/api/users/failure \
      -H "Content-Type: application/json" \
     -d '{"username":"Bob"}'
```

### 3. Get Users
```bash
  curl http://localhost:8080/api/users
```
