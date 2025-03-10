# План тестирования функционала покупки тура

### [Ссылка на описание задачи](https://github.com/netology-code/aqa-qamid-diplom)

## Цели тестирования:
 - покрыть автотестами функционал приложения aqa-shop, автоматизировать позитивные и негативные сценарии покупки тура;
 - автоматизировать API-тесты для приложения (отправляемые запросы);
 - автоматизировать проверку запросов в БД (корректность внесения информации приложением).

## Требования к полям и тестовые данные

Требования к полям и методы получения тестовых данных описаны по [ссылке, вкладка "Требования и тестовые данные"](https://docs.google.com/spreadsheets/d/1xudSUtT3Ohd0ZiRw21g0u11naYRV4NTmquqIQHJwwtM/edit?usp=sharing)

## Тестовые сценарии

Тестовые сценарии и примеры тестовых данных приведены по [ссылке, вкладка "Перечень тестовых сценариев"](https://docs.google.com/spreadsheets/d/1xudSUtT3Ohd0ZiRw21g0u11naYRV4NTmquqIQHJwwtM/edit?usp=sharing)

## Используемые инструменты
 - Google Chrome - самый популярный на данный момент браузер;
 - DevTools - встроенный инструмент в Chrome, позволяющий в частности смотреть элементы HTML, статус и тело API-запроса;
 - IDE IntelliJ IDEA - среда разработки кода, которая кроме обычных для IDE инструментов, позволяет также подключить множество разных плагинов;
 - Git/GitHub - система контроля версий с самым большим количеством пользователей;
 - Framework: Java, Gradle, JUnit5, Selenide, Cucumber, Gherkin - популярный фреймворк для тестирования;
 - DBMS DBeaver - бесплатная система управления базой данных, поддерживающая большое разнообразие БД;
 - Docker - инструмент для контейнеризации, что позволяет обойтись без VM;
 - PostgreSQL - объектно-реляционная СУБД, поддерживающая множество типов данных, в том числе JSON;
 - Report Portal - open-source сервис, запускаемый из Docker, получающий логи тестирования для дальнейшей генерации отчетов и анализа результатов автотестирования 

## Возможные риски при автоматизации
 - изменения требований;
 - включение нового функционала в SUT;
 - долгая обратная связь от эксперта по техническим вопросам-блокерам;
 - некорректная оценка требуемых временных затрат;
 - критический баг в приложении, не позволяющий провести полноценное тестирование

## Интервальная оценка с учётом рисков в часах
1. Подготовка окружения, тестовых данных, настройка подключения к БД - 3 ч; 
2. Разработка автотестов с последующей отладкой - 14 ч;
3. Интеграция c Report Portal - 8 ч;
4. Формирование отчётных документов по итогам тестирования: баг-репорты, отчёт Report.md - 3 ч;
5. Формирование отчёта Summary.md о проведённой автоматизациии - 1 ч;
6. Доработка по замечаниям эксперта - 4 ч

## План сдачи работ 
График выполнения, [ссылка](https://docs.google.com/spreadsheets/d/1ehDEVDlgLoIK3IiIOF5lsvjMAvjGFBroLf6PhLkv3kk/edit?usp=sharing)  
Код автотестов в первой редакции вместе с результатами тестрана и отчётом по автоматизации будут представлены **09.03.25**
