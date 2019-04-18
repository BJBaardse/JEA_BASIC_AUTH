package Tests;

import jwt.AuthenticationEndpoint;
import jwt.Role;
import models.Userapp;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.core.IsEqual.equalTo;

import io.restassured.http.Header;
import rest.UserappDAO;

import javax.ejb.EJB;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.xml.registry.infomodel.User;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UserTests {

    @EJB
    private UserappDAO userappDAO;

    private String tokenShell;
    private String tokenTankstation;
    private String tokenUser;
    private Userapp testuser;

    @Before
    public void initObjects() {
    tokenShell = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2Vycy9Uek1Vb2NNRjRwIiwiZXhwIjoxODcxMjA3MDU0LCJ1c2VybmFtZSI6InRlc3R1c2VyIiwiSUQiOjUsIlJvbGUiOiJTaGVsbCIsImlhdCI6MTU1NTU4Nzg1NH0.1W2tBMibkoNhXRNTKDAOFZEAsu4Ne3fUQAzCRvsoxOE";
    tokenTankstation = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2Vycy9Uek1Vb2NNRjRwIiwiZXhwIjoxODcxMjA3MzQwLCJ1c2VybmFtZSI6InRlc3R1c2VyVGFua3N0YXRpb24iLCJJRCI6NywiUm9sZSI6IlRhbmtzdGF0aW9uIiwiaWF0IjoxNTU1NTg4MTQwfQ.C9GQRVf4iaGhjV7KOhGop0FtwDD5OO6eFuYznYv3eW8";
    tokenUser = "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJ1c2Vycy9Uek1Vb2NNRjRwIiwiZXhwIjoxODcxMjA3MzgwLCJ1c2VybmFtZSI6InRlc3R1c2VyVXNlciIsIklEIjo4LCJSb2xlIjoiVXNlciIsImlhdCI6MTU1NTU4ODE4MH0.Ab6Z2_55c-Rfhl_78gOBhrXSepQiUFAuq_DKDw82xMQ";
    testuser = new Userapp("usertesttest", "password", Role.User);
    }

    @Test
    public void GetListUsers() {
        given().baseUri("http://localhost")
                .port(8080)
                .contentType("application/json")
                .header(new Header("Authorization", "Bearer "+ tokenShell))
                .when().get("/testing/resources/Userapp/").then()
                .statusCode(200);
    }
    @Test
    public void GetListUsersAsUser(){
        given().baseUri("http://localhost")
                .port(8080)
                .contentType("application/json")
                .header(new Header("Authorization", "Bearer "+ tokenUser))
                .when().get("/testing/resources/Userapp/").then()
                .statusCode(401); //notAuthorized
    }
    @Test
    public void CreateUser(){
                given().baseUri("http://localhost")
                        .port(8080)
                        .contentType("application/json")
                        .header(new Header("Authorization", "Bearer " + tokenShell))
                        .body(testuser)
                        .when().post("/testing/resources/Userapp/").then().statusCode(200);

    }
    @Test
    public void CreateUserAsUser(){
        given().baseUri("http://localhost")
                .port(8080)
                .contentType("application/json")
                .header(new Header("Authorization", "Bearer " + tokenUser))
                .body(new Userapp("usertesttest", "password", Role.User))
                .when().post("/testing/resources/Userapp/").then()
                .statusCode(401);
    }
    // test update
    // test delete
}