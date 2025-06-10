# Java Hastane Otomasyonu

Bu proje, Java Swing ile geliştirilmiş bir Hastane Otomasyon Sistemidir. Kullanıcılar (hasta, doktor, başhekim) için giriş, kayıt, randevu ve poliklinik yönetimi gibi temel işlevleri içerir. Veritabanı olarak Microsoft SQL Server kullanılmaktadır ve Docker ile kolayca ayağa kaldırılabilir.

## Özellikler

- Hasta, doktor ve başhekim giriş/kayıt işlemleri
- Randevu alma ve yönetimi
- Poliklinik ekleme, silme, güncelleme
- Doktor çalışma saatleri yönetimi
- Modern Swing arayüzü

## Gereksinimler

- Java 8 veya üzeri
- Maven veya IDE (NetBeans, IntelliJ, Eclipse)
- Docker (SQL Server için)
- SQL Server JDBC Driver (`com.microsoft.sqlserver.jdbc.SQLServerDriver`)
- [jcalendar](https://toedter.com/jcalendar/) kütüphanesi

## Kurulum

### 1. Docker ile MSSQL Sunucusu Kurulumu

Proje kök dizininde bulunan `docker-compose.yml` dosyası ile MSSQL sunucusunu başlatabilirsiniz:

```sh
docker-compose up -d
```

- MSSQL sunucusu `localhost:14330` portunda çalışacaktır.
- Varsayılan kullanıcı adı: `sa`
- Varsayılan şifre: `1234`
- Veritabanı adı: `hospital` (manuel oluşturulmalı)

### 2. Veritabanı Oluşturma

Docker ile MSSQL ayağa kalktıktan sonra, bir SQL istemcisi (Azure Data Studio, DBeaver, SSMS, vs.) ile bağlanıp aşağıdaki komutları çalıştırarak veritabanını ve tabloları oluşturun:

```sql
CREATE DATABASE hospital;
GO
USE hospital;
GO

CREATE TABLE users (
    id INT IDENTITY(1,1) PRIMARY KEY,
    tcno VARCHAR(11) NOT NULL,
    password VARCHAR(50) NOT NULL,
    name VARCHAR(100) NOT NULL,
    type VARCHAR(20) NOT NULL
);

CREATE TABLE clinics (
    id INT IDENTITY(1,1) PRIMARY KEY,
    name VARCHAR(100) NOT NULL
);

CREATE TABLE appointments (
    id INT IDENTITY(1,1) PRIMARY KEY,
    doctor_id INT,
    doctor_name VARCHAR(100),
    hasta_id INT,
    hasta_name VARCHAR(100),
    app_date VARCHAR(50)
);

CREATE TABLE whour (
    id INT IDENTITY(1,1) PRIMARY KEY,
    doctor_id INT,
    doctor_name VARCHAR(100),
    wdate VARCHAR(50),
    status VARCHAR(1) DEFAULT 'a'
);

CREATE TABLE workers (
    id INT IDENTITY(1,1) PRIMARY KEY,
    user_id INT,
    clinic_id INT
);
```

### 3. Projeyi Çalıştırma

1. Gerekli Java bağımlılıklarını (özellikle JDBC ve jcalendar) projenize ekleyin.
2. `src\Helper\DBConnection.java` dosyasındaki bağlantı ayarlarının yukarıdaki bilgilerle uyumlu olduğundan emin olun.
3. Projeyi IDE üzerinden veya komut satırından başlatın.

## Kullanıcı Rolleri

- **Hasta:** Kayıt olabilir, giriş yapabilir, randevu alabilir.
- **Doktor:** Giriş yapabilir, çalışma saatlerini yönetebilir.
- **Başhekim:** Giriş yapabilir, doktor ve poliklinik yönetimi yapabilir.

## Notlar

- Varsayılan olarak herhangi bir kullanıcı ekli değildir. İlk kullanıcıları başhekim olarak veritabanına manuel ekleyebilirsiniz.
- Docker konteyneri ilk açıldığında veritabanı otomatik oluşmaz, yukarıdaki SQL komutları ile oluşturulmalıdır.
- Şifreler ve bağlantı bilgileri örnek amaçlıdır, gerçek ortamda değiştiriniz.

## Lisans

Bu proje eğitim amaçlıdır.
