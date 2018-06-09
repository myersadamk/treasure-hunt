#Treasure Hunt (@treasure-hunt)
See PROBLEM.md for a description of the problem domain.

#Project Structure
*treasure-hunt* is configured as  Maven Reactor project: the base directory has a parent pom.xml 
that defines common build and dependency information, and along that are a set of Maven 
sub-projects/modules for the actual application.

*Modules:*
* *treasure-hunt-core* contains the business logic for the problem domain.
* *treasure-hunt-app* consumes the business logic module in order to expose it as a runnable
 application. 
 
#Running/Building the Application
Requirements:
* JDK: 9.x
* Maven: >= 3.5.x

