#  Precariada Shop - E-commerce Backend

## Overview
Precariada Shop is the backend API for an e-commerce platform featuring products from Precariada, a Madrid-based illustrator. The API handles products, categories, users, and shopping cart functionality.

## ✨ Features
- User Management: Create, read, update, and delete users
- Product Catalog: Manage products with categories
- Shopping Cart: Add/remove products, calculate totals
- Validation: Comprehensive input validation
- Error Handling: Custom exceptions and global handler

## 🛠️ Technologies

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![Spring Boot](https://img.shields.io/badge/Spring_Boot-6DB33F?style=for-the-badge&logo=spring-boot&logoColor=white)
![IntelliJ IDEA](https://img.shields.io/badge/IntelliJIDEA-000000.svg?style=for-the-badge&logo=intellij-idea&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-4479A1.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Postman](https://img.shields.io/badge/Postman-FF6C37?style=for-the-badge&logo=postman&logoColor=white)
![Apache Maven](https://img.shields.io/badge/Apache%20Maven-C71A36?style=for-the-badge&logo=Apache%20Maven&logoColor=white)
![Git](https://img.shields.io/badge/git-%23F05033.svg?style=for-the-badge&logo=git&logoColor=white)

## 🚀 Getting Started

### Running the Application

- Ensure you have Java 21 and Maven installed
- Clone the repository
```
git clone
https://github.com/femcoders-ecommerce/precariadashop.git
```
- Create a MySQL database named precariadashop
Run:
```
mvn spring-boot:run
```
The API will be available at http://localhost:8080

### Testing with Postman

Import the Postman collection (available in the project) to test all endpoints with pre-configured examples.

Example requests:

Create Product:
```
POST /api/products

{
"name": "Lámina Eres casa",
"price": 20,00,
"imageUrl": "https://shorturl.at/4tcJI",
"featured": true,
"categoryId": 2
}
```

Create User:
```
POST /api/users

{
"username": "maria",
"email": "maria@example.com",
"password": "127GHqk8#@"
}
```

## 📂 Structure

### Project Structure

```
com.precariada.precariadashop
├── controllers/
│   ├── CartController
│   ├── CategoryController
│   ├── ProductController
│   └── UserController
├── dtos/
│   ├── cart/
│   │   ├── CartDTO
│   │   └── CartMapper
│   ├── cartItem/
│   │   └── CartItemDTO
│   │   └── CartItemMapper
│   ├── category/
│   │   ├── CategoryMapper
│   │   ├── CategoryRequest
│   │   └── CategoryResponse
│   ├── product/
│   │   ├── ProductMapper
│   │   ├── ProductRequest
│   │   └── ProductResponse
│   └── users/
│       ├── UserMapper
│       ├── UserRequest
│       └── UserResponse
├── exceptions/ 
│   ├── ErrorResponse
│   └── GlobalExceptionHandler
├── models/
│   ├── Cart
│   ├── CartItem
│   ├── Category
│   ├── Product
│   └── User
├── repositories/
│   ├── CartItemRepository
│   ├── CartRepository
│   ├── CategoryRepository
│   ├── ProductRepository
│   └── UserRepository
├── services/         
│   ├── CartItemService
│   ├── CartService
│   ├── CategoryService
│   ├── ProductService
│   └── UserService
└── PrecariadashopApplication 
```
### Entity Relationship (ER) Diagram

![ER Diagram](img.png)

###  Flow Chart
[Click to access the Flow Chart](https://postimg.cc/XG8dq3QL)

## 👥 Team Members

- Lara Pla [@Lizar22](https://github.com/Lizar22)
- May Carrascal [@may-leth](https://github.com/may-leth) 
- Sofia Santos [@sofianutria](https://github.com/sofianutria)
- Thais Rocha [@thaisrqueiroz](https://github.com/thaisrqueiroz)  