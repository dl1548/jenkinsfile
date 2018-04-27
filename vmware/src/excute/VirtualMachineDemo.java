package excute;
import java.beans.IntrospectionException;
import java.io.FileOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.vmware.vim25.HostConfigInfo;
import com.vmware.vim25.HostNetworkInfo;
import com.vmware.vim25.HostSystemResourceInfo;
import com.vmware.vim25.HostVMotionInfo;
import com.vmware.vim25.ManagedObjectReference;
import com.vmware.vim25.mo.ClusterComputeResource;
import com.vmware.vim25.mo.Folder;
import com.vmware.vim25.mo.HostSystem;
import com.vmware.vim25.mo.InventoryNavigator;
import com.vmware.vim25.mo.ManagedEntity;
import com.vmware.vim25.mo.ServiceInstance;
import com.vmware.vim25.mo.VirtualMachine;

import util.GetAccount;
import util.SaveVmwareInfo;
import util.WriterExcel;

public class VirtualMachineDemo
{
  private int id;
  private String login_name;
  private int org_id;
  private String password;
  private String url;
  private static List<VirtualMachineDemo> list = new ArrayList<>();
  
  public static void main(String[] args)
  {
    
    try
    {
      getMessage();
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }
  public static void getVmLoginMess()
  {
    ResultSet rs = GetAccount.getAccount();
    if (rs != null) {
      try
      {
        while (rs.next())
        {
          VirtualMachineDemo vd = new VirtualMachineDemo();
          vd.id = rs.getInt(1);
          vd.login_name = rs.getString(2);
          vd.org_id = rs.getInt(3);
          vd.password = rs.getString(4);
          vd.url = rs.getString(5);
          list.add(vd);
        }
      }
      catch (SQLException e)
      {
        e.printStackTrace();
      }
    }
  }
 /* public static List<String> getdifference(List<String> a,List<String> b){  
      List<String> content=new ArrayList<String>();  
      if(a !=null &&  b!=null){  
          if(a.size()>0 && b.size()>0){  
              for(int i=0;i<a.size();i++){  
                  boolean target=false;//默认在b集合中不存在  
                  String diffA=a.get(i);  
                  //判断是否字符串在指定的集合当中  
                  for(int j=0;j<b.size();j++){  
                      String diffB=b.get(j);  
                      if(diffA.equalsIgnoreCase(diffB)){  
                          target=true;  
                      }  
                  }  
                  //返回相关数据集合  
                  if(!target){  
                      content.add(diffA);  
                  }  


              }  
          }  
      }  

      return content;  
  }  */
  public static void getMessage()
    throws Exception
  {
	  getVmLoginMess();
    for (VirtualMachineDemo vd : list) {
      getMessage(Session.getInstance(vd.url, vd.login_name, vd.password), vd.id, vd.org_id);
    }
  }
  private static WriterExcel we=new WriterExcel();
  public static void getMessage(ServiceInstance serviceInstance, int loginId, int org_id)
    throws Exception
  {
    
    
	  List<DtreeObj> list=new ArrayList<DtreeObj>(); 
    //数据中心关联HostSystem  
    List<String> allhost=new ArrayList<String>();  
    //已经存在HostSystem  
    List<String> hostList=new ArrayList<String>();  
    //非集群HostSystem  
    List<String> noClusterHostList=null;  


    //rootFolder-------根文件夹  
    Folder rootFolder = serviceInstance.getRootFolder();  
//    System.out.println("datacenter is:"+rootFolder.getName());  

    int counterInt=1;  

    int rootId=0;  
    DtreeObj root=new DtreeObj();  
    root.setId(rootId);  
    root.setName(rootFolder.getName());  
    root.setPid(-1);  
    root.setType(4);  

    list.add(root);
    //inventoryNavigator----文件夹目录  
    InventoryNavigator inventoryNavigator =new InventoryNavigator(rootFolder);  
    //hostEntities--------查询实体对象（esxi）  
    ManagedEntity[] hostEntities=inventoryNavigator.searchManagedEntities("HostSystem");  
    if(hostEntities!=null && hostEntities.length>0){  
        for(int i=0;i<hostEntities.length;i++){  
            HostSystem hostSystem=(HostSystem)hostEntities[i];

            Map<String, Object> hostmap = new HashMap<>();
            Map<String, Object> hmap = new HashMap<>();
//            Map<String, Object> hmap1 = method(cluster.getNetworks(),hmap);
 //           we.writerexcel(hmap1,wb,"host");
            //System.out.println("������=" + hostSystem.getName());
            hostmap.put("org_id", Integer.valueOf(org_id));
            hostmap.put("loginId", Integer.valueOf(loginId));
//            hostmap.put("clustername", cluster.getName());
            hostmap.put("hostname", hostSystem.getName());
            hostmap.put("fullname", hostSystem.getConfig().getProduct().getFullName());
            hostmap.put("cpumodel", hostSystem.getSummary().getHardware().getCpuModel());
            hostmap.put("NumCpuPkgs", Short.valueOf(hostSystem.getSummary().getHardware().getNumCpuPkgs()));
            String ipadress="";
            HostVMotionInfo Vmotion=hostSystem.getConfig().getVmotion();
            if(null!=Vmotion){
            	if(null!=Vmotion.getIpConfig()){
            		ipadress=Vmotion.getIpConfig().ipAddress;
            		System.out.println("++++++++++++++++++"+ipadress);
            	}
            }
            hostmap.put("IpAddress", ipadress);
            hostmap.put("numcputhreads", Short.valueOf(hostSystem.getHardware().getCpuInfo().getNumCpuThreads()));
            hostmap.put("cpuMhz", Long.valueOf(hostSystem.getHardware().cpuInfo.hz / 1000L));
            hostmap.put("numCpuCores", Short.valueOf(hostSystem.getHardware().getCpuInfo().numCpuCores));
            hostmap.put("memorySize", Long.valueOf(hostSystem.getHardware().memorySize));
            hostmap.put("model", hostSystem.getHardware().systemInfo.model);
            hostmap.put("uuid", hostSystem.getHardware().systemInfo.uuid);
            hostmap.put("vendor", hostSystem.getHardware().systemInfo.vendor);
            hostmap.put("time", System.currentTimeMillis()/1000);//放入字段，更新时间
            if ((hostSystem.getConfig() != null) && (hostSystem.getConfig().product != null))
            {
              hostmap.put("productLineId", hostSystem.getConfig().product.productLineId);
              hostmap.put("osType", hostSystem.getConfig().product.osType);
            }
            SaveVmwareInfo.mapToSql(hostmap, "vmwareHostInfo");
            VirtualMachine[] virtualMachines = hostSystem.getVms();
            if ((virtualMachines != null) && (virtualMachines.length > 0))
            {
              for (int k = 0; k < virtualMachines.length; k++)
              {
            	Map<String, Object> vmmap = new HashMap<>();
                VirtualMachine virtualMachine = virtualMachines[k];
                Map<String, Object> vmap = new HashMap<>();
                Map<String, Object> vmap1 = method(virtualMachine,vmap);
//                we.writerexcel(vmap1,wb,"vm");
                //System.out.println("name��" + virtualMachine.getName() + " org_id��" + org_id + " loginId��" + loginId);
                vmmap.put("DistributedCpu", virtualMachine.getSummary().getQuickStats().getDistributedCpuEntitlement());
                vmmap.put("org_id", Integer.valueOf(org_id));
                vmmap.put("loginId", Integer.valueOf(loginId));
//                vmmap.put("clustername", cluster.getName());
                vmmap.put("hostname", hostSystem.getName());
                vmmap.put("vmname", virtualMachine.getName());
                vmmap.put("GuestFullName", virtualMachine.getConfig().guestFullName);
                vmmap.put("uuid", virtualMachine.getConfig().uuid);
                vmmap.put("guestFamily", virtualMachine.getGuest().guestFamily);
                vmmap.put("memoryMB", Integer.valueOf(virtualMachine.getConfig().getHardware().memoryMB));
                vmmap.put("NumCpu", Integer.valueOf(virtualMachine.getConfig().getHardware().numCPU));
                vmmap.put("DatastoreUrl", virtualMachine.getConfig().datastoreUrl[0].url);
                vmmap.put("vmPathName", virtualMachine.getConfig().files.vmPathName);
                vmmap.put("IpAddress", virtualMachine.getGuest().ipAddress);
                vmmap.put("guestState", virtualMachine.getGuest().guestState);
                vmmap.put("Version", virtualMachine.getConfig().getVersion());
                if (virtualMachine.getGuest().getNet() != null)
                {
                  vmmap.put("macAddress", virtualMachine.getGuest().getNet()[0].macAddress);
                  vmmap.put("network", virtualMachine.getGuest().getNet()[0].network);
                }
                vmmap.put("DatastoreName", virtualMachine.getDatastores()[0].getInfo().name);
                vmmap.put("Timestamp", virtualMachine.getStorage().timestamp.getTime());
                vmmap.put("StorageCommitted", Long.valueOf(virtualMachine.getStorage().getPerDatastoreUsage()[0].committed));
                vmmap.put("StorageUncommitted", Long.valueOf(virtualMachine.getStorage().getPerDatastoreUsage()[0].uncommitted));
                vmmap.put("MemoryShares", virtualMachine.getSummary().quickStats.getSharedMemory());
                vmmap.put("maxCpuUsage", virtualMachine.getSummary().getRuntime().maxCpuUsage);
                vmmap.put("Annotation", virtualMachine.getConfig().getAnnotation());
                vmmap.put("NumCoresPerSocket", virtualMachine.getConfig().getHardware().getNumCoresPerSocket());
                vmmap.put("time", System.currentTimeMillis()/1000);
                SaveVmwareInfo.mapToSql(vmmap, "vmwareVmInfo");
              }
            }
          
            
        }  
    }  
  
    
    ManagedEntity[] managedEntities = inventoryNavigator.searchManagedEntities("ClusterComputeResource");
    if ((managedEntities != null) && (managedEntities.length > 0)) {
      for (int i = 0; i <managedEntities.length; i++)
      {
    	  HSSFWorkbook wb = new HSSFWorkbook();
        ClusterComputeResource cluster = (ClusterComputeResource)managedEntities[i];
        HostSystem[] hostSystems = cluster.getHosts();
        cluster.getNetworks();
        if ((hostSystems != null) && (hostSystems.length > 0)) {
          for (int j = 0; j < hostSystems.length; j++)
          {
            Map<String, Object> hostmap = new HashMap<>();
            HostSystem hostSystem = hostSystems[j];
            Map<String, Object> hmap = new HashMap<>();
            Map<String, Object> hmap1 = method(cluster.getNetworks(),hmap);
 //           we.writerexcel(hmap1,wb,"host");
            //System.out.println("������=" + hostSystem.getName());
            hostmap.put("org_id", Integer.valueOf(org_id));
            hostmap.put("loginId", Integer.valueOf(loginId));
            hostmap.put("clustername", cluster.getName());
            hostmap.put("hostname", hostSystem.getName());
            hostmap.put("fullname", hostSystem.getConfig().getProduct().getFullName());
            hostmap.put("cpumodel", hostSystem.getSummary().getHardware().getCpuModel());
            hostmap.put("NumCpuPkgs", Short.valueOf(hostSystem.getSummary().getHardware().getNumCpuPkgs()));
            String ipadress="";
            HostVMotionInfo Vmotion=hostSystem.getConfig().getVmotion();
            if(null!=Vmotion){
            	if(null!=Vmotion.getIpConfig()){
            		ipadress=Vmotion.getIpConfig().ipAddress;
            		System.out.println("++++++++++++++++++"+ipadress);
            	}
            }
            hostmap.put("IpAddress", ipadress);
            hostmap.put("numcputhreads", Short.valueOf(hostSystem.getHardware().getCpuInfo().getNumCpuThreads()));
            hostmap.put("cpuMhz", Long.valueOf(hostSystem.getHardware().cpuInfo.hz / 1000L));
            hostmap.put("numCpuCores", Short.valueOf(hostSystem.getHardware().getCpuInfo().numCpuCores));
            hostmap.put("memorySize", Long.valueOf(hostSystem.getHardware().memorySize));
            hostmap.put("model", hostSystem.getHardware().systemInfo.model);
            hostmap.put("uuid", hostSystem.getHardware().systemInfo.uuid);
            hostmap.put("vendor", hostSystem.getHardware().systemInfo.vendor);
            hostmap.put("time", System.currentTimeMillis()/1000);//放入字段，更新时间
            if ((hostSystem.getConfig() != null) && (hostSystem.getConfig().product != null))
            {
              hostmap.put("productLineId", hostSystem.getConfig().product.productLineId);
              hostmap.put("osType", hostSystem.getConfig().product.osType);
            }
            SaveVmwareInfo.mapToSql(hostmap, "vmwareHostInfo");
            VirtualMachine[] virtualMachines = hostSystem.getVms();
            if ((virtualMachines != null) && (virtualMachines.length > 0))
            {
              for (int k = 0; k < virtualMachines.length; k++)
              {
            	Map<String, Object> vmmap = new HashMap<>();
                VirtualMachine virtualMachine = virtualMachines[k];
                Map<String, Object> vmap = new HashMap<>();
                Map<String, Object> vmap1 = method(virtualMachine,vmap);
//                we.writerexcel(vmap1,wb,"vm");
                //System.out.println("name��" + virtualMachine.getName() + " org_id��" + org_id + " loginId��" + loginId);
                vmmap.put("DistributedCpu", virtualMachine.getSummary().getQuickStats().getDistributedCpuEntitlement());
                vmmap.put("org_id", Integer.valueOf(org_id));
                vmmap.put("loginId", Integer.valueOf(loginId));
                vmmap.put("clustername", cluster.getName());
                vmmap.put("hostname", hostSystem.getName());
                vmmap.put("vmname", virtualMachine.getName());
                vmmap.put("GuestFullName", virtualMachine.getConfig().guestFullName);
                vmmap.put("uuid", virtualMachine.getConfig().uuid);
                vmmap.put("guestFamily", virtualMachine.getGuest().guestFamily);
                vmmap.put("memoryMB", Integer.valueOf(virtualMachine.getConfig().getHardware().memoryMB));
                vmmap.put("NumCpu", Integer.valueOf(virtualMachine.getConfig().getHardware().numCPU));
                vmmap.put("DatastoreUrl", virtualMachine.getConfig().datastoreUrl[0].url);
                vmmap.put("vmPathName", virtualMachine.getConfig().files.vmPathName);
                vmmap.put("IpAddress", virtualMachine.getGuest().ipAddress);
                vmmap.put("guestState", virtualMachine.getGuest().guestState);
                vmmap.put("Version", virtualMachine.getConfig().getVersion());
                if (virtualMachine.getGuest().getNet() != null)
                {
                  vmmap.put("macAddress", virtualMachine.getGuest().getNet()[0].macAddress);
                  vmmap.put("network", virtualMachine.getGuest().getNet()[0].network);
                }
                vmmap.put("DatastoreName", virtualMachine.getDatastores()[0].getInfo().name);
                vmmap.put("Timestamp", virtualMachine.getStorage().timestamp.getTime());
                vmmap.put("StorageCommitted", Long.valueOf(virtualMachine.getStorage().getPerDatastoreUsage()[0].committed));
                vmmap.put("StorageUncommitted", Long.valueOf(virtualMachine.getStorage().getPerDatastoreUsage()[0].uncommitted));
                vmmap.put("MemoryShares", virtualMachine.getSummary().quickStats.getSharedMemory());
                vmmap.put("maxCpuUsage", virtualMachine.getSummary().getRuntime().maxCpuUsage);
                vmmap.put("Annotation", virtualMachine.getConfig().getAnnotation());
                vmmap.put("NumCoresPerSocket", virtualMachine.getConfig().getHardware().getNumCoresPerSocket());
                vmmap.put("time", System.currentTimeMillis()/1000);
                SaveVmwareInfo.mapToSql(vmmap, "vmwareVmInfo");
              }
            }
          }
        }
//        FileOutputStream fileOut = new FileOutputStream("D:\\JK"+i+".xls");  
//        wb.write(fileOut);  
//        fileOut.close();  
      }
    }
//  //处理非集群HostSystem  
//   noClusterHostList=getdifference(allhost,hostList);  
//    if(noClusterHostList!=null && noClusterHostList.size()>0){  
//        for(int i=0;i<noClusterHostList.size();i++){  
//            String content=noClusterHostList.get(i);  
//             
//
//        }  
//    }  
    
  }
  
  
public static Map<String, Object> method(Object obj,Map<String, Object> map) throws IntrospectionException, IllegalAccessException, IllegalArgumentException, InvocationTargetException {
	     Method[] methods = obj.getClass().getDeclaredMethods();//获得属性
	   for (Method method : methods) {
		   if(method.getName().startsWith("get")){
			   Object o = null;
		   try {
			    o = method.invoke(obj);//执行get方法返回一个Object
			  // System.out.println("对象="+obj+"执行方法名="+method.getName()+"返回结果"+o);
		   } catch (Exception e) {
			   e.getMessage();
		   }
			   map.put(method.getName(), o);
			   if(null!=o)method(o,map);
		   }
	    }
	return map;
	}
}
