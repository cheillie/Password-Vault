# Password Vault

## Store & organize your digital data!

Password Vault can store all your digital data and passwords in a centralized 
vault, allowing easy access to any important information with a simple click. </p>

**Password Vault functions:**
- Save & edit your existing passwords, usernames, and email addresses

**Who will use Password Vault?** <br>
Password Vault is suitable for any internet users. As personal information are becoming
more often stored in computers, passwords offer the first line of defense against 
unauthorized access. It is sometimes difficult to keep track of all the different 
passwords for different websites, Password Vault is here to help internet users with
this problem.</p>

**Why this project is of interest to me?** <br>
As a university student with a part-time job, I often find myself needing to
create new accounts either for school or personal usage. As Chrome always
tells me, it's not safe to have the same password for every single website, 
so I created this application to help keep track of all the passwords and 
different email addresses I created.

**User Stories**
- As a user, I want to be able to add a password to my saved list
- As a user, I want to be able to remove a password that I've already saved
- As a user, I want to be able to enter a short 4-digit password to enter the Password
Vault Application
- As a user, I want to be able to save and load the data

**Phase 4: Task 2**
- Chose to make User class robust
- removeAccount() and setLogin()

**Phase 4: Task 3**
- LoginScreen, PasswordVault,and PasswordVaultUI all have an association with User,
JsonReader, and JsonWriter. It would be better if 3 of these classes extended another
class/abstract class that has the association.
- I should have made PasswordVaultUI an abstract class with fields User, JsonReader, 
JsonWriter, then have each of the pages with distinct functionality extend this abstract class.
