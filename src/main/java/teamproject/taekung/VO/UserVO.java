package teamproject.taekung.VO;

/**
 * Created by taeku on 2016-09-14.
 */
public class UserVO {
    private  String id="";
    private  String pwd="";
    private  String email="";
    private  String phone="";
    private  String address="";
    private  String storename="";




    public UserVO(String id, String pwd, String email, String phone, String address, String storename) {
        this.id = id;
        this.pwd = pwd;
        this.email = email;
        this.phone = phone;
        this.address = address;
        this.storename = storename;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPwd() {
        return pwd;
    }

    public void setPwd(String pwd) {
        this.pwd = pwd;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }
}
