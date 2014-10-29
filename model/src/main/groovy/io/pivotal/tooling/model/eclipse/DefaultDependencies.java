package io.pivotal.tooling.model.eclipse;

import java.io.Serializable;
import java.util.Collection;

public class DefaultDependencies implements Serializable {
    Collection<DefaultExternalDependency> classpath;

    public DefaultDependencies(Collection<DefaultExternalDependency> classpath) {
        this.classpath = classpath;
    }

    public Collection<DefaultExternalDependency> getClasspath() {
        return classpath;
    }
}
