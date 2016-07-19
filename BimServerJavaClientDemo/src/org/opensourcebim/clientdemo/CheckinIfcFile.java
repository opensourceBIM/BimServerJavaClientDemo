package org.opensourcebim.clientdemo;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Random;

import org.bimserver.client.BimServerClient;
import org.bimserver.interfaces.objects.SDeserializerPluginConfiguration;
import org.bimserver.interfaces.objects.SProject;
import org.bimserver.plugins.services.Flow;
import org.bimserver.shared.exceptions.ServerException;
import org.bimserver.shared.exceptions.UserException;

public class CheckinIfcFile extends GenericDemoClient {

	public static void main(String[] args) {
		new CheckinIfcFile().start();
	}
	
	@Override
	public void doSomethingWithClient(BimServerClient client) {
		try {
			String randomName = "Random " + new Random().nextLong();
			
			// Create a new project with a random name
			SProject project = client.getServiceInterface().addProject(randomName, "ifc2x3tc1");
			
			long poid = project.getOid();
			String comment = "This is a comment";
			
			// This method is an easy way to find a compatible deserializer for the combination of the "ifc" file extension and this project. You can also get a specific deserializer if you want to.
			SDeserializerPluginConfiguration deserializer = client.getServiceInterface().getSuggestedDeserializerForExtension("ifc", poid);
			
			// Make sure you change this to a path to a local IFC file
			Path demoIfcFile = Paths.get("C:\\Git\\TestFiles\\TestData\\data\\AC11-Institute-Var-2-IFC.ifc");
			
			// Here we actually checkin the IFC file. Flow.SYNC indicates that we only want to continue the code-flow after the checkin has been completed
			client.checkin(poid, comment, deserializer.getOid(), false, Flow.SYNC, demoIfcFile);
		} catch (ServerException e) {
			e.printStackTrace();
		} catch (UserException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
