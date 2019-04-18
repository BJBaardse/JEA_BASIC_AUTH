package Tests;

import io.restassured.http.Header;
import jwt.Role;
import models.Brandstof;
import models.Userapp;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class BrandstofTests {
    private String tokenShell;
    private String tokenTankstation;
    private String tokenUser;

    @Before
    public void initObjects() {
        tokenShell = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2Vycy9Uek1Vb2NNRjRwIiwiZXhwIjoxODcxMjIzMTI4LCJ1c2VybmFtZSI6IlNoZWxsIiwiUm9sZSI6IlNoZWxsIiwiaWF0IjoxNTU1NjAzOTI4fQ.FYKfCsvhC92KY4206AYdhiYC3xeHNRsvqOo9KXIAryE";
        tokenTankstation = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2Vycy9Uek1Vb2NNRjRwIiwiZXhwIjoxODcxMjA3MzQwLCJ1c2VybmFtZSI6InRlc3R1c2VyVGFua3N0YXRpb24iLCJJRCI6NywiUm9sZSI6IlRhbmtzdGF0aW9uIiwiaWF0IjoxNTU1NTg4MTQwfQ.C9GQRVf4iaGhjV7KOhGop0FtwDD5OO6eFuYznYv3eW8";
        tokenUser = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2Vycy9Uek1Vb2NNRjRwIiwiZXhwIjoxODcxMjA3MzgwLCJ1c2VybmFtZSI6InRlc3R1c2VyVXNlciIsIklEIjo4LCJSb2xlIjoiVXNlciIsImlhdCI6MTU1NTU4ODE4MH0.Ab6Z2_55c-Rfhl_78gOBhrXSepQiUFAuq_DKDw82xMQ";
    }

    @Test
    public void Add(){
        Brandstof testBrandstof = new Brandstof("Bier", 2.50);
        given().baseUri("http://localhost")
                .port(8080)
                .contentType("application/json")
                .header(new Header("Authorization", "Bearer " + tokenShell))
                .body(testBrandstof)
                .when().post("/testing/resources/brandstof/").then().statusCode(200);
    }
    @Test
    public void Add_asUser(){
        given().baseUri("http://localhost")
                .port(8080)
                .contentType("application/json")
                .header(new Header("Authorization", "Bearer " + tokenUser))
                .body(new Brandstof("Bier en Meer", 2.50))
                .when().post("/testing/resources/brandstof/").then().statusCode(401);
    }
    @Test
    public void getall(){
        given().baseUri("http://localhost")
                .port(8080)
                .contentType("application/json")
                .header(new Header("Authorization", "Bearer " + tokenUser))
                .when().get("/testing/resources/brandstof/").then().statusCode(200);
    }
    @Test
    public void update(){
        Brandstof test = new Brandstof("Testy", 230);
        test.setId(900);//hardcoded testID
        given().baseUri("http://localhost")
                .port(8080)
                .contentType("application/json")
                .header(new Header("Authorization", "Bearer " + tokenShell))
                .body(test)
                .when().put("/testing/resources/brandstof/").then().statusCode(200);
    }
    @Test
    public void update_asUser(){
        Brandstof test = new Brandstof("Testy", 230);
        test.setId(900);//hardcoded testID
        given().baseUri("http://localhost")
                .port(8080)
                .contentType("application/json")
                .header(new Header("Authorization", "Bearer " + tokenUser))
                .body(test)
                .when().put("/testing/resources/brandstof/").then().statusCode(401);
    }

}
