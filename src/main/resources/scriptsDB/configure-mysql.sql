/*
Use to run mysql db docker image
$ docker run --name mysqldb -p 3306:3306 -e MYSQL_ALLOW_EMPTY_PASSWORD=yes -d mysql

connect to mysql and run as root user
 */

CREATE DATABASE talci_dev;
CREATE DATABASE talci_prod;

CREATE USER 'talci_dev_user'@'localhost' IDENTIFIED BY 'talci';
CREATE USER 'talci_prod_user'@'localhost' IDENTIFIED BY 'talci';
/*
For docker, we need to set up alternatives for connection
Because we will be coming from a different IP address and mysql will think those are
two different machines
 */
CREATE USER 'talci_dev_user'@'%' IDENTIFIED BY 'talci';
CREATE USER 'talci_prod_user'@'%' IDENTIFIED BY 'talci';

GRANT SELECT ON talci_dev.* to 'talci_dev_user'@'localhost';
GRANT INSERT ON talci_dev.* to 'talci_dev_user'@'localhost';
GRANT DELETE ON talci_dev.* to 'talci_dev_user'@'localhost';
GRANT UPDATE ON talci_dev.* to 'talci_dev_user'@'localhost';
GRANT SELECT ON talci_prod.* to 'talci_prod_user'@'localhost';
GRANT INSERT ON talci_prod.* to 'talci_prod_user'@'localhost';
GRANT DELETE ON talci_prod.* to 'talci_prod_user'@'localhost';
GRANT UPDATE ON talci_prod.* to 'talci_prod_user'@'localhost';
/*
For Docker
 */
GRANT SELECT ON talci_dev.* to 'talci_dev_user'@'%';
GRANT INSERT ON talci_dev.* to 'talci_dev_user'@'%';
GRANT DELETE ON talci_dev.* to 'talci_dev_user'@'%';
GRANT UPDATE ON talci_dev.* to 'talci_dev_user'@'%';
GRANT SELECT ON talci_prod.* to 'talci_prod_user'@'%';
GRANT INSERT ON talci_prod.* to 'talci_prod_user'@'%';
GRANT DELETE ON talci_prod.* to 'talci_prod_user'@'%';
GRANT UPDATE ON talci_prod.* to 'talci_prod_user'@'%';
