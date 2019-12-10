# Pet Shop
A homework project in Java and Sqlite. The goal of this project is meeting requirements from [apple-opsbi-homework.rev7.docx](./apple-opsbi-homework.rev7.docx) in a minimal implementation.

## Prerequisite
- JDK 8 or above
- Sqlite 3

## File structure
    .
    ├── build                   # Compiled files
    ├── lib                     # Dependent java libraries (jar)
    ├── src                     # Source files
    │   ├── main/java           # Application sources
    │   └── test/java           # Unit tests
    ├── homework.sql            # SQL statements
    ├── README.md
    └── sqlite.db               # Sqlite database file
## What it does
The pet shop adopts cats and dogs between 5 and 10 years. They are getting old time by time. They may have names or not. 
Some have adopted multiple times, thus they have more than one names.   
This system provides a tool to digitize their records online for the pet shop.
## How to compile
```
mkdir build
javac -cp "lib/*" -d ./build $(find . -name "*.java")
```
## Running programs
Main
```
java -cp build com.apple.petshop.Main
```
PetShop
```
java -cp "lib/*":build com.apple.petshop.PetShop
```
Tests
```
java -cp "lib/*":build org.junit.runner.JUnitCore com.apple.petshop.model.CatTestAbstract com.apple.petshop.model.DogTestAbstract
```

## Implementation
### Pet interface, Cat and Dog implementation along with AbstractPet
I originally defined the Cat class followed by the Coding Tasks Part 1.
As soon as it requires Dog object in the end of Part 2, I imagined the suggested method, copy & paste, could go wrong easily.
I wrote what they need to do in an interface, named it Pet because Cat and Dog are in that category, is-a relationship domain models.
In the abstract class, AbstractPet, I implemented most of actions in it. Because only difference I can think of was what sound they speak. 
Only constructors and speak() methods are in sub classes, Cat and Dog as a result, simpler, cleaner and reusable.

### Data, DAO, Sqlite
The Data pseudo code seems taking care of database transactions which corresponds to DAO pattern. Therefore I create the package of com.apple.petshop.dao.
I split the methods into a interface, Data then.  It was useful when it becomes to store Pet objects into actual database in Part 3. All I need to do was calling transaction JDBC apis in each methods and a simple insert() method.  
 
## Future Improvements
### DI container
In real world, managing dependencies around is challenging because of complexity. And managing each instance's lifecycle adds even more complexity. By using DI or IoC container, my homework would eliminate most "new" keywords. And it also helps simplifying unit tests, newPet method could be gone.  
### Connection Pool
It is expensive to create a database connection every time or opening the connection forever. DBCP could be useful if this project needs to be more practical.
### ORM in JPA
I've used bare SQL in SqliteData class. ORM could shorten the code blocks. More readable, elegant.  It'll be getting along better with Java objects, meaning insert(table, object) method is perfect fit.
