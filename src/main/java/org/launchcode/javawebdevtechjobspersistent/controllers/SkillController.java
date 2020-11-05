package org.launchcode.javawebdevtechjobspersistent.controllers;

import org.launchcode.javawebdevtechjobspersistent.models.Skill;
import org.launchcode.javawebdevtechjobspersistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("skills")
public class SkillController {

    @Autowired
    private SkillRepository skillRepository;

    @GetMapping("add")
    public String displayAddSkillForm(Model model) {
        model.addAttribute(new Skill());
        return "skills/add";
    }

    @PostMapping("add")
    public String processAddSkillForm(@ModelAttribute @Valid Skill newSkill,
                                         Errors errors, Model model) {

        if (errors.hasErrors()) {
            return "skills/add";
        }

        skillRepository.save(newSkill);
        return "redirect:";
    }

    @GetMapping
    public String displaySkills(@RequestParam(required = false) Integer skillId, Model model){

        if (skillId == null) {
            model.addAttribute("title", "All Skills");
            model.addAttribute("skills", skillRepository.findAll());
        } //else {
//            Optional<Employer> result = employerRepository.findById(employerId);
//            if (result.isEmpty()) {
//                model.addAttribute("title", "Employer ID: " + employerId);
//            } else {
//                Employer employer = result.get();
//                model.addAttribute("title", "Employers in category: " + employer.getName());
//                model.addAttribute("employers", employer.getEvents());
//            }
//        }
        // I WAS STILL USING THIS CODING FROM THE EVENTS CONTROLLER IN CODING-EVENTS2

        return "skills/index";
    }

    @GetMapping("view/{skillId}")
    public String displayViewSkill(Model model, @PathVariable int skillId) {

        Optional<Skill> optSkill = skillRepository.findById(skillId);
        //Optional optEmployer = null;
        if (optSkill.isPresent()) {
            Skill skill = (Skill) optSkill.get();
            model.addAttribute("skill", skill);
            return "skills/view";
        } else {
            return "redirect:../";
        }
    }
}