package io.pivotal.tooling.model.eclipse

import groovy.transform.Canonical
import org.gradle.tooling.model.GradleModuleVersion

@Canonical
class ExternalDependency {
    File file
    File sourceFile
    File javadocFile
    GradleModuleVersion moduleVersion
}
