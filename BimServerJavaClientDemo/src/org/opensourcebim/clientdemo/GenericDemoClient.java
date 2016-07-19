package org.opensourcebim.clientdemo;

import org.bimserver.client.BimServerClient;
import org.bimserver.client.json.JsonBimServerClientFactory;
import org.bimserver.shared.UsernamePasswordAuthenticationInfo;

/**
 * @author Ruben de Laat
 * 
 * This class provides generic code to setup/close a BimServerClient that is reused in most of the examples
 *
 */
public abstract class GenericDemoClient {
	public void start() {
		// Creating a factory in a try statement, this makes sure the factory will be closed after use
		try (JsonBimServerClientFactory factory = new JsonBimServerClientFactory("http://localhost:8080")) {
			// Creating a client in a try statement, this makes sure the client will be closed after use
			try (BimServerClient client = factory.create(new UsernamePasswordAuthenticationInfo("admin@bimserver.org", "admin"))) {
				// Do something with the client
				doSomethingWithClient(client);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} catch (Exception e1) {
			e1.printStackTrace();
		}
	}
	
	public abstract void doSomethingWithClient(BimServerClient client);
}
