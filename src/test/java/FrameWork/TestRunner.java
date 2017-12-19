package FrameWork;


import cucumber.api.*;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        features = {"src/test/resources/Feature"},
        format = {"pretty"},
//plugin = {"org.jetbrains.plugins.cucumber.java.run.CucumberJvmSMFormatter"},
        tags={"@webAE"},

        glue={"StepFiles","FrameWork"}
)
public class TestRunner
{


}