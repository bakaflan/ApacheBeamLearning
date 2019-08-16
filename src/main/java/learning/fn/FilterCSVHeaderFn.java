package learning.fn;

import org.apache.beam.sdk.transforms.DoFn;

public class FilterCSVHeaderFn extends DoFn<String, String> {
    private String headerFilter;

    public FilterCSVHeaderFn(String headerFilter) {
        this.headerFilter = headerFilter;
    }

    @ProcessElement
    public void processElement(ProcessContext c) {
        String row = c.element();
        // Filter out elements that match the header
        if (!row.equals(this.headerFilter)) {
            c.output(row);
        }
    }
}
