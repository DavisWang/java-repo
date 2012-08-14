import java.io.IOException;
import java.io.PrintStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class HttpStatusCodeChecker
{
  static String option = "";

  private static void print(String s)
  {
    try
    {
      if (option.charAt(1) == 'v')
        System.out.println(s);
    }
    catch (Exception localException)
    {
    }
  }

  private static int statusCodeParser(String s) {
    int statusCode = 0;
    String[] strArray = s.split(" ");
    for (int a = 0; a < strArray.length; a++)
      try {
        statusCode = Integer.parseInt(strArray[a]);
      }
      catch (NumberFormatException localNumberFormatException) {
      }
    return statusCode;
  }

  private static int redirect(String s)
    throws IOException
  {
    URL url = new URL(s);
    URLConnection conn = url.openConnection();
    int statusCode = 0;

    for (int i = 0; ; i++)
    {
      String name = conn.getHeaderFieldKey(i);
      String value = conn.getHeaderField(i);

      if ((name == null) && (value == null))
        break;
      if (name == null) {
        statusCode = statusCodeParser(value);
      }
      else if (name.startsWith("Location")) {
        print("Redirecting to (redirect) : " + value);
        return redirect(value);
      }
    }
    return statusCode;
  }

  public static void checkURL(String URL)
    throws Exception
  {
    String pageSource = Jsoup.connect(URL).get().html();

    Document doc = Jsoup.parse(pageSource);
    Elements hrefLinks = doc.select("[href]");
    Elements srcLinks = doc.select("[src]");

    List resources = new ArrayList();
    resources.add(URL);
    for (Element e : hrefLinks) {
      e.setBaseUri(URL);
      if (!e.absUrl("href").isEmpty()) {
        resources.add(e.absUrl("href"));
      }
    }

    for (Element e : srcLinks) {
      e.setBaseUri(URL);
      if (!e.absUrl("src").isEmpty()) {
        resources.add(e.absUrl("src"));
      }
    }

    print(resources.size() + " resources on page.");

    for (String s : resources) {
      print(s);
    }

    for (String s : resources)
    {
      URL url = new URL(s);
      URLConnection conn = url.openConnection();
      String redirectURL = null;
      int statusCode = 0;

      for (int i = 0; ; i++)
      {
        String name = conn.getHeaderFieldKey(i);
        String value = conn.getHeaderField(i);

        if ((name == null) && (value == null))
          break;
        if (name == null) {
          print("Server HTTP version, Response code:");
          print(value);
          print("\n");
          statusCode = statusCodeParser(value);
        }
        else if (name.startsWith("Location")) {
          redirectURL = value;
          print("Redirecting to : " + value);
        }
      }

      if ((statusCode >= 300) && (statusCode <= 399)) {
        statusCode = redirect(redirectURL);
      }

      if ((statusCode >= 200) && (statusCode <= 299)) {
        continue;
      }
      if (redirectURL == null)
        System.out.println("This URL failed (status code: " + statusCode + ") : " + s);
      else
        System.out.println("This redirect URL failed (status code: " + statusCode + ") : " + redirectURL);
    }
  }

  public static void main(String[] args)
  {
    if (args.length != 1)
    {
      if (args.length == 2) {
        option = args[0];
      }
      else {
        System.out.println("Usage: java -jar [jarname] [-option] [url]");
      }

    }

    System.out.println("Checking " + args[(args.length - 1)]);
    System.out.println("Make sure to specify the protocol for the URL, such as http, https, etc...");
    try
    {
      checkURL(args[(args.length - 1)]);
    }
    catch (Exception e) {
      System.out.println("Something has gone wrong!");

      e.printStackTrace();
    }

    System.out.println("Execution complete.");
  }
}