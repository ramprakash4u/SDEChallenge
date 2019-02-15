# PaytmLabs SDE Challenge

## Coding Question

Write an interface for a data structure that can provide the moving average of the last N elements added, add elements to the structure and get access to the elements. Provide an efficient implementation of the interface for the data structure.

### Minimum Requirements

1. Provide a separate interface (IE `interface`/`trait`) with documentation for the data structure
2. Provide an implementation for the interface
3. Provide any additional explanation about the interface and implementation in a README file.

Solution 

## Design Question

Design A Google Analytic like Backend System.
We need to provide Google Analytic like services to our customers. Please provide a high level solution design for the backend system. Feel free to choose any open source tools as you want.

### Requirements

1. Handle large write volume: Billions of write events per day.
2. Handle large read/query volume: Millions of merchants wish to gain insight into their business. Read/Query patterns are time-series related metrics.
3. Provide metrics to customers with at most one hour delay.
4. Run with minimum downtime.
5. Have the ability to reprocess historical data in case of bugs in the processing logic.

## Solution:

## 1. Coding Question Documentation
### Feature
   A Restful service API to Calculate moving average of last N elements from input stream.
   
   ##### MovingAverageLastNElement Datastructure documentation
   This class is implementation of interface "MovingAverageDataStructureIE" to calculate moving average of last N element.
   it calculates moving average with time complexity-> O(1) by keeping track of four things head & tail of queue , Size of current queue/buffer and queueTotal as sum of all elements from current queue/buffer.
   
  @see com.paytmlabs.demo.service.MovingAverageDataStructureIE#movingAvgOfLastNElement(java.lang.Double, java.lang.Integer)
     
   This method calculates and returns moving average of last N element from stream  
   1. Accepts new element to be added in queue from stream of input data
   2. Accepts maxSize of queue as duration/period for which moving average needs to be calculated
   3. Maitains the first(head) element and last(tail) element of queue
   4. Removes first element entered in FIFO queue buffer, once buffer is full based on input maxSize and decrement queueTotal & queue
   size
   5. Maintains the size of queue buffer equal to input maxSize
   6. Size -> Increment and calculates current total queue/buffer size upon addition of new element
   7. queueTotal -> Calculates Sum of all elements from current queue/buffer
   8. calculates moving average with time complexity-> O(1)    
   
  
  Additonally Restful Webservice API is created to use the MovingAverageLastNElement datastructure. 
  The API calculate moving average of last N element from input stream. It accepts duration/period and input stream to calculate moving average. The details documentaiton can be found on Swagger and Controller class.
    
    
   ##### Tech Stacks
   Jave - 1.8  
   Framework - Springboot 1.5.x and Spring Cloud dependencies Dalston.SR3  
   API Documentation - Swagger2  
   Spring DependencyManagement  
     Springboot Starter Web  
     Springboot Starter  
     Spring Cloud Starter Config  
     Spring provided internal tomcat   
   Build and Compile - Maven     
   
   ##### Install and run  
   Maven: Compile Install to generate executable jar.  
   spring-boot:run to run the application. it uses springboot provided internal tomcat on port 5000 configured in bootstrap.yml to start application. you can change this port if you want to run on different port as it is configurable.
   
   ##### TEST RUN using POSTMAN for API endpoint MovingAverageLastNElement
   http://localhost:5000/v1/get/stream/moving-average/last/{nthElement}?stream=[list<Double>]
   
   Example-:  http://localhost:5000/v1/get/stream/moving-average/last/5?stream=1,-9,5.2,4,5,6,7,0.2,0.33    
   In above example nthElement=5 , which is basically period/duration of last N element we want to calculate the Moving average from given stream of data input to API endpoint which is list - [1,-9,5.2,4,5,6,7,0.2,0.33]
   
   Step 1-  First input goes from input stream {1} for processing, queueSize is 1, the moving average = 1/1 = 1  
   Step 2-  Second input goes from input stream {1,-9.5} for processing, queueSize is 2, the moving average = (1 -9.5)/2 = -4   
   Step 3- Third input goes from input stream {1,-9.5,5.2} for processing, queueSize is 3, the moving average = (1 -9.5 + 5.2)/3 = -0.933   
   Step 4- Fourth input goes from input stream {1,-9.5,5.2,4} for processing, queueSize is 4, the moving average = (1 -9.5 + 5.2+4)/4 = 0.3   
   step 5- fifth input goes from input stream {1,-9.5,5.2,4,5} for processing, queueSize is 5, the moving average = (1 -9.5 + 5.2+4+5)/5 = 1.24  
   
   step 6 - sixth input goes from input stream {1,-9.5,5.2,4,5,6} for processing, now here as buffer is full so API will remove head element from queue and the calculate moving average, the queueSize is 5, the moving average = (-9.5 + 5.2+4+5+6)/5 = 2.239
   
   step 7 - Seventh input goes from input stream {1,-9.5,5.2,4,5,6,7} for processing, buffer is again full so API will remove head element from queue and the calculate moving average, the queueSize is 5, the moving average = (5.2+4+5+6+7)/5 = 5.44
   
   step 8- Eighth input goes from input stream {1,-9.5,5.2,4,5,6,7,0.2} for processing, buffer is again full so API will remove head element from queue and the calculate moving average, the queueSize is 5, the moving average = (4+5+6+7+0.2)/5 = 4.43
  
  step 8- Ninth input goes from input stream {1,-9.5,5.2,4,5,6,7,0.2,0.33} for processing, buffer is again full so API will remove head element from queue and the calculate moving average, the queueSize is 5, the moving average = (5+6+7+0.2+0.33)/5 = 3.706
  
   #### In essence of scope of question the TDD development approach was not used but for better engineering practise TDD(Test driven development) could have been utilized where feature file is created with all Given, When , Then for business scenarios and Unit & Automation test case have written for 100% coverage of business scenarios and atleast 80% coverage (unit tested) for overall project.
   
   You can view the api documentation in swagger-ui by pointing to 
   http://localhost:5000  and API endpoint specification (Payload request & Response) is mentioned in file "API Specification Swagger Docs.txt" uploaded to code base.
   
   ###### Swagger URL
   http://localhost:5000/swagger-ui.html#!/mi-vs-controller/
   
   ![Swagger1](https://github.com/ramprakash4u/SDEChallenge/blob/feature-Code-Challenge-Moving-Average-LastNelement/Swagger1.png)
   ![Swagger1](https://github.com/ramprakash4u/SDEChallenge/blob/feature-Code-Challenge-Moving-Average-LastNelement/Swagger2.png)
   ![Swagger1](https://github.com/ramprakash4u/SDEChallenge/blob/feature-Code-Challenge-Moving-Average-LastNelement/Swagger3.png)
   
## 2. Google Analytics Design Documentation

### What is Google Analytics
It is web Analytics tool to provide User Activity that helps in understanding the user traffic metadata on a website. 

Use Case
  1. Helps to understand customers by Geo
  2. Most visited page and how many times a page is visited in single day
  3. How much time spent by user on specific page
  4. New user versus returning users metrics
  5. what Browser, operating system- mobile, desktop, mac, windows etc used by user to visit website
  

### Requirements and Goals of system
Let's Design Google Analytics like service Backend System!! 

For sake of this design document let's restrict to have some few basic feature of Google Analytics like system as oppose to all features that google analytics provides.

What we wish to achieve from Google Analytics -: A backend system that tracks and reports website traffic from users in form of user friendly Analytics report metrics in order to improve company sales, understanding user behaviour, Customer relationship management- CRM.

#### Fucntional Requirement
1. Business team should be able to track and get there website user traffic by Geo.
2. Website is used from mobile, desktop or tablet.
3. Page metrics to show how many times website hit by different user, new user or returning user, behaviour information
4. Source of access - Website used directly or from social media or organic search.

#### Non Functional Requirement
1. Handle large write volume: Billions of write events per day.
2. Handle large read/query volume: Millions of merchants wish to gain insight into their business. Read/Query patterns are time-series
   related metrics.
3. Provide metrics to customers with at most one hour delay.
4. Run with minimum downtime.
5. Have the ability to reprocess historical data in case of bugs in the processing logic.

#### Extended Requirement

1. How much time a user spend on particular page ?
2. Analytics- How many times redirection happened.

#### Not in Scope
- Provide full analytics solution and all metrics that currently google analytics provide.
- Provide hytorical information older than 2 months.

### Capacity Estimation and Constraints
Our backend service will be write heavy (billions of writes events per day). There will be write event for all user activity on customer websites compared to read happens by millions of merchants who may pull analytics report may be once or couple of times in day.

#### Traffic Estimates
Write event - Billions of write events per day.   
  New write event per second -: 1B / (24 hours * 3600 seconds) = 12K writes per second
                    
Read event - Lets assume Millions of merchant visit 5 times a day on average for time-series related metrics. so it will be 5 Millions per day.
  New Read event per second -: 5B / (24 hours * 3600 seconds) = 57 reads per second.

  Well! above may not the right numbers if we talk about real world users and deriving numbers based on number of merchants for google
  analytics may not be accurate. There are factors that might change numbers above. People don't live in perfectly distributed demographic around the world . People don't access the favroutie application perfectly like its smooth blended people using application across world. There will be peaks and drops in usage based on where the most concentrated user base around the world. It could be North america and eastern europe or it could be South Asia etc. So it depends which geography the Google analytics is widely used and it gives different pattern based on user base. 
  
  So for sake of design lets say 
  1. Out of Millions merchant users the 70% are active merchant who return back to google analytics on daily basis. So ~700K active merchants
  2. Out of those 70% active merchant(700k) there are only 70% who access google analytics in peak hours. So ~ 490K peak hour merchants
  3. 490 k / 60 * 60 = ~136 concurrent merchants per second during peak hours.
  
  So we optimize our system for ~136 concurrent merchants read request per second to support peak hours. 
  
#### Storage Estimates
 Lets assume on average the metadata for user activity message is 100 bytes. Assumption is only 5 years historical information will be maintained in system.
 
     1 billion write request messages per day * 100 bytes => 93 GB/day
     To store 5 years history =  93GB/day * 365 * 5 years = 0.164 PB
     
#### Bandwidth Estimation
  if backend system receives 93 GB data per day then  93GB per day / 86400 = 1 MB / sec.
  
### High Level Design
At high level the google analytics like system that collects user activity does four things Configuration, Collection, Processing and Reporting. The client application website requires to configure GA tracking java script something similar below schedu code to be configured on html page which have Prod account number for production(UA-123456789) and Non prod account for lower environments. it can go in common header section of html. For similicity of this design our focus is on backend system design, so will not go much in detail on user/client website configuration of javascript.
   
       
When visitor browse page it loads page and as part of loading it executes GA tracking javascript which sends user cookies to Analytics backend server for processing. Analytics server process data and present user activity information in form of reporting to business merchants.        
      

![High level design](https://github.com/ramprakash4u/SDEChallenge/blob/feature-Code-Challenge-Moving-Average-LastNelement/Design.png)


### Detailed Component Design
In order to build simple design system and for sake of this design lets consider website user activity is captured using click event from website page. The backend system for google analytics is designed on Microservices Architecture platform using API based restful service. 

Assumptions-: The metrics provided by Google analytics to user may not be Real-time,there will be slight lag.    

#### Security
The authentication will happen at API gateway entry level where user website page sends Google Analytics account ex - "UA1234567" configured on GA javascript. The UA** account# for user production system will be different than development or lower environment and accordingly the metrics will also differ based on account information sent.    

#### Message Processing
At high level, the overall Google analytics processing flow is divided in four below parts.  
1. Collection -: At first step is to collect the user activity from user website using click events where user website page is configured with javascript GA ( has Google analytics account information as well )responsible to collect and send the event metadata information to google analytics backend servers.  
2. Configuration - It about setting up rules for data processing and administrative functionality for message processing.   
3. Processing - In this Google Analtics Read and Write API based on message type along with other processing API process the injestion message for reporting.    
4. Reporting-: Finally in reporting provides different type of metrics and time series metrics.

#### API Gateway -:  
The API gateway server as single point entry gate for injestion incoming request for user activity. It deals with security, throttling, caching ,monitoring , static response handling and load balancing. The autehntication happens at API gateway where it sends access token to Authentication service and after successful authentication service returns back JWT token which is passed in downstream private API's. The API gateway also determines the type of message to route whether it needs to route towards READ API or WRITE API. The READ API will accept request from Merchant users that want to see Analytics metrics, time series metrics. The WRITE API will accept request from user website click event that needs to get stored in database on top of which processing and reporting can be done.

#### Autentication service -:
Oauth2- Ping implementation is used for authentication service. The API gateway send access token to ping service and ping service send back JWT token. The token is then passed to downstream private API's and also token has expiry ties with it. If any transaction takes more time to do complete processing downstream and expires... then it requires to send refresh token to get new token as oppose to access token as authentication already happened.

#### Read API -: 
The READ API will accept request from Merchant users that want to see Analytics metrics, time series metrics. It sends flow to Real time reporting API (Assumption this will collect information of user activity in real time but may not have report available in real time may be assume there will be lag of 1 hour). The READ API also Cache top queries by Merchant that hold metadata and reporting information of most heavly used metrics by company/business, this will be less expensive call and reduce processing time, improve throughput , IO's.

#### Write API-:
The Write API will accept request of user activity , process and store in database for reporting. it send flow to Metadata API for processing.

#### Cache- MemCached . 
Memcached serves best for design as it easy for implementation with simple query on API code using key value store. it cache most used queries from user. Since the data is held in RAM it is much faster than where data is stored in database and database calls are more expensive. Query activity stream can be cached.

When to update Cache -: Since you can only store a limited amount of data in cache, "Cache-aside" mechanism known as lazy loading serves best for design. Only requested data is cached, which avoids filling up the cache with data that isn't requested.

#### Database -:
SQL vs NoSQL ? -: Choice is SQL in our use case. The RDBMS type database fits best for this design as oppose to NoSQL as nature of data is Structured data, Relational in nature, Lookups by index are very fast. NoSQL db fits in use case where data is mostly non structured. In our case most if information is actually metadata of website user activity stream. RDBMS also provide ACID for transactions.  

For better scaling purpose the Database is designed using following implementation strategy  

###### Master- Master Slave replication.
Both masters serve writes and coordinate with each other on writes, it fits best for our use case as our application is WRITE heavy. If either master goes down, the system can continue to operate with  writes. There will be replication setup to replicate writes to one or more slaves, which serve only reads.laves can also replicate to additional slaves in a tree-like fashion. If the master goes offline, the system can continue to operate in read-only mode until a slave is promoted to a master or a new master is provisioned.

Disadvantage
1. Most master-master systems are either loosely consistent (violating ACID) or have increased write latency due to synchronization. 
2. Replication-: There is a potential for loss of data if the both master fails which is very unlikely in our case before any newly written data can be replicated to other nodes.
3. Replication adds more hardware and additional complexity.

#### Common Service.
The common service API provides some of common services required by all of the Private API of google analytics. 
like Common security, Common logging service, comming config service. This will help to reduce lot of logic needed on individual application for some of these common service and also provides uniform tech stacks implementation of these common service which improces maintainability.

Tech Stacks
API framework - Springboot 2.x
Security - Oauth2, Ping using JWT token and use Springboot security for implementation.
Logging  Logstash, logback, Spring Sleuth
Cloud Computing- AWS EC2, Elastic Beanstalk, Lamda function, Auto Scaling
API Documentation - Swagger
file Storage - S3
Database- RDS PostgreSQL
API - Rest API
CICD - Git, bitbucket, Jenkins pipeline, AWS Code Commit, Code build, Code pipeline
Logging- Spring Sleuth, Logstash, Elastic Search, Kibana, SLF4j
Configuration - Spring Cloud Config
Hystrix Circuit breaker
Testing - unit testing using - Junit, Mockito, SpringMVC  and Automation testing - Rest Assured, Selenium 
Monitoring & Alerting- Netcool, Cloudwatch alarm
Spring dependencyManagement
	- Springboot externalized configuration
	- Springboot Actuator
	- Springboot Internal Container Solution
	- Springboot autoconfiguration
	- Starter POM & Executable JAR
	- Spring Cloud Config
	- Spring Cloud Sleuth to generate Trans ID and Span ID for each transaction.
	- Spring data / JPA
	- Spring Integration


