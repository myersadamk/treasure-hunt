#Treasure Hunt (@treasure-hunt)
See PROBLEM.md for a description of the problem domain.

##Project Structure
*treasure-hunt* is configured as  Maven Reactor project: the base directory has a parent pom.xml 
that defines common build and dependency information, and along that are a set of Maven 
sub-projects/modules for the actual application.

*Modules:*
* *treasure-hunt-core* contains the business logic for the problem domain.
* *treasure-hunt-app* consumes the business logic module in order to expose it as a runnable
 application. 
 
##Running/Building the Application
__System Requirements:__
* JDK: 9.x
* Maven: >= 3.5.x

__Running the Application__
A bash script named *treasure-hunt.sh* is provided in the root directory. It will execute a 'mvn 
clean install' if no target directory is found. The jar to run is in *treasure-hunter/treasure-hunter-app/target*.

Once the jar is compiled, the script executes a command similar to this:
./treasure-hunt.sh --path=`pwd`/scenario.txt
OR
bash treasure-hunt.sh --path=`pwd`/scenario.txt

Note that the program also supports a "naive" strategy, which can be specified via --strategy=naive.
This basically just computes the distance of all steps and outputs them.


__Annotation processing/Immutables:__ 
*treasure-hunt-core* uses the [immutables.org](https://immutables.github.io/) dependency, which
generates immutable implementations for interfaces & classes via annotation processing. It may be 
necessary to enable this feature in your IDE to get the generated *ImmutableImplementation.java* 
classes corresponding to interfaces annotated with __@Value.Immutable__.

I really like this library because it comes with so much stuff for free, and minimizing mutability 
lends to simpler and safer applications. The syntax is similar to Guava's ImmutableCollections API,
and now to Java 9's new Collection factory methods, e.g. __List.of()__.

##Final Notes
Unfortunately I did end up running close to the 48 hours, partially because I discovered the input 
file requires NW, SW, etc., and I hadn't originally accounted for that. That's regrettable for a few
reasons: missing an input is not acceptable on a real project, and there are many ways I would clean 
up what I currently have. 

Alas, I think it's appropriate to call myself out on this. These are the main things I would yet do:

* I would add documentation and testing for the app module.
* I would rename some files and scrub names to make sure they make sense, e.g. the purpose of the 
Distance class is a bit ambiguous. Documentation would also help here.
* I would clean up the poms by running some dependency analysis.

I made an explicit effort not to over-engineer things, but at the same time, I used this exercise as
a chance to explore some things I had not done before, such as Spring's command-line configurations.
I did this mostly so that the exercise would not only illustrate my experience level, but also 
benefit me from an educational standpoint.

Thanks for your time!
