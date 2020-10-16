///**
// *
// */
//package com.neo.exception;
//
//import com.nec.wellness.exception.BadRequestException;
//import com.nec.wellness.exception.ResourceAlreadyExistException;
//import com.nec.wellness.exception.ResourceNotFoundException;
//import com.nec.wellness.payload.ErrorRspBody;
//import org.apache.shiro.authz.AuthorizationException;
//import org.apache.shiro.authz.UnauthorizedException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.ControllerAdvice;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RestController;
//import org.springframework.web.context.request.WebRequest;
//import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
//
///**
// * @author chenkj
// *
// */
//@ControllerAdvice
//@RestController
//public class GlobalRspExceptionHandler extends ResponseEntityExceptionHandler {
//    private static final Logger logger = LoggerFactory.getLogger(GlobalRspExceptionHandler.class);
//
//    @ExceptionHandler(Exception.class)
//    public final ResponseEntity<ErrorRspBody> handleAllExceptions(Exception e, WebRequest request) {
//        logger.error(e.getMessage(), e);
//        HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
//        String path = request.getDescription(false);
//        ErrorRspBody errorRspBody = new ErrorRspBody(httpStatus, path, e);
//        return new ResponseEntity<ErrorRspBody>(errorRspBody, httpStatus);
//    }
//
//    @ExceptionHandler(BadRequestException.class)
//    public final ResponseEntity<ErrorRspBody> handleBadRequestException(BadRequestException e, WebRequest request) {
//        // logger.error(e.getMessage(), e);
//        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
//        String path = request.getDescription(false);
//        ErrorRspBody errorRspBody = new ErrorRspBody(httpStatus, path, e);
//        return new ResponseEntity<ErrorRspBody>(errorRspBody, httpStatus);
//    }
//
//    @ExceptionHandler(ResourceNotFoundException.class)
//    public final ResponseEntity<ErrorRspBody> handleResourceNotFoundException(ResourceNotFoundException e,
//                                                                              WebRequest request) {
//        // logger.error(e.getMessage(), e);
//        HttpStatus httpStatus = HttpStatus.NOT_FOUND;
//        String path = request.getDescription(false);
//        ErrorRspBody errorRspBody = new ErrorRspBody(httpStatus, path, e);
//        return new ResponseEntity<ErrorRspBody>(errorRspBody, httpStatus);
//    }
//
//    @ExceptionHandler(ResourceAlreadyExistException.class)
//    public final ResponseEntity<ErrorRspBody> handleResourceAlreadyExistException(ResourceAlreadyExistException e,
//                                                                                  WebRequest request) {
//        // logger.error(e.getMessage(), e);
//        HttpStatus httpStatus = HttpStatus.CONFLICT;
//        String path = request.getDescription(false);
//        ErrorRspBody errorRspBody = new ErrorRspBody(httpStatus, path, e);
//        return new ResponseEntity<ErrorRspBody>(errorRspBody, httpStatus);
//    }
//
//    /**
//     * 处理没有权限的异常
//     *
//     * @param e
//     * @param request
//     * @return
//     */
//    @ExceptionHandler(UnauthorizedException.class)
//    public final ResponseEntity<ErrorRspBody> handleShiroException(Exception e, WebRequest request) {
//        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
//        String path = request.getDescription(false);
//        ErrorRspBody errorRspBody = new ErrorRspBody(httpStatus, path, e);
//        errorRspBody.setMessage("没有权限");
//        return new ResponseEntity<ErrorRspBody>(errorRspBody, httpStatus);
//    }
//
//
//    /**
//     * 处理权限认证失败的异常
//     *
//     * @param e
//     * @param request
//     * @return
//     */
//    @ExceptionHandler(AuthorizationException.class)
//    public final ResponseEntity<ErrorRspBody> handleAuthorizationException(Exception e, WebRequest request) {
//        HttpStatus httpStatus = HttpStatus.UNAUTHORIZED;
//        String path = request.getDescription(false);
//        ErrorRspBody errorRspBody = new ErrorRspBody(httpStatus, path, e);
//        errorRspBody.setMessage("权限认证失败");
//        return new ResponseEntity<ErrorRspBody>(errorRspBody, httpStatus);
//    }
//}
