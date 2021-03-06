/**
 * 
 * Licensed under the Apache License, Version 2.0 (the "License");
 * 
 * Founder admin@love320.com
 * 
 * http://www.love320.com
 */
package cms.bean;

import org.json.simple.JSONObject;

public class RiaJsonObject {
	
	/**
	 * navTabAjaxDone是DWZ框架中预定义的表单提交回调函数．
	 * 服务器转回navTabId可以把那个navTab标记为reloadFlag=1, 下次切换到那个navTab时会重新载入内容. 
	 * callbackType如果是closeCurrent就会关闭当前tab
	 * 只有callbackType="forward"时需要forwardUrl值
	 * navTabAjaxDone这个回调函数基本可以通用了，如果还有特殊需要也可以自定义回调函数.
	 * 如果表单提交只提示操作是否成功, 就可以不指定回调函数. 框架会默认调用DWZ.ajaxDone()

	 * <form action="/user.do?method=save" onsubmit="return validateCallback(this, navTabAjaxDone)">
	 * 
	 * form提交后返回json数据结构statusCode=DWZ.statusCode.ok表示操作成功, 做页面跳转等操作. statusCode=DWZ.statusCode.error表示操作失败, 提示错误原因. 
	 * statusCode=DWZ.statusCode.timeout表示session超时，下次点击时跳转到DWZ.loginUrl
	 * {"statusCode":"200", "message":"操作成功", "navTabId":"navNewsLi", "forwardUrl":"", "callbackType":"closeCurrent"}
	 * {"statusCode":"300", "message":"操作失败"}
	 * {"statusCode":"301", "message":"会话超时"}
	 * 
	 */

	private Integer statusCode = 200;
	private String message = "操作成功";
	private String navTabId;
	private String rel;
	private String callbackType = "forward" ;
	private String forwardUrl;

	public JSONObject getAjaxObject(){
		JSONObject obj = new JSONObject();
		obj.put("statusCode",  statusCode);
		obj.put("message", message);
		obj.put("navTabId", navTabId);
		obj.put("rel", rel);
		obj.put("callbackType", callbackType);
		obj.put("forwardUrl", forwardUrl);
		return obj;
	}
	
	public JSONObject getAjaxObject(Integer statusCode,String message,String navTabId, String rel,String callbackType,String forwardUrl){
		 this.statusCode = statusCode;
		 this.message = message;
		 this.navTabId = navTabId;
		 this.rel = rel;
		 this.callbackType = callbackType ;
		 this.forwardUrl = forwardUrl;
		return getAjaxObject();
	}
	
	public JSONObject getAjaxObject(String message,String navTabId,String callbackType){
		 this.message = message;
		 this.navTabId = navTabId;
		 this.callbackType = callbackType ;
		return getAjaxObject();
	}
	
	public JSONObject getAjaxObject(Integer statusCode,String message,String navTabId,String callbackType){
		 this.statusCode = statusCode ;
		 this.message = message;
		 this.navTabId = navTabId;
		 this.callbackType = callbackType ;
		return getAjaxObject();
	}
	
	public Integer getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public String getNavTabId() {
		return navTabId;
	}
	public void setNavTabId(String navTabId) {
		this.navTabId = navTabId;
	}
	public String getRel() {
		return rel;
	}
	public void setRel(String rel) {
		this.rel = rel;
	}
	public String getCallbackType() {
		return callbackType;
	}
	public void setCallbackType(String callbackType) {
		this.callbackType = callbackType;
	}
	public String getForwardUrl() {
		return forwardUrl;
	}
	public void setForwardUrl(String forwardUrl) {
		this.forwardUrl = forwardUrl;
	}
	
	
	
}
