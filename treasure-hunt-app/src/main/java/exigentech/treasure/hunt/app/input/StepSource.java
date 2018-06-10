package exigentech.treasure.hunt.app.input;

import exigentech.treasure.hunt.core.Step;
import java.util.List;

public interface StepSource {

  List<Step> parseFile();
}
