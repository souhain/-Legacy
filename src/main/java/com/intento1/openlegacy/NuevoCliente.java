package com.intento1.openlegacy;

import java.math.BigDecimal;

import org.openlegacy.annotations.rpc.Action;
import org.openlegacy.annotations.rpc.Direction;
import org.openlegacy.annotations.rpc.Languages;
import org.openlegacy.annotations.rpc.RpcActions;
import org.openlegacy.annotations.rpc.RpcEntity;
import org.openlegacy.annotations.rpc.RpcField;
import org.openlegacy.annotations.rpc.RpcNumericField;
import org.openlegacy.rpc.actions.RpcActions.EXECUTE;
import org.openlegacy.rpc.actions.RpcActions.SHOW;

@RpcEntity(name="NuevoCliente", language=Languages.SQL)
@RpcActions(actions = {
				@Action(action = SHOW.class, path = ""), 
				@Action(action = EXECUTE.class, path = "nuevo_cliente", displayName = "Execute", alias = "execute"
						)			})
public class NuevoCliente {

	
	@RpcField(length = 45, direction = Direction.INPUT , originalName = "name")
	private String name;
	
	
	@RpcField(length = 45, direction = Direction.INPUT , originalName = "address")
	private String address;
	
	
	@RpcField(length = 100, direction = Direction.INPUT , originalName = "email")
	private String email;
	
	
	@RpcField(length = 10, direction = Direction.INPUT , originalName = "phone")
	private String phone;
	
	@RpcNumericField(decimalPlaces = 2)	
	@RpcField(length = 10, direction = Direction.INPUT , originalName = "saldo")
	private BigDecimal saldo;
	
	
	@RpcField(length = 45, direction = Direction.INPUT , originalName = "ejecutive_email")
	private String ejecutiveEmail;
	
	@RpcNumericField(minimumValue = -2147483648, maximumValue = 2147483647, decimalPlaces = 0)	
	@RpcField(length = 10, originalName = "p_cliente2")
	public Integer pcliente2;
	
	@RpcNumericField(minimumValue = -2147483648, maximumValue = 2147483647, decimalPlaces = 0)	
	@RpcField(length = 10, originalName = "p_cuenta2")
	public Integer pcuenta2;
	
	@RpcNumericField(decimalPlaces = 2)	
	@RpcField(length = 10, originalName = "p_saldo")
	public BigDecimal psaldo;
	
	
}

