package io.pivotal.tooling.plugin.eclipse

import io.pivotal.tooling.model.eclipse.DefaultDependencies
import io.pivotal.tooling.model.eclipse.Dependencies
import io.pivotal.tooling.model.eclipse.ExternalDependency
import org.gradle.api.Project
import org.gradle.api.internal.artifacts.component.DefaultModuleComponentSelector
import org.gradle.api.specs.Specs
import org.gradle.language.base.artifact.SourcesArtifact
import org.gradle.language.java.artifact.JavadocArtifact
import org.gradle.runtime.jvm.JvmLibrary
import org.gradle.tooling.provider.model.ToolingModelBuilder

class DependenciesModelBuilder implements ToolingModelBuilder {
    Project project

    @Override
    public boolean canBuild(String modelName) {
        return modelName.equals(Dependencies.name)
    }

    @Override
    public Object buildAll(String modelName, Project project) {
        def externalDependenciesById = [:]

        def binaryDependencies = project.configurations.compile.incoming.resolutionResult.allDependencies.inject([]) { mods, dep ->
            if(dep.requested instanceof DefaultModuleComponentSelector)
                mods += dep.selected.id
            return mods
        }

        def binaryComponents = project.dependencies.createArtifactResolutionQuery()
                .forComponents(*binaryDependencies)
                .withArtifacts(JvmLibrary.class, *[SourcesArtifact, JavadocArtifact])
                .execute()
                .getResolvedComponents()

        def binaryDependenciesAsStrings = binaryDependencies*.toString()

        // set the compile jar
        project.configurations.compile.resolvedConfiguration.lenientConfiguration.getArtifacts(Specs.SATISFIES_ALL).each {
            def id = it.moduleVersion.id.toString()
            if(binaryDependenciesAsStrings.contains(id))
                externalDependenciesById[id] = new ExternalDependency(it.file)
        }

        binaryComponents.each { binaryDependency ->
            def externalDependency = externalDependenciesById[binaryDependency.id.toString()]

            // set the sources jar
            binaryDependency.getArtifacts(SourcesArtifact).each { sourcesResult ->
                externalDependency.sourceFile = sourcesResult.file
            }

            // set the javadoc jar
            binaryDependency.getArtifacts(JavadocArtifact).each { javadocResult ->
                externalDependency.javadocFile = javadocResult.file
            }
        }

        return new DefaultDependencies(externalDependenciesById.values())
    }
}
