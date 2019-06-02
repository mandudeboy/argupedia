Welcome to Argupedia!

![Argument modeled as nodes and relationships](dark_abortion.png?raw=true "Argupedia")

### What is this repository for? ###

* Argupedia core is the java backend for Argupedia, connecting to a neo4j graph database and exposing an API for any front-end apps. 
* Version 0.1.0

### How do I get set up? ###

* Download neo4j here: https://neo4j.com/download/ 
* Go to the downloaded neo4j directory in a terminal and do: ```./bin/neo4j console```
* visit http://localhost:7474 to see that Neo4j is up and running. Do a tutorial if you want
* go to project and run: mvn eclipse:eclipse to build the eclipse project
* import the project in eclipse and "run as Spring Boot Application"
* try the endpoints. You can see them by running ```curl http://localhost:8080``` in a terminal
* go back to neo4j gui at http://localhost:7474 and run the following query to populate database:
```
CREATE (prop0:Proposition {title:'Every human has a right to decide what they do with their own body'})
CREATE (fact1:Proposition {title:'Every woman is a human'})
CREATE (source0:Source {title:'www.google.com'})
CREATE (arg0:Argument {title:'The female Autonomy', summary:'Every human has a right to decide what they do with their own body and every woman is a human, so every woman has a right to decide what she does with her own body'})
CREATE (prop1:Proposition {title:'Every woman has a right to decide what she does with her own body'})
CREATE (prop2:Proposition {title:'A fetus is a part of a womans body before birth, and becomes a separate human after birth'})
CREATE (arg1:Argument {title:'The Mother Autonomy', summary:'Every woman has a right to her own body, and fetuses are only a part of the womans body before they are born, so every woman has a right to her fetus'})
CREATE (prop3:Proposition {title:'Every pregnant woman has a right to decide what they do with their fetus'})
CREATE (fact2:Proposition {title:'An abortion is when a pregnant woman removes the fetus from her body, ending the pregnant state'})
CREATE (arg2:Argument {title:'The Abortion Right', summary:'Every pregnant woman has a right to do what she wants with her fetus, and abortion is one thing you can do with a fetus, therefor every pregnant woman has a right to do an abortion'})
CREATE (prop4:Proposition {title:'Every pregnant woman has a right to do an abortion'})
CREATE 
	(fact1)<-[:SUPPORTS]-(source0),
	(arg0)<-[:SUPPORTS]-(prop0),
	(arg0)<-[:SUPPORTS]-(fact1),
	(prop1)<-[:SUPPORTS]-(arg0),
	(arg1)<-[:SUPPORTS]-(prop1),
	(arg1)<-[:SUPPORTS]-(prop2),
	(prop3)<-[:SUPPORTS]-(arg1),
	(arg2)<-[:SUPPORTS]-(prop3),
	(arg2)<-[:SUPPORTS]-(fact2),
	(prop4)<-[:SUPPORTS]-(arg2)
```
* try endpoints again to see that they return data now

### Contribution guidelines ###

* DevOps stuff is welcome, do whatever you think is good!
* Code review should be done on all comits regarding business logic
* As soon as argupedia "goes live", try to argue for new features using argupedia, as a way to add traceability to decisions, and as a way to "learn by using"

### Who do I talk to? ###

* Joel Holmberg should be able to answer all questions for now, at joel@axesslab.com
* There is a facebook group called argupedia with some history and other persons who are interested in the projecct.
