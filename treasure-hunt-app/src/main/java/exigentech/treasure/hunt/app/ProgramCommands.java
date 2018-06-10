package exigentech.treasure.hunt.app;

import exigentech.treasure.hunt.core.Step;
import java.util.List;
import javax.validation.constraints.Size;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;
import org.springframework.shell.standard.ShellOption;

@ShellComponent
public final class ProgramCommands {

  @ShellMethod("Hunt treasure!")
  public List<Step> hunt(
      @ShellOption(defaultValue="false") boolean interactive,
      @ShellOption(arity = 1) @Size(min = 1) String path
  ) {
//    System.out.println(isInteractive + " " + path)
    System.out.println(path);
    return null;
  }
}
