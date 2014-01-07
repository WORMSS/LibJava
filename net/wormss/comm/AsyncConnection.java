package net.wormss.comm;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.AbstractHttpClient;
import org.apache.http.impl.client.DefaultHttpClient;

import android.os.AsyncTask;

public class AsyncConnection extends AsyncTask<HttpUriRequest, Void, HttpResponse[]>
{
	private final OnCompleteListener lisener;
	private final AbstractHttpClient client;
	
	public AsyncConnection()
	{
		this((OnCompleteListener) null);
	}
	
	public AsyncConnection(AbstractHttpClient client)
	{
		this(null, client);
	}
	
	public AsyncConnection(OnCompleteListener listener)
	{
		this(listener, new DefaultHttpClient());
	}
	
	public AsyncConnection(OnCompleteListener listener, AbstractHttpClient client)
	{
		this.lisener = listener;
		this.client = client;
	}
	
	@Override
	protected HttpResponse[] doInBackground(HttpUriRequest... params )
	{
		int length = params.length;
		HttpResponse[] responses = new HttpResponse[length];
		
		int i = 0;
		while( i < length )
		{
			try	{ responses[i] = client.execute(params[i]); }
			catch (IOException e) { e.printStackTrace(); }
			finally { i++; }
		}
		return responses;
	}
	
	@Override
	protected void onPostExecute(HttpResponse[] responses)
	{
		if ( lisener != null ) lisener.onComplete(responses);
	}
	
	public static interface OnCompleteListener
	{
		void onComplete(HttpResponse[] responses);
	}
	
}