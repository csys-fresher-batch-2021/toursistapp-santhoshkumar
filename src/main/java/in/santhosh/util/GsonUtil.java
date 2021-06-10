package in.santhosh.util;
import java.time.LocalDate;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;


public class GsonUtil {
	private GsonUtil(){
		//DEFAULT Constructor
	}

	public static Gson create() {
		 Gson gson = new GsonBuilder().setPrettyPrinting()
				 .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
			.create();
		 return gson;
	}
	

}
