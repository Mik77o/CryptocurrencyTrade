# Exchange Monitor
> Projekt w trakcie rozwijania związany z giełdami kryptowalut

## Spis treści
* [Ogólne informacje](#ogólne-informacje)
* [Technologie](#technologie)
* [Baza danych](#baza-danych)
* [Połączenie z lokalnym serwerem](#połączenie-z-lokalnym-serwerem)
* [Połączenie z zdalnym serwerem](#połączenie-z-zdalnym-serwerem)
* [Tworzenie tabeli](#tworzenie-tabeli)
* [Web Controller](#web-controller)
* [Twilio SMS](#twilio-sms)
* [Dodatkowe pluginy](#dodatkowe-pluginy)
* [Screenshots](#screenshots)

## Ogólne informacje
Projekt obejmuje pobieranie informacji dotyczących kryptowalut z giełd: Bitbay, Bitfinex, Bittrex, Poloniex, Wex
i przetwarzanie tych informacji, podawanie różnych statystyk. Informacje zapisywane są w bazie danych po wywołaniu 
odpowiednich metod RestControllera o którym jest mowa poniżej

## Technologie
* Java
* Spring Boot
* Spring Data (Spring JPA)
* Meaven
* XML
* SQL
* JSON

## Baza danych
* PostgreSQL 10.5
* pgAdmin4 (uruchamia się w przeglądarce, przydatne narzędzie: _psql.exe_)

Podczas instalacji PostgreSQL-a należy podać hasło do lokalnego serwera oraz port.
Dodatkowe oprogramowanie można pobrać przy pomocy Application Stack Builder.
Należy także dodać zmienne środowiskowe:

![first](./img/first.png)


![two](./img/two.png)


![three](./img/three.png)

Konfiguracja PostgreSQL-a: pliki: `pg_hba.conf`   `postgresql.conf` 
Folder `data` znajdujący się w folderze PostgreSQL

## Połączenie z lokalnym serwerem
Plik _application.properties_

Przykładowy kod:

`spring.datasource.url=jdbc:postgresql://localhost/postgres`
`spring.datasource.username=postgres`
`spring.datasource.password=spotlike18`
`spring.jpa.generate-ddl=true`
`server.port=8000`
`spring.jpa.properties.hibernate.jdbc.lob.non_contextual_creation=true`

## Połączenie z zdalnym serwerem

 postgres://nfokbrfc:53AXqgx-bdBVRLBYvzRfxRRS0_ij4qxF@horton.elephantsql.com:5432/nfokbrfc

## Tworzenie tabeli
Przykładowy kod dla giełdy Bitbay:
`-- Table: public.bitbay`
`-- DROP TABLE public.bitbay;`

`CREATE TABLE public.bitbay`
`(`
    `id bigint NOT NULL,`
    `kurs double precision,`
    `data timestamp without time zone,`
    `procent double precision,`
    `CONSTRAINT test_pk PRIMARY KEY (id)`
`)`
`WITH (`
    `OIDS = FALSE`
`)`
`TABLESPACE pg_default;`

`ALTER TABLE public.bitbay`
    `OWNER to postgres;`

Kolumna _procent_ dotyczy obliczania różnicy procentowej w wartości kursu w dowolnym przedziale czasu

## Web Controller
Przy pomocy RestControllera w Spring Boot można uzyskiwać różne informacje bazy danych za pomocą
odpwiedniego adresu, na lokalnym serwerze wygląda to tak:

metoda _save_ zapisuje pojedynczy rekord do wybranej tabeli lub do wszystkich na raz

* Aby zapisać pojedynczy rekord do tabeli _bitbay_ podajemy w przeglądarce następujący adres:

`http://localhost:8000/save?nameOfExchange=bitbay`

Domyślnie wywołanie metody save bez podania parametru zapisuje rekord do każdej z tabeli
związanej z konkretnę giełdą lub podanie parametru `all` spowoduje taki sam efekt

* Aby wyświetlić wszystkie informacje z tabel: _kurs_, _data_, _procent_
należy wywołać metodę _findall_ lub _findnameOfExchange_ dla danej giełdy:

`http://localhost:8000/findall`

`http://localhost:8000/findallbitbay`

`http://localhost:8000/findallbitfinex`

`http://localhost:8000/findallbittrex`

`http://localhost:8000/findallpoloniex`

`http://localhost:8000/findallwex`

* Aby otrzymać informację o maksymalnej lub minimalnej wartości kursu (pary ETHBTC)
z danych przechowywanych w tabeli oraz datę i czas, kiedy te wartości występowały
należy wywołać metody _max_ lub _min_:

`http://localhost:8000/min`
`http://localhost:8000/max`

* Usunięcie wszystkich rekordów z tabel następuję poprzez wywołanie metody _deleteAllRecords_:

`http://localhost:8000/deleteAllRecords`

## Twilio SMS

* W kontrolerze nie wspomniano jeszcze o jednej metodzie: _saveStillAll_ .
Metoda ta dodaje dane ze wszystkich giełd co 10 sekund (czas ten można zmienić).
Dane zapisywane są do bazy danych, a gdy w zadanym przedziale czasu nastąpi określona
różnica w tym przypadku dla pary ETHBTC (może być inne) wysyłane zostaje powiadomienie
na SMS-a, dla numeru, który podaje się tworząć konto Twilio. Twilio nie jest darmowym
REST API jeśli jest wykorzystywane do celów komercyjnych.

Wywołanie metody: `http://localhost:8000/saveStillAll`

* Tworzenie konta w Twilio

https://www.twilio.com/
Utworzenie konta jest darmowe. Utworzono Programmable SMS Dashboard. Otrzymano jeden
numer Twilio. Weryfkacja następuje poprzez wpisywania kodu AUTHMSG. Aby móc otrzymywać
powiadomienia na swój telefon niezbędne jest podanie numeru w kodzie oraz wartości parametrów:
`ACCOUNT_SID`, `AUTH_TOKEN` które zostają generowane po utworzeniu konta.

![twilio1](./img/twilio1.png)


![twilio2](./img/twilio2.png)

* Przykładowa wiadomość SMS:

![twilio3](./img/twilio3.png)


## Dodatkowe pluginy
Plik _pom.xml_ dostarcza informacji o wykorzystanych bibliotekach.
W celu dodania biblioteki Lombok oprócz dodania `<dependency>` 
w _pom.xml_ należy pobrać plugin do Intelij 
i go zainstalować oraz zrestartować Intelij 
(instalacja plugina odbywa się poprzez wejście w: 
File -> Settings -> Plugins ->...
Wersja plugina w projekcie: lombok-plugin-0.19-2018.1
https://plugins.jetbrains.com/plugin/6317-lombok-plugin

Podobnie z biblioteką związaną z klasą _Serializable_
GenerateSerialVersionUID_plugin_V3.0.3

## Screenshots
![findall](./img/findall.png)


![max](./img/max.png)


![min](./img/min.png)


![findall_bitbay](./img/findall_bitbay.png)


![bitbay_tab](./img/bitbay_tab.png)
