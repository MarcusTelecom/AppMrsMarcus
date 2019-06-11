package telecom.marcus.appmrsmarcus;

public class ClassUser {
    private String id;
    private String name;
    private String name_user;
    private String registration;
    private String function;

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

    public String getName_user() {
        return name_user;
    }

    public void setName_user(String name_user) {
        this.name_user = name_user;
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

    public ClassUser(String id, String name, String name_user, String registration, String function) {
        this.id = id;
        this.name = name;
        this.name_user = name_user;
        this.registration = registration;
        this.function = function;


    }
}

