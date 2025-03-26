package ru.netology.term.util;

import com.codeborne.selenide.Configuration;
import com.codeborne.selenide.Selenide;
import io.qameta.allure.Attachment;
import org.junit.jupiter.api.extension.AfterTestExecutionCallback;
import org.junit.jupiter.api.extension.BeforeTestExecutionCallback;
import org.junit.jupiter.api.extension.ExtensionContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Optional;

public class AllureScreenShooterExtension implements BeforeTestExecutionCallback, AfterTestExecutionCallback {
    private static final Logger log = LoggerFactory.getLogger(AllureScreenShooterExtension.class);

    private final boolean captureSuccessfulTests;

    public AllureScreenShooterExtension() {
        this(false);
    }

    public AllureScreenShooterExtension(final boolean captureSuccessfulTests) {
        this.captureSuccessfulTests = captureSuccessfulTests;
    }

    @Override
    public void beforeTestExecution(final ExtensionContext context) {
        // You can configure other options for screenshots here, but for full page,
        // we handle it explicitly later in the afterTestExecution.
        Configuration.screenshots = true; // Enabling screenshot functionality globally
    }

    @Override
    public void afterTestExecution(final ExtensionContext context) {
        if (captureSuccessfulTests) {
            log.info("Test passed, capturing screenshot...");
            takeFullPageScreenshot();
        } else {
            context.getExecutionException().ifPresent(error -> {
                log.error("Test failed, capturing screenshot...");
                takeFullPageScreenshot();
            });
        }
    }

    @Attachment(value = "Screenshot", type = "image/png")
    public byte[] takeFullPageScreenshot() {
        // Scroll to the top before taking the screenshot to ensure the full page is captured
        Selenide.executeJavaScript("window.scrollTo(0, 0);");

        // Capture the screenshot, specify the screenshot name here
        String screenshotPath = Selenide.screenshot("full_page_screenshot");

        // Convert the screenshot file to a byte array and attach to Allure
        File screenshotFile = new File(screenshotPath);
        try {
            return Files.readAllBytes(screenshotFile.toPath());
        } catch (IOException e) {
            log.error("Failed to read screenshot file", e);
            return new byte[0];  // Return an empty byte array if reading the file fails
        }
    }
}
