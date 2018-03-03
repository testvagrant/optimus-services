package com.testvagrant.services.models;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Document
public class Builds {

    @Id private String id;
    private String runMode;
    private Date buildStartTime;
    private Date buildEndTime;
    private int buildScenarios;
    private int buildSuccessRate;
    private int scenariosCount;
    private String scenarioSuccessRate;
    private String crashlytics;
    private Date created_date = new Date();


    public String getRunMode() {
        return runMode;
    }

    public void setRunMode(String runMode) {
        this.runMode = runMode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getBuildStartTime() {
        return buildStartTime;
    }

    public void setBuildStartTime(Date buildStartTime) {
        this.buildStartTime = buildStartTime;
    }

    public Date getBuildEndTime() {
        return buildEndTime;
    }

    public void setBuildEndTime(Date buildEndTime) {
        this.buildEndTime = buildEndTime;
    }

    public int getScenariosCount() {
        return scenariosCount;
    }

    public void setScenariosCount(int scenariosCount) {
        this.scenariosCount = scenariosCount;
    }

    public int getBuildScenarios() {
        return buildScenarios;
    }

    public void setBuildScenarios(int buildScenarios) {
        this.buildScenarios = buildScenarios;
    }

    public int getBuildSuccessRate() {
        return buildSuccessRate;
    }

    public void setBuildSuccessRate(int buildSuccessRate) {
        this.buildSuccessRate = buildSuccessRate;
    }

    public String getScenarioSuccessRate() {
        return scenarioSuccessRate;
    }

    public void setScenarioSuccessRate(String scenarioSuccessRate) {
        this.scenarioSuccessRate = scenarioSuccessRate;
    }

    public String getCrashlytics() {
        return crashlytics;
    }

    public void setCrashlytics(String crashlytics) {
        this.crashlytics = crashlytics;
    }

    public Date getCreated_date() {
        return created_date;
    }

    public void setCreated_date(Date created_date) {
        this.created_date = created_date;
    }
}
