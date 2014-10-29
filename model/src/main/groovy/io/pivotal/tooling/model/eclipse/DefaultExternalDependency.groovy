package io.pivotal.tooling.model.eclipse

import groovy.transform.Canonical
import org.gradle.tooling.model.GradleModuleVersion

@Canonical
class DefaultExternalDependency implements Serializable {
    File file
    File sourceFile
    File javadocFile
    GradleModuleVersion moduleVersion
}
