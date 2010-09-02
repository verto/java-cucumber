package com.couggi.task;

import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.apache.tools.ant.BuildException;
import org.apache.tools.ant.Task;
import org.mortbay.jetty.Handler;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.DefaultHandler;
import org.mortbay.jetty.handler.HandlerCollection;
import org.mortbay.jetty.webapp.WebAppContext;
import org.mortbay.resource.ResourceCollection;

/**
 * Start task baseada na task criada para o projeto http://github.com/caelum/calopsita
 */
public class JettyStartTask extends Task {

	static final int SHUTDOWN = 1;
	private Context context;
	private Resources resources;

	public void setWaitForShutdown(boolean waitForShutdown) {
		this.waitForShutdown = waitForShutdown;
	}

	private boolean waitForShutdown;
	private int port;
	private String databasePort;
	private Server server;

	public void setPort(int port) {
		this.port = port;
	}

	public static void main(String[] args) throws Exception {
		final JettyStartTask task = new JettyStartTask();
		task.setPort(8080);
		task.setDatabasePort("9005");
		task.setWaitForShutdown(false);
		Context ctx = new Context();
		ctx.setContext("/projeto");
		ctx.setClasses("web/WEB-INF/classes");
		Resources resources = new Resources();
		resources.addPath(new Path("web"));
		task.addResources(resources);
		task.addContext(ctx);
		Thread t = new Thread() {
			@Override
			public void run() {
				task.execute();
			}
		};
		t.start();
		ServerSocket ss = new ServerSocket(task.port + 100);
		while (true) {
			Socket s = ss.accept();
			ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
			int val = ois.readInt();
			s.close();
			if (val == SHUTDOWN) {
				break;
			}
		}
		task.server.stop();
	}

	@Override
	public void execute() throws BuildException {

		this.server = new Server(port);

		try {
			Thread.currentThread().setContextClassLoader(
					this.getClass().getClassLoader());
			List<Handler> lista = new ArrayList<Handler>();
			
			WebAppContext wac = new WebAppContext();
			wac.setAttribute("databasePort", this.databasePort);
			wac.setParentLoaderPriority(true);
			wac.setContextPath(context.getContext());
			ResourceCollection resourceCollection = resources.getResources();
			wac.setBaseResource(resourceCollection);
			lista.add(wac);
			lista.add(new DefaultHandler());
			HandlerCollection handlers = new HandlerCollection();
			handlers.setHandlers(lista.toArray(new Handler[] {}));
			server.setHandler(handlers);
			server.setStopAtShutdown(true);
			server.start();
			if (waitForShutdown) {
				server.join();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new BuildException(e);
		}
	}

	public void addContext(Context ctx) {
		this.context = ctx;
	}

	public void setDatabasePort(String databasePort) {
		this.databasePort = databasePort;
	}

	public void addResources(Resources resources) { 
		this.resources = resources;
	}
	
}
