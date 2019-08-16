package learning;

import learning.common.AttackLogInfo;
import learning.fn.CountMvpShipFn;
import learning.fn.FilterCSVHeaderFn;
import learning.fn.ParseAttackLogFn;
import org.apache.beam.sdk.Pipeline;
import org.apache.beam.sdk.io.TextIO;
import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.PipelineOptionsFactory;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.transforms.ParDo;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.TypeDescriptors;

public class Test {

    private static final String CSVHeader ="时间,海域,地图点,状态,战况,敌舰队,捞！,大破舰,旗舰,旗舰 (第二舰队),MVP,MVP (第二舰队)\n";

    public static void main(String[] args) {
//        MyOptions options = PipelineOptionsFactory.fromArgs(args).withValidation().as(MyOptions.class);
        runCsvCount();

    }

    private static void runCsvCount() {
        PipelineOptions options = PipelineOptionsFactory.create();
        Pipeline p = Pipeline.create(options);
        PCollection<String> csvFile = p.apply("Read from CSV File",
                TextIO.read().from("./resources/testdata/attack.csv"));

        PCollection<String> csvRows = csvFile.apply("remove csv header",
                ParDo.of(new FilterCSVHeaderFn(CSVHeader)));

        PCollection<AttackLogInfo> attackLogs = csvRows.apply("Parse attack log", ParDo.of(new ParseAttackLogFn()));

        PCollection<KV<String, Integer>> countMvpShip = attackLogs.apply("count mvp ship", new CountMvpShipFn());

        countMvpShip.apply(
                MapElements.into(TypeDescriptors.strings())
                        .via(
                                (KV<String, Integer> wordCount) ->
                                        wordCount.getKey() + ": " + wordCount.getValue()))
                .apply(TextIO.write().to("./resources/kancolle/statistics/mvpcounts"));
        p.run().waitUntilFinish();


    }

}
