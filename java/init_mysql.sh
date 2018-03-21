#created mysql db in docker
docker run -p 3306:3306 --name loggers_mysql -e MYSQL_ROOT_PASSWORD="gxk&,5*kB?PU" -e MYSQL_DATABASE=loggers -e MYSQL_USER=loggers -e MYSQL_PASSWORD="BKF6QsEmsG+ASD" -d mysql:latest
