package org.zhaojin;

import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;

import org.json.JSONArray;
import org.json.JSONException;

public class ServicePlugin extends CordovaPlugin {
	public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
	    if ("echo".equals(action)) {
	    	String message = args.getString(0); 
	    	/* cordova.getActivity().runOnUiThread(new Runnable() {//与UI交互
	              public void run() {
	            	  //...
	                  callbackContext.success(); // Thread-safe.
	              }
	          });
	    	  cordova.getThreadPool().execute(new Runnable() {//开新的线程,而不阻塞WebCore线程
	              public void run() {
	                 // ...
	                  callbackContext.success(); // Thread-safe.
	              }
	          });
	    	  */
	    	this.echo(message, callbackContext);
	        callbackContext.success();
	        return true;
	    }
	    return false;  // Returning false results in a "MethodNotFound" error.
	}
	private void echo(String message, CallbackContext callbackContext) {
        if (message != null && message.length() > 0) { 
            callbackContext.success("hello "+message);
        } else {
            callbackContext.error("Expected one non-empty string argument.");
        }
    }
}
