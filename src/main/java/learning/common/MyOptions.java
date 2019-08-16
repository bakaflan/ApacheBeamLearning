package learning.common;

import org.apache.beam.sdk.options.PipelineOptions;
import org.apache.beam.sdk.options.Validation;
import org.hibernate.validator.constraints.NotBlank;

public interface MyOptions extends PipelineOptions {

        @Validation.Required
        @NotBlank
        String getInputFile();

        @Validation.Required
        @NotBlank
        String getOutput();

}

