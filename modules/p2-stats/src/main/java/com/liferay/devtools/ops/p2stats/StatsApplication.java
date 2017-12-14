package com.liferay.devtools.ops.p2stats;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URI;
import java.net.URL;
import java.util.Collections;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.HEAD;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;

import org.osgi.service.component.annotations.Component;

import aQute.bnd.http.HttpClient;

/**
 * @author Gregory Amerson
 */
@ApplicationPath("/stats")
@Component(immediate = true, service = Application.class)
public class StatsApplication extends Application {

	public Set<Object> getSingletons() {
		return Collections.<Object>singleton(this);
	}

	@HEAD
	public void head(@Context HttpHeaders headers) {
		System.out.println("head request");

		try (HttpClient client = new HttpClient()){
			URI get = new URI("https://data-devtools.wedeploy.io/stats/p2installs/count");
			Integer count = client.build().get(Integer.class).go(get);

			System.out.println("current p2install stats count is " + count);

			count++;

			String body = "count=" + count;

			put(new URL("https://data-devtools.wedeploy.io/stats/p2installs"), body);

			count = client.build().get(Integer.class).go(get);

			System.out.println("new p2install stats count is " + count);
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void put(URL url, String body) throws Exception {
		HttpURLConnection con = (HttpURLConnection) url.openConnection();

		con.setRequestMethod("PUT");
		con.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
		con.setRequestProperty("Content-Length", body.length() + "");


		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(body);
		wr.flush();
		wr.close();
	}
}