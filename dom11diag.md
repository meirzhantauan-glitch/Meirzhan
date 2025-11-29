```mermaid
@startuml
skinparam classAttributeIconSize 0
skinparam shadowing false

abstract class User {
    -id: Long
    -name: String
    -email: String
    -phone: String
    -address: String
    -role: Role
    +register()
    +login()
    +updateProfile()
    +getOrders(): List<Order>
}

class Customer {
    -loyaltyPoints: int
    +addLoyaltyPoints(points: int)
    +applyDiscount(code: String): double
}

class Administrator {
    +logAction(action: String)
    +manageProducts()
    +manageUsers()
}

enum Role { CLIENT, ADMIN }

class Category {
    -id: Long
    -name: String
    -parentCategory: Category
}

class Product {
    -id: Long
    -name: String
    -description: String
    -price: BigDecimal
    -stockQuantity: int
    -discountPercent: double
    -images: List<String>
    +applyDiscount(percent: double)
    +reduceStock(quantity Puj: int)
    +increaseStock(quantity: int)
}

abstract class Payment {
    -id: Long
    -amount: BigDecimal
    -date: LocalDateTime
    -status: PaymentStatus
    +process(): boolean
    +refund(): boolean
}

class CardPayment extends Payment {
    -cardLastFour: String
    -cardType: String
}

class WalletPayment extends Payment {
    -walletProvider: String
}

enum PaymentStatus { PENDING, COMPLETED, FAILED, REFUNDED }

class Order {
    -id: Long
    -orderDate: LocalDateTime
    -status: OrderStatus
    -totalAmount: BigDecimal
    -promoCode: String
    -discountAmount: BigDecimal
    +calculateTotal()
    +applyPromoCode(code: String)
    +placeOrder()
    +cancel()
    +pay(payment: Payment)
}

class OrderItem {
    -quantity: int
    -unitPrice: BigDecimal
    -discountApplied: BigDecimal
}

class Delivery {
    -id: Long
    -address: String
    -deliveryStatus: DeliveryStatus
    -trackingNumber: String
    -courierName: String
    -estimatedDate: LocalDate
    +track()
    +complete()
}

class Review {
    -id: Long
    -rating: int (1-5)
    -comment: String
    -date: LocalDateTime
    -isApproved: boolean
}

class PromoCode {
    -code: String
    -discountPercent: double
    -validUntil: LocalDate
    -minOrderAmount: BigDecimal
    +isValid(orderAmount: BigDecimal): boolean
}

class Warehouse {
    -id: Long
    -name: String
    -location: String
}

class ProductWarehouse {
    -quantity: int
}

enum OrderStatus { PENDING, CONFIRMED, PAID, SHIPPED, DELIVERED, CANCELLED, REFUNDED }
enum DeliveryStatus { PREPARING, IN_TRANSIT, DELIVERED, FAILED }

' Наследование
User <|-- Customer
User <|-- Administrator
Payment <|-- CardPayment
Payment <|-- WalletPayment

' Связи
Customer "1" --> "0.." Order : places
Order "1" --> "1" Customer : belongs to
Order "1" --> "1" Delivery : has
Order "1" --> "0.." OrderItem : contains
OrderItem "many" --> "1" Product : refers to

Product "many" --> "1" Category : belongs to
Product "many" --> "0.." Review : has reviews

Customer "1" --> "0.." Review : writes

ProductWarehouse "many" --> "1" Product
ProductWarehouse "many" --> "1" Warehouse

Order "0.." --> "1" Payment : paid with

' Дополнительно: абстрактный класс Payment использован как пример полиморфизма
note right of Payment
  Абстрактный класс Payment позволяет
  легко добавлять новые способы оплаты
  (Apple Pay, Crypto и т.д.) без изменения
  существующего кода Order.
end note

@enduml
```
