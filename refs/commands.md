## docker-compose -f docker-compose-postgres.yml -p term_project_postgres up -d --force-recreate --build
-f or --file: Specify a custom Docker Compose YAML file.  
-p or --project-name: Define a custom project name.  
up: Create and start the services defined in the docker-compose.yml file.  
-d or --detach: Run containers in detached (background) mode.  
--force-recreate: Force containers to be recreated even if there are no changes.  
--build: Force the images to be rebuilt before starting the containers.  

## git commit --amend --no-edit
## git push --force (not in main)

To remove gradle/ and files from Git tracking but keep it locally, run the following commands:
## git rm --cached gradle/ gradlew gradlew.bat settings.gradle (no need to run `git add -A` afterward)