package FrameWork;


import cucumber.api.*;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        features = {"src/test/resources/Feature"},
        format = {"pretty", "html:target/Destination"} ,
//plugin = {"org.jetbrains.plugins.cucumber.java.run.CucumberJvmSMFormatter"},
        tags={"@test"},
       // tags={"@webAE"},
        glue={"StepFiles","FrameWork","Pages"}
)
public class TestRunner
{


}