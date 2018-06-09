package exigentech.treasure.hunt.core;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import org.junit.jupiter.params.provider.ArgumentsSource;

@ArgumentsSource(StepArgumentProvider.class)
@Retention(RetentionPolicy.RUNTIME)
@interface StepSource {
  String[] steps();
}

