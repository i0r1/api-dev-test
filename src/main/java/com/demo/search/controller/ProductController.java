package com.demo.search.controller;

import com.demo.search.model.Product;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ObjectMapper objectMapper;
    private RestTemplate restTemplate;
    private final String bestBuyUrl = "https://api.bestbuy.com/v1/products((%s))?apiKey=pfe9fpy68yg28hvvma49sc89&sort=salePrice.asc&show=name,salePrice&pageSize=1&format=json";
    private final String walmartUrl = "http://api.walmartlabs.com/v1/search?query=%s&apiKey=rm25tyum3p9jm9x9x7zxshfa&categoryId=3944&sort=price&order=asc&numItems=1";

    @Autowired
    void setRestTemplate(RestTemplateBuilder builder) {
        restTemplate = builder.build();
    }

    @GetMapping("/search")
    public ResponseEntity<String> searchProduct(@RequestParam String name) {
        // put each word inside a 'search={query}' for better search results
        String bestBuyQueryString = String.join("&", Arrays.stream(name.split(" ")).map(query -> "search=" + query).collect(Collectors.toList()));

        // replace spaces with '+' for better search results (at least that's how it works in their developer console)
        String walmartQueryString = name.replaceAll(" ", "+");


        Product bestBuy = getLowest("BestBuy", String.format(bestBuyUrl, bestBuyQueryString), "products");
        Product walmart = getLowest("Walmart", String.format(walmartUrl, walmartQueryString), "items");
        String noResult = "No Results Found.";

        if (bestBuy != null && walmart != null)
            return new ResponseEntity(bestBuy.getBestPrice() < walmart.getBestPrice() ? bestBuy : walmart, HttpStatus.OK);

        if (bestBuy == null && walmart == null)
            return new ResponseEntity(noResult, HttpStatus.OK);

        return new ResponseEntity(bestBuy == null ? walmart : bestBuy, HttpStatus.OK);
    }

    private Product getLowest(String store, String url, String productsStr) {
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        Product product = null;
        try {
            JsonNode root = objectMapper.readTree(responseEntity.getBody());
            JsonNode productsNode = root.get(productsStr);
            if (productsNode.isArray() && productsNode.size() > 0) {
                JsonNode productNode = productsNode.get(0);
                product = new Product(productNode.get("name").textValue(), productNode.get("salePrice").floatValue(), "CAD", store);
            }
        } catch (Exception e) { }

        return product;
    }
}
