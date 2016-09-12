package server;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.webapp.WebAppContext;

/**
 * This class launches the web application in an embedded Jetty container.
 */
public class Main {

    public static void main(String[] args) throws Exception{
        String webappDirLocation = "src/main/webapp/";

        Server server = new Server(Integer.valueOf(System.getenv("PORT")));
        WebAppContext root = new WebAppContext();

        root.setContextPath("/");
        root.setDescriptor(webappDirLocation+"/WEB-INF/web.xml");
        root.setResourceBase(webappDirLocation);

        root.setParentLoaderPriority(true);

        server.setHandler(root);

        server.start();
        server.join();
    }

}