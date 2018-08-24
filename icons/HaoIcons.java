package icons;

import com.intellij.openapi.util.IconLoader;
import javax.swing.Icon;

public class HaoIcons
{
  private static Icon load(String path)
  {
    return IconLoader.getIcon(path, HaoIcons.class);
  }
  
  public static final Icon Jstructure = load("/icons/Jstructure.png");
}



