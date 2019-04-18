package Tests;

import io.restassured.http.Header;
import org.junit.Before;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class TankbeurtTest {
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
    public void getall(){
        given().baseUri("http://localhost")
                .port(8080)
                .contentType("application/json")
                .header(new Header("Authorization", "Bearer " + tokenShell))
                .when().get("/testing/resources/coupon/").then().statusCode(200);
    }
}
