# CAF SoftSol Assignment

Spring Boot project implementing:

- **Assignment-1** → Config File Loader + Section Query API  
- **Assignment-2** → TSV Price Engine + Price Lookup API  
- Developed using **TDD (Test Driven Development)** with unit tests.

---

## 🏗️ Project Structure

```

src/main/java/com/tanmoy/assignment_service
├── controller
│   ├── ConfigController.java      <-- Assignment-1 API (Q2)
│   ├── PriceController.java       <-- Assignment-2 API (Q2)
├── loader
│   └── ConfigLoader.java          <-- Assignment-1 Loader (Q1)
├── service
│   ├── ConfigService.java         <-- Assignment-1 Service
│   └── PriceEngine.java           <-- Assignment-2 Engine (Q1a/Q1b)
├── model
│   └── PriceSlot.java             <-- Assignment-2 Model

```
```

src/test/java/com/tanmoy/assignment_service
├── ConfigServiceTests.java        <-- Assignment-1 TDD Tests (Q3)
├── PriceEngineTests.java          <-- Assignment-2 TDD Tests (Q3)

```
```

src/test/resources
├── config.txt                     <-- Assignment-1 input file
├── prices.tsv                     <-- Assignment-2 input file

````

---

## ▶️ Running the Application

### Using Maven Wrapper

```bash
./mvnw spring-boot:run
````

Windows:

```bash
mvnw.cmd spring-boot:run
```

App starts at:

```
http://localhost:8080
```

---

## 🟢 ASSIGNMENT-1 — Config Loader

### ✔ **Q1 — Load config & store in-memory**

File:

```
src/test/resources/config.txt
```

Loader class:

```
ConfigLoader.java
```

Loads section-wise key/value config into memory at startup.

---

### ✔ **Q2 — API to fetch section details**

Controller:

```
ConfigController.java
```

Endpoint:

```
GET /config?section=<Section Name>
```

Example:

```
http://localhost:8080/config?section=Order Service
```

Expected JSON:

```json
{
  "broker": "https://orbroker.in",
  "topic": ["test_os_topic_1","test_os_topic_2"]
}
```

---

### ✔ **Q3 — TDD Unit Tests**

Test file:

```
ConfigServiceTests.java
```

Run:

```bash
mvn test
```

---

## 🟣 ASSIGNMENT-2 — TSV Price Engine

### ✔ **Q1a — Upload TSV & load for fast lookup**

Upload API (POST):

```
/price/upload
```

Postman → Body → form-data

| Key  | Type | Value      |
| ---- | ---- | ---------- |
| file | File | prices.tsv |

File path used during testing:

```
src/test/resources/prices.tsv
```

---

### ✔ **Q1b — Data stored in in-memory price map**

Implemented in:

```
PriceEngine.java
```

---

### ✔ **Q2 — Price Lookup API**

Endpoint:

```
GET /price?skuid=<id>&time=<HH:mm>
```

Examples:

| Input                      | Expected Result |
| -------------------------- | --------------- |
| skuid=u00006541&time=09:55 | NOT SET         |
| skuid=u00006541&time=10:03 | 101             |
| skuid=u00006541&time=10:05 | 99              |

---

### ✔ **Q3 — TDD Unit Tests**

Test file:

```
PriceEngineTests.java
```

Run:

```bash
mvn test
```

---

## 🧪 How to Verify Each Question (Evaluator Checklist)

### **Assignment-1**

| Question | How to verify            | File / API                         |
| -------- | ------------------------ | ---------------------------------- |
| Q1       | Config loads in memory   | `ConfigLoader.java` + `config.txt` |
| Q2       | GET section returns JSON | `/config?section=Order Service`    |
| Q3       | Unit tests pass          | `ConfigServiceTests.java`          |

### **Assignment-2**

| Question | How to verify            | File / API              |
| -------- | ------------------------ | ----------------------- |
| Q1a      | TSV upload works         | `/price/upload`         |
| Q1b      | Data stored in memory    | `PriceEngine.java`      |
| Q2       | Price lookup logic works | `/price?skuid=&time=`   |
| Q3       | Unit tests pass          | `PriceEngineTests.java` |
| Q4       | Code in GitHub           | Repository link         |

---

## 📝 Notes

* Application follows **TDD approach**
* Data is stored **in-memory for fast retrieval**
* Supports multiple topics & overlapping price slots

---

## ✔ Build & Test Together

```bash
./mvnw clean test
```
