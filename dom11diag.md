```mermaid
@startuml
skinparam classAttributeIconSize 0
skinparam shadowing false
skinparam nodesep 70
skinparam ranksep 80
skinparam class {
    BackgroundColor #E3F2FD
    BorderColor #1565C0
    ArrowColor #1565C0
}

title Internet-duken: UML Klass diagrammasy

abstract class Paydalanushy {
    +ID : int
    +AtyJoni : string
    +Email : string
    +Mekenjay : string
    +Telefon : string
    +Roli : string
    --
    +Tirkelu()
    +Kiru()
    +DerekterdiJangartu()
}

class Klient {
    +BonustykBall : int
    +NacislitBall(somma)
    +TapsyrytarTarihy()
}

class Akimshi {
    +LogKuru(areket)
    +TauarKosu()
    +TauarOshiru()
}

class Tauar {
    +ID : int
    +Atau : string
    +Sipattama : string
    +Bagasy : decimal
    +QoymadagySany : int
    +Foto : string
    +JenildikProcenti : decimal
    --
    +Jangartu()
    +Oshiru()
    +QoljetimdilikTekseru() : bool
}

class Kategoriya {
    +ID : int
    +Atau : string
}

class Tapsyrys {
    +ID : int
    +JasalganKuni : DateTime
    +Status : enum {Oformlen, Joneltilgen, Jetkizilgen, Toqtatyldy}
    +JalpySomasy : decimal
    +Promokod : string
    --
    +Rastau()
    +Toqtatuy()
    +Toleu()
    +SomanyEsepteu()
}

class TapsyrysJoly {
    +Sany : int
    +BirlikBagasy : decimal
    +Jiyntyq : decimal
}

class Tolem {
    +ID : int
    +Turi : enum {Karta, ElKoshelek, QolmaQol}
    +Somasy : decimal
    +Status : enum {Satti, Satsiz, Qaytaryldy}
    +Kuni : DateTime
    --
    +Ondeu()
    +Qaytaru()
}

class Jetkizu {
    +ID : int
    +Mekenjay : string
    +Status : enum {Dayyndaluda, Jolda, Jetkizildi}
    +Kuryer : string
    +TrekNomiri : string
    --
    +Jonelty()
    +Baqylau()
    +Ayaqtau()
}

class Pikir {
    +ID : int
    +Bagasy : int (1-5)
    +Matini : string
    +Kuni : DateTime
}

class Qoyma {
    +ID : int
    +Atau : string
    +Mekenjay : string
}

' Muragerlik
Paydalanushy <|-- Klient
Paydalanushy <|-- Akimshi

' Assotsiatsiyalar
Klient ||--o{ Tapsyrys : jasaydy
Tapsyrys }o--|| Klient

Tapsyrys ||--o{ TapsyrysJoly
TapsyrysJoly }o--o{ Tauar : qamtydy

Tauar }o--|| Kategoriya : tiesili

Tapsyrys ||--|| Tolem : baylanysty
Tapsyrys ||--|| Jetkizu : baylanysty

Klient ||--o{ Pikir : jazady
Tauar ||--o{ Pikir : alady

Tauar }o--o{ Qoyma : saqtala

note right of TapsyrysJoly
  Tapsyrys pen Tauar arasyn
  kop-ke-kop qatynasty
  juzegе asyrady
end note

note bottom of Paydalanushy
  Abstrakti klass – tikеley obekt
  qurylmaydy, tek muragerlik ushin
end note
@enduml

```
