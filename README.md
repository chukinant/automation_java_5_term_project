## Процедура подготовки и запуска автотестов.
Предусловие: на локальный ПК должен быть скачан [репозиторий с кодом автотестов](https://github.com/chukinant/automation_java_5_term_project). Это можно сделать запустив в терминале команду `git clone --single-branch --branch main {url репозитория} {название папки (опционально)}`.

Шаги:
1. Запустить Docker Desktop.
2. Открыть проект в IntelliJ IDEA.
3. В терминале запустить команду `docker-compose -f docker-compose-postgres.yml -p term_project_db up -d --force-recreate --build`, убедиться что контейнер запустился и работает.
4. Запустить приложение командой `java -jar ./artifacts/aqa-shop.jar`
5. Когда приложение запустится, в терминале в логах появится запись типа "ru.netology.shop.ShopApplication: Started ShopApplication in 3.892 seconds (JVM running for 4.302)"
6. Открыть другое окно терминала, запустить команду `./gradlew clean test --stacktrace --info`
7. Для получения отчета Allure запустить `./gradlew allureReport`, далее открыть сгенерированный index.html из папки .\build\reports\allure-report\allureReport\
8. После тестирования завершить работу приложения в его терминале при помощи команды `^C`
9. Остановить и удалить контейнер БД, запущенный в Docker, при помощи `docker-compose -f docker-compose-postgres.yml -p term_project_db down`