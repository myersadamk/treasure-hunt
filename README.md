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
__System Requirements:__
* JDK: 9.x
* Maven: >= 3.5.x

__Annotation processing/Immutables:__ 
*treasure-hunt-core* uses the [immutables.org](https://immutables.github.io/) dependency, which
generates immutable implementations for interfaces & classes via annotation processing. It may be 
necessary to enable this feature in your IDE to get the generated *ImmutableImplementation.java* 
classes corresponding to interfaces annotated with __@Value.Immutable__.

I am totally crazy about this library because it comes with so much stuff for free, and
minimizing mutability lends to simpler and safer applications. The syntax is similar to Guava's
ImmutableCollections API, and now to Java 9's new Collection factory methods, e.g. __List.of()__.
