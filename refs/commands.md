## docker-compose -f docker-compose-postgres.yml -p term_project_db up -d --force-recreate --build
## docker-compose -f docker-compose-reportportal.yml -p reportportal up -d --force-recreate

-f or --file: Specify a custom Docker Compose YAML file.  
-p or --project-name: Define a custom project name.  
up: Create and start the services defined in the docker-compose.yml file.  
-d or --detach: Run containers in detached (background) mode.  
--force-recreate: Force containers to be recreated even if there are no changes.  
--build: Force the images to be rebuilt before starting the containers.  

## java -jar ./artifacts/aqa-shop.jar

## git commit --amend --no-edit
## git push --force (not in main branch)

./gradlew clean test --stacktrace --info