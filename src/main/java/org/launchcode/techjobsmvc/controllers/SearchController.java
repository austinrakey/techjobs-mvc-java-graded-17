package org.launchcode.techjobsmvc.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.launchcode.techjobsmvc.models.Job;
import org.launchcode.techjobsmvc.models.JobData;

import java.util.ArrayList;

import static org.launchcode.techjobsmvc.controllers.ListController.columnChoices;


/**
 * Created by LaunchCode
 */
@Controller
@RequestMapping("search")
public class SearchController {

    @GetMapping(value = "")
    public String search(Model model) {
        model.addAttribute("columns", columnChoices);
        return "search";
    }

    // TODO #3 - Create a handler to process a search request and render the updated search view.

    @PostMapping(value = "results")
    public String displaySearchResults(Model model,
                                       @RequestParam String searchType,
                                       @RequestParam String searchTerm) {
        ArrayList<Job> jobs;
//        if (searchType.equals("all") && (searchTerm == null || searchTerm.isBlank())) {
//            jobs = JobData.findAll();
//        } else {
//            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
//        }
//
//        model.addAttribute("columns", columnChoices);
//        model.addAttribute("jobs", jobs);
//        model.addAttribute("title", "Jobs with " + searchType + ": " + searchTerm);
//
//        System.out.println("Search Term: " + searchTerm);
//        System.out.println("Search Type: " + searchType);
//        System.out.println("Number of Jobs Found: " + jobs.size());
//
//        return "search";
//    }

        if (searchTerm.toLowerCase().equals("all") || searchTerm.isEmpty()){
            jobs = JobData.findAll();
            model.addAttribute("title", "All Jobs");
        } else {
            jobs = JobData.findByColumnAndValue(searchType, searchTerm);
            model.addAttribute("title", "Jobs with " + columnChoices.get(searchType) + ": " + searchTerm);
        }
        model.addAttribute("jobs", jobs);
        model.addAttribute("columns", columnChoices);

        return "search";
    }

}

