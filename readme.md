# Javalin-JWT example

This repository contains a source code with example on using JWT authentication with Javalin. The example app has two main components:

* Task API to CRUD operations with ```Task``` entities
* User API to handle authorization and authentication

More information about usage can be found in [this post](https://codesityou.com).

## Requirements

* Java 8+
* Maven
* Postgresql 10+

## Installation

1. Clone this repository
2. Run ```mvn clean install```
3. Setup Postgresql:

- Create table ```users``` with columns ```user_id``` (PK), ```email```, ```password```
- Create table ```tasks``` with columns ```task_id``` (PK), ```content```, ```user_id```, ```finished```
- Change credentials in ```App.java``` (url, username, password)

Finally, start ```App.main()``` and you can call endpoints on ```http://localhost:4567/```

## License

This code is brought to you under MIT license. Use it on your own risk.

## Contacts

Codesity OU, 10130 Kiriku 6 Tallinn Estonia

Author: Yuri Mednikov [email](mailto:yuri.mednikov@codesityou.com)