/*
 * JBoss, Home of Professional Open Source
 * Copyright 2015, Red Hat, Inc. and/or its affiliates, and individual
 * contributors by the @authors tag. See the copyright.txt in the
 * distribution for a full listing of individual contributors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jboss.as.quickstarts.kitchensink.controller;

import org.jboss.as.quickstarts.kitchensink.data.MemberListProducer;
import org.jboss.as.quickstarts.kitchensink.model.Member;
import org.jboss.as.quickstarts.kitchensink.service.MemberRegistration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

// The @Model stereotype is a convenience mechanism to make this a request-scoped bean that has an
// EL name
// Read more about the @Model stereotype in this FAQ:
// http://www.cdi-spec.org/faq/#accordion6
@Controller
public class MemberController {

    private final MemberRegistration memberRegistration;
    private final MemberListProducer memberListProducer;

    private Member newMember;

    @Autowired
    public MemberController(MemberRegistration memberRegistration, MemberListProducer memberListProducer) {
        this.memberRegistration = memberRegistration;
        this.memberListProducer = memberListProducer;
    }

    // This method initializes the newMember object
    @GetMapping("/register")
    public String initNewMember(Model model) {
        newMember = new Member();
        model.addAttribute("newMember", newMember);
        model.addAttribute("members", memberListProducer.getMembers());
        return "default";
    }

    @PostMapping("/register")
    public String register(Member newMember, RedirectAttributes redirectAttributes) {
        try {
            // Call the service to register the new member
            memberRegistration.register(newMember);

            // Add a success message to be displayed after redirect
            redirectAttributes.addFlashAttribute("successMessage", "Registered! Registration successful");

            // Redirect to the registration page to clear the form
            return "redirect:/register";
        } catch (Exception e) {
            // Handle the error message
            String errorMessage = getRootErrorMessage(e);
            redirectAttributes.addFlashAttribute("errorMessage", errorMessage);
            return "redirect:/register"; // Redirect to the form with error
        }
    }

    private String getRootErrorMessage(Exception e) {
        String errorMessage = "Registration failed. See server log for more information.";
        if (e != null) {
            Throwable t = e;
            while (t.getCause() != null) {
                t = t.getCause();
            }
            errorMessage = t.getLocalizedMessage();
        }
        return errorMessage;
    }

}
