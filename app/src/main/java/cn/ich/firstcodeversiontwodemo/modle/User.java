package cn.ich.firstcodeversiontwodemo.modle;

public class User {
    private String userName;
    private String gender;
    private Integer age;

    public User(String userName, String gender, Integer age) {
        this.userName = userName;
        this.gender = gender;
        this.age = age;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getUserName() {
        return userName;
    }

    public String getGender() {
        return gender;
    }

    public Integer getAge() {
        return age;
    }
}
