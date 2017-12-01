package com.liferay.devtools.ops.p2stats;

import java.util.Collections;
import java.util.Set;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.GET;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;

import org.osgi.service.component.annotations.Component;

/**
 * @author Gregory Amerson
 */
@ApplicationPath("/p2stats")
@Component(immediate = true, service = Application.class)
public class P2StatsApplication extends Application {

	public Set<Object> getSingletons() {
		return Collections.<Object>singleton(this);
	}

	@GET
	@Produces("text/plain")
	public String stats() {
		return "It works!";
	}


}