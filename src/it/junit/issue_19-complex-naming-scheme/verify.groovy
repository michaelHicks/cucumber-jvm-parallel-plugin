import org.junit.Assert

import static org.hamcrest.Matchers.equalToIgnoringWhiteSpace

File suite01 = new File(basedir, "target/generated-test-sources/cucumber/FooFeature101IT.java");
File suite02 = new File(basedir, "target/generated-test-sources/cucumber/FooFeature202IT.java");

assert suite01.isFile()
assert suite02.isFile()

String expected01 =
        """import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(strict = true, features = {"classpath:features/feature1.feature"}, tags = {"@feature1"},  plugin = {"json:target/cucumber-parallel/1.json",
"pretty"}, monochrome = false, glue = { "foo", "bar" })
public class FooFeature101IT {
}"""

String expected02 =
        """import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;

@RunWith(Cucumber.class)
@CucumberOptions(strict = true, features = {"classpath:features/feature2.feature"}, tags = {"@feature2"}, plugin = {"json:target/cucumber-parallel/2.json",
"pretty"}, monochrome = false, glue = { "foo", "bar" })
public class FooFeature202IT {
}"""

// Depending on the OS, listFiles can list files in different order.  The actual order of files isn't necessary

if (suite01.text.contains("feature1")) {
    Assert.assertThat(suite01.text, equalToIgnoringWhiteSpace(expected01))
    Assert.assertThat(suite02.text, equalToIgnoringWhiteSpace(expected02))
} else {
    Assert.assertThat(suite02.text, equalToIgnoringWhiteSpace(expected01))
    Assert.assertThat(suite01.text, equalToIgnoringWhiteSpace(expected02))
}

