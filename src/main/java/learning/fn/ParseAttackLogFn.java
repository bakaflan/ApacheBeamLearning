package learning.fn;

import learning.common.AttackLogInfo;
import org.apache.beam.sdk.metrics.Counter;
import org.apache.beam.sdk.metrics.Metrics;
import org.apache.beam.sdk.transforms.DoFn;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ParseAttackLogFn extends DoFn<String, AttackLogInfo> {

    private static final Logger LOG = LoggerFactory.getLogger(ParseAttackLogFn.class);
    private final Counter counter = Metrics.counter("main", "ParseErrors");

    @ProcessElement
    public void processElement(ProcessContext context){
        String[] components =context.element().split(",",-1);
        try{
            AttackLogInfo info = AttackLogInfo.builder()
                    .time(components[0])
                    .map(components[1])
                    .mapPoint(components[2])
                    .state(components[3])
                    .result(components[4])
                    .enemyName(components[5])
                    .drop(components[6])
                    .damagedShip(components[7])
                    .flagShip(components[8].split("\\(")[0])
                    .flagShipTsu(components[9].split("\\(")[0])
                    .mvpShip(components[10].split("\\(")[0])
                    .mvpShipTsu(components[11].split("\\(")[0])
                    .build();
            context.output(info);
        }catch (ArrayIndexOutOfBoundsException | NumberFormatException e){
            counter.inc();
            LOG.info("Parse error on "+ context.element() + " , " + e.getMessage());
        }
    }

}
