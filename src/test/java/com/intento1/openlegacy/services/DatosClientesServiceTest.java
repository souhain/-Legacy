package com.intento1.openlegacy.services;

import com.intento1.openlegacy.NuevoCliente;
import java.math.BigDecimal;
import org.openlegacy.rpc.RpcSession;

import javax.ws.rs.core.MultivaluedMap;

import org.apache.commons.lang3.StringUtils;
import java.util.Collections;
import javax.inject.Inject;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.Before;

import com.intento1.openlegacy.services.DatosClientesService;
import com.intento1.openlegacy.services.DatosClientesService.*;

import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;


/**
 *  A test which invokes DatosClientes service.
 *  To run the test, select Run As -> JUnit test.
 *  If the service has parameters, they should be set via the test.
 */
@ContextConfiguration("/META-INF/spring/applicationContext-test.xml")
@RunWith(SpringJUnit4ClassRunner.class)
public class DatosClientesServiceTest {

	@Inject
	DatosClientesService datosClientesService;


	@Test
	public void testDatosClientesService() throws Exception {
		long before = System.currentTimeMillis();

		DatosClientesIn datosClientesIn = new DatosClientesIn();
		datosClientesIn.setName("");
		datosClientesIn.setAddress("");
		datosClientesIn.setEmail("");
		datosClientesIn.setPhone("");
		datosClientesIn.setSaldo(BigDecimal.valueOf(0));
		datosClientesIn.setEjecutiveEmail("");
		DatosClientesOut datosClientesOut = datosClientesService.getDatosClientes(datosClientesIn);
		Assert.assertNotNull(datosClientesOut);

		long after = System.currentTimeMillis();
		System.out.println("Execution time:" + (after-before));
	}
}
