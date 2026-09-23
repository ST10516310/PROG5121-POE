public class Login {
    public boolean checkUserName(String u){
        return u.contains("_") && u.length()<=5;
    }
    public boolean checkPasswordComplexity(String p){
        boolean cap=false, num=false, spec=false;
        if(p.length()<8) return false;
        for(char c:p.toCharArray()){
            if(Character.isUpperCase(c)) cap=true;
            if(Character.isDigit(c)) num=true;
            if(!Character.isLetterOrDigit(c)) spec=true;
        }
        return cap && num && spec;
    }
    public boolean checkCellPhoneNumber(String c){
        return c.matches("\\+27\\d{9}");
    }
    public String registerUser(String u,String p,String cell){
        if(!checkUserName(u)) return "Username is not correctly formatted, please ensure that your username contains an underscore and is no more than five characters in length.";
        if(!checkPasswordComplexity(p)) return "Password is not correctly formatted; please ensure that the password contains at least eight characters, a capital letter, a number, and a special character.";
        if(!checkCellPhoneNumber(cell)) return "Cell phone number incorrectly formatted or does not contain international code.";
        return "User registered successfully.";
    }
    public boolean loginUser(String u1,String p1,String u2,String p2){
        return u1.equals(u2) && p1.equals(p2);
    }
    public String returnLoginStatus(boolean s){
        if(s) return "Welcome ST_10, it is great to see you again.";
        return "Username or password incorrect, please try again.";
    }
}