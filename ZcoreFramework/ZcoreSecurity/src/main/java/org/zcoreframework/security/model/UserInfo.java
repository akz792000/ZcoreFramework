/**
 * @author Ali Karimizandi
 * @since 2009
 */

package org.zcoreframework.security.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import com.fasterxml.jackson.databind.ObjectMapper.DefaultTyping;
import com.ibm.icu.util.Calendar;
import com.ibm.icu.util.PersianCalendar;
import com.ibm.icu.util.ULocale;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.zcoreframework.base.annotation.JsonAutoDetectNone;
import org.zcoreframework.base.calendar.icu.PersianDateFormat;
import org.zcoreframework.base.model.PropertyModel;
import org.zcoreframework.base.util.JsonUtils;
import org.zcoreframework.security.core.ComplexGrantedAuthority;
import org.zcoreframework.security.core.DeterrentState;

import java.util.*;

@JsonAutoDetectNone
public class UserInfo implements UserDetails {

    private static final long serialVersionUID = 1L;

    private String stream;

    @JsonProperty
    private long id;

    @JsonProperty
    private String trackId;

    @JsonProperty
    private Set<DeterrentState> DeterrentStates = new LinkedHashSet<>();

    @JsonProperty
    private long channelTypeId;

    @JsonProperty
    private String username;

    @JsonProperty
    private String password;

    @JsonProperty
    private String firstname;

    @JsonProperty
    private String lastname;

    @JsonProperty
    private boolean enabled;

    @JsonProperty
    private Date enabledDate;

    @JsonProperty
    private boolean locked;

    @JsonProperty
    private String lockedDue;

    @JsonProperty
    private boolean expired;

    @JsonProperty
    private Date expiredDate;

    @JsonProperty
    private boolean credentialsExpired;

    @JsonProperty
    private Date credentialsExpiredDate;

    @JsonProperty
    private Boolean credentialsExpiredPeriodical;

    @JsonProperty
    private Long credentialsExpiredPeriod;

    @JsonProperty
    private Date credentialsLastChangedDate;

    @JsonProperty
    private int sessionTimeout;

