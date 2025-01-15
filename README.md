                                            JWT Flow in an application
<img src="https://github.com/user-attachments/assets/96db2f70-0d80-405c-bc90-6a81e3b38866" alt="JWT Flow chart client and server">

### Code Flow Summary
1. **Login Request**:
   - `AdminService` -> `AuthenticationManager` -> `DaoAuthenticationProvider` -> `MyUserDetailsService` -> `JwtService` -> Response with JWT

2. **Subsequent Requests**:
   - `JwtFilter` -> `JwtService` -> `SecurityContextHolder` -> Access to protected resources

-----------------------------------------------------------------------------------------------------------------------------------------------

   **Detailed**
1. User Login Request
- The user sends a login request to the `/login` endpoint with their username and password.
  
2. Authentication Process
- The `AdminService` class handles the login request.
- The `verify` method in `AdminService` creates a `UsernamePasswordAuthenticationToken` with the provided username and password.
- This token is passed to the `AuthenticationManager` for authentication.

3. AuthenticationManager
- The `AuthenticationManager` uses the `DaoAuthenticationProvider` to authenticate the user.
- The `DaoAuthenticationProvider` uses the `MyUserDetailsService` to load the user details from the database.
- If the credentials are valid, the user is authenticated.

4. JWT Generation
- Upon successful authentication, the `JwtService` generates a JWT for the user.
- The JWT is returned as part of the response.

5. Subsequent Requests
It intercepts HTTP requests to validate token  
- For any subsequent requests, the `JwtFilter` intercepts the request.
- It checks the `Authorization` header for a JWT.
  (The line String authHeader = request.getHeader("Authorization");
   retrieves the value of the Authorization header from the HTTP request.
  This header typically contains the JWT token prefixed with "Bearer ".
   The token is then used for authentication and authorization purposes in the application.)

- If a valid JWT is found, it extracts the username and validates the token.
- The `JwtFilter` sets the authentication in the `SecurityContextHolder`.

### 6. Security Configuration
- The `SecurityConfig` class configures the security settings.
- It disables CSRF, permits access to certain endpoints, and requires authentication for others.
- It adds the `JwtFilter` before the `UsernamePasswordAuthenticationFilter`.

The JwtFilter is added before the UsernamePasswordAuthenticationFilter to ensure that the JWT token is validated before the username and password authentication process. 

Token Validation: The JwtFilter checks the Authorization header for a JWT token and validates it. If the token is valid, it sets the authentication in the SecurityContextHolder.  

Authentication Bypass: By placing the JwtFilter before the UsernamePasswordAuthenticationFilter, the application can bypass the username and password authentication if a valid JWT token is present. This allows for stateless authentication using tokens.  

Security Context: If the JwtFilter successfully validates the token, it sets the authentication in the SecurityContextHolder, which means that subsequent filters, including the UsernamePasswordAuthenticationFilter, will see the user as already authenticated.

