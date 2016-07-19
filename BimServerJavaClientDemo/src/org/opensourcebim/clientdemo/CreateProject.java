package org.opensourcebim.clientdemo;

import org.bimserver.client.BimServerClient;
import org.bimserver.interfaces.objects.SProject;
import org.bimserver.shared.exceptions.PublicInterfaceNotFoundException;
import org.bimserver.shared.exceptions.ServerException;
import org.bimserver.shared.exceptions.UserException;
import org.bimserver.shared.interfaces.ServiceInterface;

/**
 * @author Ruben de Laat
 * 
 * Example of how to create a new project on a BIMserver
 *
 */
public class CreateProject extends GenericDemoClient {

	public static void main(String[] args) {
		new CreateProject().start();
	}
	
	@Override
	public void doSomethingWithClient(BimServerClient client) {
		try {
			// Get a reference to the ServiceInterface. ServiceInterface is one of about 10 interfaces to BIMserver.
			ServiceInterface serviceInterface = client.getServiceInterface();
			
			SProject project = serviceInterface.addProject("New project name", "ifc2x3tc1");
			
			System.out.println("Poid: " + project.getOid());
			
			// The project variable contains all sort of information about the newly created project. The ObjectID (project.getOid()) will give you the Project's ObjectID, also called "poid"
		} catch (ServerException e) {
			// Something is wrong on the server, have a look at the server log files
			e.printStackTrace();
		} catch (UserException e) {
			// Usually indices a user error. In this case it could mean there is already a project with the same name, of the IFC schema is invalid
			e.printStackTrace();
		}
	}
}