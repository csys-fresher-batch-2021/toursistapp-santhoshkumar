package in.santhosh.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Random;

import javax.net.ssl.HttpsURLConnection;

public class SendOtp {
	private SendOtp() {

	}
	/**
	 * This method is used to send otp
	 * @param message
	 * @param number
	 * @param apiKey
	 */

	public static void sendOTP(String message, String number, String apiKey) {
		try {
			String sendId = "TXTIND";
			String language = "english";
			String route = "v3";

			message = URLEncoder.encode(message, "UTF-8"); // Important Step

			String myUrl = "https://www.fast2sms.com/dev/bulkV2?authorization=" + apiKey + "&sender_id=" + sendId
					+ "&message=" + message + "&language=" + language + "&route=" + route + "&numbers=" + number;

			URL url = new URL(myUrl);
			HttpsURLConnection con = (HttpsURLConnection) url.openConnection();

			con.setRequestMethod("GET");

			con.setRequestProperty("User-Agent", "Mozilla/5.0");
			con.setRequestProperty("cache-control", "no-cache");

			int responseCode = con.getResponseCode();

			StringBuffer response = new StringBuffer();

			BufferedReader br = new BufferedReader(new InputStreamReader(con.getInputStream()));

			while (true) {
				String line = br.readLine();

				if (line == null) {
					break;
				}

				response.append(line);
			}

			System.out.println(response);

		} catch (Exception e) {
			System.out.println(e);
		}
	}

	public static String generateOTP(int otpLength) {
		Random random = new Random();

		StringBuilder sb = new StringBuilder();

		for (int i = 0; i < otpLength; i++) {
			sb.append(random.nextInt(10));
		}

		 return sb.toString();

	}

	public static String userOpt(String mobileNumber) {
		System.out.println("Program Started....");

		String otpmessage = SendOtp.generateOTP(5);
		System.out.println("Generate OTP : " + otpmessage);

		String apiKey = "bZ84kgKHiPl7QCtpDFE5JBncOLomjRUYqfhIsG9SaNdvz1uwA39Lu0NRmap1EGdKBF4Vel3nQSJgyC6O";
		String number = mobileNumber;

		sendOTP("Hey this message is send by tour planners. Your OTP is:" + otpmessage, number, apiKey);

		return otpmessage;

	}
}