    @JsonProperty
    @JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.WRAPPER_OBJECT)
    private List<GrantedAuthority> authorities = new ArrayList<>();

    public UserInfo() {
    }

    public UserInfo(long id) {
        this.id = id;
    }

    public String getStream() {
        if (StringUtils.isEmpty(stream)) {
            return JsonUtils.encode(this, DefaultTyping.JAVA_LANG_OBJECT);
        }
        return stream;
    }

    public void setStream(String stream) {
        this.stream = stream;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTrackId() {
        return trackId;
    }

    public void setTrackId(String trackId) {
        this.trackId = trackId;
    }

    public Set<DeterrentState> getDeterrentStates() {
        return DeterrentStates;
    }

    public void setDeterrentStates(Set<DeterrentState> deterrentStates) {
        DeterrentStates = deterrentStates;
    }

    public long getChannelTypeId() {
        return channelTypeId;
    }

    public void setChannelTypeId(long channelTypeId) {
        this.channelTypeId = channelTypeId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public Date getEnabledDate() {
        return enabledDate;
    }

    public void setEnabledDate(Date enabledDate) {
        this.enabledDate = enabledDate;
    }

    public boolean isLocked() {
        return locked;
    }

    public void setLocked(boolean locked) {
        this.locked = locked;
    }

    public String getLockedDue() {
        return lockedDue;
    }

    public void setLockedDue(String lockedDue) {
        this.lockedDue = lockedDue;
    }

    public boolean isExpired() {
        return expired;
    }

    public void setExpired(boolean expired) {
        this.expired = expired;
    }

    public Date getExpiredDate() {
        return expiredDate;
    }

    public void setExpiredDate(Date expiredDate) {
        this.expiredDate = expiredDate;
    }

    public boolean isCredentialsExpired() {
        return credentialsExpired;
    }

    public void setCredentialsExpired(boolean credentialsExpired) {
        this.credentialsExpired = credentialsExpired;
    }

    public Date getCredentialsExpiredDate() {
        return credentialsExpiredDate;
    }

    public void setCredentialsExpiredDate(Date credentialsExpiredDate) {
        this.credentialsExpiredDate = credentialsExpiredDate;
    }

    public Boolean getCredentialsExpiredPeriodical() {
        return credentialsExpiredPeriodical;
    }

    public void setCredentialsExpiredPeriodical(Boolean credentialsExpiredPeriodical) {
        this.credentialsExpiredPeriodical = credentialsExpiredPeriodical;
    }

    public Long getCredentialsExpiredPeriod() {
        return credentialsExpiredPeriod;
    }

    public void setCredentialsExpiredPeriod(Long credentialsExpiredPeriod) {
        this.credentialsExpiredPeriod = credentialsExpiredPeriod;
    }

    public Date getCredentialsLastChangedDate() {
        return credentialsLastChangedDate;
    }

    public void setCredentialsLastChangedDate(Date credentialsLastChangedDate) {
        this.credentialsLastChangedDate = credentialsLastChangedDate;
    }

    public int getSessionTimeout() {
        return sessionTimeout;
    }

    public void setSessionTimeout(int sessionTimeout) {
        this.sessionTimeout = sessionTimeout;
    }

    public List<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(List<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    public int hashCode() {
        return (int) getId();
    }

    @Override
    public boolean equals(Object obj) {
        return ((UserInfo) obj).getId() == getId();
    }

    @Override
    public boolean isAccountNonExpired() {
        return !expired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return !locked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return !credentialsExpired;
    }

    public boolean isCredentialsNonExpiredPeriodical() {
        return !credentialsExpiredPeriodical;
    }

    public Boolean isMultiOrganizational() {
        return authorities.size() > 1;
    }

    public List<PropertyModel> getOrganizations() {
        List<PropertyModel> result = new ArrayList<>();
        for (GrantedAuthority grantedAuthority : authorities) {
            result.add(((ComplexGrantedAuthority) grantedAuthority).getOrganization());
        }
        return result;
    }

    public PropertyModel getOrganization() {
        return ((ComplexGrantedAuthority) authorities.get(0)).getOrganization();
    }

    public List<PropertyModel> getPositions() {
        List<PropertyModel> result = new ArrayList<>();
        for (GrantedAuthority grantedAuthority : authorities) {
            result.add(((ComplexGrantedAuthority) grantedAuthority).getPosition());
        }
        return result;
    }

    public PropertyModel getPosition() {
        return ((ComplexGrantedAuthority) authorities.get(0)).getPosition();
    }

    public Date getCalendarDate() {
        return ((ComplexGrantedAuthority) authorities.get(0)).getDate();
    }

    @SuppressWarnings("static-access")
    public Date getCalendarDateTime() {
        PersianDateFormat formatTime = new PersianDateFormat("HH:mm:ss", new ULocale("fz", "IR", ""));
        Calendar calendar = new PersianCalendar();
        calendar.setTime(getCalendarDate());
        calendar.set(Calendar.HOUR_OF_DAY, formatTime.getCalendar().get(Calendar.HOUR_OF_DAY));
        calendar.set(Calendar.MINUTE, formatTime.getCalendar().get(Calendar.MINUTE));
        calendar.set(Calendar.SECOND, formatTime.getCalendar().get(Calendar.SECOND));
        calendar.set(Calendar.MILLISECOND, 0);
        return calendar.getTime();
    }

    public List<PropertyModel> getRoles() {
        return ((ComplexGrantedAuthority) authorities.get(0)).getRoles();
    }

    public boolean hasDeterrentState() {
        return getDeterrentStates().size() != 0;
    }

    public boolean isDeterrentStateExist(DeterrentState deterrentState) {
        for (Iterator<DeterrentState> item = getDeterrentStates().iterator(); item.hasNext(); ) {
            if (((DeterrentState) item.next()).equals(deterrentState)) {
                return true;
            }
        }
        return false;
    }

    public DeterrentState getDefaultDeterrentState() {
        DeterrentState result = null;
        for (Iterator<DeterrentState> item = getDeterrentStates().iterator(); item.hasNext(); ) {
            DeterrentState deterrentState = (DeterrentState) item.next();
            if (result == null || result.value() < deterrentState.value()) {
                result = deterrentState;
            }
        }
        return result;
    }

    public String getFullname() {
        return firstname + " " + lastname;
    }

}