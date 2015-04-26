import java.io.BufferedReader;
import java.io.IOException;

import org.glassfish.embeddable.*;

import java.io.*;

public class EmbeddedTest {
	static GlassFishRuntime glassfishRuntime =null;
	static GlassFishProperties glassfishProperties =null;
	static GlassFish glassfish =null;
	     /**
	     * @param args the command line arguments
	     */
	    public static void main(String[] args) {
	        File configFile = new File 
	("//home//dave//payaralatest//Payara//appserver//distributions//payara//target//stage//payara41//glassfish//domains//domain1//config//domain.xml");
	        File war = new File("//home//dave//cargotracker~svn//trunk//target//cargo-tracker.war");
	        try {
	            glassfishRuntime = GlassFishRuntime.bootstrap();
	            glassfishProperties = new GlassFishProperties();
	            glassfishProperties.setConfigFileURI(configFile.toURI().toString());
	            glassfishProperties.setConfigFileReadOnly(false);
	            glassfish = glassfishRuntime.newGlassFish(glassfishProperties);
	            glassfish.start();
	            Deployer deployer = glassfish.getDeployer();
	            deployer.deploy(war, "--force=true");
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	        System.out.println("Press Enter to stop server");
	        // wait for Enter
	        try {
				new BufferedReader(new java.io.InputStreamReader(System.in)).readLine();
			} catch (IOException e1) {
				
				e1.printStackTrace();
			}
	        try {
	            glassfish.dispose();
	            glassfishRuntime.shutdown();
	        }
	        catch (Exception e) {
	            e.printStackTrace();
	        }
	    }

}
