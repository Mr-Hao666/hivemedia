package cn.honghuroad.exception;

import cn.honghuroad.result.BaseResult;
import cn.honghuroad.common.exception.RRException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author ZengXiong
 * @Description 异常处理器
 * @Date 2018/11/23 09:40
 */
@RestControllerAdvice
public class RRExceptionHandler {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 处理自定义异常
     */
    @ExceptionHandler(RRException.class)
    public BaseResult handleRRException(RRException e) {
        BaseResult baseResult = new BaseResult();
        baseResult.setCode(e.getCode());
        baseResult.setMessage(e.getMessage());

        return baseResult;
    }

    @ExceptionHandler(DuplicateKeyException.class)
    public BaseResult handleDuplicateKeyException(DuplicateKeyException e) {
        logger.error(e.getMessage(), e);
        return BaseResult.failure("数据库中已存在该记录");
    }

    @ExceptionHandler(Exception.class)
    public BaseResult handleException(Exception e) {
        logger.error(e.getMessage(), e);
        return BaseResult.failure(e.getMessage());
    }
}
