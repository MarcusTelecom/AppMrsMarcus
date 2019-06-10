package telecom.marcus.appmrsmarcus;

import org.json.JSONArray;

public class ClassUser {

    private String id;
    private String name;
    private String user_name;
    private String registration;
    private String function;


    public ClassUser(String id, String name, String user_name, String registration, String function) {
        this.id = id;
        this.name = name;
        this.user_name = user_name;
        this.registration = registration;
        this.function = function;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getRegistration() {
        return registration;
    }

    public void setRegistration(String registration) {
        this.registration = registration;
    }

    public String getFunction() {
        return function;
    }

    public void setFunction(String function) {
        this.function = function;
    }
}
