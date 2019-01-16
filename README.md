# Blue Spurs Developer Test

The purpose of this test is to see how you approach a problem and what your solution looks like.  

## Problem

Your task is to create a RESTful web service that takes a product name query parameter and return the lowest current available price.  The price for comparison can be found by querying and comparing results from Best Buy and Walmart using the supplied API keys.  If there are multiple products, the lowest priced product should be returned.

**Sample Request**
```
GET /product/search?name=ipad
```

**Sample Response**
```
{
    "productName": "iPad Mini",
    "bestPrice": "150.00",
    "currency": "CAD",
    "location": "Walmart"
}
```
## Requirements

* The following API keys are for use in the application
```
BestBuy: `pfe9fpy68yg28hvvma49sc89`
Walmart: `rm25tyum3p9jm9x9x7zxshfa`
```
* Assume the production server will be running Ubuntu 16.04
* Solution can be built using either Java, Python, or NodeJS
* You may use any open source libraries or frameworks needed to build the application
* Upload your completed solution to a GitHub or equivalent repository; this will make it easier for us to look at it

**Hints:**

* Make it easy to look at, run, and deploy
* Treat the changes as if they were to be deployed to a production environment; we will be evaluating the solution and overall architecture


* Build and Deployment instructions
  1) Build(needs docker to be running):
     ./mvnw clean package
  2) Run in docker:
     docker run -p 8080:8080 -t search:0.0.1-SNAPSHOT
  3) Access api from browser:
     http://localhost:8080/product/search?name=ipad
