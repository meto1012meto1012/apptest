

package com.mycompany.mavenproject1.util.web;

import java.lang.management.ManagementFactory;
import java.lang.management.MemoryMXBean;
import java.lang.management.MemoryPoolMXBean;
import java.lang.management.MemoryUsage;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;


@RequestScoped
@Named
public class Snoop {
	@Inject
	private HttpServletRequest request;
	
	private MemoryUsage heapMemoryUsage;
	private MemoryUsage nonHeapMemoryUsage;
	private List<MemoryPoolMXBean> memoryPoolMXBeans;
	
	private List<String> headerNames;
	private List<String> parameterNames;
	private List<String> attributeNames;
	private List<String> initParameterNames;

	@PostConstruct
	private void postConstruct() {
		final MemoryMXBean memoryMxBean = ManagementFactory.getMemoryMXBean();
		heapMemoryUsage = memoryMxBean.getHeapMemoryUsage();
		nonHeapMemoryUsage = memoryMxBean.getNonHeapMemoryUsage();
		
		memoryPoolMXBeans = ManagementFactory.getMemoryPoolMXBeans();
		
		Enumeration<String> e = request.getHeaderNames();
		headerNames = new ArrayList<>();
		if (e != null && e.hasMoreElements()) {
			while (e.hasMoreElements()) {
				headerNames.add(e.nextElement());
			}
		}
		
		e = request.getParameterNames();
		parameterNames = new ArrayList<>();
		if (e != null && e.hasMoreElements()) {
			while (e.hasMoreElements()) {
				parameterNames.add(e.nextElement());
			}
		}
		
		e = request.getAttributeNames();
		attributeNames = new ArrayList<>();
		if (e != null && e.hasMoreElements()) {
			while (e.hasMoreElements()) {
				attributeNames.add(e.nextElement());
			}
		}
		
		e = request.getServletContext().getInitParameterNames();
		initParameterNames = new ArrayList<>();
		if (e != null && e.hasMoreElements()) {
			while (e.hasMoreElements()) {
				initParameterNames.add(e.nextElement());
			}
		}
	}

	public MemoryUsage getHeapMemoryUsage() {
		return heapMemoryUsage;
	}

	public MemoryUsage getNonHeapMemoryUsage() {
		return nonHeapMemoryUsage;
	}

	public List<MemoryPoolMXBean> getMemoryPoolMXBeans() {
		return memoryPoolMXBeans;
	}

	public List<String> getHeaderNames() {
		return headerNames;
	}

	public List<String> getParameterNames() {
		return parameterNames;
	}

	public List<String> getAttributeNames() {
		return attributeNames;
	}

	public List<String> getInitParameterNames() {
		return initParameterNames;
	}
}
