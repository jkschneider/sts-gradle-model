package io.pivotal.tooling.model.eclipse;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

public class DefaultDependencies implements Serializable {
    Collection<ExternalDependency> classpath;

    public DefaultDependencies(Collection<ExternalDependency> classpath) {
        this.classpath = classpath;
    }

    Collection<ExternalDependency> getClasspath() {
        return classpath;
    }
}
