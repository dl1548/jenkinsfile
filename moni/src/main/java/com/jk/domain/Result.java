package com.jk.domain;

/**
 * http请求返回的对象
 * @author 18203
 *
 * @param <T>
 */
public class Result<T> {

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	/** 错误码 */
	private Integer code;
	
	/** 提示信息*/
	private String msg;
	
	/** 具体的内容*/
	private T data;
}
