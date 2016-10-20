This is a Maven+Spring Boot project that provides a trivial RESTful web service.

It demonstrates how to create and test a RESTful web service using Spring and
a very light n-tier architecture. Also demonstrated is how to store monetary
values accurately for financial calculations. The software was built up from unit
tests that exercise the validation rules in the offer class.

The JavaDocs and tests are not intended to be comprehensive, this is a skeletal
solution. The logging has not been completed.

The service accepts and stores "offers", an offer has a description and a price
along with a currency. These are stored in memory and can be recalled for
display. It's assumed that the client will perform the task of presenting the
data nicely, and locale hasn't been taken into consideration on the server side.

Prerequisites:
1) Maven 3
2) JDK 8
3) browser with internet connection

Once you have the prerequisites installed and setup, download the project
from GitHub into a project folder.

From a command line in the project folder enter:
>mvn package && java -jar target/OfferService.jar

This should build the executable jar and run it. (You can also try to load the
project in your favourite IDE and run it.)

Browse http://localhost:8080/ to view a web page to interact with the service.

Discussion
I wanted to build an offer class that would provide a real monetary object
to store the offer price. This could have been my own Price or Money object with a
BigDecimal amount and a Currency property, however with Java 9 and the Java 
Money API on the horizon it seemed sensibile to take this approach and have
all the benefits of a comprehensive money class implementation (future proofing).
This ensures accurate calculation and the power of a domain specific approach.





