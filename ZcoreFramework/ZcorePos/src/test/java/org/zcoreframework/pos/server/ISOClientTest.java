package org.zcoreframework.pos.server;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.zcoreframework.base.core.PropertiesFactoryBean;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import static org.junit.Assert.assertEquals;

/**
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"/META-INF/testContext.xml"})
public class ISOClientTest {

    private static Socket socket = null;
    private static PrintWriter writer = null;
    private static BufferedReader reader = null;

    @AfterClass
    public static void tearDown() throws IOException {
        if (writer != null) {
            writer.close();
        }

        if (reader != null) {
            reader.close();
        }

        if (socket != null) {
            socket.close();
        }
    }

    @Before
    public void setUp() throws IOException {
        if (socket != null) {
            return;
        }

        String port = PropertiesFactoryBean.getProperties().getProperty("zcore.pos.server.port");

        socket = new Socket("localhost", Integer.parseInt(port));
        writer = new PrintWriter(socket.getOutputStream(), true);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));

        socket.setSoTimeout(1000);
    }

    private String receive() throws Exception {
        String line = null;
        StringBuilder sb = new StringBuilder();

        while (!"</isomsg>".equalsIgnoreCase(line)) {
            line = reader.readLine();

            if (!line.trim().startsWith("<!--")) {
                sb.append(line.trim());
            }
        }

        return sb.toString();
    }

    @Test
    public void testInvoke1200() throws Exception {
        String request = "<isomsg><field id=\"0\" value=\"1200\"/><field id=\"3\" value=\"500000\"/>" +
                "<field id=\"11\" value=\"123456\"/><field id=\"12\" value=\"961020051012\"/>" +
                "<field id=\"32\" value=\"123\"/><field id=\"41\" value=\"321\"/></isomsg>";
        String response = request
                .replace("1200", "1210")
                .replace("<field id=\"41\" value=\"321\"/></isomsg>"
                        , "<field id=\"39\" value=\"000\"/><field id=\"41\" value=\"321\"/></isomsg>");

        writer.println(request);
        writer.flush();

        assertEquals(response, receive());
    }

    @Test
    public void testInvoke1100() throws Exception {
        String request = "<isomsg><field id=\"0\" value=\"1100\"/><field id=\"3\" value=\"100000\"/>" +
                "<field id=\"11\" value=\"123456\"/><field id=\"12\" value=\"961020051012\"/>" +
                "<field id=\"32\" value=\"123\"/><field id=\"41\" value=\"321\"/></isomsg>";
        String response = request
                .replace("1100", "1110")
                .replace("<field id=\"41\" value=\"321\"/></isomsg>"
                        , "<field id=\"39\" value=\"000\"/><field id=\"41\" value=\"321\"/></isomsg>");

        writer.println(request);
        writer.flush();

        assertEquals(response, receive());
    }

    @Test
    public void testInvokeError() throws Exception {
        String request = "<isomsg><field id=\"0\" value=\"1600\"/><field id=\"3\" value=\"100000\"/>" +
                "<field id=\"11\" value=\"123456\"/><field id=\"12\" value=\"961020051012\"/>" +
                "<field id=\"32\" value=\"123\"/><field id=\"41\" value=\"321\"/></isomsg>";
        String response = request
                .replace("1600", "1610")
                .replace("<field id=\"41\" value=\"321\"/></isomsg>"
                        , "<field id=\"39\" value=\"909\"/><field id=\"41\" value=\"321\"/></isomsg>");

        writer.println(request);
        writer.flush();

        assertEquals(response, receive());
    }

}
