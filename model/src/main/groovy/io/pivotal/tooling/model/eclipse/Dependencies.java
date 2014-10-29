package io.pivotal.tooling.model.eclipse;

import java.util.Collection;

public interface Dependencies {
    /**
     * Returns the external dependencies which make up the classpath of this project.
     * The set includes ALL binary transitive dependencies, including those that are derived from
     * project dependencies.
     */
    Collection<ExternalDependency> getClasspath();
}
