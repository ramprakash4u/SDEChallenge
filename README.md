# PaytmLabs SDE Challenge

## Coding Question

Write an interface for a data structure that can provide the moving average of the last N elements added, add elements to the structure and get access to the elements. Provide an efficient implementation of the interface for the data structure.

### Minimum Requirements

1. Provide a separate interface (IE `interface`/`trait`) with documentation for the data structure
2. Provide an implementation for the interface
3. Provide any additional explanation about the interface and implementation in a README file.

## Solution:
   ### Feature
   A Restful service API to Calculate moving average of last N elements from input stream.
   
   ##### MovingAverageLastNElement Datastructure documentation
   
   
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

 
