package com.generic;

import java.net.HttpURLConnection;
import java.net.URL;

class PractiTestResult
{
	public static int exit_code;

	public static void resultUpload(String instance_id)

	{
		try
		{

			StringBuilder sb = new StringBuilder();

			String token = "$TOKEN$";
			URL url = new URL("https://prod.practitest.com/api/automated_tests/upload_test_result.json");
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();

			sb.append("custom api_token=").append("cf2cd7ffa39cc95a5c41364674c3c371d562b968");
			conn.setDoOutput(true);
			conn.setRequestProperty("Authorization", sb.toString());
			// httpWebRequest.ContentType = "application/json";
			conn.setRequestProperty("Content-Type", "application/json");
			// httpWebRequest.Method = "POST";
			conn.setRequestMethod("POST");
			// try (java.io.OutputStreamWriter streamWriter = new
			// java.io.OutputStreamWriter(httpWebRequest.GetRequestStream()))

			try (java.io.OutputStreamWriter streamWriter = new java.io.OutputStreamWriter(conn.getOutputStream()))
			{

				// String json =
				// "{\"project_id\":39,\"instance_display_id\":\"2:1\",\"exit_code\":1, \"api_token\":\""
				// + token + "\"}";
				String json = "{\"project_id\":2618,\"instance_display_id\":" + "\"" + instance_id + "\"" + ",\"exit_code\":" + exit_code + ", \"api_token\":\"" + token + "\"}";

				// String json =
				// "{\"project_id\":2618,\"instance_display_id\":\"28:1\",\"exit_code\":1, \"api_token\":\""
				// + token + "\"}";
				System.out.println(json);
				streamWriter.write(json);
				streamWriter.flush();
				streamWriter.close();
			}
			// HttpWebResponse httpResponse =
			// (HttpWebResponse)httpWebRequest.GetResponse();
			int response = conn.getResponseCode();
			// printResponse(httpResponse);
			System.out.println("Response : " + response);
			System.out.println("Uploaded to practtitest");
		} catch (Exception e)
		{
			e.printStackTrace();
		}

	}
}
