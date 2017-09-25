package cub.incest.aum.service.enums;

/**
 * 付款別 Enum
 * @author NT80212
 *
 */
public enum PayType {
	OBU('3'),
	DBU('1')
	;
	
	private char code;
	
	private PayType(char code){
		this.code = code;
	}
	
	public char getCode(){
		return this.code;
	}
	
}
