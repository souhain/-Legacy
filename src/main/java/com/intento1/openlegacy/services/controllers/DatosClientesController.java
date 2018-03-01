package com.intento1.openlegacy.services.controllers;

import com.intento1.openlegacy.services.DatosClientesService;
import com.intento1.openlegacy.services.DatosClientesService.*;

import com.intento1.openlegacy.NuevoCliente;
import java.math.BigDecimal;
import org.openlegacy.rpc.RpcSession;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import javax.ws.rs.QueryParam;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.POST;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.springframework.stereotype.Component;
import org.openlegacy.annotations.OpenlegacyDesigntime;

@Component
@Path("/datosclientes")
@Api(value="DatosClientes")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
@OpenlegacyDesigntime(editor = "WebServiceEditor")
public class DatosClientesController {

	@Inject
	private DatosClientesService datosClientesService;

	@GET
	@ApiOperation(value="DatosClientes GET operation", response = DatosClientesOut.class)
	public Response getDatosClientes(@QueryParam(value="name") String name, @QueryParam(value="address") String address, @QueryParam(value="email") String email, @QueryParam(value="phone") String phone, @QueryParam(value="saldo") BigDecimal saldo, @QueryParam(value="ejecutiveEmail") String ejecutiveEmail) throws Exception {
		DatosClientesIn datosClientesIn = new DatosClientesIn();
		datosClientesIn.setName(name);
		datosClientesIn.setAddress(address);
		datosClientesIn.setEmail(email);
		datosClientesIn.setPhone(phone);
		datosClientesIn.setSaldo(saldo);
		datosClientesIn.setEjecutiveEmail(ejecutiveEmail);
		DatosClientesOut datosClientesOut = datosClientesService.getDatosClientes(datosClientesIn);
		return Response.status(200).entity(datosClientesOut).build();
	}
}
