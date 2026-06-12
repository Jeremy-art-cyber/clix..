package com.example.simplecalculator;

import android.content.Context;

import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

/**
 * Instrumented tests that run on an Android device.
 *
 * These tests verify:
 * 1. The application context is available.
 * 2. The package name is correct.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {

    private static final String EXPECTED_PACKAGE_NAME =
            "com.example.simplecalculator";

    private Context appContext;

    @Before
    public void setUp() {
        appContext = InstrumentationRegistry
                .getInstrumentation()
                .getTargetContext();
    }

    @Test
    public void useAppContext() {
        // Original test logic preserved
        assertEquals(EXPECTED_PACKAGE_NAME, appContext.getPackageName());
    }

    @Test
    public void appContextShouldNotBeNull() {
        assertNotNull("Application context should not be null", appContext);
    }

    @Test
    public void packageNameShouldNotBeEmpty() {
        assertFalse(
                "Package name should not be empty",
                appContext.getPackageName().isEmpty()
        );
    }
}
