package exigentech.treasure.hunt.app.input;

import exigentech.treasure.hunt.core.Distance;
import java.util.List;

public interface StepSource {

  List<Distance> parseFile();
}
