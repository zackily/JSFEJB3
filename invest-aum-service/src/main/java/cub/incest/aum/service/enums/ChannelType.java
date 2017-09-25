package cub.incest.aum.service.enums;

/**
 * 通路別 Enum
 * @author NT80212
 *
 */
public enum ChannelType {
	ROBO('R')
	;
	
	private char code;
	
	private ChannelType(char code){
		this.code = code;
	}
	
	public char getCode(){
		return this.code;
	}
	
}
