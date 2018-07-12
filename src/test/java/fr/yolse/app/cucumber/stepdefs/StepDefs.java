package fr.yolse.app.cucumber.stepdefs;

import fr.yolse.app.YolseApp;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.ResultActions;

import org.springframework.boot.test.context.SpringBootTest;

@WebAppConfiguration
@SpringBootTest
@ContextConfiguration(classes = YolseApp.class)
public abstract class StepDefs {

    protected ResultActions actions;

}
