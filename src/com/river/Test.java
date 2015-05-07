package com.river;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class Test {

	public static void assignDbsWithMech(OpenResourceParamVo paramVo,List<DbsPhysicalResourcePool> modelList){
		//从数据库中查找空余机器，组成hashmap<String,list>,其中key 为ip，list为同一ip下的物理库集合
		HashMap<String,ArrayList<DbsPhysicalResourcePool>> model=new HashMap<>();
		for(DbsPhysicalResourcePool instance: modelList)
		{
			ArrayList<DbsPhysicalResourcePool> list=model.get(instance.getResHost());
			
			if(list!=null&&!list.contains(instance))
			{
				list.add(instance);
			}
			if(model.get(instance.getResHost())==null)
			{
				if(list==null)
				{
					list=new ArrayList<>();
				}
				list.add(instance);
				model.put(instance.getResHost(),list);
			}
		}
		int dbNum=paramVo.getMasterNum();
		List<DbsPhysicalResourcePool> masterdb=new ArrayList<DbsPhysicalResourcePool>();
		
		//测试集合类，测试完成后删除
		List<DbsPhysicalResourcePool> test=new ArrayList<DbsPhysicalResourcePool>();
		//分配主库，尽量保证主库的IP不同
		while(dbNum>0)
		{	
			Iterator<Entry<String,ArrayList<DbsPhysicalResourcePool>>> iterator=model.entrySet().iterator();
			while(iterator.hasNext()){
				Map.Entry<String,ArrayList<DbsPhysicalResourcePool>> entry = iterator.next();
				if(entry.getValue()!=null&&entry.getValue().size()!=0)
				{
					DbsPhysicalResourcePool dbsInstance=entry.getValue().get(0);
					masterdb.add(dbsInstance);
					entry.getValue().remove(0);
					--dbNum;
				}else{
					iterator.remove();
				}
			}
		}
		
		
		HashMap<String,ArrayList<DbsPhysicalResourcePool>> slaveIns=removeEmpty(model);
		//测试
		test.addAll(masterdb);
		//分配从库，尽量保证从库IP与主库不同
		for(DbsPhysicalResourcePool slave:masterdb)
		{
			List<DbsPhysicalResourcePool> slaveassign=new ArrayList<DbsPhysicalResourcePool>();
			slaveassign.addAll(masterdb);
			slaveassign.remove(slave);
			boolean complete=false;
			DbsPhysicalResourcePool dbs=new DbsPhysicalResourcePool();
			for(int i=0;i<slaveassign.size();i++)
			{
				String host=slaveassign.get(i).getResHost();
				if(slaveIns.get(host)!=null&&slaveIns.get(host).size()!=0)
				{
					dbs=slaveIns.get(host).get(0);
					dbs.setMsFlag("slave");
					test.add(dbs);
					slaveIns.get(host).remove(0);
					complete=true;
					break;
				}
			}
			if(complete)
			{
				continue;
			}else{
				Iterator<Entry<String,ArrayList<DbsPhysicalResourcePool>>> iterator=model.entrySet().iterator();
				while(iterator.hasNext())
				{
					Map.Entry<String,ArrayList<DbsPhysicalResourcePool>> entry = iterator.next();
				    ArrayList<DbsPhysicalResourcePool> list=entry.getValue();
				    if(list==null||list.size()==0)
				    {
				    	continue;
				    }else{
				    	dbs=list.get(0);
				    	dbs.setMsFlag("slave");
				    	list.remove(0);
				    	test.add(dbs);
					    break;
				    }
				    
				}
			}
			
		}
		
		
		//测试
		for(DbsPhysicalResourcePool instance:test)
		{
			System.out.println("name:"+instance.getResName()+";host:"+instance.getResHost()+";isMaster:"+instance.getMsFlag());
		}
	}
	public static HashMap<String,ArrayList<DbsPhysicalResourcePool>> removeEmpty(HashMap<String,ArrayList<DbsPhysicalResourcePool>> model){
		Iterator<Entry<String, ArrayList<DbsPhysicalResourcePool>>> iterator = model.entrySet().iterator();
		while (iterator.hasNext()) {
		    Map.Entry<String,ArrayList<DbsPhysicalResourcePool>> entry = iterator.next();
		    String key=entry.getKey();
		    if(entry.getValue().size()==0)
		    {
		    	iterator.remove();
		    }
		}
		
		return model;
	}
	public static void main(String[] args)
	{
		List<DbsPhysicalResourcePool> modelList=new ArrayList<>();
		for(int i=0;i<6;i++)
		{
			DbsPhysicalResourcePool dbs=new DbsPhysicalResourcePool();
			String host=new Integer(i).toString();
			dbs.setResHost("1");
			dbs.setResName(host);
			dbs.setMsFlag("master");
			/*if(host.equals("5"))
			{
				dbs.setResHost("2");
			}*/
			modelList.add(dbs);
		}
		OpenResourceParamVo param=new OpenResourceParamVo();
		param.setMasterNum(3);
		Test.assignDbsWithMech(param, modelList);
	}
}
