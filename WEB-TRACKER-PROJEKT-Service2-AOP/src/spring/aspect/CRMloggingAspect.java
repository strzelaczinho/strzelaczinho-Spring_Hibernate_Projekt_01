package spring.aspect;

import java.util.logging.Logger;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect		// zeby moc wstrzyknac AOP PROXY
@Component	// zezwolic na skanowanie obiektow
public class CRMloggingAspect {

		// steup Logger
		private Logger myLogger = Logger.getLogger(getClass().getName());	// logging API zbudowane jest w dzawie 
	
		// pointcuty 
		@Pointcut("execution(* spring.Controller.*.*(..))")	// pakiet , potem Controller.* = match any class w pakieciiee , any meetehood, jakakolwwiek liczba argueemntow
		private void forControllerPackage()	{}
		
		@Pointcut("execution(* spring.service.*.*(..))")
		private void forServicePackage() {}
		
		@Pointcut("execution(* spring.DAO.*.*(..))")
		private void forDaoPackage() {}
		
		@Pointcut("forControllerPackage() || forServicePackage() || forDaoPackage()")
		private void forAppFlow() {}
		
		@Before("forAppFlow()")
		public void before(JoinPoint theJoinPoint)
		{
			// pokaz wolana metode 
			String method = theJoinPoint.getSignature().toShortString();
			myLogger.info("====>>> in @Before wolam metode o nazwie "+method);
			// pokaz argumenty metody do ktorych sie odnosimy 
			Object[] args = theJoinPoint.getArgs();
			
			for (Object tempArg: args)
			{
				myLogger.info("====>>> argument : "+tempArg);
			}
		}
		
		// after
		
		@AfterReturning(pointcut="forAppFlow()", returning="theResult") // result musi byc zbindowany do obiektu w metodzie ponizej
		public void afterReturning(JoinPoint theJoinPoint,Object theResult) 
		{
			String method = theJoinPoint.getSignature().toShortString();
			myLogger.info("====>>> in @AfterRetruning from method "+method);
			
			myLogger.info("====>>> result : "+theResult);
		}
		
		
		
}		
