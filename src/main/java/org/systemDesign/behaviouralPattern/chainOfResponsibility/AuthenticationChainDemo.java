package org.systemDesign.behaviouralPattern.chainOfResponsibility;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class AuthenticationChainDemo {
    public static void main(String[] args) {
        // Build the chain
        AuthenticationHandler userPassHandler = new UsernamePasswordHandler();
        AuthenticationHandler roleHandler = new RoleCheckHandler();
        AuthenticationHandler statusHandler = new AccountStatusHandler();
        userPassHandler.setNext(roleHandler);
        roleHandler.setNext(statusHandler);

        System.out.println("=== Test 1: Valid user ===");
        boolean result1 = userPassHandler.authenticate("admin", "admin123");
        System.out.println("Result: " + (result1 ? "AUTHENTICATED" : "FAILED") + "\n");

        System.out.println("=== Test 2: Invalid password ===");
        boolean result2 = userPassHandler.authenticate("admin", "wrong");
        System.out.println("Result: " + (result2 ? "AUTHENTICATED" : "FAILED") + "\n");

        System.out.println("=== Test 3: Blocked user ===");
        boolean result3 = userPassHandler.authenticate("blocked_user", "any");
        System.out.println("Result: " + (result3 ? "AUTHENTICATED" : "FAILED"));
    }
}
// Handler Interface
abstract class AuthenticationHandler {
    protected AuthenticationHandler nextHandler;
    public void setNext(AuthenticationHandler handler){
        this.nextHandler = handler;
    }
    public abstract boolean authenticate(String userName, String password);
}
// Concrete Handler 1 : Username/Password validation
class UsernamePasswordHandler extends AuthenticationHandler {
    private final Map<String, String> validUsers;
    public UsernamePasswordHandler(){
        this.validUsers = new HashMap<>();
        validUsers.put("admin", "admin123");
        validUsers.put("user", "user123");
    }
    @Override
    public boolean authenticate(String userName, String password) {
        System.out.println("UsernamePasswordHandler: Checking credentials");
        if(validUsers.containsKey(userName) && validUsers.get(userName).equals(password)) {
            System.out.println("✓ Valid credentials");
            if (null != this.nextHandler) {
                return this.nextHandler.authenticate(userName, password);
            }
            return true;
        }
        System.out.println("✗ Invalid credentials");
        return false; // Stop chain
    }
}
// Concrete Handler 2: Role-based access
class RoleCheckHandler extends AuthenticationHandler {
    private final Map<String, String> userRoles;
    public RoleCheckHandler(){
        this.userRoles = new HashMap<>();
        userRoles.put("admin", "ADMIN");
        userRoles.put("user", "USER");
    }
    @Override
    public boolean authenticate(String userName, String password) {
        System.out.println("RoleCheckHandler: Checking user role");
        String role = this.userRoles.get(userName);
        if(role != null){
            System.out.println("✓ User has role: " + role);
            if(null != this.nextHandler){
                return this.nextHandler.authenticate(userName, password);
            }
            return true;
        }
        System.out.println("✗ No role assigned");
        return false;
    }
}
// Concrete Handler 3: Account Status Check
class AccountStatusHandler extends AuthenticationHandler {
    private final Set<String> blockedUsers;
    public AccountStatusHandler(){
        this.blockedUsers = new HashSet<>();
        blockedUsers.add("blocked_user");
    }
    @Override
    public boolean authenticate(String userName, String password) {
        System.out.println("AccountStatusHandler: Checking account status");
        if(blockedUsers.contains(userName)){
            System.out.println("x Account is blocked");
            return false;
        }
        System.out.println("✓ Account is active");
        if(null != nextHandler){
            return this.nextHandler.authenticate(userName, password);
        }
        return true;
    }
}