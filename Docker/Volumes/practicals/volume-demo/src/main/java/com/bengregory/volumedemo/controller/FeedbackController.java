package com.bengregory.volumedemo.controller;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
public class FeedbackController {

    private static final Path FEEDBACK_DIR = Paths.get("feedback").toAbsolutePath().normalize();
    private static final Path TEMP_DIR    = Paths.get("temp").toAbsolutePath().normalize();

    public FeedbackController() throws IOException {
        // Create folders if missing
        Files.createDirectories(FEEDBACK_DIR);
        Files.createDirectories(TEMP_DIR);
    }

    @GetMapping("/")
    public String showForm() {
        return "index.html";
    }

    @GetMapping("/exists")
    public String showExists() {
        return "exists.html";
    }

    @PostMapping(value = "/create", consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public String createFeedback(
            @RequestParam("title") String title,
            @RequestParam("text")  String content,
            RedirectAttributes redirectAttributes) throws IOException {

        if (title == null || title.trim().isEmpty()) {
            redirectAttributes.addFlashAttribute("error", "Title is required");
            return "redirect:/";
        }

        String safeTitle = title.trim().toLowerCase().replaceAll("[^a-z0-9- ]", "").replaceAll("\\s+", "-");
        if (safeTitle.isEmpty()) {
            safeTitle = "untitled-" + System.currentTimeMillis();
        }

        String filename = safeTitle + ".txt";

        Path tempPath = TEMP_DIR.resolve(filename);
        Path finalPath = FEEDBACK_DIR.resolve(filename);

        // Write to temp first
        Files.writeString(tempPath, content);

        // Check existence in final location
        if (Files.exists(finalPath)) {
            Files.deleteIfExists(tempPath); // cleanup
            return "redirect:/exists";
        }

        // Atomically move "transaction"
        Files.move(tempPath, finalPath, StandardCopyOption.ATOMIC_MOVE, StandardCopyOption.REPLACE_EXISTING);

        return "redirect:/";
    }
}