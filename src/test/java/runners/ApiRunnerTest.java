package runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import io.qameta.allure.TmsLink;
import io.qameta.allure.junit4.DisplayName;
import org.junit.runner.RunWith;

@TmsLink("TEST-T2")
@DisplayName("Проверка REST Api")
@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/apiFeatures",
        glue = "apiSteps"
)
public class ApiRunnerTest {
}
