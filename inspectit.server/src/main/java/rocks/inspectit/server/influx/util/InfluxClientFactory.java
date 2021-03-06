package rocks.inspectit.server.influx.util;

import org.apache.commons.lang.StringUtils;
import org.influxdb.InfluxDB;
import org.influxdb.InfluxDBFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * Factory class to create {@link InfluxDB} clients.
 *
 * @author Marius Oehler
 *
 */
@Component
public class InfluxClientFactory {

	/**
	 * Host where InfluxDB is running.
	 */
	@Value("${influxdb.host}")
	String host;

	/**
	 * Port of the running InfluxDB.
	 */
	@Value("${influxdb.port}")
	int port;

	/**
	 * InfluxDB user.
	 */
	@Value("${influxdb.user}")
	String user;

	/**
	 * Password of the InfluxDB user.
	 */
	@Value("${influxdb.passwd}")
	String password;

	/**
	 * Creates a new {@link InfluxDB} client based on the configured host, port, user and password.
	 *
	 * @return An {@link InfluxDB} client.
	 * @throws IllegalArgumentException
	 *             is thrown if the client could not be created
	 */
	public InfluxDB createClient() throws IllegalArgumentException {
		if (StringUtils.isEmpty(host)) {
			throw new IllegalArgumentException("Host may not be null or empty.");
		}
		if (port <= 0) {
			throw new IllegalArgumentException("Specify a valid port in the range between 1 and 65535.");
		}
		String influxUrl = "http://" + host + ":" + port;
		return InfluxDBFactory.connect(influxUrl, user, password);
	}

}
