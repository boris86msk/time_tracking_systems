# Method execution time accounting systems

### Цифровая академия T1

### Открытая школа разработчиков JAVA

Система учета времени выполнения методов в приложении с использованием Spring AOP.
Система способна асинхронно логировать и анализировать данные о времени выполнения
синхронных и асинхронных методов.

### Стек технологий:

+ Java 17;
+ Maven;
+ Spring boot 3;
+ Spring AOP;
+ Spring Data;
+ Liquibase;
+ Docker;
+ Lombok;
+ Postgres. 

### Запуск приложения:
<p>1. Клонируем проект "git clone https://github.com/boris86msk/time_tracking_systems"</p>
<p>2. Запускаем Docker-контейнер docker compose up -d.</p>
<p>3. Для сборки проекта в корне проекта выполняем команду "mvn install".</p>
<p>4. Запуска приложения "java -jar target/timetrackingsystems-0.0.1-SNAPSHOT.jar". По умолчанию риложение
доступно на http://localhost:8081/</p>

### API
Информация о методах возвращается в формате JSON, пример:<br />
{<br />
"id": 17,<br />
"name": "myTestMethod",<br />
"executed": "2024-04-08T21:24:21.016002",<br />
"nanoSecond": 2089100,<br />
"milliSecond": 2,<br />
"asynchronous": false<br />
}
+ GET /allTracking - полный список обработанных методов.
+ GET /fast_methods - методы с временем выполнения менее 1 мс.
+ GET /long_methods - методы с временем выполнения более 1 сек.
+ GET /async_methods - список методов,выполненных асинхронно.
+ GET /avg_time - среднее время выполнения всех методов. Возвращает long.
+ GET /by_name/{name} - поиск метода по имени/части имени.