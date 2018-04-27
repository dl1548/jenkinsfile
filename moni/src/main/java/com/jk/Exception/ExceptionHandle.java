package com.jk.Exception;


//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.jk.domain.Result;
 
//@ResponseBody  
//@ControllerAdvice
//public class ExceptionHandle {
//
//	 /**
//     * 全局异常捕捉处理
//     * @param ex
//     * @return
//     */
//    @SuppressWarnings({ "unchecked", "rawtypes" })
//	@ResponseBody
//    @ExceptionHandler(value = Exception.class)
//    public Result errorHandler(Exception ex) {
//    	ex.getMessage();
//    	Result result = new Result();  
//    	result.setCode(100);
//    	result.setMsg("发生异常，请联系开发人员");
//    	result.setData(ex.getLocalizedMessage());
//    	return result;
//    }
//}
