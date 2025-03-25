## Процедура подготовки и запуска автотестов.
Предусловие: на локальный ПК должен быть скачан [репозиторий с кодом автотестов](https://github.com/chukinant/automation_java_5_term_project). Это можно сделать запустив в терминале команду `git clone {url репозитория} {название папки (опционально)}`.

Шаги:
1. Запустить Docker Desktop.
2. Открыть проект в IntelliJ IDEA.
3. В терминале запустить команду `docker-compose -f docker-compose-postgres.yml -p term_project_db up -d --force-recreate --build`, убедиться что контейнер запустился и работает.
4. Запустить приложение командой `java -jar ./artifacts/aqa-shop.jar`
5. Открыть браузер, в адресной строке указать http://localhost:8080/, должна отобразиться стартовая страница приложения
6. 