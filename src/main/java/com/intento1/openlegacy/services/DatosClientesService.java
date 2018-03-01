package com.intento1.openlegacy.services;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.openlegacy.annotations.services.*;
import org.openlegacy.annotations.OpenlegacyDesigntime;

import com.intento1.openlegacy.NuevoCliente;
import java.math.BigDecimal;
import org.openlegacy.rpc.RpcSession;

/**
 *  A service interface and input/oputput definition for a web service.
 *  Defines the contract between the client and server. The client uses the same interface for testing the service via Java code. 
 *  The interface DatosClientesService can be customized to enabling passing parameters to the service.     
 */

@OpenlegacyDesigntime(editor = "WebServiceEditor")
public interface DatosClientesService {

	@ServiceMethod(name = "getDatosClientes", cacheDuration = 0, entities = {
			@EntityMapping(alias="nuevoCliente", entity=com.intento1.openlegacy.NuevoCliente.class)})
	public DatosClientesOut getDatosClientes(DatosClientesIn datosClientesIn) throws Exception;

	@BindInputs(endpoints = {
			@BindTo(endpoint = "nuevoCliente.name", expression = "name"),
			@BindTo(endpoint = "nuevoCliente.address", expression = "address"),
			@BindTo(endpoint = "nuevoCliente.email", expression = "email"),
			@BindTo(endpoint = "nuevoCliente.phone", expression = "phone"),
			@BindTo(endpoint = "nuevoCliente.saldo", expression = "saldo"),
			@BindTo(endpoint = "nuevoCliente.ejecutiveEmail", expression = "ejecutiveEmail")})
	public static class DatosClientesIn{
		String name;
		String address;
		String email;
		String phone;
		BigDecimal saldo;
		String ejecutiveEmail;
		
		public String getName(){
			return name;
		}
		
		public void setName(String name){
			this.name = name;
		}

		public String getAddress(){
			return address;
		}
		
		public void setAddress(String address){
			this.address = address;
		}

		public String getEmail(){
			return email;
		}
		
		public void setEmail(String email){
			this.email = email;
		}

		public String getPhone(){
			return phone;
		}
		
		public void setPhone(String phone){
			this.phone = phone;
		}

		public BigDecimal getSaldo(){
			return saldo;
		}
		
		public void setSaldo(BigDecimal saldo){
			this.saldo = saldo;
		}

		public String getEjecutiveEmail(){
			return ejecutiveEmail;
		}
		
		public void setEjecutiveEmail(String ejecutiveEmail){
			this.ejecutiveEmail = ejecutiveEmail;
		}

	}
	@ApiModel(value="DatosClientesOut", description="")
	public static class DatosClientesOut{
		@BindFrom("nuevoCliente.pcliente2")
		public Integer pcliente2;
		@BindFrom("nuevoCliente.pcuenta2")
		public Integer pcuenta2;
		@BindFrom("nuevoCliente.psaldo")
		public BigDecimal psaldo;


		@ApiModelProperty(value="Pcliente2")
		public Integer getPcliente2(){
			return pcliente2;
		}
		
		public void setPcliente2(Integer pcliente2){
			this.pcliente2 = pcliente2;
		}

		@ApiModelProperty(value="Pcuenta2")
		public Integer getPcuenta2(){
			return pcuenta2;
		}
		
		public void setPcuenta2(Integer pcuenta2){
			this.pcuenta2 = pcuenta2;
		}

		@ApiModelProperty(value="Psaldo")
		public BigDecimal getPsaldo(){
			return psaldo;
		}
		
		public void setPsaldo(BigDecimal psaldo){
			this.psaldo = psaldo;
		}
	}
}
