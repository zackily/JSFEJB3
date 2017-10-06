package cub.invest.aum.service.dto;

public class SvcResult {	
	int code;
	String reason;
	Object result;
	
	public static SvcResult getSuccessInstance(){
		return new SvcResult(0, "Success", null);
	}
	
	public static SvcResult getSuccessInstance(Object result){
		return new SvcResult(0, "Success", result);
	}
	
	public static SvcResult getFailInstance(int code, String reason){
		return new SvcResult(code, reason, null);
	} 
	
	private SvcResult(int code, String reason, Object result){
		this.code = code;
		this.reason = reason;
		this.result = result;
	}
	
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
	public Object getResult() {
		return result;
	}
	public void setResult(Object result) {
		this.result = result;
	}

}
