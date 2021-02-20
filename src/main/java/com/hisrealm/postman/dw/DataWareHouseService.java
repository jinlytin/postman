package com.hisrealm.postman.dw;


import org.springframework.stereotype.Service;

import javax.servlet.AsyncContext;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author dingjingli
 * @version 1.0
 * @date 2021/2/19 20:05
 */
@Service
public class DataWareHouseService {



	private class AsyncTask{

		private AsyncContext asyncContext;
		private Long requestTime;
		private boolean timeout=false;

		public AsyncTask(AsyncContext asyncContext, Long requestTime) {
			this.asyncContext = asyncContext;
			this.requestTime = requestTime;
		}

		public AsyncContext getAsyncContext() {
			return asyncContext;
		}

		public void setAsyncContext(AsyncContext asyncContext) {
			this.asyncContext = asyncContext;
		}

		public Long getRequestTime() {
			return requestTime;
		}

		public void setRequestTime(Long requestTime) {
			this.requestTime = requestTime;
		}

		public boolean isTimeout() {
			return timeout;
		}

		public void setTimeout(boolean timeout) {
			this.timeout = timeout;
		}
	}


	private static Map<String, List<AsyncTask>> dataMap = new HashMap<>();

	private static Map<String,String> configValueMap = new HashMap<>();



	 public void addListener(String configKey,AsyncContext asyncContext){

	 	if(dataMap.get(configKey)==null){
	 		dataMap.put(configKey,new ArrayList<>());
	  }
		dataMap.get(configKey).add(new AsyncTask(asyncContext,System.currentTimeMillis()));
	 }



	 public boolean updateConfig(String configKey,String value){
			configValueMap.put(configKey,value);
		 new Thread(()->{postUpdateToClient(configKey);}).start();

			return true;
	 }



	 public void postUpdateToClient(String configKey){

	 	List<AsyncTask> taskList = dataMap.get(configKey);
	 	if(taskList!=null && taskList.size()>0){
	 		for(AsyncTask task:taskList){
			  try {
				  task.getAsyncContext().getResponse().getWriter().print(configValueMap.get(configKey));
				  task.getAsyncContext().complete();
			  } catch (IOException e) {
				  e.printStackTrace();
			  }
		  }
	  }

	 }



}
