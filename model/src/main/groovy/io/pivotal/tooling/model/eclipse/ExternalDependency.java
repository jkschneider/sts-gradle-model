package io.pivotal.tooling.model.eclipse;

import org.gradle.tooling.model.GradleModuleVersion;

import java.io.File;

public interface ExternalDependency {
    File getFile();
    File getSourceFile();
    File getJavadocFile();
    GradleModuleVersion getModuleVersion();
}
