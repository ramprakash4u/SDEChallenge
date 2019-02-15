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
   spring-boot:run to run the application. it uses springboot provided internal tomcat on port 5000 configured in bootstrap.yml to start application.  
   
   ##### TEST RUN using POSTMAN for API endpoint MovingAverageLastNElement
   http://localhost:5000/v1/get/stream/moving-average/last/{nthElement}?stream=[list<Double>]
   
   Example-:  http://localhost:5000/v1/get/stream/moving-average/last/5?stream=1,-9,5.2,4,5,6,7,0.2,0.33
   In above example nthElement=5 which is bascically period/duration of last N element want to calcuate Moving average
   Stream data input to endpoint is list - [1,-9,5.2,4,5,6,7,0.2,0.33]
   
   Step 1-  First input goes from input stream {1} for processing, queueSize is 1, the moving average = 1/1 = 1  
   Step 2-  Second input goes from input stream {1,-9.5} for processing, queueSize is 2, the moving average = (1 -9.5)/2 = -4   
   Step 3- Third input goes from input stream {1,-9.5,5.2} for processing, queueSize is 3, the moving average = (1 -9.5 + 5.2)/3 = -0.933   
   Step 4- Fourth input goes from input stream {1,-9.5,5.2,4} for processing, queueSize is 4, the moving average = (1 -9.5 + 5.2+4)/4 = 0.3   
   step 5- fifth input goes from input stream {1,-9.5,5.2,4,5} for processing, queueSize is 5, the moving average = (1 -9.5 + 5.2+4+5)/5 = 1.24  
   
   step 6 - sixth input goes from input stream {1,-9.5,5.2,4,5,6} for processing, now here as buffer is full so API will remove head element from queue and the calculate moving average, the queueSize is 5, the moving average = (-9.5 + 5.2+4+5+6)/5 = 2.239
   
   step 7 - Seventh input goes from input stream {1,-9.5,5.2,4,5,6,7} for processing, buffer is again full so API will remove head element from queue and the calculate moving average, the queueSize is 5, the moving average = (5.2+4+5+6+7)/5 = 5.44
   
   step 8- Eighth input goes from input stream {1,-9.5,5.2,4,5,6,7,0.2} for processing, buffer is again full so API will remove head element from queue and the calculate moving average, the queueSize is 5, the moving average = (4+5+6+7+0.2)/5 = 4.43
  
  step 8- Ninth input goes from input stream {1,-9.5,5.2,4,5,6,7,0.2,0.33} for processing, buffer is again full so API will remove head element from queue and the calculate moving average, the queueSize is 5, the moving average = (5+6+7+0.2+0.33)/5 = 3.706
  
   
   
   You can view the api documentation in swagger-ui by pointing to 
   http://localhost:5000
   
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
