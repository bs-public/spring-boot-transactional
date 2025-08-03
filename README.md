# Spring Boot Transactional

---
## 1. Transaction basic case

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

## 2. Transaction ReadOnly case

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

## Transaction Propagation case

### Propagation Type - REQUIRED
- Joins existing transaction (default)
- Both outer and inner run in same transaction.
- Exception in outer rolls back both.

```bash
   curl -X POST http://localhost:8080/api/tx/required \
      -H "Content-Type: application/json" \
      -d '{"username":"Alice"}'
```

### Propagation Type - REQUIRES_NEW
- Suspends existing and starts new transaction
- Inner runs in a separate transaction. 
- Exception in outer only rolls back outer, inner remains.

```bash
   curl -X POST http://localhost:8080/api/tx/requires-new \
      -H "Content-Type: application/json" \
      -d '{"username":"Alice"}'
```
