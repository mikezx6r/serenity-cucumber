package net.serenitybdd.cucumber.outcomes

import com.github.goldin.spock.extensions.tempdir.TempDir
import net.serenitybdd.cucumber.integration.BrokenStepLibraryScenario
import net.serenitybdd.cucumber.integration.IllegalStepLibraryScenario
import spock.lang.Specification

import static net.serenitybdd.cucumber.util.CucumberRunner.thucydidesRunnerForCucumberTestRunner

/**
 * Created by john on 23/07/2014.
 */
class WhenUsingAnIllegalStepLibrary extends Specification {

    @TempDir
    File outputDirectory

    def "should throw a meaningful exception if a step library with no default constructor is used"() {
        given:
        def runtime = thucydidesRunnerForCucumberTestRunner(IllegalStepLibraryScenario.class, outputDirectory);

        when:
        runtime.run();

        then:
        runtime.errors

        and:
        runtime.errors[0].message.contains("IllegalStepInstantiationSteps doesn't have an empty or a page enabled constructor")
    }

    def "should throw a meaningful exception if a step library if the step library could not be instantiated"() {
        given:
        def runtime = thucydidesRunnerForCucumberTestRunner(BrokenStepLibraryScenario.class, outputDirectory);

        when:
        runtime.run();

        then:
        runtime.errors

        and:
        runtime.errors[0].message.contains("Failed to instantiate class")
    }


}