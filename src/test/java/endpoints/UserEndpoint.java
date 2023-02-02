package endpoints;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import model.User;
import utils.CommonUtils;
import utils.HttpOperation;

public class UserEndpoint extends CommonUtils {
	private final String USER_ENDPOINT = "/user";


	public String createUser(User user) {

		initBase("baseURL");
		init(USER_ENDPOINT, HttpOperation.POST);
		setHeader("Content-Type","application/json");
		setBody(user);
		String resp = callIt();
		System.out.println("Created User: " + resp);
		return resp;
	}

	public String readUser(User user) {

		initBase("baseURL");
		init(USER_ENDPOINT+"/"+user.getUsername(), HttpOperation.GET);
		setHeader("Content-Type","application/json");
		String resp = callIt();
		System.out.println("Read User: " + resp);
		return resp;
	}

	public String deleteUser(User user) {

		initBase("baseURL");
		init(USER_ENDPOINT+"/"+user.getUsername(), HttpOperation.DELETE);
		setHeader("Content-Type","application/json");
		String resp = callIt();
		System.out.println("Read User: " + resp);
		return resp;
	}

	public String updateUser(User user,String username,String firstName,String lastName,String email,String password,String phone) {

		initBase("baseURL");
		init(USER_ENDPOINT+"/"+user.getUsername(), HttpOperation.PUT);
		setHeader("Content-Type","application/json");
		setBody(new User(username,firstName,lastName,email,password,phone));
		String resp = callIt();
		System.out.println("Update User: " + resp);
		return resp;
	}

	public void validateStatusCode(int statusCode) {
		assertIt(statusCode);
	}
}
