package pl.touk.loggers.preso.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import pl.touk.loggers.preso.config.SimUserData;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class SimulatorRest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private final static int REQ_INTERVAL = 300;
    private final ExecutorService pool;
    private Random rand = new Random();
    private boolean started = false;

    public SimulatorRest() {
        pool = Executors.newFixedThreadPool(1);

        pool.submit(() -> {

            try {
                while (true) {
                    Thread.sleep(REQ_INTERVAL);

                    if (!started) {
                        continue;
                    }

                    int userId = randomUserId();
                    String phoneNo = userPhoneNo(userId);



                }
            } catch (InterruptedException e) {
                logger.error("Simulator thread interrupted", e);
                pool.shutdown();;
            }
        });
    }

    @GetMapping("/simulator/start")
    public void start() {

        if (started) {
            throw new RuntimeException("simulator already started!");
        }

        started = true;
    }

    @GetMapping("/simulator/stop")
    public void stop() {

        if (!started) {
            throw new RuntimeException("simulator already stopped!");
        }

        started = false;
    }

    private int randomUserId() {
        return rand.nextInt(SimUserData.numOfUsers + 1);
    }

    private String userPhoneNo(Integer idx) {
        if (idx > 9) {
            throw new RuntimeException(String.format("Index exceeds 9: [%s]", idx));
        }
        return "60000000" + idx.toString();
    }
}
