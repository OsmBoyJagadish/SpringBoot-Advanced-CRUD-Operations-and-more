package SpringPracticeCode.service;

import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.slf4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class SchedulerServiceImpl implements SchedulerService{
    private static final Logger logger = LoggerFactory.getLogger("SchedulerServiceImpl.class");

//    @Scheduled(fixedRate = 5000)
//    @Override
//    public void executeTask() {
//        logger.info("Inside Excecute Task");
//        System.out.println("Implementing Scheduler...");
//    }

//    @Scheduled(cron = "*/2 * * * * *")
//    @Override
//    public void executeTaskByCron() {
//        System.out.println("Executing By cron");
//    }
}
