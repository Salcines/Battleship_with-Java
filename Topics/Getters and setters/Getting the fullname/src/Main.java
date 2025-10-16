class User {
    private String firstName;
    private String lastName;

    public User() {
        this.firstName = "";
        this.lastName = "";
    }

    public void setFirstName(String firstName) {
        if ((firstName == null) || (firstName.isEmpty())) {
            firstName = "";
        }
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        if ((lastName == null) || (lastName.isEmpty())) {
            lastName = "";
        }
        this.lastName = lastName;
    }

    public String getFullName() {
        String fullName;
        if ((firstName.isEmpty()) && (lastName.isEmpty())) {
            return "Unknown";
        }
        if (lastName.isEmpty()) {
            return firstName;
        }

        if (firstName.isEmpty()) {
            return lastName;
        }

        return String.format("%s %s", firstName, lastName);
    }
}