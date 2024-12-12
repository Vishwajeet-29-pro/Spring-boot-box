#### curl request

##### POST
```bash
 Invoke-WebRequest -Uri http://localhost:8080/api/user-profile `
>> -Method POST `
>> -Headers @{ "Content-Type" = "application/json" } `
>> -Body '{"username":"johndoe","email":"johndoe@example.com","phone":"1234567890","isActive":true}'
```

#### GET
```bash
Invoke-WebRequest -Uri http://localhost:8080/api/user-profile -Method GET
```