

package com.mycompany.mavenproject1.util.rest;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;

import static com.mycompany.mavenproject1.util.Constants.REST_PATH;


@ApplicationPath(REST_PATH)
public class JaxRsActivator extends Application {
}
