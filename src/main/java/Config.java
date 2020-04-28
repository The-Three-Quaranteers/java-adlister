import java.security.PublicKey;

public class Config {
    // this must be in the git ignore to prevent being pushed up to github!!!!

    // url host for mysql
    public String getUrl(){
        return "jdbc:mysql://localhost/contacts_db?serverTimezone=UTC&useSSL=false";
    }

    //mysql username
    public String getUsername(){
        return "brycepayne";
    }

    //mysql passcode
    public String getPassword(){
        return "Wbr@yhs01";
    }
}
