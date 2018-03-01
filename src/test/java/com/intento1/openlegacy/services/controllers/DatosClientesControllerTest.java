package com.intento1.openlegacy.services.controllers;

import com.intento1.openlegacy.NuevoCliente;
import java.math.BigDecimal;
import org.openlegacy.rpc.RpcSession;
import org.openlegacy.rpc.actions.RpcActions;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.lang3.StringUtils;

import javax.inject.Inject;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Before;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 *  A test which invokes DatosClientes web service via an http client.
 *  The application should be app and running (via run-application.launch)
 *  To run the test, select Run As -> JUnit test.
 *  If the service has parameters, they should be set via the test.
 */
@ContextConfiguration("/META-INF/spring/applicationContext-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DatosClientesControllerTest {

	private static MultivaluedMap<String, String> queryParams = new MultivaluedMapImpl();

	private String url = "http://localhost:8080/intento1/services/datosclientes";

	@BeforeClass
	public static void initialize() {
		queryParams.add("name", "");
		queryParams.add("address", "");
		queryParams.add("email", "");
		queryParams.add("phone", "");
		queryParams.add("saldo", "");
		queryParams.add("ejecutiveEmail", "");
	}

	@Test
	public void testGetDatosClientes(){
		Client client = new Client();
		WebResource resource = client.resource(url);

		ClientResponse clientResponse = resource.queryParams(queryParams).type("application/json").header("client_id", "client_id").header("client_secret", "client_secret").get(ClientResponse.class);
		Assert.assertEquals(200, clientResponse.getStatus());

		String output = clientResponse.getEntity(String.class);
		Assert.assertFalse(StringUtils.isEmpty(output));
		System.out.println("Client output: " + output);
	}

	@Test
	public void testPostDatosClientes(){
		Client client = new Client();
		WebResource resource = client.resource(url);

		String input = "{\"name\":\"\"\"address\":\"\"\"email\":\"\"\"phone\":\"\"\"saldo\":\"\"\"ejecutiveEmail\":\"\"}";

		ClientResponse clientResponse = resource.type("application/json").post(ClientResponse.class, input);
		Assert.assertEquals(200, clientResponse.getStatus());

		String output = clientResponse.getEntity(String.class);
		Assert.assertFalse(StringUtils.isEmpty(output));
		System.out.println("Client output: " + output);
	}

	@Test
	public void testGetDatosClientesCache() throws Exception {
		final Object[] requests = getRequests();
		int count = requests.length;
		if (count == 0) {
			return;
		}
		Thread[] clientThreads = new Thread[count];
		final WebResource[] clients = generateClients(count);
		final Throwable error = new Throwable();
		for (int i = 0; i < count; i++) {
			final int j = i;
			Thread thread = new Thread(new Runnable() {

				WebResource client = clients[j];
				MultivaluedMap<String, String> input = (MultivaluedMap<String, String>)requests[j];
				int processTimes = 10;

				@Override
				public void run() {
					while (processTimes > 0) {
						try {
							ClientResponse response = client.queryParams(input).type("application/json").header("client_id", "client_id").header("client_secret", "client_secret").get(ClientResponse.class);
							Assert.assertEquals(200, response.getStatus());
							String data = response.getEntity(String.class);
							Assert.assertNotNull(data);
							processTimes--;
						} catch (Throwable th) {
							synchronized (error) {
								error.initCause(th);
							}
						}
					}
				}
			});
			clientThreads[i] = thread;
		}

		for (Thread thread : clientThreads) {
			thread.start();
		}

		boolean exit = false;
		while (!exit) {
			synchronized (error) {
				if (error.getCause() != null) {
					throw (new Exception(error));
				}
			}
			for (Thread thread : clientThreads) {
				exit = !thread.isAlive();
			}
		}
	}

	private WebResource[] generateClients(int count) {
		if (count == 0) {
			return null;
		}
		WebResource[] result = new WebResource[count];
		for (int i = 0; i < count; i++) {
			result[i] = new Client().resource(url);
		}
		return result;
	}

	// specify more input data if need
	@SuppressWarnings("unchecked")
	private Object[] getRequests() {
		return new Object[] { queryParams, queryParams };
	}

}
