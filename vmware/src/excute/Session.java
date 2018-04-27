package excute;

import java.net.MalformedURLException;
import java.net.URL;
import java.rmi.RemoteException;

import com.vmware.vim25.mo.ServiceInstance;

public class Session {

	public static ServiceInstance getInstance(String host,String username,String password) throws RemoteException, MalformedURLException {
		URL url = new URL("https", host, "/sdk");
		return new ServiceInstance(url, username, password, true);
	}

}
