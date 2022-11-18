package apiSteps;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.assertj.core.api.Assertions;

import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.Instant;
import java.util.Date;
import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class ApiStepDef {

    private HashMap<String, Integer> paramMap = new HashMap<>();
    RequestSpecification requestSpec;

    @Given("сайт {string}")
    public void setSpec(String url) {
        requestSpec = given()
                .baseUri("https://reqres.in/");
    }

    @Then("пользователь с id {int} имеет {string} {string}")
    public void checkField(int id, String field, String value) {
        requestSpec
                .when()
                .get("api/users/" + id)
                .then()
                .statusCode(200)
                .assertThat()
                .body("data." + field, equalTo(value));
    }

    @When("считаю {string} и сохраняю в {string}")
    public void countData(String what, String param) {
        int res = requestSpec
                .queryParam("page", 1)
                .when()
                .get("api/users")
                .then()
                .statusCode(200)
                .extract()
                .path(what);
        System.out.println(res);
        paramMap.put(param, res);
    }

    @Then("сумма users в {string} равна {string}")
    public void checkRes(String param, String total) {
        int all_users = 0;
        for (int i = 1; i <= paramMap.get(param); i++) {
            int per_page = given()
                    .queryParam("page", i)
                    .when()
                    .get("https://reqres.in/api/users")
                    .then()
                    .statusCode(200)
                    .extract()
                    .path("per_page");
            all_users += per_page;
        }
        Assertions.assertThat(all_users).isEqualTo(paramMap.get(total));
    }

    @Then("при создании user c {string}, {string} статус {int} и поля верные")
    public void createCheck(String name, String job, int code) {
        String user = "{\"name\":\"" + name + "\", \"job\":\"" + job + "\"}";
        Response response = requestSpec
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .post("api/users")
                .then()
                .extract()
                .response();
        Assertions.assertThat(response.statusCode()).isEqualTo(code);
        Assertions.assertThat((String) response.jsonPath().get("name")).isEqualTo(name);
        Assertions.assertThat((String) response.jsonPath().get("job")).isEqualTo(job);
    }

    @Then("при модификации user {int} данных {string}, {string} статус {int} и поля верные")
    public void updateCheck(int id, String name, String job, int code) {
        String user = "{\"name\":\"" + name + "\", \"job\":\"" + job + "\"}";
        Response response = requestSpec
                .header("Content-type", "application/json")
                .and()
                .body(user)
                .when()
                .put("api/users/" + id)
                .then()
                .extract()
                .response();
        Assertions.assertThat(response.statusCode()).isEqualTo(code);
        Assertions.assertThat((String) response.jsonPath().get("name")).isEqualTo(name);
        Assertions.assertThat((String) response.jsonPath().get("job")).isEqualTo(job);
    }

    @Then("при удалении user {int} статус {int}")
    public void deleteCheck(int id, int code) {
        requestSpec
                .header("Content-type", "application/json")
                .when()
                .delete("https://reqres.in/api/users/" + id)
                .then()
                .statusCode(code);
    }
}
