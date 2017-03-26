package ${targetPackage}.manager.controller.vo;

public class BaseResponseVo {
	private boolean success = false;
	private String message = "";
	private int code = 0;
	private Object data;

	public BaseResponseVo() {
		super();
		this.success = true;
		this.message = "success";
		this.code = 1;
	}

	public BaseResponseVo(boolean success, int code, String message) {
		super();
		this.success = success;
		this.message = message;
		this.code = code;
	}

	public boolean isSuccess() {
		return success;
	}

	public void setSuccess(boolean success) {
		this.success = success;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
}
