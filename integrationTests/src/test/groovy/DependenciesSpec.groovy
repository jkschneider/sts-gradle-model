import io.pivotal.tooling.model.eclipse.Dependencies
import org.gradle.tooling.GradleConnector
import org.gradle.tooling.ModelBuilder
import spock.lang.Specification

class DependenciesSpec extends Specification {
    def 'all binary transitive dependencies are discovered'() {
        when:
        def connector = GradleConnector.newConnector()
        connector.forProjectDirectory(new File(getClass().getResource('sample').toURI()))
        def connection = connector.connect()

        ModelBuilder<Dependencies> customModelBuilder = connection.model(Dependencies.class)
        customModelBuilder.withArguments("--init-script", new File(getClass().getResource('init.gradle').toURI()).absolutePath)
        Dependencies model = customModelBuilder.get()

        then:
        model.getClasspath().size() == 1
    }
}
