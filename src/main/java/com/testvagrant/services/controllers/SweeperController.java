package com.testvagrant.services.controllers;

import com.mongodb.BasicDBObject;
import com.mongodb.DBObject;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.gridfs.GridFsOperations;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;

@RestController
public class SweeperController {

    private GridFsOperations gridFsOperations;
    private MongoTemplate mongoTemplate;

    public SweeperController(GridFsOperations gridFsOperations, MongoTemplate mongoTemplate) {
        this.gridFsOperations = gridFsOperations;
        this.mongoTemplate = mongoTemplate;
    }

    @DeleteMapping("/sweep")
    public String deleteRecords(@RequestParam("daysOld") int numberOfDaysOld) {
        try{
            Date dateForQuery = getDateForQuery(numberOfDaysOld);
            deleteBuildsOlderThanThisDate(dateForQuery);
            deleteDevicesOlderThanThisDate(dateForQuery);
            deleteScenariosOlderThanThisDate(dateForQuery);
            deleteIntellisenseOlderThanThisDate(dateForQuery);
            deleteScreenShotsOlderThanThisDate(dateForQuery);
        } catch (Exception e) {
            return e.getMessage();
        }

        return "OK";
    }

    private Date getDateForQuery(@RequestParam("daysOld") int numberOfDaysOld) {
        Calendar calendar = Calendar.getInstance();
        calendar.add(Calendar.DAY_OF_MONTH, numberOfDaysOld*-1);
        return calendar.getTime();
    }

    private void deleteBuildsOlderThanThisDate(Date date) {
        mongoTemplate.getCollection("builds").remove(getDateDBObject(date));
    }

    private void deleteDevicesOlderThanThisDate(Date date) {
        mongoTemplate.getCollection("devices").remove(getDateDBObject(date));
    }

    private void deleteScenariosOlderThanThisDate(Date date) {
        mongoTemplate.getCollection("scenarios").remove(getDateDBObject(date));
    }

    private void deleteIntellisenseOlderThanThisDate(Date date) {
        mongoTemplate.getCollection("intellisense").remove(getDateDBObject(date));
    }

    private void deleteScreenShotsOlderThanThisDate(Date dateForQuery) {
        Query gridFsQuery = getGridFsQuery(dateForQuery);
        gridFsOperations.delete(gridFsQuery);
    }

    private DBObject getDateDBObject(Date date) {
        return new BasicDBObject("created_date", new BasicDBObject("$lte", date));
    }

    private Query getGridFsQuery(Date date) {
        Query query = new Query();
        query.addCriteria(Criteria.where("uploadDate").lte(date));
        return query;
    }
}
