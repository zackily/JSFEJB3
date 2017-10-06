package cub.incest.aum.service.enums;

public enum CurrencyCode {
	NTD("NTD"),
	USD("USD")
	;
	
	private String code;
	
	private CurrencyCode(String code){
		this.code = code;
	}
	
	public String getCode(){
		return this.code;
	}
}
