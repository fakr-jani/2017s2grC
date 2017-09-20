package ro.msg.edu.business;

import java.io.File;
import java.io.IOException;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.spec.EnterpriseArchive;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;
import org.junit.runner.RunWith;


@RunWith(Arquillian.class)
public abstract class AbstractIntegrationTest {

	@Deployment
	public static Archive<?> createDeploymentPackage() throws IOException {
		final File[] ejbDependencies = Maven.resolver().loadPomFromFile("pom.xml").importRuntimeDependencies().resolve()
				.withTransitivity().asFile();

		final JavaArchive ejbJar = ShrinkWrap.create(JavaArchive.class, "ejb-jar.jar") //
				.addPackages(true, getEjbPackages()).addAsManifestResource("beans.xml");

		System.out.println(ejbJar.toString(true));

		final WebArchive testWar = ShrinkWrap.create(WebArchive.class, "test.war").addAsLibraries(ejbDependencies)
				.addAsLibrary(ejbJar).addAsWebInfResource("beans.xml");
		System.out.println(testWar.toString(true));

		final EnterpriseArchive ear = ShrinkWrap.create(EnterpriseArchive.class)
				.setApplicationXML("test-application.xml").addAsModule(testWar);
		System.out.println(ear.toString(true));

		return ear;
	}

	public static String[] getEjbPackages() {
		return new String[] { "ro.msg.edu" };
	}

}
