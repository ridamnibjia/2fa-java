
# 2fa-java
this 2 factor authentication process for any java application. in this email and totp is implemented for two layers of security.


## Installation
Steps to run this project:

1- unzip the project, open in preferred ide and import the libraries from ```lib``` folder present in the project.

2- setup an smtp server and use those in the ```
Utils.java``` file and change the email, password and companyName.

3- import the ```auth.sql``` in the sql server and default password is ```""```(empty) so change the sql password in java files.
## Demo
![alt text](https://raw.githubusercontent.com/ridamnibjia/2fa-java/main/img/register.png)

![alt text](https://raw.githubusercontent.com/ridamnibjia/2fa-java/main/img/login.png)
