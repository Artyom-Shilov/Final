package by.shilov.newsSite.domain;

import java.util.Date;
import java.util.Objects;

public class User extends Entity {

    private Role role;
    private String login;
    private String password;
    private String name;
    private String surname;
    private Date registrationDate;
    private Date dateOfBirth;
    private String iconUrl;
    private UserStatus status = UserStatus.UNBLOCKED;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getIconUrl() {
        return iconUrl;
    }

    public void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }

    public UserStatus getStatus() {
        return status;
    }

    public void setStatus(UserStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return role == user.role &&
                Objects.equals(login, user.login) &&
                Objects.equals(password, user.password) &&
                Objects.equals(name, user.name) &&
                Objects.equals(surname, user.surname) &&
                Objects.equals(registrationDate, user.registrationDate) &&
                Objects.equals(dateOfBirth, user.dateOfBirth) &&
                Objects.equals(iconUrl, user.iconUrl) &&
                status == user.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, login, password, name, surname, registrationDate, dateOfBirth, iconUrl, status);
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + getId() +
                ", role='" + role + '\'' +
                ", login='" + login + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", registrationDate=" + registrationDate +
                ", dateOfBirth=" + dateOfBirth +
                ", iconUrl='" + iconUrl + '\'' +
                ", status=" + status +
                '}';
    }
}
