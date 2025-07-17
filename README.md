# 🔐 NMS Backend – Secure Spring Boot Authentication API

A robust and secure **Spring Boot-based REST API** that supports encrypted **user signup/login**, **JWT-based authentication**, and **role-based authorization**. Built for scalable and secure backend systems.

---

## 📦 Tech Stack

- 🟨 Java 17+
- ☕ Spring Boot
- 🔐 Spring Security
- ⚙️ Maven
- 🛡️ JWT (JJWT)
- 🧊 AES Encryption (JWE)
- 🛢️ MySQL / H2 (optional for testing)

---

## 🚀 Getting Started

### 🛠️ Prerequisites

Ensure you have the following installed:

- [Java JDK 17+](https://adoptium.net/)
- [Maven](https://maven.apache.org/download.cgi)
- [MySQL](https://dev.mysql.com/downloads/) or use H2 (in-memory DB)

---

### 🧱 Build & Run

# Clone the repository
git clone https://github.com/your-username/nms-backend.git
cd nms-backend

# Build the project
mvn clean install

# Run the project
mvn spring-boot:run
🔗 API Endpoints
📝 Signup – POST /nms/user/v1/signup
Request Body (All passwords must be AES-encrypted):

json
Copy
Edit
{
  "customerType": "customer",
  "email": "user@example.com",
  "userPassword": "EncryptedAESPasswordHere",
  "confirmPassword": "EncryptedAESPasswordHere",
  "firstName": "Hardeep",
  "lastName": "Singh",
  "isoCountry": "IN",
  "phone": "9876543210"
}
🔐 Login – POST /nms/user/v1/login
Request Body:

json
Copy
Edit
{
  "email": "user@example.com",
  "userPassword": "EncryptedAESPasswordHere"
}
✅ Returns a JWT token on successful authentication.

🧪 AES Encryption Tool (Optional)
Use this Java utility method to encrypt passwords before sending them via API:

java
Copy
Edit
String plainPassword = "Test@123";
String encrypted = AesEncryptionUtil.encrypt(plainPassword);
🛡️ Security Features
🔒 AES (JWE) encryption for password transmission

🔑 BCrypt password hashing for secure storage

🔐 JWT token generation and validation

🎯 Role-based access control (ROLE_USER, ROLE_ADMIN)

📛 Endpoint protection using annotations like @PreAuthorize

📁 Project Structure
nms-backend/
├── config/                     # Security configurations (JWT, filters, etc.)
│   └── SecurityConfig.java
├── controller/                 # API endpoint controllers
├── dto/                        # Data Transfer Objects
├── model/                      # JPA entity classes
├── repository/                 # Spring Data JPA repositories
├── service/                    # Business logic layer
├── util/                       # Utility classes
│   ├── AesEncryptionUtil.java
│   ├── JwtUtil.java
│   └── PasswordEncoderUtil.java
└── NmsBackendApplication.java  # Main application file


🧪 Sample Data (Optional)
You can either:

Use the signup API with AES-encrypted passwords, or

Insert users directly into the database:

sql
Copy
Edit
INSERT INTO users (...) VALUES (...);
INSERT INTO user_roles (...) VALUES (..., 'ROLE_USER');
