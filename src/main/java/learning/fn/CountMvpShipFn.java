package learning.fn;

import learning.common.AttackLogInfo;
import org.apache.beam.sdk.transforms.MapElements;
import org.apache.beam.sdk.transforms.PTransform;
import org.apache.beam.sdk.transforms.Sum;
import org.apache.beam.sdk.values.KV;
import org.apache.beam.sdk.values.PCollection;
import org.apache.beam.sdk.values.TypeDescriptors;

public class CountMvpShipFn extends PTransform<PCollection<AttackLogInfo>, PCollection<KV<String, Integer>>> {

    @Override
    public PCollection<KV<String, Integer>> expand(PCollection<AttackLogInfo> info) {
        return info.apply(
                MapElements.into(TypeDescriptors.kvs(TypeDescriptors.strings(),TypeDescriptors.integers()))
                .via((AttackLogInfo aInfo) -> KV.of(aInfo.getFlagShip(),1))).apply(Sum.integersPerKey());
    }
}
