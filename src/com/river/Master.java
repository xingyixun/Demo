package com.river;

import java.io.IOException;

import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class Master implements Watcher{

	ZooKeeper zk;
	String hostPort;
	Master(String hostPort)
	{
		this.hostPort=hostPort;
	}
	void startzk() throws IOException{
		zk=new ZooKeeper("127.0.0.1",15000,this);
	}
	@Override
	public void process(WatchedEvent event) {
		// TODO Auto-generated method stub
		System.out.println(event.toString());
	}
	public static void main(String[] args) throws IOException, InterruptedException{
		Master m=new Master("2181");
		m.startzk();
		Thread.sleep(16000);
	}
}
