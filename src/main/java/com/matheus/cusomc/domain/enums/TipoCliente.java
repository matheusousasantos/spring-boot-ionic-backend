package com.matheus.cusomc.domain.enums;

public enum TipoCliente {
	
	PESSOAFISICA(1, "Pessoa Física"),
	PESSOAJURIDICA(2, "Pessoa Jurídica");
	
	private int cod;
	private String desc;
	
	private TipoCliente(int cod, String desc) {
		this.cod = cod;
		this.desc = desc;
	}
	
	public int getCod() {
		return this.cod;
	}
	
	public String getDesc() {
		return this.desc;
	}
	
	public static TipoCliente toEnum(Integer id) { 
//  Basicamente retorna PESSOAFISICA(1) ou PESSOAJURIDICA(2) que será um TipoCliente	
		if(id == null) {
			return null;
		}
		
		for(TipoCliente x : TipoCliente.values()) {
			if(id.equals(x.getCod())) {
				return x;
			}
		}
		
		throw new IllegalArgumentException("Id inválido: " + id);
	}

}
