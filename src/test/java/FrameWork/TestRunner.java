package FrameWork;


import cucumber.api.*;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        features = {"D:\\ClientApp_Automation\\MailAutomation\\src\\test\\resources\\Feature"},
        format = {"pretty"},
//plugin = {"org.jetbrains.plugins.cucumber.java.run.CucumberJvmSMFormatter"},


        glue={"StepFiles","FrameWork"}
)
public class TestRunner
{


}