package hello.hellospring.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class TimeTraceAop {

    /**
     * 패키지명, 클래스명, 파라미터 등등
     * 패키지 하위에는 다 적용해
     * @param joinPoint
     * @return
     * @throws Throwable
     */
    @Around("execution(* hello.hellospring.service.*(..))")
    public Object execute(ProceedingJoinPoint joinPoint) throws Throwable {
        long start = System.currentTimeMillis();

        System.out.printf("START:"+joinPoint.toString());


        try{
            Object result = joinPoint.proceed();
            return result;
        }finally {
            long finsih = System.currentTimeMillis();
            long timeMs = finsih - start;
            System.out.printf("END:"+joinPoint.toString()+"ms");

        }

    }
}
