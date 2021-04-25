package com.swiftdroid.posterhouse.admin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.swiftdroid.posterhouse.admin.repo.FeedBackRepository;

@Controller
public class FeedBackController {

	
	@Autowired
	private FeedBackRepository feedBackRepository;
	
	
	@GetMapping("/userFeedback")
	public String getUserFeedBack(Model model) {
		model.addAttribute("feedback",feedBackRepository.findAll());
		return "feedback";

	}
	
}
