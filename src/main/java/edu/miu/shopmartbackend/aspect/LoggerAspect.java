package edu.miu.shopmartbackend.aspect;

import edu.miu.shopmartbackend.model.Logger;
import edu.miu.shopmartbackend.model.User;
import edu.miu.shopmartbackend.repo.LoggerRepo;
import lombok.Data;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Aspect
@Component
public class LoggerAspect {


    @Autowired
    LoggerRepo loggerRepo;

    Logger logger = new Logger();

    User user;

    @Pointcut("@annotation(edu.miu.shopmartbackend.aspect.annotation.EmailSender)")
    public void annotationPointcut() {
    }

   @Around("annotationPointcut()")
    public void logExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {

        long startTime = System.currentTimeMillis();
        proceedingJoinPoint.proceed();
        long endTime = System.currentTimeMillis();
        System.out.println(endTime - startTime);


        long id = logger.getId() + 1;
        logger.setId(id);
        logger.setDate(LocalDate.now());
        logger.setTime(endTime - startTime);
        logger.setOperation(proceedingJoinPoint.getSignature().getName());
        loggerRepo.save(logger);

        System.out.println("Email Notification: transaction has happend " );

    }


    }
