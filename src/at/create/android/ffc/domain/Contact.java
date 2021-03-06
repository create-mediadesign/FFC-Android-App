/*
 * Fat Free CRM Android App
 * Copyright 2012 create mediadesign GmbH
 * 
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU Affero General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 * 
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU Affero General Public License for more details.
 * 
 * You should have received a copy of the GNU Affero General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package at.create.android.ffc.domain;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.sql.Date;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.simpleframework.xml.Element;
import org.simpleframework.xml.Root;
import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.springframework.http.converter.xml.SimpleXmlHttpMessageConverter;

import android.text.TextUtils;
import android.util.Log;
import at.create.android.ffc.http.MockHttpInputMessage;

/**
 * @author Philipp Ullmann
 * The contacts model contains all the contact information for a person that the business connects with.
 */
@Root(name = "contact", strict = false)
public final class Contact {
    private static final String  TAG = Contact.class.getSimpleName();
    @Element
    private Integer id              = null;
    @Element(required = false)
    private String  title           = null;
    @Element(required = false)
    private String  department      = null;
    @Element(name = "first-name")
    private String  firstName       = null;
    @Element(name = "last-name")
    private String  lastName        = null;
    @Element(required = false)
    private String  email           = null;
    @Element(required = false)
    private String  phone           = null;
    @Element(required = false)
    private String  mobile          = null;
    @Element(required = false)
    private String  fax             = null;
    @Element(name = "born-on", required = false)
    private Date    bornOn          = null;
    @Element(name = "background-info", required = false)
    private String  backgroundInfo  = null;
    @Element(required = false)
    private String  blog            = null;
    @Element(required = false)
    private String  linkedin        = null;
    @Element(required = false)
    private String  facebook        = null;
    @Element(required = false)
    private String  twitter         = null;
    @Element(required = false)
    private String  skype           = null;
    
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
     * @return the mobile
     */
    public String getMobile() {
        return mobile;
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
    public Date getBornOn() {
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
    
    /**
     * @param id the id to set
     */
    public void setId(Integer id) {
        this.id = id;
    }
    
    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }
    
    /**
     * @param department the department to set
     */
    public void setDepartment(String department) {
        this.department = department;
    }
    
    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    
    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }
    
    /**
     * @param mobile the mobile to set
     */
    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
    
    /**
     * @param fax the fax to set
     */
    public void setFax(String fax) {
        this.fax = fax;
    }
    
    /**
     * @param bornOn the bornOn to set
     */
    public void setBornOn(Date bornOn) {
        this.bornOn = bornOn;
    }
    
    /**
     * @param bornOn
     */
    public void setBornOn(String bornOn) {
        if (!TextUtils.isEmpty(bornOn)) {
            DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
            try {
                this.bornOn = new Date(dateFormat.parse(bornOn).getTime());
            } catch (ParseException e) {
                Log.e(TAG,
                      "Can not parse born on date: \"" + bornOn + "\"",
                      e);
            }
        }
    }
    
    /**
     * @param backgroundInfo the backgroundInfo to set
     */
    public void setBackgroundInfo(String backgroundInfo) {
        this.backgroundInfo = backgroundInfo;
    }
    
    /**
     * @param blog the blog to set
     */
    public void setBlog(String blog) {
        this.blog = blog;
    }
    
    /**
     * @param linkedin the linkedin to set
     */
    public void setLinkedin(String linkedin) {
        this.linkedin = linkedin;
    }
    
    /**
     * @param facebook the facebook to set
     */
    public void setFacebook(String facebook) {
        this.facebook = facebook;
    }
    
    /**
     * @param twitter the twitter to set
     */
    public void setTwitter(String twitter) {
        this.twitter = twitter;
    }
    
    /**
     * @param skype the skype to set
     */
    public void setSkype(String skype) {
        this.skype = skype;
    }
    
    /**
     * @return true if this contact has an email address, otherwise false is returned.
     */
    public boolean hasEmailAddress() {
        return !TextUtils.isEmpty(email);
    }
    
    /**
     * @return true if this contact has a phone number, otherwise false is returned.
     */
    public boolean hasPhoneNumber() {
        return !TextUtils.isEmpty(phone);
    }
    
    /**
     * @return true if this contact has a mobile phone number, otherwise false is returned.
     */
    public boolean hasMobilePhoneNumber() {
        return !TextUtils.isEmpty(mobile);
    }
    
    /**
     * @return an XML represenation.
     * @throws Exception 
     */
    public String toXML() throws Exception {
        Serializer serializer     = new Persister();
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        serializer.write(this, out);
        return out.toString();
    }
    
    /**
     * @param xml
     * @return contact
     * @throws IOException
     */
    public static Contact fromXML(String xml) throws IOException {
        SimpleXmlHttpMessageConverter xmlConverter = new SimpleXmlHttpMessageConverter();
        MockHttpInputMessage inputMessage          = new MockHttpInputMessage(xml.getBytes("UTF-8"));
        return (Contact) xmlConverter.read(Contact.class, inputMessage);
    }
}
