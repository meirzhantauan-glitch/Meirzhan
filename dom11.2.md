```mermaid 
@startuml ComponentDiagram
title UML Компонент Диаграммасы: Логистикалық Жүйе

' Компоненттерді анықтау
component [Frontend (Web/Mobile)] as FE
component [Backend / Biznes Logika] as BE
component [Məlіmetter bazasy] as DB
component [Marshrutty Optim. Modul] as RO
component [Kurierler Integr. Modul] as CI
component [Habarlama Zhuyesi] as NS
component [Skladty Basqaru Moduli] as IM

' Интерфейстерді анықтау (Ортақ нотация)
interface "REST API" as REST
interface "Database (SQL)" as SQL
interface "Inventory API" as INV
interface "Route Optimization API" as ROUTE_OPT
interface "Courier API" as COURIER
interface "Notification Service" as NOTIF

' Сыртқы жүйелер
component [Syrtyqy Kuriier API] as ExtCourier
component [Tolem Zhuyesi (Vneshniy)] as Payment
component [SMS/Email API] as ExtNotif

' Қатынастар (Жеткізілетін және Талап етілетін интерфейстер)

' Frontend - Backend
FE -[ REST

' Backend - Интерфейстер
BE .right.o NOTIF : uses
NS .left. NOTIF

BE .up.o ROUTE_OPT : uses
RO .down. ROUTE_OPT

BE .left.o COURIER : uses
CI .right. COURIER

BE .down.o SQL : uses
DB .up. SQL

' Склад Модулі - База
IM .down.o SQL : uses

' Склад Модулі - Backend (интерфейс арқылы)
BE .up.o INV : uses
IM .down. INV

' Сыртқы интеграциялар
CI --> ExtCourier : REST/Webhook
NS --> ExtNotif : API

' Backend және Төлем Жүйесі (Сыртқы жүйе)
Payment -- BE : Tolem / Qaytarym

@enduml

```
