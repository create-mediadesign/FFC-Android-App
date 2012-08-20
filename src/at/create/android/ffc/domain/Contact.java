package at.create.android.ffc.domain;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;

/**
 * @author Philipp Ullmann
 * The contacts model contains all the contact information for a person that the business connects with.
 */
@Root(name = "contact", strict = false)
public final class Contact {
    @Element
    private Integer id;
    @Element(required = false)
    private String  title;
    @Element(required = false)
    private String  department;
    @Element(name = "first-name")
    private String  firstName;
    @Element(name = "last-name")
    private String  lastName;
    @Element(required = false)
    private String  email;
    @Element(required = false)
    private String  phone;
    @Element(required = false)
    private String  mobil;
    @Element(required = false)
    private String  fax;
    @Element(name = "born-on", required = false)
    private String  bornOn;
    @Element(name = "background-info", required = false)
    private String  backgroundInfo;
    @Element(required = false)
    private String  blog;
    @Element(required = false)
    private String  linkedin;
    @Element(required = false)
    private String  facebook;
    @Element(required = false)
    private String  twitter;
    @Element(required = false)
    private String  skype;
    
    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }
    
    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }
    
    /**
     * @return the department
     */
    public String getDepartment() {
        return department;
    }
    
    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }
    
    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }
    
    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }
    
    /**
     * @return the mobil
     */
    public String getMobil() {
        return mobil;
    }
    
    /**
     * @return the fax
     */
    public String getFax() {
        return fax;
    }
    
    /**
     * @return the bornOn
     */
    public String getBornOn() {
        return bornOn;
    }
    
    /**
     * @return the backgroundInfo
     */
    public String getBackgroundInfo() {
        return backgroundInfo;
    }
    
    /**
     * @return the blog
     */
    public String getBlog() {
        return blog;
    }
    
    /**
     * @return the linkedin
     */
    public String getLinkedin() {
        return linkedin;
    }
    
    /**
     * @return the facebook
     */
    public String getFacebook() {
        return facebook;
    }
    
    /**
     * @return the twitter
     */
    public String getTwitter() {
        return twitter;
    }
    
    /**
     * @return the skype
     */
    public String getSkype() {
        return skype;
    }
    
    /**
     * @return the name (first name and last name)
     */
    public String getName() {
        StringBuilder sb = new StringBuilder();
        sb.append(firstName);
        sb.append(" ");
        sb.append(lastName);
        return sb.toString();
    }
}
