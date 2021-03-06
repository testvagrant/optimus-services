package com.testvagrant.services.workers;

import com.testvagrant.services.models.Builds;
import com.testvagrant.services.repositories.BuildsRepository;
import com.testvagrant.services.repositories.DevicesRepository;
import com.testvagrant.services.repositories.IntellisenseRepository;
import com.testvagrant.services.repositories.ScenarioRepository;
import org.bson.types.ObjectId;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.DeleteMapping;

import java.util.List;

//@Component
public class BuildCleanupWorker {
    private BuildsRepository buildsRepository;
    private ScenarioRepository scenarioRepository;
    private DevicesRepository devicesRepository;
    private IntellisenseRepository intellisenseRepository;

    public BuildCleanupWorker(BuildsRepository buildsRepository, ScenarioRepository scenarioRepository, DevicesRepository devicesRepository, IntellisenseRepository intellisenseRepository) {
        this.buildsRepository = buildsRepository;
        this.scenarioRepository = scenarioRepository;
        this.devicesRepository = devicesRepository;
        this.intellisenseRepository = intellisenseRepository;
    }

//    @Scheduled(fixedDelay = 15 * 60 * 1000, initialDelay = 15 * 60 * 1000)
    public String cleanup() {
        List<Builds> allBuildsByScenarioCount = buildsRepository.findAllBuildsByComplete(false);
        allBuildsByScenarioCount.forEach(build -> {
            scenarioRepository.removeByBuildId(new ObjectId(build.getId()));
            devicesRepository.removeByBuildId(new ObjectId(build.getId()));
        } );
        buildsRepository.removeByScenariosCount(0);
        return "OK";
    }

}
