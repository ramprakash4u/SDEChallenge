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
Read event - Lets assume Millions of merchant visit 5 times a day on average for time-series related metrics.

