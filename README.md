# Blue Spurs Developer Test

The purpose of this test is to see how you approach a problem and what the solution looks like.  

## Problem

Using the provided API keys for the BestBuy and Walmart APIs, create a RESTful web service endpoint that allows a client to input a product name as a GET query parameter and returns the cheapest product.  The web service should return the best (minimum) price for the product and which store to buy it from. If there are multiple products, always return the lowest priced product. For example:

**Request**
```
GET /product/search?name=ipad
```

**Example Response**
```
{
    "productName": "iPad Mini",
    "bestPrice": "150.00",
    "currency": "CAD",
    "location": "Walmart"
}
```

### Required API Keys

**BestBuy**: `pfe9fpy68yg28hvvma49sc89`

**Walmart**: `rm25tyum3p9jm9x9x7zxshfa`


## Requirements

* 
·  Feel free to use any open source libraries if required
·  Treat the changes as if they were to be deployed to a production environment; we will be evaluating the solution and overall architecture
·  When completed, upload your completed project to Github